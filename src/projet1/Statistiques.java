/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1;

/**
 *
 * @author Moes
 */
public class Statistiques {

    private static Integer nbreDeclarationsTraitees;
    private static Integer nbreDeclarationsCompletes;
    private static Integer nbreDeclarationsIncompletes;
    private static Integer nbreDeclarationsHommes;
    private static Integer nbreDeclarationsFemmes;
    private static Integer nbreDeclarationsGensInconnu;
    private static Integer nbreActivitesDansCours;
    private static Integer nbreActivitesDansAtelier;
    private static Integer nbreActivitesDansSeminaire;
    private static Integer nbreActivitesDansColloque;
    private static Integer nbreActivitesDansConference;
    private static Integer nbreActivitesDansLectureDirigee;
    private static Integer nbreActivitesDansPresentation;
    private static Integer nbreActivitesDansGDiscussion;
    private static Integer nbreActivitesDansPRecherche;
    private static Integer nbreActivitesDansRProfessionnelle;

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
    
    public void enregistrerStats(){
        
        
    }

}
