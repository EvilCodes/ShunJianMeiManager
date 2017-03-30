package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserProfessionLevelDao;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserProfessionLevelDaoImpl implements IUserProfessionLevelDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfessionLevelModel> searchUserProfessionLevel() throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserProfessionLevelModel";
			Query query = session.createQuery(hql);

			List<UserProfessionLevelModel> userProfessionLevelList = null;
			if (query.list() != null && query.list().size() > 0) {
				userProfessionLevelList = (List<UserProfessionLevelModel>) query.list();
			}
			return userProfessionLevelList;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
