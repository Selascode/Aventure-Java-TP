package fr.insarouen.iti.prog.aventure.data;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.io.File;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Cle;
import fr.insarouen.iti.prog.aventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import fr.insarouen.iti.prog.aventure.elements.vivants.JoueurHumain;
import fr.insarouen.iti.prog.aventure.elements.vivants.Monstre;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;


public class LecteurDescription extends Object implements Lecteur {

    private Monde monde;

    public LecteurDescription(Reader reader) throws IOException, NomDEntiteDejaUtiliseDansLeMondeException , EntiteDejaDansUnAutreMondeException{
        Scanner scanner = new Scanner(reader);

        while (scanner.hasNext()) {
            String typeLigne = scanner.next();
            switch (typeLigne) {
            case "Monde":
                this.lireMonde(scanner);
                break;
            case "Piece":
                this.lirePiece(scanner);
                break;
            case "Porte":
                this.lirePorte(scanner);
                break;
            case "PorteSerrure":
                this.lirePorteSerrure(scanner);
                break;
            case "Cle":
                this.lireCle(scanner);
                break;
            case "JoueurHumain":
                this.lireJoueurHumain(scanner);
                break;
            case "Monstre":
                this.lireMonstre(scanner);
                break;
 }

            
        }
        scanner.close();
    }

    private void lireMonde(Scanner scanner) {
        this.monde = new Monde(scanner.next());
 }

    private void lirePiece(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        new Piece(scanner.next(), this.monde);
 }

    private Piece getPiece(String nomPiece) {
        return (Piece) this.monde.getEntite(nomPiece);
 }

    private Porte getPorte(String nomPorte) {
        return (Porte) this.monde.getEntite(nomPorte);
 }

    private void lirePorte(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException , EntiteDejaDansUnAutreMondeException {
        String nomPorte = scanner.next();
        Piece pieceA = this.getPiece(scanner.next());
        Piece pieceB = this.getPiece(scanner.next());
        new Porte(nomPorte, this.monde, pieceA, pieceB);
 }

    private void lirePorteSerrure(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException , EntiteDejaDansUnAutreMondeException {
        String nomPorte = scanner.next();
        Piece pieceA = this.getPiece(scanner.next());
        Piece pieceB = this.getPiece(scanner.next());
        Serrure serrure = new Serrure(this.monde);
        new Porte(nomPorte, this.monde, serrure, pieceA, pieceB);
 }

    private void lireCle(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nomPorte = scanner.next();
        String nomPiece = scanner.next();
        Porte porte = this.getPorte(nomPorte);
        Cle cle = porte.getSerrure().creerCle();
        this.getPiece(nomPiece).deposer(cle);
 }

    private void lireJoueurHumain(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = scanner.next();
        int pointsVie = Integer.parseInt(scanner.next());
        int pointsForce = Integer.parseInt(scanner.next());
        Piece piece = this.getPiece(scanner.next());
        new JoueurHumain(nom, this.monde, pointsVie, pointsForce, piece);
 }

    private void lireMonstre(Scanner scanner) throws NomDEntiteDejaUtiliseDansLeMondeException {
        String nom = scanner.next();
        int pointsVie = Integer.parseInt(scanner.next());
        int pointsForce = Integer.parseInt(scanner.next());
        Piece piece = this.getPiece(scanner.next());
        new Monstre(nom, this.monde, pointsVie, pointsForce, piece);
 }

    public Monde getMonde() {
        return this.monde;
 }

}
