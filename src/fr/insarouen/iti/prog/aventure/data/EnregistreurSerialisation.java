package fr.insarouen.iti.prog.aventure.data;
import fr.insarouen.iti.prog.aventure.Monde;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.concurrent.locks.Condition;

import fr.insarouen.iti.prog.aventure.conditions.ConditionDeFin;

public class EnregistreurSerialisation  implements Enregistreur{
    private ObjectOutputStream oos; 
    
    public EnregistreurSerialisation(ObjectOutputStream oos) {
        this.oos = oos; 
    }
    @Override
    public void enregistrer(Monde monde, Collection<ConditionDeFin> conditionsDeFin)throws IOException{
        this.oos.writeObject(monde);
        this.oos.writeObject(conditionsDeFin);;
    }

    
    
}
