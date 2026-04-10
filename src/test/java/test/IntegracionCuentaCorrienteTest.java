/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelo.BancoModelo;
import Modelo.Cuenta;
import Modelo.CuentaCorriente;
import Modelo.FechaApertura;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author felip
 */
public class IntegracionCuentaCorrienteTest {

    private BancoModelo modelo;

    @BeforeEach
    public void preparar() {
        modelo = new BancoModelo();
        FechaApertura fecha = new FechaApertura(5, 6, 2023);
        modelo.agregarCuenta(
            new CuentaCorriente("C001", "Maria Gil", fecha, 500.0, 300.0)
        );
    }

    @Test
    public void testCrearYBuscarCuentaCorriente() {
        Cuenta encontrada = modelo.buscarCuenta("C001");
        assertNotNull(encontrada);
        assertEquals("Maria Gil", encontrada.getNombreDueno());
    }

    @Test
    public void testRetirarUsandoSobregroYVerificarEnModelo() {
        Cuenta cuenta = modelo.buscarCuenta("C001");
        boolean resultado = cuenta.retirar(700.0);
        assertTrue(resultado);
        assertEquals(-200.0, modelo.buscarCuenta("C001").getSaldo(), 0.001);
    }

    @Test
    public void testRetirarSuperandoCupoNoAfectaModelo() {
        Cuenta cuenta = modelo.buscarCuenta("C001");
        cuenta.retirar(9999.0);
        assertEquals(500.0, modelo.buscarCuenta("C001").getSaldo(), 0.001);
    }

    @Test
    public void testConsignarSacarDelSobregiro() {
        Cuenta cuenta = modelo.buscarCuenta("C001");
        cuenta.retirar(700.0);   // saldo queda en -200
        cuenta.consignar(300.0); // saldo queda en 100
        assertEquals(100.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testCupoDisponibleDespuesDeVariasOperaciones() {
        CuentaCorriente cuenta = (CuentaCorriente) modelo.buscarCuenta("C001");
        cuenta.retirar(200.0);    // saldo 300
        cuenta.consignar(100.0);  // saldo 400
        // cupo disponible = 400 + 300 = 700
        assertEquals(700.0, cuenta.getCupoDisponible(), 0.001);
    }

    @Test
    public void testFlujoCompletoSobregroYRecuperacion() {
        Cuenta cuenta = modelo.buscarCuenta("C001");
        cuenta.retirar(800.0);    // agota todo: saldo -300
        cuenta.consignar(1000.0); // recupera: saldo 700
        assertTrue(cuenta.retirar(600.0)); // retira 600: saldo 100
        assertEquals(100.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testImprimirDatosDesdeModeloContieneSobregiro() {
        Cuenta cuenta = modelo.buscarCuenta("C001");
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("Corriente"));
        assertTrue(datos.contains("Maria Gil"));
        assertTrue(datos.contains("300"));
    }
}