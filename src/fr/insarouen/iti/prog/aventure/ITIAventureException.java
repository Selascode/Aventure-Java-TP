package fr.insarouen.iti.prog.aventure;
import java.lang.Exception;

public class ITIAventureException extends Exception{
    public ITIAventureException(String message){
        super(message);
    }
    public ITIAventureException(String message , Throwable cause){
        super(message,cause);
    }
}