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
	public void getBugsAny() throws MalformedURLException, IOException{
		String url ="http://sourceforge.net/tracker/index.php?func=browse&group_id=48857&atid=454373&set=custom&_assigned_to=0&_status=4&_category=100&_group=100&order=artifact_id&sort=DESC&offset=0";
			Source source=new Source(new URL(url));
			List<Element> elementi=source.findAllElements("table");
			Element tab = elementi.get(1);
			int numtab =tab.findAllElements("tr").size()-1;
			System.out.println(numtab);
		}
		
	}

