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
		int offset=0;
		String urlprecedente = creaurl(projectId,caratteristica,tipo,offset);
		Source source=new Source(new URL(urlprecedente));
		while (!(source.extractText().contains(notFound))){
			offset=offset+50;
			String urlattuale=creaurl(projectId,caratteristica,tipo,offset);
			source=new Source(new URL(urlattuale));
			if (source.extractText().contains(notFound)){
				source=new Source(new URL(urlprecedente));
				List<Element> elementi=source.findAllElements("table");
				Element tab = elementi.get(1);
				offset=offset-50;
				numtab =tab.findAllElements("tr").size()-1+offset;
				break;
			}
			else{
				urlprecedente=urlattuale;
				source=new Source(new URL(urlprecedente));
			}
		}	
		
		return numtab;
	}
	
	public String creaurl(String projectId, String caratteristica,
			String tipo, int offset) {
		String url = baseUrl.concat(projectId).concat("&atid=").concat(caratteristica).concat("&set=custom&_assigned_to=0").concat("&_status=").concat(tipo).concat("&_category=100&_group=100&order=artifact_id&sort=DESC&offset=")+offset;
		return url;
	}
	
	}

