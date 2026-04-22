package vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * ProveedorUI - Capa: Vista
 *
 * Interfaz gráfica del sistema de gestión de proveedores (Java Swing).
 * Estilo inspirado en los colores de Hoopa: morado claro y gris.
 * El formulario y el panel de resultados se ubican lado a lado.
 *
 * En el diagrama UML es parte del ProveedorControlador:
 * ProveedorControlador <-- ProveedorUI : es parte
 *
 * Expone los campos y botones para que el Controlador registre los eventos.
 */
public class ProveedorUI extends JFrame {

    // --- Paleta Hoopa ---
    private static final Color MORADO_PRIMARIO   = new Color(126, 87, 194);
    private static final Color MORADO_SUAVE      = new Color(179, 157, 219);
    private static final Color MORADO_CLARO      = new Color(237, 231, 246);
    private static final Color GRIS_FONDO        = new Color(245, 245, 248);
    private static final Color GRIS_OSCURO       = new Color(80, 75, 90);
    private static final Color BLANCO            = Color.WHITE;
    private static final Font  FUENTE_TITULO     = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font  FUENTE_NORMAL     = new Font("Segoe UI", Font.PLAIN, 12);

    /** Campos de entrada correspondientes a los atributos de ProveedorDTO. */
    public JTextField txtId;
    public JTextField txtNombre;
    public JTextField txtTelefono;
    public JTextField txtDireccion;

    /** Botones que disparan las operaciones CRUD en el Controlador. */
    public JButton btnInsertar;
    public JButton btnBuscar;
    public JButton btnModificar;
    public JButton btnEliminar;
    public JButton btnMostrar;

    /** Área donde se muestran los resultados de cada operación. */
    public JTextArea areaResultado;

    /** Constructor - inicializa y muestra la ventana. */
    public ProveedorUI() {
        intComponents();
    }

    /**
     * intComponents - Construye y organiza todos los componentes gráficos.
     * Método definido en el diagrama UML.
     */
    public void intComponents() {
        setTitle("Sistema de Gestión de Proveedores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 500);
        setLocationRelativeTo(null);
        setBackground(GRIS_FONDO);
        setLayout(new BorderLayout(0, 0));

        // ── Header ──
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(MORADO_PRIMARIO);
        header.setBorder(new EmptyBorder(14, 20, 14, 20));
        JLabel titulo = new JLabel("  Sistema de Gestión de Proveedores");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        titulo.setForeground(BLANCO);
        titulo.setIcon(crearIconoProveedor());
        header.add(titulo, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // ── Panel central: formulario IZQUIERDA | resultados DERECHA ──
        JPanel centro = new JPanel(new GridLayout(1, 2, 15, 0));
        centro.setBackground(GRIS_FONDO);
        centro.setBorder(new EmptyBorder(15, 15, 10, 15));

        // ── IZQUIERDA: formulario + botones ──
        JPanel izquierda = new JPanel(new BorderLayout(0, 12));
        izquierda.setBackground(GRIS_FONDO);

        // Formulario redondeado
        JPanel panelForm = new PanelRedondeado(18, BLANCO);
        panelForm.setLayout(new GridBagLayout());
        panelForm.setBorder(new EmptyBorder(14, 16, 14, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 5, 6, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelForm.add(labelForm("ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtId = campoTexto();
        panelForm.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelForm.add(labelForm("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombre = campoTexto();
        panelForm.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelForm.add(labelForm("Teléfono:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtTelefono = campoTexto();
        panelForm.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panelForm.add(labelForm("Dirección:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtDireccion = campoTexto();
        panelForm.add(txtDireccion, gbc);

        // Titulo del panel formulario
        JLabel lblForm = new JLabel("Datos del Proveedor");
        lblForm.setFont(FUENTE_TITULO);
        lblForm.setForeground(MORADO_PRIMARIO);
        lblForm.setBorder(new EmptyBorder(0, 2, 4, 0));

        JPanel wrapForm = new JPanel(new BorderLayout(0, 4));
        wrapForm.setBackground(GRIS_FONDO);
        wrapForm.add(lblForm, BorderLayout.NORTH);
        wrapForm.add(panelForm, BorderLayout.CENTER);

        // Botones CRUD
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 8, 8));
        panelBotones.setBackground(GRIS_FONDO);

        btnInsertar  = boton("Insertar",      new Color(103, 58, 183));
        btnBuscar    = boton("Buscar",         new Color(149, 117, 205));
        btnModificar = boton("Modificar",      new Color(123, 97, 175));
        btnEliminar  = boton("Eliminar",       new Color(69, 39, 160));
        btnMostrar   = boton("Mostrar Todos",  MORADO_SUAVE);

        panelBotones.add(btnInsertar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMostrar);

        izquierda.add(wrapForm, BorderLayout.CENTER);
        izquierda.add(panelBotones, BorderLayout.SOUTH);

        // ── DERECHA: área de resultados ──
        JPanel derecha = new JPanel(new BorderLayout(0, 4));
        derecha.setBackground(GRIS_FONDO);

        JLabel lblResultados = new JLabel("Resultados");
        lblResultados.setFont(FUENTE_TITULO);
        lblResultados.setForeground(MORADO_PRIMARIO);
        lblResultados.setBorder(new EmptyBorder(0, 2, 4, 0));

        areaResultado = new JTextArea();
        areaResultado.setFont(FUENTE_NORMAL);
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setBackground(MORADO_CLARO);
        areaResultado.setForeground(GRIS_OSCURO);
        areaResultado.setBorder(new EmptyBorder(10, 12, 10, 12));

        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        // Panel redondeado que envuelve el scroll
        JPanel wrapScroll = new PanelRedondeado(18, MORADO_CLARO);
        wrapScroll.setLayout(new BorderLayout());
        wrapScroll.add(scroll, BorderLayout.CENTER);

        derecha.add(lblResultados, BorderLayout.NORTH);
        derecha.add(wrapScroll, BorderLayout.CENTER);

        centro.add(izquierda);
        centro.add(derecha);
        add(centro, BorderLayout.CENTER);

        // ── Barra de estado ──
        JLabel lblEstado = new JLabel("  Listo");
        lblEstado.setFont(FUENTE_NORMAL);
        lblEstado.setForeground(BLANCO);
        lblEstado.setBackground(MORADO_SUAVE);
        lblEstado.setOpaque(true);
        lblEstado.setBorder(new EmptyBorder(5, 12, 5, 12));
        add(lblEstado, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ─────────────────────────────────────────
    // Métodos auxiliares de construcción de UI
    // ─────────────────────────────────────────

    private JLabel labelForm(String texto) {
        JLabel l = new JLabel(texto);
        l.setFont(FUENTE_TITULO);
        l.setForeground(GRIS_OSCURO);
        return l;
    }

    private JTextField campoTexto() {
        JTextField tf = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);
                super.paintComponent(g);
                g2.dispose();
            }
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(MORADO_SUAVE);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);
                g2.dispose();
            }
        };
        tf.setFont(FUENTE_NORMAL);
        tf.setOpaque(false);
        tf.setBorder(new EmptyBorder(5, 8, 5, 8));
        tf.setBackground(BLANCO);
        return tf;
    }

    private JButton boton(String texto, Color color) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isPressed() ? color.darker() : color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.setForeground(BLANCO);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 10, 8, 10));
        return btn;
    }

    /** Panel con esquinas redondeadas pintadas manualmente. */
    private static class PanelRedondeado extends JPanel {
        private final int radio;
        private final Color fondo;

        public PanelRedondeado(int radio, Color fondo) {
            this.radio = radio;
            this.fondo = fondo;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fondo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Icono simple de caja/proveedor dibujado con Graphics2D. */
    private Icon crearIconoProveedor() {
        return new Icon() {
            public int getIconWidth()  { return 22; }
            public int getIconHeight() { return 22; }
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BLANCO);
                g2.fillRoundRect(x + 2, y + 6, 18, 14, 4, 4);
                g2.fillRoundRect(x + 7, y + 2, 8, 6, 3, 3);
                g2.setColor(MORADO_SUAVE);
                g2.setStroke(new BasicStroke(1.2f));
                g2.drawLine(x + 2, y + 10, x + 20, y + 10);
            }
        };
    }
}