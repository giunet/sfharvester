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

public class Week {

	String notFound = "No matches found.";
	
	public String[] getPagineAcceduteWeek(String idprogetto, String ugn) 
	throws MalformedURLException, IOException,NullPointerException{		
	String baseUrlweek="http://sourceforge.net/project/stats/detail.php?group_id="+idprogetto+"&ugn="+ugn+"&mode=week&type=sfweb";
	String[] pagineaccedute={"0","0","0","0"};
	Source sourceweek = new Source(new URL(baseUrlweek));
	String p=null;
	Element table = (Element)sourceweek.findAllElements("table").get(2);
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
	
	public String getDownloadWeek(String idprogetto, String ugn) 
	throws MalformedURLException, IOException,NullPointerException{		
	String baseUrlweek="http://sourceforge.net/project/stats/detail.php?group_id="+idprogetto+"&ugn="+ugn+"&mode=week&type=prdownload";
	String download="0";
	Source sourceweek = new Source(new URL(baseUrlweek));
	String p=null;
	Element table = (Element)sourceweek.findAllElements("table").get(2);
	List td=table.findAllElements("td");
	int indice=td.size();
	Element downWeek=(Element)td.get(indice-2);
	download=downWeek.extractText();
	download = download.replace(",", "");

	return download;
	}

	public String getEtaReleaseWeek(String ultRelease) {
		int ultrelease=Integer.parseInt(ultRelease, 10);
		String finale="0";
		if(ultrelease<=7){
			finale=Integer.toString(ultrelease);
		}
		
		return finale;
	}

	public String getUltAdminWeek(String accessAdmin) {
		int ultadmin=Integer.parseInt(accessAdmin, 10);
		String finale="0";
		if(ultadmin<=7){
			finale=Integer.toString(ultadmin);
		}
		
		return finale;
	}

	public String getNumReleaseWeek(String ultReleaseWeek) {
		String finale="0";
		if (ultReleaseWeek=="0"){
			finale="0";
		}
		else{
			finale="1";
		}
		return finale;
	}

	public String getCVSweek(String idprogetto, String ugn) 
	throws MalformedURLException, IOException,NullPointerException{		
		String baseUrlweek="http://sourceforge.net/project/stats/detail.php?group_id="+idprogetto+"&ugn="+ugn+"&mode=week&type=cvs";
		String cvs="0";
		Source sourceweek = new Source(new URL(baseUrlweek));
		String p=null;
		Element table = (Element)sourceweek.findAllElements("table").get(2);
		List td=table.findAllElements("td");
		int indice=td.size();
		Element CVSWeek=(Element)td.get(indice-1);
		cvs=CVSWeek.extractText();
		cvs = cvs.replace(",", "");

		return cvs;
		}

	public int getCaratteristicheWeek(String projectId, String caratteristica,
			String tipo) throws MalformedURLException, IOException{
		Bugs b=new Bugs();
		TagS t=new TagS();
		int numtab = 0;
		int offset=0;
		String url = b.creaurl(projectId,caratteristica,tipo,offset);
		Source source=new Source(new URL(url));
		while (!(source.extractText().contains(notFound))){
			List<Element> elementi=source.findAllElements("table");
			Element tab = elementi.get(1);
			List tr=tab.findAllElements("tr");
			int i =1;
			while (i<tr.size()-1){   //-1 per evitare l eccezione: quando ci sono 50 elementi viene aggiunta una nuova riga
				Element attuale=(Element)tr.get(i);
				String fa=((Element)attuale.findAllElements("td").get(2)).extractText();
				String[] numeri=t.extract(fa);
				long giorni=t.calcolagiorni(numeri);
				if(giorni <=7){
					++numtab;
				}else{break;}
				++i;
			}
			offset=offset+50;
			if(numtab+1==offset){
				url = b.creaurl(projectId,caratteristica,tipo,offset);
				source=new Source(new URL(url));
			}else{break;}
		}	
		
		return numtab;
	}
	
}