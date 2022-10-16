import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


public class Assignment4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {


			File xmlFile = new File("JobResult_UCSDExt.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Results of XML Parsing using DOM Parser:");
			NodeList serialList = doc.getElementsByTagName("serial");// node serial
			NodeList dataList = doc.getElementsByTagName("data");// node data
			NodeList unsignedList = doc.getElementsByTagName("unsigned");
			
			
			
			for(int i = 0; i<serialList.getLength(); i++) {
				Node s = serialList.item(i);
				//System.out.println("\nCurrent Element :" + s.getNodeName());
				Node data = dataList.item(i);
				
				Node unsignedData = unsignedList.item(i);
				
				if(s.getNodeType() == Node.ELEMENT_NODE) {
					Element serial = (Element) s;
					
					System.out.println("serial: " + serial.getTextContent());
					
				}
				//data visible-string
				if (data.getNodeType() == Node.ELEMENT_NODE) {
					Element vstring = (Element) data;// assign an element for visible-string
					
					System.out.println("visible-string: " + vstring.getElementsByTagName("visible-string")
			                  .item(0).getTextContent());
				}
				
				//data for unsigned
				if (unsignedData.getNodeType() == Node.ELEMENT_NODE) {
					Element un = (Element) unsignedData;// assign an element for visible-string
					
					System.out.println("unsigned: " + un.getTextContent());
				}
			}
			
/////////////// SAX SAX SAX SAX ////////////////////////////////////////////////////			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			System.out.println("Results of XML Parsing using SAX Parser");
			SAXParser saxParser = factory.newSAXParser();
			UserHandler userhandler = new UserHandler();
			saxParser.parse(xmlFile, userhandler);   

///////////////// XPath XPath /////////////////////////////////////////////////////
			 XPath xPath =  XPathFactory.newInstance().newXPath();
			       
	         NodeList nodeList = (NodeList) xPath.compile("//jobresult").evaluate(
	            doc, XPathConstants.NODESET);
	         System.out.println("Results of XML Parsing using Xpath Parser");
	        
	         for (int y = 0; y < nodeList.getLength(); y++) {
	        	 Node s = serialList.item(y);
	        	 
	        	 Node data = dataList.item(y);

	        	 Node unsignedData = unsignedList.item(y);

	        	 //System.out.println("\nCurrent Element :" + nNode.getNodeName());
	        	 if(s.getNodeType() == Node.ELEMENT_NODE) {
	        		 Element serial = (Element) s;

	        		 System.out.println("serial: " + serial.getTextContent());

	        	 }
	        	 //data visible-string
	        	 if (data.getNodeType() == Node.ELEMENT_NODE) {
	        		 Element vstring = (Element) data;// assign an element for visible-string

	        		 System.out.println("visible-string: " + vstring.getElementsByTagName("visible-string")
	        		 .item(0).getTextContent());
	        	 }

	        	 //data for unsigned
	        	 if (unsignedData.getNodeType() == Node.ELEMENT_NODE) {
	        		 Element un = (Element) unsignedData;// assign an element for visible-string

	        		 System.out.println("unsigned: " + un.getTextContent());
	        	 }

	         }
	         System.out.println("All Done!");
		}

		catch (Exception e){
			e.printStackTrace();
		}
	}
}

//// creating class for UserHandler of XML Parsing using SAX /////////////////////
class UserHandler extends DefaultHandler{
	//DefaultHandler handler = new DefaultHandler() {
	boolean bserial = false;
	boolean bVstring = false;
	boolean bunsigned = false;



	public void startElement(
			String uri, String localName, String qName, Attributes attributes)
					throws SAXException {

		if(qName.equalsIgnoreCase("serial")) {
			bserial = true;
		}

		else if(qName.equalsIgnoreCase("visible-string")) {
			bVstring= true;
		}

		else if(qName.equalsIgnoreCase("unsigned")) {
			bunsigned = true;
		}

	}

	public void endElement(String uri, 
			String localName, String qName) throws SAXException {


	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (bserial) {
			System.out.println("serial: " + new String(ch, start, length));
			bserial = false;
		}
		if (bVstring) {
			System.out.println("visible-string: " + new String(ch, start, length));
			bVstring = false;
		}
		if (bunsigned) {
			System.out.println("unsigned: " + new String(ch, start, length));
			bunsigned = false;
		}
	}
}















