
package Logica;

import java.util.LinkedList;
//Ecuación de estado Soave-Redlich-Wong



import sun.security.util.DerInputStream;

public class Determinación_del_equilibrio {

	public Determinación_del_equilibrio (
			LinkedList<Compuesto_Caracterizable> lista_final_compuestos,
			LinkedList<Fracciones_No_Caracterizables> lista_fracciones_no_caracterizables,
			Float temperatura, Float presion, Float flujo_crudo_gas) {
		
		
		// TODO Auto-generated constructor stub


		//Variables de operación que deben conocerse, las ingresa el usuario
		//Presión.
		//Temperatura.
		//Flujo BOPD- flujo de crudo por día


		Float Temperatura_de_operacion =(float)100;
		Float Presion_de_operacion =(float)185;

		//Constante para los gases ideales (en unidades inglesas).
		Float Constante_R = (float)10.73;

		//Variables de caracterización de los componentes de la corriente de alimentación.
		//Tc
		//Pc
		//Vc
		//W
		//Peso molecular

		//conversion a temperatura absoluta
		float Temperatura = Temperatura_de_operacion +460;

		//Variables de cada compuesto según las condiciones de operación 
		//Propiedades reducidas
		Float Treducida_Tr = (float)0;
		Float Preducida_Pr = (float)0;

		//Caso de estudio para la primera corrida
		//Constantes para el propano
		Float Tcritica_Tc = (float)666;
		Float Pcritica_Pc = (float)616.3;
		Float factor_acentrico = (float)0.1524;
		Float Peso_molecular = (float)44;


		// ==============================================================

		//Determinación del equilibrio

		//===============================================================

		Double coeficiente_de_reparto [] =new Double [lista_final_compuestos.size()+lista_fracciones_no_caracterizables.size()];
		Double Fraccion_molar_alimento [] =new Double [lista_final_compuestos.size()+lista_fracciones_no_caracterizables.size()];	
		Double Fraccion_molar_fase_vapor =(double)0;
		Double Fraccion_molar_fase_liquida =(double)0;
		Double Funcion_fnv =(double)0;
		Double nuevo_Funcion_fnv =(double)0;
		Double derivada_Funcion_fnv=(double)0;
		Double calculo_A=(double)0;
		Double calculo_B=(double)0;
		Double fraccion_molar_fase_liquida_xi [] =new Double [lista_final_compuestos.size()+lista_fracciones_no_caracterizables.size()];
		Double fraccion_molar_vapor_yi [] =new Double [lista_final_compuestos.size()+lista_fracciones_no_caracterizables.size()];	


		// 1) APLICACIÓN DE LA ECUACIÓN DE WILSON PARA LA DETERMINACIÓN DE UN COEFICIENTE DE REPARTO INICIAL.

int cont=(int)0;
		for (int i=0;i<lista_final_compuestos.size();i++){

			coeficiente_de_reparto[cont]=lista_final_compuestos.get(i).getPresión_critica_Pc()/presion*Math.exp(5.37*(1+lista_final_compuestos.get(i).getFactor_acentrico_w())*(1-lista_final_compuestos.get(i).getTemperatura_critica_Tc()/temperatura));
		cont++;
			}

		for (int i=0;i<(lista_fracciones_no_caracterizables.size());i++){

		
			coeficiente_de_reparto[cont]=lista_fracciones_no_caracterizables.get(i).getPresión_critica_Pc()/presion*Math.exp(5.37*(1+lista_fracciones_no_caracterizables.get(i).getFactor_acentrico_w())*(1-lista_fracciones_no_caracterizables.get(i).getTemperatura_critica_Tc()/temperatura));
		cont++;
		}

		// 2) DETERMINACIÓN DE LA FRACCION MOLAR FASE VAPOR

		for (int i=0;i<cont;i++){

			calculo_A=calculo_A+ Fraccion_molar_alimento[i]*(coeficiente_de_reparto[i]-1);
			calculo_B=calculo_B+ Fraccion_molar_alimento[i]*(coeficiente_de_reparto[i]-1)/coeficiente_de_reparto[i];
		}

		Fraccion_molar_fase_vapor=calculo_A/(calculo_A-calculo_B);

		//3) CALCULO DE LA FUNCIÓN F(nv)

		for (int i=0;i<cont;i++){
			Funcion_fnv=Funcion_fnv+Fraccion_molar_alimento[i]*(coeficiente_de_reparto[i]-1)/(Fraccion_molar_fase_vapor*(coeficiente_de_reparto [i]-1)+1);
		}
		if (Funcion_fnv>10e-3){
			for (int i=0;i<(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size());i++){
				derivada_Funcion_fnv=(derivada_Funcion_fnv-Fraccion_molar_alimento[i]*Math.pow(coeficiente_de_reparto[i]-1, 2)/(Math.pow(Fraccion_molar_fase_vapor*(coeficiente_de_reparto[i]-1)+1, 2)));
			}
			nuevo_Funcion_fnv=Fraccion_molar_fase_vapor-Funcion_fnv/derivada_Funcion_fnv;
			Funcion_fnv=nuevo_Funcion_fnv;
		}

		//4) FRACCIÓN MOLAR DE LA FASE LÍQUIDA

		Fraccion_molar_fase_liquida=(1-Fraccion_molar_fase_vapor);

		// 5)CALCULO COMPOSICIÓN DE LA FASE LÍQUIDA Y VAPOR

		for (int i=0;i<cont-1;i++){
			fraccion_molar_fase_liquida_xi[i]=(Fraccion_molar_alimento[i]/(Fraccion_molar_fase_liquida+Fraccion_molar_fase_vapor*coeficiente_de_reparto[i]));
			fraccion_molar_vapor_yi[i]=(Fraccion_molar_alimento[i]*coeficiente_de_reparto[i]/(Fraccion_molar_fase_liquida+Fraccion_molar_fase_vapor*coeficiente_de_reparto[i]));
			System.out.println(fraccion_molar_fase_liquida_xi[i]+"  "+fraccion_molar_vapor_yi[i]);
		}

	}
}