package net.sf.ddg;

import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;


public class main {

	/**
	 * @param args
	 * @throws XPathExpressionException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws XPathExpressionException, SAXException, IOException {
		// TODO Auto-generated method stub
		//ExtractURL ex = new ExtractURL();
		//ex.extract();
		//TagS t = new TagS();
		//t.extract(t.homePage());
		Bugs b =new  Bugs();
		b.getCaratterisiche("454376", "4");
	}

}