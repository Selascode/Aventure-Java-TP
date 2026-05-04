package fr.insarouen.iti.prog.aventure.conditions;
import java.io.Serializable;


public  abstract class ConditionDeFin implements Serializable {

    private EtatDuJeu etat;
    
    public ConditionDeFin(EtatDuJeu etat){
        this.etat = etat;
    }

    public  EtatDuJeu getEtatSiConditionVerifie(){
        return this.etat;
        }
    

    public abstract EtatDuJeu verifierCondition();
    


}