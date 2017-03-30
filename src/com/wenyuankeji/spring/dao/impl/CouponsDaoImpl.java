package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.ICouponsDao;
import com.wenyuankeji.spring.model.CouponsModel;
import com.wenyuankeji.spring.model.UserCouponsModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class CouponsDaoImpl implements ICouponsDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public int addCoupons(CouponsModel o) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			return id;
		}catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public Integer addUserCouponsModel(UserCouponsModel o)throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			return id;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
}
