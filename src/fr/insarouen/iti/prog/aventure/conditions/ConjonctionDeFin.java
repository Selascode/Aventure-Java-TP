package fr.insarouen.iti.prog.aventure.conditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;

public class ConjonctionDeFin extends ConditionDeFin{
    private List<ConditionDeFin> conditions = new ArrayList<>(); 

    public ConjonctionDeFin(EtatDuJeu etat, ConditionDeFin... conditions ){
        super(etat); 
        Collections.addAll(this.conditions, conditions); 
    }

    @Override
    public EtatDuJeu verifierCondition(){
        for (ConditionDeFin c : this.conditions){
           if(c.verifierCondition().equals(EtatDuJeu.ENCOURS) ){
            return EtatDuJeu.ENCOURS; 
           }
        }
        return this.getEtatSiConditionVerifie(); 
    }
    
}
