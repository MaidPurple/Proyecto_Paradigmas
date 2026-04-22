package accesoDatos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistencia - Capa: Acceso a Datos
 * Clase genérica que maneja la lectura y escritura de objetos en archivos binarios.
 * En el diagrama UML, ProveedorDAO hereda de esta clase:
 * ProveedorDAO --|> Persistencia
 * Atributos del diagrama: archivo, lista, entrada, salida.
 */
public class Persistencia<T> {

    /**Ruta del archivo donde se persisten los objetos. */
    private String archivo;

    /**Lista en memoria que refleja el contenido del archivo. */
    private List<T> lista;

    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    /**
     * Constructor - carga la lista desde el archivo si existe.
     * @param archivo ruta del archivo de objetos.
     */
    public Persistencia(String archivo) {
        this.archivo = archivo;
        this.lista = new ArrayList<>();
        cargar();
    }

    /**Carga la lista desde el archivo binario al iniciar. */
    @SuppressWarnings("unchecked")
    private void cargar() {
        File f = new File(archivo);
        if (!f.exists()) return;
        try {
            entrada = new ObjectInputStream(new FileInputStream(archivo));
            lista = (List<T>) entrada.readObject();
            entrada.close();
        } catch (Exception e) {
            lista = new ArrayList<>();
        }
    }

    /**Guarda el estado actual de la lista en el archivo binario. */
    public void guardar() {
        try {
            salida = new ObjectOutputStream(new FileOutputStream(archivo));
            salida.writeObject(lista);
            salida.close();
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public List<T> getLista() { return lista; }
    public void setLista(List<T> lista) { this.lista = lista; }
}
