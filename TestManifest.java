import org.w3c.dom.Document;

/**
 * Teste l'analyse du fichier manifest.xml, pas utile pour le projet final
 */
public class TestManifest {
   public static void main(String[] args) {
        AnalyseManifest Test = new AnalyseManifest("test.odp");
        int NombreImages = Test.NombreImages();
        System.out.println(NombreImages);
    }
}
