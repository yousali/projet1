package projet1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class Geologues {

    static void traiter(JSONObject contenu) {
        Integer sexe = contenu.getInt("sexe");
        if (!Projet1.sexeValide(sexe)) {
            Projet1.erreurSexe();
            return;
        }

        if (!(noPermisGeologuesValide(contenu))) {
            Projet1.erreurDePermis();
            return;
        }
        String cycle = contenu.getString("cycle").trim();
        if (!cycleValide(cycle)) {
            Projet1.erreurDeCycle();
            return;
        }

    }

    static boolean noPermisGeologuesValide(JSONObject contenu) {
        String nom = contenu.getString("nom").trim().toUpperCase();
        String prenom = contenu.getString("prenom").trim().toUpperCase();
        String noPermis = contenu.getString("numero_de_permis").trim();
        return noPermis.matches(nom.charAt(0) + "" + prenom.charAt(0) + "[0-9]{4}");
    }

    static boolean cycleValide(String cycle) {

        return cycle.equals("2013-2016");
    }

    /**
     *
     * static boolean validationCycle(JSONObject contenu) { String cycle =
     * contenu.getString("cycle").trim(); if (cycle.equals("2013-2016")) {
     * return true; } return false; }
     *
     * static boolean validationDate(Date date) throws ParseException { Date
     * date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2013-06-01"); Date
     * date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2016-06-01");
     *
     * if (date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0) { return
     * true; } return false; }
     *
     * static boolean ValidationCategorie(String Categorie) { ArrayList<String>
     * categ = new ArrayList<String>(); categ.add("cours");
     * categ.add("atelier"); categ.add("séminaire"); categ.add("colloque");
     * categ.add("conférence"); categ.add("lecture dirigée");
     * categ.add("présentation"); categ.add("groupe de discussion");
     * categ.add("projet de recherche"); categ.add("rédaction professionnelle");
     * if (categ.contains(Categorie)) { return true; } return false; }
     *
     * static void traiter(JSONObject contenu) throws ParseException {
     *
     * ArrayList<String> activitesTraitees = new ArrayList<>(); Integer
     * nbreHeuresCours = 0; Integer nbreHeuresGroupeDeDiscussion = 0; Integer
     * nbreHeuresProjetDeRecherche = 0;
     *
     * Integer heure = 0; Integer heureTotale = 0;
     *
     * if (!validationCycle(contenu)) { erreurs.add("Le cyle entré n'est pas
     * supporte"); } else { JSONArray activitesFaites =
     * contenu.getJSONArray("activites"); String permis =
     * contenu.getString("numero_de_permis"); for (int i = 0; i <
     * activitesFaites.size(); i++) { JSONObject activiteSuivie =
     * activitesFaites.getJSONObject(i); String categ =
     * activiteSuivie.getString("categorie"); String description =
     * activiteSuivie.getString("description"); heure =
     * activiteSuivie.getInt("heures"); // heure = validationHeure(heure);
     *
     * String descriptionActivite =
     * activiteSuivie.getString("description").trim().toLowerCase(); String
     * dateActivite = activiteSuivie.getString("date").trim(); Date date = new
     * SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);
     *
     * if (!validationDate(date)) { erreurs.add("L'activité " +
     * descriptionActivite + " n'a pas ete complétée" + " entre le 1er Juin 2013
     * et le 1er Juin 2016."); }
     *
     * if (!ValidationCategorie(categ)) { erreurs.add("L'activité " + categ +
     * "n'appartient a aucune categorie reconnue"); }
     *
     * if (!noPermisValide(permis)) { erreurs.add("Le numeros de permis n'est
     * pas valide"); }
     *
     * if (!descriptionActiviteValide(description)) { erreurs.add("Une
     * description de plus de 20 caracteres devrait etre donnee pour chaque
     * activite faite."); }
     *
     * if (ValidationCategorie(categ) && validationDate(date)) { heureTotale +=
     * heure; //on increment toujours les heures if (categ.equals("cours")) {
     * nbreHeuresCours += heure; }
     *
     * if (categ.equals("groupe de discussion")) { nbreHeuresGroupeDeDiscussion
     * += heure; }
     *
     * if (categ.equals("projet de recherche")) { nbreHeuresProjetDeRecherche +=
     * heure; } } }
     *
     * if (nbreHeuresCours < 22) { erreurs.add("Il y a moins de 22 heures de
     * cours"); }
     *
     * if (nbreHeuresProjetDeRecherche < 3) { erreurs.add("Il y a moins de 3
     * heures de projet de recherche"); }
     *
     * if (nbreHeuresGroupeDeDiscussion < 1) { erreurs.add("Il y a moins d'une
     * heure de groupe de discution"); }
     *
     * if ((heureTotale) < 55) { erreurs.add("Il manque " + (55 - heureTotale) +
     * " heure(s) de formation pour completer le cycle."); } } }
     */
}
