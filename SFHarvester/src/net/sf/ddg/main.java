package net.sf.ddg;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import net.sf.ddg.hibernate.PrincipaleGenDAO;

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
			System.out.println("Il progetto è : "+nomeprogetto);
			System.out.println("L' ID del progetto è : "+idprogetto);
			System.out.println("Il Project Rank è : "+ProRank);
			//vengono presi bugs,patch,feature,cvs
			String[] elepatch= tag.getPatches(urlEstat.get(i));
			String idpatch=elepatch[0];
			String patchopen= elepatch[1];
			String patchtotal= elepatch[2];
			System.out.println("le patch sono: "+patchopen+" "+patchtotal+" L'ID è: "+ idpatch);
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
			String bugstotal= elebugs[2];
			System.out.println("i bugs sono: "+bugsopen+" "+bugstotal+" L'ID è: "+ idbugs);
			int bugpending=0;
			if (bugstotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				bugpending=b.getCaratterisiche(idprogetto, idbugs, "4");
				System.out.println("Bugs pending :"+bugpending);
			}
			
			String[] elefeature=tag.getFeature(urlEstat.get(i));
			String idfeature= elefeature [0];
			String featureopen= elefeature[1];
			String featuretotal= elefeature[2];
			System.out.println("le feature sono: "+featureopen+" "+featuretotal+" L'ID è: "+ idfeature);
			int featurepending=0;
			if (featuretotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				featurepending=b.getCaratterisiche(idprogetto, idfeature, "4");
				System.out.println("Feature pending :"+featurepending);
			}
			
			String[] eleCVS=tag.getCVS(urlEstat.get(i));
			String CVS= eleCVS[1];
			System.out.println("commit cvs sono: "+CVS);
			
			String developers=tag.getDevelopers(urlEstat.get(i));
			System.out.println("Sviluppatori : "+developers);
			String developmentstatus= tag.getDevelopmentStatus(urlEstat.get(i));
			System.out.println("Stato : "+developmentstatus);
			long etaprogetto=tag.getEtàProgetto(urlEstat.get(i));			
			System.out.println("Il progetto ha :"+etaprogetto+" giorni");
			String[] accEadmin=tag.getAdminErelease(urlEstat.get(i+2));
			String AccessAdmin=accEadmin[0];
			String UltRelease=accEadmin[1];
			System.out.println("Ultimo Admin :"+AccessAdmin);
			System.out.println("Ultima Release :"+UltRelease);
			String NumRelease=tag.getNumRelease(idprogetto);
			System.out.println("Il numero totale delle Release è :"+NumRelease);
			String ugn=tag.extractugn(urlEstat.get(i));
			System.out.println("UGN :"+ugn);
			String[] pagacceduteAllTime=tag.getPagineAcceduteAllTime(idprogetto,ugn);
			String PagTimeSF=pagacceduteAllTime[0];
			String PagTimeWEB=pagacceduteAllTime[1];
			System.out.println("Pagine SF all time :"+PagTimeSF);
			System.out.println("Pagine WEB all time :"+PagTimeWEB);
			String downloadtime=tag.getDownloadTime(idprogetto, ugn);
			System.out.println("I download totali sono :"+downloadtime);
			System.out.println("");
			
			String[] pagacceduteWeek=week.getPagineAcceduteWeek(idprogetto,ugn);
			String PagWeekSF=pagacceduteWeek[0];
			String PagWeekWEB=pagacceduteWeek[1];
			System.out.println("Pagine SF week :"+PagWeekSF);
			System.out.println("Pagine WEB week :"+PagWeekWEB);
			String downloadweek=week.getDownloadWeek(idprogetto, ugn);
			System.out.println("I download settimanali sono :"+downloadweek);
			String UltReleaseWeek = week.getEtaReleaseWeek(UltRelease);
			System.out.println("L' età dell ultima release settimanale è :"+UltReleaseWeek);
			String UltAdminWeek = week.getUltAdminWeek(AccessAdmin);
			System.out.println("L' età dell ultima accesso dell ADMIN settimanale è :"+UltAdminWeek);
			String NumReleaseWeek= week.getNumReleaseWeek(UltReleaseWeek);
			System.out.println("Il numero di release settimanali sono :"+NumReleaseWeek);
			String cvsweek="0";
			if (CVS!="0"){
				cvsweek=week.getCVSweek(idprogetto, ugn);
			}
			System.out.println("I Commit CVS settimanali sono :"+cvsweek);
			
			
			int patchopenweek=0;
			if(patchopen!="0"){
				//calcola patch aperti settimanali
			}
			System.out.println("Bug aperti settimanali sono :"+patchopenweek);
			
			int patchtotalweek=0;
			if(patchtotal!="0"){
				//calcola patch totali settimanali
			}
			System.out.println("Bug aperti settimanali sono :"+patchtotalweek);
			
			int patchpendingweek=0;
			if(patchpending!=0){
				//calcola patch pending settimanali
			}
			System.out.println("Bug aperti settimanali sono :"+patchpendingweek);
			
			int patchchiuseweek=0;
			if(patchchiuse!=0){
				//calcola patch chiuse settimanali
			}
			System.out.println("Bug aperti settimanali sono :"+patchchiuseweek);
			
			int bugopenweek=0;
			if(bugsopen!="0"){
				//calcola bug aperti settimanali
			}
			System.out.println("Bug aperti settimanali sono :"+bugopenweek);
			int bugtotalweek=0;
			if(bugstotal!="0"){
				//calcola bug totali settimanali
			}
			System.out.println("Bug totali settimanali sono :"+bugtotalweek);
			int bugpendingweek=0;
			if(bugpending!=0){
				//calcola bug pending settimanali
			}
			System.out.println("Bug totali settimanali sono :"+bugpendingweek);
			int featureopenweek=0;
			if(featureopen!="0"){
				//calcola feature aperti settimanali
			}
			System.out.println("Bug aperti settimanali sono :"+featureopenweek);
			int featuretotalweek=0;
			if(featuretotal!="0"){
				//calcola feature totali settimanali
			}
			System.out.println("Bug totali settimanali sono :"+featuretotalweek);
			int featurependingweek=0;
			if(featurepending!=0){
				//calcola feature pending settimanali
			}
			System.out.println("Bug totali settimanali sono :"+featurependingweek);
			
			
			System.out.println("");
			i=i+4;
		}
		 
		 }
		
	

}