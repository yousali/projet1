package projet1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ValidationsCommunes {

    static boolean declarationValide = false;
    static ArrayList<String> erreurs = new ArrayList<>();

    static boolean permisValide(String permis) {//A completer

        return true;
    }

    static boolean ordreSupporte(String ordre) {

        return (ordre.equals("architectes") || ordre.equals("géologues") || ordre.equals("psycologues"));
    }

    static boolean noPermisValide(String noPermis) {

        return true;
    }

    static boolean descriptionActiviteValide(String description) {//A completer

        return true;
    }

    static void erreurDescriptionActivite() {

        System.out.println("Erreur, une description de plus de 20 caracteres devrait etre donnee pour "
                + "chaque activite faite.");
        erreurs.add("Une description de plus de 20 caracteres devrait etre donnee pour chaque activite faite.");
        declarationValide = false;
    }

    static void erreurFormatDate() {

        System.out.println(" Erreur, la date doit etre specifiee en format ISO 8601.");
        erreurs.add("La date doit etre specifiee en format ISO 8601.");
        declarationValide = false;

    }

    static void ecrireFichierSortie(String nomFichier) {

        JSONObject contenuRetourne = new JSONObject();
        contenuRetourne.put("Complet", declarationValide);
        JSONArray listeDesErreurs = new JSONArray();
        erreurs.stream().forEach((erreur) -> {
            listeDesErreurs.add(erreur);
        });
        contenuRetourne.put("erreurs", listeDesErreurs);

        try (FileWriter fichierDeSortie = new FileWriter(nomFichier)) {
            contenuRetourne.write(fichierDeSortie);
        } catch (IOException e) {
            System.out.println("Erreur d'ecriture dans le fichier specifie en argument");
        }
    }

}
