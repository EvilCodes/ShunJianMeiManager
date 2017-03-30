package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.dao.ICouponsDao;
import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.service.ICouponSystemService;
import com.wenyuankeji.spring.util.BaseException;
import com.wenyuankeji.spring.util.Constant;
import com.wenyuankeji.spring.util.ShunJianMeiUtil;

@Service
public class CouponSystemServiceImpl implements ICouponSystemService{

	@Autowired
	private IBaseConfigDao baseConfigDao;
	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private IUserinfoDao userinfoDao;
	
	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode) throws BaseException {
		return baseConfigDao.searchBaseConfigByCode(configcode);
	}

	@Override
	public List<BaseConfigModel> searchBaseConfigList(String configcode)throws BaseException{
		return baseConfigDao.searchBaseConfigList(configcode);
	}
	
	@Override
	public boolean updateRegCoupon(int hl,int rf,int tf,int xc,int xjc,int tc)throws BaseException{
		
		try {
			Map<String, Integer> regCouponMap = new HashMap<String, Integer>();
			regCouponMap.put("count", hl+rf+tf+xc+xjc+tc);
			regCouponMap.put("hl", hl);
			regCouponMap.put("rf", rf);
			regCouponMap.put("tf", tf);
			regCouponMap.put("xc", xc);
			regCouponMap.put("xjc", xjc);
			regCouponMap.put("tc", tc);
			
			for (String key : regCouponMap.keySet()) {
				baseConfigDao.updateBaseConfig("RegCoupon",key,regCouponMap.get(key).toString());
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean updateBaseConfig(String code, String minValue, String maxValue,int type) throws BaseException{
		try {
			if (type == 1 ) {
				boolean result1 = baseConfigDao.updateBaseConfig(Constant.CONFIGCODE_05+code, minValue);
				boolean result2 = baseConfigDao.updateBaseConfig(Constant.CONFIGCODE_06+code, maxValue);
				if (result1 == true & result2 == true) {
					return true;
				}
			}else{
				boolean result1 = baseConfigDao.updateBaseConfig(Constant.CONFIGCODE_07+code, minValue);
				boolean result2 = baseConfigDao.updateBaseConfig(Constant.CONFIGCODE_08+code, maxValue);
				if (result1 == true & result2 == true) {
					return true;
				}
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean addUserCoupon(String txtUserCoupon, String mobile, String amount,int userid)throws BaseException {
		
		try {
			Date date = new Date();
			
			//添加优惠卷信息
			CouponsModel couponsModel = new CouponsModel();
			String couponname = ShunJianMeiUtil.formatCouponType(txtUserCoupon);
			couponsModel.setCouponname(couponname);
			couponsModel.setCouponcode("Y"+ShunJianMeiUtil.getYYYYMMDD());
			couponsModel.setAmount(Integer.parseInt(amount));
			couponsModel.setUsecondition("0");
			couponsModel.setStarttime(date);
			couponsModel.setEndtime(ShunJianMeiUtil.getNextMonth6());
			couponsModel.setDeleted(false);
			couponsModel.setRemark("平台赠送优惠券");
			couponsModel.setCreatetime(date);
			couponsModel.setServicecode(txtUserCoupon.toLowerCase());
			
			couponsDao.addCoupons(couponsModel);
			
			if (couponsDao.addCoupons(couponsModel) > 0) {
				//添加我的优惠卷
				UserCouponsModel userCouponsModel =  new UserCouponsModel();
				
				userCouponsModel.setUserid(userid);
				userCouponsModel.setCouponid(couponsModel.getCouponid());
				userCouponsModel.setOrderid(0);
				userCouponsModel.setCreatetime(date);
				userCouponsModel.setSource("0");
				Integer id = couponsDao.addUserCouponsModel(userCouponsModel);
				if (id > 0) {
					return true;
				}
			}
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

	@Override
	public UserinfoModel searchUserinfo(String mobile, int type) throws BaseException {
		return userinfoDao.searchUserinfo(mobile, type);
	}

	@Override
	public boolean updateBaseConfig(String configcode, String configvalue)  throws BaseException {
		try {
			boolean result = baseConfigDao.updateBaseConfig(configcode, configvalue);
			if (result) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

}
