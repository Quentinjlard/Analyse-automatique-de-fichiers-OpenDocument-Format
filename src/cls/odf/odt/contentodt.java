package cls.odf.odt;

import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import org.w3c.dom.*;

import java.io.*;

public class contentodt
{
	public contentodt(Document document, String nom)
	{
		XPath xpath;
		String DELIMITER = "/";
    	String SEPARATOR = "\n";
		String HEADER = "Balise/ Contenu";

		try
		{
			System.out.println("XPath generation d'expression...");
			xpath = XPathFactory.newInstance().newXPath();
			String exp = "/document-content/body/*";
			var res = xpath.compile(exp).evaluate(document, javax.xml.xpath.XPathConstants.NODESET);
			System.out.println("\nResultats :");
			System.out.println("------------------------");
			if(res instanceof NodeList)
			{
				NodeList nodes = (NodeList)res;
				FileWriter file = null;
				
				for(int i=0; i<nodes.getLength(); i++)
				{
					System.out.println(nodes.item(i).getNodeName() + DELIMITER + nodes.item(i).getTextContent() + DELIMITER);
					//System.out.println(((Element)nodes.item(i)).getLocalName()+DELIMITER);
					//System.out.println(nodes.item(i).getNodeValue()+DELIMITER);
					try
					{
						file = new FileWriter(nom+"-content.csv");
						//Ajouter l'en-tête
						file.append(HEADER);
						//Ajouter une nouvelle ligne après l'en-tête
						file.append(SEPARATOR);
						for(int j=0; j<nodes.getLength(); j++){
							file.append(nodes.item(j).getNodeName() + DELIMITER + nodes.item(j).getTextContent());
							file.append(SEPARATOR);
							
						}
						file.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}else{
				System.out.println("L'extraction de NodeList a echoue.");
			}
		}
		catch(Exception e)
		{
			System.out.println("\nErreur");
			System.out.println("------------------------");
			e.printStackTrace();
			return;
		}
		System.out.println("\n------------------------");
		System.out.println("Programme execute avec succes");
	}
}