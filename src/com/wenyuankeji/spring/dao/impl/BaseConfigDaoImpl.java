package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseConfigDao;
import com.wenyuankeji.spring.model.BaseBusinessareaModel;
import com.wenyuankeji.spring.model.BaseConfigModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseConfigDaoImpl implements IBaseConfigDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode=? order by sortnum,configid";
			Query query = session.createQuery(hql);

			query.setString(0, configcode);

			BaseConfigModel baseConfig = new BaseConfigModel();
			if (query.list() != null && query.list().size() > 0) {
				baseConfig = (BaseConfigModel) query.list().get(0);
			}
			return baseConfig;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	
	@Override
	public boolean updateBaseConfig(String configcode, String configvalue) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer("UPDATE BaseConfigModel ");
		sql.append(" SET configvalue= ?");
		sql.append(" WHERE configcode = ? ");
		Query query = session.createQuery(sql.toString());

		query.setString(0, configvalue);
		query.setString(1, configcode);

		int isok = query.executeUpdate();

		if (isok > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseConfigModel> searchBaseConfigList(String configcode)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode=? order by sortnum,configid";
			Query query = session.createQuery(hql);
			query.setString(0, configcode);

			List<BaseConfigModel> baseConfigList = new ArrayList<BaseConfigModel>();

			if (query.list() != null && query.list().size() > 0) {
				baseConfigList = (List<BaseConfigModel>) query.list();
			}
			return baseConfigList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public BaseConfigModel searchBaseConfigByCode(String configcode,
			String configvalue) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode=? and configvalue=? order by sortnum,configid";
			Query query = session.createQuery(hql);

			query.setString(0, configcode);
			query.setString(1, configvalue);

			BaseConfigModel baseConfig = new BaseConfigModel();
			if (query.list() != null && query.list().size() > 0) {
				baseConfig = (BaseConfigModel) query.list().get(0);
			}
			return baseConfig;

		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateBaseConfig(String configcode, String configvalue,
			String value1) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("UPDATE BaseConfigModel ");
			sql.append(" SET value1= ?");
			sql.append(" WHERE configcode = ? AND configvalue=? ");
			Query query = session.createQuery(sql.toString());

			query.setString(0, value1);
			query.setString(1, configcode);
			query.setString(2, configvalue);

			int isok = query.executeUpdate();

			if (isok > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

	@Override
	public List<BaseConfigModel> searchBaseConfig(String configcode)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM BaseConfigModel WHERE configcode=? order by sortnum,configid";
			Query query = session.createQuery(hql);

			query.setString(0, configcode);

			@SuppressWarnings("unchecked")
			List<BaseConfigModel> baseConfigList = (List<BaseConfigModel>) query
					.list();
			return baseConfigList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateBaseConfigByConfigCode(String configcode,
			String configvalue) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("UPDATE BaseConfigModel ");
			sql.append(" SET configvalue= ?");
			sql.append(" WHERE configcode = ?");
			Query query = session.createQuery(sql.toString());

			query.setString(0, configvalue);
			query.setString(1, configcode);

			int isok = query.executeUpdate();

			if (isok > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean addBaseBusinessarea(BaseBusinessareaModel o)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			if (id > 0) {
				return true;
			}
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean addBaseConfig(BaseConfigModel o)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Integer id = (Integer) session.save(o);
			if (id > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
