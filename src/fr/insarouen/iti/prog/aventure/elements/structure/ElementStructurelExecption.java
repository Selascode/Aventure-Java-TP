package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.ITIAventureException;

public class ElementStructurelExecption extends ITIAventureException{
    public ElementStructurelExecption(String message){
        super(message); 
    }

    public ElementStructurelExecption(String message, Throwable cause){
        super(message,cause);
    }
}