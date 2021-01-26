
/**
 * Enumeration class TipoApercibimiento - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum TipoApercibimiento{
    DIEZ(10), 

    VEINTE(20), 

    TREINTA(30);
    private int numero;
    
    private TipoApercibimiento(int numero){
        this.numero = numero; 
    }

    public int getMaximo(){
        return numero;
    }
    
}
