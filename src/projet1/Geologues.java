package projet1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class Geologues {
    
    static ArrayList<String> activitesTraitees = new ArrayList<>();
    static final Integer NBREHEURESREQUISES = 55;
    static Integer nbreHeuresCours = 0;
    static Integer nbreHeuresGroupeDeDiscussion = 0;
    static Integer nbreHeuresProjetDeRecherche = 0;
    static Integer TotalHeure = 0;

    static void traiter(JSONObject contenu) {
        Integer sexe = contenu.getInt("sexe");
        if (!Projet1.sexeValide(sexe)) {
            Projet1.erreurSexe();
            return;
        }
        //String noPermis = contenu.getString("numero_de_permis").trim();
        if (!(noPermisGeologuesValide(contenu))) {
            Projet1.erreurDePermis();
            return;
        }
        String cycle = contenu.getString("cycle").trim();
        if (!cycleValide(cycle)) {
            Projet1.erreurDeCycle();
            return;
        }
        JSONArray activitesFaites = (JSONArray) contenu.getJSONArray("activites");
        for (int i = 0; i < activitesFaites.size(); i++) {
            JSONObject activiteSuivie = activitesFaites.getJSONObject(i);
            String descriptionActivite = activiteSuivie.getString("description").trim().toLowerCase();
            if (!Projet1.descriptionActiviteValide(descriptionActivite)) {
                Projet1.erreurDescriptionActivite();
                return;
            }
            String dateActivite = activiteSuivie.getString("date").trim();
            if (!Projet1.dateValide(dateActivite)) {
                Projet1.erreurFormatDate();
                return;
            }
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);
                if (!validerDateActivite(date)) {
                    Projet1.erreurs.add("L'activité " + descriptionActivite
                            + " n'a pas ete completee entre le 1er juin 2013 et le 1er juin 2016.");

                } else {
                    String categorieActivite = activiteSuivie.getString("categorie").trim().toLowerCase();
                    String activite = categorieActivite + " " + descriptionActivite + " " + dateActivite;
                    if (activitesTraitees.indexOf(activite) == -1) {
                        activitesTraitees.add(activite);
                        int nbreHeuresActivite = activiteSuivie.getInt("heures");
                        if (nbreHeuresActivite > 0) {
                            ajouterActivite(categorieActivite, nbreHeuresActivite);
                        } else {
                            Projet1.erreurInteger();
                            return;
                        }

                    }
                }
            } catch (ParseException e) {
                Projet1.erreurFormatDate();
                break;
            } catch (JSONException e) {

                Projet1.erreurInteger();
                break;
            } catch (NumberFormatException e) {

                Projet1.erreurInteger();
                return;
            }

        }
        analyserHeuresFaites();

    }

    static void ajouterActivite(String categorieActivite, int nbreHeuresActivite) {

        switch (categorieActivite) {
            case "cours":
                Statistiques.nbreActivitesDansCours++;
                nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "atelier":
                Statistiques.nbreActivitesDansAtelier++;
                TotalHeure = TotalHeure + nbreHeuresActivite;

                break;
            case "séminaire":
                Statistiques.nbreActivitesDansSeminaire++;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "colloque":
                Statistiques.nbreActivitesDansColloque++;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "conférence":
                Statistiques.nbreActivitesDansConference++;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "lecture dirigée":
                Statistiques.nbreActivitesDansLectureDirigee++;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "présentation":
                Statistiques.nbreActivitesDansPresentation++;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "groupe de discussion":
                Statistiques.nbreActivitesDansGDiscussion++;
                nbreHeuresGroupeDeDiscussion = nbreHeuresGroupeDeDiscussion + nbreHeuresActivite;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "projet de recherche":
                Statistiques.nbreActivitesDansPRecherche++;
                nbreHeuresProjetDeRecherche = nbreHeuresProjetDeRecherche + nbreHeuresActivite;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            case "rédaction professionnelle":
                Statistiques.nbreActivitesDansRProfessionnelle++;
                TotalHeure = TotalHeure + nbreHeuresActivite;
                break;
            default:
                Projet1.erreurs.add("La catégorie " + categorieActivite + " n'est pas reconnue.");
        }

    }

    

    static boolean validerDateActivite(Date date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2013-06-01");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2016-06-01");

        return date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0;
    }

    static boolean cycleValide(String cycle) {

        return cycle.equals("2013-2016");
    }
    static boolean noPermisGeologuesValide(JSONObject contenu) {
        String nom = contenu.getString("nom").trim().toUpperCase();
        String prenom = contenu.getString("prenom").trim().toUpperCase();
        String noPermis = contenu.getString("numero_de_permis").trim();
        return noPermis.matches(nom.charAt(0) + "" + prenom.charAt(0) + "[0-9]{4}");
    }

    static void analyserHeuresFaites() {

        if (nbreHeuresCours < 22) {
            Projet1.erreurs.add("Il y a moins de 22 heures de cours");
        }
        if (nbreHeuresProjetDeRecherche < 3) {
            Projet1.erreurs.add("Il y a moins de 3 heures de projet de recherche");  
        }
        if (nbreHeuresGroupeDeDiscussion < 1) {
            Projet1.erreurs.add("Il y a moins d'une heure de groupe de discution");   
        }
        if ((TotalHeure) < NBREHEURESREQUISES) {
            Projet1.erreurs.add("Il manque " + (NBREHEURESREQUISES - TotalHeure) + " heure(s) de formation pour completer le cycle.");
        }
       if (nbreHeuresCours < 22 || nbreHeuresProjetDeRecherche < 3 || nbreHeuresGroupeDeDiscussion < 1 || TotalHeure < NBREHEURESREQUISES){
            Projet1.declarationValide = false;
            Statistiques.nbreDeclarationsIncompletes++;
        } 
    }

    
    /**
   static void traiter(JSONObject contenu) {
     Integer sexe = contenu.getInt("sexe");
     if (!Projet1.sexeValide(sexe)) {
     Projet1.erreurSexe();
     return;
     }
     Statistiques.calculerSexe(sexe);
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

    static boolean validationCycle(JSONObject contenu) {
        String cycle = contenu.getString("cycle").trim();
        if (cycle.equals("2013-2016")) {
            return true;
        }
        return false;
    }

    static boolean validationDate(Date date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2013-06-01");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2016-06-01");
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

    static void traiter(JSONObject contenu) throws ParseException {
        Integer sexe = contenu.getInt("sexe");
        if (!Projet1.sexeValide(sexe)) {
            Projet1.erreurSexe();
            return;
        }
        Statistiques.calculerSexe(sexe);
        if (!(noPermisGeologuesValide(contenu))) {
            Projet1.erreurDePermis();
            return;
        }
        String cycle = contenu.getString("cycle").trim();
        if (!cycleValide(cycle)) {
            Projet1.erreurDeCycle();
            return;
        }

        ArrayList<String> activitesTraitees = new ArrayList<>();
        Integer nbreHeuresCours = 0;
        Integer nbreHeuresGroupeDeDiscussion = 0;
        Integer nbreHeuresProjetDeRecherche = 0;
        Integer heure = 0;
        Integer heureTotale = 0;

        JSONArray activitesFaites = contenu.getJSONArray("activites");
        for (int i = 0; i < activitesFaites.size(); i++) {
            JSONObject activiteSuivie = activitesFaites.getJSONObject(i);
            String categ = activiteSuivie.getString("categorie").trim();
            String description = activiteSuivie.getString("description");
            heure = activiteSuivie.getInt("heures");
            String descriptionActivite = activiteSuivie.getString("description").trim().toLowerCase();
            String dateActivite = activiteSuivie.getString("date").trim();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);
            if (!validationDate(date)) {
                Projet1.erreurs.add("L'activité " + descriptionActivite + " n'a pas ete complétée"
                        + " entre le 1er Juin 2013 et le 1er Juin 2016.");
            }
            if (!ValidationCategorie(categ)) {
                Projet1.erreurs.add("L'activité " + categ + "n'appartient a aucune categorie reconnue");
            }
            if (!Projet1.descriptionActiviteValide(description)) {
               Projet1.erreurDescriptionActivite();
               return;
            }
            if (ValidationCategorie(categ) && validationDate(date)) {
                heureTotale += heure;
                
                
                if (categ.equals("cours")) {
                    nbreHeuresCours += heure;
                }
                if (categ.equals("groupe de discussion")) {
                    nbreHeuresGroupeDeDiscussion += heure;
                }
                if (categ.equals("projet de recherche")) {
                    nbreHeuresProjetDeRecherche += heure;
                }
            }
        }
        if (nbreHeuresCours < 22) {
            Projet1.erreurs.add("Il y a moins de 22 heures de cours");
        }
        if (nbreHeuresProjetDeRecherche < 3) {
            Projet1.erreurs.add("Il y a moins de 3 heures de projet de recherche");
        }
        if (nbreHeuresGroupeDeDiscussion < 1) {
            Projet1.erreurs.add("Il y a moins d'une heure de groupe de discution");
        }
        if ((heureTotale) < 55) {
            Projet1.erreurs.add("Il manque " + (55 - heureTotale) + " heure(s) de formation pour completer le cycle.");
        }

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
