package net.sf.ddg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.Source;


public class ExtractURL {
	String baseUrl = "http://sourceforge.net";
	public ArrayList<String> extractByCategory() throws MalformedURLException, IOException{
		String url = "http://sourceforge.net/softwaremap/trove_list.php?form_cat=66&limit=100";
		Source source = new Source(new URL (url));
		List progetti = source.findAllElements("h3");
		ArrayList<String> listaUrl = new ArrayList<String>();
		int i = 1;
		while (i<progetti.size()-1){
			Element el = (Element) progetti.get(i);
			List link = el.findAllElements("a");
			Element ln = (Element) link.get(0);
			String progetto = ln.getAttributeValue("href");
			String urlcompleta = baseUrl.concat(progetto);
			listaUrl.add(urlcompleta);
			//System.out.println(baseUrl.concat(ln.getAttributeValue("href")));
			i=i+2;
		}
		return listaUrl;
		
		
		
	}

}
