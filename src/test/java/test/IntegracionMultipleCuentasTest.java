/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import Modelo.BancoModelo;
import Modelo.Cuenta;
import Modelo.CuentaAhorros;
import Modelo.CuentaCorriente;
import Modelo.FechaApertura;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author felip
 */
public class IntegracionMultipleCuentasTest {

    private BancoModelo modelo;

    @BeforeEach
    public void preparar() {
        modelo = new BancoModelo();

        modelo.agregarCuenta(new CuentaAhorros(
            "A001", "Ana Ruiz",
            new FechaApertura(1, 1, 2024), 2000.0, 4.0));

        modelo.agregarCuenta(new CuentaAhorros(
            "A002", "Luis Mora",
            new FechaApertura(15, 3, 2023), 800.0, 3.5));

        modelo.agregarCuenta(new CuentaCorriente(
            "C001", "Pedro Diaz",
            new FechaApertura(20, 7, 2022), 1000.0, 500.0));
    }

    @Test
    public void testTresClientesEnElModelo() {
        assertEquals(3, modelo.listarCuentas().size());
    }

    @Test
    public void testCadaCuentaTieneSuPropioSaldo() {
        assertEquals(2000.0, modelo.buscarCuenta("A001").getSaldo(), 0.001);
        assertEquals(800.0,  modelo.buscarCuenta("A002").getSaldo(), 0.001);
        assertEquals(1000.0, modelo.buscarCuenta("C001").getSaldo(), 0.001);
    }

    @Test
    public void testOperacionEnUnaCuentaNoAfectaLasOtras() {
        modelo.buscarCuenta("A001").consignar(500.0);
        // A002 y C001 no deben cambiar
        assertEquals(800.0,  modelo.buscarCuenta("A002").getSaldo(), 0.001);
        assertEquals(1000.0, modelo.buscarCuenta("C001").getSaldo(), 0.001);
    }

    @Test
    public void testPolimorfismoRetirarAhorrosVsCorriente() {
        Cuenta ahorros   = modelo.buscarCuenta("A002");
        Cuenta corriente = modelo.buscarCuenta("C001");

        // Ahorros no puede retirar mas del saldo
        assertFalse(ahorros.retirar(9999.0));

        // Corriente puede usar el sobregiro
        assertTrue(corriente.retirar(1400.0)); // usa 400 de sobregiro
    }

    @Test
    public void testListarCuentasEsIndependienteDelModelo() {
        List<Cuenta> lista = modelo.listarCuentas();
        lista.clear(); // borrar copia no debe afectar el modelo
        assertEquals(3, modelo.listarCuentas().size());
    }

    @Test
    public void testAgregarCuartaCuentaYBuscarla() {
        modelo.agregarCuenta(new CuentaCorriente(
            "C002", "Sofia Vega",
            new FechaApertura(8, 8, 2024), 300.0, 100.0));

        assertEquals(4, modelo.listarCuentas().size());
        assertNotNull(modelo.buscarCuenta("C002"));
        assertEquals("Sofia Vega", modelo.buscarCuenta("C002").getNombreDueno());
    }

    @Test
    public void testBuscarCuentaInexistenteEntreMuchas() {
        assertNull(modelo.buscarCuenta("X999"));
    }
}
