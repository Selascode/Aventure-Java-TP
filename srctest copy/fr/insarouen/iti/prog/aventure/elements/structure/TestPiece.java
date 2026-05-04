package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.containsInAnyOrder;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.TableauDynamique;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.ITIAventureException;
public class TestPiece{
    private Piece p1;    
    private Piece p2;
    private Porte porteA;
    private Porte porteB;
    private Vivant v1;
    private Vivant v2;
    private Monde monde; 
    private PiedDeBiche pdb1;
    private PiedDeBiche pdb2;

    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.monde = new Monde("insa"); 
        this.p1 = new Piece("magellan",monde); 
        this.p2 = new Piece("dumont",monde); 
        this.v1 = new VivantC("Marwan", monde,10, 29, p1);
        this.v2 = new VivantC("Selas", monde,10, 29, p1);
        this.pdb1 = new PiedDeBiche("pdb1", monde);
        this.pdb2 = new PiedDeBiche("pdb2", monde);
        this.p1.deposer(this.pdb1);
        this.p1.deposer(this.pdb2);
        this.porteA = new Porte("Entre p1 et p2", monde, p2, p1); 
        this.p1.addPorte(porteA);
        this.porteB = new Porte("Entre p2 et null", monde, p2, null); 
        
    }
 
    @Test
    public void test_contientObjet(){
        assertThat(this.p1.contientObjet("pdb1"), equalTo(true));
        assertThat(this.p1.contientObjet(this.pdb1), equalTo(true));
        assertThat(this.p1.contientObjet("pdb2"), equalTo(true));
        assertThat(this.p1.contientObjet(this.pdb2), equalTo(true));
        assertThat(this.p2.contientObjet("pdb1"), equalTo(false));
        assertThat(this.p2.contientObjet(this.pdb1), equalTo(false));
        assertThat(this.p2.contientObjet("pdb2"), equalTo(false));
        assertThat(this.p2.contientObjet(this.pdb2), equalTo(false));
    }

    @Test
    public void test_contientVivant(){
         assertThat(this.p1.contientVivant("Marwan"), equalTo(true));
         assertThat(this.p1.contientVivant(v1), equalTo(true));
         assertThat(this.p1.contientVivant("Selas"), equalTo(true));
         assertThat(this.p1.contientVivant(v2), equalTo(true));
         assertThat(this.p2.contientVivant("Marwan"), equalTo(false));
         assertThat(this.p2.contientVivant(v1), equalTo(false));
         assertThat(this.p2.contientVivant("Selas"), equalTo(false));
         assertThat(this.p2.contientVivant(v2), equalTo(false));
    }
    @Test
    public void test_entrer()throws NomDEntiteDejaUtiliseDansLeMondeException{
        //v3 qui sort de null part
        Vivant v3 = new VivantC("Edouard", monde, 0, 0, null);
        this.p2.entrer(v3);
        assertThat(this.p2.contientVivant(v3),equalTo(true)); 
        assertThat(v3.getPiece(),equalTo(this.p2)); 
        //Ajoute de v1
        this.p2.entrer(this.v1);
        assertThat(this.p2.contientVivant(v1), equalTo(true));
        assertThat(this.p1.contientVivant(v1), equalTo(false));
        assertThat(this.v1.getPiece(),equalTo(p2)); 
    }

    @Test
    public void test_getObjets(){
        Objet[] objets = this.p1.getObjets().toArray(new Objet[0]); 
        assertThat(objets[1], equalTo((Objet)this.pdb1));
        assertThat(objets[0], equalTo((Objet)this.pdb2));
        //assertThat(this.p2.getObjets(), equalTo(null)); 
    }

    @Test
    public void test_getVivants(){
        Vivant[] vivants = this.p1.getVivants(); 
        assertThat(vivants[0], equalTo(this.v1));
        assertThat(vivants[1], equalTo(this.v2));
        //assertThat(this.p2.getObjets(), equalTo(null)); 
    }


    @Test
    public void test_retirer() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        this.p1.retirer(this.pdb1);
        this.p1.retirer("pdb2");
        Objet[] objets =  this.p1.getObjets().toArray(new Objet[0]); 
        assertThat(objets.length, equalTo(0)); 
    }
    

    @Test(expected = ObjetAbsentDeLaPieceException.class)
    public void test_ObjetAbsentDeLaPieceException() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException{
        this.p2.retirer(this.pdb1); 
    }

    @Test(expected = ObjetNonDeplacableException.class)
    public void test_ObjetNonDeplacable() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException, NomDEntiteDejaUtiliseDansLeMondeException{    
        Objet statue = new ObjetNonDeplacable("statue", monde); 
        this.p1.deposer(statue);
        this.p1.retirer(statue);
    }
    
    
    @Test
    public void test_sortir() throws VivantAbsentDeLaPieceException{
        this.p1.sortir(v1); 
        assertThat(this.p1.contientVivant(v1),equalTo(false)); 
        assertThat(this.v1.getPiece(), equalTo(null)); 
    }


    @Test(expected = VivantAbsentDeLaPieceException.class)
    public void test_VivantAbsentDeLaPieceException_Nom() throws VivantAbsentDeLaPieceException{
        this.p2.sortir(this.v2.getNom()); 
    }

    @Test(expected = VivantAbsentDeLaPieceException.class)
    public void test_VivantAbsentDeLaPieceException_Vivant() throws VivantAbsentDeLaPieceException{
        this.p2.sortir(this.v2); 

    }

    @Test
    public void test_addPorte(){
        this.p2.addPorte(porteB);
        assertThat(this.p2.getPortes().toArray(),equalTo(this.p2.getPortes().toArray())); //Contain in any order
    }
    @Test
    public void test_aLaPorte(){ 
        assertThat(this.p1.aLaPorte(porteA),equalTo(true)); 
        assertThat(this.p1.aLaPorte(porteB), equalTo(false)); 
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
    @Override
	public void executer() throws ITIAventureException {
		throw new UnsupportedOperationException("Unimplemented method 'executer'");
	}
}


class ObjetNonDeplacable extends Objet {

    public ObjetNonDeplacable(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
	    super(nom,monde);
    }

    public boolean estDeplacable(){
        return false;
    }


}