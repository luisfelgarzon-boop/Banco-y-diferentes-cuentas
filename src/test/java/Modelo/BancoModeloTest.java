/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author felip
 */
public class BancoModeloTest {

    private BancoModelo modelo;
    private CuentaAhorros cuentaAhorros;
    private CuentaCorriente cuentaCorriente;

    @BeforeEach
    public void preparar() {
        modelo = new BancoModelo();

        FechaApertura f1 = new FechaApertura(1, 1, 2024);
        FechaApertura f2 = new FechaApertura(5, 6, 2023);

        cuentaAhorros    = new CuentaAhorros("A001", "Juan Torres", f1, 800.0, 3.0);
        cuentaCorriente  = new CuentaCorriente("C001", "Laura Gil", f2, 400.0, 250.0);

        modelo.agregarCuenta(cuentaAhorros);
        modelo.agregarCuenta(cuentaCorriente);
    }

    @Test
    public void testAgregarCuentaAumentaLaLista() {
        FechaApertura f = new FechaApertura(10, 10, 2024);
        modelo.agregarCuenta(new CuentaAhorros("A002", "Pedro", f, 100.0, 2.0));
        assertEquals(3, modelo.listarCuentas().size());
    }

    @Test
    public void testListarCuentasRetornaDosRegistros() {
        assertEquals(2, modelo.listarCuentas().size());
    }

    @Test
    public void testListarCuentasRetornaCopiaNoDirect() {
        List<Cuenta> lista = modelo.listarCuentas();
        lista.clear(); // borrar la copia no debe afectar el modelo
        assertEquals(2, modelo.listarCuentas().size());
    }

    @Test
    public void testBuscarCuentaExistente() {
        Cuenta encontrada = modelo.buscarCuenta("A001");
        assertNotNull(encontrada);
    }

    @Test
    public void testBuscarCuentaRetornaElTitularCorrecto() {
        Cuenta encontrada = modelo.buscarCuenta("C001");
        assertEquals("Laura Gil", encontrada.getNombreDueno());
    }

    @Test
    public void testBuscarCuentaInexistenteRetornaNull() {
        Cuenta encontrada = modelo.buscarCuenta("ZZZ");
        assertNull(encontrada);
    }

    @Test
    public void testBuscarCuentaNumeroVacioRetornaNull() {
        Cuenta encontrada = modelo.buscarCuenta("");
        assertNull(encontrada);
    }
}