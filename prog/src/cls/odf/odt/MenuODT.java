package cls.odf.odt;

import cls.ext.OdfFiles;
import java.util.Vector;

public class MenuODT
{
    public MenuODT(Vector<OdfFiles> vector)
    {
        for(OdfFiles odf : vector)
        {
            System.out.println(odf.get("content.xml").getNodeName());
        }
    }
}