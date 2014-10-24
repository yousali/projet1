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
            
        String fichier = FileReader.loadFileIntoString(args[0], null);
        JSONObject contenu = (JSONObject) JSONSerializer.toJSON(fichier);
        
            String ordre = contenu.getString("ordre").trim().toLowerCase();
            switch (ordre) {

                case "architectes":
                    System.out.println("Architectes");
                    ValidationArchitectes.traiter(contenu);
                    break;
                case "geologues":
                    ValidationGeologues.traiter();
                    break;
                case "psychologues":

                    ValidationPsychologues.traiter();
                    break;
                default:
                    System.out.println("L'ordre n'est pas accepte");
                    ValidationsCommunes.erreurs.add("L'ordre n'est pas supporte.");
                    ValidationsCommunes.declarationValide = false;

            }
            
            ValidationsCommunes.ecrireFichierSortie(args[1]);
        

        
    }
}
