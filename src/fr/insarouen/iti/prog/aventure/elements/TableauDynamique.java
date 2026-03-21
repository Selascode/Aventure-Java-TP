package fr.insarouen.iti.prog.aventure.elements; 
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import java.lang.Object;
/**
 * Classe représentant une collection dynamique d'{@link Entite}s.
 * <p>
 * Cette classe gère un tableau d'entités qui se redimensionne automatiquement
 * lors des ajouts et des retraits. Elle permet de stocker indifféremment des
 * objets ou des vivants et propose des méthodes pour les rechercher ou les filtrer.
 * </p>
 */
public class TableauDynamique extends Object{
    /** Le tableau interne stockant les entités. */
    private Entite[] tabEnt = new Entite[0];

    /**
     * Ajoute une entité à la collection.
     * <p>
     * Cette méthode agrandit le tableau interne d'une case et place la nouvelle
     * entité à la fin.
     * </p>
     *
     * @param o L'entité à ajouter.
     */
    public void ajouter(Entite o){
        Entite[] newTab = new Entite[this.tabEnt.length+1]; 
        
        for (int i=0; i <this.tabEnt.length; i++){
            newTab[i] = this.tabEnt[i]; 
            
        }
        newTab[newTab.length-1] = o; 
        this.tabEnt = newTab; 
    }

    /**
     * Vérifie si une entité spécifique est présente dans la collection.
     * <p>
     * La comparaison s'effectue par référence (opérateur {@code ==}).
     * </p>
     *
     * @param o L'entité à rechercher.
     * @return {@code true} si l'entité est trouvée, {@code false} sinon.
     */
    public boolean contient(Entite o) {
        for (int i = 0; i < this.tabEnt.length; i++) {
            if (this.tabEnt[i] == o) {
                return true;  
            }
        }
        return false; 
    }
/**
     * Récupère l'entité située à un index donné.
     *
     * @param i L'index de l'élément à récupérer.
     * @return L'entité à cet index.
     * @throws ArrayIndexOutOfBoundsException si l'index est invalide.
     */
    public Entite get(int i){
        return this.tabEnt[i];
    }
    /**
     * Récupère la première entité trouvée portant un nom donné.
     *
     * @param nom Le nom de l'entité recherchée.
     * @return L'entité correspondante si elle est trouvée, {@code null} sinon.
     */
    public Entite get(String nom){
        int i = 0;
        boolean trouve=false;

        while(i<this.taille()&& !trouve){ 
            if(this.tabEnt[i].getNom().equals(nom)){
                return this.tabEnt[i];
            }
            i++; 
        }
        return null;
    }


/**
     * Vérifie si une entité portant un certain nom est présente.
     *
     * @param nom Le nom de l'entité à rechercher.
     * @return {@code true} si une entité avec ce nom existe, {@code false} sinon.
     */
    public boolean contient(String nom){
        for (int i = 0; i < this.tabEnt.length; i++ ){
            if (this.tabEnt[i].getNom() == nom){
                return true; 
            }
        }
        return false; 
    }


public Entite retirer(Entite o) {
    
    if (this.contient(o)){
        int nb = 0;
        // Compter les éléments à conserver
        for (int i = 0; i < this.taille(); i++) {
            if (this.tabEnt[i] != o) {
                nb++;
            }
        }

        Entite[] newTabEnt = new Entite[nb];
        int j = 0;

        for (int i = 0; i < this.taille(); i++) {
            if (this.tabEnt[i] != o) {
                newTabEnt[j] = this.tabEnt[i];
                j++;
            }
        }

        this.tabEnt = newTabEnt; 
        return o;
    } else {
        return null; 
    }
    
   
}

/**
     * Retire une entité de la collection en utilisant son nom.
     * <p>
     * Si plusieurs entités portent le même nom, elles seront toutes retirées.
     * </p>
     *
     * @param nom Le nom de l'entité à retirer.
     * @return La dernière entité retirée ayant ce nom, ou {@code null} si aucune n'a été trouvée.
     */
public Entite retirer(String nom) {
    int nb = 0;
    Entite ent = null; 

    // Compter l'élément à conserver 
    for (int i = 0; i < this.taille(); i++) {
        if (!this.tabEnt[i].getNom().equals(nom)) {
            nb++;
        }else{
            ent = this.tabEnt[i];
        }
    }
    if (ent == null) return null; 

    Entite[] tabEntR = new Entite[nb];
    int j = 0;

    for (int i = 0; i < this.taille(); i++) {
        if (!this.tabEnt[i].getNom().equals(nom)) {
            tabEntR[j] = this.tabEnt[i];
            j++;
        }else{
            ent = this.tabEnt[i];
        }
    }

    this.tabEnt = tabEntR;
    return ent;
}


    /**
     * Extrait et renvoie un sous-ensemble ne contenant que les vivants.
     * <p>
     * Cette méthode filtre le tableau courant pour ne garder que les instances
     * de la classe {@link Vivant} (ou ses sous-classes).
     * </p>
     *
     * @return Un tableau d'{@link Vivant}.
     */
    public Vivant[] getTabVivants(){
        int nbV = 0; 
        for( Entite e : this.tabEnt) {
            if(e instanceof Vivant){
                nbV++; 
            }
        }
        
        Vivant[] tabV = new Vivant[nbV]; 
        int j = 0; 
        for (int i = 0; i<this.tabEnt.length; i++){
            if (this.tabEnt[i] instanceof Vivant){
                tabV[j] = (Vivant)this.tabEnt[i];
                j++;
            }
        }
        return tabV;
    }

/**
     * Extrait et renvoie un sous-ensemble ne contenant que les objets.
     * <p>
     * Cette méthode filtre le tableau courant pour ne garder que les instances
     * de la classe {@link Objet} (ou ses sous-classes).
     * </p>
     *
     * @return Un tableau d'{@link Objet}.
     */
    public Objet[] getTabObjets(){
        int nbObjets = 0; 
        for( Entite e : this.tabEnt) {
            if(e instanceof Objet){
                nbObjets++; 
            }
        }
        
        Objet[] tabObj = new Objet[nbObjets]; 
        int j = 0; 
        for (int i = 0; i<this.tabEnt.length; i++){
            if (this.tabEnt[i] instanceof Objet){
                tabObj[j] = (Objet)this.tabEnt[i];
                j++;
            }
        }
        return tabObj;
    }
/**
     * Remplace l'entité à un index donné.
     *
     * @param i L'index de l'élément à remplacer.
     * @param o La nouvelle entité.
     */
    public void set(int i,Entite o){
        this.tabEnt[i] = o;
    }
/**
     * Renvoie le nombre d'éléments dans la collection.
     *
     * @return La taille du tableau.
     */
    public int taille(){
        return this.tabEnt.length;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0 ; i < this.taille(); i++){
            Entite ent = this.tabEnt[i]; 

            if(ent == null){
                sb.append(" null "); 
            }else{
                sb.append(ent.toString());
            }

            if (i < this.taille() - 1) {
            sb.append(", ");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }





}