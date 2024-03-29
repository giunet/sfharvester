package net.sf.ddg;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import net.sf.ddg.hibernate.DatiGeneraleDAO;
import net.sf.ddg.hibernate.DatiUltSettDAO;
import net.sf.ddg.hibernate.PrincipaleGenDAO;

import org.hibernate.SessionFactory;
import org.xml.sax.SAXException;


public class main {

	/**
	 * @param args
	 * @throws XPathExpressionException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws XPathExpressionException, SAXException, IOException{
		
		ExtractURL ex = new ExtractURL();
		TagS tag = new TagS();
		Bugs b = new Bugs();
		Week week= new Week();
		ArrayList<String> urlEstat = ex.extractByCategory(); //prende tutti gli URL dei progetti e delle statistiche
		//PrincipaleGenDAO pgd = new PrincipaleGenDAO();
		int i =0;
		//la lista urlStat contiene l url della pag.principale e succ. l url delle statistiche
		//pertanto per andare da un progetto all altro fare i=i+2 anziche i++
		while (i<urlEstat.size()-1){
			//pgd.setNomeProgetto(url.get(i));  //dall URL prendi nome progetto
			String idprogetto = tag.extractIdProgetto(urlEstat.get(i)); //dall URL prendi id progetto
			String nomeprogetto = urlEstat.get(i+1); //nome del progetto
			String ProRank=urlEstat.get(i+3);
			ProRank = ProRank.replaceAll(",","");
			int pr= Integer.parseInt(ProRank);
			System.out.println("Il progetto � : "+nomeprogetto);
			System.out.println("L' ID del progetto � : "+idprogetto);
			System.out.println("Il Project Rank � : "+ProRank);
			//vengono presi bugs,patch,feature,cvs
			String[] elepatch= tag.getPatches(urlEstat.get(i));
			String idpatch=elepatch[0];
			String patchopen= elepatch[1];
			int patchAperte = Integer.parseInt(patchopen);
			String patchtotal= elepatch[2];
			int patchTotali = Integer.parseInt(patchtotal); 
			System.out.println("le patch sono: "+patchopen+" "+patchtotal+" L'ID �: "+ idpatch);
			int patchpending=0;
			int patchchiuse=0;
			if (patchtotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				patchpending=b.getCaratterisiche(idprogetto, idpatch, "4");
				System.out.println("Patch pending :"+patchpending);
				patchchiuse=b.getCaratterisiche(idprogetto, idpatch, "2");
				System.out.println("Patch closed :"+patchchiuse);
			}
			
			String[] elebugs= tag.getBugs(urlEstat.get(i));
			String idbugs=elebugs[0];
			String bugsopen= elebugs[1];
			int bugAperti = Integer.parseInt(bugsopen);
			String bugstotal= elebugs[2];
			int bugTotali = Integer.parseInt(bugstotal);
			System.out.println("i bugs sono: "+bugsopen+" "+bugstotal+" L'ID �: "+ idbugs);
			int bugpending=0;
			if (bugstotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				bugpending=b.getCaratterisiche(idprogetto, idbugs, "4");
				System.out.println("Bugs pending :"+bugpending);
			}
			
			String[] elefeature=tag.getFeature(urlEstat.get(i));
			String idfeature= elefeature [0];
			String featureopen= elefeature[1];
			int openFeatureRequest = Integer.parseInt(featureopen);
			String featuretotal= elefeature[2];
			int totalFeatureRequest = Integer.parseInt(featuretotal);
			System.out.println("le feature sono: "+featureopen+" "+featuretotal+" L'ID �: "+ idfeature);
			int featurepending=0;
			if (featuretotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				featurepending=b.getCaratterisiche(idprogetto, idfeature, "4");
				System.out.println("Feature pending :"+featurepending);
			}
			
			String[] eleCVS=tag.getCVS(urlEstat.get(i));
			String CVS= eleCVS[1];
			int commitCvs = Integer.parseInt(CVS);
			System.out.println("commit cvs sono: "+CVS);
			
			String developers=tag.getDevelopers(urlEstat.get(i));
			int nDevelopers = Integer.parseInt(developers);
			System.out.println("Sviluppatori : "+developers);
			String developmentstatus= tag.getDevelopmentStatus(urlEstat.get(i));
			System.out.println("Stato : "+developmentstatus);
			long etaprogetto=tag.getEt�Progetto(urlEstat.get(i));
			int etaProgetto = (int)etaprogetto;
			System.out.println("Il progetto ha :"+etaprogetto+" giorni");
			String[] accEadmin=tag.getAdminErelease(urlEstat.get(i+2));
			String AccessAdmin=accEadmin[0];
			int ultimoAccAdmin = Integer.parseInt(AccessAdmin);
			String UltRelease=accEadmin[1];
			int etaUltRelease = Integer.parseInt(UltRelease);
			System.out.println("Ultimo Admin :"+AccessAdmin);
			System.out.println("Ultima Release :"+UltRelease);
			String NumRelease=tag.getNumRelease(idprogetto);
			int releaseTotali = Integer.parseInt(NumRelease);
			System.out.println("Il numero totale delle Release � :"+NumRelease);
			String ugn=tag.extractugn(urlEstat.get(i));
			System.out.println("UGN :"+ugn);
			String[] pagacceduteAllTime=tag.getPagineAcceduteAllTime(idprogetto,ugn);
			String PagTimeSF=pagacceduteAllTime[0];
			double pagineAccedute= Integer.parseInt(PagTimeSF); 
			String PagTimeWEB=pagacceduteAllTime[1];
			int pagineWeb = Integer.parseInt(PagTimeWEB);
			System.out.println("Pagine SF all time :"+PagTimeSF);
			System.out.println("Pagine WEB all time :"+PagTimeWEB);
			String downloadtime=tag.getDownloadTime(idprogetto, ugn);
			double fileScaricati = Integer.parseInt(downloadtime);
			System.out.println("I download totali sono :"+downloadtime);
			System.out.println("");
			
			String[] pagacceduteWeek=week.getPagineAcceduteWeek(idprogetto,ugn);
			String PagWeekSF=pagacceduteWeek[0];
			int pagineAcceduteUS = Integer.parseInt(PagWeekSF);
			String PagWeekWEB=pagacceduteWeek[1];
			int pagineWebUS= Integer.parseInt(PagWeekWEB);
			System.out.println("Pagine SF week :"+PagWeekSF);
			System.out.println("Pagine WEB week :"+PagWeekWEB);
			String downloadweek=week.getDownloadWeek(idprogetto, ugn);
			int fileScaricatiUS= Integer.parseInt(downloadweek);
			System.out.println("I download settimanali sono :"+downloadweek);
			String UltReleaseWeek = week.getEtaReleaseWeek(UltRelease);
			int etaUltReleaseUS= Integer.parseInt(UltReleaseWeek);
			System.out.println("L' et� dell ultima release settimanale � :"+UltReleaseWeek);
			String UltAdminWeek = week.getUltAdminWeek(AccessAdmin);
			int ultimoAccAdminUS = Integer.parseInt(UltAdminWeek);
			System.out.println("L' et� dell ultima accesso dell ADMIN settimanale � :"+UltAdminWeek);
			String NumReleaseWeek= week.getNumReleaseWeek(UltReleaseWeek);
			int releaseTotaliUS  = Integer.parseInt(NumReleaseWeek); 
			System.out.println("Il numero di release settimanali sono :"+NumReleaseWeek);
			String cvsweek="0";
			if (CVS!="0"){
				cvsweek=week.getCVSweek(idprogetto, ugn);
			}
			int commitCvsUS = Integer.parseInt(cvsweek);
			System.out.println("I Commit CVS settimanali sono :"+cvsweek);
			
			
			int patchopenweek=0;
			if(patchopen!="0"){
				//100 any 1 open 2 closed 3 deleted 4 pending
				patchopenweek=week.getCaratteristicheWeek(idprogetto, idpatch, "1");
			}
			System.out.println("Patch aperti settimanali sono :"+patchopenweek);
			
			int patchtotalweek=0;
			if(patchtotal!="0"){
				patchtotalweek=week.getCaratteristicheWeek(idprogetto, idpatch, "100");
			}
			System.out.println("Patch totali settimanali sono :"+patchtotalweek);
			
			int patchpendingweek=0;
			if(patchpending!=0){
				patchpendingweek=week.getCaratteristicheWeek(idprogetto, idpatch, "4");
			}
			System.out.println("Patch pending settimanali sono :"+patchpendingweek);
			
			int patchchiuseweek=0;
			if(patchchiuse!=0){
				patchchiuseweek=week.getCaratteristicheWeek(idprogetto, idpatch, "2");
			}
			System.out.println("Patch chiuse settimanali sono :"+patchchiuseweek);
			
			int bugopenweek=0;
			if(bugsopen!="0"){
				bugopenweek=week.getCaratteristicheWeek(idprogetto, idbugs, "1");
			}
			System.out.println("Bug aperti settimanali sono :"+bugopenweek);
			
			int bugtotalweek=0;
			if(bugstotal!="0"){
				bugtotalweek=week.getCaratteristicheWeek(idprogetto, idbugs, "100");
			}
			System.out.println("Bug totali settimanali sono :"+bugtotalweek);
			
			int bugpendingweek=0;
			if(bugpending!=0){
				bugpendingweek=week.getCaratteristicheWeek(idprogetto, idbugs, "4");
			}
			System.out.println("Bug pending settimanali sono :"+bugpendingweek);
			
			int featureopenweek=0;
			if(featureopen!="0"){
				featureopenweek=week.getCaratteristicheWeek(idprogetto, idfeature, "1");
			}
			System.out.println("Feature aperti settimanali sono :"+featureopenweek);
			
			int featuretotalweek=0;
			if(featuretotal!="0"){
				featuretotalweek=week.getCaratteristicheWeek(idprogetto, idfeature, "100");
			}
			System.out.println("Feature totali settimanali sono :"+featuretotalweek);
			
			int featurependingweek=0;
			if(featurepending!=0){
				featurependingweek=week.getCaratteristicheWeek(idprogetto, idfeature, "4");
			}
			System.out.println("Feture pending settimanali sono :"+featurependingweek);
			
			
			System.out.println("");
			
			/*SessionFactory sfac = null; 
			sfac.openSession();*/
			
			DatiGeneraleDAO dgd = new DatiGeneraleDAO();
			dgd.memDatiGenerale(nomeprogetto, pr, pagineAccedute, pagineWeb, fileScaricati, etaUltRelease, 
					ultimoAccAdmin, commitCvs, bugAperti, bugpending, bugTotali, patchAperte, patchpending, 
					patchTotali, patchchiuse, releaseTotali, etaProgetto, openFeatureRequest, 
					featurepending, totalFeatureRequest, nDevelopers, developmentstatus);
			
			DatiUltSettDAO dusd = new DatiUltSettDAO();
			dusd.memDatiUltSett(nomeprogetto, pr, pagineAcceduteUS, pagineWebUS, fileScaricatiUS, etaUltReleaseUS,
					ultimoAccAdminUS, commitCvsUS, bugopenweek, bugpendingweek, bugtotalweek, patchopenweek, patchpendingweek, 
					patchtotalweek, patchchiuseweek, releaseTotaliUS, featureopenweek, featurependingweek, 
					featuretotalweek);
			
			i=i+4;
		}
		 
	}
		
	

}