package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import java.util.*;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import java.io.Serializable;

/**
 * Classe représentant une pièce  du monde.
 * <p>
 * Une pièce est un élément structurel qui peut contenir des {@link Objet}s
 * et des {@link Vivant}s. Elle permet aux entités d'interagir entre elles
 * et constitue un nœud de navigation dans le jeu.
 * </p>
 */


public class Piece extends ElementStructurel{
    /** Tableau dynamique stockant les objets présents dans la pièce. */
    private Map<String,Objet> Objets ;
    /** Tableau dynamique stockant les vivants présents dans la pièce. */
    private Map<String,Vivant>  Vivants;
    private Map<String,Porte>  Portes ;
    /**
     * Construit une nouvelle pièce vide.
     *
     * @param nom   Le nom de la pièce (ex: "Cuisine", "Donjon").
     * @param monde Le monde auquel la pièce appartient.
     */
    public Piece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{ 
        super(nom,monde);
        this.Objets = new HashMap<>();
        this.Vivants = new HashMap<>();
        this.Portes = new HashMap<>();
    }
    /**
     * Vérifie si la pièce contient un objet spécifique.
     *
     * @param obj L'objet à rechercher.
     * @return {@code true} si l'objet est présent, {@code false} sinon.
     */
    public boolean contientObjet(Objet obj){
        return this.contientObjet(obj.getNom());
    }
    /**
     * Vérifie si la pièce contient un objet portant un certain nom.
     *
     * @param nomObj Le nom de l'objet à rechercher.
     * @return {@code true} si un objet avec ce nom est présent, {@code false} sinon.
     */
    public boolean contientObjet(String nomObj){
        return this.Objets.containsKey(nomObj);
    }
    /**
     * Vérifie si la pièce contient un vivant spécifique.
     *
     * @param vivant Le vivant à rechercher.
     * @return {@code true} si le vivant est présent, {@code false} sinon.
     */
    public boolean contientVivant(Vivant vivant){
        return this.contientVivant(vivant.getNom());
    }
    /**
     * Vérifie si la pièce contient un vivant portant un certain nom.
     *
     * @param nomVivant Le nom du vivant à rechercher.
     * @return {@code true} si un vivant avec ce nom est présent, {@code false} sinon.
     */
    public boolean contientVivant(String nomVivant){
        return this.Vivants.containsKey(nomVivant); 
    }
    /**
     * Dépose un objet dans la pièce.
     * <p>
     * L'objet est ajouté à la liste des objets visibles dans ce lieu.
     * </p>
     *
     * @param obj L'objet à déposer.
     */
    public void deposer(Objet obj){
        this.Objets.put(obj.getNom(),obj);
    }
    /**
     * Fait entrer un vivant dans la pièce.
     * <p>
     * Cette méthode ajoute le vivant à la liste des présents et met à jour
     * la localisation du vivant (via {@code vivant.setPiece(this)}).
     * </p>
     *
     * @param vivant Le vivant qui entre dans la pièce.
     */
    public void entrer(Vivant vivant) {
        if (!this.contientVivant(vivant)) {
            this.Vivants.put(vivant.getNom() , vivant);  
        }

        if (vivant.getPiece() != this) {
            vivant.setPiece(this);
        }
    }
    /**
     * Récupère la liste de tous les objets présents dans la pièce.
     *
     * @return Une collection contenant les objets.
     */
    public Collection<Objet> getObjets(){
        return Collections.unmodifiableCollection(this.Objets.values());
    }
    /**
     * Récupère la liste de tous les vivants présents dans la pièce.
     * <p>
     * Cette méthode filtre le contenu de la pièce pour ne renvoyer que
     * les entités de type {@link Vivant}.
     * </p>
     *
     * @return Un tableau contenant les vivants.
     */
    public Vivant[] getVivants() {
        /**int taille = 0;
        for (int i = 0; i < this.Vivants.taille(); i++) {
            if (this.Vivants.get(i) instanceof Vivant) {
                taille++;
            }
        }
        
        Vivant[] tabVivant = new Vivant[taille];
        int j = 0;
        for (int i = 0; i < this.Vivants.taille(); i++) {
            Entite e = this.Vivants.get(i);
            if (e instanceof Vivant) {
                tabVivant[j] = (Vivant) e;
                j++;
            }
        }*/
        return this.Vivants.values().toArray(new Vivant[this.Vivants.size()]);
    }

/**
     * Retire un objet de la pièce.
     *
     * @param obj L'objet à retirer.
     * @return L'objet retiré s'il était présent, sinon {@code null} (selon l'implémentation de TableauDynamique).
     */
    public Objet retirer(Objet obj) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        return this.retirer(obj.getNom());
    }
/**
     * Retire un objet de la pièce en utilisant son nom.
     *
     * @param nom Le nom de l'objet à retirer.
     * @return L'objet retiré s'il a été trouvé, sinon {@code null}.
     */
    public Objet retirer(String nom) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        if(!this.contientObjet(nom)){
            throw new ObjetAbsentDeLaPieceException(String.format("L'objet ( %s ) est absent de la pièce ( %s ) ",nom, this.getNom()));
        }
        Objet o = (Objet)this.Objets.remove(nom);

        if (!o.estDeplacable()){
            this.Objets.put(nom, o);
            throw new ObjetNonDeplacableException(String.format("L'objet %s n'est pas déplaçable",nom));
        }
        return o; 
    }
/**
     * Fait sortir un vivant de la pièce.
     *
     * @param vivant Le vivant à faire sortir.
     * @return Le vivant qui vient de sortir.
     */
    public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException{
        if(!this.Vivants.containsKey((vivant.getNom()))){
            throw new VivantAbsentDeLaPieceException(String.format("Le vivant (%s) n'est pas dans la pièce", vivant.getNom()));
        }

        Vivant v = this.Vivants.remove(vivant.getNom());

 
        if (v.getPiece() == this) {
            v.setPiece(null);
        }

        return v;
    }

    /**
     * Fait sortir un vivant de la pièce en utilisant son nom.
     *
     * @param nomVivant Le nom du vivant à faire sortir.
     * @return Le vivant correspondant s'il était présent.
     */
    public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException {
        if(!this.Vivants.containsKey(nomVivant)){
            throw new VivantAbsentDeLaPieceException("Le vivant n'est pas dans la pièce");
        }
        return this.Vivants.remove(nomVivant);
    }

    /**
     * Ajoute une porte à la pièce
     *
     * @param porte La porte à ajouter à la pièce.
     */
    protected void addPorte(Porte porte){
            Portes.put(porte.getNom(), porte);

    }
        

    /**
     * Permet de vérifier si la porte est dans la pièce
     *
     * @param porte La porte à vérifier.
     * @return {@code true} si la porte est présente dans la pièce, {@code false} sinon.
     */
    public boolean aLaPorte(Porte porte){
        return this.aLaPorte(porte.getNom());
    }

    /**
     * Fait sortir un vivant de la pièce en utilisant son nom.
     *
     * @param porte La porte à vérifier.
     * @return {@code true} si la porte est présente dans la pièce, {@code false} sinon.
     */
    public boolean aLaPorte(String nomPorte){
        return this.Portes.containsKey(nomPorte);
    }

     /**
     * List des différentes portes de la pièce
     * @return Collection<Porte> ArrayList des portes 
     */
    public Collection<Porte> getPortes(){
        return Collections.unmodifiableCollection(this.Portes.values()) ;

    }

     /* Récupère une porte de la pièce par son nom.
     * 
     * @param nomPorte Le nom de la porte à récupérer.
     * @return La porte correspondante, ou null si elle n'existe pas.
     */
    public Porte getPorte(String nomPorte) {
        return this.Portes.get(nomPorte);
    }

    

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Piece: %s", this.getNom()));
        sb.append(" ( ");
        sb.append(Arrays.toString(this.getObjets().toArray()));
        sb.append(" ");
        sb.append(Arrays.toString(this.getVivants()));
        sb.append(" )");
        return sb.toString();
    }


}