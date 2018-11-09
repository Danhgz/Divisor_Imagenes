public class Grupo
{
    private static final String nombreDireccion[]={"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"};
    private static final int sumaF[]={-1,-1,0,1,1,1,0,-1};//"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"
    private static final int sumaC[]={0,-1,-1,-1,0,1,1,1};
    
    private Imagen imgPrincipal;
    private int cantidadDeGrupos;
    private int[][] m;
    private int[][] mCopia;
    private int fondo;
    
    public Grupo(String imgEntrada, int cantidadDeGrupos){
        this.cantidadDeGrupos = cantidadDeGrupos;
        imgPrincipal = new Imagen(imgEntrada);
        m = imgPrincipal.getMatriz();
        fondo= m[0][0];
        mCopia = new int [m.length][m[0].length];
        //imgPrincipal.dibujar();
    }
    
   public void agrupar()
   {
       marcarFigura();
       
   }
    
    public void marcarFigura()
    {
        int numFigura=1;
        for(int f = 0; f < m.length; ++f){
            for(int c = 0; c < m[f].length; ++c){
                //Aqui va toa la atsion 
                if(m[f][c]!=fondo && mCopia[f][c] == 0){
                    recorreTodas(f,c,numFigura);
                    ++numFigura;
                }
            }
        }
        
        String mCopiaStr="";
        for(int f = 0; f < m.length; ++f){
            for(int c = 0; c < m[f].length; ++c){
                mCopiaStr+=" "+mCopia[f][c];
            }
            mCopiaStr="\n";
        }
        System.out.println(mCopiaStr);       
    }
       
    public void recorreTodas(int f, int c,int figura){
        boolean esFondo;
        boolean noEvaluado; //noEvaluado v Fondo no perteneciente a una figura
        for(int d = 0; d < nombreDireccion.length; ++d){
            noEvaluado = mCopia[f+sumaF[d]][c+sumaC[d]] == 0;
            esFondo = m[f+sumaF[d]][c+sumaC[d]]==fondo;
            
            mCopia[f][c]=figura;
            if(posicionValida(f+sumaF[d],c+sumaC[d]) && !esFondo &&  noEvaluado) 
            {                
                recorreTodas(f+sumaF[d],c+sumaC[d],figura);
            }// evalua las 8 direcciones posibles para el fondo
            else{
                //Pasa por el fondo de la imagen
                if(posicionValida(f+sumaF[d],c+sumaC[d]) && esFondo && noEvaluado){
                    if(recorreFondo(f+sumaF[d],c+sumaC[d],figura)){
                        recorreTodas(f+sumaF[d],c+sumaC[d],figura);
                    }
                }
            }
            
        }
       
        
       
        
    }
    
    public boolean recorreFondo(int fila, int columna, int figura){
       boolean deFigura = true;
       for(int d = 0; deFigura && d < nombreDireccion.length; d=d+2){
            deFigura = cuatroDirecciones(fila, columna, d, figura); // evalua las 4 direcciones posibles para el fondo
       }
       return deFigura;
    }
        
    public boolean cuatroDirecciones(int f, int c, int direccion, int figura){
        boolean tocaFigura = false;       
        if(posicionValida(f,c)){
            if(mCopia[f][c]==figura){
                tocaFigura=true;
            }
            else{
                tocaFigura = cuatroDirecciones(f+sumaF[direccion], c+sumaC[direccion],direccion, figura);  
            }             
        }
        return tocaFigura;
    }      
    
    public boolean posicionValida(int f, int c){
       return m!=null && f >=0 && f < m.length && c >= 0 && c < m[f].length;
    }    
      
    //Cantidad de objetos <= cantidad de grupos.
    private boolean cantidadCorrecta(){
        return true;
    }

}
