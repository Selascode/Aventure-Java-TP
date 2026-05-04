package fr.insarouen.iti.prog.aventure.conditions;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.conditions.ConjonctionDeFin;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFinVivantDansPiece;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFinVivantPossedeObjets;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.conditions.EtatDuJeu;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;

import java.util.*;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin {
    
    private Piece piece;
    private Vivant vivant;
    private List<Objet> objets = new ArrayList<>();

    public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etat, Vivant vivant, Piece piece, Objet... objets){
        super(etat);
        this.vivant = vivant;
        this.piece = piece;
        Collections.addAll(this.objets , objets);
    }

    public EtatDuJeu verifierCondition(){
        ConditionDeFin conditionPiece = new ConditionDeFinVivantDansPiece(this.getEtatSiConditionVerifie(),this.vivant, this.piece); 
        ConditionDeFin conditionObjets = new ConditionDeFinVivantPossedeObjets(this.getEtatSiConditionVerifie(),this.vivant, this.objets.toArray(Objet[]::new));
        List<ConditionDeFin> conditions = new ArrayList<>(); 
        Collections.addAll(conditions, conditionPiece,conditionObjets);
        ConditionDeFin conj = new ConjonctionDeFin(this.getEtatSiConditionVerifie(), conditions.toArray(ConditionDeFin[]::new));
        return conj.verifierCondition(); 
    }
}
