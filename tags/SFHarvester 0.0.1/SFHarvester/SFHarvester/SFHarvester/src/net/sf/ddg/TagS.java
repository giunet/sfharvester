package net.sf.ddg;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.w3c.dom.html.HTMLElement;

import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.HTMLElementName;
import au.id.jericho.lib.html.Source;
import au.id.jericho.lib.html.StartTag;
import au.id.jericho.lib.html.TextExtractor;

public class TagS {
	@SuppressWarnings("deprecation")
	public String homePage() throws MalformedURLException, IOException {
		String sourceUrlString="http://sourceforge.net/projects/keepass";
		Source source=new Source(new URL(sourceUrlString));
		//source.fullSequentialParse();
		List elementi=source.getElementById("sf_project_public_areas").getChildElements();
		//System.out.println(el.get(0).toString());
		String s = null;
		String contenuto = source.getElementById("sf_project_public_areas").extractText();
		Iterator iter = elementi.iterator();
		while (iter.hasNext()){
			Element el = (Element) iter.next();
			if (el.extractText().contains("Bugs")){
	
				s = s + el.extractText() ;
			}
			if (el.extractText().contains("Patches")){
					s = s+el.extractText();
					}
			if (el.extractText().contains("Feature")){
				s = s+el.extractText();
				}
			if (el.extractText().contains("CVS")){
				s = s+el.extractText();
				}
		}
		s = s.replace(",", "");
		return s;
	}
	
	
	public void extract(String s) {
		Pattern pat_page_content=Pattern.compile("[\\D]+");
			String item_page[]=pat_page_content.split(s);
			int i = 1;
			while (i<item_page.length){
				System.out.println(item_page[i]);
				i++;
			}
	}


}
	

