package fr.insarouen.iti.prog.aventure.elements;
import fr.insarouen.iti.prog.aventure.Monde; 
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;
import java.lang.Object;
import java.io.Serializable;
import java.lang.Error;

/**
 * Classe abstraite représentant une entité de base du jeu.
 * <p>
 * Une entité est le concept le plus général du projet. Elle possède un nom
 * et est rattachée à un {@link Monde}. Toutes les autres classes du jeu
 * (Objets, Vivants, Éléments structurels) héritent de cette classe.
 * </p>
 */
public abstract class Entite extends Object implements Serializable{
    /** Le nom de l'entité. */
    private String nom;
    /** Le monde auquel l'entité appartient. */
    private Monde monde;

    /**
     * Construit une nouvelle entité.
     * <p>
     * Lors de la construction, l'entité est automatiquement ajoutée au monde
     * via la méthode {@link Monde#ajouter(Entite)}.
     * </p>
     *
     * @param nom   Le nom de l'entité (ne doit pas être null).
     * @param monde Le monde dans lequel l'entité est créée.
     */
    public Entite(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.nom = nom;
        this.monde = monde;
        try{
            this.monde.ajouter(this);
        }
        catch(EntiteDejaDansUnAutreMondeException e){
            throw new Error("Ne devrait jamais arriver");
        }
    }
    /**
     * Obtient le nom de l'entité.
     *
     * @return Le nom sous forme de chaîne de caractères.
     */
    public String getNom(){
        return this.nom;
    }
    /**
     * Obtient le monde auquel l'entité appartient.
     *
     * @return L'instance de {@link Monde} associée.
     */
    public Monde getMonde(){
        return this.monde;
    }
    /**
     * Vérifie l'égalité entre cette entité et un autre objet.
     * <p>
     * Deux entités sont considérées comme égales si elles ont la même classe,
     * le même nom et appartiennent au même monde.
     * </p>
     *
     * @param obj L'objet avec lequel comparer.
     * @return {@code true} si les objets sont égaux, {@code false} sinon.
     */
    public boolean equals(Object obj){

        // Tests de la classe 
        if (obj == null || !(this.getClass().equals(obj.getClass()) )) {
        return false; 
        }
        // Transtipage
        Entite ent = (Entite)obj;

        if (ent == null || (this.getNom() != ent.getNom() || this.getMonde() != ent.getMonde() )){
            return false;
        }
        return true;
    }

    // HashCode Entite
    public int hashCode() {
        final int premier1 = 17;
        final int premier2 = 13;
        return ((this.nom == null) ? 0 : this.nom.hashCode()) * premier1 + ((this.monde == null) ? 0 : this.monde.hashCode()) * premier2 ;
    }


    public String toString(){
        return String.format( this.getNom() );
    }
}