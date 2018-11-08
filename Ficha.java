public class Ficha {
    public static final byte ROJA = 0;
    public static final byte NEGRA = 1;
    public static final char [] SIMBOLO = {'R','N'};
    
    private byte color;
    
    public Ficha() {
       color = NEGRA;
    }

    public Ficha(byte color){
       this.color = NEGRA;
       if(color==ROJA){
          this.color = ROJA;
       }
    }

    public Ficha( Ficha otra ){
       this.color = otra.color;
    }
    
    public boolean equals(Ficha otra){
       return this.color==otra.color;
    }
    
    public String toString(){
       return ""+SIMBOLO[color];
    }
}