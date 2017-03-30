package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserEvaluateDao;
import com.wenyuankeji.spring.model.UserEvaluateModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserEvaluateDaoImpl implements IUserEvaluateDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public UserEvaluateModel searchUserEvaluate(int evaid) throws BaseException {
		UserEvaluateModel userEvaluateModel = null;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserEvaluateModel WHERE evaid=?";
			
			Query query = session.createQuery(hql);
			query.setInteger(0, evaid);
			
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				userEvaluateModel = (UserEvaluateModel) oList.get(0);
			}
			
			return userEvaluateModel;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}


	@Override
	public List<UserEvaluateModel> searchUserEvaluate(String userid,String bindphone,String startTime, String endTime, int page, int rows)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM UserEvaluateModel WHERE deleted = 0 ");
			//评论者ID
			if (userid !=null && userid.trim().length() > 0) {
				sql.append(" and userid like '%"+ userid +"%' ");
			}
			
			if (bindphone !=null && bindphone.trim().length() > 0) {
				sql.append(" and userinfo.username like '%"+ bindphone +"%' ");
			}
			
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
			List<UserEvaluateModel> userEvaluateModels = (List<UserEvaluateModel>) query.list();

			return userEvaluateModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}


	@Override
	public int searchUserEvaluateCount(String userid,String bindphone,String startTime, String endTime) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM UserEvaluateModel WHERE deleted = 0 ");
			//评论者ID
			if (userid !=null && userid.trim().length() > 0) {
				sql.append(" and userid like '%"+ userid +"%' ");
			}
			
			if (bindphone !=null && bindphone.trim().length() > 0) {
				sql.append(" and userinfo.username like '%"+ bindphone +"%' ");
			}
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
			List<UserEvaluateModel> userEvaluateModels = (List<UserEvaluateModel>) query.list();

			return userEvaluateModels.size();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}


	@Override
	public boolean deleteUserEvaluate(int evaid) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer("UPDATE UserEvaluateModel SET deleted=? WHERE evaid = ? ");
		Query query = session.createQuery(sql.toString());
		
		query.setInteger(0, 1);
		query.setInteger(1, evaid);

		int isok = query.executeUpdate();

		if (isok > 0) {
			return true;
		}
		return false;
	}
}
