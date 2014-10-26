package projet1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ValidationsCommunes {

    static boolean declarationValide = true;
    static ArrayList<String> erreurs = new ArrayList<>();

    static boolean noPermisValide(String noPermis) {
        
       return noPermis.matches("[ARSZ][0-9]{4}");   
    }

    static boolean descriptionActiviteValide(String description) {

        return (description.length() > 20);
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
    
    static void erreurInteger(){
        
        System.out.println("Erreur! Les heures d'activite devraient etre des entiers positifs");
        ArrayList<String> erreurInteger = new ArrayList<>();
        erreurInteger.add("Erreur! Les heures d'activite devraient etre des entiers positifs");
        ValidationsCommunes.erreurs = erreurInteger;
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
