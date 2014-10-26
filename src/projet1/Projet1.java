package projet1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Projet1 {

    static boolean declarationValide = true;
    static ArrayList<String> erreurs = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        String fichier = FileReader.loadFileIntoString(args[0], null);
        traiterFichierEntree(fichier);
        ecrireFichierSortie(args[1]);

    }

    static void traiterFichierEntree(String fichier) {
        try {
            JSONObject contenu = (JSONObject) JSONSerializer.toJSON(fichier);

            String ordre = contenu.getString("ordre").trim().toLowerCase();
            switch (ordre) {

                case "architectes":
                    ValidationArchitectes.traiter(contenu);
                    break;
                case "geologues":
                    ValidationGeologues.traiter();
                    break;
                case "psychologues":

                    ValidationPsychologues.traiter();
                    break;
                default:
                    System.out.println("L'ordre n'est pas supporte");
                    erreurs.add("L'ordre n'est pas supporte.");
                    declarationValide = false;

            }
        } catch (JSONException e) {

            erreurDansFichierJSON();

        }
    }

    static boolean noPermisValide(String noPermis) {
        return noPermis.matches("[ARSZ][0-9]{4}");
    }

    static boolean descriptionActiviteValide(String description) {
        return (description.length() > 20);
    }

    static void erreurDescriptionActivite() {
        System.out.println("Erreur, une description de plus de 20 caracteres devrait etre donnée pour "
                + "chaque activité faite.");
        erreurs.clear();
        erreurs.add("Le fichier d'entree est invalide.");
        declarationValide = false;
    }

    static void erreurFormatDate() {
        System.out.println(" Erreur, la date doit etre specifiée en format ISO 8601.");
        erreurs.clear();
        erreurs.add("Le fichier d'entree est invalide.");
        declarationValide = false;
    }

    static void erreurInteger() {
        System.out.println("Erreur! Les heures d'activité devraient etre des entiers positifs");
        erreurs.clear();
        erreurs.add("Erreur! Les heures d'activité devraient etre des entiers positifs");

        declarationValide = false;
    }

    static void erreurDansFichierJSON() {
        System.out.println("Erreur! Le fichier d'entree est invalide.");
        erreurs.clear();
        erreurs.add("Le fichier d'entree est invalide.");
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
            System.out.println("Erreur d'ecriture dans le fichier specifié en argument");
        }
    }

}
