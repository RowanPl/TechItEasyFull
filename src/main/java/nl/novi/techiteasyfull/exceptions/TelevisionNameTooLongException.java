package nl.novi.techiteasyfull.exceptions;

public class TelevisionNameTooLongException extends RuntimeException{
    public TelevisionNameTooLongException (String message){
        super(message);
    }

    public TelevisionNameTooLongException(){
        super();
    }
}
