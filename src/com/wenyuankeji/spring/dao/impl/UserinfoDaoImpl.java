package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserinfoDao;
import com.wenyuankeji.spring.model.UserinfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserinfoDaoImpl implements IUserinfoDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int searchAllUserinfo(UserinfoModel userinfo) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserinfoModel WHERE usertype=1";

		if (userinfo.getUserid() > 0) {
			hql += " AND userid =" + userinfo.getUserid();
		}

		if (userinfo.getUsername() != "" && userinfo.getUsername() != null) {
			hql += " AND username='" + userinfo.getUsername() + "'";
		}

		//昵称
		if (userinfo.getNickname() !=null && userinfo.getNickname() .trim().length() > 0) {
			hql +=" and nickname like '%"+ userinfo.getNickname()  +"%' ";
		}
		//性别
		if (userinfo.getSex() >= 0) {
			hql += " AND sex='" + userinfo.getSex() + "'";
		}
		
		
		//注册开始时间
		if (userinfo.getDateFrom() !=null && userinfo.getDateFrom().trim().length() > 0) {
			hql += " and createtime>='"+ userinfo.getDateFrom() +" 00:00:00' ";
		}
		//注册结束时间
		if (userinfo.getDateTo() !=null && userinfo.getDateTo().trim().length() > 0) {
			hql += " and createtime<='"+ userinfo.getDateTo() +" 23:59:59' ";
		}
		
		hql += " ORDER BY createtime DESC";

		Query query = session.createQuery(hql);
		return query.list().size();
	}

	@Override
	public List<UserinfoModel> searchAllUserinfo(UserinfoModel userinfo,
			int page, int rows) {

		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserinfoModel WHERE usertype=1";

		if (userinfo.getUserid() > 0) {
			hql += " AND userid=" + userinfo.getUserid();
		}

		if (userinfo.getUsername() != "" && userinfo.getUsername() != null) {
			hql += " AND username='" + userinfo.getUsername() + "'";
		}

		//昵称
		if (userinfo.getNickname() !=null && userinfo.getNickname() .trim().length() > 0) {
			hql +=" and nickname like '%"+ userinfo.getNickname()  +"%' ";
		}
		//性别
		if (userinfo.getSex() >= 0) {
			hql += " AND sex='" + userinfo.getSex() + "'";
		}
		
		
		//注册开始时间
		if (userinfo.getDateFrom() !=null && userinfo.getDateFrom().trim().length() > 0) {
			hql += " and createtime>='"+ userinfo.getDateFrom() +" 00:00:00' ";
		}
		//注册结束时间
		if (userinfo.getDateTo() !=null && userinfo.getDateTo().trim().length() > 0) {
			hql += " and createtime<='"+ userinfo.getDateTo() +" 23:59:59' ";
		}
		
		hql += " ORDER BY createtime DESC";

		Query query = session.createQuery(hql);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);

		@SuppressWarnings("unchecked")
		List<UserinfoModel> userinfoList = query.list();
		return userinfoList;
	}

	@Override
	public boolean updateUserinfo(UserinfoModel userinfo) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE UserinfoModel SET userstate=? WHERE userid = ?";
		Query query = session.createQuery(hql);

		query.setInteger(0, userinfo.getUserstate());
		query.setInteger(1, userinfo.getUserid());

		int isok = query.executeUpdate();

		if (isok > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserinfoModel searchUserinfo(String username, int type) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM UserinfoModel WHERE username = ? and usertype= ?";
			
			Query query = session.createQuery(hql);
			query.setString(0, username);
			query.setInteger(1, type);
			
			UserinfoModel userinfoModels = null;
			
			if (query.list() != null  && query.list().size() > 0) {
				userinfoModels = (UserinfoModel) query.list().get(0);
			}

			return userinfoModels;
			
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public UserinfoModel searchUserinfoById(int userid) throws BaseException {
		UserinfoModel userinfoModel = new UserinfoModel();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserinfoModel WHERE userid=?";
		Query query = session.createQuery(hql);

		query.setInteger(0, userid);

		@SuppressWarnings("unchecked")
		List<Object> olist = query.list();
		
		if(null != olist && olist.size() > 0 ){
			userinfoModel = (UserinfoModel) olist.get(0);
		}
		return userinfoModel;
	}

	
	@Override
	public boolean updateUserInfoByUserId(UserinfoModel userInfo) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userInfo);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
		/*
		String hql = "update UserinfoModel set username = ?,password=?,nickname=?,sex=?,birthday=?,provinceid=?,cityid=?,bindphone=?,usertype=?,userstate=?,updatetime=? where userid = ? ";
		Query query = session.createQuery(hql);
		int i = 0;
		query.setString(i, userInfo.getUsername());
		query.setString(++i, userInfo.getPassword());
		query.setString(++i, userInfo.getNickname());
		query.setInteger(++i, userInfo.getSex());
		query.setDate(++i, userInfo.getBirthday());
		query.setInteger(++i, userInfo.getProvinceid());
		query.setInteger(++i, userInfo.getCityid());
		query.setString(++i, userInfo.getBindphone());
		query.setInteger(++i, userInfo.getUsertype());
		query.setInteger(++i, userInfo.getUserstate());
		query.setDate(++i, userInfo.getUpdatetime());
		query.setInteger(++i, userInfo.getUserid());

		int isok = query.executeUpdate();
	    
	    if (isok > 0) {
			return true;
		}	    
	    return false;*/
	}

	@Override
	public UserinfoModel searchUserInfoLogin(String username, String password)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			String hql = "FROM UserinfoModel WHERE username=? and password=? and usertype=3";
			
			Query query = session.createQuery(hql);
			query.setString(0, username);
			query.setString(1, password);
			
			UserinfoModel userinfo = new UserinfoModel();
			
			if (query.list() != null  && query.list().size() > 0) {
				userinfo = (UserinfoModel) query.list().get(0);
			}

			return userinfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

}
