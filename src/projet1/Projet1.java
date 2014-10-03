package projet1;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Projet1 {

    public static void main(String[] args) throws Exception {
        String fichierDeclaration = args[0];
        //A RAJOUTER LE FICHIER DE SORTIE ICI
        Boolean declarationValide = true;
        ArrayList<String> erreurs = new ArrayList<String>();
        ArrayList<String> activitesTraitees = new ArrayList<String>();
         Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2012-04-01");
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-01");
        Integer nbreHeuresCours = 0;
        Integer nbreHeuresPresentation = 0;
        Integer  nbreHeuresGroupeDeDiscussion = 0;
        Integer  nbreHeuresProjetDeRecherche = 0;
        Integer nbreHeuresRedaction = 0;
        
        

        String fichier = FileReader.loadFileIntoString(fichierDeclaration, null);
        JSONObject contenu = (JSONObject) JSONSerializer.toJSON(fichier);
        String numeroDePermis = contenu.getString("numero_de_permis");
        String cycle = contenu.getString("cycle");
        if (!cycle.equals("2012-2014")) {

            erreurs.add("Le cyle entré n'est pas supporte");
            declarationValide = false;

            System.out.println(erreurs);
            System.out.println("#################");
            System.out.println("Déclaration non valide");
            System.out.println("#################");

        } else {
            int heuresTransfereesCylePrecedent = contenu.getInt("heures_transferees_du_cycle_precedent");
            
            JSONArray activitesFaites = (JSONArray) contenu.getJSONArray("activites");
           

            for (int i = 0; i < activitesFaites.size(); i++) {

                JSONObject activiteSuivie = activitesFaites.getJSONObject(i);
                String descriptionActivite = activiteSuivie.getString("description").trim().toLowerCase();
                String dateActivite = activiteSuivie.getString("date").trim();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);
                if (date.compareTo(date1) < 0 || date.compareTo(date2) > 0) {

                    erreurs.add("L'activité \"" + descriptionActivite + "\" n'a pas ete complétée"
                            + " entre entre le 1er Avril 2012 et le 1er Avril 2014.");

                } else {

                    

                    String categorieActivite = activiteSuivie.getString("categorie").trim().toLowerCase();
                    
                    System.out.println(categorieActivite + "" + descriptionActivite + dateActivite);
                    int nbreHeuresActivite = activiteSuivie.getInt("heures");

                    switch (categorieActivite) {
                        case "cours":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;}
                            
                            
                            break;
                        case "atelier":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;}
                            
                            break;
                        case "séminaire":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;}
                            
                            break;
                        case "colloque":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;}
                            
                            break;
                        case "conférence":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;}
                            
                            break;
                        case "lecture dirigée":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresCours = nbreHeuresCours + nbreHeuresActivite;}
                            
                            break;
                        case "présentation":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresPresentation = nbreHeuresPresentation + nbreHeuresActivite;}
                            
                            break;
                        case "groupe de discussion":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresGroupeDeDiscussion = nbreHeuresGroupeDeDiscussion + nbreHeuresActivite;}
                            
                            break;
                        case "projet de recherche":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresProjetDeRecherche = nbreHeuresProjetDeRecherche + nbreHeuresActivite;}
                            
                            break;
                        case "rédaction professionnelle":
                            activitesTraitees.add("" + categorieActivite + descriptionActivite + dateActivite);
                            if(nbreHeuresActivite > 0){
                            nbreHeuresRedaction = nbreHeuresRedaction + nbreHeuresActivite;}
                            
                            break;
                        default:
                            erreurs.add("La catégorie \""+ categorieActivite + "\" n'est reconnue.");
                           // System.out.println(categorieActivite+ " n'est pas une activite reconnue.");

                    }

                }

                
               

            }
            
            System.out.print("Nombre heures cours:  ");
            System.out.println(nbreHeuresCours);
                 System.out.print("Nombre heures presentation: ");
                 System.out.println(nbreHeuresPresentation);
                System.out.print("Nombre heures groupe de discussion:  ");
                System.out.println(nbreHeuresGroupeDeDiscussion);
                System.out.print("Nombre heures projet derecherche:   ");
                System.out.println(nbreHeuresProjetDeRecherche);
                System.out.print("Nombre heure redaction:   ");
                System.out.println(nbreHeuresRedaction);
                

        }
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            System.out.println(erreurs);
            System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
            System.out.println(activitesTraitees);
    }
}
