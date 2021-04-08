package cls.odf.ods;

import cls.ext.OdfFiles;
import java.util.Vector;

public class MenuODS
{
    public MenuODS(Vector<OdfFiles> vector)
    {
        for (OdfFiles odf : vector)
        {
            System.out.println(odf.get("content.xml").getNodeName());
        }
    }
}