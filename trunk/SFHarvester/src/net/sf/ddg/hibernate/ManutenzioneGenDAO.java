package net.sf.ddg.hibernate;

import org.hibernate.Query;

public class ManutenzioneGenDAO extends BaseDAO {

	public ManutenzioneGenDAO() {}
	
	public void setBugsAperti (String nomeProgetto, int bugOpen) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.bugAperti='"+ bugOpen + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
		
	}
	
	public void setBugsTotali (String nomeProgetto, int bugTotal) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.bugTotali='"+ bugTotal + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setBugsPendenti (String nomeProgetto, int bugPendenti) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.bugPendenti='"+ bugPendenti + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setPatchAperte (String nomeProgetto, int patchOpen) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.patchAperte='"+ patchOpen + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
		
	}
	
	public void setPatchTotali (String nomeProgetto, int patchTotali) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.patchTotali='"+ patchTotali + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setPatchPendenti (String nomeProgetto, int patchPendenti) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.patchPendenti='"+ patchPendenti + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}
	
	public void setPatchChiuse (String nomeProgetto, int patchChiuse) {
		begin();
		Query q = getSession().createQuery("UPDATE DatiGenerale dg SET dg.patchChiuse='"+ patchChiuse + "' where dg.nomeProgetto='" + nomeProgetto + "'" );
		q.executeUpdate();
		commit();
	}	
	
	public void setFeatAperte(String nomeProgetto, int featOpen) {
		begin();
		Query q = getSession().createQuery(
				"UPDATE DatiGenerale dg SET dg.openFeatureRequest='" + featOpen
						+ "' where dg.nomeProgetto='" + nomeProgetto + "'");
		q.executeUpdate();
		commit();

	}

	public void setFeatTotali(String nomeProgetto, int featTotal) {
		begin();
		Query q = getSession().createQuery(
				"UPDATE DatiGenerale dg SET dg.totalFeatureRequest='" + featTotal
						+ "' where dg.nomeProgetto='" + nomeProgetto + "'");
		q.executeUpdate();
		commit();
	}

	public void setFeatPendenti(String nomeProgetto, int featPendenti) {
		begin();
		Query q = getSession().createQuery(
				"UPDATE DatiGenerale dg SET dg.bugPendenti='" + featPendenti
						+ "' where dg.nomeProgetto='" + nomeProgetto + "'");
		q.executeUpdate();
		commit();
	}
	
	public void setReleaseTotali (String nomeProgetto, int releaseTotali){
		begin();
		Query q = getSession().createQuery(
				"UPDATE DatiGenerale dg SET dg.releaseTotali='" + releaseTotali
						+ "' where dg.nomeProgetto='" + nomeProgetto + "'");
		q.executeUpdate();
		commit();
	}
	
	public void setEtaProgetto (String nomeProgetto, int etaProgetto){
		begin();
		Query q = getSession().createQuery(
				"UPDATE DatiGenerale dg SET dg.etaProgetto='" + etaProgetto
						+ "' where dg.nomeProgetto='" + nomeProgetto + "'");
		q.executeUpdate();
		commit();
	}
	
	public void setDevelopers (String nomeProgetto, int developers){
		begin();
		Query q = getSession().createQuery(
				"UPDATE DatiGenerale dg SET dg.developers='" + developers
						+ "' where dg.nomeProgetto='" + nomeProgetto + "'");
		q.executeUpdate();
		commit();
	}
	
	public void setDevelopmentStatus (String nomeProgetto, String developmentStatus){
		begin();
		Query q = getSession().createQuery(
				"UPDATE DatiGenerale dg SET dg.developers='" + developmentStatus
						+ "' where dg.nomeProgetto='" + nomeProgetto + "'");
		q.executeUpdate();
		commit();
	}
}
