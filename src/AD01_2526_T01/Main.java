/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AD01_2526_T01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Clase Principal
 *
 * @author Antonio Naranjo Castillo
 */
public class Main {

    /**
     * Método main ejecución del programa
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // DECLARACIÓN DE VARIABLES
        Scanner teclado;
        int opcion;
        Usuario user = null;
        String id;
        String password;
        String address;
        int yearbirth;
        String nombreFichero;
        boolean existenciaArchivo;
        String salidaPermitida = "N";
        String restaurarDatos = "N";

        // Comprobar si existe el archivo al iniciar por primera vez el programa y antes de mostrar el menú
        System.out.println("Introduzca el nombre del archivo para guardar la lista de usuarios, por defecto introducir 'user.dat'");
        teclado = new Scanner(System.in);
        nombreFichero = teclado.nextLine();
        existenciaArchivo = Usuario.comprobarExistenciaArchivo(nombreFichero);

        // Si el archivo no existe se le notificará al usuario que la aplicación no tiene datos grabados
        // Y si existe, se mostrarán los datos
        if (existenciaArchivo) {
            System.out.println(String.format("Existe el archivo '%s' a continuación se muestran los datos grabados.", nombreFichero));
            Usuario.cargarLista(nombreFichero);
            Usuario.mostrarListaUsuarios();
        } else {
            System.out.println(String.format("El archivo '%s' no existe, no se mostrarán datos grabados.", nombreFichero));
        }

        // Se implementa un bucle do-while para reproducir el menú tantas veces como opción distinta de 0 (Salir) se presenten por el usuario
        do {

            // Menú
            System.out.println("\n----");
            System.out.println("MENÚ");
            System.out.println("----");
            System.out.println("\n\t1. Agregar un usuario a la lista de usuarios.");
            System.out.println("\t2. Borrar un usuario a la lista de usuarios.");
            System.out.println("\t3. Guardar la lista de usuarios en un archivo (serialización).");
            System.out.println("\t4. Cargar la lista de usuarios desde el archivo (deserialización).");
            System.out.println("\t5. Mostrar los usuarios en la consola.");
            System.out.println("\t6. Exportar a fichero .txt la lista de usuarios.\n");
            System.out.println("\t0. Salir de la aplicación.\n");
            System.out.print("\t\t---> Opción seleccionada por el usuario: ");

            // Recoger opción seleccionada por el usuario
            opcion = Integer.parseInt(teclado.nextLine());

            // Estructura selectiva para ejecutar una determinada acción demandada por el usuario
            switch (opcion) {

                // Salir de la aplicación
                case 0:
                    if (!Usuario.compararListas()) {
                        System.out.println("Ha habido cambios en el programa que todavía no se han guardado. Si desea guardarlos ejecute la opción correspondiente del menú. Si sale ahora no se guardarán. ¿Está seguro de que desea salir sin guardar? (S/N)");
                        salidaPermitida = teclado.nextLine();
                    }
                    break;
                // Agregar un usuario a la lista de usuarios
                case 1:
                    System.out.println(String.format("La opción elegida por el usuario es: %d", opcion));

                    System.out.println("Introduzca el identificador del usuario");
                    id = teclado.nextLine();
                    System.out.println("Introduzca la contraseña del usuario");
                    password = teclado.nextLine();
                    System.out.println("Introduzca el dirección del usuario");
                    address = teclado.nextLine();
                    System.out.println("Introduzca el año de nacimiento del usuario");
                    yearbirth = teclado.nextInt();

                    // Instanciación del objeto user de la clase Usuario
                    user = new Usuario(id, password, address, yearbirth);
                    // Se agrega el usuario a la lista de usuarios
                    user.agregarUsuario(user);
                    break;

                // Borrar un usuario a la lista de usuarios
                case 2:
                    System.out.println(String.format("La opción elegida por el usuario es: %d", opcion));
                    // Se solicita al usuario el identificador del usuario a eliminar
                    System.out.println("Introduzca el identificador del usuario a eliminar");
                    id = teclado.nextLine();
                    // Se llama al método estático de la clase Usuario para eliminar el usuario solicitado
                    Usuario.borrarUsuario(id);
                    break;
                // Guardar la lista de usuarios en un archivo (serialización)
                case 3:
                    System.out.println(String.format("La opción elegida por el usuario es: %d", opcion));
                    System.out.println("Introduzca el nombre del archivo para guardar la lista de usuarios, por defecto introducir 'user.dat'");
                    String nombreFichero1 = teclado.nextLine();
                    Usuario.guardarLista(nombreFichero1);

                    break;

                // Cargar la lista de usuarios desde el archivo (deserialización)
                case 4:
                    System.out.println(String.format("La opción elegida por el usuario es: %d", opcion));
                    System.out.println("Introduzca el nombre del archivo del cual cargar la lista de usuarios, por defecto introducir 'user.dat'");
                    String nombreFichero2 = teclado.nextLine();
                    if (!Usuario.compararListas()) {
                        System.out.println("Ha realizado cambios que no se ha guardado en disco. Si continúa la carga del archivo se restaurarán los datos de disco y se perderán los cambios no guardados. ¿Desea continuar con la carga y restaurar los datos del archivo? (S/N)");
                        restaurarDatos = teclado.nextLine();
                    }
                    if (restaurarDatos.equals("S")) {
                        Usuario.cargarLista(nombreFichero2);
                    }
                    break;

                // Mostrar los usuarios en la consola
                case 5:
                    System.out.println(String.format("La opción elegida por el usuario es: %d", opcion));
                    // Se muestran en consola los usuarios de la lista de usuarios
                    Usuario.mostrarListaUsuarios();
                    break;

                // Exportar a fichero .txt la lista de usuarios
                case 6:
                    System.out.println(String.format("La opción elegida por el usuario es: %d", opcion));
                    System.out.println("Introduzca el nombre del archivo de texto txt para exportar la lista de usuarios, por defecto introducir 'user.txt'");
                    String nombreFichero3 = teclado.nextLine();
                    Usuario.escribirTXT(nombreFichero3);

                    break;

                // Mostrar un mensaje de error en la consola en caso de introducir una opción no permitida
                default:
                    if (opcion < 0 || opcion > 6) {
                        System.err.println(String.format("\nEl usuario ha introducido la opción '%d' incorrecta, debe ingresar una opción comprendida entre 0 y 6.", opcion));
                    }
            }

        } while (salidaPermitida.equals("N"));

        teclado.close();

    }

}
