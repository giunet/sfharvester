package net.sf.ddg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import au.id.jericho.lib.html.Element;
import au.id.jericho.lib.html.Source;

public class TagS {

	@SuppressWarnings("deprecation")
	
	public String[] getPatches(String osourceUrlStringurceUrlString)
	throws MalformedURLException, IOException {
Source source = new Source(new URL(osourceUrlStringurceUrlString));
//prende gli elementi presenti nel div "sf_project_public_areas"
List elementi = source.getElementById("sf_project_public_areas").getChildElements();  
String s = null;
String[] a = {"0","0","0"};
String[] idpatch={"0"};
Iterator iter = elementi.iterator();
while (iter.hasNext()) {   // per ogni elemento 
	Element el = (Element) iter.next();  // elemento corrente
	if (el.extractText().contains("Patches")) {
		Element href= (Element) el.getChildElements().get(0);
		String stringa=href.getAttributeValue("href");
		idpatch=this.extract(stringa);
		s = el.extractText();
		s = s.replace(",", "");
		a = this.extract(s);
		a[0]=idpatch[1];
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
String[] idbugs={"0"};
Iterator iter = elementi.iterator();
while (iter.hasNext()) {   // per ogni elemento 
	Element el = (Element) iter.next();  // elemento corrente
	if (el.extractText().contains("Bugs")) {
		Element href= (Element) el.getChildElements().get(0);
		String stringa=href.getAttributeValue("href");
		idbugs=this.extract(stringa);
		s = el.extractText();
		s = s.replace(",", "");
		b = this.extract(s);
		b[0]=idbugs[1];
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
String[] idfeature={"0"};
Iterator iter = elementi.iterator();
while (iter.hasNext()) {   // per ogni elemento 
	Element el = (Element) iter.next();  // elemento corrente
	if (el.extractText().contains("Feature")) {
		Element href= (Element) el.getChildElements().get(0);
		String stringa=href.getAttributeValue("href");
		idfeature=this.extract(stringa);
		s = el.extractText();
		s = s.replace(",", "");
		c = this.extract(s);
		c[0]=idfeature[1];
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
		return completa[1];

	}

	public String getDevelopers(String osourceUrlStringurceUrlString) 
		throws MalformedURLException, IOException,NullPointerException {
		Source source = new Source(new URL(osourceUrlStringurceUrlString));
		String s="non trovato";
		List link = source.findAllElements("div");
		int i =0;
		while (i < link.size()-1){
			Element el = (Element) link.get(i); //elemento corrente
			try{
			if (el.getAttributeValue("style").contains("width: 59%; float: left; ")) {
				Element ele= (Element) el.findAllElements("ul").get(0);
				List finale=ele.getChildElements();
				Iterator iter = finale.iterator();
				while (iter.hasNext()) {   // per ogni elemento 
					Element attuale = (Element) iter.next();
					if (attuale.extractText().contains("Developers")) {
						s = attuale.extractText();
						s = s.replace(",", "");
						s = this.extract(s)[1];
						break;
						}
				}
			}
			}catch(NullPointerException ex){
			}
			
			++i;
		}
		return s;	
	}

	public String getDevelopmentStatus(String osourceUrlStringurceUrlString) 
		throws MalformedURLException, IOException,NullPointerException {
			Source source = new Source(new URL(osourceUrlStringurceUrlString));
			String s="non trovato";
			List link = source.findAllElements("div");
			int i =0;
			while (i < link.size()-1){
				Element el = (Element) link.get(i); //elemento corrente
				try{
				if (el.getAttributeValue("style").contains("width: 59%; float: left; ")) {
					Element ele= (Element) el.findAllElements("ul").get(0);
					List finale=ele.getChildElements();
					Iterator iter = finale.iterator();
					while (iter.hasNext()) {   // per ogni elemento 
						Element attuale = (Element) iter.next();
						if (attuale.extractText().contains("Development Status")) {
							s = attuale.extractText();
							s=this.extract(s)[1];
							break;
							}
					}
				}
				}catch(NullPointerException ex){
				}
				
				++i;
			}
			return s;	
		}

	public long getEtàProgetto(String osourceUrlStringurceUrlString) 
	throws MalformedURLException, IOException,NullPointerException {
		Source source = new Source(new URL(osourceUrlStringurceUrlString));
		String s="null";
		String[] data={"null","null","null","null"};
		List link = source.findAllElements("div");
		int i =0;
		long eta=0;
		while (i < link.size()-1){
			Element el = (Element) link.get(i); //elemento corrente
			try{
			if (el.getAttributeValue("style").contains("width: 59%; float: left; ")) {
				Element ele= (Element) el.findAllElements("ul").get(0);
				List finale=ele.getChildElements();
				Iterator iter = finale.iterator();
				while (iter.hasNext()) {   // per ogni elemento 
					Element attuale = (Element) iter.next();
					if (attuale.extractText().contains("Registered")) {
						s = attuale.extractText();
						data=this.extract(s);
						eta=calcolagiorni(data);
						break;
						}
				}
			}
			}catch(NullPointerException ex){
			}
			
			++i;
		}
		return eta;	
	}

	private long calcolagiorni(String[] data) {
		int anno= Integer.parseInt(data[1],10);
		int mese= Integer.parseInt(data[2],10)-1;
		int giorno= Integer.parseInt(data[3],10);
		GregorianCalendar dataprogetto= new GregorianCalendar (anno,mese,giorno);
		GregorianCalendar attuale= new GregorianCalendar();
		long eta=(attuale.getTimeInMillis()-dataprogetto.getTimeInMillis())/86400000;
		return eta;
	}

	public String[] getAdminErelease(String osourceUrlStringurceUrlString) 
	throws MalformedURLException, IOException,NullPointerException {
		Source source = new Source(new URL(osourceUrlStringurceUrlString));
		String[] accessoErelease={"non trovato","non trovato"};
		List link = source.findAllElements("table");
		Element tabella = (Element) link.get(0);
		Element riga=(Element) tabella.findAllElements("tr").get(1);
		Element td=(Element)riga.findAllElements("td").get(8);
		Element td2=(Element)riga.findAllElements("td").get(7);
		String testo= td.extractText();
		String testo2=td2.extractText();
		accessoErelease[0]=this.extract(testo)[0];
		accessoErelease[1]=this.extract(testo2)[0];
		return accessoErelease;
	}

	public String getNumRelease(String idprogetto) 
	throws MalformedURLException, IOException,NullPointerException{
		String urlfinale="http://sourceforge.net/project/showfiles.php?group_id="+idprogetto;
		String numero="non trovato";
		Source source = new Source(new URL(urlfinale));
		Element tfoot=(Element)source.findAllElements("tfoot").get(1);
		Element release= (Element)tfoot.findAllElements("td").get(1);
		numero=release.extractText();
		return numero;
		
	}
	
public String extractugn(String url){
		
		String[] item_page = url.split("/");
		int indice = item_page.length;
		return item_page[indice-1];
	}

public String[] getPagineAcceduteAllTime(String idprogetto, String ugn) 
	throws MalformedURLException, IOException,NullPointerException{		
	String baseUrlalltime="http://sourceforge.net/project/stats/detail.php?group_id="+idprogetto+"&ugn="+ugn+"&mode=alltime&type=sfweb";
	String[] pagineaccedute={"non trovato","non trovato","non trovato","non trovato"};
	Source sourcealltime = new Source(new URL(baseUrlalltime));
	String p=null;
	Element table = (Element)sourcealltime.findAllElements("table").get(2);
	List td=table.findAllElements("td");
	int indice=td.size();
	Element pagSF=(Element)td.get(indice-2);
	pagineaccedute[0]=pagSF.extractText();
	pagineaccedute[0] = pagineaccedute[0].replace(",", "");
	Element pagWEB=(Element)td.get(indice-1);
	pagineaccedute[1]=pagWEB.extractText();
	pagineaccedute[1] = pagineaccedute[1].replace(",", "");
	return pagineaccedute;
}

public String getDownloadTime(String idprogetto, String ugn) 
throws MalformedURLException, IOException,NullPointerException{		
String baseUrltime="http://sourceforge.net/project/stats/detail.php?group_id="+idprogetto+"&ugn="+ugn+"&mode=alltime&type=prdownload";
String download="non trovato";
Source sourcetime = new Source(new URL(baseUrltime));

String p=null;
try{
Element table = (Element)sourcetime.findAllElements("table").get(2);
List td=table.findAllElements("td");
int indice=td.size();
Element downTime=(Element)td.get(indice-2);
download=downTime.extractText();
download = download.replace(",", "");
}
catch(IndexOutOfBoundsException ex){
	download=getDownloadTime(idprogetto,ugn);
}
return download;
}
	
}
