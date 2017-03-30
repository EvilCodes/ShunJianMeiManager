package com.wenyuankeji.spring.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseAdmininfoDao;
import com.wenyuankeji.spring.model.BaseAdmininfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseAdmininfoDaoImpl implements IBaseAdmininfoDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public BaseAdmininfoModel searchBaseAdmininfoModel(String username,
			String password) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseAdmininfoModel WHERE username=? and password=? and state=1 ";

			Query query = session.createQuery(hql);
			query.setString(0, username);
			query.setString(1, password);

			BaseAdmininfoModel admininfo = null;

			if (query.list() != null && query.list().size() > 0) {
				admininfo = (BaseAdmininfoModel) query.list().get(0);
			}

			return admininfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public boolean updateTestSQL(String sql) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {

			Query query = session.createSQLQuery(sql);

			return query.executeUpdate() != 0;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
}
