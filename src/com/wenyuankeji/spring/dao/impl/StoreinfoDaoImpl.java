package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IStoreinfoDao;
import com.wenyuankeji.spring.model.StoreinfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class StoreinfoDaoImpl implements IStoreinfoDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public StoreinfoModel searchStoreinfo(int storeid) throws BaseException {
		
		StoreinfoModel storeinfoModel = null;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM StoreinfoModel WHERE storeid=?";
			
			Query query = session.createQuery(hql);
			query.setInteger(0, storeid);
			
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				storeinfoModel = (StoreinfoModel) oList.get(0);
			}
			
			return storeinfoModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateStoreinfo(StoreinfoModel storeinfoModel) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer("UPDATE StoreinfoModel SET state=? WHERE storeid = ? ");
		Query query = session.createQuery(sql.toString());
		
		query.setInteger(0, storeinfoModel.getState());
		query.setInteger(1, storeinfoModel.getStoreid());

		int isok = query.executeUpdate();

		if (isok > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<StoreinfoModel> searchStoreinfo(String storeid, String startTime, String endTime, String state,
			String name, int page, int rows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM StoreinfoModel WHERE 1=1 ");
			//商户ID
			if (storeid !=null && storeid.trim().length() > 0) {
				sql.append(" and storeid='"+ storeid +"' ");
			}
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and createtime>='"+ startTime +" 00:00:00' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and createtime<='"+ endTime +" 23:59:59' ");
			}
			//审核状态
			if (state !=null && state.trim().length() > 0 && !state.equals("-1")) {
				if("0".equals(state)){
					sql.append(" and state != 2 ");
				}else{
					sql.append(" and state = 2 ");
				}
			}
			//商户名称
			if (name !=null && name.trim().length() > 0) {
				sql.append(" and name like '%"+ name +"%' ");
			}
			Query query = session.createQuery(sql.toString());

			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);

			@SuppressWarnings("unchecked")
			List<StoreinfoModel> storeinfoModels = (List<StoreinfoModel>) query.list();

			return storeinfoModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchStoreinfoCount(String storeid, String startTime, String endTime, String state, String name)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM StoreinfoModel WHERE 1=1 ");
			//商户ID
			if (storeid !=null && storeid.trim().length() > 0) {
				sql.append(" and storeid='"+ storeid +"' ");
			}
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and createtime>='"+ startTime +" 00:00:00' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and createtime<='"+ endTime +" 23:59:59' ");
			}
			//审核状态
			if (state !=null && state.trim().length() > 0 && !state.equals("-1")) {
				if("0".equals(state)){
					sql.append(" and state != 2 ");
				}else{
					sql.append(" and state = 2 ");
				}
			}
			//商户名称
			if (name !=null && name.trim().length() > 0) {
				sql.append(" and name like '%"+ name +"%' ");
			}
			Query query = session.createQuery(sql.toString());
			@SuppressWarnings("unchecked")
			List<StoreinfoModel> storeinfoModels = (List<StoreinfoModel>) query.list();

			return storeinfoModels.size();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int addInitStoreinfo(StoreinfoModel storeinfoModel) throws Exception {
		int result = 0;
		Session session = sessionFactory.getCurrentSession();
		Serializable isok = session.save(storeinfoModel);
		if (Integer.parseInt(isok.toString()) > 0) {
			result = Integer.parseInt(isok.toString());
		}
		return result;
	}

	@Override
	public boolean updateStore(StoreinfoModel storeinfo) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("UPDATE StoreinfoModel SET ");
			hql.append("name=?, ");
			hql.append("provinceid=?, ");
			hql.append("cityid=?, ");
			hql.append("districtid=?, ");
			hql.append("address=?, ");
			hql.append("tel=?, ");
			hql.append("carnumber=?, ");
			hql.append("businesshours=?, ");
			hql.append("areaid=?, ");
			hql.append("empiricalmode=?, ");
			hql.append("intro=?, ");
			hql.append("bossname=?, ");
			hql.append("bossmobile=?, ");
			hql.append("storemanagername=?, ");
			hql.append("storemanagermobile=?, ");
			hql.append("state=?, ");
			hql.append("longitude=?, ");
			hql.append("latitude=?, ");
			hql.append("ownername=?, ");
			hql.append("bank=?, ");
			hql.append("cardnumber=?, ");
			hql.append("allocation=? ");
			hql.append("WHERE ");
			hql.append("storeid=? ");
			Query query = session.createQuery(hql.toString());

			query.setString(0, storeinfo.getName());
			query.setInteger(1, storeinfo.getProvinceid());
			query.setInteger(2, storeinfo.getCityid());
			query.setInteger(3, storeinfo.getDistrictid());
			query.setString(4, storeinfo.getAddress());
			query.setString(5, storeinfo.getTel());
			query.setInteger(6, storeinfo.getCarnumber());
			query.setString(7, storeinfo.getBusinesshours());
			query.setInteger(8, storeinfo.getAreaid());		
			query.setInteger(9, storeinfo.getEmpiricalmode());
			query.setString(10, storeinfo.getIntro());
			query.setString(11, storeinfo.getBossname());
			query.setString(12, storeinfo.getBossmobile());
			query.setString(13, storeinfo.getStoremanagername());
			query.setString(14, storeinfo.getStoremanagermobile());
			query.setInteger(15, storeinfo.getState());
			query.setString(16, storeinfo.getLongitude());
			query.setString(17, storeinfo.getLatitude());
			query.setString(18, storeinfo.getOwnername());
			query.setString(19, storeinfo.getBank());
			query.setString(20, storeinfo.getCardnumber());
			query.setString(21, storeinfo.getAllocation());
			
			query.setInteger(22, storeinfo.getStoreid());

			int isok = query.executeUpdate();

			if (isok > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}
}
