package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IOrderinfoDao;
import com.wenyuankeji.spring.model.OrderinfoModel;

@Repository
public class OrderinfoDaoImpl implements IOrderinfoDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public OrderinfoModel searchOrderinfo(int orderid) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " FROM OrderinfoModel WHERE orderid=" + orderid;
		Query query = session.createQuery(hql);

		@SuppressWarnings("unchecked")
		List<OrderinfoModel> orderinfo = (List<OrderinfoModel>) query.list();
		if (orderinfo != null && orderinfo.size() > 0) {
			return orderinfo.get(0);
		} else {
			return null;
		}

	}

	@Override
	public int searchOrderinfo(int customerid, int userid, int storeid,
			String startTime, String endTime, int paystate, int orderid,
			String customerTelephone,String userTelephone) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " FROM OrderinfoModel AS A";

		hql += " WHERE A.userinfo.usertype = 1 ";
		if (customerid > 0) {
			hql += " AND A.customerid like '%" + customerid + "%'  ";
		}
		if (userid > 0) {
			hql += " AND A.userid like '%" + userid + "%'  ";
		}
		if (storeid > 0) {
			hql += " AND A.storeid like '%" + storeid + "%'  ";
		}
		if (startTime != "") {
			hql += " AND A.createtime >='" + startTime + " 00:00:00'";
		}
		if (endTime != "") {
			hql += " AND A.createtime <='" + endTime + " 23:59:59'";
		}
		if (paystate > 0) {
			if(paystate == 7){
				hql += " AND A.paystate IN (7,8)";
				hql += " AND A.orderid IN (SELECT B.orderid FROM UserEvaluateModel AS B WHERE A.orderid = B.orderid )";
			}else{
				hql += " AND A.paystate=" + paystate;
			}
		}
		if (orderid > 0) {
			hql += " AND A.orderid like '%" + orderid + "%'  ";
		}
		
		if (!customerTelephone.equals("0")) {
			hql += " AND A.userinfo.username like '%" + customerTelephone+"%' ";
			//hql += " AND userinfo.userid=" + 205;
		}
		if (!userTelephone.equals("0")) {
			hql += " AND A.usersubinfo_contactmobile like '%" + userTelephone+"%' ";
		}
		
		hql += " ORDER BY A.createtime DESC";

		Query query = session.createQuery(hql);

		int result = query.list().size();

		return result;
	}

	@Override
	public List<OrderinfoModel> searchOrderinfo(int customerid, int userid,
			int storeid, String startTime, String endTime, int paystate,
			int orderid, String customerTelephone,String userTelephone, int page, int rows) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " FROM OrderinfoModel AS A";

		hql += " WHERE A.userinfo.usertype = 1 ";
		if (customerid > 0) {
			hql += " AND A.customerid like '%" + customerid + "%'  ";
		}
		if (userid > 0) {
			hql += " AND A.userid like '%" + userid + "%'  ";
		}
		if (storeid > 0) {
			hql += " AND A.storeid like '%" + storeid + "%'  ";
		}
		if (startTime != "") {
			hql += " AND A.createtime >='" + startTime + " 00:00:00'";
		}
		if (endTime != "") {
			hql += " AND A.createtime <='" + endTime + " 23:59:59'";
		}
		if (paystate > 0) {
			if(paystate == 7){
				hql += " AND A.paystate IN (7,8,9,10)";
				hql += " AND A.orderid IN (SELECT B.orderid FROM UserEvaluateModel AS B WHERE A.orderid = B.orderid )";
			}else{
				hql += " AND A.paystate=" + paystate;
			}
		}
		if (orderid > 0) {
			hql += " AND A.orderid like '%" + orderid + "%'  ";
		}
		
		if (!customerTelephone.equals("0")) {
			hql += " AND A.userinfo.username like '%" + customerTelephone+"%' ";
			//hql += " AND userinfo.userid=" + 205;
		}
		if (!userTelephone.equals("0")) {
			hql += " AND A.usersubinfo_contactmobile like '%" + userTelephone+"%' ";
		}
		
		hql += " ORDER BY A.createtime DESC";

		Query query = session.createQuery(hql);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);

		@SuppressWarnings("unchecked")
		List<OrderinfoModel> searchModelList = query.list();

		/*
		 * List<Object[]> objList = (List<Object[]>) query.list();
		 * List<OrderinfoModel> searchModelList = new
		 * ArrayList<OrderinfoModel>(); for (Object[] obj : objList) {
		 * OrderinfoModel searchModel = new OrderinfoModel();
		 * searchModel.setOrderid(Integer.parseInt(obj[0].toString()));
		 * searchModel.setAmount(Double.parseDouble(obj[1].toString()));
		 * searchModel.setPaystate(Integer.parseInt(obj[2].toString()));
		 * searchModelList.add(searchModel); }
		 */

		return searchModelList;
	}

	@Override
	public boolean updateOrderinfo(OrderinfoModel orderinfo) {
		return false;
	}

	@Override
	public List<OrderinfoModel> searchOrderExceptionInfo(int paystate,
			int page, int rows) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer(
				"FROM OrderinfoModel WHERE paystate =" + paystate);
		Query query = session.createQuery(sql.toString());
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);

		@SuppressWarnings("unchecked")
		List<OrderinfoModel> orderinfoModels = (List<OrderinfoModel>) query
				.list();
		return orderinfoModels;
	}

	@Override
	public int searchOrderExceptionInfoCount(int paystate) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer(
				"FROM OrderinfoModel WHERE paystate =" + paystate);
		Query query = session.createQuery(sql.toString());
		@SuppressWarnings("unchecked")
		List<OrderinfoModel> orderinfoModels = (List<OrderinfoModel>) query
				.list();
		return orderinfoModels.size();
	}

	@Override
	public List<OrderinfoModel> searchOrderCountInfo(int paystate,
			String startTime, String endTime, int page, int rows) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer(
				"FROM OrderinfoModel WHERE paystate=" + paystate + " ");
		// 开始时间
		if (startTime != null && startTime.trim().length() > 0) {
			sql.append(" and createtime>='" + startTime + "' ");
		}
		// 结束时间
		if (endTime != null && endTime.trim().length() > 0) {
			sql.append(" and createtime<='" + endTime + "' ");
		}
		Query query = session.createQuery(sql.toString());

		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);

		@SuppressWarnings("unchecked")
		List<OrderinfoModel> orderinfoModels = (List<OrderinfoModel>) query
				.list();

		return orderinfoModels;
	}

	@Override
	public int searchOrderCountInfoCount(String paystate, String startTime,
			String endTime) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer(
				"FROM OrderinfoModel WHERE 1=1");
		//paystate=""查询全部状态
		if(!("").equals(paystate)){
			sql.append(" and paystate IN (" + paystate + ") ");
		}
		// 开始时间
		if (startTime != null && startTime.trim().length() > 0) {
			sql.append(" and createtime>='" + startTime + " 00:00:00' ");
		}
		// 结束时间
		if (endTime != null && endTime.trim().length() > 0) {
			sql.append(" and createtime<='" + endTime + " 23:59:59' ");
		}
		Query query = session.createQuery(sql.toString());

		@SuppressWarnings("unchecked")
		List<OrderinfoModel> orderinfoModels = (List<OrderinfoModel>) query
				.list();

		return orderinfoModels.size();
	}

	@Override
	public boolean updateOrderinfoState(int orderid, int state) {

		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE OrderinfoModel SET paystate = ? WHERE orderid = ? ";
		Query query = session.createQuery(hql);
		query.setInteger(0, state);
		query.setInteger(1, orderid);
		
		return query.executeUpdate() > 0;
	}

	@Override
	public String searchOrderinfoCount(String startTime, String endTime) {
		String result = "";

		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT SUM(couponamount) FROM orderinfo WHERE 1=1 ";
		
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

		return result;
	}
	@Override
	public List<String> searchOrderServicenameList(int orderid) {

		Session session = sessionFactory.getCurrentSession();
		
		String sql = "select t2.servicename, t1.materialname from  orderdetail t1 ";
		sql += "INNER JOIN serviceattribute t2 ON ";
		sql += "t1.servicecode = t2.servicecode ";
		sql += "where t1.orderid = '" + orderid + "' ";

		Query query = session.createSQLQuery(sql);

		List<String> servicenameList = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<Object[]> objList = (List<Object[]>) query.list();
		// List<String> servicenameList = query.list();
		for (Object[] obj : objList) {
			if (obj[1] == null) {
				servicenameList.add(obj[0].toString());
			} else {
				servicenameList.add(obj[0].toString() + "("
						+ obj[1].toString() + ")");
			}
		}

		return servicenameList;
	}
}
