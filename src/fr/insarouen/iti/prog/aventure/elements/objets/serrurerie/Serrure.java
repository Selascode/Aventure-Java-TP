package fr.insarouen.iti.prog.aventure.elements.objets.serrurerie;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.Activable;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.io.Serializable;

public class Serrure extends Objet implements Activable,Serializable{

    private Etat etat;
    private Cle cle = null; 

    public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(monde.genererNom("verisure"), monde);
        this.etat = Etat.VERROUILLE;
        
    }

    public Serrure(String nom , Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom,monde);
        this.etat = Etat.VERROUILLE;

    }

    public Etat getEtat(){
        return this.etat;
    }

    public Cle creerCle() throws NomDEntiteDejaUtiliseDansLeMondeException {
        //Si aucune clé
        if (this.cle == null) {
            //Création de l'unique
            this.cle = new Cle(this.getMonde().genererNom("cle"), this.getMonde());
            return this.cle;
        } 
            return null; 
          
    }

    public boolean estDeplacable(){
        return false;
    }

    
    public void activer() throws ActivationImpossibleException{
        throw new ActivationImpossibleException("activation impossible man");
    }

    public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
        if (!(obj == null)){
            if(this.activableAvec(obj)){
                this.etat = Etat.DEVERROUILLE; 
            } else{
            throw new ActivationImpossibleAvecObjetException("activation impossible avec l'objet "+ obj.getNom());  
            }   
        }
    }

    public boolean activableAvec(Objet obj){
        if (obj == null){
            return false; 
        }
        
        return this.cle != null && this.cle.equals(obj);
    }




}
