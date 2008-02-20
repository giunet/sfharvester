package net.sf.ddg.hibernate;

// default package
// Generated 19-feb-2008 16.59.34 by Hibernate Tools 3.2.0.b9

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class DatiGenerale.
 * @see .DatiGenerale
 * @author Hibernate Tools
 */
public class DatiGeneraleHome {

	private static final Log log = LogFactory.getLog(DatiGeneraleHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(DatiGenerale transientInstance) {
		log.debug("persisting DatiGenerale instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(DatiGenerale instance) {
		log.debug("attaching dirty DatiGenerale instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DatiGenerale instance) {
		log.debug("attaching clean DatiGenerale instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(DatiGenerale persistentInstance) {
		log.debug("deleting DatiGenerale instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DatiGenerale merge(DatiGenerale detachedInstance) {
		log.debug("merging DatiGenerale instance");
		try {
			DatiGenerale result = (DatiGenerale) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DatiGenerale findById(int id) {
		log.debug("getting DatiGenerale instance with id: " + id);
		try {
			DatiGenerale instance = (DatiGenerale) sessionFactory
					.getCurrentSession().get("DatiGenerale", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DatiGenerale> findByExample(DatiGenerale instance) {
		log.debug("finding DatiGenerale instance by example");
		try {
			List<DatiGenerale> results = (List<DatiGenerale>) sessionFactory
					.getCurrentSession().createCriteria("DatiGenerale").add(
							create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
