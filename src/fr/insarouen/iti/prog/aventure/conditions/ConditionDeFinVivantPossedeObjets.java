package fr.insarouen.iti.prog.aventure.conditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.conditions.EtatDuJeu;
public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin{
    private EtatDuJeu etat;
    private Vivant vivant;
    private List<Objet> objets = new ArrayList<>();

    public ConditionDeFinVivantPossedeObjets(EtatDuJeu etat, Vivant vivant, Objet... objets){
        super(etat); 
        this.etat = etat; 
        this.vivant = vivant ;
        Collections.addAll(this.objets,objets);
    }

    @Override
    public EtatDuJeu verifierCondition() {
        for (Objet o : this.objets){
            if (!this.vivant.possede(o)) {
                return EtatDuJeu.ENCOURS;
            };

        }
        return this.etat;
    }
}

