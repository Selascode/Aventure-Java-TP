package fr.insarouen.iti.prog.aventure.elements.structure;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.TableauDynamique;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.Etat;

public class TestPorte{
    private Monde monde;
    private Piece piece11;
    private Piece piece12;
    private Piece piece21;
    private Piece piece22;
    private Porte p1;
    private Porte p2;
    private Porte p3;
    
    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.monde = new Monde("Maladain");
        this.piece11 = new Piece("bureau" , monde);
        this.piece12 = new Piece("salle de bain" , monde);
        this.piece21 = new Piece("salle à manger " , monde);
        this.piece22 = new Piece("cuisine" , monde);
        this.p1 = new Porte("Entre bureau et sdb" , monde , this.piece11 , this.piece12 );
        this.p2 = new Porte("Entre cuisine et sam" , monde , this.piece21 , this.piece22 );
        this.p3 = new Porte("Entre bureau et MA" , monde , piece11 ,null );
        


    }
    
    @Test
    public void test_getEtat_activer() throws ActivationImpossibleException{
        assertThat(this.p1.getEtat() , equalTo(Etat.FERME));
        this.p1.activer();
        assertThat(this.p1.getEtat() , equalTo(Etat.OUVERT));
        this.p1.activer();
        assertThat(this.p1.getEtat() , equalTo(Etat.FERME));

        assertThat(this.p2.getEtat() , equalTo(Etat.FERME));
        
    }

    @Test
    public void test_getPieceAutreCote_non_null(){
        assertThat(this.p1.getPieceAutreCote(this.piece11) , equalTo(this.piece12));
        assertThat(this.p2.getPieceAutreCote(this.piece21) , equalTo(this.piece22));
        assertThat(this.p1.getPieceAutreCote(this.piece12) , equalTo(this.piece11));
        assertThat(this.p2.getPieceAutreCote(this.piece22) , equalTo(this.piece21));

    }

    @Test
    public void test_getPieceAutreCote_null(){
        assertThat(this.p3.getPieceAutreCote(this.piece11) , equalTo(null));
    }


}