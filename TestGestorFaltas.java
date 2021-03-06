
/**
 * Punto de entrada a la aplicaci�n
 */
public class TestGestorFaltas {
    /**
     * Se acepta como argumento del main() el n� m�ximo de estudiantes
     * y una vez creado el gestor de faltas se muestra la informaci�n solicitada
     * (ver enunciado)
     */
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Error en argumentos" + 
                "Sintaxis: java TestGestorFaltas <max_estudiantes>");
        }
        else{
            GestorFaltas grupo = new GestorFaltas(Integer.parseInt(args[0]));
            grupo.leerDeFichero();
            //mostrar alumnos
            System.out.println("Relaci�n de estudiantes(" + grupo.numeroAlumnos() + ")");
            System.out.println();
            System.out.println(grupo.toString());
            
            
            // Justificar faltas a iriso
            System.out.println();
            System.out.println("Justificar 6 faltas a IRISO FLAMARIQUE");
            if(grupo.buscarEstudiante("IRISO FLAMARIQUE ") > 0){
                grupo.justificarFaltas("IRISO FLAMARIQUE ", 6);
                System.out.println("Justificadas 6 faltas a IRISO FLAMARIQUE, Carla");
            }
            else{
                System.out.println("No est� la alumna");
            }
            
            
            
            //Ordenar en base a las faltas
            System.out.println();
            System.out.println();
            System.out.println("Alumnos ordenados por sus faltas");
            System.out.println();
            grupo.ordenar();
            System.out.println(grupo.toString()); 

            //Eliminar 30+ faltas
            System.out.println();
            System.out.println();
            System.out.println("Matricula anulada a los alumnos con + de 30 faltas");
            System.out.println();
            grupo.anularMatricula();
            System.out.println("Relaci�n de estudiantes(" + grupo.numeroAlumnos() + ")");
            System.out.println(grupo.toString());
        }
    }

}
