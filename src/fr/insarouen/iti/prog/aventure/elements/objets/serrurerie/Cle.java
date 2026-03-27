package fr.insarouen.iti.prog.aventure.elements.objets.serrurerie;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;

import java.io.Serializable;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class Cle extends Objet implements Serializable {

    private String nom;
    private Monde monde;

    Cle(String nom , Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
        this.nom = nom;
        this.monde = monde;
    } 

    public boolean estDeplacable(){
        return true;
    }

}