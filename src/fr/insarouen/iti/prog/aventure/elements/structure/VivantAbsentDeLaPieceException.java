package fr.insarouen.iti.prog.aventure.elements.structure;
import fr.insarouen.iti.prog.aventure.elements.structure.PieceException;
public class VivantAbsentDeLaPieceException extends PieceException{
    public VivantAbsentDeLaPieceException(String message){
        super(message); 
    }

    public VivantAbsentDeLaPieceException(String message, Throwable cause){
        super(message,cause);
    }
}