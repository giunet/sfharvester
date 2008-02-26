package net.sf.ddg.hibernate;

import org.hibernate.Query;

public class TrafficoSettDAO extends BaseDAO{
	
	public TrafficoSettDAO() {}
	
	public void setPagineAccedute (String nomeProgetto, double pagineAccedute) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.pagineAccedute='"+ pagineAccedute + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setPagineWeb (String nomeProgetto, int pagineWeb) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.pagineWeb='"+ pagineWeb + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}	
	
	public void setFileScaricati (String nomeProgetto, double fileScaricati) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.fileScaricati='"+ fileScaricati + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}	
}
