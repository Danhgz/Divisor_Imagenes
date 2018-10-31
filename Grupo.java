public class Grupo{
    
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
