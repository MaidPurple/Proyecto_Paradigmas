package accesoDatos;

import modeloDTO.ProveedorDTO;
import java.util.List;

/**
 * ICrud - Capa: Acceso a Datos
 * Define las operaciones CRUD del sistema.
 * Según el diagrama UML, es implementada por ProveedorDAO
 * mediante la relación: ProveedorDAO ..|> ICrud
 */
public interface ICrud {

    /**RF1-Agrega un nuevo proveedor a la lista persistente. */
    boolean insertar(ProveedorDTO dto);

    /**RF2-Retorna el proveedor ubicado en la posicion indicada. */
    ProveedorDTO leer(int index);

    /**RF3-Reemplaza el proveedor en la posición indicada con los nuevos datos. */
    boolean actualizar(int index, ProveedorDTO dto);

    /**RF4-Elimina el proveedor ubicado en la posición indicada. */
    boolean eliminar(int index);

    /**Retorna la posición del proveedor en la lista, o -1 si no existe. */
    int buscarIndex(ProveedorDTO dto);

    /**RF5-Retorna la lista completa de proveedores registrados. */
    List<ProveedorDTO> leerTodos();
}
