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
			throws MalformedURLException, IOException {
		Source source = new Source(new URL(osourceUrlStringurceUrlString));
		// source.fullSequentialParse();
		List elementi = source.getElementById("sf_project_public_areas")
				.getChildElements();
		// System.out.println(el.get(0).toString());
		String s = null;
		String a = null;
		String contenuto = source.getElementById("sf_project_public_areas")
				.extractText();
		Iterator iter = elementi.iterator();
		while (iter.hasNext()) {
			Element el = (Element) iter.next();
			if (el.extractText().contains("Bugs")) {

				s = s + el.extractText();
			}
			if (el.extractText().contains("Patches")) {
				s = s + el.extractText();
			}
			if (el.extractText().contains("Feature")) {
				s = s + el.extractText();
			}
			if (el.extractText().contains("CVS")) {
				s = s + el.extractText();
			} else {
				System.out.println("è vuoto");
			}
		}
		try {
			s = s.replace(",", "");
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			System.out.println("eccezione in replace");
		} try {
		a = this.extract(s);
		} catch (NullPointerException ex){
			System.out.println("eccezione in exctract");
			ex.printStackTrace();
		} 
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
		List link = source.findAllElements("a");
		int i = 0;
		String completa = null;
		while (i < link.size() - 1) {
			Element el = (Element) link.get(i);
			if (el.getAttributeValue("href").contains(patt)) {
				completa = el.getAttributeValue("href");
				// System.out.println(completa);
				completa = this.extract(completa);
				break;
			} else {
				i++;
			}
		}
		System.out.println(completa);
		return completa;

	}

}
