/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author felip
 */

public class CuentaAhorrosTest {

    private CuentaAhorros cuenta;

    @BeforeEach
    public void preparar() {
        FechaApertura fecha = new FechaApertura(10, 5, 2024);
        cuenta = new CuentaAhorros("A001", "Carlos Perez", fecha, 1000.0, 5.0);
    }

    @Test
    public void testSaldoInicialCorrecto() {
        assertEquals(1000.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testConsignarAumentaElSaldo() {
        cuenta.consignar(500.0);
        assertEquals(1500.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testConsignarMontoNegativoNoAfectaSaldo() {
        cuenta.consignar(-200.0);
        assertEquals(1000.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testConsignarCeroNoAfectaSaldo() {
        cuenta.consignar(0);
        assertEquals(1000.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarDisminuyeElSaldo() {
        boolean resultado = cuenta.retirar(300.0);
        assertTrue(resultado);
        assertEquals(700.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarExactoElSaldoQuedaEnCero() {
        boolean resultado = cuenta.retirar(1000.0);
        assertTrue(resultado);
        assertEquals(0.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarMasDelSaldoRetornaFalso() {
        boolean resultado = cuenta.retirar(1500.0);
        assertFalse(resultado);
    }

    @Test
    public void testRetirarMasDelSaldoNoModificaElSaldo() {
        cuenta.retirar(9999.0);
        assertEquals(1000.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarMontoNegativoRetornaFalso() {
        boolean resultado = cuenta.retirar(-100.0);
        assertFalse(resultado);
    }

    @Test
    public void testAplicarInteresCalculaCorrecto() {
        // 5% de 1000 = 50 → saldo queda en 1050
        cuenta.aplicarInteres();
        assertEquals(1050.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testGetTasaInteres() {
        assertEquals(5.0, cuenta.getTasaInteres(), 0.001);
    }

    @Test
    public void testImprimirDatosContieneNumero() {
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("A001"));
    }

    @Test
    public void testImprimirDatosContieneTitular() {
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("Carlos Perez"));
    }

    @Test
    public void testImprimirDatosContieneTipoAhorros() {
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("Ahorros"));
    }

    @Test
    public void testImprimirDatosConSeparador() {
        String datos = cuenta.imprimirDatos(": ");
        assertTrue(datos.contains(": "));
        assertTrue(datos.contains("A001"));
    }
}