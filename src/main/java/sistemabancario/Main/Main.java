/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package sistemabancario.Main;
import Controlador.BancoControlador;
import Modelo.BancoModelo;
import Vista.BancoVista;
import javax.swing.SwingUtilities;
/**
 *
 * @author felip
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BancoModelo modelo = new BancoModelo();
            BancoVista  vista  = new BancoVista();
            new BancoControlador(modelo, vista);
            vista.setVisible(true);
        });
    }
}