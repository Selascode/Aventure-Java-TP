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
import fr.insarouen.iti.prog.aventure.elements.ActivationException;
import fr.insarouen.iti.prog.aventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.iti.prog.aventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.iti.prog.aventure.elements.structure.Porte;
import java.io.Serializable;
import java.util.*;
import java.lang.Class;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.insarouen.iti.prog.aventure.elements.Executable;

public class JoueurHumain extends Vivant implements Executable{
    private static final Scanner scanner = new Scanner(System.in);
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

    public void executer() throws ITIAventureException{
        if (this.ordre == null || this.ordre.isBlank()) {
            System.out.print("Entrez la commande du joueur : ");
            this.ordre = scanner.nextLine().trim();
        }
        String commande = this.getCommande();
        Object[] parametresEffectifs = this.getParametres();
        Class<String>[] typeParametresFormels = this.getTypesParametresFormels(parametresEffectifs);
        //Paramètres Class
        try {
            Method methode = getClass().getDeclaredMethod("commande"+commande, typeParametresFormels);
            methode.invoke(this, parametresEffectifs);
            this.ordre = null; // Reset ordre après exécution
        } catch (InvocationTargetException e){
            Throwable cause = e.getCause();
            if (cause instanceof ITIAventureException) {
                throw (ITIAventureException) cause;
            } else {
                throw new CommandeImpossiblePourLeVivantException("Erreur lors de l'exécution de la commande: " + cause.getMessage(), cause);
            }
        } catch (Exception e) {
            throw new CommandeImpossiblePourLeVivantException(String.format("Impossible d'effectuer la commande: %s", e.getMessage()), e); 

        }
    
    }

    public String getCommande() throws CommandeImpossiblePourLeVivantException {
        if (this.ordre == null || this.ordre.isBlank()) {
            throw new CommandeImpossiblePourLeVivantException("Aucune commande fournie");
        }
        String cmd = this.ordre.trim().split("\\s+")[0];
        return cmd.substring(0,1).toUpperCase() + cmd.substring(1).toLowerCase();
    }
    public Object[] getParametres() throws CommandeImpossiblePourLeVivantException {
        if (this.ordre == null || this.ordre.isBlank()) {
            throw new CommandeImpossiblePourLeVivantException("Aucune commande fournie");
        }
        return Arrays.stream(this.ordre.trim().split("\\s+")).skip(1).filter(s -> !s.equalsIgnoreCase("avec")).toArray(String[]::new);
        
    }
    @SuppressWarnings("unchecked")
    private Class<String>[] getTypesParametresFormels(Object[] parametres){
        Class<String>[] parametresFormels = (Class<String>[]) new Class[parametres.length]; 
        Arrays.fill(parametresFormels, String.class); 
        return parametresFormels; 
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
        Porte porte = this.piece.getPorte(nomPorte);
        if (porte == null) {
            throw new PorteInexistanteDansLaPieceException(String.format("La porte %s n'existe pas dans la pièce %s", nomPorte, this.piece.getNom()));
        }
        porte.activer();
    }
    private void commandeOuvrirPorte(String nomPorte, String nomObjet) throws ActivationException, PorteInexistanteDansLaPieceException,ObjetNonPossedeParLeVivantException{
        Porte porte = this.piece.getPorte(nomPorte);
        if (porte == null) {
            throw new PorteInexistanteDansLaPieceException(String.format("La porte %s n'existe pas dans la pièce %s", nomPorte, this.piece.getNom()));
        }
        porte.activerAvec(this.getObjet(nomObjet));
    }

    
}