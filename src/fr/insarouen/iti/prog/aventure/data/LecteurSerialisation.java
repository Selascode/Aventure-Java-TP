package fr.insarouen.iti.prog.aventure.data;

import java.io.ObjectInputStream;
import java.util.Collection;
import java.io.IOException;
import java.util.*;

import fr.insarouen.iti.prog.aventure.Monde;
import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;

public class LecteurSerialisation implements Lecteur {

    private ObjectInputStream ois;

    public Monde getMonde() throws IOException, ClassNotFoundException {
        return (Monde)this.ois.readObject();
    }

    

    public LecteurSerialisation(ObjectInputStream ois){
        this.ois = ois;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<ConditionDeFin> getConditionsDeFin() throws IOException, ClassNotFoundException  {
        return (Collection<ConditionDeFin>)this.ois.readObject();
    }

}
