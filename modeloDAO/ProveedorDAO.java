package modeloDAO;

import accesoDatos.ICrud;
import accesoDatos.Persistencia;
import modeloDTO.ProveedorDTO;

import java.util.List;

/**
 * ProveedorDAO - Capa: Modelo DAO
 * Implementa la logica de acceso a datos para la entidad Proveedor.
 * En el diagrama UML:
 *   - Hereda de Persistencia (conexion al archivo): ProveedorDAO --|> Persistencia
 *   - Implementa ICrud (operaciones CRUD):          ProveedorDAO ..|> ICrud
 * Atributo del diagrama: listaProveedores.
 */
public class ProveedorDAO extends Persistencia<ProveedorDTO> implements ICrud {

    /**Lista en memoria de todos los proveedores, sincronizada con el archivo. */
    private List<ProveedorDTO> listaProveedores;

    /**Carga la lista desde el archivo al iniciar. */
    public ProveedorDAO() {
        super("proveedores.dat");
        this.listaProveedores = getLista();
    }

    /**RF1-Agrega el proveedor y persiste el cambio. */
    @Override
    public boolean insertar(ProveedorDTO dto) {
        try {
            listaProveedores.add(dto);
            guardar();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**RF2-Retorna el proveedor en la posicion indicada. */
    @Override
    public ProveedorDTO leer(int index) {
        if (index >= 0 && index < listaProveedores.size()) {
            return listaProveedores.get(index);
        }
        return null;
    }

    /**RF3-Actualiza el proveedor en la posición indicada y persiste el cambio. */
    @Override
    public boolean actualizar(int index, ProveedorDTO dto) {
        if (index >= 0 && index < listaProveedores.size()) {
            listaProveedores.set(index, dto);
            guardar();
            return true;
        }
        return false;
    }

    /**RF4-Elimina el proveedor en la posición indicada y persiste el cambio. */
    @Override
    public boolean eliminar(int index) {
        if (index >= 0 && index < listaProveedores.size()) {
            listaProveedores.remove(index);
            guardar();
            return true;
        }
        return false;
    }

    /**Busca el índice del proveedor por ID. Retorna -1 si no existe. */
    @Override
    public int buscarIndex(ProveedorDTO dto) {
        for (int i = 0; i < listaProveedores.size(); i++) {
            if (listaProveedores.get(i).getId() == dto.getId()) {
                return i;
            }
        }
        return -1;
    }

    /**RF5-Retorna la lista completa de proveedores. */
    @Override
    public List<ProveedorDTO> leerTodos() {
        return listaProveedores;
    }
}
