package fr.insarouen.iti.prog.aventure.elements.vivants;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.ActivationException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import java.io.Serializable;
import java.util.*;
import fr.insarouen.iti.prog.aventure.elements.Executable;

public class JoueurHumain extends Vivant implements Serializable , Executable{
    private String nom;
    private Monde monde;
    private int pointsVie;
    private int pointsForce;
    private Piece piece;
    private Map<String,Objet> objets = new HashMap<String,Objet>();
    private String ordre = null;
    
    public JoueurHumain(String nom , Monde monde , int pointsVie , int pointForce , Piece piece , Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException{
        super(nom , monde , pointsVie , pointForce , piece , objets);
    }

    public String getOrdre(){
        return this.ordre;
    }

    public void executer(){

    }
    public void setOrdre(String Ordre){
        this.ordre = Ordre;
    }
    private void commandePrendre(String nomObjet) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException{
        this.prendre(nomObjet);
    }
    private void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException{
        this.deposer(nomObjet);
    }
    private void commandeFranchir(String nomPorte) throws PorteFermeException,PorteInexistanteDansLaPieceException{
        this.franchir(nomPorte);
    }
    private void commandeOuvrirPorte(String nomPorte) throws ActivationException, PorteInexistanteDansLaPieceException{
        this.piece.getPorte(nomPorte).activer();
    }
    private void commandeOuvrirPorte(String nomPorte, String nomObjet) throws ActivationException, PorteInexistanteDansLaPieceException,ObjetNonPossedeParLeVivantException{
        this.piece.getPorte(nomPorte).activerAvec(this.getObjet(nomObjet));
    }

    
}