package Logica;

import java.util.LinkedList;
//Ecuación de estado Soave-Redlich-Wong

public class Ecuación_de_estado_SRW_modificada {

	public Ecuación_de_estado_SRW_modificada(
			LinkedList<Compuesto_Caracterizable> lista_final_compuestos,
			LinkedList<Fracciones_No_Caracterizables> lista_fracciones_no_caracterizables,
			Float temperatura, Float presion, Float flujo_crudo_gas) {
		// TODO Auto-generated constructor stub


		//Variables de operación que deben conocerse. 
		//Presión.
		//Temperatura.

	
		Float Temperatura_de_operacion =(float)620;
		Float Presion_de_operacion =(float)4000;

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

		//Ecuación de Soave Redlich Kwong modificada.

		//===============================================================
		// variables del sistema

		Float Parametro_a = (float)0;
		Float Parametro_b = (float)0;
		Float Parametro_A = (float)0;
		Float Parametro_B = (float)0;
		Float Parametro_ZL = (float)0;
		Float Parametro_ZV = (float)0;
		Float Parametro_omega_a = (float)0.42747;
		Float Parametro_omega_b = (float)0.08664;
		Float Parametro_alfa = (float)0;
		Float Parametro_a_alfa_mezcla=(float)0;
		Float Parametro_b_mezcla=(float)0;
		Float Parametro_m = (float)0;
		Float Parametro_a_vec[]= new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Parametro_b_vec[]= new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Parametro_m_vec[]= new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Parametro_A_vec[]= new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Parametro_B_vec[]= new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Parametro_alfa_vec[]= new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Parametro_xi_vec[]= new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float parametro_yi_vec[]=new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Treducida_Tr_vec[]=new Float[(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float fraccion_molar_vec[]= new Float [(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];
		Float Constate_de_interacción_kij[][]= new Float [(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())][(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size())];


int cont=0;
		for (int i=0;i<lista_final_compuestos.size();i++){

			Treducida_Tr=temperatura/Tcritica_Tc;
			Parametro_m=(float) (0.480+1.574*lista_final_compuestos.get(i).getFactor_acentrico_w()-0.176*(Math.pow(lista_final_compuestos.get(i).getFactor_acentrico_w(),2)));
			Parametro_a= (float) (Parametro_omega_a*Math.pow(Constante_R*lista_final_compuestos.get(i).getTemperatura_critica_Tc(),2)/lista_final_compuestos.get(i).getPresión_critica_Pc());
			Parametro_b= (float) (Parametro_omega_b*Constante_R*lista_final_compuestos.get(i).getTemperatura_critica_Tc()/lista_final_compuestos.get(i).getPresión_critica_Pc());
			Parametro_alfa= (float)(Math.pow((1+Parametro_m*(1-Math.pow(Treducida_Tr,0.5))),2));

			Parametro_a_vec [i]=Parametro_a;
			Parametro_b_vec [i]=Parametro_b;
			Parametro_m_vec [i]=Parametro_m;
			Parametro_alfa_vec [i]=Parametro_alfa;
			cont++;

		}
		
		
System.out.println(lista_final_compuestos.size());
System.out.println(lista_fracciones_no_caracterizables.size());
int alalala=(int)(lista_final_compuestos.size()+lista_fracciones_no_caracterizables.size());
System.out.println(alalala);
		for (int i=0;i<(lista_fracciones_no_caracterizables.size());i++){

			Treducida_Tr=temperatura/Tcritica_Tc;
			Parametro_m=(float) (0.480+1.574*lista_fracciones_no_caracterizables.get(i).getFactor_acentrico_w()-0.176*(Math.pow(lista_fracciones_no_caracterizables.get(i).getFactor_acentrico_w(),2)));
			Parametro_a= (float) (Parametro_omega_a*Math.pow(Constante_R*lista_fracciones_no_caracterizables.get(i).getTemperatura_critica_Tc(),2)/lista_fracciones_no_caracterizables.get(i).getPresión_critica_Pc());
			Parametro_b= (float) (Parametro_omega_b*Constante_R*lista_fracciones_no_caracterizables.get(i).getTemperatura_critica_Tc()/lista_fracciones_no_caracterizables.get(i).getPresión_critica_Pc());
			Parametro_alfa= (float)(Math.pow((1+Parametro_m*(1-Math.pow(Treducida_Tr,0.5))),2));

			Parametro_a_vec [cont]=Parametro_a;
			Parametro_b_vec [cont]=Parametro_b;
			Parametro_m_vec [cont]=Parametro_m;
			Parametro_alfa_vec [cont]=Parametro_alfa;
			cont++;

		}	

		for(int i =0;i<cont;i++){
			for(int j=0;j<(lista_fracciones_no_caracterizables.size()+lista_final_compuestos.size());j++){
				Parametro_a_alfa_mezcla=(float)(Parametro_a_alfa_mezcla+fraccion_molar_vec[i]*fraccion_molar_vec[j]*Math.pow(((Parametro_a_vec[i]*Parametro_a_vec[j])*Parametro_alfa_vec[i]*Parametro_alfa_vec[j]), 0.5)*(Constate_de_interacción_kij[i][j]-1));
			}
			Parametro_b_mezcla=(float)(Parametro_b_mezcla+fraccion_molar_vec[i]*Parametro_b_vec[i]);
		}


		//calculo de los parÃ¡metros A y B.
		Parametro_A = (Parametro_a_alfa_mezcla*Presion_de_operacion)/(float)(Math.pow((Constante_R*Temperatura),2));
		Parametro_B = (Parametro_b_mezcla*Presion_de_operacion)/(float)(Constante_R*Temperatura);

		//Calculo de las raices de Z -aplicando Newton y Rhapson

		Float X1 =(float) 0;
		Float X2 =(float) 0;
		Float delta = (float)0.05;
		Float tolerancia =(float) 0.0005;
		Float Ecuacion_de_estado =(float)0;
		Float Ecuacion_de_estado1 =(float)0;
		Float Parametro_Z1 = (float)0;
		Float Parametro_Z2 = (float)0;
		Float Parametro_Z3 = (float)0;


		//Primera raíz - Ecución de estado
		Ecuacion_de_estado = (float) ((Math.pow(X1,3))-(Math.pow(X1,2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*X1-Parametro_A*Parametro_B);
		Ecuacion_de_estado1 = (float)((Math.pow((X1-delta),3))-(Math.pow((X1-delta),2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*(X1-delta)-Parametro_A*Parametro_B);
		X2=(float) (X1-Ecuacion_de_estado*delta/(Ecuacion_de_estado-Ecuacion_de_estado1));
		while(Ecuacion_de_estado!=0 && Math.abs(X1-X2)>tolerancia){
			X1=X2;
			Ecuacion_de_estado = (float)(Math.pow(X1,3))-(float)(Math.pow(X1,2))+(Parametro_A-Parametro_B-(float)(Math.pow(Parametro_B,2)))*X1-Parametro_A*Parametro_B;
			Ecuacion_de_estado1 = (float)(Math.pow((X1-delta),3))-(float)(Math.pow((X1-delta),2))+(Parametro_A-Parametro_B-(float)(Math.pow(Parametro_B,2)))*(X1-delta)-Parametro_A*Parametro_B;
			X2=(float) (X1-Ecuacion_de_estado*delta/(Ecuacion_de_estado-Ecuacion_de_estado1));
		}
		Parametro_Z1=X2;
		System.out.println("primera raíz= " +Parametro_Z1);

		//Segunda raíz - Ecuación de estado
		X1=(float)0;
		Ecuacion_de_estado = (float) (((Math.pow(X1,3))-(Math.pow(X1,2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*X1-Parametro_A*Parametro_B)/(X1-Parametro_Z1));
		Ecuacion_de_estado1 = (float)(((Math.pow((X1-delta),3))-(Math.pow((X1-delta),2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*(X1-delta)-Parametro_A*Parametro_B)/(X1-delta-Parametro_Z1));
		X2=(float) (X1-Ecuacion_de_estado*delta/(Ecuacion_de_estado-Ecuacion_de_estado1));
		while(Ecuacion_de_estado!=0 && Math.abs(X1-X2)>tolerancia){
			X1=X2;
			Ecuacion_de_estado = (float) (((Math.pow(X1,3))-(Math.pow(X1,2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*X1-Parametro_A*Parametro_B)/(X1-Parametro_Z1));
			Ecuacion_de_estado1 = (float)(((Math.pow((X1-delta),3))-(Math.pow((X1-delta),2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*(X1-delta)-Parametro_A*Parametro_B)/(X1-delta-Parametro_Z1));
			X2=(float) (X1-Ecuacion_de_estado*delta/(Ecuacion_de_estado-Ecuacion_de_estado1));
		}

		Parametro_Z2=X2;
		System.out.println("segunda raíz= " +Parametro_Z2);

		//Tercera raíz - Ecuación de estado
		X1=(float)0;
		Ecuacion_de_estado = (float) (((Math.pow(X1,3))-(Math.pow(X1,2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*X1-Parametro_A*Parametro_B)/((X1-Parametro_Z1)*(X1-Parametro_Z2)));
		Ecuacion_de_estado1 = (float)(((Math.pow((X1-delta),3))-(Math.pow((X1-delta),2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*(X1-delta)-Parametro_A*Parametro_B)/((X1-delta-Parametro_Z1)*(X1-delta-Parametro_Z2)));
		X2=(float) (X1-Ecuacion_de_estado*delta/(Ecuacion_de_estado-Ecuacion_de_estado1));
		while(Ecuacion_de_estado!=0 && Math.abs(X1-X2)>tolerancia){
			X1=X2;
			Ecuacion_de_estado = (float) (((Math.pow(X1,3))-(Math.pow(X1,2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*X1-Parametro_A*Parametro_B)/((X1-Parametro_Z1)*(X1-Parametro_Z2)));
			Ecuacion_de_estado1 = (float)(((Math.pow((X1-delta),3))-(Math.pow((X1-delta),2))+(Parametro_A-Parametro_B-(Math.pow(Parametro_B,2)))*(X1-delta)-Parametro_A*Parametro_B)/((X1-delta-Parametro_Z1)*(X1-delta-Parametro_Z2)));
			X2=(float) (X1-Ecuacion_de_estado*delta/(Ecuacion_de_estado-Ecuacion_de_estado1));
		}

		Parametro_Z3=X2;
		System.out.println("tercera raíz= " +Parametro_Z3);	

		// selección del factor de compresibilidad del líquido y del vapor
		double numeros[] = { Parametro_Z2,Parametro_Z3,Parametro_Z1};
		for (int i = 0 ; i < numeros.length; i++) {
			int min = i;
			//buscamos el menor número
			for (int j = i + 1 ; j < numeros.length ; j++) {
				if (numeros[j] < numeros[min]) {
					min = j;    
					//encontramos el menor número
				}
			}

			if (i != min) {
				//permutamos los valores
				double aux = numeros [i];
				numeros [i] = numeros [min];
				numeros[min] = aux;
			}
		}	                

		Parametro_ZL = (float) numeros[0];
		Parametro_ZV = (float) numeros[2];

		System.out.println("Z del vapor= "+Parametro_ZV+" 	Z del líquido= "+Parametro_ZL);


	}
}