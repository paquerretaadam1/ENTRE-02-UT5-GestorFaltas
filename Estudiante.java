/**
 * Un objeto de esta clase guarda la información de un estudiante
 *
 */
public class Estudiante {
    private final static String SEPARADOR = ",";
    private String nombre;
    private String apellidos;
    private int faltasNoJustificadas;
    private int faltasJustificadas;
    /**
     *  
     *  Inicializa los atributos a partir de la información recibida
     *  Esta información se encuentra en lineaDatos
     *  (ver enunciado) 
     *  
     */
    public Estudiante(String lineaDatos) {
        String[] datos = lineaDatos.split(SEPARADOR); 
        this.nombre = hacerNombre(datos[0]);
        this.apellidos = hacerApellidos(datos[1]);
        this.faltasNoJustificadas = Integer.parseInt(datos[2].trim());
        this.faltasJustificadas = Integer.parseInt(datos[3].trim());
    }

    /**
     * 
     */
    private String hacerNombre(String nombre) {
        String[] aFormatear = nombre.split(" ");
        int numeroNombres = aFormatear.length;
        StringBuilder resul = new StringBuilder("");
        if(numeroNombres > 1){
            for(int i = 0; i < numeroNombres - 1; i++){
                if(!aFormatear[i].isEmpty()){
                    resul.append(aFormatear[i].toUpperCase().charAt(0));
                    resul.append(". ");
                }
            }
        }
        char capitalizada = aFormatear[numeroNombres - 1].toUpperCase().charAt(0);
        resul.append(capitalizada);
        resul.append(aFormatear[numeroNombres - 1].substring(1));
        return resul.toString();
    }

    /**
     * 
     */
    private String hacerApellidos(String apellidos) {
        apellidos = apellidos.trim();
        StringBuilder resul = new StringBuilder("");
        String[] sinEspacios = apellidos.split(" ");
        for(int i = 0; i < sinEspacios.length; i++){
            if(!sinEspacios[i].isEmpty()){
                resul.append(sinEspacios[i].toUpperCase() + " ") ;   
            }
        }
        return resul.toString();
    }

    /**
     * accesor para el nombre completo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre
     *  
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *  mutador para los apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * accesor para el nº de faltas no justificadas
     */
    public int getFaltasNoJustificadas() {
        return faltasNoJustificadas;
    }

    /**
     * mutador para el nº de faltas no justificadas
     */
    public void setFaltasNoJustificadas(int faltasNoJustificadas) {
        this.faltasNoJustificadas = faltasNoJustificadas;
    }

    /**
     * accesor para el nº de faltas justificadas
     */
    public int getFaltasJustificadas() {
        return faltasJustificadas;
    }

    /**
     *  mutador para el nº de faltas justificadas
     */
    public void setFaltasJustificadas(int faltasJustificadas) {
        this.faltasJustificadas = faltasJustificadas;
    }
    
    /**
     * 
     */
    public String apercibimientos(int injustificadas) {
        StringBuilder strB = new StringBuilder("");
        if(injustificadas < TipoApercibimiento.DIEZ.getMaximo()){
            return "Sin apercibimientos";
        }
        if(injustificadas > TipoApercibimiento.DIEZ.getMaximo()){
            strB.append("DIEZ ");
        }
        if(injustificadas > TipoApercibimiento.VEINTE.getMaximo()){
            strB.append("VEINTE ");
        }
        if(injustificadas > TipoApercibimiento.TREINTA.getMaximo()){
            strB.append("TREINTA");
        }
        return strB.toString();
    }

    /**
     *  se justifican n faltas que hasta el momento eran injustificadas
     *  Se asume n correcto
     */
    public void justificar(int n) {
        this.faltasNoJustificadas -= n;
        this.faltasJustificadas += n;
    }

    /**
     * Representación textual del estudiante
     * (ver enunciado)
     */
    public String toString() {
        StringBuilder str = new StringBuilder("");
        String formateado = "";
        for(int i = 0; i < 8; i++){
            switch(i){
                case 0:
                formateado = "Apellidos y Nombre:";
                break;
                case 1:
                formateado = this.apellidos + SEPARADOR + " " + this.nombre ;
                break;
                case 2:
                formateado = "Faltas No Justificadas:";
                break;
                case 3:
                formateado = "" + faltasNoJustificadas + "";
                break;
                case 4:
                formateado = "Faltas Justificadas:";
                break;
                case 5:
                formateado = "" + faltasJustificadas + "";
                break;
                case 6:
                formateado = "Apercibimientos:";
                break;
                case 7:
                formateado = apercibimientos(this.faltasNoJustificadas);
                break;
            }

            str.append(String.format("%-25s", formateado));
            if(i % 2 != 0){
                str.append("\n");
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Estudiante e1 = new Estudiante("  ander ibai  ,  Ruiz Sena , 12, 23");
        System.out.println(e1);
        System.out.println();
        Estudiante e2 = new Estudiante(
        " pedro josé   andrés  ,  Troya Baztarrica , 42, 6 ");
        System.out.println(e2);
        System.out.println();
        Estudiante e3 = new Estudiante("  Javier  ,  Suescun  Andreu , 39, 9 ");
        System.out.println(e3);
        System.out.println();
        Estudiante e4 = new Estudiante("julen, Duque Puyal, 5, 55");
        System.out.println(e4);
        System.out.println();

        System.out.println("---------------------------------");
        e1.justificar(3);
        System.out.println(e1);
        System.out.println();

        System.out.println("---------------------------------");

        e3.justificar(12);
        System.out.println(e3);
        System.out.println();

    }

}
