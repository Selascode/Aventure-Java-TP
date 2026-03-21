package fr.insarouen.iti.prog.aventure.elements.objets;

import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
/**
 * Classe représentant un pied-de-biche.
 * <p>
 * Cet objet est un outil spécifique du jeu. Il est caractérisé par le fait
 * qu'il est toujours déplaçable par le joueur (pour être ajouté à l'inventaire).
 * </p>
 */

public class PiedDeBiche extends Objet{
    
    /**
     * Construit un nouveau pied-de-biche.
     *
     * @param nom   Le nom de l'objet (par exemple "Pied-de-biche rouillé").
     * @param monde Le monde dans lequel l'objet est créé.
     */

    public PiedDeBiche(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }
    /**
     * Indique si le pied-de-biche peut être déplacé.
     * <p>
     * Cette méthode surcharge le comportement par défaut pour indiquer
     * que cet outil est toujours transportable.
     * </p>
     *
     * @return {@code true} systématiquement, car un pied-de-biche peut être pris par le joueur.
     */
    public boolean estDeplacable(){
        return true;
    }


}