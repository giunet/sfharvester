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
		ArrayList<String> url = ex.extractByCategory(); //prende tutti gli URL dei progetti
		//PrincipaleGenDAO pgd = new PrincipaleGenDAO();
		int i =0;
		 
		while (i<url.size()-1){
			//pgd.setNomeProgetto(url.get(i));  //dall URL prendi nome progetto
			String idprogetto = tag.extractIdProgetto(url.get(i)); //dall URL prendi id progetto
			String nomeprogetto = tag.extractNomeProgetto(url.get(i)); //nome del progetto
			System.out.println("Il progetto è : "+nomeprogetto);
			//vengono presi bugs,patch,feature,cvs
			String[] elepatch= tag.getPatches(url.get(i));
			String idpatch=elepatch[0];
			String patchopen= elepatch[1];
			String patchtotal= elepatch[2];
			System.out.println("le patch sono: "+patchopen+" "+patchtotal+" L'ID è: "+ idpatch);
			if (patchtotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				int patchpending=b.getCaratterisiche(idprogetto, idpatch, "4");
				System.out.println("Patch pending :"+patchpending);
				int patchchiuse=b.getCaratterisiche(idprogetto, idpatch, "2");
				System.out.println("Patch closed :"+patchchiuse);
			}
			
			String[] elebugs= tag.getBugs(url.get(i));
			String idbugs=elebugs[0];
			String bugsopen= elebugs[1];
			String bugstotal= elebugs[2];
			System.out.println("i bugs sono: "+bugsopen+" "+bugstotal+" L'ID è: "+ idbugs);
			if (bugstotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				int bugpending=b.getCaratterisiche(idprogetto, idbugs, "4");
				System.out.println("Bugs pending :"+bugpending);
			}
			
			String[] elefeature=tag.getFeature(url.get(i));
			String idfeature= elefeature [0];
			String featureopen= elefeature[1];
			String featuretotal= elefeature[2];
			System.out.println("le feature sono: "+featureopen+" "+featuretotal+" L'ID è: "+ idfeature);
			if (featuretotal!="0"){
				 //100 any 1 open 2 closed 3 deleted 4 pending
				int featurepending=b.getCaratterisiche(idprogetto, idfeature, "4");
				System.out.println("Feature pending :"+featurepending);
			}
			
			String[] eleCVS=tag.getCVS(url.get(i));
			String CVS= eleCVS[1];
			//String CVStotal= eleCVS[2];
			System.out.println("commit cvs sono: "+CVS);
			
			String developers=tag.getDevelopers(url.get(i));
			System.out.println("Sviluppatori : "+developers);
			String developmentstatus= tag.getDevelopmentStatus(url.get(i));
			System.out.println("Stato : "+developmentstatus);
			long etaprogetto=tag.getEtàProgetto(url.get(i));			
			System.out.println("Il progetto ha :"+etaprogetto+" giorni");
			i++;
		}
		 
		 }
		
	

}