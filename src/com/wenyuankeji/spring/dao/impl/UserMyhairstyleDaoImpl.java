package com.wenyuankeji.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserMyhairstyleDao;
import com.wenyuankeji.spring.model.UserMyhairstyleModel;
import com.wenyuankeji.spring.model.UserMyhairstyleSearchModel;

@Repository
public class UserMyhairstyleDaoImpl implements IUserMyhairstyleDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int searchUserMyhairstyle(String userid, String tel, 
			String startTime, String endTime,String checkstate,String txtNickname) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " SELECT";
		hql += " user_myhairstyle.mystyleid,";
		hql += " user_hairstyle.`name`,";
		hql += " userinfo.nickname,";
		hql += " user_myhairstyle.state";

		hql += " FROM user_myhairstyle";
		hql += " INNER JOIN userinfo ";
		hql += " ON user_myhairstyle.userid = userinfo.userid";
		hql += " INNER JOIN user_hairstyle ";
		hql += " ON user_myhairstyle.styleid = user_hairstyle.styleid";

		hql += " WHERE 1=1";
		hql += " AND userinfo.usertype=2";
		//美发师ID
		if (userid !=null && userid.trim().length() > 0) {
			hql += " and user_myhairstyle.userid='"+ userid +"' ";
		}
		//手机号
		if (tel !=null && tel.trim().length() > 0) {
			hql += " and userinfo.username like '%"+ tel +"%' ";
		}
		//昵称
		if (txtNickname !=null && txtNickname.trim().length() > 0) {
			hql += " and userinfo.nickname like '%"+ txtNickname +"%' ";
		}
		//注册开始时间
		if (startTime !=null && startTime.trim().length() > 0) {
			hql += " and user_myhairstyle.createtime>='"+ startTime +" 00:00:00' ";
		}
		//注册结束时间
		if (endTime !=null && endTime.trim().length() > 0) {
			hql += " and user_myhairstyle.createtime<='"+ endTime +" 23:59:59' ";
		}
		//审核状态
		if (checkstate !=null && !checkstate.equals("0") && checkstate.trim().length() > 0) {
			hql += " AND user_myhairstyle.state = '" + checkstate + "'";
		}

		hql += " ORDER BY user_myhairstyle.createtime DESC";
		
		Query query = session.createSQLQuery(hql);

		return query.list().size();
	}

	@Override
	public List<UserMyhairstyleSearchModel> searchUserMyhairstyle(
			String userid, String tel, 
			String startTime, String endTime,String checkstate,String txtNickname, int page, int rows) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " SELECT";
		hql += " user_myhairstyle.mystyleid,";
		hql += " user_hairstyle.`name`,";
		hql += " userinfo.nickname,";
		hql += " user_myhairstyle.state,";
		hql += " userinfo.username";

		hql += " FROM user_myhairstyle";
		hql += " INNER JOIN userinfo ";
		hql += " ON user_myhairstyle.userid = userinfo.userid";
		hql += " INNER JOIN user_hairstyle ";
		hql += " ON user_myhairstyle.styleid = user_hairstyle.styleid";

		hql += " WHERE 1=1";
		hql += " AND userinfo.usertype=2";
		//美发师ID
		if (userid !=null && userid.trim().length() > 0) {
			hql += " and user_myhairstyle.userid='"+ userid +"' ";
		}
		//手机号
		if (tel !=null && tel.trim().length() > 0) {
			hql += " and userinfo.username like '%"+ tel +"%' ";
		}
		//昵称
		if (txtNickname !=null && txtNickname.trim().length() > 0) {
			hql += " and userinfo.nickname like '%"+ txtNickname +"%' ";
		}
		//注册开始时间
		if (startTime !=null && startTime.trim().length() > 0) {
			hql += " and user_myhairstyle.createtime>='"+ startTime +" 00:00:00' ";
		}
		//注册结束时间
		if (endTime !=null && endTime.trim().length() > 0) {
			hql += " and user_myhairstyle.createtime<='"+ endTime +" 23:59:59' ";
		}
		//审核状态
		if (checkstate !=null && !checkstate.equals("0") && checkstate.trim().length() > 0) {
			hql += " AND user_myhairstyle.state = '" + checkstate + "'";
		}

		hql += " ORDER BY user_myhairstyle.createtime DESC";

		Query query = session.createSQLQuery(hql);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);

		@SuppressWarnings("unchecked")
		List<Object[]> objList = (List<Object[]>) query.list();
		List<UserMyhairstyleSearchModel> searchModelList = new ArrayList<UserMyhairstyleSearchModel>();
		for (Object[] obj : objList) {
			UserMyhairstyleSearchModel searchModel = new UserMyhairstyleSearchModel();
			searchModel.setMystyleid(Integer.parseInt(obj[0].toString()));
			searchModel.setName(obj[1].toString());
			searchModel.setNickname(obj[2].toString());
			searchModel.setState(Integer.parseInt(obj[3].toString()));
			searchModel.setBindphone(obj[4].toString());
			searchModelList.add(searchModel);
		}

		return searchModelList;
	}

	@Override
	public boolean updateUserMyhairstyle(UserMyhairstyleModel userMyhairstyle) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE UserMyhairstyleModel SET state=? WHERE mystyleid = ?";
		Query query = session.createQuery(hql);

		query.setInteger(0, userMyhairstyle.getState());
		query.setInteger(1, userMyhairstyle.getMystyleid());

		int isok = query.executeUpdate();

		if (isok > 0) {
			return true;
		}
		return false;
	}

	@Override
	public UserMyhairstyleModel searchUserMyhairstyle(int mystyleid) {

		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserMyhairstyleModel WHERE mystyleid=?";

		Query query = session.createQuery(hql);
		query.setInteger(0, mystyleid);

		@SuppressWarnings("unchecked")
		List<Object> oList = query.list();

		UserMyhairstyleModel userMyhairstyle = null;
		if (null != oList && oList.size() > 0) {
			userMyhairstyle = (UserMyhairstyleModel) oList.get(0);
		}

		return userMyhairstyle;
	}

}
