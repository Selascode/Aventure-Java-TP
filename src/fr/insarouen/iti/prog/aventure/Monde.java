package fr.insarouen.iti.prog.aventure;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.iti.prog.aventure.EntiteDejaDansUnAutreMondeException;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import java.lang.Math;

/**
 * Classe représentant le monde du jeu d'aventure.
 * <p>
 * Le Monde agit comme un conteneur global. Il possède un nom et maintient
 * la liste de toutes les {@link Entite}s (objets, vivants, lieux) créées
 * dans le jeu. C'est via cette classe que l'on peut retrouver une entité
 * à partir de son nom.
 * </p>
 */
public class Monde{
    /** Le nom du monde. */
    private String nom;
    /** Tableau stockant toutes les entités présentes dans ce monde. */
    /**private Entite[] tabEntite = new Entite[0];*/
    //private List<Entite> tabEntite = new ArrayList<Entite>();   
    private Map<String,Entite> tabEntite = new HashMap<String,Entite>();

    /**
     * Construit un nouveau Monde.
     *
     * @param nom Le nom du monde (par exemple "Terre du Milieu").
     */
    public Monde(String nom){
        this.nom = nom;
    }
    /**
     * Obtient le nom du monde.
     *
     * @return Le nom du monde.
     */
    public String getNom(){
        return this.nom;
    }
    /**
     * Récupère le tableau contenant toutes les entités du monde.
     *
     * @return Un tableau d'{@link Entite}.
     */
    /**public Entite[] getEntites(){
        return this.tabEntite.values().toArray(new Entite[this.tabEntite.size()]);
    }*/
    public Collection<Entite> getEntites(){
        // Modifier valeus par un tanbleau de copy 

        return Collections.unmodifiableCollection(this.tabEntite.values());
    }
    /**
     * Recherche une entité dans le monde par son nom.
     *
     * @param nomEntite Le nom de l'entité à rechercher.
     * @return L'entité correspondante si elle est trouvée.
     * @throws ArrayIndexOutOfBoundsException (Dans la version actuelle) si l'entité n'est pas trouvée.
     */
    public Entite getEntite(String nomEntite){
        /**int n = this.tabEntite.length;
        boolean res = false;
        int i = 0;
        while(!res && i<n){
            if (this.tabEntite[i].getNom() == nomEntite){
                res = true;
                return this.tabEntite[i];
            }
            i++;
        }
        return null; */
        for(Entite e : this.tabEntite.values())
            if (e.getNom() == nomEntite){
                return e;
            }
            return null;
    }
    /**
     * Ajoute une nouvelle entité au monde.
     * <p>
     * Cette méthode redimensionne le tableau interne pour y stocker la nouvelle entité.
     * Elle est généralement appelée automatiquement par le constructeur de la classe {@link Entite}.
     * </p>
     *
     * @param entite L'entité à ajouter.
     */
    public void ajouter(Entite entite) throws NomDEntiteDejaUtiliseDansLeMondeException , EntiteDejaDansUnAutreMondeException{
    
        if (this.getEntite(entite.getNom()) != null) {
            throw new NomDEntiteDejaUtiliseDansLeMondeException(
                String.format("L'entité %s est déjà utilisée dans le monde %s", entite.getNom(), this.getNom())
            );
        }
 
        if (entite.getMonde() != null && !entite.getMonde().equals(this)) {
            throw new EntiteDejaDansUnAutreMondeException(
                 String.format("L'entité ( %s ) est déjà dans UN AUTRE MONDE : %s ",entite.getNom(), entite.getMonde().getNom())
            );
        }

        /**Entite[] newTab = new Entite[this.tabEntite.length+1]; 
        for (int i = 0; i<this.tabEntite.length ; i++){
            newTab[i] = this.tabEntite[i]; 
        }
        newTab[newTab.length-1] = entite;
        this.tabEntite = newTab;*/
        //this.tabEntite.add(entite);
        this.tabEntite.put(entite.getNom(), entite);
    }

    public String genererNom(String prefixe){
       StringBuilder sb = new StringBuilder();
        do{
            sb.delete(0,sb.length());
            long ale = Math.round(Math.random() *1000);
            sb.append(prefixe);
            sb.append(ale);

        }
        while(getEntite(sb.toString()) != null) ;
        return sb.toString();
    }
    

    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Monde: %s", this.getNom()));
        sb.append(" ( Entités : ");
        sb.append(Arrays.toString(this.tabEntite.values().toArray(new Entite[this.tabEntite.size()])));
        sb.append(" )");
        return sb.toString();
 
 
    }



}