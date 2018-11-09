public class Grupo
{
    private static final String nombreDireccion[]={"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"};
    private static final int sumaF[]={-1,-1,0,1,1,1,0,-1};//"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"
    private static final int sumaC[]={0,-1,-1,-1,0,1,1,1};
    
    private Imagen imgPrincipal;
    private int cantidadDeGrupos;
    int[] tamFigura;
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
       medirFiguras(marcarFiguras());
    }
    
    public int marcarFiguras()
    {
        int numFigura=0;
        for(int f = 0; f < m.length; ++f){
            for(int c = 0; c < m[f].length; ++c){
                if(m[f][c]!=fondo && mCopia[f][c] == 0){
                    ++numFigura;
                    recorreTodas(f,c,numFigura);                  
                }
            }
        }
        
        String mCopiaStr="";
        for(int f = 0; f < m.length; ++f){
            for(int c = 0; c < m[f].length; ++c){
                mCopiaStr+=" "+mCopia[f][c];
            }
            mCopiaStr+="\n";
        }
        System.out.println(mCopiaStr);  
        return numFigura;
    }
       
    public void recorreTodas(int f, int c,int numFigura){
        boolean esFondo;
        boolean noEvaluado; //noEvaluado v Fondo no perteneciente a una figura
        for(int d = 0; d < nombreDireccion.length; ++d){
            noEvaluado = mCopia[f+sumaF[d]][c+sumaC[d]] == 0;
            esFondo = m[f+sumaF[d]][c+sumaC[d]]==fondo;            
            mCopia[f][c]=numFigura;
            if(posicionValida(f+sumaF[d],c+sumaC[d]) && !esFondo &&  noEvaluado) 
            {                
                recorreTodas(f+sumaF[d],c+sumaC[d],numFigura);
            }// evalua las 8 direcciones posibles para el fondo
            else{
                //Pasa por el fondo de la imagen
                if(posicionValida(f+sumaF[d],c+sumaC[d]) && esFondo && noEvaluado){
                    if(recorreFondo(f+sumaF[d],c+sumaC[d],numFigura)){
                        recorreTodas(f+sumaF[d],c+sumaC[d],numFigura);
                    }
                }
            }          
        }                        
    }
    
    public boolean recorreFondo(int fila, int columna, int numFigura){
       boolean deFigura = true;
       for(int d = 0; deFigura && d < nombreDireccion.length; d=d+2){
            deFigura = cuatroDirecciones(fila, columna, d, numFigura); // evalua las 4 direcciones posibles para el fondo
       }
       return deFigura;
    }
        
    public boolean cuatroDirecciones(int f, int c, int direccion, int numFigura){
        boolean tocaFigura = false;       
        if(posicionValida(f,c)){
            if(mCopia[f][c]==numFigura){
                tocaFigura=true;
            }
            else{
                tocaFigura = cuatroDirecciones(f+sumaF[direccion], c+sumaC[direccion],direccion, numFigura);  
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
    
    public void medirFiguras(int numFiguras)
    {   
        if(numFiguras>0 && numFiguras>cantidadDeGrupos)
        {
            tamFigura = new int [numFiguras];
        
            for(int tF= 0;tF<tamFigura.length; ++tF){
                for(int f = 0; f < mCopia.length; ++f){
                    for(int c = 0; c < mCopia[f].length; ++c){
                        if(mCopia[f][c]==(tF+1)){
                            ++tamFigura[tF];
                        }  
                    }
                }
            }
        }
        else{
            System.out.println("Error! No hay suficientes figuras");
        }
    }
    
    
}
