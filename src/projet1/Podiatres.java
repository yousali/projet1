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
import net.sf.json.JSONObject;

/**
 *
 * @author Moes
 */
public class Podiatres {
    static ArrayList<String> activitesTraitees = new ArrayList<>();
    static Integer nbreHeuresRequises;
    static Integer nbreHeuresCours = 0;
    static Integer nbreHeuresPresentation = 0;
    static Integer nbreHeuresGroupeDeDiscussion = 0;
    static Integer nbreHeuresProjetDeRecherche = 0;
    static Integer nbreHeuresRedaction = 0;
    
    static void traiter(JSONObject contenu){
        Integer sexe = contenu.getInt("sexe");
        if(!Projet1.sexeValide(sexe)){
            Projet1.erreurSexe();
            return;   
        }
        String noPermis = contenu.getString("numero_de_permis").trim();
        if (!(noPermisPodiatresValide(noPermis))) {    
            Projet1.erreurDePermis();
            return;
        }
        String cycle = contenu.getString("cycle").trim();
        if (!cycleValide(cycle)) {
           
            Projet1.erreurDeCycle();
            return;
        }
        
        
    };
    
    static boolean noPermisPodiatresValide(String noPermis) {
        return noPermis.matches("[0-9]{5}");
    }
    static boolean validationDate(Date date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2013-06-01");
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2016-06-01");

        if (date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0) {
            return true;
        }
        return false;
    }
    static boolean cycleValide(String cycle) {
        
        return cycle.equals("2013-2016");
    }
    
}
