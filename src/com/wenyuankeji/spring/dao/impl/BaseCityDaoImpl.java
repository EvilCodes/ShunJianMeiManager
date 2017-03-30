package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.wenyuankeji.spring.dao.IBaseCityDao;
import com.wenyuankeji.spring.model.BaseCityModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseCityDaoImpl implements IBaseCityDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public BaseCityModel searchBaseCity(int cityId) throws BaseException {

		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserinfoModel WHERE cityId=?";
			Query query = session.createQuery(hql);
			query.setInteger(0, cityId);

			BaseCityModel baseCityModel = new BaseCityModel();
			if (query.list() != null && query.list().size() > 0) {
				baseCityModel = (BaseCityModel) query.list().get(0);
			}
			return baseCityModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseCityModel> searchBaseCityByProvinceId(int provinceId)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM BaseCityModel WHERE provinceid=?";
			Query query = session.createQuery(hql);

			query.setInteger(0, provinceId);

			List<BaseCityModel> baseCityList = new ArrayList<BaseCityModel>();
			if (query.list() != null && query.list().size() > 0) {
				baseCityList = (List<BaseCityModel>)query.list();
			}
			return baseCityList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
