
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
        String fichierDeclaration =   args[0];
        ArrayList<String> erreurs = new ArrayList<String>();
        String categoriesReconnues[] = new String[]{"cours", "atelier", "séminaire", "colloque", "conférence", "lecture dirigée", "présentation", "groupe de discussion", "projet de recherche", "rédaction professionnelle"};

        String fichier = FileReader.loadFileIntoString(fichierDeclaration, null);
        JSONObject contenu = (JSONObject) JSONSerializer.toJSON(fichier);
        String numeroDePermis = contenu.getString("numero_de_permis");
        String cycle = contenu.getString("cycle");
        int heuresTransfereesCylePrecedent = contenu.getInt("heures_transferees_du_cycle_precedent");
        JSONArray activitesFaites = (JSONArray) contenu.getJSONArray("activites");

        //-------------------------
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2012-04-01");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-04-01");
        for (int i = 0; i < activitesFaites.size(); i++) {

            JSONObject activiteSuivie = activitesFaites.getJSONObject(i);
            String descriptionActivite = activiteSuivie.getString("description");
            String categorieActivite = activiteSuivie.getString("categorie");
            int nombreHeuresActivite = activiteSuivie.getInt("heures");
            String dateActivite = activiteSuivie.getString("date");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateActivite);
            System.out.println(descriptionActivite);
            System.out.println(categorieActivite);
            System.out.println(nombreHeuresActivite);
            System.out.println("******");
            System.out.println(date);
            System.out.println("******");
            System.out.println("------------******");
            if (date.compareTo(date1) > 0 && date.compareTo(date2) < 0) {

                System.out.println("GOOOOOOOD");
            } else {

                System.out.println("BAAAAAAAAD");
            }
            System.out.println("------------******");

        }

        System.out.println(numeroDePermis);
        System.out.println(cycle);
        System.out.println(heuresTransfereesCylePrecedent);

    }

}
