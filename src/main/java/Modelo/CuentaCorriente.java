/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author felip
 */
public class CuentaCorriente extends Cuenta {
    private double cupoSobregiro;

    public CuentaCorriente(String numero, String nombreDueno,
                           FechaApertura fecha, double saldoInicial,
                           double cupoSobregiro) {
        super(numero, nombreDueno, fecha, saldoInicial);
        this.cupoSobregiro = cupoSobregiro;
    }

    @Override
    public boolean retirar(double monto) {
        // Permite retirar hasta cubrir el sobregiro
        if (monto <= 0) return false;
        if ((saldo - monto) >= -cupoSobregiro) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public double getCupoSobregiro()  { return cupoSobregiro; }
    public double getCupoDisponible() { return saldo + cupoSobregiro; }

    @Override
    public String imprimirDatos() {
        return super.imprimirDatos() +
               "\nTipo: Corriente" +
               "\nCupo sobregiro: $" + String.format("%.2f", cupoSobregiro) +
               "\nDisponible: $" + String.format("%.2f", getCupoDisponible());
    }
}