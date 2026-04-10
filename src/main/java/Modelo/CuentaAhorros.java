/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author felip
 */
public class CuentaAhorros extends Cuenta {
    private double tasaInteres;

    public CuentaAhorros(String numero, String nombreDueno,
                         FechaApertura fecha, double saldoInicial,
                         double tasaInteres) {
        super(numero, nombreDueno, fecha, saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) return false;
        saldo -= monto;
        return true;
    }

    public void aplicarInteres() {
        saldo += saldo * (tasaInteres / 100);
    }

    public double getTasaInteres() { return tasaInteres; }

    @Override
    public String imprimirDatos() {
        return super.imprimirDatos() +
               "\nTipo: Ahorros" +
               "\nTasa interés: " + tasaInteres + "%";
    }
}