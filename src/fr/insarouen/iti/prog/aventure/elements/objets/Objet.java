package fr.insarouen.iti.prog.aventure.elements.objets;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import java.lang.Object;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

public abstract class Objet extends Entite {

/**
 * Classe abstraite représentant un objet générique dans le monde du jeu d'aventure.
 * <p>
 * Un objet est une forme spécialisée. Cette classe sert de base
 * pour définir des objets concrets (comme un coffre, une clé, etc.) et définit
 * le contrat concernant leur transportabilité via la méthode estDeplacable}.
 * </p>
 *
 */
    
    public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
    }
/**
     * Indique si cet objet peut être déplacé par le joueur.
     * <p>
     * Les sous-classes doivent implémenter cette méthode pour spécifier
     * si l'objet peut être mis dans l'inventaire ou déplacé d'une pièce à l'autre.
     * </p>
     *
     * @return {@code true} si l'objet est déplaçable, {@code false} sinon.
     */

    public abstract boolean estDeplacable();
}
        
    
