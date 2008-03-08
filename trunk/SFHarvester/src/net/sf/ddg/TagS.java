package net.sf.ddg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.Source;

public class TagS {

	@SuppressWarnings("deprecation")
/*	public String getDatiHomePage(String osourceUrlStringurceUrlString)
			throws MalformedURLException, IOException, NullPointerException {
		Source source = new Source(new URL(osourceUrlStringurceUrlString));
		//prende gli elementi presenti nel div "sf_project_public_areas"
		List elementi = source.getElementById("sf_project_public_areas").getChildElements();  
		String s = null;
		String a = null;
		// contenuto = source.getElementById("sf_project_public_areas").extractText();
		Iterator iter = elementi.iterator();
		while (iter.hasNext()) {   // per ogni elemento 
			Element el = (Element) iter.next();  // elemento corrente
			if (el.extractText().contains("Bugs")) {
				s = el.extractText();
				s = s.replace(",", "");
				a = this.extract(s);
				System.out.println("Bugs sono : "+a);
			}
			if (el.extractText().contains("Patches")) {
				s = el.extractText();
				s = s.replace(",", "");
				a = this.extract(s);
				System.out.println("Patch sono : "+a);
			}
			if (el.extractText().contains("Feature")) {
				s = el.extractText();
				s = s.replace(",", "");
				a = this.extract(s);
				System.out.println("Feature sono : "+a);
			}
			if (el.extractText().contains("CVS")) {
				s = el.extractText();
				s = s.replace(",", "");
				a = this.extract(s);
				System.out.println("CVS sono : "+a);
				} 
			} 
		a = this.extract(s);
		return a;
	}*/

	public String[] getPatches(String osourceUrlStringurceUrlString)
	throws MalformedURLException, IOException {
Source source = new Source(new URL(osourceUrlStringurceUrlString));
//prende gli elementi presenti nel div "sf_project_public_areas"
List elementi = source.getElementById("sf_project_public_areas").getChildElements();  
String s = null;
String[] a = {"0","0","0"};
// contenuto = source.getElementById("sf_project_public_areas").extractText();
Iterator iter = elementi.iterator();
while (iter.hasNext()) {   // per ogni elemento 
	Element el = (Element) iter.next();  // elemento corrente
	if (el.extractText().contains("Patches")) {
		s = el.extractText();
		s = s.replace(",", "");
		a = this.extract(s);
		}
	} 
	
return a;
}
	
	public String[] getBugs(String osourceUrlStringurceUrlString)
	throws MalformedURLException, IOException {
Source source = new Source(new URL(osourceUrlStringurceUrlString));
//prende gli elementi presenti nel div "sf_project_public_areas"
List elementi = source.getElementById("sf_project_public_areas").getChildElements();  
String s = null;
String[] b = {"0","0","0"};
// contenuto = source.getElementById("sf_project_public_areas").extractText();
Iterator iter = elementi.iterator();
while (iter.hasNext()) {   // per ogni elemento 
	Element el = (Element) iter.next();  // elemento corrente
	if (el.extractText().contains("Bugs")) {
		s = el.extractText();
		s = s.replace(",", "");
		b = this.extract(s);
		}
	} 
	
return b;
}
	
	public String[] getFeature(String osourceUrlStringurceUrlString)
	throws MalformedURLException, IOException {
Source source = new Source(new URL(osourceUrlStringurceUrlString));
//prende gli elementi presenti nel div "sf_project_public_areas"
List elementi = source.getElementById("sf_project_public_areas").getChildElements();  
String s = null;
String[] c = {"0","0","0"};
// contenuto = source.getElementById("sf_project_public_areas").extractText();
Iterator iter = elementi.iterator();
while (iter.hasNext()) {   // per ogni elemento 
	Element el = (Element) iter.next();  // elemento corrente
	if (el.extractText().contains("Feature")) {
		s = el.extractText();
		s = s.replace(",", "");
		c = this.extract(s);
		}
	} 
		
return c;
}
	
	public String[] getCVS(String osourceUrlStringurceUrlString)
	throws MalformedURLException, IOException {
Source source = new Source(new URL(osourceUrlStringurceUrlString));
//prende gli elementi presenti nel div "sf_project_public_areas"
List elementi = source.getElementById("sf_project_public_areas").getChildElements();  
String s = null;
String[] d = {"0","0","0"};
// contenuto = source.getElementById("sf_project_public_areas").extractText();
Iterator iter = elementi.iterator();
while (iter.hasNext()) {   // per ogni elemento 
	Element el = (Element) iter.next();  // elemento corrente
	if (el.extractText().contains("CVS")) {
		s = el.extractText();
		s = s.replace(",", "");
		d = this.extract(s);
		}
	}
return d;
}
	
	/**
	 * @param s
	 * @return
	 * @throws NullPointerException
	 */
	public String[] extract(String s) throws NullPointerException {
		Pattern pat_page_content = Pattern.compile("[\\D]+");
		String[] item_page = pat_page_content.split(s);
		return item_page;
	}

	public String extractIdProgetto(String sourceUrlString)
			throws MalformedURLException, IOException {
		Source source = new Source(new URL(sourceUrlString));
		String patt = "group_id";
		List link = source.findAllElements("a"); //lista elementi a 
		int i = 0;
		String com = null;
		String[] completa= null;
		while (i < link.size() - 1) {
			Element el = (Element) link.get(i); //elemento corrente
			if (el.getAttributeValue("href").contains(patt)) { //vedi se l url ha patt group_id
				com = el.getAttributeValue("href");
				completa = this.extract(com);  //estrae l ID del progetto
				break;
			} else {
				i++;
			}
		}
		//System.out.println(completa);
		return completa[1];

	}

}
