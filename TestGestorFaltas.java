/**
 * Punto de entrada a la aplicación
 */
public class TestGestorFaltas {
    /**
     * Se acepta como argumento del main() el nº máximo de estudiantes
     * y una vez creado el gestor de faltas se muestra la información solicitada
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
            System.out.println(grupo.toString());
            // Justificar faltas a iriso
            if(grupo.buscarEstudiante("IRISO FLAMARIQUE ") > 0){
                grupo.justificarFaltas("IRISO FLAMARIQUE ", 6);
                System.out.println("Justificadas 6 faltas a IRISO FLAMARIQUE, Carla");
            }
            else{
                System.out.println("No está la alumna");
            }
            //Ordenar en base a las faltas
            grupo.ordenar();
            System.out.println(grupo.toString()); 

            //Eliminar 30+ faltas
            grupo.anularMatricula();
            System.out.println(grupo.toString());
        }
    }

}
