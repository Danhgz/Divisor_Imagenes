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
