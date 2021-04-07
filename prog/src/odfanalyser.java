import cls.odf.odt.MenuODT;
import cls.odf.ods.MenuODS;
import cls.odf.odp.MenuODP;
import cls.exception.*;
import cls.ext.*;

/**
 * Classe Main de l'application Odfanalyseur
 * @author Corentin Machet
 * @version 1.0
 */
public class odfanalyser
{
    private static String path;
    private static Extension fileExtension;
    private static boolean toDir = false;
    private static boolean isExtractable = false;
    private static boolean hasVerbose = false;

    /**
     * Analyse l'entr&eacute;e en ligne de commande de l'utilisateur, puis le redirige vers le menu adapt&eacute; au format de fichier
     * @param args arguments pass&eacute;s par l'utilisateur
     */
    public static void main(String[] args)
    {
        // vérifier la présence ou non d'arguments
        // si aucun argument, afficher la doc
        if(args.length < 1)
        {
            docs();
            System.exit(0);
        }
        // vérifier si le 1er arg est une option -t -s ou -p 
        // sinon il s'agit d'un path
        if(args[0].charAt(0) != '-')
            path = args[0];
        // traiter les cas -t -s et -p
        else
        {
            toDir = true;
            try
            {
                if(args[0].length() != 2)
                    throw new OdfException(ExceptionTypes.TYPE_ARGUMENT);
                switch(args[0].charAt(1))
                {
                    case 't' :
                        // Test => à modifier
                        new MenuODT();
                        break;
                    case 'p' : 
                        // Test => à modifier
                        new MenuODP();
                        break;
                    case 's' : 
                        // Test => à modifier
                        new MenuODS();
                        break;
                    default : throw new OdfException(ExceptionTypes.TYPE_ARGUMENT);
                }
                // continuer l'analyse (options)
                if(args.length > 2)
                {
                    String[] opt = new String[(args.length - 2)];
                    int j=0;
                    for(int i=2; i<args.length; i++)
                    {
                        opt[j] = args[i];
                        j++;
                    }
                    options(opt);
                }
            }
            catch(OdfException e)
            {
                System.err.println(e.getMessage());
                System.exit(0);
            }
        }
        // si aucun des cas -t -s ou -p, alors continuer l'analyse...
        if(!toDir)
        {
            if (args.length > 1)
            {
                String[] opt = new String[(args.length - 1)];
                int j = 0;
                for (int i = 1; i < args.length; i++)
                {
                    opt[j] = args[i];
                    j++;
                }
                options(opt);
            }
        }
        // ...sinon passer directement à l'execution
        // appel aux menus
        try
        {
            if(java.nio.file.Paths.get(path).endsWith(".odt"))
            {

            }
            else if(java.nio.file.Paths.get(path).endsWith(".odp"))
            {

            }
            else if(java.nio.file.Paths.get(path).endsWith(".ods"))
            {

            }
            else
            {
                throw new OdfException(ExceptionTypes.EXTENSION);
            }
        }
        catch(java.nio.file.InvalidPathException e)
        {
            System.err.println((new OdfException(ExceptionTypes.INVALID_PATH)).getMessage());
            System.exit(0);
        }
        catch(OdfException e)
        {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Gestion des des options verbose et extraction
     * @param opt liste des options pass&eacute;s en param&egrave;tre
     */
    private static void options(String[] opt)
    {

    }

    /**
     * Affichage de la doc dans le terminal
     */
    private static void docs()
    {
        String doc = "ODF ANALYSER";
        doc += "\n-------------------------";
        doc += "\nDocumentation";
        doc += "\nOdfAnalyser est un logiciel de traitement de l'information\nen ligne de commande et menu textuel,\nsur les fichiers text, presentation et tableur\nde la suite ODF par OASIS.";
        doc += "\n\nJAVA -JAR ODFANALYSER.JAR [FILE][OPTION]";
        doc += "\n                          [-ARG [DIR]][OPTION]";
        doc += "\n\n>>> FILE";
        doc += "\nLe path vers le fichier odt, ods ou odp a analyser.";
        doc += "\n\n>>> ARG";
        doc += "\nA la place d'un unique path, il est possible d'analyser un ensemble\nde fichiers d'un type donne, contenu dans un repertoire.";
        doc += "\n\n\t-t [DIR]\t\tSuivi du path vers le repertoire,\n        \t\t\tl'argument -t permet l'analyse des fichiers odt.";
        doc += "\n\t-p [DIR]\t\tIdem que l'argument -t mais pour les odp.";
        doc += "\n\t-s [DIR]\t\tIdem que les arguments -t et -p mais pour les ods.";
        doc += "\n\n>>> OPTION";
        doc += "Autres actions possibles.";
        doc += "\n\n\t-x [DIR]\t\tCette option force la decompression de l'archive odf.\n        \t\t\tSi elle est suivie d'un repertoire, le resultat de\n        \t\t\tla decompression aura lieu dans ce dossier specifie.";
        doc += "\n\t-v [DIR]\t\tVerbose.";
        doc += "\n\njava -jar odfanalyser.jar\tAffiche cette documentation.";
        doc += "\n\n-------------------------";
        doc += "\n\nAuteurs : Corentin Machet, Corentin Antoine, Corentin Niarquin\n          Quentin Juillard, Victor Lanotte, Florian Colson";
        System.out.println(doc);
    }
}