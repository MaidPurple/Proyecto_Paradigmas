package run;

import controlador.ProveedorControlador;
import vista.ProveedorUI;

/**
 * SistemaProveedoresMVC - Punto de entrada del programa.
 * Instancia la Vista y el Controlador para arrancar el sistema.
 * El Controlador recibe la UI, conecta el DAO y registra los listeners,
 * siguiendo el flujo del diagrama UML:
 *   ProveedorUI → ProveedorControlador → ProveedorDAO → Persistencia(archivo)
 */
public class SistemaProveedoresMVC {
    public static void main(String[] args) {
        ProveedorUI ui = new ProveedorUI();
        new ProveedorControlador(ui);
    }
}
