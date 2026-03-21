package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.elements.structure.PieceException;

public class PorteInexistanteDansLaPieceException extends PieceException{
    public PorteInexistanteDansLaPieceException(String message){
        super(message); 
    }

    public PorteInexistanteDansLaPieceException(String message, Throwable cause){
        super(message,cause);
    }
}