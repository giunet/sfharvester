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
	public String getDatiHomePage(String osourceUrlStringurceUrlString)
			throws MalformedURLException, IOException, NullPointerException {
		Source source = new Source(new URL(osourceUrlStringurceUrlString));
		// source.fullSequentialParse();
		//prende gli elementi presenti nel div "sf_project_public_areas"
		List elementi = source.getElementById("sf_project_public_areas").getChildElements();  
		// System.out.println(el.get(0).toString());
		String s = null;
		String a = null;
		// contenuto = source.getElementById("sf_project_public_areas").extractText();
		Iterator iter = elementi.iterator();
		
		while (iter.hasNext()) {   // per ogni elemento 
			Element el = (Element) iter.next();  // elemento corrente
			if (el.extractText().contains("Bugs")) {
				s =  s + "Bugs are: " +el.extractText();
			}
			if (el.extractText().contains("Patches")) {
				s = s + "Patches are: " + el.extractText();
			}
			if (el.extractText().contains("Feature")) {
				s = s + "Feature are: "+el.extractText();
			}
			if (el.extractText().contains("CVS")) {
				s = s +"CVS are: " +el.extractText();
				} 
			} 
	
			s = s.replace(",", "");
		a = this.extract(s);
		return a;
	}

	/**
	 * @param s
	 * @return
	 * @throws NullPointerException
	 */
	public String extract(String s) throws NullPointerException {
		Pattern pat_page_content = Pattern.compile("[\\D]+");
		String item_page[] = pat_page_content.split(s);
		int i = 1;
		while (i < item_page.length-1) {
			System.out.println(item_page[i]);
			i++;
		}
		return item_page[i];
	}

	public String extractIdProgetto(String sourceUrlString)
			throws MalformedURLException, IOException {
		Source source = new Source(new URL(sourceUrlString));
		String patt = "group_id";
		List link = source.findAllElements("a"); //lista elementi a 
		int i = 0;
		String completa = null;
		while (i < link.size() - 1) {
			Element el = (Element) link.get(i); //elemento corrente
			if (el.getAttributeValue("href").contains(patt)) { //vedi se l url ha patt group_id
				completa = el.getAttributeValue("href");
				// System.out.println(completa);
				completa = this.extract(completa);  //estrae l ID del progetto
				break;
			} else {
				i++;
			}
		}
		//System.out.println(completa);
		return completa;

	}

}
