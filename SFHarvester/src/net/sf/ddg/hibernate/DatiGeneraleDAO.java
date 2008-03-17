package net.sf.ddg.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatiGeneraleDAO extends BaseDAO {
	public DatiGeneraleDAO() {}
	
	public void memDatiGenerale (String nomeProgetto, int pr,double pagineAccedute, int pagineWeb, 
			double fileScaricati, int etaUltRelease, int ultimoAccAdmin, int commitCvs, int bugAperti, 
			int bugPendenti, int bugTotali, int patchAperte, int patchPendenti, int patchTotali, 
			int patchChiuse, int releaseTotali, int etaProgetto, int openFeatureRequest, 
			int pendingFeatureRequest, int totalFeatureRequest, int developers, String developmentStatus) {
		/*SessionFactory sfac = new Configuration().buildSessionFactory();
		*/
		getSession();
		begin();
		DatiGenerale dg = new DatiGenerale();
		dg.setNomeProgetto(nomeProgetto);
		dg.setPr(pr);
		dg.setPagineAccedute(pagineAccedute);
		dg.setPagineWeb(pagineWeb);
		dg.setFileScaricati(fileScaricati);
		dg.setEtaUltRelease(etaUltRelease);
		dg.setUltimoAccAdmin(ultimoAccAdmin);
		dg.setCommitCvs(commitCvs);
		dg.setBugAperti(bugAperti);
		dg.setBugPendenti(bugPendenti);
		dg.setBugTotali(bugTotali);
		dg.setPatchAperte(patchAperte);
		dg.setPatchPendenti(patchPendenti);
		dg.setPatchTotali(patchTotali);
		dg.setPatchChiuse(patchChiuse);
		dg.setReleaseTotali(releaseTotali);
		dg.setEtaProgetto(etaProgetto);
		dg.setOpenFeatureRequest(openFeatureRequest);
		dg.setPendingFeatureRequest(pendingFeatureRequest);
		dg.setTotalFeatureRequest(totalFeatureRequest);
		dg.setDevelopers(developers);
		dg.setDevelopmentStatus(developmentStatus);
		getSession().save(dg);
		commit();
		close();
	}
}
