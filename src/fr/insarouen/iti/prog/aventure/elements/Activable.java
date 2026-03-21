package fr.insarouen.iti.prog.aventure.elements;
import fr.insarouen.iti.prog.aventure.elements.objets.Objet;
import fr.insarouen.iti.prog.aventure.elements.ActivationException;


public interface Activable {

public abstract boolean activableAvec(Objet o);
public abstract void activer() throws ActivationException;
public abstract void activerAvec(Objet obj) throws ActivationException;

}