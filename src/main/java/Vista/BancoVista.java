/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author felip
 */
public class BancoVista extends JFrame {

    // Pestana: Crear cuenta
    public JTextField txtNumero  = new JTextField(15);
    public JTextField txtTitular = new JTextField(15);
    public JTextField txtDia     = new JTextField(4);
    public JTextField txtMes     = new JTextField(4);
    public JTextField txtAnio    = new JTextField(6);
    public JTextField txtSaldo   = new JTextField(10);
    public JTextField txtExtra   = new JTextField(10);
    public JComboBox<String> cboTipo = new JComboBox<>(
            new String[]{"Ahorros", "Corriente"});
    public JButton btnCrear = new JButton("Crear cuenta");

    // Pestana: Operaciones
    public JTextField txtNumeroBuscar = new JTextField(15);
    public JTextField txtMonto        = new JTextField(10);
    public JButton btnConsignar = new JButton("Consignar");
    public JButton btnRetirar   = new JButton("Retirar");
    public JButton btnVerDatos  = new JButton("Ver datos");

    // Area de resultados (parte de abajo)
    public JTextArea areaResultado = new JTextArea(8, 45);

    public BancoVista() {
        super("Sistema Bancario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Crear Cuenta",  construirPanelCrear());
        pestanas.addTab("Operaciones",   construirPanelOperaciones());
        add(pestanas, BorderLayout.CENTER);

        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(areaResultado), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel construirPanelCrear() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        agregarFila(panel, gbc, 0, "Numero de cuenta:", txtNumero);
        agregarFila(panel, gbc, 1, "Titular:",           txtTitular);
        agregarFila(panel, gbc, 2, "Dia de apertura:",   txtDia);
        agregarFila(panel, gbc, 3, "Mes de apertura:",   txtMes);
        agregarFila(panel, gbc, 4, "Anio de apertura:",  txtAnio);
        agregarFila(panel, gbc, 5, "Saldo inicial:",     txtSaldo);
        agregarFila(panel, gbc, 6, "Tipo de cuenta:",    cboTipo);
        agregarFila(panel, gbc, 7, "Tasa (%) o Sobregiro ($):", txtExtra);

        gbc.gridx = 1; gbc.gridy = 8;
        panel.add(btnCrear, gbc);

        return panel;
    }

    private JPanel construirPanelOperaciones() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        agregarFila(panel, gbc, 0, "Numero de cuenta:", txtNumeroBuscar);
        agregarFila(panel, gbc, 1, "Monto:",             txtMonto);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        botones.add(btnConsignar);
        botones.add(btnRetirar);
        botones.add(btnVerDatos);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(botones, gbc);

        return panel;
    }

    // Metodo auxiliar para agregar una fila etiqueta + campo
    private void agregarFila(JPanel panel, GridBagConstraints gbc,
                              int fila, String etiqueta, JComponent campo) {
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1;
        panel.add(campo, gbc);
    }

    public void mostrarMensaje(String mensaje) {
        areaResultado.setText(mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
