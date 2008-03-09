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
			String patchopen= tag.getPatches(url.get(i))[1];
			String patchtotal= tag.getPatches(url.get(i))[2];
			System.out.println("le patch sono: "+patchopen+" "+patchtotal);
			String bugsopen= tag.getBugs(url.get(i))[1];
			String bugstotal= tag.getBugs(url.get(i))[2];
			System.out.println("i bugs sono: "+bugsopen+" "+bugstotal);
			String featureopen= tag.getFeature(url.get(i))[1];
			String featuretotal= tag.getFeature(url.get(i))[2];
			System.out.println("le feature sono: "+featureopen+" "+featuretotal);
			String CVSopen= tag.getCVS(url.get(i))[1];
			String CVStotal= tag.getCVS(url.get(i))[2];
			System.out.println("commit cvs sono: "+CVSopen+" "+CVStotal);
			System.out.println("Bug pendenti : ");
			System.out.println(b.getCaratterisiche("176962","879332","" ));
			
			i++;
		}
		 
		 }
		
	

}