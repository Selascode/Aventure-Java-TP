package fr.insarouen.iti.prog.aventure.elements.vivants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;

public class Monstre extends Vivant {
    
    public Monstre(String nom, Monde monde, int pointVie, int pointForce, Piece piece) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointVie, pointForce, piece); 
    }

    public void executer() throws ITIAventureException {
        // S'il est vivant
        if (!this.estMort()) {
            // perd 1 point de vie
            this.setPointVie(this.getPointVie() - 1);
            
            if (!this.estMort()) {
                Collection<Porte> portes = this.getPiece().getPortes(); 

                if (!portes.isEmpty()) {
                    // cherche une porte ouverte ou fermée (OUVERT/FERME)
                   Porte porte = portes.stream()
                                        .filter(p -> p.getEtat() == Etat.FERME || p.getEtat() == Etat.OUVERT)
                                        .findFirst()
                                        .get();

                    // franchit la porte en l'ouvrant si nécessaire
                    if (porte.getEtat() == Etat.FERME) {
                        porte.activer(); 
                    }
                    
                    this.franchir(porte);
                        
                    List<Objet> objetsMonstre= new ArrayList<>(this.getObjets()); 
                    List<Objet> objetsPiece = new ArrayList<>((this.getPiece()).getObjets()); 
                    // Il dépose SES anciens objets dans la NOUVELLE pièce
                    for (Objet nomObj : objetsMonstre) {
                        this.deposer(nomObj);
                    }
                    
                    // Il ramasse les objets initiaux de la NOUVELLE pièce
                    
                    for (Objet objAPrendre : objetsPiece) {
                        try {
                            this.prendre(objAPrendre);
                        } catch (ObjetNonDeplacableException e) {
                        }
                    }
                }
            }
        }
    }
}


