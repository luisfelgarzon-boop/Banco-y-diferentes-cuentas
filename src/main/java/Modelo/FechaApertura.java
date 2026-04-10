/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author felip
 */

public class FechaApertura {
    private int dia, mes, anio;

    public FechaApertura(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    public int getDia()  { return dia; }
    public int getMes()  { return mes; }
    public int getAnio() { return anio; }

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }
}
