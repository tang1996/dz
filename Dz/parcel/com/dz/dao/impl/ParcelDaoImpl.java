package com.dz.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dz.dao.IParcelDao;
import com.dz.entity.Parcel;

/**
 * 用户DAO实现类
 * 
 * @author GJ
 * @date 2015年4月15日
 */
@Repository("parcelDao")
@SuppressWarnings("unchecked")
public class ParcelDaoImpl implements IParcelDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// 用户列表
	@Override
	public List<Parcel> parcelList(String sql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		List<Parcel> parcelList = query.list();
		return parcelList;
	}

	// 用户列表
	@Override
	public Parcel getparcel(int goodsId) {
		String sql = "SELECT o FROM Parcel o WHERE o.goodsId=:v_goodsId";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setInteger("v_goodsId", goodsId);
		List<Parcel> parcelList = query.list();
		if (parcelList.size() > 0) {
			return parcelList.get(0);
		} else {
			return null;
		}
	}

	// 删除用户
	@Override
	public void delete(String id) {
		String sql = "DELETE FROM Parcel o WHERE o.id=:v_id";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql);
		query.setString("v_id", id);
		query.executeUpdate();
	}

	// 添加或修改信息
	@Override
	public void saveORupdate(Parcel parcel) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(parcel);
	}

}