package net.sf.ddg.hibernate;

import org.hibernate.Query;

public class PrincipaleSettDAO extends BaseDAO{

	public PrincipaleSettDAO() {}
	
	public void setNomeProgetto (String nomeProgetto) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.nomeProgetto='"+ nomeProgetto + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setPr (String nomeProgetto, int pr) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.pr='"+ pr + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}	
}
