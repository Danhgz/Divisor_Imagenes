public class Tablero {
    public static final Ficha FICHA_NEGRA = new Ficha(Ficha.NEGRA);
    public static final Ficha FICHA_ROJA = new Ficha(Ficha.ROJA);
    public static final String nombreDireccion[]={"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"};
    public static final int sumaF[]={-1,-1,0,1,1,1,0,-1};
    public static final int sumaC[]={0,-1,-1,-1,0,1,1,1};    
    
    private Ficha [][] m;
    private int tope[];
    
    public Tablero(){
       inicializar(6,7);
    }
    
    public Tablero(int filas, int columnas){
       inicializar(filas,columnas);
    }
    private void inicializar(int filas, int columnas){
      m = new Ficha [filas][columnas]; 
      tope = new int [columnas];
      for(int i=0; i < columnas; ++i){
          tope[i]=filas-1;
      }
    }
 
    private boolean posicionValida(int f, int c){
       return m!=null && f >=0 && f < m.length && c >= 0 && c < m[f].length;
    }
    
    private boolean posicionVacia(int f, int c){
       return posicionValida(f,c) && m[f][c]==null;
    }
    
    public boolean puedoPoner(int columna){
       return columna >=0 && columna < m[0].length && tope[columna] > 0;
    }
    
    public void ponerFicha(int columna, Ficha ficha){
       --columna;
       if(puedoPoner(columna)){
          m[tope[columna]--][columna] = new Ficha(ficha); // Poner una copia de la ficha
       }
    }
    
    private boolean cuatroIguales(Ficha ficha, int fila, int columna){
       boolean hay = false;
       for(int d = 0; !hay && d < nombreDireccion.length; ++d){
          // Se mueve hacia nombreDireccion[d] 
          hay = cuatroIguales(ficha, fila, columna, 1 , d ); // evalua las 8 direcciones posibles
       }
       return hay;
    }
    private boolean cuatroIguales(Ficha ficha, int fila, int columna,int cantidad,int direccion){
        boolean hay = false;
        if(posicionValida(fila,columna)&&!posicionVacia(fila,columna)){
           if(ficha.equals(m[fila][columna])){
              if(cantidad==4){
                 hay = true; 
              }
              else {
                 hay = cuatroIguales(ficha, fila+sumaF[direccion], columna + sumaC[direccion], cantidad + 1,direccion);  
              }
           }  
        }
        return hay;
    }

    
    public String toStringDelTablero(){
      String tira="";
      for(int c=0; c < m[0].length;++c){
          tira+=" "+(c+1)+" ";
      }
      tira+="\n";
      // for(int c=0; c < m[0].length;++c){
          // tira+=" | ";
      // }
      // tira+="\n";
      for(int c=0; c < m[0].length;++c){
          tira+="---";
      }
      tira+="\n";
      
      for(int f=0; f < m.length; ++f){
         for(int c=0; c < m[f].length; ++c){
             tira+=""+ ((m[f][c]==null)?"( )":"("+m[f][c]+")");
         }  
         tira+="\n";
      } 
 
      return tira;
    }

    public boolean tableroLleno(){
      boolean hayCampo = false;
      for(int i=0; !hayCampo && i<tope.length; ++i){
          hayCampo = tope[i] >= 0 ;
      }
      return !hayCampo;
    }    
    
    public boolean gana(Ficha ficha){
      boolean gano = false;
      for(int f=0; !gano && f < m.length; ++f){
         for(int c=0; !gano && c < m[f].length; ++c){
              gano = cuatroIguales(ficha,f,c);
         }  
      }       
      return gano;
    }
    
    public boolean fin(){
       return tableroLleno()|| gana(FICHA_NEGRA) || gana(FICHA_ROJA) ;
    }
    
    public String toString(){
       String estado = toStringDelTablero();
       if(gana(FICHA_NEGRA)){
           estado+="\nGana la ficha negra";
       }
       else {
          if(gana(FICHA_ROJA)){
              estado+="\nGana la ficha roja";
          }
          else {
             if(tableroLleno()){
                 estado+="\nNadie gana";
             }  
          }
       }
       return estado;
    }
}
