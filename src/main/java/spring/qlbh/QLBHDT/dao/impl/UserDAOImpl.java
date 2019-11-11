package spring.qlbh.QLBHDT.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.qlbh.QLBHDT.dao.UserDAO;
import spring.qlbh.QLBHDT.entity.User;
import spring.qlbh.QLBHDT.model.UserInfo;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserInfo> getList() {
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "SELECT new " + UserInfo.class.getName() + " (u.id, u.username, u.password)" 
				+ " FROM " + User.class.getName() + " u";
		
		Query query = session.createQuery(sql);
		
		return query.getResultList();
	}

}
