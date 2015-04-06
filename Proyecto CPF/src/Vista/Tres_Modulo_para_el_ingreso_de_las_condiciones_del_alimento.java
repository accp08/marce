package Vista;

import javax.swing.JOptionPane;

public class Tres_Modulo_para_el_ingreso_de_las_condiciones_del_alimento {

	public static void main(String[] args) {
		Double Temperatura =(Double.parseDouble(JOptionPane.showInputDialog("Ingrese la temperatura del alimento")));
		Double Presion=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese la presión del alimento")));
		Double Flujo_de_crudo=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el flujo de hidrocarburo en la corriente de alimento")));
		Double Flujo_de_gas=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el flujo de  de gas en la corriente de alimento")));
		Double Flujo_de_agua=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el flujo de la corriente de gas")));
		
	
	}

}
