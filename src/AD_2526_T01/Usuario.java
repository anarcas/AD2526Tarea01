/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AD_2526_T01;

// Conjunto de paquetes importandos
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Representa una entidad de usuario en el sistema. Esta clase implementa la
 * interfaz {@code Serializable} para permitir que sus objetos puedan ser
 * convertidos en un flujo de bytes y persistidos (guardados) en archivos o
 * transmitidos por red.
 *
 * @author Antonio Naranjo Castillo
 */
public class Usuario implements Serializable {

    // Mecanismo de control de versiones (serialización)
    private static final long serialVersionUID = 42L;

    // Atributos privados
    private String identificador;
    private String contrasena;
    private String direccion;
    private int anoNacimiento;

    // Atributos privados estáticos de la clase Usuario donde se manejarán todos los usurios añadidos, borrados, exportados
    private static List<Usuario> listaUsuariosTemp = new ArrayList<>();
    private static List<Usuario> listaUsuariosDeserializada = new ArrayList<>();
    private static List<String> listaUsuariosTXT = new ArrayList<>();

    // Atributos estáticos públicos
    public static final String nombreArchivo = "user.dat";
    public static final String nombreArchivoTXT = "user.txt";

    /**
     * Método constructor de la clase Usuario
     *
     * @param id String que representa la identificación de los usuarios
     * @param password String que representa la contraseña de los usuarios
     * @param address String que almacena la dirección de los usuarios
     * @param yearBirth Dato numérico de tipo entero (int) que almacena el año
     * de nacimiento de los usuarios
     */
    public Usuario(String id, String password, String address, int yearBirth) {
        this.identificador = id;
        this.contrasena = password;
        this.direccion = address;
        this.anoNacimiento = yearBirth;
    }

    /**
     * Método getter que devuelve el identificador del usuario
     *
     * @return String identificador
     */
    public String getID() {
        return this.identificador;
    }

    /**
     * Método getter que devuelve la contraseña del usuario
     *
     * @return String contraseña
     */
    public String getPassword() {
        return this.contrasena;
    }

    /**
     * Método getter que devuelve la dirección del usuario
     *
     * @return String dirección
     */
    public String getAddress() {
        return this.direccion;
    }

    /**
     * Método getter que devuelve el año de nacimiento del usuario
     *
     * @return int año de nacimiento
     */
    public int getYearBirth() {
        return this.anoNacimiento;
    }

    /**
     * Método setter que establece el identificador del usuario
     *
     * @param id String identificador del usuario
     */
    public void setID(String id) {
        this.identificador = id;
    }

    /**
     * Método setter que establece contraseña del usuario
     *
     * @param password String contraseña del usuario
     */
    public void setPassword(String password) {
        this.contrasena = password;
    }

    /**
     * Método setter dirección del usuario
     *
     * @param address String dirección del usuario
     */
    public void setAddress(String address) {
        this.direccion = address;
    }

    /**
     * Método setter año nacimiento del usuario
     *
     * @param yearBirth Dato numérico int año de nacimiento del usuario
     */
    public void setYearBirth(int yearBirth) {
        this.anoNacimiento = yearBirth;
    }

    /**
     * Método para agregar un usuario a la lista de usuarios temporal Se emplea
     * un bucle do-while para recorrer todos los elementos de la lista temporal
     * de usuarios y compararla con el identificador del usuario a ingresar. Si
     * existe se evaluará si se actualizan los atributos del usuario existente.
     * Si no existe el usuario se añadirá a la lista temporal.
     *
     * @param user Objeto usuario a agregar a la lista temporal
     */
    public static void agregarUsuario(Usuario user) {
        boolean usuarioExiste;
        int contador = 0;
        Scanner teclado = new Scanner(System.in);
        String respuesta;

        if (!Usuario.listaUsuariosTemp.isEmpty()) {

            do {
                if (Usuario.listaUsuariosTemp.get(contador).getID().equals(user.getID())) {
                    usuarioExiste = true;
                    System.out.println(String.format("El usuario con identificador %s ya se encuentra en la lista de usuarios, ¿desea sustituir su contenido? (S|N)", user.getID()));
                    respuesta = teclado.nextLine();
                    if (respuesta.equals("S")) {
                        Usuario.listaUsuariosTemp.get(contador).contrasena = user.getPassword();
                        Usuario.listaUsuariosTemp.get(contador).direccion = user.getAddress();
                        Usuario.listaUsuariosTemp.get(contador).anoNacimiento = user.getYearBirth();
                        System.out.println(String.format("El usuario existente con identificador '%s' ha actualizado sus atributos con éxito", Usuario.listaUsuariosTemp.get(contador).getID()));
                    } else {
                        System.out.println(String.format("El usuario existente con identificador '%s' no se han actualizado sus atributos", Usuario.listaUsuariosTemp.get(contador).getID()));
                    }
                } else {
                    usuarioExiste = false;
                }
                contador++;
            } while (!usuarioExiste && (contador < Usuario.listaUsuariosTemp.size()));

            if (!usuarioExiste) {
                Usuario.listaUsuariosTemp.add(user);
                System.out.println(String.format("El usuario con identificador '%s' ha sido agregado con éxito", user.getID()));
            }
            
        } else {
            Usuario.listaUsuariosTemp.add(user);
            System.out.println(String.format("El usuario con identificador '%s' ha sido agregado con éxito", user.getID()));
        }

    }

    /**
     * Método estático para mostrar en consola la lista de usuarios de la lista
     * temporal.
     */
    public static void mostrarListaUsuarios() {
        for (Usuario user : Usuario.listaUsuariosTemp) {
            System.out.println(user.toString());
        }
    }

    /**
     * Método toString para mostrar los atributos del objeto usuario en una
     * cadena de caracteres.
     */
    @Override
    public String toString() {
        return String.format("Usuario [Identificador= %s, Contraseña= %s, Dirección= %s, Año de nacimiento= %d]",
                getID(),
                getPassword(),
                getAddress(),
                getYearBirth());
    }

    /**
     * Método para borrar un usuario a la lista de usuarios temporal aportando
     * el atributo identificador del objeto usuario entidad.
     *
     * @param identificador String cadena de caracteres identificador del
     * usuario
     */
    public static void borrarUsuario(String identificador) {
        // Booleano que determina si el usuario existe
        boolean usuarioExiste = false;
        // Se instancia un iterador de la lista de usuarios para eliminar el usuario de manera segura
        Iterator<Usuario> iterador = Usuario.listaUsuariosTemp.iterator();
        while (iterador.hasNext()) {
            Usuario user = iterador.next();
            if (user.getID().equals(identificador)) {
                iterador.remove();
                usuarioExiste = true;
            }
        }
        if (usuarioExiste) {
            System.out.println(String.format("El usuario con identificador '%s' ha sido borrado con éxito.", identificador));
        } else {
            System.out.println(String.format("El usuario con identificador '%s' no existe en la lista de usuarios.", identificador));
        }
    }

    /**
     * Método para guardar la lista de usuarios en un archivo dado
     * (serialización) se da persistencia a los datos almacenados en la lista
     * temporal
     *
     * @param nombreArchivo String que almacena el nombre del archivo que se
     * guardará en el disco duro
     */
    public static void guardarLista(String nombreArchivo) {

        // Serializar la lista completa en el fichero
        try (
                // Escribir bytes físicamente en el archivo
                FileOutputStream fileOut = new FileOutputStream(nombreArchivo); // Convertir el objeto listaUsuariosTemp en bytes
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            // Escribir el objeto listaUsuariosTemp completo (Serialización)
            out.writeObject(Usuario.listaUsuariosTemp);

        } catch (IOException ex) {
            // Manejo de la excepción
            System.err.println("Error de E/S durante la serialización: " + ex.getMessage());

        }
    }

    /**
     * Método para cargar la lista de usuarios desde un archivo dado
     * (deserialización)
     *
     * @param nombreArchivo String que almacena el nombre del archivo desde el
     * cual se recuperan los datos del disco
     */
    //@SuppressWarnings("unchecked")
    public static void cargarLista(String nombreArchivo) {

        try (
                // Obtener los bytes almacenados en el archivo
                FileInputStream fileIn = new FileInputStream(nombreArchivo); // Conviertir los bytes en objetos
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

            // Se actualiza la lista temporal según datos almacenados en el fichero del disco duro previo casting
            Usuario.listaUsuariosTemp = (List<Usuario>) in.readObject();

            // Manejo de excepciones
        } catch (FileNotFoundException ex) {
            System.err.println("Error: El archivo '" + nombreArchivo + "' no existe.");
        } catch (IOException ex) {
            System.err.println("Error de Entrada/Salida: Falló la lectura del fichero.");
        } catch (ClassNotFoundException | ClassCastException ex) {
            System.err.println("Error de clase: No se pudo encontrar la clase Usuario.");
        }

    }

    /**
     * Método para guardar la lista de usuarios en un archivo de texto txt.
     *
     * @param nombreArchivo String que almacena el nombre del archivo TXT que se
     * guardará en el disco duro
     */
    public static void escribirTXT(String nombreArchivo) {
        // Se limpia la lista para reutilizarla si es el caso
        Usuario.listaUsuariosTXT.clear();
        // Se alimenta la lista de usuarios empleando el método toString() para cada objeto Usuarios
        for (Usuario user : Usuario.listaUsuariosTemp) {
            listaUsuariosTXT.add(user.toString());
        }

        try (
                // Se convierte los caracteres en bytes
                FileWriter fw = new FileWriter(nombreArchivo, false); // Se establece un flujo de escritura
                 BufferedWriter bw = new BufferedWriter(fw)) {

            // Por cada cadena de caracteres almacenada en la lista listaUsuariosTXT se escribe en el archivo txt.
            // Se introducir un salto de carro después de cada String str.
            for (String str : Usuario.listaUsuariosTXT) {
                bw.write(str);
                bw.newLine();
            }
            // Manejo de excepciones de Entrada/Salida
        } catch (IOException ex) {
            System.err.println("Error de E/S al escribir en el fichero: " + ex.getMessage());
        }
    }

    /**
     * Método para comprobar si el archivo indicado por el usuario existe
     *
     * @param nombreArchivo String que almacena el nombre del archivo a
     * comprobar su existencia
     * @return booleano que devuelve el método tras comprobar la existencia del
     * archivo
     */
    public static boolean comprobarExistenciaArchivo(String nombreArchivo) {

        // Se declara un objeto archivo de la clase File
        File archivo;

        // Se instancia el objeto archivo anterior
        archivo = new File(nombreArchivo);

        // Return
        return archivo.exists();

    }

    /**
     * Método para cargar la lista de usuarios desde un archivo dado.
     *
     * @param nombreArchivo String que contiene el nombre del archivo donde se
     * almacenarán los datos de los usuarios.
     */
    public static void cargarListaGrabada(String nombreArchivo) {

        try (
                // Obtener los bytes almacenados en el archivo
                FileInputStream fileIn = new FileInputStream(nombreArchivo); // Conviertir los bytes en objetos
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

            // Se almacenan los objetos usuarios deserializados en una lista
            Usuario.listaUsuariosDeserializada = (List<Usuario>) in.readObject();

            // Manejo de excepciones
        } catch (FileNotFoundException ex) {
            System.err.println("Error: El archivo " + nombreArchivo + " no existe.");
        } catch (IOException ex) {
            System.err.println("Error de Entrada/Salida: Falló la lectura del fichero.");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error de clase: No se pudo encontrar la clase Usuario.");
        }

    }

    /**
     * Método estático para comparar el contenido de dos listas estáticas de la
     * clase Usuario que almacenan objetos Usuario. Compara las listas teniendo
     * en cuenta: 1) Mismo número de elementos. 2) Mismo orden de los elementos.
     * 3) Los atributos de los objetos Usuario en su misma posición de las
     * listas son iguales.
     *
     * @return boolean Devuelve true si ambas listas tienen el mismo tamaño, el
     * mismo orden y los usuarios contenidos coinciden en todos sus atributos.
     */
    public static boolean compararListas() {

        // Variable que almacena booleano que determina si las listas a comparar son iguales
        boolean listasIguales = false;

        if (Usuario.listaUsuariosTemp.size() == Usuario.listaUsuariosDeserializada.size()) {
            for (int i = 0; i < Usuario.listaUsuariosTemp.size(); i++) {
                if (Usuario.listaUsuariosTemp.get(i).identificador.equals(Usuario.listaUsuariosDeserializada.get(i).identificador)
                        && Usuario.listaUsuariosTemp.get(i).contrasena.equals(Usuario.listaUsuariosDeserializada.get(i).contrasena)
                        && Usuario.listaUsuariosTemp.get(i).direccion.equals(Usuario.listaUsuariosDeserializada.get(i).direccion)
                        && Usuario.listaUsuariosTemp.get(i).anoNacimiento == Usuario.listaUsuariosDeserializada.get(i).anoNacimiento) {
                    listasIguales = true;
                } else {
                    listasIguales = false;
                }
            }
            return listasIguales;
        } else {
            return listasIguales;
        }

    }

}
