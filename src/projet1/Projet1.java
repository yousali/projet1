package projet1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Projet1 {

    public static void main(String[] args) throws Exception {
        String fichierDeclaration = args[0];
        Boolean declarationValide = true;
        ArrayList<String> erreurs = new ArrayList<>();
        ArrayList<String> activitesTraitees = new ArrayList<>();
        Integer nbreHeuresCours = 0;
        Integer nbreHeuresPresentation = 0;
        Integer nbreHeuresGroupeDeDiscussion = 0;
        Integer nbreHeuresProjetDeRecherche = 0;
        Integer nbreHeuresRedaction = 0;
        String fichier = FileReader.loadFileIntoString(fichierDeclaration, null);
        JSONObject contenu = (JSONObject) JSONSerializer.toJSON(fichier);
        String cycle = contenu.getString("cycle").trim();
        if (!validerCycle(cycle)) {
            erreurs.add("Le cyle entré n'est pas supporte");
            declarationValide = false;
        } else {
            int heuresTransfereesCylePrecedent = contenu.getInt("heures_transferees_du_cycle_precedent");
            if (heuresTransfereesCylePrecedent < 0) {
                heuresTransfereesCylePrecedent = 0;
            } else if (heuresTransfereesCylePrecedent > 7) {
                heuresTransfereesCylePrecedent = 7;
                erreurs.add("Le nombre d'heures transferées du cycle precedent est ramené a 7.");
            }
            JSONArray activitesFaites = (JSONArray) contenu.getJSONArray("activites");
            for (int i = 0; i < activitesFaites.size(); i++) {
                JSONObject activiteSuivie = activitesFaites.getJSONObject(i);
                String descriptionActivite = activiteSuivie.getString("description").trim().toLowerCase();
                String dateActivite = activiteSuivie.getString("date").trim();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);
                if (!activiteSuivieDurantLeCycle(date)) {
                    erreurs.add("L'activité " + descriptionActivite + " n'a pas ete complétée"
                            + " entre le 1er Avril 2012 et le 1er Avril 2014.");
                } else {
                    String categorieActivite = activiteSuivie.getString("categorie").trim().toLowerCase();
                    String activite = categorieActivite + " " + descriptionActivite + " " + dateActivite;
                    if (activitesTraitees.indexOf(activite) == -1) {
                        activitesTraitees.add(activite);
                        int nbreHeuresActivite = activiteSuivie.getInt("heures");
                        if (nbreHeuresActivite > 0) {
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
                                    erreurs.add("La catégorie " + categorieActivite + " n'est  pas reconnue.");
                            }
                        }
                    }
                }
            }
            if ((nbreHeuresCours + heuresTransfereesCylePrecedent) < 17) {
                declarationValide = false;
                erreurs.add("Le minimum de 17 heures n'a pas été atteint dans les catégories cours,atelier,"
                        + "séminaire,colloque ,conférence et lecture dirigée.");
            }
            if (nbreHeuresPresentation > 23) {
                nbreHeuresPresentation = 23;
            }
            if (nbreHeuresGroupeDeDiscussion > 17) {
                nbreHeuresGroupeDeDiscussion = 17;
            }
            if (nbreHeuresProjetDeRecherche > 23) {
                nbreHeuresProjetDeRecherche = 23;
            }
            if (nbreHeuresRedaction > 17) {
                nbreHeuresRedaction = 17;
            }
            int nbreTotalHeures = heuresTransfereesCylePrecedent + nbreHeuresRedaction + nbreHeuresCours + nbreHeuresGroupeDeDiscussion + nbreHeuresPresentation + nbreHeuresProjetDeRecherche;
            if ((nbreTotalHeures) < 40) {
                declarationValide = false;
                erreurs.add("Il manque " + (40 - nbreTotalHeures) + " heure(s) de formation pour completer le cycle.");
            }
        }
        JSONObject contenuRetourne = new JSONObject();
        contenuRetourne.put("Complet", declarationValide);
        JSONArray listeDesErreurs = new JSONArray();
        erreurs.stream().forEach((erreur) -> {
            listeDesErreurs.add(erreur);
        });
        contenuRetourne.put("erreurs", listeDesErreurs);

        try (FileWriter fichierDeSortie = new FileWriter(args[1])) {
            contenuRetourne.write(fichierDeSortie);
        } catch (IOException e) {
            System.out.println("Erreur d'ecriture dans le fichier specifie en argument");
        }
    }

    private static boolean activiteSuivieDurantLeCycle(Date date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2012-04-01");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-01");
        return date.compareTo(date1) > 0 && date.compareTo(date2) < 0;
    }

    private static boolean validerCycle(String cycle) {
        return (cycle.equals("2012-2014"));
    }
}
