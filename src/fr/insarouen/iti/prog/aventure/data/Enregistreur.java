package fr.insarouen.iti.prog.aventure.data;
import fr.insarouen.iti.prog.aventure.Monde;
import java.lang.Throwable;

public interface Enregistreur{
    public abstract void enregistrer(Monde monde) throws Throwable;
}