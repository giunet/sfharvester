package net.sf.ddg.hibernate;

import org.hibernate.Query;

public class PrincipaleGenDAO extends BaseDAO{

	public PrincipaleGenDAO() {}
	
	public void setNomeProgetto (String nomeProgetto) {
		begin();
		DatiGenerale dg = new DatiGenerale();
		dg.setNomeProgetto(nomeProgetto);
		//Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.nomeProgetto='"+ nomeProgetto + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		//q.executeUpdate();
		getSession().save(dg);
		commit();
	}
	
	public void setPr (String nomeProgetto, int pr) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.pr='"+ pr + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}	
}
