package modeloDTO;

import java.io.Serializable;

/**
 * ProveedorDTO - Capa: Modelo DTO
 * Objeto de transferencia de datos que representa un Proveedor.
 * En el diagrama UML implementa Serializable para poder ser
 * almacenado en archivos de objetos mediante Persistencia.
 * Relación: ProveedorDTO ..> Serializable
 * Atributos del diagrama: id, nombre, telefono, direccion.
 */
public class ProveedorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String telefono;
    private String direccion;

    public ProveedorDTO() {}

    public ProveedorDTO(int id, String nombre, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre +
               " | Teléfono: " + telefono + " | Dirección: " + direccion;
    }
}
