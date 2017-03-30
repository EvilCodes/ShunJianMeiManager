package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserTradelogDao;
import com.wenyuankeji.spring.model.UserTradelogModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserTradelogDaoImpl implements IUserTradelogDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<UserTradelogModel> searchUserTradelog(String startTime, String endTime)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "SELECT logid,walletid,orderid,amount,logtype,remark,createtime FROM user_tradelog WHERE 1=1 ";

			if(startTime != ""){
				sql += " AND createtime >= '" + startTime + " 00:00:00'";
			}
			if(endTime != ""){
				sql += " AND createtime <= '" + endTime + " 23:59:59'";
			}

			sql += " order by createtime desc";

			Query query = session.createSQLQuery(sql);

			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			List<UserTradelogModel> searchModelList = new ArrayList<UserTradelogModel>();
			for (Object[] obj : objList) {
				UserTradelogModel searchModel = new UserTradelogModel();
				searchModel.setLogid(Integer.parseInt(obj[0].toString()));
				searchModel.setWalletid(Integer.parseInt(obj[1].toString()));
				if(obj[2] != null){
					searchModel.setOrderid(Integer.parseInt(obj[2].toString()));
				}
				searchModel.setAmount(Float.parseFloat(obj[3].toString()));
				searchModel.setLogtype(Integer.parseInt(obj[4].toString()));
				searchModel.setRemark(obj[5].toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(obj[6].toString());
				searchModel.setCreatetime(date);
				searchModelList.add(searchModel);
			}

			return searchModelList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public List<UserTradelogModel> searchUserTradelog(String startTime,
			String endTime, int page, int rows) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "SELECT logid,walletid,orderid,amount,logtype,remark,createtime FROM user_tradelog WHERE 1=1 ";

			if(startTime != ""){
				sql += " AND createtime >= '" + startTime + " 00:00:00'";
			}
			if(endTime != ""){
				sql += " AND createtime <= '" + endTime + " 23:59:59'";
			}

			sql += " order by createtime desc";

			Query query = session.createSQLQuery(sql);

			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			
			@SuppressWarnings("unchecked")
			List<Object[]> objList = (List<Object[]>) query.list();
			List<UserTradelogModel> searchModelList = new ArrayList<UserTradelogModel>();
			for (Object[] obj : objList) {
				UserTradelogModel searchModel = new UserTradelogModel();
				searchModel.setLogid(Integer.parseInt(obj[0].toString()));
				searchModel.setWalletid(Integer.parseInt(obj[1].toString()));
				if(obj[2] != null){
					searchModel.setOrderid(Integer.parseInt(obj[2].toString()));
				}
				searchModel.setAmount(Float.parseFloat(obj[3].toString()));
				searchModel.setLogtype(Integer.parseInt(obj[4].toString()));
				searchModel.setRemark(obj[5].toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = sdf.parse(obj[6].toString());
				searchModel.setCreatetime(date);
				searchModelList.add(searchModel);
			}

			return searchModelList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean addUserTradelog(UserTradelogModel userTradelog)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Serializable isok = session.save(userTradelog);
			if (Integer.parseInt(isok.toString()) > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

	@Override
	public String searchCountUserTradelog(String logtype, String startTime,
			String endTime) throws BaseException {
		String result = "";
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "SELECT SUM(amount) FROM user_tradelog WHERE 1=1 ";
			
			sql += " AND logtype IN ( " + logtype + ") ";

			if(startTime != ""){
				sql += " AND createtime >= '" + startTime + " 00:00:00'";
			}
			if(endTime != ""){
				sql += " AND createtime <= '" + endTime + " 23:59:59'";
			}

			Query query = session.createSQLQuery(sql);
			Object obj = query.list().get(0);
			if(obj == null){
				result = "0";
			}else{
				result = obj.toString();
			}
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return result;
	}

}
