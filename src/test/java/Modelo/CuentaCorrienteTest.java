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

public class CuentaCorrienteTest {

    private CuentaCorriente cuenta;

    @BeforeEach
    public void preparar() {
        FechaApertura fecha = new FechaApertura(1, 3, 2023);
        cuenta = new CuentaCorriente("C001", "Maria Lopez", fecha, 500.0, 300.0);
    }

    @Test
    public void testSaldoInicialCorrecto() {
        assertEquals(500.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testConsignarAumentaElSaldo() {
        cuenta.consignar(200.0);
        assertEquals(700.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarDentroDelSaldoNormal() {
        boolean resultado = cuenta.retirar(400.0);
        assertTrue(resultado);
        assertEquals(100.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarUsandoSobregiroParcial() {
        // Saldo 500, retira 700 → queda en -200 (dentro del sobregiro de 300)
        boolean resultado = cuenta.retirar(700.0);
        assertTrue(resultado);
        assertEquals(-200.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarAgotandoTodoElCupo() {
        // Saldo 500 + sobregiro 300 = 800 disponible
        boolean resultado = cuenta.retirar(800.0);
        assertTrue(resultado);
        assertEquals(-300.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarSuperandoElSobregroRetornaFalso() {
        // Intenta retirar 801, supera el limite de 800
        boolean resultado = cuenta.retirar(801.0);
        assertFalse(resultado);
    }

    @Test
    public void testRetirarSuperandoSobregroNoModificaSaldo() {
        cuenta.retirar(9999.0);
        assertEquals(500.0, cuenta.getSaldo(), 0.001);
    }

    @Test
    public void testRetirarMontoNegativoRetornaFalso() {
        boolean resultado = cuenta.retirar(-50.0);
        assertFalse(resultado);
    }

    @Test
    public void testGetCupoSobregiro() {
        assertEquals(300.0, cuenta.getCupoSobregiro(), 0.001);
    }

    @Test
    public void testGetCupoDisponibleSinUsar() {
        // Saldo 500 + sobregiro 300 = 800 disponibles
        assertEquals(800.0, cuenta.getCupoDisponible(), 0.001);
    }

    @Test
    public void testGetCupoDisponibleDespuesDeRetirar() {
        cuenta.retirar(200.0);
        // Saldo queda en 300 + sobregiro 300 = 600 disponibles
        assertEquals(600.0, cuenta.getCupoDisponible(), 0.001);
    }

    @Test
    public void testGetCupoDisponibleEnSobregiro() {
        cuenta.retirar(700.0);
        // Saldo queda en -200 + sobregiro 300 = 100 disponibles
        assertEquals(100.0, cuenta.getCupoDisponible(), 0.001);
    }

    @Test
    public void testImprimirDatosContieneTipoCorriente() {
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("Corriente"));
    }

    @Test
    public void testImprimirDatosContieneSobregiro() {
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("300"));
    }

    @Test
    public void testImprimirDatosContieneTitular() {
        String datos = cuenta.imprimirDatos();
        assertTrue(datos.contains("Maria Lopez"));
    }
}