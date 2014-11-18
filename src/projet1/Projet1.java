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
        String fichier = FileReader.loadFileIntoString(args[0], null);
        traiterFichierEntree(fichier);
        ecrireFichierSortie(args[1]);

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
    }

    static boolean sexeValide(Integer sexe){
        return (sexe == 0 || sexe == 1 || sexe ==2);
    }
    
    static void erreurSexe(){
        
        System.out.println("Erreur, le sexe entré n'est pas valide.");
        Projet1.declarationValide = false;
        Projet1.erreurs.add("Erreur dans le fichier entré. Le sexe est invalide.");
    }
    static void erreurDePermis() {
        System.out.println("Erreur, le numero de permis est invalide");
        Projet1.declarationValide = false;
        Projet1.erreurs.add("Le fichier d'entree est invalide.");
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
    static boolean dateValide(String dateAVerifier){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sdf.setLenient(false);
        try{
            Date date =sdf.parse(dateAVerifier);
        }catch(ParseException e){
            return false;
        }
        return true;
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
        erreurs.add("ALe fichier d'entree est invalide.");
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
