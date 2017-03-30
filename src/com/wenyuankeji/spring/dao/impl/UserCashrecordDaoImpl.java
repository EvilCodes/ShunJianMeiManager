package com.wenyuankeji.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.wenyuankeji.spring.dao.IUserCashrecordDao;
import com.wenyuankeji.spring.model.UserCashrecordModel;
import com.wenyuankeji.spring.util.BaseException;

@Repository
public class UserCashrecordDaoImpl implements IUserCashrecordDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<UserCashrecordModel> searchUserCashrecord(String startTime,
			String endTime, int page, int rows) throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "FROM UserCashrecordModel WHERE 1=1 ";
			
			if(startTime != ""){
				sql += " AND createtime >= '" + startTime + " 00:00:00'";
			}
			if(endTime != ""){
				sql += " AND createtime <= '" + endTime + " 23:59:59'";
			}

			sql += " order by createtime desc";

			Query query = session.createQuery(sql);

			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);

			@SuppressWarnings("unchecked")
			List<UserCashrecordModel> userCashrecordList = (List<UserCashrecordModel>) query
					.list();

			return userCashrecordList;
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public int searchUserCashrecord(String startTime, String endTime)
			throws BaseException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String sql = "FROM UserCashrecordModel WHERE 1=1 ";

			if(startTime != ""){
				sql += " AND createtime >= '" + startTime + " 00:00:00'";
			}
			if(endTime != ""){
				sql += " AND createtime <= '" + endTime + " 23:59:59'";
			}

			sql += " order by createtime desc";

			Query query = session.createQuery(sql);

			@SuppressWarnings("unchecked")
			List<UserCashrecordModel> userCashrecordList = (List<UserCashrecordModel>) query
					.list();

			return userCashrecordList.size();
		} catch (Exception e) {
			throw new BaseException(e.getMessage());
		}
	}

	@Override
	public boolean updateUserCashrecordById(int recordid, int state) throws BaseException {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE UserCashrecordModel SET state=? WHERE recordid = ?";
		Query query = session.createQuery(hql);

		query.setInteger(0, state);
		query.setInteger(1, recordid);

		int isok = query.executeUpdate();

		if (isok > 0) {
			return true;
		}
		return false;
	}
	

}
