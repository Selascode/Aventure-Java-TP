package fr.insarouen.iti.prog.aventure.elements;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.module.ModuleFinder;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.MondeException;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;

public class TestEntite{
    private Entite ent;
    private Monde monde;
    private Monde monde2;
    
    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
        this.monde = new Monde("Géraldine"); 
        this.monde2 = new Monde("Nico"); 
        this.ent = new EntiteC("Tableau",monde);
    }

    @Test
    public void test_not_equals() throws NomDEntiteDejaUtiliseDansLeMondeException{
        Entite ent2 = new EntiteC("Cubana",monde);
        assertThat(this.ent.equals(ent) , equalTo(true));
        assertThat(this.ent.getNom() , not(equalTo("Tablau")));
    }

    @Test
    public void test_getter_nom(){
        assertThat(this.ent.getNom() , equalTo("Tableau"));
        assertThat(this.ent.getNom() , not(equalTo("Tablau")));
    }
    
    @Test
    public void test_getter_monde_(){
        Monde monde2 = new Monde("Pablo");
        assertThat(this.ent.getMonde() , equalTo(this.monde));
        assertThat(this.ent.getMonde() , not(equalTo(monde2)));
    }

    
    @Test
    public void test_equals() throws NomDEntiteDejaUtiliseDansLeMondeException{
        Entite ent2 = new EntiteC("Cubana",monde);
        assertThat(this.ent.equals(this.ent) , equalTo(true));
        assertThat(this.ent.equals(ent2) , equalTo(false));
    }


    @Test (expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void test_NomDEntiteDejaUtiliseDansLeMondeException() throws NomDEntiteDejaUtiliseDansLeMondeException{
        Entite ent2 = new EntiteC("Tableau",monde);
    }

    @Test (expected = EntiteDejaDansUnAutreMondeException.class)
    public void test_EntiteDejaDansUnAutreMondeException() throws NomDEntiteDejaUtiliseDansLeMondeException,EntiteDejaDansUnAutreMondeException{
        this.monde2.ajouter(this.ent);
    }
}

class EntiteC extends Entite {

    public EntiteC(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
	super(nom,monde);
    }


}