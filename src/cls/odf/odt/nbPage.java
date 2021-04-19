package cls.odf.odt;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import org.w3c.dom.*;

import java.io.*;

public class nbPage {
    

    public nbPage(Document document, String nom){

        String DELIMITER = "/";
    	String SEPARATOR = "\n";
		String HEADER = "Balise/ Contenu";
		XPath xpath;
		FileWriter file;

        try
		{
			xpath = XPathFactory.newInstance().newXPath();
			String exp = "/document-content/meta/document-statisti";
			if(document == null)
				throw new Exception("Document cannot be null");
			var res = xpath.compile(exp).evaluate(document, javax.xml.xpath.XPathConstants.NODESET);
			System.out.println("\nResultats :");
			System.out.println("------------------------");

			if(res instanceof NodeList)
			{
				NodeList nodes = (NodeList)res;
				for(int i=0; i<nodes.getLength(); i++)
				{
					System.out.print("\n" + nodes.item(i).getNodeName() + " : ");
					if(nodes.item(i) instanceof Element)
					{
						System.out.println("#element");
						var attributs = nodes.item(i).getAttributes();
						if(attributs == null)
						{
							System.out.println("\tNe contient pas d'attributs...");
						}
						else
						{
                            file = new FileWriter(nom+"-content.csv");
                            //Ajouter l'en-tête
                            file.append(HEADER);
                            //Ajouter une nouvelle ligne après l'en-tête
                            file.append(SEPARATOR);
							for(int j=0; j<attributs.getLength(); j++)
							{
								var attr = (Attr)(attributs.item(j));
								System.out.println("\t" + attr.getName() + " = " + attr.getValue());
                                file.append (attr.getName() + DELIMITER + attr.getValue());
                                file.append(SEPARATOR);
							}
                            
                            file.close();
                            }
					}
					else
					{
						System.out.println("##");
					}
				}

			}else{
				System.out.println("L'extraction a echoue.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}        
    }
}


