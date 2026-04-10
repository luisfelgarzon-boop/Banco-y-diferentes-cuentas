/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelo.BancoModelo;
import Modelo.Cuenta;
import Modelo.CuentaAhorros;
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
public class IntegracionCuentaAhorrosTest {

    private BancoModelo modelo;

    @BeforeEach
    public void preparar() {
        modelo = new BancoModelo();
        FechaApertura fecha = new FechaApertura(10, 4, 2024);
        modelo.agregarCuenta(
            new CuentaAhorros("A001", "Juan Torres", fecha, 1000.0, 5.0)
        );
    }

    @Test
    public void testCrearYBuscarCuentaAhorros() {
        Cuenta encontrada = modelo.buscarCuenta("A001");
        assertNotNull(encontrada);
        assertEquals("Juan Torres", encontrada.getNombreDueno());
    }

    @Test
    public void testConsignarYVerificarSaldoEnModelo() {
        Cuenta cuenta = modelo.buscarCuenta("A001");
        cuenta.consignar(500.0);
        assertEquals(1500.0, modelo.buscarCuenta("A001").getSaldo(), 0.001);
    }

    @Test
    public void testRetirarYVerificarSaldoEnModelo() {
        Cuenta cuenta = modelo.buscarCuenta("A001");
        boolean resultado = cuenta.retirar(400.0);
        assertTrue(resultado);
        assertEquals(600.0, modelo.buscarCuenta("A001").getSaldo(), 0.001);
    }

    @Test
    public void testRetirarMasDelSaldoNoAfectaElModelo() {
        Cuenta cuenta = modelo.buscarCuenta("A001");
        cuenta.retirar(9999.0);
        assertEquals(1000.0, modelo.buscarCuenta("A001").getSaldo(), 0.001);
    }

    @Test
    public void testFlujoCompletoConsignarRetirarConsignar() {
        Cuenta cuenta = modelo.buscarCuenta("A001");
        cuenta.consignar(200.0);  // 1200
        cuenta.retirar(500.0);    // 700
        cuenta.consignar(100.0);  // 800
        assertEquals(800.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testAplicarInteresSeRefleja() {
        CuentaAhorros cuenta = (CuentaAhorros) modelo.buscarCuenta("A001");
        cuenta.aplicarInteres(); // 5% de 1000 = 1050
        assertEquals(1050.0, modelo.buscarCuenta("A001").getSaldo(), 0.001);
    }

    @Test
    public void testImprimirDatosDesdeModeloContieneInfo() {
        Cuenta cuenta = modelo.buscarCuenta("A001");
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("A001"));
        assertTrue(datos.contains("Juan Torres"));
        assertTrue(datos.contains("Ahorros"));
    }
}