package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserProfessionLevelServiceDao;
import com.wenyuankeji.spring.model.UserProfessionLevelServiceModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserProfessionLevelServiceDaoImpl implements
		IUserProfessionLevelServiceDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<UserProfessionLevelServiceModel> searchUserProfessionLevelService(
			int levelid) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserProfessionLevelServiceModel WHERE levelid=? order by id";
			Query query = session.createQuery(hql);

			query.setInteger(0, levelid);
			
			@SuppressWarnings("unchecked")
			List<UserProfessionLevelServiceModel> userProfessionLevelService = (List<UserProfessionLevelServiceModel>)query.list();
			return userProfessionLevelService;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public UserProfessionLevelServiceModel searchUserProfessionLevelService(
			int levelid, String servicecode) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UserProfessionLevelServiceModel WHERE levelid=? and servicecode=?";
			Query query = session.createQuery(hql);

			query.setInteger(0, levelid);
			query.setString(1, servicecode);
			
			UserProfessionLevelServiceModel userProfessionLevelService = null;
			if (query.list() != null && query.list().size() > 0) {
				userProfessionLevelService = (UserProfessionLevelServiceModel) query.list().get(0);
			}
			
			return userProfessionLevelService;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		
	}

	@Override
	public boolean addOrUpdateUserProfessionLevelService(UserProfessionLevelServiceModel userProfessionLevelServiceModel)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		boolean result = false;
		try {
			String searchSql = "FROM UserProfessionLevelServiceModel WHERE levelid = ? AND servicecode = ?";
			Query searchQuery = session.createQuery(searchSql.toString());
			searchQuery.setInteger(0, userProfessionLevelServiceModel.getLevelid());
			searchQuery.setString(1, userProfessionLevelServiceModel.getServicecode());
			
			if (searchQuery.list() != null && searchQuery.list().size() > 0) {
				//修改
				String updateString = "update UserProfessionLevelServiceModel set price = ? where levelid = ? and servicecode = ?";
				Session updateSession = sessionFactory.getCurrentSession();
				Query updateQuery = updateSession.createQuery(updateString);
				updateQuery.setInteger(0, userProfessionLevelServiceModel.getPrice());
				updateQuery.setInteger(1, userProfessionLevelServiceModel.getLevelid());
				updateQuery.setString(2, userProfessionLevelServiceModel.getServicecode());
				int isok = updateQuery.executeUpdate();
				if (isok > 0) {
					result =  true;
				}
			}else{
				//新增
				Session insertSession = sessionFactory.getCurrentSession();
				Serializable isok = insertSession.save(userProfessionLevelServiceModel);
				if (Integer.parseInt(isok.toString()) > 0) {
					result = true;
				}
			}
			return result;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
