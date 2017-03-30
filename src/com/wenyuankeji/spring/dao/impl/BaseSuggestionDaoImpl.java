package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IBaseSuggestionDao;
import com.wenyuankeji.spring.model.BaseSuggestionModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class BaseSuggestionDaoImpl implements IBaseSuggestionDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public BaseSuggestionModel searchBaseSuggestion(int sid) throws BaseException {
		BaseSuggestionModel baseSuggestionModel = null;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM BaseSuggestionModel WHERE sid=?";
			
			Query query = session.createQuery(hql);
			query.setInteger(0, sid);
			
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				baseSuggestionModel = (BaseSuggestionModel) oList.get(0);
			}
			
			return baseSuggestionModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}


	@Override
	public List<BaseSuggestionModel> searchBaseSuggestion(String startTime, String endTime, int page, int rows)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM BaseSuggestionModel WHERE 1=1 ");
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and createtime>='"+ startTime +" 00:00:00' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and createtime<='"+ endTime +" 23:59:59' ");
			}
			Query query = session.createQuery(sql.toString());

			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);

			@SuppressWarnings("unchecked")
			List<BaseSuggestionModel> baseSuggestionModels = (List<BaseSuggestionModel>) query.list();

			return baseSuggestionModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}


	@Override
	public int searchBaseSuggestionCount(String startTime, String endTime) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM BaseSuggestionModel WHERE 1=1 ");
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and createtime>='"+ startTime +" 00:00:00' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and createtime<='"+ endTime +" 23:59:59' ");
			}
			Query query = session.createQuery(sql.toString());


			@SuppressWarnings("unchecked")
			List<BaseSuggestionModel> baseSuggestionModels = (List<BaseSuggestionModel>) query.list();

			return baseSuggestionModels.size();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
}
