package net.sf.ddg.hibernate;

import org.hibernate.Query;

public class SviluppoSettDAO extends BaseDAO {

	public SviluppoSettDAO() {}
	
	public void setEtaUltRelease (String nomeProgetto, int etaUltRelease) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.etaUltRelease='"+ etaUltRelease + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}

	public void setUltimoAccAdmin (String nomeProgetto, int ultimoAccAdmin) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.ultimoAccAdmin='"+ ultimoAccAdmin + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setCommitCvs (String nomeProgetto, int commitCvs) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiUltSett dg SET dg.commitCvs='"+ commitCvs + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
}
