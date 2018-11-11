public class Grupo
{
    private static final String NOMBRE_DIRECCION[]={"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"};
    private static final int SUMA_F[]={-1,-1,0,1,1,1,0,-1};//"Norte","Noroeste","Oeste","Suroeste","Sur","Sureste","Este","Noreste"
    private static final int SUMA_C[]={0,-1,-1,-1,0,1,1,1};
    
    private Imagen imgPrincipal;
    private int cantidadDeGrupos;
    private int[] tamFigura;
    private int[][] grupo;
    private int[][] m;
    private int[][] mCopia;
    private int fondo;
    private int[] prevCentroide;
    
    public Grupo(String imgEntrada, int cantidadDeGrupos){
        this.cantidadDeGrupos = cantidadDeGrupos;
        imgPrincipal = new Imagen(imgEntrada);
        m = imgPrincipal.getMatriz();
        mCopia = new int [m.length][m[0].length];
        fondo= m[0][0];        
        prevCentroide = new int[cantidadDeGrupos];
    }
    
    public void ejecutar()
    {      
        if(!medirFiguras(marcarFiguras())){
            organizar();
            imprimirGrupos();
        }
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
        for(int d = 0; d < NOMBRE_DIRECCION.length; ++d){
            noEvaluado = mCopia[f+SUMA_F[d]][c+SUMA_C[d]] == 0;
            esFondo = m[f+SUMA_F[d]][c+SUMA_C[d]]==fondo;            
            mCopia[f][c]=numFigura;
            if(posicionValida(f+SUMA_F[d],c+SUMA_C[d]) && !esFondo &&  noEvaluado) 
            {                
                recorreTodas(f+SUMA_F[d],c+SUMA_C[d],numFigura);
            }// evalua las 8 direcciones posibles para el fondo
            else{
                //Pasa por el fondo de la imagen
                if(posicionValida(f+SUMA_F[d],c+SUMA_C[d]) && esFondo && noEvaluado)
                {
                    if(recorreFondo(f+SUMA_F[d],c+SUMA_C[d],numFigura)){
                        recorreTodas(f+SUMA_F[d],c+SUMA_C[d],numFigura);
                    }
                    
                    if(m[f][c] == fondo && mCopia[f][c] == numFigura && cuatroDirecciones(f+SUMA_F[d],c+SUMA_C[d],d,numFigura)){
                        mCopia[f][c] = 0;
                    }
                }
            }          
        }                        
    }
    
    public boolean recorreFondo(int fila, int columna, int numFigura){
       boolean deFigura = true;
       for(int d = 0; deFigura && d < NOMBRE_DIRECCION.length; d=d+2){
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
                tocaFigura = cuatroDirecciones(f+SUMA_F[direccion], c+SUMA_C[direccion],direccion, numFigura);  
            }             
        }
        return tocaFigura;
    }      
    
    public boolean posicionValida(int f, int c){
       return m!=null && f >=0 && f < m.length && c >= 0 && c < m[f].length;
    }          
    
    public boolean medirFiguras(int numFiguras)
    {   
        boolean err=false;
        if(numFiguras>0 && numFiguras>=cantidadDeGrupos)
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
            err=true;
        }
        return err;
    }
    
    public void organizar()
    {
        boolean huboCambios;
        grupo = new int[cantidadDeGrupos][tamFigura.length];        
        int[] prevCentroide = new int[cantidadDeGrupos];
        randomCentroides();              
        do{
            agruparFiguras();
            huboCambios= elegirNuevosCentroides();
            if(huboCambios){
                limpiarMiembros();
            }
        }while(huboCambios);         
    }
    
    public void limpiarMiembros() //Elimina los integrantes de los grupos si hubo cambios de centroides
    {
        for(int i=0; i<grupo.length; ++i){
            for(int j=1; j<grupo[i].length; ++j){
                grupo[i][j]=0;
            }
        }
    }
    
    public void randomCentroides()
    {
        boolean[] vUsado = new boolean[tamFigura.length];        
        int numAux;
        for(int i=0; i<cantidadDeGrupos; ++i)
        {           
            do{
                numAux=(int)(Math.random()*tamFigura.length+1);
                grupo[i][0]=numAux;//Guarda el numFigura real no su pos en tamFigura[]
            }while(vUsado[numAux-1]==true);
            vUsado[numAux-1]=true;
        }
    }
    
    public void agruparFiguras()
    {
        int[] difMinima = new int [2];
        int diferencia;
        int asignados=0;
        for(int i=0; i<tamFigura.length; ++i)
        {
            if(!esCabeza(i)){
                difMinima[0]= -1;
                for(int j=0; j<grupo.length; ++j)
                {
                    diferencia = Math.abs(tamFigura[grupo[j][0]-1]-tamFigura[i]);
                    if(diferencia<difMinima[0]){
                        difMinima[0]= diferencia;
                        difMinima[1]= j;
                    }
                    else{
                        if(difMinima[0]==-1){
                            difMinima[0]=diferencia;
                            difMinima[1]= j;
                        }                        
                    } 
                }
                grupo[difMinima[1]][++asignados]= i+1;
            }      
        }       
    }
    
    public boolean esCabeza(int numFigura)//Verifica el numFigura real, no su pos en tamFigura[]
    {
        boolean esCentroide=false;
        for(int i=0; !esCentroide && i<grupo.length; ++i)
        {
            esCentroide = numFigura+1== grupo[i][0];
        }
        return esCentroide;
    }
    
    public boolean elegirNuevosCentroides()
    {
        boolean cambios=false;
        double promedio;
        int contador;
        int aux;
        int[] difMinima = new int [2];
        int diferencia;
        for(int i=0; i<grupo.length; ++i)
        {
            promedio=0.0;
            contador=0;
            difMinima[0]= -1;
            difMinima[1]= 0;
            for(int j=0; j<grupo[i].length; ++j)
            {
                if(grupo[i][j]!=0){
                    promedio+=tamFigura[grupo[i][j]-1];
                    ++contador;
                }              
            }            
            promedio= Math.round(promedio/contador);
            
            for(int k=0; k<grupo[i].length; ++k)
            {
                if(grupo[i][k]-1>=0)
                {
                    diferencia = Math.abs((int)(promedio) - (tamFigura[grupo[i][k]-1]));
                    if(diferencia<difMinima[0]){
                        difMinima[0]= (int)Math.round(diferencia);
                        difMinima[1]= k;
                    }
                    else{
                        if(difMinima[0]==-1){
                            difMinima[0]= (int)Math.round(diferencia);
                            difMinima[1]= k;
                        }                        
                    } 
                }
            }           
            if(grupo[i][0]!=grupo[i][difMinima[1]] && grupo[i][difMinima[1]]!=prevCentroide[i]){               
                cambios=true;
                prevCentroide[i]= grupo[i][0];
                aux= grupo[i][0];
                grupo[i][0]= grupo[i][difMinima[1]];
                grupo[i][difMinima[1]]= aux;
            }
        }        
        return cambios;
    }
    
    public void imprimirGrupos()
    {
        int[][][] v_mGrupo = new int [cantidadDeGrupos][m.length][m[0].length]; //Vector de matrices donde estaran almacenadas las matrices de los grupos
        Imagen[] imgGrupo= new Imagen[cantidadDeGrupos];
        
        for(int h=0;h<cantidadDeGrupos;++h){
            for(int i=0;i<v_mGrupo[h].length;++i){
                for(int j=0;j<v_mGrupo[h][i].length;++j){
                    if(perteneceGrupo(h,i,j)){
                        v_mGrupo[h][i][j]=m[i][j];
                    }
                    else{
                        v_mGrupo[h][i][j]=fondo;
                    }
                }
            }
        }
        
        for(int i=0;i<cantidadDeGrupos;++i){
            imgGrupo[i] = new Imagen(v_mGrupo[i]);
            //imgGrupo[i].dibujar();
        }
    }
    
    public boolean perteneceGrupo(int numGrupo, int i, int j)
    {
        boolean pertenece=false;
        if(mCopia[i][j]!=0){
            for(int k=0;!pertenece && k<grupo[numGrupo].length;++k)
            {            
                pertenece = mCopia[i][j]==grupo[numGrupo][k];
            }
        }
        return pertenece;
    }
    
}
