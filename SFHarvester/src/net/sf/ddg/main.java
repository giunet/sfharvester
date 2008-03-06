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
		// TODO Auto-generated method stub
		//ExtractURL ex = new ExtractURL();
		//ex.extract();
		//TagS t = new TagS();
		//t.extractidProgetto();
		//t.extract(t.homePage());
		//Bugs b =new  Bugs();
		//b.getCaratterisiche("454376", "4");
		ExtractURL ex = new ExtractURL();
		TagS tag = new TagS();
		Bugs b = new Bugs();
		ArrayList<String> url = ex.extractByCategory(); //prende tutti gli URL dei progetti
		PrincipaleGenDAO pgd = new PrincipaleGenDAO();
		 int i =0;
		 
		while (i<url.size()-1){
			pgd.setNomeProgetto(url.get(i));  //dall URL prendi nome progetto
			String idprogetto = tag.extractIdProgetto(url.get(i)); //dall URL prendi id progetto
			try{
			System.out.println(tag.getDatiHomePage(url.get(i)));  // prende Bugs, Patch, Feature , CVS
			} catch (NullPointerException exc){
				System.out.println("stampa 0");
			}
			i++;
		}
		 
		 }
		
	

}