package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.elements.structure.ElementStructurelExecption;

public class PorteFermeException extends ElementStructurelExecption{
    public PorteFermeException(String message){
        super(message); 
    }

    public PorteFermeException(String message, Throwable cause){
        super(message,cause);
    }
}