package net.sf.ddg;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.html.HTMLElement;

import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.HTMLElementName;
import au.id.jericho.lib.html.Source;

public class Bugs {
	String notFound = "No matches found.";
	String baseUrl ="http://sourceforge.net/tracker/index.php?func=browse&group_id=";
	public int getCaratterisiche(String projectId, String caratteristica, String tipo) throws MalformedURLException, IOException{
			int numtab = 0;
			String url = baseUrl.concat(projectId).concat("&atid=").concat(caratteristica).concat("&set=custom&_assigned_to=0").concat("&_status=").concat(tipo).concat("&_category=100&_group=100&order=artifact_id&sort=DESC&offset=0");
			//System.out.println(url);
			Source source=new Source(new URL(url));
			if (source.extractText().contains(notFound)){
				System.out.println("Non trovato");
			}
			else{
			List<Element> elementi=source.findAllElements("table");
			Element tab = elementi.get(1);
			numtab =tab.findAllElements("tr").size()-1;
			//System.out.println(numtab);
			}
			return numtab;
		}
	
	}

