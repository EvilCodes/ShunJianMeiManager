package com.wenyuankeji.spring.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUsersubinfoDao;
import com.wenyuankeji.spring.model.UserProfessionLevelModel;
import com.wenyuankeji.spring.model.UsersubinfoModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UsersubinfoDaoImpl implements IUsersubinfoDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/************管理端************/
	@Override
	public List<UsersubinfoModel> searchUsersubinfo(String cityid, String userid,
			String tel, String startTime, String endTime, String checkstate,String txtNickname,
			int page, int rows) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM UsersubinfoModel WHERE  userid IN (SELECT userid FROM UserinfoModel WHERE usertype=2) ");
			//美发师ID
			if (userid !=null && userid.trim().length() > 0) {
				sql.append(" and userid='"+ userid +"' ");
			}
			//手机号
			if (tel !=null && tel.trim().length() > 0) {
				sql.append(" and userinfoModel.bindphone like '%"+ tel +"%' ");
			}
			//昵称
			if (txtNickname !=null && txtNickname.trim().length() > 0) {
				sql.append(" and userinfoModel.nickname like '%"+ txtNickname +"%' ");
			}
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and userinfoModel.createtime>='"+ startTime +" 00:00:00' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and userinfoModel.createtime<='"+ endTime +" 23:59:59' ");
			}
			//审核状态
			if (checkstate !=null && !checkstate.equals("0") && checkstate.trim().length() > 0) {
				if (checkstate.equals("2")) {
					sql.append(" and checkstate = 2 ");
				}else{
					sql.append(" and checkstate != 2 ");
				}
			}
			
			Query query = session.createQuery(sql.toString());

			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);

			@SuppressWarnings("unchecked")
			List<UsersubinfoModel> usersubinfoModels = (List<UsersubinfoModel>) query.list();

			return usersubinfoModels;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchUserinfoCount(String cityid, String userid, String tel,
			String startTime, String endTime, String checkstate,String txtNickname)
			throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		try {
			StringBuffer sql = new StringBuffer("FROM UsersubinfoModel WHERE userid IN (SELECT userid FROM UserinfoModel WHERE usertype=2) ");
			//美发师ID
			if (userid !=null && userid.trim().length() > 0) {
				sql.append(" and userid='"+ userid +"' ");
			}
			//手机号
			if (tel !=null && tel.trim().length() > 0) {
				sql.append(" and userinfoModel.bindphone like '%"+ tel +"%' ");
			}
			//昵称
			if (txtNickname !=null && txtNickname.trim().length() > 0) {
				sql.append(" and userinfoModel.nickname like '%"+ txtNickname +"%' ");
			}
			//注册开始时间
			if (startTime !=null && startTime.trim().length() > 0) {
				sql.append(" and userinfoModel.createtime>='"+ startTime +" 00:00:00' ");
			}
			//注册结束时间
			if (endTime !=null && endTime.trim().length() > 0) {
				sql.append(" and userinfoModel.createtime<='"+ endTime +" 23:59:59' ");
			}
			//审核状态
			if (checkstate !=null && !checkstate.equals("0") && checkstate.trim().length() > 0) {
				if (checkstate.equals("2")) {
					sql.append(" and checkstate = 2 ");
				}else{
					sql.append(" and checkstate != 2 ");
				}
			}
			
			Query query = session.createQuery(sql.toString());
			@SuppressWarnings("unchecked")
			List<UsersubinfoModel> usersubinfoModels = (List<UsersubinfoModel>) query.list();

			return usersubinfoModels.size();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	@Override
	public UsersubinfoModel searchUsersubinfo(int userid) throws BaseException {
		
		UsersubinfoModel usersubinfo = null;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM UsersubinfoModel WHERE userid=?";
			
			Query query = session.createQuery(hql);
			query.setInteger(0, userid);
			
			@SuppressWarnings("unchecked")
			List<Object> oList = query.list();
			
			if (null != oList && oList.size() > 0) {
				usersubinfo = (UsersubinfoModel) oList.get(0);
			}
			
			return usersubinfo;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateUsersubinfo(UsersubinfoModel usersubinfoModel, int type) {
		Session session = sessionFactory.getCurrentSession();
		StringBuffer sql = new StringBuffer("UPDATE UsersubinfoModel ");
		//职称等级
		if (type == 1) {
			sql.append(" SET levelid=? ");
		}
		//审核状态
		if (type == 2) {
			sql.append(" SET checkstate=? ");
		}
		sql.append(" WHERE userid = ? ");
		Query query = session.createQuery(sql.toString());

		//职称等级
		if (type == 1) {
			query.setInteger(0, usersubinfoModel.getLevelid());
		}
		//审核状态
		if (type == 2) {
			query.setInteger(0, usersubinfoModel.getCheckstate());
		}
		query.setInteger(1, usersubinfoModel.getUserid());

		int isok = query.executeUpdate();

		if (isok > 0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean updateUsersubinfo(UsersubinfoModel usersubinfoModel) throws BaseException{
		try {
			Session session = sessionFactory.getCurrentSession();
			StringBuffer sql = new StringBuffer("UPDATE UsersubinfoModel SET levelid=?,checkstate=?,allocation=? WHERE userid = ?");
			
			Query query = session.createQuery(sql.toString());

			//职称等级
			query.setInteger(0, usersubinfoModel.getLevelid());
			//审核状态
			query.setInteger(1, usersubinfoModel.getCheckstate());
			//分成
			query.setString(2, usersubinfoModel.getAllocation());
			query.setInteger(3, usersubinfoModel.getUserid());

			int isok = query.executeUpdate();

			if (isok > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}
	
	
	@Override
	public UsersubinfoModel searchUserSubInfoByUserId(int userId) {
		UsersubinfoModel usersubinfo = new UsersubinfoModel();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UsersubinfoModel WHERE userid=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, userId);
		if (null != query.list() && query.list().size() > 0) {
			usersubinfo = (UsersubinfoModel) query.list().get(0);
		}
		return usersubinfo;
	}
	
	@Override
	public List<UserProfessionLevelModel> searchUserProfessionLevel() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserProfessionLevelModel";
		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<UserProfessionLevelModel> objects = query.list();
		
		if (null != objects && objects.size() > 0) {
			return objects;
		}
		return null;
	}
	
	
	@Override
	public int addUserSubInfo(UsersubinfoModel userSubInfo) {
		int result = 0;
		Session session = sessionFactory.getCurrentSession();
		Serializable isok = session.save(userSubInfo);
		if (Integer.parseInt(isok.toString()) > 0) {
			result = Integer.parseInt(isok.toString());
		}
		return result;
	}
	@Override
	public boolean updateUserSubInfoByUserId(UsersubinfoModel userSubInfo)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(userSubInfo);
			return true;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}

		/*
		 * String hql =
		 * "update UsersubinfoModel set levelid = ?,intro=?,workinglife=?,checkstate=?,checktime=?,score=?,ordernum=?,hairstyle=?,hobbies=?,starid=?,email=?,household=?,truename=?,idcard=?,address=?,contact=?,contactmobile=?,relationship=?,createtime=? where userid = ? "
		 * ; Query query = session.createQuery(hql); int i = 0;
		 * query.setInteger(i, userSubInfo.getLevelid()); query.setString(++i,
		 * userSubInfo.getIntro()); query.setInteger(++i,
		 * userSubInfo.getWorkinglife()); query.setInteger(++i,
		 * userSubInfo.getCheckstate()); query.setDate(++i,
		 * userSubInfo.getChecktime()); query.setFloat(++i,
		 * userSubInfo.getScore()); query.setInteger(++i,
		 * userSubInfo.getOrdernum()); query.setString(++i,
		 * userSubInfo.getHairstyle()); query.setString(++i,
		 * userSubInfo.getHobbies()); query.setInteger(++i,
		 * userSubInfo.getStarid()); query.setString(++i,
		 * userSubInfo.getEmail()); query.setString(++i,
		 * userSubInfo.getHousehold()); query.setString(++i,
		 * userSubInfo.getTruename()); query.setString(++i,
		 * userSubInfo.getIdcard()); query.setString(++i,
		 * userSubInfo.getAddress()); query.setString(++i,
		 * userSubInfo.getContact()); query.setString(++i,
		 * userSubInfo.getContactmobile()); query.setString(++i,
		 * userSubInfo.getRelationship()); query.setDate(++i,
		 * userSubInfo.getCreatetime()); query.setInteger(++i,
		 * userSubInfo.getUserid());
		 * 
		 * int isok = query.executeUpdate();
		 * 
		 * if (isok > 0) { return true; } return false;
		 */
	}
}
