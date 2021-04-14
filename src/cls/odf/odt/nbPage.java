package cls.odf.odt;

import javax.xml.parsers.*;
import org.w3c.dom.*;


public class nbPage {
    

    public nbPage(Document document){

        Document doc = document;
        NodeList attributList = doc.getElementsByTagName("document-statistic");
        attributList = doc.getElementsByTagName("page-count");
        for (int  i = 0; i < attributList.getLength(); i++){
            Node a = attributList.item(i);
            if(a.getNodeType() == Node.ELEMENT_NODE){
                Element attr = (Element) a;
                String pagecount = attr.getAttribute("page-count");
                NodeList pageList = attr.getChildNodes();
                for(int j= 0; j<pageList.getLength();j++){
                    Node attri = pageList.item(j);
                    if(attri.getNodeType() == Node.ELEMENT_NODE){
                        Element attribut = (Element) attri;
                        System.out.println("Test" + pagecount + " : " + attribut.getTagName() + " = " + attribut.getTextContent() ); 
                    }
                }
            }
        }
        
    }
    
}


