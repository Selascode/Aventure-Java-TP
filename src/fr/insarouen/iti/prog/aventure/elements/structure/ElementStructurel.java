package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.lang.Object ;

/**
 * Classe abstraite représentant un élément structurel du monde.
 * <p>
 * Un élément structurel est une {@link Entite} qui constitue l'environnement
 * ou la topologie du jeu, comme une {@code Piece}.
 * </p>
 */

public abstract class ElementStructurel extends Entite {
    /**
     * Construit un nouvel élément structurel.
     *
     * @param nom   Le nom de l'élément (par exemple "Salon", "Couloir Nord").
     * @param monde Le monde auquel cet élément appartient.
     */
    public ElementStructurel(String nom , Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }

}