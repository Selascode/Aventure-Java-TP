package fr.insarouen.iti.prog.aventure.elements.vivants;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestMonstre {

    private Monde monde;
    private Piece piece1;
    private Piece piece2;
    private Porte porte;
    private PiedDeBiche pdbPiece1;
    private PiedDeBiche pdbPiece2;
    private Cle cle;
    private Monstre monstre1;
    private Monstre monstre2;

    
    @Before
    public void avantChaqueTest() throws Exception {
        // Création du monde 
        monde = new Monde("MondeTest");
        piece1 = new Piece("Piece1", monde);
        piece2 = new Piece("Piece2", monde);
        porte = new Porte("Porte", monde, piece1, piece2);

        // Création des objets
        pdbPiece1 = new PiedDeBiche("pdbPiece1", monde);
        Serrure serrure = new Serrure("serrure", monde); 
        cle = serrure.creerCle();
        
        // On pose les objets dans la piece1 pour que le monstre puisse les ramasser
        piece1.deposer(pdbPiece1);
        piece1.deposer(cle);
        // -------------------------------

        pdbPiece2 = new PiedDeBiche("pdbPiece2", monde);
        piece2.deposer(pdbPiece2);

        // Création des monstres
        monstre1 = new Monstre("Monstre1", monde, 10, 10, piece1);
        monstre1.prendre(pdbPiece1); 
        monstre1.prendre(cle);

    
    }

    @Test
    public void testDeplacementAvecObjetsMonstreLocal() throws Exception {
        monstre1.executer();

        assertThat(monstre1.getPiece(), is(piece2));

        assertThat(piece2.contientObjet(pdbPiece1), is(true));
        assertThat(piece2.contientObjet(cle), is(true));

        assertThat(monstre1.possede(pdbPiece2), is(true));
    }
}