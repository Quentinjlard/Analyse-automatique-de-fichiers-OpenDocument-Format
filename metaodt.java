import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import org.w3c.dom.*;

import java.io.*;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class metaodt
{
	public static void main(String[] args)
	{
		String odfFilePath;
		DocumentBuilder documentBuilder;
		XPath xpath;
		Document document;
		ZipFile zip;
		String DELIMITER = ",";
    	String SEPARATOR = "\n";
		String HEADER = "Valeur";

		if(args.length > 0)
			odfFilePath = args[0];
		else
		{
			System.out.println("Aide...");
			return;
		}
		try
		{
			System.out.println("Chargement...");
			zip = new ZipFile(odfFilePath);
			var header = zip.getFileHeader("meta.xml");
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			System.out.println("Analyse ...");
			document = documentBuilder.parse(zip.getInputStream(header));
			System.out.println("Document cree avec succes.");
		}
		catch(Exception e)
		{
			System.out.println("\nEchec");
			System.out.println("------------------------");
			e.printStackTrace();
			return;
		}
		try
		{
			System.out.println("XPath generation d'expression...");
			xpath = XPathFactory.newInstance().newXPath();
			String exp = "/document-meta/office/*";
			var res = xpath.compile(exp).evaluate(document, javax.xml.xpath.XPathConstants.NODESET);
			System.out.println("\nResultats :");
			System.out.println("------------------------");
			if(res instanceof NodeList)
			{
				NodeList nodes = (NodeList)res;
				FileWriter file = null;
				
				for(int i=0; i<nodes.getLength(); i++)
				{
					System.out.println(nodes.item(i).getNodeName() + DELIMITER + nodes.item(i).getTextContent());
					String str = (String) nodes.item(i).getAttributes();
					
					System.out.println(str);
					try
					{
						file = new FileWriter("Meta.csv");
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