public class TestFichierBase {
    public static void main(String[] args) {
        FichierBase Test = new FichierBase("test.odp");
        Test.parse((ListeXML)"STYLE");
    }
}
