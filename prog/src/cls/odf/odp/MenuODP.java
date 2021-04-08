package cls.odf.odp;

import cls.ext.OdfFiles;
import java.util.Vector;

public class MenuODP
{
    public MenuODP(Vector<OdfFiles> vector)
    {
        for (OdfFiles odf : vector)
        {
            System.out.println(odf.get("content.xml").getNodeName());
        }
    }
}