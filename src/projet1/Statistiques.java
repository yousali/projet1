package projet1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class Statistiques {

    private static Integer nbreDeclarationsTraitees = 0;
    private static Integer nbreDeclarationsCompletes = 0;
    private static Integer nbreDeclarationsIncompletes = 0;
    private static Integer nbreDeclarationsHommes = 0;
    private static Integer nbreDeclarationsFemmes = 0;
    private static Integer nbreDeclarationsGensInconnu = 0;
    private static Integer nbreActivitesDansCours = 0;
    private static Integer nbreActivitesDansAtelier = 0;
    private static Integer nbreActivitesDansSeminaire = 0;
    private static Integer nbreActivitesDansColloque = 0;
    private static Integer nbreActivitesDansConference = 0;
    private static Integer nbreActivitesDansLectureDirigee = 0;
    private static Integer nbreActivitesDansPresentation = 0;
    private static Integer nbreActivitesDansGDiscussion = 0;
    private static Integer nbreActivitesDansPRecherche = 0;
    private static Integer nbreActivitesDansRProfessionnelle = 0;

   /**
    static ArrayList<String> activitesCours = new ArrayList<>();
    static ArrayList<String> activitesAtelier = new ArrayList<>();
    static ArrayList<String> activitesSeminaire = new ArrayList<>();
    static ArrayList<String> activitesColloque = new ArrayList<>();
    static ArrayList<String> activitesConference = new ArrayList<>();
    static ArrayList<String> activitesLectureDirigee = new ArrayList<>();
    static ArrayList<String> activitesPresentation = new ArrayList<>();
    static ArrayList<String> activitesGdiscussion = new ArrayList<>();
    static ArrayList<String> activitesPRecherche = new ArrayList<>();
    static ArrayList<String> activitesRProfessionnelle = new ArrayList<>(); */

    public static void calculer() throws IOException {
        chargerStats();
        System.out.println("Nombre total de déclarations traitées: " + nbreDeclarationsTraitees);
        System.out.println("Nombre total de déclarations complètes: " + nbreDeclarationsCompletes);
        System.out.println("Nombre total de déclarations incomplètes ou invalides: " + nbreDeclarationsIncompletes);
        System.out.println("Nombre total de déclarations faites par des hommes" + nbreDeclarationsHommes);
        System.out.println("Nombre total de déclarations faites par des femmes:" + nbreDeclarationsFemmes);
        System.out.println("Nombre total de déclarations faites par des gens de sexe inconnu:" + nbreDeclarationsGensInconnu);
        System.out.println("Nombre total d'activités valides dans les déclarations: ");
        System.out.println("Nombre d'activités valides dans la catégorie COURS :" + nbreActivitesDansCours);
        System.out.println("Nombre d'activités valides dans la catégorie ATELIER: " + nbreActivitesDansAtelier);
        System.out.println("Nombre d'activités valides dans la catégorie SEMINAIRE: " + nbreActivitesDansSeminaire);
        System.out.println("Nombre d'activités valides dans la catégorie COLLOQUE: " + nbreActivitesDansColloque);
        System.out.println("Nombre d'activités valides dans la catégorie CONFERENCE: " + nbreActivitesDansConference);
        System.out.println("Nombre d'activités valides dans la catégorie LECTURE DIRIGEE: " + nbreActivitesDansLectureDirigee);
        System.out.println("Nombre d'activités valides dans la catégorie PRESENTATION: " + nbreActivitesDansPresentation);
        System.out.println("Nombre d'activités valides dans la catégorie GROUPE DE DISCUSSION: " + nbreActivitesDansGDiscussion);
        System.out.println("Nombre d'activités valides dans la catégorie PROJET DE RECHERCHE: " + nbreActivitesDansPRecherche);
        System.out.println("Nombre d'activités valides dans la catégorie REDACTION PROFESSIONNELLE: " + nbreActivitesDansRProfessionnelle);

    }

    public static void chargerStats() throws IOException {
        String fichierStats = FileReader.loadFileIntoString("fichiers/stats.json", null);
        JSONObject contenu = (JSONObject) JSONSerializer.toJSON(fichierStats);
        nbreDeclarationsTraitees = contenu.getInt("nbreDeclarationsTraitees");
        nbreDeclarationsCompletes = contenu.getInt("nbreDeclarationsCompletes");
        nbreDeclarationsIncompletes = contenu.getInt("nbreDeclarationsIncompletes");
        nbreDeclarationsHommes = contenu.getInt("nbreDeclarationsHommes");
        nbreDeclarationsFemmes = contenu.getInt("nbreDeclarationsFemmes");
        nbreDeclarationsGensInconnu = contenu.getInt("nbreDeclarationsGensInconnu");
        
        nbreActivitesDansCours = contenu.getInt("nbreActivitesDansCours");
        nbreActivitesDansAtelier = contenu.getInt("nbreActivitesDansAtelier");
        nbreActivitesDansSeminaire = contenu.getInt("nbreActivitesDansSeminaire");
        nbreActivitesDansColloque = contenu.getInt("nbreActivitesDansColloque");
        nbreActivitesDansConference = contenu.getInt("nbreActivitesDansConference");
        nbreActivitesDansLectureDirigee = contenu.getInt("nbreActivitesDansLectureDirigee");
        nbreActivitesDansPresentation = contenu.getInt("nbreActivitesDansPresentation");
        nbreActivitesDansGDiscussion = contenu.getInt("nbreActivitesDansGDiscussion");
        nbreActivitesDansPRecherche = contenu.getInt("nbreActivitesDansPRecherche");
        nbreActivitesDansRProfessionnelle = contenu.getInt("nbreActivitesDansRProfessionnelle");

    }

    public static void setDeclarationsTraitees() {
        nbreDeclarationsTraitees = nbreDeclarationsTraitees + 1;
    }

    public static void setDeclarationsCompletes() {
        nbreDeclarationsCompletes = nbreDeclarationsCompletes + 1;
    }

    public static void setDeclarationsIncompletes() {
        nbreDeclarationsIncompletes++;
    }

    public static void setDeclarationsHommes() {
        nbreDeclarationsHommes++;
    }

    public static void setDeclarationsFemmes() {
        nbreDeclarationsFemmes++;
    }

    public static void setDeclarationGInconnu() {
        nbreActivitesDansCours++;
    }

    public static void setActivitesDansCours() {
        nbreActivitesDansCours++;
    }

    public static void setActivitesDansAtelier() {
        nbreActivitesDansAtelier++;
    }

    public static void setActivitesDansSeminaire() {
        nbreActivitesDansSeminaire++;
    }

    public static void setActivitesDansColloque() {
        nbreActivitesDansColloque++;
    }

    public static void setActivitesDansConference() {
        nbreActivitesDansConference++;
    }

    public static void setActivitesDansLectureDirigee() {
        nbreActivitesDansLectureDirigee++;
    }

    public static void setActivitesDansPresentation() {
        nbreActivitesDansPresentation++;
    }

    public static void setActivitesDansGDiscussion() {
        nbreActivitesDansGDiscussion++;
    }

    public static void setActivitesDansPRecherche() {
        nbreActivitesDansPRecherche++;
    }

    public static void setActivitesDansRProfessionnelle() {
        nbreActivitesDansRProfessionnelle++;
    }

    public static int getDeclarationsTraitees() {
        return nbreDeclarationsTraitees;
    }

    public static int getDeclarationsCompletes() {
        return nbreDeclarationsCompletes;
    }

    public static int getDeclarationsIncompletes() {
        return nbreDeclarationsIncompletes;
    }

    public static int getDeclarationsHommes() {
        return nbreDeclarationsHommes;
    }

    public static int getDeclarationsFemmes() {
        return nbreDeclarationsFemmes;
    }

    public static int getDeclarationGInconnu() {
        return nbreActivitesDansCours;
    }

    public static int getActivitesDansCours() {
        return nbreActivitesDansCours;
    }

    public static int getActivitesDansAtelier() {
        return nbreActivitesDansAtelier;
    }

    public static int getActivitesDansSeminaire() {
        return nbreActivitesDansSeminaire;
    }

    public static int getActivitesDansColloque() {
        return nbreActivitesDansColloque;
    }

    public static int getActivitesDansConference() {
        return nbreActivitesDansConference;
    }

    public static int getActivitesDansLectureDirigee() {
        return nbreActivitesDansLectureDirigee;
    }

    public static int getActivitesDansPresentation() {
        return nbreActivitesDansPresentation;
    }

    public static int getActivitesDansGDiscussion() {
        return nbreActivitesDansGDiscussion;
    }

    public static int getActivitesDansPRecherche() {
        return nbreActivitesDansPRecherche;
    }

    public static int getActivitesDansRProfessionnelle() {
        return nbreActivitesDansRProfessionnelle;
    }

    public static void reinitialiser() throws IOException {
        nbreDeclarationsTraitees = 0;
        nbreDeclarationsCompletes = 0;
        nbreDeclarationsIncompletes = 0;
        nbreDeclarationsHommes = 0;
        nbreDeclarationsFemmes = 0;
        nbreDeclarationsGensInconnu = 0;
        nbreActivitesDansCours = 0;
        nbreActivitesDansAtelier = 0;
        nbreActivitesDansSeminaire = 0;
        nbreActivitesDansColloque = 0;
        nbreActivitesDansConference = 0;
        nbreActivitesDansLectureDirigee = 0;
        nbreActivitesDansPresentation = 0;
        nbreActivitesDansGDiscussion = 0;
        nbreActivitesDansPRecherche = 0;
        nbreActivitesDansRProfessionnelle = 0;
        enregistrerStats();
        System.out.println(" Les statistiqeus ont ete reinitialisees.");

    }

    public static void enregistrerStats() throws IOException {
        JSONObject statsAEnregistrer = new JSONObject();
        statsAEnregistrer.put("nbreDeclarationsTraitees", nbreDeclarationsTraitees);
        statsAEnregistrer.put("nbreDeclarationsCompletes", nbreDeclarationsCompletes);
        statsAEnregistrer.put("nbreDeclarationsIncompletes", nbreDeclarationsIncompletes);
        statsAEnregistrer.put("nbreDeclarationsHommes", nbreDeclarationsHommes);
        statsAEnregistrer.put("nbreDeclarationsHommes", nbreDeclarationsHommes);
        statsAEnregistrer.put("nbreDeclarationsFemmes", nbreDeclarationsFemmes);
        statsAEnregistrer.put("nbreDeclarationsGensInconnu", nbreDeclarationsGensInconnu);
        statsAEnregistrer.put("nbreActivitesDansCours", nbreActivitesDansCours);
        statsAEnregistrer.put("nbreActivitesDansAtelier", nbreActivitesDansAtelier);
        statsAEnregistrer.put("nbreActivitesDansSeminaire", nbreActivitesDansSeminaire);
        statsAEnregistrer.put("nbreActivitesDansColloque", nbreActivitesDansColloque);
        statsAEnregistrer.put("nbreActivitesDansConference", nbreActivitesDansConference);
        statsAEnregistrer.put("nbreActivitesDansLectureDirigee", nbreActivitesDansLectureDirigee);
        statsAEnregistrer.put("nbreActivitesDansPresentation", nbreActivitesDansPresentation);
        statsAEnregistrer.put("nbreActivitesDansGDiscussion", nbreActivitesDansGDiscussion);
        statsAEnregistrer.put("nbreActivitesDansPRecherche", nbreActivitesDansPRecherche);
        statsAEnregistrer.put("nbreActivitesDansRProfessionnelle", nbreActivitesDansRProfessionnelle);
        try (FileWriter fichierDeSortie = new FileWriter("fichiers/stats.jsp")) {
            statsAEnregistrer.write(fichierDeSortie);
        } catch (IOException e) {
            System.out.println("Erreur d'ecriture dans le fichier des statistiques");
        }
    }

    /* PrintWriter stats;
     stats = new PrintWriter(new BufferedWriter(new FileWriter("fichiers/stats")));
     stats.println("Nombre total de déclarations traitées: " + nbreDeclarationsTraitees);
     stats.println("Nombre total de déclarations complètes: " + nbreDeclarationsCompletes);
     stats.println("Nombre total de déclarations incomplètes ou invalides: " + nbreDeclarationsIncompletes);
     stats.println("Nombre total de déclarations faites par des hommes" + nbreDeclarationsHommes);
     stats.println(" Nombre total de déclarations faites par des femmes:" + nbreDeclarationsFemmes);
     stats.println(" Nombre total de déclarations faites par des gens de sexe inconnu:" + nbreDeclarationsGensInconnu);
     stats.println("Nombre total d'activités valides dans les déclarations: ");
     stats.println("Nombre d'activités valides dans la catégorie COURS :" + nbreActivitesDansCours);
     stats.println("Nombre d'activités valides dans la catégorie ATELIER: " + nbreActivitesDansAtelier);
     stats.println("Nombre d'activités valides dans la catégorie SEMINAIRE: " + nbreActivitesDansSeminaire);
     stats.println("Nombre d'activités valides dans la catégorie COLLOQUE: " + nbreActivitesDansColloque);
     stats.println("Nombre d'activités valides dans la catégorie CONFERENCE: " + nbreActivitesDansConference);
     stats.println("Nombre d'activités valides dans la catégorie LECTURE DIRIGEE: " + nbreActivitesDansLectureDirigee);
     stats.println("Nombre d'activités valides dans la catégorie PRESENTATION: " + nbreActivitesDansPresentation);
     stats.println("Nombre d'activités valides dans la catégorie GROUPE DE DISCUSSION: " + nbreActivitesDansGDiscussion);
     stats.println("Nombre d'activités valides dans la catégorie PROJET DE RECHERCHE: " + nbreActivitesDansPRecherche);
     stats.println("Nombre d'activités valides dans la catégorie REDACTION PROFESSIONNELLE: " + nbreActivitesDansRProfessionnelle);
     stats.close(); */
}
