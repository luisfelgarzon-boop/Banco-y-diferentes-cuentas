/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author felip
 */
public class BancoModelo {

    private List<Cuenta> cuentas = new ArrayList<>();

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public Cuenta buscarCuenta(String numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumero().equals(numero)) {
                return c;
            }
        }
        return null;
    }

    public List<Cuenta> listarCuentas() {
        return new ArrayList<>(cuentas);
    }
}