import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Un objeto de esta clase permite registrar estudiantes de un
 * curso (leyendo la informaci�n de un fichero de texto) y 
 * emitir listados con las faltas de los estudiantes, justificar faltas, 
 * anular matr�cula dependiendo del n� de faltas, .....
 *
 */
public class GestorFaltas {
    private Estudiante[] estudiantes;
    private int pos;
    public GestorFaltas(int n) {
        this.estudiantes = new Estudiante[n];
        this.pos = 0;
    }

    /**
     * Devuelve true si el array de estudiantes est� completo,
     * false en otro caso
     */
    public boolean cursoCompleto() {
        return pos == estudiantes.length;
    }

    /**
     *    A�ade un nuevo estudiante solo si el curso no est� completo y no existe ya otro
     *    estudiante igual (con los mismos apellidos). 
     *    Si no se puede a�adir se muestra los mensajes adecuados 
     *    (diferentes en cada caso)
     *    
     *    El estudiante se a�ade de tal forma que queda insertado en orden alfab�tico de apellidos
     *    (de menor a mayor)
     *    !!OJO!! No hay que ordenar ni utilizar ning�n algoritmo de ordenaci�n
     *    Hay que insertar en orden 
     *    
     */
    public void addEstudiante(Estudiante nuevo) {
        if(cursoCompleto()){
            System.out.println("No se pueden a�adir m�s alumnos, el curso est� lleno.");
        }
        else if(buscarEstudiante(nuevo.getApellidos()) >= 0){
            System.out.println("No se pueden a�adir al alumno(" + nuevo.getApellidos() + "), sus apellidos ya est�n registrados.");
        }
        else{
            int i = 0;
            boolean agregado = false;
            while(i < pos){
                if(nuevo.getApellidos().compareTo(estudiantes[i].getApellidos()) < 0){
                    System.arraycopy(estudiantes, i, estudiantes, i + 1, pos - i);
                    estudiantes[i] = nuevo;
                    pos++;
                    i = pos;
                    agregado = true;
                }
                else{
                    i++;
                }
            }
            if(agregado == false){
                estudiantes[i] = nuevo;
                pos++;
            }           
        }
    }

    /**
     * buscar un estudiante por sus apellidos
     * Si est� se devuelve la posici�n, si no est� se devuelve -1
     * Es indiferente may�sculas / min�sculas
     * Puesto que el curso est� ordenado por apellido haremos la b�squeda m�s
     * eficiente
     *  
     */
    public int buscarEstudiante(String apellidos) {
        int izquierda = 0;
        int derecha = pos - 1;
        while (izquierda <= derecha) {
            int mitad = (izquierda + derecha) / 2;
            if (estudiantes[mitad].getApellidos().equalsIgnoreCase(apellidos)) {
                return mitad;
            }
            else if (estudiantes[mitad].getApellidos().compareTo(apellidos.toUpperCase()) > 0) {
                derecha = mitad - 1;
            }
            else {
                izquierda = mitad + 1;
            }
        }
        return -1;

    }

    /**
     * Representaci�n textual del curso
     * Utiliza StringBuilder como clase de apoyo.
     *  
     */
    public String toString() {
        StringBuilder strB = new StringBuilder();
        for(int i = 0; i < pos; i++){
            strB.append("--------------" + "\n");
            strB.append(estudiantes[i].toString());
        }
        return strB.toString();
    }

    /**
     *  Se justifican las faltas del estudiante cuyos apellidos se proporcionan
     *  El m�todo muestra un mensaje indicando a qui�n se ha justificado las faltas
     *  y cu�ntas
     *  
     *  Se asume todo correcto (el estudiante existe y el n� de faltas a
     *  justificar tambi�n)
     */
    public void justificarFaltas(String apellidos, int faltas) {
        int aCambiar = buscarEstudiante(apellidos);
        estudiantes[aCambiar].justificar(faltas);
    }

    /**
     * ordenar los estudiantes de mayor a menor n� de faltas injustificadas
     * si coinciden se tiene en cuenta las justificadas
     * M�todo de selecci�n directa
     */
    public void ordenar() {
        for (int i = pos - 1; i > 0; i--) {
            int posmin = i;
            for (int j = i - 1; j > 0; j--) {
                if (estudiantes[j].getFaltasNoJustificadas() > estudiantes[posmin].getFaltasNoJustificadas()) {
                    posmin = j;
                }
                else if(estudiantes[j].getFaltasNoJustificadas() == estudiantes[posmin].getFaltasNoJustificadas()
                    && estudiantes[j].getFaltasJustificadas() > estudiantes[posmin].getFaltasJustificadas()){
                    posmin = j;
                }
            }
            Estudiante aux = estudiantes[posmin];
            estudiantes[posmin] = estudiantes[i];
            estudiantes[i] = aux;
        }
    }

    /**
     * anular la matr�cula (dar de baja) a 
     * aquellos estudiantes con 30 o m�s faltas injustificadas
     */
    public void anularMatricula() {
        int i = 0;
        while( i < pos){
            if(estudiantes[i].getFaltasNoJustificadas() > 30){
                System.arraycopy(estudiantes, i + 1, estudiantes, i, pos - i);
                pos--;
            }
            else{
                i++;
            }
        }
    }

    /**
     * Lee de un fichero de texto los datos de los estudiantes
     *   con ayuda de un objeto de la  clase Scanner
     *   y los guarda en el array. 
     */
    public void leerDeFichero() {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("estudiantes.txt"));
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                Estudiante estudiante = new Estudiante(linea);
                this.addEstudiante(estudiante);

            }

        }
        catch (IOException e) {
            System.out.println("Error al leer del fichero");
        }
        finally {
            if (sc != null) {
                sc.close();
            }
        }

    }

}
