package fr.insarouen.iti.prog.aventure.data;
<<<<<<< HEAD
import fr.insarouen.iti.prog.aventure.Monde;

public interface Lecteur{
    public abstract Monde getMonde();
=======

import java.io.IOException;

import fr.insarouen.iti.prog.aventure.Monde;

public interface Lecteur {
    public Monde getMonde() throws IOException, ClassNotFoundException;

>>>>>>> b66aeb1 (Ajot des cha,gement)
}