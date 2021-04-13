import cls.odf.odt.MenuODT;
import cls.odf.ods.MenuODS;
import cls.odf.odp.MenuODP;
import cls.exception.*;
import cls.ext.*;

import java.io.File;
import java.util.Vector;

import cls.net.lingala.zip4j.ZipFile;
// import cls.net.lingala.zip4j.exception.ZipException;

/**
 * Classe Main de l'application Odfanalyseur
 * @author Corentin Machet
 * @version 1.0
 */
public class odfanalyser
{
    private static String path;
    private static String extractPath;
    private static Extension fileExtension = Extension.UNDEFINED;
    private static boolean toDir = false;
    private static boolean isExtractable = false;
    private static boolean hasVerbose = false;
    private static ZipFile zip;
    private static Vector<OdfFiles> vector = new Vector<OdfFiles>();

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
                        fileExtension = Extension.ODT;
                        if (args[1].charAt(0) == '-')
                            throw new OdfException(ExceptionTypes.NEEDED_PATH);
                        path = args[1];
                        break;
                    case 'p' : 
                        fileExtension = Extension.ODP;
                        if (args[1].charAt(0) == '-')
                            throw new OdfException(ExceptionTypes.NEEDED_PATH);
                        path = args[1];
                        break;
                    case 's' : 
                        fileExtension = Extension.ODS;
                        if (args[1].charAt(0) == '-')
                            throw new OdfException(ExceptionTypes.NEEDED_PATH);
                        path = args[1];
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
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.err.println((new OdfException(ExceptionTypes.NEEDED_PATH)).getMessage());
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
                try
                {
                    options(opt);
                }
                catch(OdfException e)
                {
                    System.err.println(e.getMessage());
                    System.exit(0);
                }
            }
        }
        // ...sinon passer directement à l'execution
        // appel aux menus
        try
        {
            File f = new File(path);
            if(f.exists())
            {
                if(fileExtension != Extension.UNDEFINED && f.isDirectory())
                {
                    // récupérer ts les fichiers dans des OdfFiles
                }
                else if(fileExtension == Extension.UNDEFINED && f.isFile())
                {
                    if(path.endsWith(".odt"))
                    {
                        fileExtension = Extension.ODT;
                        zip = new ZipFile(path);
                        vector.add(new OdfFiles(zip));
                    }
                    else if(path.endsWith(".odp"))
                    {
                        fileExtension = Extension.ODP;
                        zip = new ZipFile(path);
                        vector.add(new OdfFiles(zip));
                    }
                    else if(path.endsWith(".ods"))
                    {
                        fileExtension = Extension.ODS;
                        zip = new ZipFile(path);
                        vector.add(new OdfFiles(zip));
                    }
                    else
                    {
                        throw new OdfException(ExceptionTypes.EXTENSION);
                    }
                }
                else
                {
                    throw new OdfException(ExceptionTypes.UNDEFINED_ERROR);
                }
            }
            else
            {
                throw new OdfException(ExceptionTypes.FILE_DOES_NOT_EXIST);
            }
        }
        catch(NullPointerException e)
        {
            System.err.println((new OdfException(ExceptionTypes.INVALID_PATH)).getMessage());
            System.exit(0);
        }
        catch(OdfException e)
        {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        // si toutes les vérifications sont faites, valider l'execution
        goToMenu();
    }

    /**
     * Fait l'appel au menu
     */
    private static void goToMenu()
    {
        if(hasVerbose)
            verify();
        if(!isExtractable)
        {
            switch(fileExtension)
            {
                case ODT :
                    new MenuODT(vector);
                    break;
                case ODP :
                    new MenuODP(vector);
                    break;
                case ODS : 
                    new MenuODS(vector);
                    break;
                default : 
                    // du code...
                    break;
            }
        }
        else
        {
            // utiliser les variables path et extractPath pour l'extraction 
        }
    }

    /**
     * Gestion des des options verbose et extraction
     * @param opt liste des options pass&eacute;s en param&egrave;tre
     */
    private static void options(String[] opt) throws OdfException
    {
        for(int j=0; j<opt.length; j++)
        {
            if(opt[j].charAt(0) != '-')
                throw new OdfException(ExceptionTypes.INVALID_OPTION);
            for(int i=1; i<opt[j].length(); i++)
            {
                switch(opt[j].charAt(i))
                {
                    case 'x':
                        isExtractable = true;
                        break;
                    case 'v':
                        hasVerbose = true;
                        break;
                    default : throw new OdfException(ExceptionTypes.INVALID_OPTION);
                }
            }
            if(isExtractable && extractPath == null)
            {
                j++;
                try
                {
                    extractPath = opt[j];
                    if(!(new File(extractPath)).isDirectory())
                        extractPath = null;
                    if(opt[j].equals("-v"))
                        hasVerbose = true;
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    extractPath = null;
                }
            }
        }
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

    /**
     * Affiche la description complète des attribut de la classe pour vérifier son bon fonctionnement
     */
    private static void verify()
    {
        String str = "Description";
        str += "\n\tPath : " + path;
        str += "\n\tExtract Path : " + extractPath;
        str += "\n\tFile Extension : " + fileExtension;
        str += "\n\tExtractable : " + isExtractable;
        str += "\n\tVerbose : " + hasVerbose;
        System.out.println(str);
    }
}