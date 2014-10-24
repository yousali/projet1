package projet1;

import java.util.ArrayList;

public class ValidationsCommunes {
    static boolean declarationValide = false;
    static ArrayList<String> erreurs = new ArrayList<>();
    
    static boolean permisValide(String permis){//A completer
       
        return true;
    }
    
   static boolean ordreSupporte(String ordre) {

        return (ordre.equals("architectes") || ordre.equals("géologues") || ordre.equals("psycologues"));
    }
   
   static boolean noPermisValide(String noPermis){
       
       return true;
   }
   static boolean descriptionActiviteValide(String description){//A completer
       
       return true;
   }
   
   static void erreurDescriptionActivite(){
       
       System.out.println("Erreur, une description de plus de 20 caracteres devrait etre donnee pour "
               + "chaque activite faite.");
       erreurs.add("Une description de plus de 20 caracteres devrait etre donnee pour chaque activite faite.");
       declarationValide = false;
   }
   
   static void erreurFormatDate(){
       
       System.out.println(" Erreur, la date doit etre specifiee en format ISO 8601.");
       erreurs.add("La date doit etre specifiee en format ISO 8601.");
       declarationValide = false;
       
   }
 
    
}
