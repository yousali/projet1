package projet1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Psychologues {

    static void traiter(JSONObject contenu) throws ParseException {
        ArrayList<String> activitesTraitees = new ArrayList<>();
        Integer nbreHeuresCours = 0;
        Integer nbreHeuresConference = 0;
        Integer heure = 0;
        Integer heureTotale = 0;
        
        if (!validerCyclePsychologues(contenu)) {
            Projet1.declarationValide = false;
            Projet1.erreurs.add("Le cycle entré n'est pas supporte");
            return;
        }
        Integer sexe = contenu.getInt("sexe");
        if (!Projet1.sexeValide(sexe)) {
            Projet1.erreurSexe();
            return;

        }
        String noPermis = contenu.getString("numero_de_permis");
        if (!(noPermisPsychologuesValide(noPermis))) {
            Projet1.erreurDePermis();
            return;
        }

        

        JSONArray activitesFaites = contenu.getJSONArray("activites");

        for (int i = 0; i < activitesFaites.size(); i++) {
            JSONObject activiteSuivie = activitesFaites.getJSONObject(i);
            String categ = activiteSuivie.getString("categorie");
           // String description = activiteSuivie.getString("description");
            heure = activiteSuivie.getInt("heures");
            //heure = validationHeure(heure);

            String descriptionActivite = activiteSuivie.getString("description").trim().toLowerCase();
            String dateActivite = activiteSuivie.getString("date").trim();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);

            if (!validationDate(date)) {
                Projet1.erreurs.add("L'activité " + descriptionActivite + " n'a pas ete complétée"
                        + " entre le 1er Janvier 2010 et le 1er Janvier 2015.");
            }

            if (!ValidationCategorie(categ)) {
                Projet1.erreurs.add("L'activité " + categ + "n'appartient a aucune categorie reconnue");
            }

            if (!Projet1.descriptionActiviteValide(descriptionActivite)) {
                Projet1.erreurs.add("Une description de plus de 20 caracteres devrait etre donnee pour chaque activite faite.");
            }

            if (ValidationCategorie(categ) && validationDate(date)) {

                if (categ.equals("conférence")) {

                    nbreHeuresConference += heure;

                } else {
                    // les heures totales son incremente si ce nest pas une conference. 
                    // on ne risque pas de les faire changer.
                    if (categ.equals("cours")) {
                        nbreHeuresCours += heure;
                    }

                    heureTotale += heure;
                }

            }

        }

        if (nbreHeuresCours < 25) {
            Projet1.erreurs.add("Il y a moins de 25 heures de cours");
        }

        if (nbreHeuresConference > 15) {
            nbreHeuresConference = 15;
        }

        int nbreTotalHeures = heureTotale + nbreHeuresConference;

        if (nbreTotalHeures < 90) {

            Projet1.erreurs.add("Il manque " + (90 - nbreTotalHeures) + " heure(s) de formation pour completer le cycle.");

        }

    }

    static boolean validerCyclePsychologues(JSONObject contenu) {

        String cycle = contenu.getString("cycle").trim();
        if (cycle.equals("2010-2015")) {
            return true;
        }
        return false;
    }

    static boolean validationDate(Date date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-01");

        if (date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0) {
            return true;
        }
        return false;
    }

    static boolean ValidationCategorie(String Categorie) {
        ArrayList<String> categ = new ArrayList<String>();
        categ.add("cours");
        categ.add("atelier");
        categ.add("séminaire");
        categ.add("colloque");
        categ.add("conférence");
        categ.add("lecture dirigée");
        categ.add("présentation");
        categ.add("groupe de discussion");
        categ.add("projet de recherche");
        categ.add("rédaction professionnelle");
        if (categ.contains(Categorie)) {
            return true;
        }
        return false;
    }

    static boolean validerCyclePsychologues(String cycle) {

        return (cycle.equals("2010-2015"));
    }

    static boolean noPermisPsychologuesValide(String noPermis) {

        return noPermis.matches("[0-9]{5}-[0-9]{2}");
    }
}
