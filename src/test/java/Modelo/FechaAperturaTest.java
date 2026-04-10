/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 *
 * @author felip
 */

public class FechaAperturaTest {

    private FechaApertura fecha;

    @BeforeEach
    public void preparar() {
        fecha = new FechaApertura(15, 8, 2023);
    }

    @Test
    public void testGetDia() {
        assertEquals(15, fecha.getDia());
    }

    @Test
    public void testGetMes() {
        assertEquals(8, fecha.getMes());
    }

    @Test
    public void testGetAnio() {
        assertEquals(2023, fecha.getAnio());
    }

    @Test
    public void testToStringFormatoConBarras() {
        assertEquals("15/8/2023", fecha.toString());
    }

    @Test
    public void testToStringNoEstaVacio() {
        assertFalse(fecha.toString().isEmpty());
    }
}