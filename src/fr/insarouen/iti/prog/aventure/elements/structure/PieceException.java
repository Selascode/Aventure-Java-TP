package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.elements.structure.ElementStructurelExecption;
public class PieceException extends ElementStructurelExecption{
    public PieceException(String message){
        super(message); 
    }

    public PieceException(String message, Throwable cause){
        super(message,cause);
    }
}