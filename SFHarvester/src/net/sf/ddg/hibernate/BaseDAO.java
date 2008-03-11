package net.sf.ddg.hibernate;

import java.util.ResourceBundle;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BaseDAO {


	protected BaseDAO() {
	}

	public static Session getSession() {
		Session session = (Session) BaseDAO.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			BaseDAO.session.set(session);
		}
		return session;
	}

	protected void begin() {

		getSession().beginTransaction();
	}

	protected void commit() {
		Transaction tx = getSession().getTransaction();
		tx.commit();
		
	}

	protected void rollback() {
		Transaction tx=getSession().getTransaction();
		try {
			tx.rollback();
		} catch (HibernateException e) {
			System.out.println("Cannot rollback Hibernate transaction" + e.getMessage());
		}
		try {
			getSession().close();
		} catch (HibernateException e) {
			System.out.println("Cannot close Hibernate session:" + e.getMessage());
		}
		BaseDAO.session.set(null);
	}

	public static void close() {
		getSession().close();
		BaseDAO.session.set(null);
	}
	
	

	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
	private static final SessionFactory sessionFactory = new Configuration()
			.configure().buildSessionFactory();
}