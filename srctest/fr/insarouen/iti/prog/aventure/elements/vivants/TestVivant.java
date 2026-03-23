package fr.insarouen.iti.prog.aventure.elements.vivants;
import org.junit.Test;
import org.junit.Before;
import java.util.*; 
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteFermeException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.TableauDynamique;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;

public class TestVivant{
    private Monde monde1; 
    private Piece piece1;
    private Piece piece2;
    private Objet objet1;
    private Porte p1;     
    private Porte p2;     
    private int pointVie = 10; 
    private int pointForce = 20; 
    private Vivant vivant; 
    private Monstre monstre1; 
    
  


    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException, ActivationImpossibleException{
        this.monde1 = new Monde("MondeTest");
        this.piece1 = new Piece("PieceDepart", monde1);
        this.piece2 = new Piece("PieceArrivee", monde1);
        this.p1 = new Porte("porte1", monde1,piece1, piece2);
        this.p1.activer();
        this.p2 = new Porte("porte2", monde1, piece2, null);
        this.objet1 = new PiedDeBiche("PiedDeBiche", monde1);
        this.piece1.deposer(objet1);
        this.vivant = new VivantC("Marwan", monde1,this.pointVie, this.pointForce, piece1);
        this.monstre1 = new Monstre("malandin", monde1, pointVie, pointForce, piece1);
    }

    @Test
    public void test_Getters() {
        assertThat(this.vivant.getNom(), equalTo("Marwan"));
        assertThat(this.vivant.getPiece(),equalTo(this.piece1));
        assertThat(this.vivant.getPointForce(),equalTo(this.pointForce));
        assertThat(this.vivant.getPointVie(),equalTo(this.pointVie));
    }
    //Actions 
    @Test
    public void test_prendre()throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{

        assertThat("La pièce doit contenir objet1", this.piece1.contientObjet(objet1));
        assertThat("Le vivant ne possède pas l'objet1", !this.vivant.possede(objet1));

        this.vivant.prendre(objet1);

        assertThat("Le vivant est sensé possédé objet1", this.vivant.possede(objet1)); 
        assertThat("La pièce ne doit pas avoir objet1", !this.piece1.contientObjet(objet1)); 

    }

    @Test
    public void test_deposer() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException, ObjetNonPossedeParLeVivantException{
        this.vivant.prendre(this.objet1);
        assertThat(this.piece1.contientObjet(objet1),equalTo(false));
        assertThat(this.vivant.possede(objet1),equalTo(true));

        this.vivant.deposer(objet1);

        assertThat(this.piece1.contientObjet(objet1),equalTo(true));
        assertThat(this.vivant.possede(objet1),equalTo(false));
    }
    
    @Test
    public void test_getEntites() throws ObjetAbsentDeLaPieceException , ObjetNonDeplacableException {
        this.vivant.prendre(this.objet1);
        assertThat(this.piece1.contientObjet(objet1),equalTo(false));
     
    }

    @Test
    public void test_getObjets() throws ObjetAbsentDeLaPieceException , ObjetNonDeplacableException{
        
    this.vivant.prendre(this.objet1); 
    Collection<Objet> lesObj = this.vivant.getObjets(); 
    assertThat(lesObj, hasItems(objet1));
     
    }



    @Test
    public void test_setPiece() throws ObjetNonDeplacableException,VivantAbsentDeLaPieceException {
        this.vivant.setPiece(piece2);

        assertThat(this.vivant.getPiece(), equalTo(this.piece2));
        assertThat(this.piece1.contientVivant(this.vivant), equalTo(false)); 
        assertThat(this.piece2.contientVivant(this.vivant), equalTo(true)); 


    }

 
    @Test
    public void test_setPieceNull() throws VivantAbsentDeLaPieceException{
        this.vivant.setPiece(null);
        assertThat(this.vivant.getPiece(), equalTo(null)); 
        assertThat(this.piece1.contientVivant(this.vivant), equalTo(false)); 
        assertThat(this.piece2.contientVivant(this.vivant), equalTo(false));
    }

    @Test
    public void test_ObjetNonPossedeParLeVivantException() throws ObjetNonPossedeParLeVivantException{
         this.vivant.getObjet("fruit"); 
    }

    @Test
    public void test_toString() throws VivantAbsentDeLaPieceException{
        assertThat(this.vivant.toString(), equalTo("vivant: Marwan dans PieceDepart possede []"));
    }

    @Test
    public void test_franchir() throws PorteInexistanteDansLaPieceException,PorteFermeException{
        this.vivant.franchir(p1);
        this.monstre1.franchir(p1);
        assertThat(this.vivant.getPiece(), equalTo(this.piece2));
        assertThat(this.vivant.getPiece(), equalTo(this.piece2));
    }

    @Test(expected = PorteInexistanteDansLaPieceException.class)
    public void test_franchirException() throws PorteInexistanteDansLaPieceException,PorteFermeException{
        this.vivant.franchir(p2);

    }

    




     

}



class EntiteC extends Entite {

    public EntiteC(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
	super(nom,monde);
    }
}

class VivantC extends Vivant {
    public VivantC(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointVie, pointForce, piece, objets);
    }
}