package fr.insarouen.iti.prog.aventure.data;
import fr.insarouen.iti.prog.aventure.Monde;

import java.io.IOException;
import java.io.ObjectOutputStream;; 

public class EnregistreurSerialisation  implements Enregistreur{
    private ObjectOutputStream oos; 
    
    public EnregistreurSerialisation(ObjectOutputStream oos) {
        this.oos = oos; 
    }

    public void enregistrer(Monde monde)throws IOException{
        this.oos.writeObject(monde);

    }

    
    
}
