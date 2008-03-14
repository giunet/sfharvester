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
		String url = "http://sourceforge.net/softwaremap/trove_list.php?form_cat=141&limit=100";
		Source source = new Source(new URL (url));
		List progetti = source.findAllElements("h3");
		ArrayList<String> listaUrl = new ArrayList<String>();  //liste degli URL dei progetti
		int i = 1;
		while (i<progetti.size()-1){
			Element el = (Element) progetti.get(i); //progetto corrente
			List link = el.findAllElements("a");   //prende i tag a all interno di h3
			Element ln = (Element) link.get(0);       //prende il primo tag a
			String progetto = ln.getAttributeValue("href");    //prende attributo href di a
			String urlcompleta = baseUrl.concat(progetto);    //crea il link del progetto
			String dati=ln.extractText(); //contiene o il nome del progetto o il PR
			listaUrl.add(urlcompleta);
			listaUrl.add (dati);
			//System.out.println(baseUrl.concat(ln.getAttributeValue("href")));
			i=i+1;
		}
		return listaUrl;
	}

}
