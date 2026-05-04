package fr.insarouen.iti.prog.aventure.conditions;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.conditions.EtatDuJeu;

public class ConditionDeFinVivantMort extends ConditionDeFin{
    
    private Vivant vivant;

    public ConditionDeFinVivantMort(EtatDuJeu etat , Vivant vivant){
        super(etat);
        this.vivant = vivant;
    }

    @Override
    public EtatDuJeu verifierCondition(){
        if (this.vivant.estMort()){
            return this.getEtatSiConditionVerifie();
        }
        else{
            return EtatDuJeu.ENCOURS;
        }
    }
    

}
