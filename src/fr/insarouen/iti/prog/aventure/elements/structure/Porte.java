package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.Activable;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import java.util.*;
import javax.swing.UIDefaults.ActiveValue;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;
<<<<<<< HEAD
=======
import java.io.Serializable;
>>>>>>> b66aeb1 (Ajot des cha,gement)

public class Porte extends ElementStructurel implements Activable, Serializable{
    private Piece pieceA;
    private Piece pieceB;
    private Etat etat ;
    private Serrure serrure = null;

    public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
        this.pieceA = pieceA;
        this.pieceB = pieceB;
        this.etat = Etat.FERME;
<<<<<<< HEAD
        this.pieceA.addPorte(this);
        this.pieceB.addPorte(this);
        
    }

    public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB)
            throws NomDEntiteDejaUtiliseDansLeMondeException,
                EntiteDejaDansUnAutreMondeException {
=======
        if (this.pieceA != null) this.pieceA.addPorte(this);
        if (this.pieceB != null) this.pieceB.addPorte(this);
        
    }

    public Porte(String nom, Monde monde, Serrure serrure, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException {
>>>>>>> b66aeb1 (Ajot des cha,gement)

        super(nom, monde);
        this.serrure = serrure;
        this.pieceA = pieceA;
        this.pieceB = pieceB;

        etat = Etat.VERROUILLE;
        pieceA.addPorte(this);
        pieceB.addPorte(this);

<<<<<<< HEAD
                }
=======
        }
>>>>>>> b66aeb1 (Ajot des cha,gement)

    public boolean activableAvec(Objet obj){
        if (serrure == null){
            return false;
        }
 
        return ((obj instanceof PiedDeBiche) && this.serrure.getEtat().equals(Etat.VERROUILLE) || this.serrure.activableAvec(obj)) ;
    }

    /**
     * Actionne l'ouverture de la porte et change d'état
     *
     * @param piece La piece dont on cherche l'autre coté.
     * @return void
     */
    @Override
    public void activer() throws ActivationImpossibleException {
        if (this.serrure != null && this.serrure.getEtat().equals(etat.VERROUILLE)){
            throw new ActivationImpossibleException("La porte "+this.getNom()+" est vérrouilée"); 
        }
        this.etat = switch(this.etat){
            case Etat.FERME ->Etat.OUVERT;
            case Etat.OUVERT ->Etat.FERME;
            default -> throw new ActivationImpossibleException("Impossible d'ouvrir la tepor"); 
        };
    }

    @Override
    public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
        if (!this.activableAvec(obj)){
            throw new ActivationImpossibleAvecObjetException("impossible d'ouvrir avec l'objet : " + obj.getNom());
        }
        // Ouverture pdb
        if(obj instanceof PiedDeBiche){
            this.serrure = null;
            this.etat = Etat.OUVERT;
        } 

        if (obj instanceof Cle){
            this.serrure.activerAvec(obj);
            this.etat = switch(this.etat){
                    case Etat.FERME -> Etat.VERROUILLE; 
                    case Etat.OUVERT -> Etat.VERROUILLE; 
                    case Etat.VERROUILLE -> Etat.OUVERT; 
                    default -> Etat.OUVERT;
                };
            }
        }
    
       /**
     * Récupère la ppièce à de l'autre coté de la porte
     * @return L'état actuelle de la porte ("Ouvert", "Fermé")
     */
    public Etat getEtat(){
        return this.etat; 

    }   
    /**
     * Récupère la ppièce à de l'autre coté de la porte 
     *
     * @param piece La piece dont on cherche l'autre coté.
     * @return La piece correspondante si elle est trouvée, {@code null} sinon.
     */
    public Piece getPieceAutreCote(Piece piece){
        if(this.pieceA.equals(piece)){
            return this.pieceB;
        }else if (this.pieceB.equals(piece)){
            return this.pieceA;
        } else {
            return null; 
        }
    }

    public Serrure getSerrure(){
        return this.serrure;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Porte: %s" , this.getNom()));
        sb.append(" ( ");
        sb.append(String.format("Monde: %s" ,  this.getMonde().getNom()));  
        sb.append(String.format("La serrure: %s" , this.serrure));
        sb.append("Concernant la pièce A");
        sb.append(Arrays.toString(this.pieceA.getObjets().toArray()));
        sb.append(" ");
        sb.append(Arrays.toString(this.pieceA.getVivants()));
        sb.append("Concernant la pièce B");
        sb.append(Arrays.toString(this.pieceB.getObjets().toArray()));
        sb.append(" ");
        sb.append(Arrays.toString(this.pieceB.getVivants()));
        sb.append(" )");
        return sb.toString();
    }

    }
    

