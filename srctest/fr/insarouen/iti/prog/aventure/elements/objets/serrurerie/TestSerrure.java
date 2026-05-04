package fr.insarouen.iti.prog.aventure.elements.objets.serrurerie;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.beans.Transient;

import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.iti.prog.aventure.elements.ActivationImpossibleException;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.TableauDynamique;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;

public class TestSerrure {
    private Serrure s1;
    private Serrure s2;
    private Cle c1;
    private Cle c2 = null;
    private Monde m;

    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.m = new Monde("Insa"); 
        this.s1 = new Serrure(this.m); 
        this.s2 = new Serrure("The Serrure",this.m);
        this.c1 = this.s1.creerCle(); 
    }

    @Test
    public void test_creerCle() throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.c2 = this.s1.creerCle(); 
        assertThat(this.c1, not(equalTo(null)));
        assertThat(this.c2, equalTo(null));
    }

    @Test(expected = ActivationImpossibleException.class)
    public void test_activationException() throws ActivationImpossibleException{
        this.s1.activer(); 

    }

    @Test 
    public void test_activationException2(){
       assertThat(this.s1.activableAvec(this.c2), equalTo(false)); 
    }

    @Test(expected = ActivationImpossibleAvecObjetException.class)
    public void test_activationAvecMauvaisObjet() throws ActivationImpossibleAvecObjetException, NomDEntiteDejaUtiliseDansLeMondeException{
        Objet Banane = new ObjetC("banane", this.m); 
        this.s1.activerAvec(Banane); 

    }


    @Test
    public void test_activerAvec() throws ActivationImpossibleAvecObjetException{
        //v3 qui sort de null part
        assertThat(this.s1.getEtat(),equalTo(Etat.VERROUILLE)); 
        this.s1.activerAvec(this.c1); 
        assertThat(this.s1.getEtat(),equalTo(Etat.DEVERROUILLE));
    }



}


class ObjetC extends Objet {
    public ObjetC(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return true; 

    }
}

