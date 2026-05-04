package fr.insarouen.iti.prog.aventure.elements.conditions;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFinVivantDansPieceEtPossedeObjets;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.vivants.Monstre; 
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.Etat;
import fr.insarouen.iti.prog.aventure.conditions.EtatDuJeu;
public class TestConditionDeFinVivantDansPieceEtPossedeObjets {

    private Monde monde;
    private Piece pieceRequise;
    private Piece pieceMauvaise;
    private Monstre vivant;
    private PiedDeBiche objet1;
    private PiedDeBiche objet2;
    private ConditionDeFin condition;

    @Before
    public void avantChaqueTest() throws Exception {
        monde = new Monde("MondeTest");
        
        // On crée deux pièces pour vérifier la position
        pieceRequise = new Piece("PieceRequise", monde);
        pieceMauvaise = new Piece("PieceMauvaise", monde);

        // On crée les objets que le vivant devra posséder
        objet1 = new PiedDeBiche("Objet1", monde);
        objet2 = new PiedDeBiche("Objet2", monde);

        // Le vivant commence dans la mauvaise pièce et les poches vides
        vivant = new Monstre("MonstreTest", monde, 10, 10, pieceMauvaise);

        // On crée la condition : Le vivant doit être dans "pieceRequise" et posséder "objet1" et "objet2"
        // Si c'est validé, le jeu doit retourner EtatDuJeu.SUCCES
        condition = new ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu.SUCCES, vivant, pieceRequise, objet1, objet2);
    }

    @Test
    public void testConditionNonVerifiee_MauvaisePiece_SansObjets() {
        // Scénario 1 : Le vivant est dans pieceMauvaise et n'a aucun objet
        assertThat(condition.verifierCondition(), is(EtatDuJeu.ENCOURS));
    }

    @Test
    public void testConditionNonVerifiee_BonnePiece_SansObjets() throws Exception {
        // Scénario 2 : On déplace le vivant dans la bonne pièce
        vivant.setPiece(pieceRequise);
        
        // Mais il lui manque toujours les objets !
        assertThat(condition.verifierCondition(), is(EtatDuJeu.ENCOURS));
    }

    @Test
    public void testConditionNonVerifiee_MauvaisePiece_AvecObjets() throws Exception {
        // Scénario 3 : Le vivant ramasse les objets
        pieceMauvaise.deposer(objet1);
        pieceMauvaise.deposer(objet2);
        vivant.prendre(objet1);
        vivant.prendre(objet2);

        // Mais il est toujours dans la mauvaise pièce !
        assertThat(condition.verifierCondition(), is(EtatDuJeu.ENCOURS));
    }

    @Test
    public void testConditionVerifiee_BonnePiece_AvecObjets() throws Exception {
        // Scénario 4 : Le vivant est dans la bonne pièce ET possède les objets
        vivant.setPiece(pieceRequise);
        
        pieceRequise.deposer(objet1);
        pieceRequise.deposer(objet2);
        vivant.prendre(objet1);
        vivant.prendre(objet2);

        // Tout est parfait, la condition doit renvoyer SUCCES !
        assertThat(condition.verifierCondition(), is(EtatDuJeu.SUCCES));
    }
}