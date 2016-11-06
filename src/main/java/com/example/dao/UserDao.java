package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
@Transactional
public class UserDao {
	@Autowired
	private SessionFactory _sessionFactory;
	
	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}
	
	public void save(User user) {
	    getSession().save(user);
	    return;
	}
	
	public void delete(User user) {
	    getSession().delete(user);
	    return;
	}
	
	/*@SuppressWarnings("unchecked")
	  public User getById(long id) {
		System.out.println("DAo");
		return  (User) getSession().createCriteria(User.class).add(Restrictions.eq("id",id)).uniqueResult();
	   // return getSession().createQuery("from User").list();
	}*/
	
	@SuppressWarnings("unchecked")
	public User getByEmail(String email) {
		
		List<User> users = new ArrayList<User>();
		
		users = getSession().createQuery(
	        "from User where email = :email")
	        .setParameter("email", email)
	        .list();
		if(users.size() > 0) {
			return users.get(0);
		} else {
			return null;	
		}
	}
	
	public User getById(long id) {
	  return (User) getSession().get(User.class, id);
	}
	
	public void update(User user) {
	  getSession().update(user);
	  return;
	}
}
