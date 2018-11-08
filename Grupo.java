public class Grupo
{
    public static final String nombreDireccion[]={"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"};
    public static final int sumaF[]={-1,-1,0,1,1,1,0,-1};
    public static final int sumaC[]={0,-1,-1,-1,0,1,1,1};
    
    Imagen imgPrincipal;
    int cantidadDeGrupos;
    int[][] m;
    
    public Grupo(String imgEntrada, int cantidadDeGrupos){
        this.cantidadDeGrupos = cantidadDeGrupos;
        imgPrincipal = new Imagen(imgEntrada);
        m = imgPrincipal.getMatriz();
        imgPrincipal.dibujar();
    }
    
    public void 
    
    
    /*
    
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
    }*/
    
    
    
    
    //Cantidad de objetos <= cantidad de grupos.
    private boolean cantidadCorrecta(){
        return true;
    }
    
    
    public void echarPalSaco()
    {
        for(int f = 0; f < m.length; f++){
         for(int c = 0; c < m[f].length; c++){
            System.out.print(" "+m[f][c]); 
         } 
         System.out.println("");
        }
    }
}
