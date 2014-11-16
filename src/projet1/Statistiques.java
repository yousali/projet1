/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Moes
 */
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

    public void setDeclarationsTraitees() {
        nbreDeclarationsTraitees++;
    }

    public void setDeclarationsCompletes() {
        nbreDeclarationsCompletes++;
    }

    public void setDeclarationsIncompletes() {
        nbreDeclarationsIncompletes++;
    }

    public void setDeclarationsHommes() {
        nbreDeclarationsHommes++;
    }

    public void setDeclarationsFemmes() {
        nbreDeclarationsFemmes++;
    }

    public void setDeclarationGInconnu() {
        nbreActivitesDansCours++;
    }

    public void setActivitesDansCours() {
        nbreActivitesDansCours++;
    }

    public void setActivitesDansAtelier() {
        nbreActivitesDansAtelier++;
    }

    public void setActivitesDansSeminaire() {
        nbreActivitesDansSeminaire++;
    }

    public void setActivitesDansColloque() {
        nbreActivitesDansColloque++;
    }

    public void setActivitesDansConference() {
        nbreActivitesDansConference++;
    }

    public void setActivitesDansLectureDirigee() {
        nbreActivitesDansLectureDirigee++;
    }

    public void setActivitesDansPresentation() {
        nbreActivitesDansPresentation++;
    }

    public void setActivitesDansGDiscussion() {
        nbreActivitesDansGDiscussion++;
    }

    public void setActivitesDansPRecherche() {
        nbreActivitesDansPRecherche++;
    }

    public void setActivitesDansRProfessionnelle() {
        nbreActivitesDansRProfessionnelle++;
    }
    //

    public int getDeclarationsTraitees() {
        return nbreDeclarationsTraitees;
    }

    public int getDeclarationsCompletes() {
        return nbreDeclarationsCompletes;
    }

    public int getDeclarationsIncompletes() {
        return nbreDeclarationsIncompletes;
    }

    public int getDeclarationsHommes() {
        return nbreDeclarationsHommes;
    }

    public int getDeclarationsFemmes() {
        return nbreDeclarationsFemmes;
    }

    public int getDeclarationGInconnu() {
        return nbreActivitesDansCours;
    }

    public int getActivitesDansCours() {
        return nbreActivitesDansCours;
    }

    public int getActivitesDansAtelier() {
        return nbreActivitesDansAtelier;
    }

    public int getActivitesDansSeminaire() {
        return nbreActivitesDansSeminaire;
    }

    public int getActivitesDansColloque() {
        return nbreActivitesDansColloque;
    }

    public int getActivitesDansConference() {
        return nbreActivitesDansConference;
    }

    public int getActivitesDansLectureDirigee() {
        return nbreActivitesDansLectureDirigee;
    }

    public int getActivitesDansPresentation() {
        return nbreActivitesDansPresentation;
    }

    public int getActivitesDansGDiscussion() {
        return nbreActivitesDansGDiscussion;
    }

    public int getActivitesDansPRecherche() {
        return nbreActivitesDansPRecherche;
    }

    public int getActivitesDansRProfessionnelle() {
        return nbreActivitesDansRProfessionnelle;
    }

    public void reinitialier() {
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

    }

    public static void enregistrerStats() throws IOException {
        PrintWriter stats;
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
        stats.close();
    }
}
