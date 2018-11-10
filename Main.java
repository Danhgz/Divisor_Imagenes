/**
 * Write a description of class Main here.
 *
 * @author Mauricio Delgada finalmente
 * @author Daniel Henao Gómez
 * @author Allan Barrantas
 * 
 * @version Diay, ya ni sé.
 */
public class Main{
    public static void main(String args[]){
       if(args.length == 2){
            Grupo grupo = new Grupo(args[0],Integer.parseInt(args[1]));
            grupo.ejecutar();
       }
        
       // System.exit(0);
    }
}
