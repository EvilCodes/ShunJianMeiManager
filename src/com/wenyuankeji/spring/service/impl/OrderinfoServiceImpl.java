package com.wenyuankeji.spring.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.dao.IUserWalletDao;
import com.wenyuankeji.spring.model.OrderinfoModel;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.model.UserWalletModel;
import com.wenyuankeji.spring.service.IOrderinfoService;
import com.wenyuankeji.spring.util.BaseException;

@Service
public class OrderinfoServiceImpl implements IOrderinfoService {

	@Autowired
	private IOrderinfoDao orderinfoDao;

	@Autowired
	private IUserWalletDao userWalletDao;

	@Autowired
	private IUserTradelogDao userTradeLogDao;
	
	

	@Override
	public OrderinfoModel searchOrderinfo(int orderid) {
		return orderinfoDao.searchOrderinfo(orderid);
	}

	@Override
	public int searchOrderinfo(int customerid, int userid, int storeid,
			String startTime, String endTime, int paystate, int orderid,
			String customerTelephone,String userTelephone) {

		return orderinfoDao.searchOrderinfo(customerid, userid, storeid,
				startTime, endTime, paystate, orderid, customerTelephone, userTelephone);
	}

	@Override
	public List<OrderinfoModel> searchOrderinfo(int customerid, int userid,
			int storeid, String startTime, String endTime, int paystate,
			int orderid,  String customerTelephone,String userTelephone,
			int page, int rows) {

		return orderinfoDao.searchOrderinfo(customerid, userid, storeid,
				startTime, endTime, paystate, orderid, customerTelephone, userTelephone,
				page, rows);
	}

	@Override
	public boolean updateOrderinfo(OrderinfoModel orderinfo) {

		return orderinfoDao.updateOrderinfo(orderinfo);
	}

	@Override
	public List<OrderinfoModel> searchOrderExceptionInfo( int paystate, int page, int rows) {
		return orderinfoDao.searchOrderExceptionInfo(paystate,page, rows);
	}

	@Override
	public int searchOrderExceptionInfoCount(int paystate) {
		return orderinfoDao.searchOrderExceptionInfoCount(paystate);
	}

	@Override
	public List<OrderinfoModel> searchOrderCountInfo(int paystate, String startTime, String endTime, int page,
			int rows) {
		return orderinfoDao.searchOrderCountInfo(paystate, startTime, endTime, page, rows);
	}

	@Override
	public int searchOrderCountInfoCount(String paystate, String startTime, String endTime) {
		return orderinfoDao.searchOrderCountInfoCount(paystate, startTime, endTime);
	}

	@Override
	public boolean updateOrderinfoState(int orderid, int state) {
		return orderinfoDao.updateOrderinfoState(orderid, state);
	}

	@Override
	public boolean addOrderExceptionCompensate(String txtOrderId,
			String txtStoreId, String txtUserId, String txtCustomerId,
			String txtStoreCompensate, String txtStoreGetCompensate,
			String txtUserCompensate, String txtUserGetCompensate,
			String txtCustomerCompensate, String txtCustomerGetCompensate) throws BaseException {

		UserTradelogModel customerTradelog = new UserTradelogModel();
		UserTradelogModel userTradelog = new UserTradelogModel();
		UserTradelogModel storeTradelog = new UserTradelogModel();
		// 用户赔偿
		UserWalletModel customerWalletModel = userWalletDao
				.searchUserWallet(Integer.parseInt(txtCustomerId), 1);
		if(customerWalletModel != null){
			customerTradelog.setWalletid(customerWalletModel.getWalletid());
			customerTradelog.setOrderid(Integer.parseInt(txtOrderId));
			customerTradelog.setLogtype(6);
			customerTradelog.setCreatetime(new Date());
			if (txtCustomerCompensate != "") {
				float blance = customerWalletModel.getBalance();
				float tempBlance = Float.parseFloat(txtCustomerCompensate);
				customerWalletModel.setBalance(blance - tempBlance);
				//userWalletService.updateUserWalletByUserId(customerWalletModel);
				customerTradelog.setRemark("用户赔偿");
				customerTradelog.setAmount(Float.parseFloat(txtCustomerCompensate));
			} else {
				// 用户获得赔偿
				float blance = customerWalletModel.getBalance();
				float tempBlance = Float.parseFloat(txtCustomerGetCompensate);
				customerWalletModel.setBalance(blance + tempBlance);
				//userWalletService.updateUserWalletByUserId(customerWalletModel);
				customerTradelog.setRemark("用户获得赔偿");
				customerTradelog.setAmount(Float.parseFloat(txtCustomerGetCompensate));
			}
		}

		// 美发师赔偿
		UserWalletModel userWalletModel = userWalletDao.searchUserWallet(
				Integer.parseInt(txtUserId), 2);
		if(userWalletModel != null){
			userTradelog.setWalletid(userWalletModel.getWalletid());
			userTradelog.setOrderid(Integer.parseInt(txtOrderId));
			userTradelog.setLogtype(6);
			userTradelog.setCreatetime(new Date());
			if (txtUserCompensate != "") {
				float blance = userWalletModel.getBalance();
				float tempBlance = Float.parseFloat(txtUserCompensate);
				userWalletModel.setBalance(blance - tempBlance);
				//userWalletService.updateUserWalletByUserId(userWalletModel);
				userTradelog.setRemark("美发师赔偿");
				userTradelog.setAmount(Float.parseFloat(txtUserCompensate));
			} else {
				// 美发师获得赔偿
				float blance = userWalletModel.getBalance();
				float tempBlance = Float.parseFloat(txtUserGetCompensate);
				userWalletModel.setBalance(blance - tempBlance);
				//userWalletService.updateUserWalletByUserId(userWalletModel);
				userTradelog.setRemark("美发师获得赔偿");
				userTradelog.setAmount(Float.parseFloat(txtUserGetCompensate));
			}
		}
		// 商户赔偿
		UserWalletModel storeWalletModel = userWalletDao.searchUserWallet(
				Integer.parseInt(txtStoreId), 3);
		if(storeWalletModel != null){
			storeTradelog.setWalletid(storeWalletModel.getWalletid());
			storeTradelog.setOrderid(Integer.parseInt(txtOrderId));
			storeTradelog.setLogtype(6);
			storeTradelog.setCreatetime(new Date());
			if (txtStoreCompensate != "") {
				float blance = storeWalletModel.getBalance();
				float tempBlance = Float.parseFloat(txtStoreCompensate);
				storeWalletModel.setBalance(blance - tempBlance);
				//userWalletService.updateUserWalletByUserId(storeWalletModel);
				storeTradelog.setRemark("商户赔偿");
				storeTradelog.setAmount(Float.parseFloat(txtStoreCompensate));
			} else {
				// 
				float blance = storeWalletModel.getBalance();
				float tempBlance = Float.parseFloat(txtStoreGetCompensate);
				storeWalletModel.setBalance(blance - tempBlance);
				//userWalletService.updateUserWalletByUserId(storeWalletModel);
				storeTradelog.setRemark("商户获得赔偿");
				storeTradelog.setAmount(Float.parseFloat(txtStoreGetCompensate));
			}
		}
		//查不到钱包流水判断 start
		if(customerTradelog.getAmount() > 0){
			if(!userTradeLogDao.addUserTradelog(customerTradelog)){
				return false;
			}
		}
		if(userTradelog.getAmount() > 0){
			if(!userTradeLogDao.addUserTradelog(userTradelog)){
				return false;
			}
		}
		if(storeTradelog.getAmount() > 0){
			if(!userTradeLogDao.addUserTradelog(storeTradelog)){
				return false;
			}
		}
		//end
		
		//执行赔偿 start

		if(customerWalletModel != null){
			if(!userWalletDao.updateUserWalletByUserId(customerWalletModel)){
				return false;
			}
		}

		if(userWalletModel != null){
			if(!userWalletDao.updateUserWalletByUserId(userWalletModel)){
				return false;
			}
		}

		if(storeWalletModel != null){
			if(!userWalletDao.updateUserWalletByUserId(storeWalletModel)){
				return false;
			}
		}
		//end
		
		//修改订单状态
		if(!updateOrderinfoState(Integer.parseInt(txtOrderId), 11)){
			return false;
		}

		return true;
	}

	@Override
	public String searchOrderinfoCount(String startTime, String endTime) {
		return orderinfoDao.searchOrderinfoCount(startTime, endTime);
	}

	@Override
	public String searchOrderServicenameList(int orderid) {
		String servicenameString = "";
		
		List<String> servicenameList = orderinfoDao.searchOrderServicenameList(orderid);
		if (null != servicenameList && servicenameList.size() > 0) {
			for (String servicename : servicenameList) {
				servicenameString += servicename + "  ";
			}
		}
		
		return servicenameString;
	}
	
}
