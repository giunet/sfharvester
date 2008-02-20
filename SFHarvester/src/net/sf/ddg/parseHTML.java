package net.sf.ddg;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.sun.org.apache.xerces.internal.impl.xs.dom.DOMParser;
public class parseHTML {
	public void contaTabella(String URL) throws SAXException, IOException{
/*		int numeroTabelle =0;
		XPath xpath = XPathFactory.newInstance().newXPath(); 
		InputSource inputSource = new InputSource(URL);
		String expr = "/html/body/div/*";
		NodeSet nodi = (NodeSet) xpath.evaluate(expr, inputSource, XPathConstants.NODESET);
		numeroTabelle=nodi.size();
		System.out.println(numeroTabelle);
		return numeroTabelle;*/
		
	DOMParser dp = new DOMParser();
		dp.setFeature("http://xml.org/sax/features/validation", false);
		dp.parse(URL);
		Document doc = dp.getDocument();
		doc.setStrictErrorChecking(false);
		Element el = doc.getElementById("logo");
		String content = el.getFirstChild().getFirstChild().getTextContent();
		System.out.println(content);
		
		
	}
	
    static void printElements(Document doc) {

        NodeList nodelist = doc.getElementsByTagName("*");
        Node     node;

        for (int i=0; i<nodelist.getLength(); i++) {
            node = nodelist.item(i);
            System.out.print(node.getNodeName() + " ");
        }

        System.out.println();

    }
}