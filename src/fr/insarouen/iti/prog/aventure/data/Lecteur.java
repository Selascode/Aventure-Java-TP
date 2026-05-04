package fr.insarouen.iti.prog.aventure.data;

import java.io.IOException;
import java.util.Collection;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;
public interface Lecteur {
    public abstract Monde getMonde() throws IOException, ClassNotFoundException;

    public abstract Collection<ConditionDeFin> getConditionsDeFin()throws IOException, ClassNotFoundException; 
}