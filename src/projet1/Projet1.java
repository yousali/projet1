package projet1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Projet1 {

    static boolean declarationValide = true;
    static ArrayList<String> erreurs = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        if (args.length == 1 && args[0].matches("-S")) {
            Statistiques.calculer();
        } else if (args.length == 1 && args[0].matches("-SR")) {
            Statistiques.reinitialiser();
        } else if (args.length == 2) {
            String fichier = FileReader.loadFileIntoString(args[0], null);
            Statistiques.chargerStats();
            Statistiques.nbreDeclarationsTraitees++;
            traiterFichierEntree(fichier);
            Statistiques.enregistrerStats();
            ecrireFichierSortie(args[1]);
        } else {

            throw new IllegalArgumentException("Le programme accepte un argument pour le calcul des statistiques  et deux pour la validation d<activites de formation continue");
        }
    }

    static void traiterFichierEntree(String fichier) throws ParseException {
        try {
            JSONObject contenu = (JSONObject) JSONSerializer.toJSON(fichier);

            String ordre = contenu.getString("ordre").trim().toLowerCase();
            switch (ordre) {

                case "architectes":
                    Architectes.traiter(contenu);
                    break;
                case "geologues":
                    Geologues.traiter(contenu);
                    break;
                case "psychologues":

                    Psychologues.traiter(contenu);
                    break;
                case "podiatres":
                    Podiatres.traiter(contenu);
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

    static void erreurDeCycle() {
        System.out.println("Erreur du cycle");
        Projet1.erreurs.add("Le cycle entre n'est pas supporte.");
        Projet1.declarationValide = false;
        Statistiques.nbreDeclarationsIncompletes++;
    }

    static boolean sexeValide(Integer sexe) {
        return (sexe == 0 || sexe == 1 || sexe == 2);
    }
    static void erreurSexe() {
        System.out.println("Erreur, le sexe entré n'est pas valide.");
        Projet1.declarationValide = false;
        Projet1.erreurs.add("Erreur dans le fichier entré. Le sexe est invalide.");
        Statistiques.nbreDeclarationsIncompletes++;
        
    }

    static void erreurDePermis() {
        System.out.println("Erreur, le numero de permis est invalide");
        Projet1.declarationValide = false;
        Projet1.erreurs.add("Le fichier d'entree est invalide.");
        Statistiques.nbreDeclarationsIncompletes++;
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
        Statistiques.nbreDeclarationsIncompletes++;
    }

    static boolean dateValide(String dateAVerifier) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateAVerifier);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    static void erreurFormatDate() {
        System.out.println(" Erreur, la date doit etre specifiée en format ISO 8601.");
        erreurs.clear();
        erreurs.add("Le fichier d'entree est invalide.");
        declarationValide = false;
        Statistiques.nbreDeclarationsIncompletes++;
    }

    static void erreurInteger() {
        System.out.println("Erreur! Les heures d'activité devraient etre des entiers positifs");
        erreurs.clear();
        erreurs.add("Erreur! Les heures d'activité devraient etre des entiers positifs");
        declarationValide = false;
        Statistiques.nbreDeclarationsIncompletes++;
    }

    static void erreurDansFichierJSON() {
        System.out.println("Erreur! Le fichier d'entree est invalide.");
        erreurs.clear();
        erreurs.add("Le fichier d'entree est invalide.");
        declarationValide = false;
        Statistiques.nbreDeclarationsIncompletes++;

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
