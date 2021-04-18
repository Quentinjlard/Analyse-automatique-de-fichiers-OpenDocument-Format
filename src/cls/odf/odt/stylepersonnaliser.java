package cls.odf.odt;

import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPath;
import org.w3c.dom.*;

import java.io.*;

public class stylepersonnaliser
{
	public stylepersonnaliser(Document document, String nom)
	{
		XPath xpath;
		String DELIMITER = "/";
    	String SEPARATOR = "\n";
		String HEADER = "Colonne 1 / Colonne 2 / Colonne 3 / Colonne 4";
		FileWriter file;

		try
		{
			xpath = XPathFactory.newInstance().newXPath();
			String exp = "/document-styles/styles/style";
			if(document == null)
				throw new Exception("Document cannot be null");
			var res = xpath.compile(exp).evaluate(document, javax.xml.xpath.XPathConstants.NODESET);
			System.out.println("\nResultats :");
			System.out.println("------------------------");

			if(res instanceof NodeList)
			{
				file = new FileWriter(nom+"-auteur.csv");
				file.append(HEADER);
				file.append(SEPARATOR);

				NodeList nodes = (NodeList)res;
				for(int i=0; i<nodes.getLength(); i++)
				{
					
					file.append(SEPARATOR);
					file.append(nodes.item(i).getNodeName()+" : ");

					System.out.print("\n" + nodes.item(i).getNodeName() + " : ");
					
					if(nodes.item(i) instanceof Element)
					{
						System.out.println("#element");

						file.append(SEPARATOR);
						file.append( nodes.item(i).getNodeName()+DELIMITER+"#element");
						file.append(SEPARATOR);

						var attributs = nodes.item(i).getAttributes();
						if(attributs == null)
						{
							System.out.println("\tNe contient pas d'attributs...");

							file.append(SEPARATOR);
							file.append( "Ne contient pas d'attributs...");
							file.append(SEPARATOR);
						}
						else
						{
							var attr = (Attr)(attributs.getNamedItem("style:name"));
							if( !attr.getValue().equals("Standard") && !attr.getValue().equals("Heading") && !attr.getValue().equals("Text_20_body") && !attr.getValue().equals("Caption") && !attr.getValue().equals("Index") && !attr.getValue().equals("List"))
							{
								System.out.println("\tGeneral : ");

								file.append(SEPARATOR);
								file.append( DELIMITER+" "+"General : ");

								for(int j=0; j<attributs.getLength(); j++)
								{
									var attr1 = (Attr)(attributs.item(j));
									System.out.println("\t\t" + attr1.getName() + " = " + attr1.getValue());

									file.append(SEPARATOR);
									file.append(DELIMITER+" "+DELIMITER+" "+ attr1.getName() +" "+DELIMITER+" "+ attr1.getValue());
								}

								var elem = nodes.item(i).getChildNodes();
								for(int j=0; j<elem.getLength(); j++)
								{
									if(elem.item(j) instanceof Element)
									{
										System.out.println("\t" + elem.item(j).getNodeName() + " : ");

										file.append(SEPARATOR);
										file.append(SEPARATOR);
										file.append(" "+DELIMITER+" "+ elem.item(j).getNodeName() + " : ");
										

										var elem_attributs = elem.item(j).getAttributes();
										for(int k=0; k<elem_attributs.getLength(); k++)
										{
											var attr2 = (Attr)(elem_attributs.item(k));
											System.out.println("\t\t" + attr2.getName() + " = " + attr2.getValue());

											file.append(SEPARATOR);
											file.append(DELIMITER+" "+DELIMITER+" "+ attr2.getName() +" "+DELIMITER+" "+ attr2.getValue());
										}
									}
								}
							}
						}
					}
					else
					{
						System.out.println("##");
					}
					
				}
				file.close();
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