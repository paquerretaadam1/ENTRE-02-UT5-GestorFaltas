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
        GestorFaltas grupo = new GestorFaltas(5);
        grupo.addEstudiante(new Estudiante("  ander ibai  ,  Ruiz Sena , 12, 23"));
        grupo.addEstudiante(new Estudiante(" pedro josé   andrés  ,  Troya Baztarrica , 42, 6 "));
        grupo.addEstudiante(new Estudiante("  Javier  ,  Suescun  Andreu , 39, 9 "));
        grupo.addEstudiante(new Estudiante("julen, Duque Puyal, 5, 55"));
        grupo.addEstudiante(new Estudiante("julen, Auque Puyal, 5, 54"));
        System.out.println(grupo.toString());
        System.out.println("---------------");
        grupo.ordenar();
        
        System.out.println(grupo.toString());
    }

}
