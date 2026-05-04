package fr.insarouen.iti.prog.aventure;

import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.conditions.ConjonctionDeFin;
import fr.insarouen.iti.prog.aventure.conditions.EtatDuJeu;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Simulateur {
    private Monde monde = null; 
    private Collection<ConditionDeFin> conditionsDeFin = new ArrayList<>();
    private EtatDuJeu etatDuJeu = null; 
    public Simulateur(Monde monde,Collection<ConditionDeFin> conditionsDeFin){
        this.monde = monde; 
        this.conditionsDeFin = conditionsDeFin;  
        ConditionDeFin conj = new ConjonctionDeFin(this.etatDuJeu, conditionsDeFin.toArray(ConditionDeFin[]::new));
        this.etatDuJeu = conj.verifierCondition(); 
    }

    private <T> Collection<T> getEntites(Class<T> classe){
        return null; 
    }
    private List<Vivant> getExecutables(Collection<Entite> entites){
        return entites.stream()
                      .filter(e -> (e instanceof Vivant) && !(e instanceof JoueurHumain))
                      .map(e -> (Vivant) e)
                      .toList(); 
    }
    private List<JoueurHumain> getJoueurHumains(Collection<Entite> entites){
        return entites.stream()
                      .filter(j -> j instanceof JoueurHumain)
                      .map(j -> (JoueurHumain) j)
                      .toList(); 
    }

    private void afficherSituationJoueur(Collection<Entite> entites) {
        for (Entite entite : entites) {
            if (entite instanceof JoueurHumain) {
                JoueurHumain joueur = (JoueurHumain) entite;
                System.out.println("\n Joueur : ");
                System.out.println(joueur); // toString hérité de Vivant

                System.out.println("\n Pièce du joueur : ");
                Piece piece = joueur.getPiece();
                System.out.println(piece); // toString de la pièce

                System.out.println("\n Portes de la pièce : ");
                for (Porte p : piece.getPortes()) {
                    System.out.println(p); // toString de chaque porte
                }
            }
        }
    }


    public EtatDuJeu executerUnTour(){
        // Vérifier les conditions 
        // Vérifier ENCOURS 
        if (!this.etatDuJeu.equals(EtatDuJeu.ENCOURS)){
            return this.etatDuJeu; 
        } 
        //Afficher à l'aide des toString 
        //Objets & Points de vie (JoueurHumain)
        Collection<Entite> entites = this.monde.getEntites(); 
        afficherSituationJoueur(entites);

        List<JoueurHumain> joueurs = getJoueurHumains(entites); 
        joueurs.stream()
               .forEach(j -> {
                try {
                    j.executer();
                } catch (ITIAventureException e) {
                    e.printStackTrace();
                }
            });
        //Execution de tous les autres executanle
        List<Vivant> vivants = getExecutables(entites); 
        vivants.stream()
               .forEach(v -> {
                try {
                    v.executer();
                } catch (ITIAventureException e) {
                    e.printStackTrace();
                }
            });
        ConditionDeFin conj = new ConjonctionDeFin(this.etatDuJeu, conditionsDeFin.toArray(ConditionDeFin[]::new));
        EtatDuJeu nouvelEtat = conj.verifierCondition(); 
   
        return nouvelEtat; 
    }

    public void executerJusquALaFin(){
        EtatDuJeu etat = this.etatDuJeu;
        while (etat == EtatDuJeu.ENCOURS) {
            etat = this.executerUnTour();
        }
    }
}