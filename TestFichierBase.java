import org.w3c.dom.Document;

/**
 * Teste la classe FichierBase. Pas important pour le rendu final.
 */
public class TestFichierBase {
    public static void main(String[] args) {
        FichierBase Test = new FichierBase("test.odp");
        ListeXML style = ListeXML.STYLES;
        Document style_Test = Test.parse(ListeXML.STYLES);
        System.out.println(style_Test);
    }
}
