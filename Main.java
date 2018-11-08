/**
 * Write a description of class Main here.
 *
 * @author Mauricio Delgado
 * @author Daniel Henao Gómez
 * @author Allan Barrantas
 * 
 * @version Diay, ya ni sé.
 */
public class Main{
    public static void main(String args[]){
         Imagen i = new Imagen("dibujo.gif");
      String entrada = "dibujo.gif";
      if(args.length > 0){
         entrada = args[0];  
      }
      Imagen img1 = new Imagen(entrada);
  
      i.dibujar();
      
      int m[][] = i.getMatriz();

      for(int f = 0; f < m.length; f++){
         for(int c = 0; c < m[f].length; c++){
            System.out.print(" "+m[f][c]); 
         } 
         System.out.println("");
      }
        
        //System.exit(0);
    }
}
