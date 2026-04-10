/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.BancoModelo;
import Modelo.Cuenta;
import Modelo.CuentaAhorros;
import Modelo.CuentaCorriente;
import Modelo.FechaApertura;
import Vista.BancoVista;

/**
 *
 * @author felip
 */
public class BancoControlador {

    private BancoModelo modelo;
    private BancoVista  vista;

    public BancoControlador(BancoModelo modelo, BancoVista vista) {
        this.modelo = modelo;
        this.vista  = vista;
        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.btnCrear.addActionListener(e -> crearCuenta());
        vista.btnConsignar.addActionListener(e -> consignar());
        vista.btnRetirar.addActionListener(e -> retirar());
        vista.btnVerDatos.addActionListener(e -> verDatos());
    }

    private void crearCuenta() {
        try {
            String numero  = vista.txtNumero.getText().trim();
            String titular = vista.txtTitular.getText().trim();
            int    dia     = Integer.parseInt(vista.txtDia.getText().trim());
            int    mes     = Integer.parseInt(vista.txtMes.getText().trim());
            int    anio    = Integer.parseInt(vista.txtAnio.getText().trim());
            double saldo   = Double.parseDouble(vista.txtSaldo.getText().trim());
            double extra   = Double.parseDouble(vista.txtExtra.getText().trim());

            if (numero.isEmpty() || titular.isEmpty()) {
                vista.mostrarError("El número y el titular no pueden estar vacíos.");
                return;
            }

            if (modelo.buscarCuenta(numero) != null) {
                vista.mostrarError("Ya existe una cuenta con ese número.");
                return;
            }

            FechaApertura fecha = new FechaApertura(dia, mes, anio);
            Cuenta nueva;

            if (vista.cboTipo.getSelectedIndex() == 0) {
                nueva = new CuentaAhorros(numero, titular, fecha, saldo, extra);
            } else {
                nueva = new CuentaCorriente(numero, titular, fecha, saldo, extra);
            }

            modelo.agregarCuenta(nueva);
            vista.mostrarMensaje("Cuenta creada exitosamente.\n\n" + nueva.imprimirDatos());

        } catch (NumberFormatException ex) {
            vista.mostrarError("Verifica que los campos numéricos sean correctos.");
        }
    }

    private void consignar() {
        Cuenta cuenta = obtenerCuentaDeOperacion();
        if (cuenta == null) return;

        try {
            double monto = Double.parseDouble(vista.txtMonto.getText().trim());
            cuenta.consignar(monto);
            vista.mostrarMensaje("Consignacion exitosa.\nNuevo saldo: $"
                    + String.format("%.2f", cuenta.getSaldo()));
        } catch (NumberFormatException ex) {
            vista.mostrarError("El monto ingresado no es valido.");
        }
    }

    private void retirar() {
        Cuenta cuenta = obtenerCuentaDeOperacion();
        if (cuenta == null) return;

        try {
            double monto = Double.parseDouble(vista.txtMonto.getText().trim());
            boolean exitoso = cuenta.retirar(monto);

            if (exitoso) {
                vista.mostrarMensaje("Retiro exitoso.\nNuevo saldo: $"
                        + String.format("%.2f", cuenta.getSaldo()));
            } else {
                vista.mostrarError("Saldo insuficiente. No hay cupo disponible.");
            }
        } catch (NumberFormatException ex) {
            vista.mostrarError("El monto ingresado no es valido.");
        }
    }

    private void verDatos() {
        Cuenta cuenta = obtenerCuentaDeOperacion();
        if (cuenta != null) {
            vista.mostrarMensaje(cuenta.imprimirDatos());
        }
    }

    // Metodo auxiliar: busca la cuenta por el numero escrito en operaciones
    private Cuenta obtenerCuentaDeOperacion() {
        String numero = vista.txtNumeroBuscar.getText().trim();
        Cuenta cuenta = modelo.buscarCuenta(numero);
        if (cuenta == null) {
            vista.mostrarError("No se encontro ninguna cuenta con el numero: " + numero);
        }
        return cuenta;
    }
}