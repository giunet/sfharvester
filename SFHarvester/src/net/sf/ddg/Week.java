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

	public String[] getPagineAcceduteWeek(String idprogetto, String ugn) 
	throws MalformedURLException, IOException,NullPointerException{		
	String baseUrlweek="http://sourceforge.net/project/stats/detail.php?group_id="+idprogetto+"&ugn="+ugn+"&mode=week&type=sfweb";
	String[] pagineaccedute={"non trovato","non trovato","non trovato","non trovato"};
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
	String download="non trovato";
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
		String finale="null";
		if(ultrelease<=7){
			finale=Integer.toString(ultrelease);
		}
		
		return finale;
	}

	public String getUltAdminWeek(String accessAdmin) {
		int ultadmin=Integer.parseInt(accessAdmin, 10);
		String finale="null";
		if(ultadmin<=7){
			finale=Integer.toString(ultadmin);
		}
		
		return finale;
	}

	public String getNumReleaseWeek(String ultReleaseWeek) {
		String finale=null;
		if (ultReleaseWeek=="null"){
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
		String cvs="non trovato";
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
	
}