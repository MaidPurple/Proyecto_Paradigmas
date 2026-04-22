package controlador;

import modeloDAO.ProveedorDAO;
import modeloDTO.ProveedorDTO;
import vista.ProveedorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ProveedorControlador - Capa: Controlador
 * Intermediario entre la Vista y el Modelo. Recibe los eventos de la UI,
 * construye el DTO con los datos del formulario y delega las operaciones al DAO.
 * En el diagrama UML:
 *   - Implementa ActionListener:           ProveedorControlador ..> ActionListener
 *   - Recibe la vista como parte de él:    ProveedorControlador <-- ProveedorUI : es parte
 *   - Usa el DAO como modelo:              ProveedorControlador <-- ProveedorDAO : tiene
 *   - Crea y usa el DTO:                   ProveedorControlador <-- ProveedorDTO : crea
 * Atributos del diagrama: ui, dao, dto.
 */
public class ProveedorControlador implements ActionListener {

    private ProveedorUI ui;
    private ProveedorDAO dao;
    private ProveedorDTO dto;

    /**
     * Constructor - recibe la vista, instancia el DAO y registra los listeners
     * en cada boton de la UI.
     */
    public ProveedorControlador(ProveedorUI ui) {
        this.ui = ui;
        this.dao = new ProveedorDAO();
        this.dto = new ProveedorDTO();

        ui.btnInsertar.addActionListener(this);
        ui.btnBuscar.addActionListener(this);
        ui.btnModificar.addActionListener(this);
        ui.btnEliminar.addActionListener(this);
        ui.btnMostrar.addActionListener(this);
    }

    /**
     * Carga los datos del formulario en this.dto.
     * Si el ID no es numerico, muestra error y asigna null.
     */
    private void cargarDTODesdeCampos() {
        try {
            int id = Integer.parseInt(ui.txtId.getText().trim());
            String nombre = ui.txtNombre.getText().trim();
            String telefono = ui.txtTelefono.getText().trim();
            String direccion = ui.txtDireccion.getText().trim();
            this.dto = new ProveedorDTO(id, nombre, telefono, direccion);
        } catch (NumberFormatException ex) {
            ui.areaResultado.setText("Error: el ID debe ser un número entero.");
            this.dto = null;
        }
    }

    /**
     * actionPerformed - definido en el diagrama UML mediante ActionListener.
     * Identifica el boton presionado y delega la operación correspondiente.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        cargarDTODesdeCampos();
        if (this.dto == null) return;

        Object fuente = e.getSource();
        if (fuente == ui.btnInsertar) {
            insertar(this.dto);
        } else if (fuente == ui.btnBuscar) {
            ProveedorDTO resultado = buscar(this.dto);
            if (resultado != null) {
                ui.areaResultado.setText("Encontrado: " + resultado.toString());
            } else {
                ui.areaResultado.setText("Proveedor no encontrado.");
            }
        } else if (fuente == ui.btnModificar) {
            int index = dao.buscarIndex(this.dto);
            modificar(index, this.dto);
        } else if (fuente == ui.btnEliminar) {
            int index = dao.buscarIndex(this.dto);
            eliminar(index);
        } else if (fuente == ui.btnMostrar) {
            mostrar();
        }
    }

    /**RF1-Delega la inserción al DAO y muestra el resultado en la UI. */
    public void insertar(ProveedorDTO dto) {
        boolean resultado = dao.insertar(dto);
        ui.areaResultado.setText(resultado ? "Proveedor insertado correctamente." : "Error al insertar proveedor.");
    }

    /**RF2-Busca el proveedor por ID usando el DAO. Retorna null si no existe. */
    public ProveedorDTO buscar(ProveedorDTO dto) {
        int index = dao.buscarIndex(dto);
        if (index >= 0) {
            return dao.leer(index);
        }
        return null;
    }

    /**RF3-Delega la actualización al DAO y muestra el resultado en la UI. */
    public void modificar(int index, ProveedorDTO dto) {
        if (index < 0) {
            ui.areaResultado.setText("Proveedor no encontrado para modificar.");
            return;
        }
        boolean resultado = dao.actualizar(index, dto);
        ui.areaResultado.setText(resultado ? "Proveedor modificado correctamente." : "Error al modificar proveedor.");
    }

    /**RF4-Delega la eliminación al DAO y muestra el resultado en la UI. */
    public void eliminar(int index) {
        if (index < 0) {
            ui.areaResultado.setText("Proveedor no encontrado para eliminar.");
            return;
        }
        boolean resultado = dao.eliminar(index);
        ui.areaResultado.setText(resultado ? "Proveedor eliminado correctamente." : "Error al eliminar proveedor.");
    }

    /**RF5-Obtiene todos los proveedores del DAO y los muestra en la UI. */
    public void mostrar() {
        List<ProveedorDTO> lista = dao.leerTodos();
        if (lista.isEmpty()) {
            ui.areaResultado.setText("No hay proveedores registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("Lista de Proveedores:\n");
        for (ProveedorDTO p : lista) {
            sb.append(p.toString()).append("\n");
        }
        ui.areaResultado.setText(sb.toString());
    }
}
