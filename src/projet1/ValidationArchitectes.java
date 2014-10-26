/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import static sun.util.calendar.CalendarUtils.mod;

public class ValidationArchitectes {

    //-------------------------
    static ArrayList<String> activitesTraitees = new ArrayList<>();
    static Integer nbreHeuresRequises;
    static Integer nbreHeuresCours = 0;
    static Integer nbreHeuresPresentation = 0;
    static Integer nbreHeuresGroupeDeDiscussion = 0;
    static Integer nbreHeuresProjetDeRecherche = 0;
    static Integer nbreHeuresRedaction = 0;
    static Integer nbreHeuresCyclePrecedent = 0;

    //---------------------------------
    static void traiter(JSONObject contenu) {

        String noPermis = contenu.getString("numero_de_permis");
        if (Projet1.noPermisValide(noPermis)) {
            String cycle = contenu.getString("cycle").trim();
            if (cycleValide(cycle)) {
                nbreHeuresRequises = heuresMinimalesFormation(cycle);
                nbreHeuresCyclePrecedent = transfertHeuresCyclePrecedent(contenu.getInt("heures_transferees_du_cycle_precedent"));
                JSONArray activitesFaites = (JSONArray) contenu.getJSONArray("activites");
                for (int i = 0; i < activitesFaites.size(); i++) {
                    JSONObject activiteSuivie = activitesFaites.getJSONObject(i);
                    String descriptionActivite = activiteSuivie.getString("description").trim().toLowerCase();
                    if (Projet1.descriptionActiviteValide(descriptionActivite)) {
                        String dateActivite = activiteSuivie.getString("date").trim();
                        if (Projet1.dateValide(dateActivite)) {
                            try {
                                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);
                                if (!activiteSuivieDurantLeCycle(date, cycle)) {
                                    Projet1.erreurs.add("L'activité " + descriptionActivite
                                            + erreurCompletionActivite(cycle));
                                } else {
                                    String categorieActivite = activiteSuivie.getString("categorie").trim().toLowerCase();
                                    String activite = categorieActivite + " " + descriptionActivite + " " + dateActivite;
                                    if (activitesTraitees.indexOf(activite) == -1) {
                                        activitesTraitees.add(activite);
                                        int nbreHeuresActivite = activiteSuivie.getInt("heures");
                                        if (nbreHeuresActivite > 0) {
                                            ajoutActivite(categorieActivite, nbreHeuresActivite);
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
                        } else {

                            Projet1.erreurFormatDate();
                            return;
                        }

                    } else {

                        Projet1.erreurDescriptionActivite();

                    }
                }

                calculDesHeures();
                //***A effacer lesdeux lignes qui suivent 
                System.out.println(Projet1.declarationValide);
                System.out.println(Projet1.erreurs);

            } else {
                erreurDeCycle();
            }
        } else {
            erreurDePermis();
        }

    }

    static void ajoutActivite(String categorieActivite, Integer nbreHeuresActivite) {
        switch (categorieActivite) {
            case "cours":
            case "atelier":
            case "séminaire":
            case "colloque":
            case "conférence":
            case "lecture dirigée":
                nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;
                break;
            case "présentation":
                nbreHeuresPresentation = nbreHeuresPresentation + nbreHeuresActivite;
                break;
            case "groupe de discussion":
                nbreHeuresGroupeDeDiscussion = nbreHeuresGroupeDeDiscussion + nbreHeuresActivite;
                break;
            case "projet de recherche":
                nbreHeuresProjetDeRecherche = nbreHeuresProjetDeRecherche + nbreHeuresActivite;
                break;
            case "rédaction professionnelle":
                nbreHeuresRedaction = nbreHeuresRedaction + nbreHeuresActivite;
                break;
            default:
                Projet1.erreurs.add("La catégorie " + categorieActivite + " n'est pas reconnue.");
        }

    }

    static String erreurCompletionActivite(String cycle) {
        if (cycle.equals("2008-2010")) {
            return "n'a pas ete complete entre le 1er Avril 2008 et le 1er Juillet 2010.";
        } else if (cycle.equals("2010-2012")) {
            return " n'a pas ete completee entre le 1er Avril 2010 et le 1er Avril 2012.";
        }
        return "n'a pas ete complete entre le 1er Avril 2012 et le 1er Avril 2014.";
    }

    static int transfertHeuresCyclePrecedent(Integer nbreHeures) {
        Integer nbreHeuresATransferer = nbreHeures;
        if (nbreHeures < 0) {
            nbreHeuresATransferer = 0;
        } else if (nbreHeures > 7) {
            nbreHeuresATransferer = 7;
            Projet1.erreurs.add("Le nombre d'heures transferées du cycle precedent est ramené a 7");
        }

        return nbreHeuresATransferer;
    }

    static void erreurDePermis() {
        System.out.println("Erreur, le numero de permis est invalide");
        Projet1.declarationValide = false;
        Projet1.erreurs.add("Le fichier d'entree est invalide.");
    }

    static Integer heuresMinimalesFormation(String cycle) {
        if (cycle.equals("2012-2014")) {
            return 40;
        }
        return 42;
    }

    static boolean activiteSuivieDurantLeCycle(Date date, String cycle) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2008-04-01");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2010-04-01");
        Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2010-07-01");
        Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse("2012-04-01");
        Date date5 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-01");

        switch (cycle) {

            case "2008-2010":
                return date.compareTo(date1) >= 0 && date.compareTo(date3) <= 0;
            case "2010-2012":
                return date.compareTo(date2) >= 0 && date.compareTo(date4) <= 0;
            case "2012-2014":
                return date.compareTo(date4) >= 0 && date.compareTo(date5) <= 0;
            default:
                return false;
        }

    }

    static void erreurDeCycle() {
        System.out.println("Erreur du cycle");
        Projet1.erreurs.add("Le cycle entre n'est pas supporte.");
        Projet1.declarationValide = false;
    }

    static boolean cycleValide(String cycle) {

        return (cycle.equals("2010-2012") || cycle.equals("2008-2010") || cycle.equals("2012-2014"));
    }

    static void calculDesHeures() {
        if ((nbreHeuresCours + nbreHeuresCyclePrecedent) < 17) {
            Projet1.declarationValide = false;
            Projet1.erreurs.add("Le minimum de 17 heures n'a pas été atteint dans "
                    + "les catégories cours,atelier,séminaire,colloque ,conférence et lecture dirigée.");
        }
        if (nbreHeuresPresentation > 23) {
            nbreHeuresPresentation = 23;
        }
        if (nbreHeuresGroupeDeDiscussion > 17) {
            nbreHeuresGroupeDeDiscussion = 17;
        }
        if (nbreHeuresProjetDeRecherche > 23) {
            nbreHeuresProjetDeRecherche
                    = 23;
        }
        if (nbreHeuresRedaction > 17) {
            nbreHeuresRedaction = 17;
        }
        int nbreTotalHeures = nbreHeuresCyclePrecedent + nbreHeuresRedaction
                + nbreHeuresCours + nbreHeuresGroupeDeDiscussion + nbreHeuresPresentation
                + nbreHeuresProjetDeRecherche;
        if ((nbreTotalHeures) < nbreHeuresRequises) {
            Projet1.declarationValide = false;
            Projet1.erreurs.add("Il manque " + (nbreHeuresRequises - nbreTotalHeures)
                    + " heure(s) de formation pour completer le cycle.");
        }

    }
}
