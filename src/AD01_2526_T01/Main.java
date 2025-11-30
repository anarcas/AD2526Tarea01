/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package AD01_2526_T01;

import java.util.Scanner;

/**
 * Clase Principal
 *
 * @author Antonio Naranjo Castillo
 */
public class Main {

    /**
     * Método Principal ejecución del programa
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        // DECLARACIÓN DE VARIABLES
        // Objeto de la clase Scanner para recibir la opción solicitada por el usuario del programa
        Scanner teclado;
        // Dato tipo entero que almacena la opción seleccionada por el usuario
        int opcion;
        // Objeto usuario de la clase Usuario necesario para ingresar usuarios en la lista de usuarios
        Usuario user;
        // Atributo identificador del objeto usuario tipo de dato String
        String id;
        // Atributo contraseña del objeto usuario tipo de dato String
        String password;
        // Atributo dirección del objeto usuario tipo de dato String
        String address;
        // Atributo año de nacimiento del objeto usuario tipo de dato entero int
        int yearbirth;
        // String que almacena el mensaje a imprimir en pantalla
        String mensaje;
        // String que almacena el nombre del fichero
        String nombreFichero;
        // String que almacena el nombre del fichero de exportación TXT
        String nombreFicheroTXT;
        // Booleano que almacena verdadero|falso dependiendo de la existencia del archivo en cuestión
        boolean existenciaArchivo;
        // String que almacena el caracter que permite la salida del programa S|N, inicializado con "N"
        String salidaPermitida = "N";
        // String que almacena la restauración de los datos S|N, inicializado con "N"
        String restaurarDatos = "N";

        // Solicitar el nombre del archivo para grabar los datos al inciar el aplicativo
        System.out.println("Introduzca el nombre del archivo para grabar la lista de usuarios, pulsa ENTER para introducir por defecto 'user.dat'");
        teclado = new Scanner(System.in);
        nombreFichero = teclado.nextLine();
        
        // Si se pulsa ENTER se tomará el nombre del archivo por defecto, en caso contrario el usuario podrá definir el nombre que precise
        if (nombreFichero.isEmpty()) {
            nombreFichero = Usuario.nombreArchivo;
        } 
        
        // Comprobar si existe el archivo al iniciar por primera vez el programa y antes de mostrar el menú
        existenciaArchivo = Usuario.comprobarExistenciaArchivo(nombreFichero);

        // Si el archivo no existe se le notificará al usuario que la aplicación no tiene datos grabados
        // Y si existe, se mostrarán los datos
        if (existenciaArchivo) {
            System.out.println(String.format("Existe el archivo '%s' a continuación se muestran los datos grabados.", nombreFichero));
            Usuario.cargarLista(nombreFichero);
            System.out.println("\nDatos grabados en disco:\n");
            Usuario.mostrarListaUsuarios();
        } else {
            System.out.println(String.format("El archivo '%s' no existe, no se mostrarán datos grabados.", nombreFichero));
        }

        // Se implementa un bucle do-while para reproducir el menú tantas veces como mensajeSalida distinto de "S" (Salida permitida = SI) se presenten por el usuario
        // De esta manera se deberá insertar intencionadamente la letra S para salir del programa, evitando errores de salida del programa al pulsar accidentalmente cualquier otra tecla del teclado
        do {

            // Menú
            System.out.println("\n----");
            System.out.println("MENÚ");
            System.out.println("----\n");
            System.out.println("\t1. Agregar un usuario a la lista de usuarios.");
            System.out.println("\t2. Borrar un usuario a la lista de usuarios.");
            System.out.println("\t3. Guardar la lista de usuarios en un archivo (serialización).");
            System.out.println("\t4. Cargar la lista de usuarios desde el archivo (deserialización).");
            System.out.println("\t5. Mostrar los usuarios en la consola.");
            System.out.println("\t6. Exportar a fichero .txt la lista de usuarios.\n");
            System.out.println("\t0. Salir de la aplicación.\n");
            System.out.print("\t\t---> Opción seleccionada por el usuario: ");

            // Recoger opción seleccionada por el usuario
            opcion = Integer.parseInt(teclado.nextLine().trim());

            // Estructura selectiva para ejecutar una determinada acción demandada por el usuario
            switch (opcion) {

                // Salir de la aplicación
                case 0:
                    mensaje = "Salir de la aplicación";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion, mensaje));

                    // Se carga la lista de usuarios almacenada en el archivo del disco duro
                    Usuario.cargarListaGrabada(nombreFichero);
                    // Se comparan la lista temporal y la guardada en el archivo si son distintas o bien el archivo no existe se le propone al usuario que pueda guardar los cambios
                    if (!Usuario.compararListas() || !Usuario.comprobarExistenciaArchivo(nombreFichero)) {
                        System.out.println("Ha habido cambios en el programa que todavía no se han guardado. Si desea guardarlos ejecute la opción correspondiente del menú. Si sale ahora no se guardarán. ¿Está seguro de que desea salir sin guardar? (S/N)");
                        salidaPermitida = teclado.nextLine().trim();
                    } else {
                        salidaPermitida = "S";
                    }
                    if (salidaPermitida.equals("S")) {
                        System.out.println("FIN del programa.");
                    }
                    break;

                // Agregar un usuario a la lista de usuarios
                case 1:
                    mensaje = "Agregar un usuario a la lista de usuarios";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion, mensaje));

                    // Se solicita al usuario los atributos de los usuarios
                    System.out.println("Introduzca el identificador del usuario");
                    id = teclado.nextLine();
                    System.out.println("Introduzca la contraseña del usuario");
                    password = teclado.nextLine();
                    System.out.println("Introduzca el dirección del usuario");
                    address = teclado.nextLine();
                    System.out.println("Introduzca el año de nacimiento del usuario");
                    yearbirth = Integer.parseInt(teclado.nextLine());

                    // Instanciación del objeto user de la clase Usuario
                    user = new Usuario(id, password, address, yearbirth);
                    // Se agrega el usuario a la lista temporal de usuarios
                    Usuario.agregarUsuario(user);
                    System.out.println(String.format("El usuario con identificador '%s' ha sido agregado con éxito", user.getID()));
                    break;

                // Borrar un usuario a la lista de usuarios
                case 2:
                    mensaje = "Borrar un usuario a la lista de usuarios";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion, mensaje));
                    // Se solicita al usuario el identificador del usuario a eliminar
                    System.out.println("Introduzca el identificador del usuario a eliminar");
                    id = teclado.nextLine();
                    // Se llama al método estático de la clase Usuario para eliminar el usuario solicitado
                    Usuario.borrarUsuario(id);
                    System.out.println(String.format("El usuario con identificador '%s' ha sido borrado con éxito", id));

                    break;

                // Guardar la lista de usuarios en un archivo (serialización)
                case 3:
                    mensaje = "Guardar la lista de usuarios en un archivo (serialización)";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion, mensaje));
                    // Se llama al método estático guardarLista para almacenar hacer persistente los datos recogidos en la lista temporal
                    Usuario.guardarLista(nombreFichero);
                    System.out.println("Lista de usuarios grabada en disco con éxito.");
                    break;

                // Cargar la lista de usuarios desde el archivo (deserialización)
                case 4:
                    mensaje = "Cargar la lista de usuarios desde el archivo (deserialización)";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion, mensaje));
                    // Se llama al método estático de la clase Usuario cargarListaGrabada() para recuperar los datos almacenados en el fichero sustituyendo los datos almacenados en la lista temporal hasta ese momento
                    Usuario.cargarListaGrabada(nombreFichero);
                    // Se comprueba si ambas listas son distintas (la temporal y la persistente en el fichero del disco duro) para ofrecer al usuario del programa la posiblidad de guardar los cambios
                    if (!Usuario.compararListas() || !Usuario.comprobarExistenciaArchivo(nombreFichero)) {
                        System.out.println("Ha realizado cambios que no se ha guardado en disco. Si continúa la carga del archivo se restaurarán los datos de disco y se perderán los cambios no guardados. ¿Desea continuar con la carga y restaurar los datos del archivo? (S/N)");
                        restaurarDatos = teclado.nextLine();
                    }
                    // Si el usuario del programa pulsa intencionadamente el caracter "S" se optará por salir del programa
                    if (restaurarDatos.equals("S")) {
                        Usuario.cargarLista(nombreFichero);
                    }
                        System.out.println("La lista de usuarios ha sido cargada desde archivo con éxito.");
                    
                        break;

                // Mostrar los usuarios en la consola
                case 5:
                    mensaje = "Mostrar los usuarios en la consola";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion, mensaje));
                    // Se muestran en consola los usuarios de la lista de usuarios llamando al método estático de la clase Usuario mostrarListaUsuarios()
                    Usuario.mostrarListaUsuarios();
                    break;

                // Exportar a fichero .txt la lista de usuarios
                case 6:
                    mensaje = "Exportar a fichero .txt la lista de usuarios";
                    System.out.println(String.format("%nLa opción elegida por el usuario es: %d [%s]", opcion, mensaje));
                    System.out.println("Introduzca el nombre del archivo de texto txt para exportar la lista de usuarios, pulsa ENTER para introducir por defecto 'user.txt'");
                    nombreFicheroTXT = teclado.nextLine();
                    // Si se pulsa ENTER se tomará el nombre del archivo por defecto, en caso contrario el usuario podrá definir el nombre que precise
                    if (nombreFicheroTXT.isEmpty()) {
                        nombreFicheroTXT = Usuario.nombreArchivoTXT;
                    }
                    // Se escribe el contenido de la lista de usuario en un archivo TXT
                    Usuario.escribirTXT(nombreFicheroTXT);
                    System.out.println("Archivo de texto exportado correctamente.");

                    break;

                // Mostrar un mensaje de error en la consola en caso de introducir una opción no permitida
                default:
                    if (opcion < 0 || opcion > 6) {
                        System.err.println(String.format("\nEl usuario ha introducido la opción '%d' incorrecta, debe ingresar una opción comprendida entre 0 y 6.", opcion));
                    }
            }

        } while (!salidaPermitida.equals("S"));

        // Se cierra el flujo de entrada del objeto teclado de la clase Scanner
        teclado.close();

    }

}
