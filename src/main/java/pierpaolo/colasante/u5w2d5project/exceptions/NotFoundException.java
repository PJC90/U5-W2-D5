package pierpaolo.colasante.u5w2d5project.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id){super("Elemento con id " + id + " non trovato!!!");}
}
