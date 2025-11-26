/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AD01_2526_T01;

import java.io.BufferedWriter;
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

/**
 * Clase Usuario
 *
 * @author Antonio Naranjo Castillo
 */
public class Usuario implements Serializable {

    private static final long serialVersionUID = 42L;

    private String identificador;
    private String contrasena;
    private String direccion;
    private int anoNacimiento;

    // Atributo estático de la clase Usuario donde se almacenarán todos los usurios que se generen o eliminen
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    private static List<String> listaUsuariosTXT = new ArrayList<>();

    /**
     * Constructor de la clase Usuario
     *
     * @param id
     * @param password
     * @param address
     * @param yearBirth
     */
    public Usuario(String id, String password, String address, int yearBirth) {
        this.identificador = id;
        this.contrasena = password;
        this.direccion = address;
        this.anoNacimiento = yearBirth;
    }

    /**
     * Método getter identificador del usuario
     *
     * @return
     */
    public String getID() {
        return identificador;
    }

    /**
     * Método getter contraseña del usuario
     *
     * @return
     */
    public String getPassword() {
        return contrasena;
    }

    /**
     * Método getter dirección del usuario
     *
     * @return
     */
    public String getAddress() {
        return direccion;
    }

    /**
     * Método getter año nacimiento del usuario
     *
     * @return
     */
    public int getYearBirth() {
        return anoNacimiento;
    }

    /**
     * Método setter identificador del usuario
     *
     * @param id
     */
    public void setID(String id) {
        this.identificador = id;
    }

    /**
     * Método setter contraseña del usuario
     *
     * @param password
     */
    public void setPassword(String password) {
        this.contrasena = password;
    }

    /**
     * Método setter dirección del usuario
     *
     * @param address
     */
    public void setAddress(String address) {
        this.direccion = address;
    }

    /**
     * Método setter año nacimiento del usuario
     *
     * @param yearBirth
     */
    public void setYearBirth(int yearBirth) {
        this.anoNacimiento = yearBirth;
    }

    /**
     * Método para agregar un usuario a la lista de usuarios
     *
     * @param user
     */
    public void agregarUsuario(Usuario user) {
        listaUsuarios.add(user);
    }

    /**
     * Método para mostrar en consola la lista de usuarios
     */
    public static void mostrarListaUsuarios() {
        for (Usuario user : listaUsuarios) {
            System.out.println(user.toString());
        }
    }

    /**
     * Método para borrar un usuario a la lista de usuarios
     *
     * @param identificador
     */
    public static void borrarUsuario(String identificador) {

        // Se instancia un iterador de la lista de usuarios para eliminar el usuario de manera segura
        Iterator<Usuario> iterador = listaUsuarios.iterator();
        while (iterador.hasNext()) {
            Usuario user = iterador.next();
            if (user.getID().equals(identificador)) {
                iterador.remove();
            }
        }

    }

    /**
     * Método para guardar la lista de usuarios en un archivo dado
     *
     * @param nombreArchivo
     */
    public static void guardarLista(String nombreArchivo) {

        // Serializar la lista completa en el fichero
        try (
                // Escribir bytes físicamente en el archivo
                FileOutputStream fileOut = new FileOutputStream(nombreArchivo); // Convertir el objeto listaUsuarios en bytes
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            // Escribir el objeto listaUsuarios completo (Serialización)
            out.writeObject(listaUsuarios);

        } catch (IOException ex) {
            // Manejo de la excepción
            System.err.println("Error de E/S durante la serialización: " + ex.getMessage());

        }
    }

    /**
     * Método para cargar la lista de usuarios desde un archivo dado
     *
     * @param nombreArchivo
     */
    public static void cargarLista(String nombreArchivo) {

        listaUsuarios.clear();

        try (
                FileInputStream fileIn = new FileInputStream(nombreArchivo); ObjectInputStream in = new ObjectInputStream(fileIn)) {

            listaUsuarios = (List<Usuario>) in.readObject();

        } catch (FileNotFoundException ex) {
            System.err.println("Error: El archivo " + nombreArchivo + "no existe.");
        } catch (IOException ex) {
            System.err.println("Error de Entrada/Salida: Falló la lectura del fichero.");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error de clase: No se pudo encontrar la clase Usuario.");
        }

    }

    /**
     * Método para guardar la lista de usuarios en un archivo de texto txt
     *
     * @param nombreArchivo
     */
    public static void escribirTXT(String nombreArchivo) {
        for (Usuario user : listaUsuarios) {
            listaUsuariosTXT.add(user.toString());
        }
        try (FileWriter fw = new FileWriter(nombreArchivo, true); BufferedWriter bw = new BufferedWriter(fw)) {
            for (String str : listaUsuariosTXT) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException ex) {
            // Manejo de excepciones de Entrada/Salida
            System.err.println("Error de E/S al escribir en el fichero: " + ex.getMessage());
        }
    }

    /**
     * Método toString para mostrar los datos del usuario en una cadena de
     * caracteres
     */
    @Override
    public String toString() {
        return String.format("Usuario [Identificador= %s, Contraseña= %s, Dirección= %s, Año de nacimiento= %d]",
                this.identificador,
                this.contrasena,
                this.direccion,
                this.anoNacimiento);
    }
}
