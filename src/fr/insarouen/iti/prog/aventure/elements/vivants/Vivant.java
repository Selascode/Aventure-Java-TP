package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.TableauDynamique;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.Monde; 
import java.util.*;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;

/**
 * Classe abstraite représentant une entité vivante dans le monde.
 * <p>
 * Un Vivant est une entité capable de se déplacer d'une {@link Piece} à une autre
 * et de posséder des objets (inventaire). Il possède également des caractéristiques
 * physiques comme des points de vie et de force.
 * </p>
 */
public abstract class Vivant extends Entite {
    /***************** */
    /* Les accesseurs  */
    /***************** */
    private int pointVie;
    private int pointForce;
    private Piece piece;
    /**private TableauDynamique objets;*/
    private Map<String,Objet> objets = new HashMap<String,Objet>();

    /***************** */
    /* Le constructeur */
    /***************** */

    /**
     * Construit un nouveau Vivant.
     * <p>
     * Le vivant est automatiquement ajouté à la pièce spécifiée lors de la création.
     * Les objets passés en paramètres sont ajoutés à son inventaire initial.
     * </p>
     *
     * @param nom        Le nom du vivant.
     * @param monde      Le monde dans lequel le vivant évolue.
     * @param pointVie   Les points de vie initiaux.
     * @param pointForce Les points de force initiaux.
     * @param piece      La pièce de départ du vivant.
     * @param objets     Une liste variable d'objets (varargs) que le vivant possède au démarrage.
     */
    public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
        this.pointVie = pointVie;
        this.pointForce = pointForce;
        this.piece = piece; 
        

        if (objets != null) {
            for(Objet o : objets){
                this.objets.put(o.getNom() , o); 
            }
        }

        if (this.piece != null) {
            this.piece.entrer(this);
        }
    }

    /***************** */
    /* Les getteurs    */
    /***************** */


    /**
     * Récupère un objet de l'inventaire à partir de son nom.
     * <p>
     * Cette méthode cherche l'objet mais ne le retire pas de l'inventaire.
     * </p>
     *
     * @param nomObj Le nom de l'objet recherché.
     * @return L'objet correspondant s'il est trouvé, {@code null} sinon.
     */
    public Objet getObjet(String nomObj){
        /**int i = 0;
        boolean trouve = false;
        Objet objTrouve = null;

        if (!this.objets.contient(nomObj)) return objTrouve; 

        while(i< this.objets.taille() && !trouve){
            if (this.objets.get(i).getNom() == nomObj){
                objTrouve = (Objet)this.objets.get(i); 
                trouve = true;
            }
            i++;
        }
    */
        return this.objets.get(nomObj);
    }

    /**
     * Récupère l'ensemble des objets présents dans l'inventaire.
     * @param nomObj (Paramètre non utilisé dans l'implémentation actuelle).
     * @return Un tableau contenant tous les objets portés par le vivant.
     */
    public Collection<Objet> getObjets(){
        /**int taille = this.objets.taille(); 

        Objet[] tabObjets = new Objet[taille]; 

        for(int i=0; i < taille; i++){
            Entite ent = this.objets.get(i); 

            if (ent instanceof Objet){
                tabObjets[i] = (Objet)ent; 
            }
        }
        */
       // Faire une copie des élements 
      
        return Collections.unmodifiableCollection(this.objets.values());
    }

    /**
     * Renvoie la pièce dans laquelle se trouve le vivant.
     *
     * @return La pièce courante.
     */
    public Piece getPiece(){
        return this.piece;
    }

    /**
     * Renvoie les points de force du vivant.
     *
     * @return La force actuelle.
     */
    public int getPointForce(){
        return this.pointForce;
    }

    /**
     * Renvoie les points de vie du vivant.
     *
     * @return Les points de vie actuels.
     */
    public int getPointVie(){
        return this.pointVie;
    }

    /**
     * Renvoie l'etat de vie du vivant.
     *
     * @return Un booléen correspond à mort (true) ou vivant(false)
     */
    public boolean estMort(){
        return this.getPointVie() <= 0;
    }

    /**
     * Vérifie si le vivant possède un objet spécifique.
     *
     * @param obj L'objet à vérifier.
     * @return {@code true} si l'objet est dans l'inventaire, {@code false} sinon.
     */
    public boolean possede(Objet obj){ 
        
        return this.possede(obj.getNom()); 
    }

    /**
     * Vérifie si le vivant possède un objet portant ce nom.
     *
     * @param nomObj Le nom de l'objet.
     * @return {@code true} si un objet avec ce nom est présent, {@code false} sinon.
     */
    public boolean possede(String nomObj){
        return this.objets.containsKey(nomObj); 
    }

    /***************** */
    /* Les setteurs    */
    /***************** */

    /**
     * Modifie la localisation du vivant (déplacement).
     * <p>
     * Cette méthode gère automatiquement la transition :
     * </p>
     * <ol>
     * <li>Sortie de l'ancienne pièce (si elle existe).</li>
     * <li>Mise à jour de la référence interne.</li>
     * <li>Entrée dans la nouvelle pièce (si elle n'est pas nulle).</li>
     * </ol>
     *
     * @param piece La nouvelle pièce de destination.
     */

    // APRÈS (corrigé)
    public void setPiece(Piece piece){
        if (this.piece == piece) return;

        Piece anciennePiece = this.piece;
        this.piece = piece;              

        if (anciennePiece != null && anciennePiece.contientVivant(this)){
            try {
                anciennePiece.sortir(this); 
            } catch (VivantAbsentDeLaPieceException e){
                e.printStackTrace();
            }
        }

        if (piece != null) {
            piece.entrer(this);
        }
    }

    /**
     * fixe la valeur des points de vie.
     * @param int (valeur des points de vie)
     */
    public void setPointVie(int pv){
       this.pointVie = pv; 
    }


    /**
     * fixe la valeur des points de vie.
     * @param int (valeur des points de vie)
     */
    public void setPointForce(int pf){
       this.pointForce = pf; 
    }


    /***************** */
    /* Les actions     */
    /***************** */

    /**
     * Dépose un objet de l'inventaire dans la pièce courante.
     * <p>
     * L'objet est retiré de l'inventaire du vivant et ajouté à la liste des objets
     * de la {@link Piece} où se trouve le vivant. Si le vivant ne possède pas l'objet,
     * rien ne se passe.
     * </p>
     *
     * @param obj L'objet à déposer.
     */
    public void deposer(Objet obj) throws ObjetNonPossedeParLeVivantException{
       this.deposer(obj.getNom());
    }
    /**
     * Dépose un objet (identifié par son nom) de l'inventaire dans la pièce courante.
     *
     * @param nomObj Le nom de l'objet à déposer.
     * @see #deposer(Objet)
     */
    public void deposer(String nomObj) throws ObjetNonPossedeParLeVivantException{
        //Cas sans l'objets
        if (!this.objets.containsKey(nomObj)) {
            throw new ObjetNonPossedeParLeVivantException(String.format("L'objet ( %s ) n'est pas dans le sac à dos",nomObj)); 
        }

        //Retrait du sac
        Objet o = (Objet)this.objets.remove(nomObj);
        
        //Ajout dans la piece
        this.piece.deposer(o);
    }

    /**
     * Tente de prendre un objet présent dans la pièce pour le mettre dans l'inventaire.
     * <p>
     * L'action réussit seulement si :
     * </p>
     * <ul>
     * <li>L'objet est présent dans la pièce courante.</li>
     * <li>L'objet est déplaçable (voir {@link Objet#estDeplacable()}).</li>
     * </ul>
     *
     * @param obj L'objet à prendre.
     */
    public void prendre(Objet obj) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
          this.prendre(obj.getNom());
           
    }

    /**
     * Tente de prendre un objet (via son nom) présent dans la pièce.
     * <p>
     * Si l'objet est trouvé mais n'est pas déplaçable, il est immédiatement
     * reposé dans la pièce.
     * </p>
     *
     * @param nomObj Le nom de l'objet à prendre.
     */
    public void prendre(String nomObj) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        Objet objetRecupere = this.getPiece().retirer(nomObj);
        if(objetRecupere != null){
            this.objets.put(objetRecupere.getNom() , objetRecupere);
        }
    }
    

    /**
     * Permet au vivant de franchir une porte.
     * 
     * @param porte La porte à franchir.
     * @throws PorteInexistanteDansLaPieceException Si la porte n'existe pas dans la pièce.
     * @throws PorteFermeException Si la porte est fermée.
     */
    public void franchir(Porte porte) throws PorteInexistanteDansLaPieceException,PorteFermeException{
        if (!this.getPiece().aLaPorte(porte)){
            throw new PorteInexistanteDansLaPieceException(String.format("La porte %s n'est pas dans la pièce %s",porte.getNom(),this.getPiece()));
        }
        if (porte.getEtat().equals(Etat.FERME)){
            throw new PorteFermeException("La porte "+ porte.getNom()+" est fermé coco");
        }
        porte.getPieceAutreCote(this.getPiece()).entrer(this);
    }

    /**
     * Permet au vivant de franchir une porte à partir de son nom.
     * 
     * @param nomPorte Le nom de la porte à franchir.
     * @throws PorteInexistanteDansLaPieceException Si la porte n'existe pas dans la pièce.
     * @throws PorteFermeException Si la porte est fermée.
     */
    public void franchir(String nomPorte) throws PorteInexistanteDansLaPieceException,PorteFermeException{
        this.franchir(this.getPiece().getPorte(nomPorte));
    }


    /***************** */
    /* Les toString    */
    /***************** */
    public String toString(){
        StringBuilder sb =new StringBuilder(); 
        sb.append(String.format("vivant: %s",this.getNom()));
        sb.append(" dans ");
        sb.append((this.piece != null) ? this.piece.getNom() : "null");
        sb.append(" possede ");
        sb.append(Arrays.toString(this.objets.values().toArray(new Objet[this.objets.size()])));
        return sb.toString(); 
    }
}