package fr.insarouen.iti.prog.aventure.data;

import java.io.ObjectInputStream;
import java.io.IOException;

import fr.insarouen.iti.prog.aventure.Monde;


public class LecteurSerialisation implements Lecteur {

    private ObjectInputStream ois;

    public Monde getMonde() throws IOException, ClassNotFoundException {
        return (Monde)this.ois.readObject();
    }

    public LecteurSerialisation(ObjectInputStream ois){
        this.ois = ois;
    }

}
