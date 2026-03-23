package fr.insarouen.iti.prog.aventure;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.*;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.ITIAventureException;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.MondeException;


public class TestMonde{
    private Monde m1;
    private Monde m2;
    private Entite e1;
    private Entite e2;
    private Entite e3;


    @Before
    public void avant() throws MondeException{
        m1 = new Monde("monde1");
        m2 = new Monde("monde2");
        e1 = new EntiteC("Nicolas", m1);
        e2 = new EntiteC("Michel", m1);
        e3 = new EntiteC("Delestre", m2);
    }

    @Test
    public void test_getNom(){
        assertThat(m1.getNom(), equalTo("monde1"));
        assertThat(m1.getNom(), not(equalTo("monde2")));
    }


    @Test
    public void test_getEntite(){
        assertThat(m1.getEntite("Nicolas"), equalTo(e1));
        assertThat(m1.getEntite("Michel"), not(equalTo(e1)));
    }


    @Test
    public void test_getEntites() {
        Collection<Entite> lesEnt = m1.getEntites();
        assertThat(lesEnt, hasItems(e1,e2));
    }

    @Test
    public void test_equals() throws MatchException{
        assertThat(this.m1.equals(m1) , equalTo(true));
        assertThat(this.m1.equals(m2) , equalTo(false));
    }



    @Test(expected = MondeException.class)
    public void test_ajouterEntite_exceptionEntiteDans2Mondes() throws MondeException{
        m1.ajouter(e3);
       
    }

    @Test(expected = MondeException.class)
    public void test_ajouterEntite_exceptionEntiteDejaPresente() throws MondeException{
        m1.ajouter(e2);
    }


    @Test
    public void test_ajouterEntite() throws MondeException{
        assertThat(m2.getEntite("Marwan"), equalTo(null));
        Entite e4 = new EntiteC("Marwan", m2);
        assertThat(m2.getEntite("Marwan"), equalTo(e4));
        assertThat(m1.getEntite("Marwan"), not(equalTo(e4)));
    }

    @Test
    public void test_genererNom()  {
        String ale = m1.genererNom("Marwan");
        assertThat(m1.genererNom("Marwan"),  not(equalTo(ale)));
        
    }
 
// Affichage des toStrings 
}


class EntiteC extends Entite {
    public EntiteC(String nom, Monde monde) throws MondeException {
        super(nom, monde);
    }
}

