package fr.insarouen.iti.prog.aventure.data;

import java.util.Collection;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;

public interface Enregistreur{
    public abstract void enregistrer(Monde monde, Collection<ConditionDeFin> conditionsDeFin) throws Throwable;
}