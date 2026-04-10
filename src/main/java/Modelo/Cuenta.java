/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author felip
 */

public abstract class Cuenta {
    private String numero;
    private String nombreDueno;
    private FechaApertura fechaApertura;
    protected double saldo;

    public Cuenta(String numero, String nombreDueno,
                  FechaApertura fecha, double saldoInicial) {
        this.numero       = numero;
        this.nombreDueno  = nombreDueno;
        this.fechaApertura = fecha;
        this.saldo        = saldoInicial;
    }

    // Polimorfismo: retirar es abstracto, cada subclase lo implementa
    public abstract boolean retirar(double monto);

    public void consignar(double monto) {
        if (monto > 0) saldo += monto;
    }

    public double getSaldo() { return saldo; }
    public String getNumero()      { return numero; }
    public String getNombreDueno() { return nombreDueno; }
    public FechaApertura getFecha(){ return fechaApertura; }

    // Sobrecarga: imprimirDatos con y sin separador
    public String imprimirDatos() {
        return "Número: " + numero +
               "\nTitular: " + nombreDueno +
               "\nApertura: " + fechaApertura +
               "\nSaldo: $" + String.format("%.2f", saldo);
    }

    public String imprimirDatos(String separador) {
        return "Número" + separador + numero +
               "\nTitular" + separador + nombreDueno +
               "\nApertura" + separador + fechaApertura +
               "\nSaldo" + separador + "$" + String.format("%.2f", saldo);
    }
}
