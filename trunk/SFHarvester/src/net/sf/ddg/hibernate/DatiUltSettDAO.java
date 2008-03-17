package net.sf.ddg.hibernate;

public class DatiUltSettDAO extends BaseDAO {
	public DatiUltSettDAO() {}
	
	public void memDatiUltSett(String nomeProgetto, int pr, double pagineAccedute, int pagineWeb, 
			double fileScaricati, int etaUltRelease, int ultimoAccAdmin, int commitCvs, int bugAperti, int bugPendenti,
			int bugTotali, int patchAperte, int patchPendenti, int patchTotali, int patchChiuse, int releaseTotali,
			int openFeatureRequest, int pendingFeatureRequest, int totalFeatureRequest) {
		
		getSession();
		begin();
		DatiUltSett dus = new DatiUltSett();
		dus.setNomeProgetto(nomeProgetto);
		dus.setPr(pr);
		dus.setPagineAccedute(pagineAccedute);
		dus.setPagineWeb(pagineWeb);
		dus.setFileScaricati(fileScaricati);
		dus.setEtaUltRelease(etaUltRelease);
		dus.setUltimoAccAdmin(ultimoAccAdmin);
		dus.setCommitCvs(commitCvs);
		dus.setBugAperti(bugAperti);
		dus.setBugPendenti(bugPendenti);
		dus.setBugTotali(bugTotali);
		dus.setPatchAperte(patchAperte);
		dus.setPatchPendenti(patchPendenti);
		dus.setPatchTotali(patchTotali);
		dus.setPatchChiuse(patchChiuse);
		dus.setReleaseTotali(releaseTotali);
		dus.setOpenFeatureRequest(openFeatureRequest);
		dus.setPendingFeatureRequest(pendingFeatureRequest);
		dus.setTotalFeatureRequest(totalFeatureRequest);
		getSession().save(dus);
		commit();
		close();
	}
}