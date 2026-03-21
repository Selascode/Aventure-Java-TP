package fr.insarouen.iti.prog.aventure.elements;

import fr.insarouen.iti.prog.aventure.elements.Entite;
import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.elements.structure.Piece;
import fr.insarouen.iti.prog.aventure.elements.vivants.Vivant;
import fr.insarouen.iti.prog.aventure.elements.TableauDynamique;
import fr.insarouen.iti.prog.aventure.elements.objets.PiedDeBiche;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import java.util.Arrays; // Ajouté pour l'affichage facile des tableaux

public class ClassMainTestTableau {
    public static void main(String[] args){
/** 
        // --- Initialisation ---
        TableauDynamique tabE = new TableauDynamique();
        TableauDynamique tabMixte = new TableauDynamique(); 
        Monde monde = new Monde("Géraldine"); 
        
        // Création des entités
        Entite ent1 = new EntiteC("Neptune", monde);
        Entite ent2 = new EntiteC("Jupiter", monde);
        Entite ent3 = new EntiteC("Pluton", monde);

        // Création des objets et vivants
        Objet obj1 = new ObjetC("Coffre", monde);
        Piece p1 = new Piece("Piece1", monde);
        PiedDeBiche pdb1 = new PiedDeBiche("PiedDeBiche", monde);
        Vivant v1 = new VivantC("Étudiant", monde, 10, 5, p1);

        // --- Test 1 : AJOUTER & TOSTRING ---
        System.out.println("== Test Ajouter & toString ==");
        tabE.ajouter(ent1);
        tabE.ajouter(ent2);
        tabE.ajouter(ent3);
        System.out.println("Tableau E (attendu: Neptune, Jupiter, Pluton) :");
        System.out.println(tabE.toString());

        // --- Test 2 : CONTIENT (Entite et String) ---
        System.out.println("\n== Test Contient ==");
        System.out.println("Contient ent1 (Neptune) ? " + tabE.contient(ent1)); // true
        System.out.println("Contient 'Jupiter' ? " + tabE.contient("Jupiter")); // true
        System.out.println("Contient 'Mars' (inexistant) ? " + tabE.contient("Mars")); // false

        // --- Test 3 : GET (Index et String) ---
        System.out.println("\n== Test Get ==");
        System.out.println("Get index 0 (Neptune) : " + tabE.get(0).getNom());
        Entite recup = tabE.get("Pluton");
        System.out.println("Get nom 'Pluton' : " + (recup != null ? recup.getNom() : "null"));
        System.out.println("Get nom 'Inconnu' : " + tabE.get("Inconnu")); // null

        // --- Test 4 : RETIRER (Entite et String) ---
        System.out.println("\n== Test Retirer ==");
        System.out.println("Taille avant retrait : " + tabE.taille());
        
        tabE.retirer(ent1); // Retrait par objet
        System.out.println("Apres retrait ent1 (Neptune) : " + tabE.toString());
        
        tabE.retirer("Pluton"); // Retrait par nom
        System.out.println("Apres retrait 'Pluton' : " + tabE.toString());
        
        System.out.println("Taille finale (attendu 1) : " + tabE.taille());

        // --- Test 5 : FILTRAGE (getTabObjets / getTabVivants) ---
        // 
        System.out.println("\n== Test Filtrage (Objets vs Vivants) ==");
        
        // On remplit un tableau mixte
        tabMixte.ajouter(obj1); // Objet
        tabMixte.ajouter(v1);   // Vivant
        tabMixte.ajouter(pdb1); // Objet (PiedDeBiche)
        tabMixte.ajouter(ent2); // Entite simple (ni objet ni vivant)

        System.out.println("Contenu mixte : " + tabMixte.toString());

        // Test getTabObjets
        Objet[] lesObjets = tabMixte.getTabObjets();
        System.out.println("Tableau filtré Objets (attendu: Coffre, PiedDeBiche) : " + Arrays.toString(lesObjets));
        System.out.println("Nb Objets trouvés : " + lesObjets.length);

        // Test getTabVivants
        Vivant[] lesVivants = tabMixte.getTabVivants();
        System.out.println("Tableau filtré Vivants (attendu: Étudiant) : " + Arrays.toString(lesVivants));
        System.out.println("Nb Vivants trouvés : " + lesVivants.length);*/
    }

}

// --- Classes utilitaires pour le test ---
/*
class EntiteC extends Entite {
    public EntiteC(String nom, Monde monde){
        super(nom, monde);
    }
}

class ObjetC extends Objet {
    public ObjetC(String nom, Monde monde){
        super(nom, monde);
    }
    public boolean estDeplacable(){
        return true;
    }
}

class VivantC extends Vivant {
    public VivantC(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objets) {
        super(nom, monde, pointVie, pointForce, piece, objets);
    }
}

*/