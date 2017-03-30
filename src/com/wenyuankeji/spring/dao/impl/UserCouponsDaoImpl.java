package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserCouponsDao;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserCouponsDaoImpl implements IUserCouponsDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public int addUserCoupons(UserCouponsModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "insert into user_coupons (userid,couponid,orderid,mobile,createtime,source) "
					+ "values('"+o.getUserid()+"','"+o.getCouponid()+"','"+o.getOrderid()+"','"+o.getMobile()+"',SYSDATE(),'"+o.getSource()+"')";
			
			Query query = session.createSQLQuery(sql);

			int isok = query.executeUpdate();
			
			return isok;
			
		} catch (Exception e) {
 			throw new BaseException(e.getMessage());
		}
	}
	
}
