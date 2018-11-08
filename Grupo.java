public class Grupo{
    
    public static final String nombreDireccion[]={"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"};
    public static final int sumaF[]={-1,-1,0,1,1,1,0,-1};
    public static final int sumaC[]={0,-1,-1,-1,0,1,1,1};
    
    
    Imagen imgPrincipal;
    int cantidadDeGrupos;
    
    public Grupo(String imgEntrada, int cantidadDeGrupos){
        imgPrincipal = new Imagen(imgEntrada);
        this.cantidadDeGrupos = cantidadDeGrupos;
    }
    
    
    
    //Cantidad de objetos <= cantida de grupos.
    private boolean cantidadCorrecta(){
        return true;
    }

}
