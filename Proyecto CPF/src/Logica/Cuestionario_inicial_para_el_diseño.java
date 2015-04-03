package Logica;

import javax.swing.JOptionPane;

public class Cuestionario_inicial_para_el_diseño {

	private static final String Separdor_bifasico = null;
	private static final String Separdor_trifasico = null;
	private static final String Si = null;
	private static final String No = null;

	public Cuestionario_inicial_para_el_diseño() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		//===============================
		// Condiciones de la corriente de alimento que acotan el diseño de los equipos iniciales
		//===============================
		//Esta pregunta debe tenr solo dos opcione sde respuesta: Si o no
		String Diseño_de_separadores = (JOptionPane.showInputDialog("¿Desea usted que el software le sugiera un tipo de separador"));

		if (Diseño_de_separadores==No){

			String Tipo_de_separador =(JOptionPane.showInputDialog("Ingrese el tipo de seprador"));
		}



		else {
			double Numero_de_fases=(Double.parseDouble(JOptionPane.showInputDialog("Numero de fases del alimento")));
			//	if (Numero_de_fases==2) {
			//		Tipo_de_separador=(String)"Separador de dos fases";
			//
			//	}
			//	if (Numero_de_fases==3) {
			//		Tipo_de_separador=(String)"Separdor de tres fases";
			//	}	

			String Tipo_de_separador = ((JOptionPane.showInputDialog("¿Requiere usted una geometría específica ")));

			//Horizontal.
			//Vertical.
			//Esferico.

			//=======================================================================//
			//Hacer una ventanita que diga en que caso se recomienda cada tipo de equipo.
			//=======================================================================//

			double Presion_ingreso=(Double.parseDouble(JOptionPane.showInputDialog("Presión de la corriente de alimento (psi)")));

			//Número de separadores.- A el número reportado debe adicionarse el tanque de almacenamiento.
			//			Double Numero_de_separadores =(double)0;
			//			if (Presion_ingreso>=500) {
			//				Numero_de_separadores=(double)3;
			//
			//			}
			//			if (Presion_ingreso<500 && Presion_ingreso>100) {
			//				Numero_de_separadores=(double)2;
			//			}
			//			if (Presion_ingreso<100 && Presion_ingreso>10) {
			//				Numero_de_separadores=(double)1;
			//			}


			double Flujo_Crudo=(Double.parseDouble(JOptionPane.showInputDialog("Flujo de crudo (barriles /día)")));
			double Flujo_gas=(Double.parseDouble(JOptionPane.showInputDialog("Flujo de gas en MMSCFD Million standard cubic feet per day")));	
			double Flujo_Agua=(Double.parseDouble(JOptionPane.showInputDialog("Flujo de agua (barriles/día)")));
			//Las tres variables definirán el tamaño de los equipos. si alguna queda en blanco durante el llenado del. formulario se asumirá que su valor es cero.


			double Lineas_de_separación=(Double.parseDouble(JOptionPane.showInputDialog("Indique el número de lineas de proceso en el que desea tratar el alimento ")));
			// Dependerá del flujo a tratar. Si el flujo excede capacidades típicas de procesamiento será requerido dividirlo en diferenctes corrientes de proceso y se requerirá el doble de equipos con las mismas caracteristicas.

			double Viscosidad_crudo=(Double.parseDouble(JOptionPane.showInputDialog("Visocsidad del crudo (cp)")));
			//Del valor de la viscosidad inicial del crudo se deterinará la necesidad de unidades de calentamiento, (intercambiadores de calor) para realizar su transporte.
			
			double Grados_API=(Double.parseDouble(JOptionPane.showInputDialog("Grados API")));	
			// Determinan el tipo de hidrocarburo que se va amanejar. Permite sacar un perfil de la gravedad especifica del hidrocrburo en el tiempo.
			
			double Gas_oil_ratio=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el GOR")));
			
			
			if (Gas_oil_ratio>500) {
				System.out.println("Se trata de un crudo con alto contenido de gas disuelto. Preferiblemente un separador horizontal");
			}
			
			if (Gas_oil_ratio>500) {
				System.out.println("Se trata de un crudo con bajo contenido de gas disuelto. Posiblemente un crudo pesado");
			}
			
			String formacion_de_espumas =((JOptionPane.showInputDialog("¿Se considera importante la formación de espuma")));	
			//definirá la importancia de la adiccion de un antiespumante.
			
			//Presión de operación. Bja presión (20-200 psi) /Media presión(200-700) psi / Alta presión(700-1500) psi
			//Separador vertical _ Bajos gas-oil ratio. _ Altas capacidades de liquido _ Dificil matenimiento
			//Separador horizantal _ Altas relaciones gas-oil ratio._ altas capacidades de gas _ Mayor eficiencia en la sepración- Manejo de espumas fác- Facil mantenimieno - El más barato
			//Separador esférico _ presiones y condiciones de operación moderadas-Dificil manejo de espumas- mas costoso- Mas facil de instalar.

			//Deberá ingresar una función de la viscosidad con la temperatura.
		
		}


	}

}
