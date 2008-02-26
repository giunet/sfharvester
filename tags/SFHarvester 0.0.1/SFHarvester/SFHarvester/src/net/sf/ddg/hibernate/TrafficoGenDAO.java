package net.sf.ddg.hibernate;

import org.hibernate.Query;

public class TrafficoGenDAO extends BaseDAO{
	
	public TrafficoGenDAO() {}
	
	public void setPagineAccedute (String nomeProgetto, double pagineAccedute) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.pagineAccedute='"+ pagineAccedute + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setPagineWeb (String nomeProgetto, int pagineWeb) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.pagineWeb='"+ pagineWeb + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}	
	
	public void setFileScaricati (String nomeProgetto, double fileScaricati) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.fileScaricati='"+ fileScaricati + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}	
}
