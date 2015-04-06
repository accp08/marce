package Logica;

import java.io.ObjectInputStream.GetField;
import java.sql.PseudoColumnUsage;
import java.util.*;

import javax.swing.ListSelectionModel;



import com.sun.org.apache.bcel.internal.generic.FNEG;

import Logica.Objeto_Compuestos_Caracterizables;
import Persistencia.Lectura_HC_Compuestos_Caracterizables;
import Persistencia.Lectura_constantes_calculo_entalpias;
import Logica.Calculo_de_raices_complejos;
import Logica.Calculo_raices_metodo_Graeffe;
import Logica.Objeto_constante_calculo_entalpia;

public class Equilibrio_de_fases_PR {

	public static LinkedList<Objeto_Compuestos_Caracterizables> lista_de_compuestos= new LinkedList<Objeto_Compuestos_Caracterizables>();

	public static LinkedList<Objeto_constante_calculo_entalpia> lista_constantes_calculo_entlpias= new LinkedList<Objeto_constante_calculo_entalpia>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Necesito tener definido los compuestos que ingresan al sistema de separación.
		//Se va a generan una matriz de tamaño mXm donde m es el número de los compuestos.
		//Cada tipo de parámetros se calculan de una manera diferente.
		// La matriz de los parámertros de interacción binario tiene la diagonal igual a cero.
		//El llenado de la matriz de hará por filas.
		//=============================================================================================================================================================================================================================//
		//   1) VARIABLES, PARAMETROS Y CONSTANTES CONOCIDAS.



		//	1.1)	Variables del sistema; son ingresadas en la pantalla donde se piden los datos para el calculo de equilibrio
		lista_constantes_calculo_entlpias=Lectura_constantes_calculo_entalpias.carga();
		lista_de_compuestos = Lectura_HC_Compuestos_Caracterizables.carga();
		int numero_de_compuestos_no_caracterizables= (int)5;
		Double Temperatura_alimento =(double)580;
		Double Presion_alimento =(double)4000;
		Double Composicion []= new Double [lista_de_compuestos.size()+numero_de_compuestos_no_caracterizables];

		//	1.2)	Constante universal, parámetros de la ecuación elegida para el cálculo.

		Double Constante_gas_ideal_R =(double)10.73;
		//Constantes según la EOS seleccionada
		Double Parametro_omega_a_PR = (double)0.45724;
		Double Parametro_omega_b_PR= (double)0.07780;

		//		1.3)	Inicialización de la temperatura y la presión incial de las corrientes de vapor y liquido 
		Double Temperatura_liquido=Temperatura_alimento;
		Double Temperatura_vapor =Temperatura_alimento;
		Double Presion_liquido=Presion_alimento;
		Double Presion_vapor =Presion_alimento;
 






		//=============================================================================================================================================================================================================================//
		//	2) LLENADO DE LAS MATRICES (O VECTORES) DE COMPOSICIÓN, PROPIEDADES CRÍTICAS, FCTOR ACÉNTRICO, PESO MOLECULAR, PARÁMETROS A Y B PARA CADA COMPESTO Y PROPIEDADES REDUCIDAS



		// 2.1)	Número de compuestos totales que interactuan en la liquido.
		int numero_de_compuestos =(int)(lista_de_compuestos.size()+ numero_de_compuestos_no_caracterizables);

		//	2.2)	Llenado vector composición 
		for (int i = 0; i < Composicion.length; i++) {
			Composicion[i]=(double)0;
		}

		Composicion[22]=0.78;
		Composicion[24]=0.05;
		Composicion[0]=33.86;
		Composicion[1]=5.63;
		Composicion[2]=4.4;
		Composicion[3]=1.21;
		Composicion[4]=3.42;
		Composicion[5]=1.85;
		Composicion[6]=2.44;
		Composicion[7]=4.29;
		Composicion[27]=9.96;
		Composicion[28]=7.14;
		Composicion[29]=6.11;
		Composicion[30]=5.44;
		Composicion[31]=13.42;


		for (int i = 0; i < Composicion.length; i++) {
			//System.out.println(Composicion[i]);
		}

		//	2.3)	Propiedades de los compuestos caracterizables y no caracterizables
		double [] T_criticas_caracterizables=new double [lista_de_compuestos.size()];
		double [] P_criticas_caracterizables=new double [lista_de_compuestos.size()];
		double [] V_criticos_caracterizables=new double [lista_de_compuestos.size()];
		double [] Factor_acentrico_w_caracterizables = new double [lista_de_compuestos.size()];
		double [] Peso_molecular_MW_caracterizables=new double [lista_de_compuestos.size()];
		double [] T_criticas_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] P_criticas_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] V_criticos_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] Punto_ebullicion_estandar_no_cracterizables=new double[numero_de_compuestos_no_caracterizables];
		double [] Densidad_no_caracterizables=new double[numero_de_compuestos_no_caracterizables];
		double []Factor_acentrico_w_no_caracterizables = new double [numero_de_compuestos_no_caracterizables];
		double [] Peso_molecular_MW_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] Parametro_m_caracterizables = new double [numero_de_compuestos];

		//	2.4)	Parametros a y b para los todos los compuestos
		double [] Parametro_a_caracterizables = new double [lista_de_compuestos.size()];
		double [] Parametro_b_caracterizables = new double [lista_de_compuestos.size()];
		double [] Parametro_a_no_caracterizables = new double [numero_de_compuestos_no_caracterizables];
		double [] Parametro_b_no_caracterizables = new double [numero_de_compuestos_no_caracterizables];
		double [] Parametro_m_no_caracterizables = new double [numero_de_compuestos];

		//	2.5)	Vectores para el calculo. Agrupa las propiedades de los caracterizables y no caracterizables.
		double [] Temperatura_critica= new double [numero_de_compuestos];
		double [] Presion_critica= new double [numero_de_compuestos];
		double [] Volumen_critco= new double [numero_de_compuestos];
		double [] Factor_acentrico_w =new double [numero_de_compuestos];
		double [] Peso_molecular_MW =new double [numero_de_compuestos];
		double [] Parametro_a = new double [numero_de_compuestos];
		double [] Parametro_b = new double [numero_de_compuestos];
		double [] Parametro_m = new double [numero_de_compuestos];


		// 2.6)		Llenado de la matriz de compuestos caracterizables y no caracterizables.
		for (int i=0; i<lista_de_compuestos.size();i++){
			T_criticas_caracterizables[i]= lista_de_compuestos.get(i).getTemperatura_critica_Tc()+460;
			P_criticas_caracterizables[i]= lista_de_compuestos.get(i).getPresión_critica_Pc();
			V_criticos_caracterizables[i]=lista_de_compuestos.get(i).getVolumen_critico_Vc();
			Factor_acentrico_w_caracterizables[i] = lista_de_compuestos.get(i).getFactor_acentrico_w();
			Peso_molecular_MW_caracterizables[i]=lista_de_compuestos.get(i).getMasa_molecular();
		}
		//OJO!! ESTA MATRIZ SE DEBE LLENAR CON LOS DATOS PROVENIENTES DE LA CARACTERIZACIÓN DE LOS PSEUDOCOMPONENTES.
		for (int i=0; i<numero_de_compuestos_no_caracterizables;i++){
//			T_criticas_no_caracterizables [i] =(double)1160;
//			P_criticas_no_caracterizables [i]=(double)285;
//			V_criticos_no_caracterizables [i]=(double)2.37073211055279;
//			Factor_acentrico_w_no_caracterizables [i] =(double)0.52;
//			Peso_molecular_MW_no_caracterizables[i]=215;
		
		}

		Punto_ebullicion_estandar_no_cracterizables[0]=247.999989013672;
		Punto_ebullicion_estandar_no_cracterizables[1]=339.999995117188;
		Punto_ebullicion_estandar_no_cracterizables[2]=413.000025634766;
		Punto_ebullicion_estandar_no_cracterizables[3]=471.999958496094;
		Punto_ebullicion_estandar_no_cracterizables[4]=656.999946289063;

		Punto_ebullicion_estandar_no_cracterizables[0]=110;
		Punto_ebullicion_estandar_no_cracterizables[1]=141;
		Punto_ebullicion_estandar_no_cracterizables[2]=170;
		Punto_ebullicion_estandar_no_cracterizables[3]=195;
		Punto_ebullicion_estandar_no_cracterizables[4]=336;

		T_criticas_no_caracterizables [0]=573.00001953125;
		T_criticas_no_caracterizables [1]=672.999995117188;
		T_criticas_no_caracterizables [2]=749.999982910156;
		T_criticas_no_caracterizables [3]=807.00001953125;
		T_criticas_no_caracterizables [4]=974.999982910156;

		P_criticas_no_caracterizables [0]=441.999982897559;
		P_criticas_no_caracterizables [1]=369.999989689087;
		P_criticas_no_caracterizables [2]=350.000013214819;
		P_criticas_no_caracterizables [3]=295.000007091394;
		P_criticas_no_caracterizables [4]=220.000006788904;

		V_criticos_no_caracterizables [0]=6.90527232515276;
		V_criticos_no_caracterizables [1]=8.58046592539072;
		V_criticos_no_caracterizables [2]=10.0225680111635;
		V_criticos_no_caracterizables [3]=11.2785682926905;
		V_criticos_no_caracterizables [4]=15.5925675051808;

		Densidad_no_caracterizables[0]=47.4744211953125;
		Densidad_no_caracterizables[1]=49.405795065918;
		Densidad_no_caracterizables[2]=50.9010531164551;
		Densidad_no_caracterizables[3]=52.0224937966309;
		Densidad_no_caracterizables[4]=55.2622189377441;

		Factor_acentrico_w_no_caracterizables[0]=0.375785112380981;
		Factor_acentrico_w_no_caracterizables[1]=0.443636506795883;
		Factor_acentrico_w_no_caracterizables[2]=0.535724818706512;
		Factor_acentrico_w_no_caracterizables[3]=0.565597891807556;
		Factor_acentrico_w_no_caracterizables[4]=0.807937860488892;

		
		
		// 2.7)		Calculo de los parámetros a, b y m para compuestos caracterizables y no caracterizables.
		for (int i=0; i<(lista_de_compuestos.size());i++){
			Parametro_a_caracterizables [i]= Parametro_omega_a_PR*Math.pow(Constante_gas_ideal_R,2)*Math.pow(T_criticas_caracterizables[i],2)/P_criticas_caracterizables[i];
			Parametro_b_caracterizables [i]= Parametro_omega_b_PR*Constante_gas_ideal_R*T_criticas_caracterizables[i]/P_criticas_caracterizables[i];
			Parametro_m_caracterizables [i]=(0.379642+1.48503*Factor_acentrico_w_caracterizables[i]-0.1644*Math.pow(Factor_acentrico_w_caracterizables[i],2)+0.016667*Math.pow(Factor_acentrico_w_caracterizables[i], 3));
		}
		for (int i=0; i<numero_de_compuestos_no_caracterizables;i++){
			Parametro_a_no_caracterizables [i]= Parametro_omega_a_PR*Math.pow(Constante_gas_ideal_R,2)*Math.pow(T_criticas_no_caracterizables[i],2)/P_criticas_no_caracterizables[i];
			Parametro_b_no_caracterizables [i]= Parametro_omega_b_PR*Constante_gas_ideal_R*T_criticas_no_caracterizables[i]/P_criticas_no_caracterizables[i];
			Parametro_m_caracterizables [i]=(0.379642+1.48503*Factor_acentrico_w_caracterizables[i]-0.1644*Math.pow(Factor_acentrico_w_caracterizables[i],2)+0.016667*Math.pow(Factor_acentrico_w_caracterizables[i], 3));
		}

		// 2.8)		Llenado de los vectores correspondientes a todos los compuestos. (caracterizables y no caracterizables).
		for (int i=0; i<lista_de_compuestos.size(); i++){
			Temperatura_critica[i]=T_criticas_caracterizables[i];
			Presion_critica[i]=P_criticas_caracterizables[i];
			Volumen_critco [i]=V_criticos_caracterizables[i];
			Factor_acentrico_w[i]=Factor_acentrico_w_caracterizables[i];
			Peso_molecular_MW[i]=Peso_molecular_MW_caracterizables[i];
			Parametro_a[i]=Parametro_a_caracterizables[i];
			Parametro_b[i]=Parametro_b_caracterizables[i];
			Parametro_m [i]=Parametro_m_caracterizables[i];
		}
		for (int i=lista_de_compuestos.size(); i<numero_de_compuestos; i++){
			Temperatura_critica[i]=T_criticas_no_caracterizables[i-lista_de_compuestos.size()];
			Presion_critica[i]=P_criticas_no_caracterizables[i-lista_de_compuestos.size()];
			Volumen_critco [i]=V_criticos_no_caracterizables[i-lista_de_compuestos.size()];
			Factor_acentrico_w[i]=Factor_acentrico_w_no_caracterizables[i-lista_de_compuestos.size()];
			Peso_molecular_MW[i]=Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()];
			Parametro_a[i]=Parametro_a_no_caracterizables[i-lista_de_compuestos.size()];
			Parametro_b[i]=Parametro_b_no_caracterizables[i-lista_de_compuestos.size()];
			Parametro_m [i] =Parametro_m_no_caracterizables [i-lista_de_compuestos.size()];
		}

		// 2.9) 	Composición y factor de compresibilidad de la fase líquida y la fase vapor.
		double Composicion_molar_liquido[]=new double[numero_de_compuestos];
		double Composicion_molar_vapor[]=new double[numero_de_compuestos];
		Double Parametro_ZL=(double)0;
		Double Parametro_ZV=(double)0;

		// 2.10) 	Impresion propiedades criticas, ai, bi y mi
		for (int i=0; i<numero_de_compuestos; i++){
			//System.out.println(Temperatura_critica[i]);
			//System.out.println(Presion_critica[i]);
			//System.out.println(Volumen_critco [i]);
			//System.out.println(Factor_acentrico_w[i]);
			//System.out.println(Peso_molecular_MW[i]);
			//System.out.println(Parametro_a[i]);
			//System.out.println("Parametro_bi  "+i+" : "+Parametro_b[i]);
			//System.out.println(Parametro_m [i]);
		}






		//=============================================================================================================================================================================================================================//
		//   3) CALCULO DE LAS PROPIEDADES DEL ALIMENTO



		double [] Parametro_alfa_alimento = new double [numero_de_compuestos];
		Double parametro_a_alfa_alimento=(double)0;
		Double parametro_b_alimento= (double)0;
		Double Parametro_A_alimento= (double)0;
		Double Parametro_B_alimento= (double)0;

		// 3.1)		Propiedades reducidas.
		double [] Temperatura_reducida_alimento = new double [numero_de_compuestos];
		double [] Presion_reducida_alimento = new double [numero_de_compuestos];
		for (int i = 0; i < numero_de_compuestos; i++) {
			Temperatura_reducida_alimento[i]=Temperatura_alimento/Temperatura_critica[i];
			Presion_reducida_alimento [i]=Presion_alimento/Presion_critica[i];			
		}

		// 3.2) 	Vectores m y alfa
		for (int i = 0; i <numero_de_compuestos; i++) {
			Parametro_alfa_alimento [i]=Math.pow((1+Parametro_m [i]*(1-Math.pow(Temperatura_reducida_alimento[i],0.5))),2);
		}
		// 3.3) 	Parámetros (a*alfa) y (b)
		// 3.3.1) 	Calculo de la matriz de parámetros de interaccion binarios para el alimento.Método de Nikos et al.(1986). Calculo de los parámetros de interacción binarios para el metano, nitrogeno y dioxido de carbono.
		double[][]Parametros_de_interaccion_binarios_alimento = new double [numero_de_compuestos][numero_de_compuestos];	
		for (int i = 0; i < numero_de_compuestos; i++) {
			for (int j=0; j<numero_de_compuestos;j++){
				Parametros_de_interaccion_binarios_alimento[i][j]=0;
			}
		}
		Double Parametro_S0=(double)0;
		Double Parametro_S1=(double)0;
		Double Parametro_S2=(double)0;
		//Para Nitrogeno-Hidrocarburos.
		for (int j=1;j<numero_de_compuestos;j++){
			if( j<22){
				Parametro_S0=(0.1751787-0.7043*Math.log10(Factor_acentrico_w[j])-0.862066*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S1=(-0.584474+1.328*Math.log10(Factor_acentrico_w[j])+2.035767*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S2=(2.257079+7.869765*Math.log10(Factor_acentrico_w[j])+13.50466*Math.pow(Math.log10(Factor_acentrico_w[j]),2)+8.3864*Math.pow(Math.log10(Factor_acentrico_w[j]),3));
				Parametros_de_interaccion_binarios_alimento [22][j]=(Parametro_S2*Math.pow(Temperatura_reducida_alimento[j],2)+Parametro_S1*Temperatura_reducida_alimento[j]+Parametro_S0)*(1.04-4.2e-5*Presion_alimento);
			}
			if(j>26){
				Parametro_S0=(0.1751787-0.7043*Math.log10(Factor_acentrico_w[j])-0.862066*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S1=(-0.584474+1.328*Math.log10(Factor_acentrico_w[j])+2.035767*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S2=(2.257079+7.869765*Math.log10(Factor_acentrico_w[j])+13.50466*Math.pow(Math.log10(Factor_acentrico_w[j]),2)+8.3864*Math.pow(Math.log10(Factor_acentrico_w[j]),3));
				Parametros_de_interaccion_binarios_alimento [22][j]=(Parametro_S2*Math.pow(Temperatura_reducida_alimento[j],2)+Parametro_S1*Temperatura_reducida_alimento[j]+Parametro_S0)*(1.04-4.2e-5*Presion_alimento);
			}
		}
		//Para Metano-Hidrocarburos.				
		for (int j=0;j<numero_de_compuestos;j++){
			if(j>0 && j<22){
				Parametro_S0=(-0.01664-0.37283*Math.log10(Factor_acentrico_w[j])+1.31757*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S1=(0.48147+3.35342*Math.log10(Factor_acentrico_w[j])-1.0783*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S2=(-0.4114-3.5072*Math.log10(Factor_acentrico_w[j])-0.78798*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametros_de_interaccion_binarios_alimento [0][j]=(Parametro_S2*Math.pow(Temperatura_reducida_alimento[j],2)+Parametro_S1*Temperatura_reducida_alimento[j]+Parametro_S0);

			}
			if(j>26){
				Parametro_S0=(-0.01664-0.37283*Math.log10(Factor_acentrico_w[j])+1.31757*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S1=(0.48147+3.35342*Math.log10(Factor_acentrico_w[j])-1.0783*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametro_S2=(-0.4114-3.5072*Math.log10(Factor_acentrico_w[j])-0.78798*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
				Parametros_de_interaccion_binarios_alimento [0][j]=(Parametro_S2*Math.pow(Temperatura_reducida_alimento[j],2)+Parametro_S1*Temperatura_reducida_alimento[j]+Parametro_S0);
			}
		}
		//Para Dioxido de carbono-Hidrocarburos.				
		for (int j=0;j<numero_de_compuestos;j++){
			if(j<22){
				Parametro_S0=(0.4025636+0.1748927*Math.log10(Factor_acentrico_w[j]));
				Parametro_S1=(-0.94812-0.6009864*Math.log10(Factor_acentrico_w[j]));
				Parametro_S2=(0.741843368+0.441775*Math.log10(Factor_acentrico_w[j]));
				Parametros_de_interaccion_binarios_alimento [24][j]=(Parametro_S2*Math.pow(Temperatura_reducida_alimento[j],2)+Parametro_S1*Temperatura_reducida_alimento[j]+Parametro_S0)*(1.044269-4.375e-5*Presion_alimento);
			}
			if(j>26){
				Parametro_S0=(0.4025636+0.1748927*Math.log10(Factor_acentrico_w[j]));
				Parametro_S1=(-0.94812-0.6009864*Math.log10(Factor_acentrico_w[j]));
				Parametro_S2=(0.741843368+0.441775*Math.log10(Factor_acentrico_w[j]));
				Parametros_de_interaccion_binarios_alimento [24][j]=(Parametro_S2*Math.pow(Temperatura_reducida_alimento[j],2)+Parametro_S1*Temperatura_reducida_alimento[j]+Parametro_S0)*(1.044269-4.375e-5*Presion_alimento);
			}
		}
		//Para Sulfuro de hidrogeno-Hidrocarburos.	
		double [] matriz_e=new double[numero_de_compuestos];
		for (int i = 0; i < numero_de_compuestos; i++) {
			matriz_e [i]=Math.pow((Parametro_a[i]*Math.log(2)),0.5)/Parametro_b[i];
		}
		for (int j=0;j<numero_de_compuestos;j++){
			if(j<22){
				Parametros_de_interaccion_binarios_alimento [23][j]=(0.077654+0.017921*(-Math.pow(matriz_e[23]-matriz_e[j],0.5)/(2*matriz_e[23]*matriz_e[j])));
			}
			if(j>26){
				Parametros_de_interaccion_binarios_alimento [23][j]=(0.077654+0.017921*(-Math.pow(matriz_e[23]-matriz_e[j],0.5)/(2*matriz_e[23]*matriz_e[j])));
			}
		}
		//	3.3.2) 	Parámetros (a*alfa) y (b)
		for (int i = 0; i <numero_de_compuestos; i++) {
			for (int j = 0; j <numero_de_compuestos; j++) {
				parametro_a_alfa_alimento=parametro_a_alfa_alimento+(Composicion[i]*Composicion[j])*Math.pow((Parametro_a[i]*Parametro_a[j]*Parametro_alfa_alimento[i]*Parametro_alfa_alimento[j]),0.5)*(1-Parametros_de_interaccion_binarios_alimento[i][j]);
			}
		}
		for (int i = 0; i <numero_de_compuestos; i++) {
			parametro_b_alimento=parametro_b_alimento+Composicion[i]*Parametro_b[i];
		}
		//		3.4) 	Parámetros A y B
		Parametro_A_alimento = (parametro_a_alfa_alimento*Presion_alimento)/(float)(Math.pow((Constante_gas_ideal_R*Temperatura_alimento),2));
		Parametro_B_alimento  = (parametro_b_alimento*Presion_alimento)/(Constante_gas_ideal_R*Temperatura_alimento);

		// 3.5) 	Impresión de los parametros de caracterización de cada componente
		for (int i = 0; i <numero_de_compuestos; i++) {
			if (Composicion[i]!=0) {
				//System.out.println("compuesto "+i+" :");
				//System.out.println("Composición i= "+Composicion[i]);
				//System.out.println("T. reducida del alimento = "+Temperatura_reducida_alimento[i]);
				//System.out.println("Presión reducida del alimento ="+ Presion_reducida_alimento[i]);
				//System.out.println("parametro alfa del alimento= "+Parametro_alfa_alimento[i]);
			}
		}
		//System.out.println("parametro a alfa del alimento= "+ parametro_a_alfa_alimento);
		//System.out.println("parametro b del alimento= "+ parametro_b_alimento);
		//System.out.println(" parametro A del alimento= "+ Parametro_A_alimento);
		//System.out.println(" parametro B del alimento = "+ Parametro_B_alimento);









		//=============================================================================================================================================
		// 4) DETEMINACIÓN DEL EQUILIBRIO INICIAL - CALCULO ITERATIVO



		// 4.1) 	Definicion y llenado de la matriz de los coeficentes de reparto. requerida para el calculo iterativo
		double Constantes_de_reparto[]=new double[numero_de_compuestos];
		for (int i = 0; i <numero_de_compuestos; i++) {
			Constantes_de_reparto[i]=0;
		}

		//	4.2) 	Calculo de las constantes de reparto aproximadas a partir de la ecuación de Wilson.
		double constantes_Ki_iniciales []= new double[numero_de_compuestos];
		for (int i = 0; i <numero_de_compuestos; i++) {
			constantes_Ki_iniciales [i]=Presion_critica [i]/Presion_alimento*Math.exp(5.37*(1+Factor_acentrico_w[i])*(1-Temperatura_critica[i]/Temperatura_alimento)); 
		}

		//	4.3) 	Definición de los critrios pr la itercion
		Double Validacion_de_las_constantes_de_reparto=(double)1000;
		double tolerancia_Ki=(double)10;
		while(tolerancia_Ki!=0){

			//	4.4) 	Determinación de la fracción de vapor en la alimentación y determinación de la función de vapor (la cual será cero cuando se encuentra la verdadera composición del vapor).
			Double Fraccion_molar_vapor_inicial=(double)0;
			Double Fraccion_molar_vapor=(double)0;
			Double Funcion_de_vapor=(double)0;
			Double Funcion_de_vapor_derivada=(double)0;
			Double A=(double)0;
			Double B=(double)0;
			for (int i = 0; i <numero_de_compuestos; i++) {
				A=A+Composicion[i]*(constantes_Ki_iniciales[i]-1);
				B=B+Composicion[i]*(constantes_Ki_iniciales[i]-1)/constantes_Ki_iniciales[i];
			}
			Fraccion_molar_vapor_inicial=A/(A-B);
			Fraccion_molar_vapor=Fraccion_molar_vapor_inicial;
			for (int i = 0; i < numero_de_compuestos; i++) {
				Funcion_de_vapor=Funcion_de_vapor+(Composicion[i]*(constantes_Ki_iniciales[i]-1))/((Fraccion_molar_vapor_inicial*(constantes_Ki_iniciales[i]-1))+1);	
			}

			// 4.5) 	Calculo iterativo para determinar las composiciones del vapor en equilibrio
			while (Math.abs(Funcion_de_vapor)>0.0009){
				Fraccion_molar_vapor_inicial=Fraccion_molar_vapor;
				// 4.5.1) 	Determinación de la función de vapor. la cual será cero cuando se encuentra la verdadera composición del vapor.
				for (int i = 0; i < numero_de_compuestos; i++) {
					Funcion_de_vapor=Funcion_de_vapor+(Composicion[i]*(constantes_Ki_iniciales[i]-1))/((Fraccion_molar_vapor_inicial*(constantes_Ki_iniciales[i]-1))+1);	
				}
				// 4.5.2) 	Derivda de la función de vapor.
				for (int i = 0; i < numero_de_compuestos; i++) {
					Funcion_de_vapor_derivada=Funcion_de_vapor_derivada-(Composicion[i]*Math.pow((constantes_Ki_iniciales[i]-1),2))/(Math.pow(Fraccion_molar_vapor_inicial*(constantes_Ki_iniciales[i]-1)+1,2));	
				}
				Fraccion_molar_vapor=Fraccion_molar_vapor_inicial-Funcion_de_vapor/Funcion_de_vapor_derivada;
			}

			// 4.6) 	Determinación de la fracción de líquido.
			Double Fraccion_molar_liquido=(double)0;
			Fraccion_molar_liquido=1-Fraccion_molar_vapor;

			//4.7) Fracción molar de cada componete en la fase líquida y vapor.
			for (int i = 0; i < numero_de_compuestos; i++) {
				if (Composicion[i]!=0){
					Composicion_molar_liquido[i]=Composicion[i]/(Fraccion_molar_liquido+Fraccion_molar_vapor*constantes_Ki_iniciales[i]);
					Composicion_molar_vapor[i]=Composicion[i]*constantes_Ki_iniciales[i]/(Fraccion_molar_liquido+Fraccion_molar_vapor*constantes_Ki_iniciales[i]);
				}
			}
			//4.8) 		Impresión de la fracción de vapor, fracción de líquido y composición de cda compuesto en cada fase
			for (int i = 0; i < numero_de_compuestos; i++) {
				if (Composicion[i]!=0){
					//System.out.println("K_inicial "+i+ "  "+constantes_Ki_iniciales[i]);
				}
			}
			//System.out.println("Fraccion_molar_vapor_inicial=	"+Fraccion_molar_vapor_inicial);
			//System.out.println("Funcion_de_vapor=	"+Funcion_de_vapor);
			//System.out.println("Fraccion_molar_liquido=	"+Fraccion_molar_liquido);
			for (int i = 0; i < numero_de_compuestos; i++) {
				if (Composicion[i]!=0){
					//System.out.println("fraccion inicial=	"+Composicion[i]+"	frac. liquido =	 "+ Composicion_molar_liquido[i]+"	fracc. vapor = 	"+Composicion_molar_vapor[i] );
				}
			}









			//=============================================================================================================================================
			//   5) CALCULO DE LAS PROPIEDADES DE LA FASE LÍQUIDA



			double [] Parametro_alfa_liquido = new double [numero_de_compuestos];
			Double parametro_a_alfa_liquido=(double)0;
			Double parametro_b_liquido= (double)0;
			Double Parametro_A_liquido= (double)0;
			Double Parametro_B_liquido= (double)0;

			// 5.1)		Propiedades reducidas.
			double [] Temperatura_reducida_liquido = new double [numero_de_compuestos];
			double [] Presion_reducida_liquido = new double [numero_de_compuestos];
			for (int i = 0; i < numero_de_compuestos; i++) {
				Temperatura_reducida_liquido[i]=Temperatura_liquido/Temperatura_critica[i];
				Presion_reducida_liquido [i]=Presion_liquido/Presion_critica[i];			
			}

			// 5.2) 	Vectores m y alfa
			for (int i = 0; i <numero_de_compuestos; i++) {
				Parametro_m [i]=Parametro_m_caracterizables [i]=(0.379642+1.48503*Factor_acentrico_w[i]-0.1644*Math.pow(Factor_acentrico_w[i],2)+0.016667*Math.pow(Factor_acentrico_w[i], 3));
				Parametro_alfa_liquido [i]=Math.pow((1+Parametro_m [i]*(1-Math.pow(Temperatura_reducida_liquido[i],0.5))),2);
			}

			// 5.3) 	Parámetros (a*alfa) y (b)
			// 5.3.1) 	Calculo de la matriz de parámetros de interaccion binarios para el alimento.Método de Nikos et al.(1986). Calculo de los parámetros de interacción binarios para el metano, nitrogeno y dioxido de carbono.
			double[][]Parametros_de_interaccion_binarios_liquido = new double [numero_de_compuestos][numero_de_compuestos];	
			for (int i = 0; i < numero_de_compuestos; i++) {
				for (int j=0; j<numero_de_compuestos;j++){
					Parametros_de_interaccion_binarios_liquido[i][j]=0;
				}
			}
			Double Parametro_S0_L=(double)0;
			Double Parametro_S1_L=(double)0;
			Double Parametro_S2_L=(double)0;
			//Para Nitrogeno-Hidrocarburos.
			for (int j=1;j<numero_de_compuestos;j++){
				if( j<22){
					Parametro_S0_L=(0.1751787-0.7043*Math.log10(Factor_acentrico_w[j])-0.862066*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_L=(-0.584474+1.328*Math.log10(Factor_acentrico_w[j])+2.035767*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_L=(2.257079+7.869765*Math.log10(Factor_acentrico_w[j])+13.50466*Math.pow(Math.log10(Factor_acentrico_w[j]),2)+8.3864*Math.pow(Math.log10(Factor_acentrico_w[j]),3));
					Parametros_de_interaccion_binarios_liquido [22][j]=(Parametro_S2_L*Math.pow(Temperatura_reducida_liquido[j],2)+Parametro_S1_L*Temperatura_reducida_liquido[j]+Parametro_S0_L)*(1.04-4.2e-5*Presion_liquido);
				}
				if(j>26){
					Parametro_S0_L=(0.1751787-0.7043*Math.log10(Factor_acentrico_w[j])-0.862066*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_L=(-0.584474+1.328*Math.log10(Factor_acentrico_w[j])+2.035767*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_L=(2.257079+7.869765*Math.log10(Factor_acentrico_w[j])+13.50466*Math.pow(Math.log10(Factor_acentrico_w[j]),2)+8.3864*Math.pow(Math.log10(Factor_acentrico_w[j]),3));
					Parametros_de_interaccion_binarios_liquido [22][j]=(Parametro_S2_L*Math.pow(Temperatura_reducida_liquido[j],2)+Parametro_S1_L*Temperatura_reducida_liquido[j]+Parametro_S0_L)*(1.04-4.2e-5*Presion_liquido);
				}
			}
			//Para Metano-Hidrocarburos.				
			for (int j=0;j<numero_de_compuestos;j++){
				if(j>0 && j<22){
					Parametro_S0_L=(-0.01664-0.37283*Math.log10(Factor_acentrico_w[j])+1.31757*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_L=(0.48147+3.35342*Math.log10(Factor_acentrico_w[j])-1.0783*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_L=(-0.4114-3.5072*Math.log10(Factor_acentrico_w[j])-0.78798*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametros_de_interaccion_binarios_liquido [0][j]=(Parametro_S2_L*Math.pow(Temperatura_reducida_liquido[j],2)+Parametro_S1_L*Temperatura_reducida_liquido[j]+Parametro_S0_L);
				}
				if(j>26){
					Parametro_S0_L=(-0.01664-0.37283*Math.log10(Factor_acentrico_w[j])+1.31757*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_L=(0.48147+3.35342*Math.log10(Factor_acentrico_w[j])-1.0783*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_L=(-0.4114-3.5072*Math.log10(Factor_acentrico_w[j])-0.78798*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametros_de_interaccion_binarios_liquido [0][j]=(Parametro_S2_L*Math.pow(Temperatura_reducida_liquido[j],2)+Parametro_S1_L*Temperatura_reducida_liquido[j]+Parametro_S0_L);
				}
			}
			//Para Dioxido de carbono-Hidrocarburos.				
			for (int j=0;j<numero_de_compuestos;j++){
				if(j<22){
					Parametro_S0_L=(0.4025636+0.1748927*Math.log10(Factor_acentrico_w[j]));
					Parametro_S1_L=(-0.94812-0.6009864*Math.log10(Factor_acentrico_w[j]));
					Parametro_S2_L=(0.741843368+0.441775*Math.log10(Factor_acentrico_w[j]));
					Parametros_de_interaccion_binarios_liquido [24][j]=(Parametro_S2_L*Math.pow(Temperatura_reducida_liquido[j],2)+Parametro_S1_L*Temperatura_reducida_liquido[j]+Parametro_S0_L)*(1.044269-4.375e-5*Presion_liquido);
				}
				if(j>26){
					Parametro_S0_L=(0.4025636+0.1748927*Math.log10(Factor_acentrico_w[j]));
					Parametro_S1_L=(-0.94812-0.6009864*Math.log10(Factor_acentrico_w[j]));
					Parametro_S2_L=(0.741843368+0.441775*Math.log10(Factor_acentrico_w[j]));
					Parametros_de_interaccion_binarios_liquido [24][j]=(Parametro_S2_L*Math.pow(Temperatura_reducida_liquido[j],2)+Parametro_S1_L*Temperatura_reducida_liquido[j]+Parametro_S0_L)*(1.044269-4.375e-5*Presion_liquido);
				}
			}
			//Para Sulfuro de hidrogeno-Hidrocarburos.	
			double [] matriz_e_L=new double[numero_de_compuestos];
			for (int i = 0; i < numero_de_compuestos; i++) {
				matriz_e_L [i]=Math.pow((Parametro_a[i]*Math.log(2)),0.5)/Parametro_b[i];
			}
			for (int j=0;j<numero_de_compuestos;j++){
				if(j<22){
					Parametros_de_interaccion_binarios_liquido [23][j]=(0.077654+0.017921*(-Math.pow(matriz_e_L[23]-matriz_e_L[j],0.5)/(2*matriz_e_L[23]*matriz_e_L[j])));
				}
				if(j>26){
					Parametros_de_interaccion_binarios_liquido [23][j]=(0.077654+0.017921*(-Math.pow(matriz_e_L[23]-matriz_e_L[j],0.5)/(2*matriz_e_L[23]*matriz_e_L[j])));
				}
			}
			//	5.3.2) 	Parámetros (a*alfa) y (b)
			for (int i = 0; i <numero_de_compuestos; i++) {
				for (int j = 0; j <numero_de_compuestos; j++) {
					parametro_a_alfa_liquido=parametro_a_alfa_liquido+(Composicion_molar_liquido[i]*Composicion_molar_liquido[j])*Math.pow((Parametro_a[i]*Parametro_a[j]*Parametro_alfa_liquido[i]*Parametro_alfa_liquido[j]),0.5)*(1-Parametros_de_interaccion_binarios_liquido[i][j]);
				}
			}
			for (int i = 0; i <numero_de_compuestos; i++) {
				parametro_b_liquido=parametro_b_liquido+Composicion_molar_liquido[i]*Parametro_b[i];
			}

			//	5.4) 	Parámetros A y B
			Parametro_A_liquido = (parametro_a_alfa_liquido*Presion_liquido)/(float)(Math.pow((Constante_gas_ideal_R*Temperatura_liquido),2));
			Parametro_B_liquido  = (parametro_b_liquido*Presion_liquido)/(Constante_gas_ideal_R*Temperatura_liquido);

			// 5.5) 	Impresión de los parametros de caracterización de cada componente
			for (int i = 0; i <numero_de_compuestos; i++) {
				if (Composicion_molar_liquido[i]!=0) {
					//System.out.println("compuesto "+i+" :");
					//System.out.println("Composición i="+"	"+Composicion_molar_liquido[i]);
					//System.out.println("T. reducida del liquido = "+Temperatura_reducida_liquido[i]);
					//System.out.println("Presión reducida del liquido ="+ Presion_reducida_liquido[i]);
					//System.out.println("parametro alfa del liquido= "+Parametro_alfa_liquido[i]);
				}
			}
//			System.out.println("parametro a alfa del liquido= "+ parametro_a_alfa_liquido);
//			System.out.println("parametro b del liquido= "+ parametro_b_liquido);
//			System.out.println(" parametro A del liquido= "+ Parametro_A_liquido);
//			System.out.println(" parametro B del liquido = "+ Parametro_B_liquido);









			//=============================================================================================================================================
			//   6) CALCULO DE LAS PROPIEDADES DE LA FASE VAPOR



			double [] Parametro_alfa_vapor = new double [numero_de_compuestos];
			Double parametro_a_alfa_vapor=(double)0;
			Double parametro_b_vapor= (double)0;
			Double Parametro_A_vapor= (double)0;
			Double Parametro_B_vapor= (double)0;

			// 6.1)		Propiedades reducidas.
			double [] Temperatura_reducida_vapor = new double [numero_de_compuestos];
			double [] Presion_reducida_vapor = new double [numero_de_compuestos];
			for (int i = 0; i < numero_de_compuestos; i++) {
				Temperatura_reducida_vapor[i]=Temperatura_vapor/Temperatura_critica[i];
				Presion_reducida_vapor [i]=Presion_vapor/Presion_critica[i];			
			}

			// 6.2) 	Vectores m y alfa
			for (int i = 0; i <numero_de_compuestos; i++) {
				Parametro_alfa_vapor [i]=Math.pow((1+Parametro_m [i]*(1-Math.pow(Temperatura_reducida_vapor[i],0.5))),2);
			}

			// 6.3) 	Parámetros (a*alfa) y (b)
			// 6.3.1) 	Calculo de la matriz de parámetros de interaccion binarios para el alimento.Método de Nikos et al.(1986). Calculo de los parámetros de interacción binarios para el metano, nitrogeno y dioxido de carbono.
			double[][]Parametros_de_interaccion_binarios_vapor = new double [numero_de_compuestos][numero_de_compuestos];	
			for (int i = 0; i < numero_de_compuestos; i++) {
				for (int j=0; j<numero_de_compuestos;j++){
					Parametros_de_interaccion_binarios_vapor[i][j]=0;
				}
			}
			Double Parametro_S0_V=(double)0;
			Double Parametro_S1_V=(double)0;
			Double Parametro_S2_V=(double)0;
			//Para Nitrogeno-Hidrocarburos.
			for (int j=1;j<numero_de_compuestos;j++){
				if( j<22){
					Parametro_S0_V=(0.1751787-0.7043*Math.log10(Factor_acentrico_w[j])-0.862066*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_V=(-0.584474+1.328*Math.log10(Factor_acentrico_w[j])+2.035767*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_V=(2.257079+7.869765*Math.log10(Factor_acentrico_w[j])+13.50466*Math.pow(Math.log10(Factor_acentrico_w[j]),2)+8.3864*Math.pow(Math.log10(Factor_acentrico_w[j]),3));
					Parametros_de_interaccion_binarios_vapor [22][j]=(Parametro_S2_L*Math.pow(Temperatura_reducida_vapor[j],2)+Parametro_S1_L*Temperatura_reducida_vapor[j]+Parametro_S0_L)*(1.04-4.2e-5*Presion_vapor);
				}
				if(j>26){
					Parametro_S0_V=(0.1751787-0.7043*Math.log10(Factor_acentrico_w[j])-0.862066*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_V=(-0.584474+1.328*Math.log10(Factor_acentrico_w[j])+2.035767*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_V=(2.257079+7.869765*Math.log10(Factor_acentrico_w[j])+13.50466*Math.pow(Math.log10(Factor_acentrico_w[j]),2)+8.3864*Math.pow(Math.log10(Factor_acentrico_w[j]),3));
					Parametros_de_interaccion_binarios_vapor [22][j]=(Parametro_S2_V*Math.pow(Temperatura_reducida_vapor[j],2)+Parametro_S1_V*Temperatura_reducida_vapor[j]+Parametro_S0_V)*(1.04-4.2e-5*Presion_vapor);
				}
			}
			//Para Metano-Hidrocarburos.				
			for (int j=0;j<numero_de_compuestos;j++){
				if(j>0 && j<22){
					Parametro_S0_V=(-0.01664-0.37283*Math.log10(Factor_acentrico_w[j])+1.31757*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_V=(0.48147+3.35342*Math.log10(Factor_acentrico_w[j])-1.0783*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_V=(-0.4114-3.5072*Math.log10(Factor_acentrico_w[j])-0.78798*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametros_de_interaccion_binarios_vapor [0][j]=(Parametro_S2_V*Math.pow(Temperatura_reducida_vapor[j],2)+Parametro_S1_V*Temperatura_reducida_vapor[j]+Parametro_S0_V);
				}
				if(j>26){
					Parametro_S0_V=(-0.01664-0.37283*Math.log10(Factor_acentrico_w[j])+1.31757*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S1_V=(0.48147+3.35342*Math.log10(Factor_acentrico_w[j])-1.0783*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametro_S2_V=(-0.4114-3.5072*Math.log10(Factor_acentrico_w[j])-0.78798*Math.pow(Math.log10(Factor_acentrico_w[j]),2));
					Parametros_de_interaccion_binarios_vapor [0][j]=(Parametro_S2_V*Math.pow(Temperatura_reducida_vapor[j],2)+Parametro_S1_V*Temperatura_reducida_vapor[j]+Parametro_S0_V);
				}
			}
			//Para Dioxido de carbono-Hidrocarburos.				
			for (int j=0;j<numero_de_compuestos;j++){
				if(j<22){
					Parametro_S0_V=(0.4025636+0.1748927*Math.log10(Factor_acentrico_w[j]));
					Parametro_S1_V=(-0.94812-0.6009864*Math.log10(Factor_acentrico_w[j]));
					Parametro_S2_V=(0.741843368+0.441775*Math.log10(Factor_acentrico_w[j]));
					Parametros_de_interaccion_binarios_vapor [24][j]=(Parametro_S2_V*Math.pow(Temperatura_reducida_vapor[j],2)+Parametro_S1_V*Temperatura_reducida_vapor[j]+Parametro_S0_V)*(1.044269-4.375e-5*Presion_vapor);
				}
				if(j>26){
					Parametro_S0_V=(0.4025636+0.1748927*Math.log10(Factor_acentrico_w[j]));
					Parametro_S1_V=(-0.94812-0.6009864*Math.log10(Factor_acentrico_w[j]));
					Parametro_S2_V=(0.741843368+0.441775*Math.log10(Factor_acentrico_w[j]));
					Parametros_de_interaccion_binarios_vapor [24][j]=(Parametro_S2_V*Math.pow(Temperatura_reducida_vapor[j],2)+Parametro_S1_V*Temperatura_reducida_vapor[j]+Parametro_S0_V)*(1.044269-4.375e-5*Presion_vapor);
				}
			}
			//Para Sulfuro de hidrogeno-Hidrocarburos.	
			double [] matriz_e_V=new double[numero_de_compuestos];
			for (int i = 0; i < numero_de_compuestos; i++) {
				matriz_e_V [i]=Math.pow((Parametro_a[i]*Math.log(2)),0.5)/Parametro_b[i];
			}
			for (int j=0;j<numero_de_compuestos;j++){
				if(j<22){
					Parametros_de_interaccion_binarios_vapor [23][j]=(0.077654+0.017921*(-Math.pow(matriz_e_V[23]-matriz_e_V[j],0.5)/(2*matriz_e_V[23]*matriz_e_V[j])));
				}
				if(j>26){
					Parametros_de_interaccion_binarios_vapor [23][j]=(0.077654+0.017921*(-Math.pow(matriz_e_V[23]-matriz_e_V[j],0.5)/(2*matriz_e_V[23]*matriz_e_V[j])));
				}
			}

			//	6.3.2) 	Parámetros (a*alfa) y (b)
			for (int i = 0; i <numero_de_compuestos; i++) {
				for (int j = 0; j <numero_de_compuestos; j++) {
					parametro_a_alfa_vapor=parametro_a_alfa_vapor+(Composicion_molar_vapor[i]*Composicion_molar_vapor[j])*Math.pow((Parametro_a[i]*Parametro_a[j]*Parametro_alfa_vapor[i]*Parametro_alfa_vapor[j]),0.5)*(1-Parametros_de_interaccion_binarios_vapor[i][j]);
				}
			}
			for (int i = 0; i <numero_de_compuestos; i++) {
				parametro_b_vapor=parametro_b_vapor+Composicion_molar_vapor[i]*Parametro_b[i];
			}
			//	6.4) 	Parámetros A y B
			Parametro_A_vapor = (parametro_a_alfa_vapor*Presion_vapor)/(float)(Math.pow((Constante_gas_ideal_R*Temperatura_vapor),2));
			Parametro_B_vapor  = (parametro_b_vapor*Presion_vapor)/(Constante_gas_ideal_R*Temperatura_vapor);

			// 6.5) 	Impresión de los parametros de caracterización de cada componente
			for (int i = 0; i <numero_de_compuestos; i++) {
				if (Composicion_molar_vapor[i]!=0) {
					//System.out.println("compuesto "+i+" :");
					//System.out.println("Composición i= "+Composicion_molar_vapor[i]);
					//System.out.println("T. reducida del vapor = "+Temperatura_reducida_vapor[i]);
					//System.out.println("Presión reducida del vapor ="+ Presion_reducida_vapor[i]);
					//System.out.println("parametro alfa del vapor= "+Parametro_alfa_vapor[i]);
				}
			}
			//System.out.println("parametro a alfa del vapor= "+ parametro_a_alfa_vapor);
			//System.out.println("parametro b del vapor= "+ parametro_b_vapor);
			//System.out.println(" parametro A del vapor= "+ Parametro_A_vapor);
			//System.out.println(" parametro B del vapor = "+ Parametro_B_vapor);









			//=============================================================================================================================================
			// 7) DETEMINACIÓN DEL EQUILIBRIO A TRAVÉS DE LA ECUACION DE PENG-ROBINSON PARA EL CALCULO DEL FACTOR DE COMPRESIBILIDAD DE AMBAS FASES.



			// 7.1) 	Calculo de las raices de Z_ Para la fase líquida
			Double Parametro_A_iteracion =(double)0; 
			Double Parametro_B_iteracion=(double)0;
			Parametro_A_iteracion=Parametro_A_liquido;
			Parametro_B_iteracion=Parametro_B_liquido;
			double [] Vector_coeficientes_L={1,(Parametro_B_iteracion-1),Parametro_A_iteracion-2*Parametro_B_iteracion-3*Math.pow(Parametro_B_iteracion, 2),-Parametro_A_iteracion*Parametro_B_iteracion+Math.pow(Parametro_B_iteracion,2)+Math.pow(Parametro_B_iteracion,3) };
			Calculo_raices_metodo_Graeffe g_L=new Calculo_raices_metodo_Graeffe(Vector_coeficientes_L);
			Vector vec_L = g_L.mostrarRaices();
			for (int i = 0; i < vec_L.size(); i++) {
				//System.out.println(vec_L.get(i));
			}
			Parametro_ZL=(Double) vec_L.get(0);
			//System.out.println(Parametro_ZL);

			// 7.2) 	Calculo de las raices de Z_ Para la fase vapor
			Parametro_A_iteracion=Parametro_A_vapor;
			Parametro_B_iteracion=Parametro_B_vapor;
			double [] Vector_coeficientes_V={1,(Parametro_B_iteracion-1),Parametro_A_iteracion-2*Parametro_B_iteracion-3*Math.pow(Parametro_B_iteracion, 2),-Parametro_A_iteracion*Parametro_B_iteracion+Math.pow(Parametro_B_iteracion,2)+Math.pow(Parametro_B_iteracion,3) };
			Calculo_raices_metodo_Graeffe g_V=new Calculo_raices_metodo_Graeffe(Vector_coeficientes_V);
			Vector vec_V = g_V.mostrarRaices();
			Parametro_ZV=(Double) vec_V.get(vec_V.size()-1);

			// 	7.3) Impresion de los factores de compresibilidad de la fase líquida y la fase vapor
			//System.out.println("Factor de compresibilidad del líquido: "+Parametro_ZL);
			//System.out.println("Factor de compresibilidad del vapor: "+Parametro_ZV);






			//=============================================================================================================================================
			// 8) DETERMINACIÓN DE LOS COEFICIENTES DE FUGACIDAD Y CALCULO DE LOS COEFICIENTES DE REPARTO (Ki) (Peng-Robinson)



			// 8.1) 	Determinación de las funciones PSI para la fase líquida y la fase vapor
			double Funcion_PSI_vapor[]=new double [numero_de_compuestos]; 
			double Funcion_PSI_liquido[]=new double [numero_de_compuestos];
			for (int i = 0; i <numero_de_compuestos; i++) {
				if (Composicion[i]!=0){
					for (int j = 0; j <numero_de_compuestos; j++) {
						if (Composicion[j]!=0){
							//System.out.println(i +"  "+  j );
							Funcion_PSI_liquido [i]=Funcion_PSI_liquido [i]+ Composicion_molar_liquido[j]*Math.pow((Parametro_a[i]*Parametro_a[j]*Parametro_alfa_liquido[i]*Parametro_alfa_liquido[j]),0.5)*(1-Parametros_de_interaccion_binarios_liquido[i][j]);
							Funcion_PSI_vapor [i]=Funcion_PSI_vapor [i]+Composicion_molar_vapor[j]*Math.pow((Parametro_a[i]*Parametro_a[j]*Parametro_alfa_vapor[i]*Parametro_alfa_vapor[j]),0.5)*(1-Parametros_de_interaccion_binarios_vapor[i][j]);
						}
					}
				}
			}

			// 8.2) 	Calculo de coeficientes de fugacidad para la fase líquida y para la fase vapor.
			double Coeficiente_de_fugacidad_liquido[] =new double[numero_de_compuestos];
			double Coeficiente_de_fugacidad_vapor  []=new double[numero_de_compuestos];
			for (int i = 0; i <numero_de_compuestos; i++) {
				Coeficiente_de_fugacidad_liquido [i]=Math.exp(Parametro_b[i]*(Parametro_ZL-1)/parametro_b_liquido-Math.log(Parametro_ZL-Parametro_B_liquido)-Parametro_A_liquido/(2*Math.pow(2,0.5)*Parametro_B_liquido)*(2*Funcion_PSI_liquido[i]/parametro_a_alfa_liquido-(Parametro_b[i]/parametro_b_liquido))*(Math.log((Parametro_ZL+(1+Math.pow(2,0.5))*Parametro_B_liquido)/(Parametro_ZL+(1-Math.pow(2,0.5))*Parametro_B_liquido))));
				Coeficiente_de_fugacidad_vapor [i]=Math.exp(Parametro_b[i]*(Parametro_ZV-1)/parametro_b_vapor-Math.log(Parametro_ZL-Parametro_B_vapor)-Parametro_A_vapor/(2*Math.pow(2,0.5)*Parametro_B_vapor)*(2*Funcion_PSI_vapor[i]/parametro_a_alfa_vapor-(Parametro_b[i]/parametro_b_vapor))*(Math.log((Parametro_ZL+(1+Math.pow(2,0.5))*Parametro_B_vapor)/(Parametro_ZL+(1-Math.pow(2,0.5))*Parametro_B_vapor))));
			}

			// 8.3) 	Calculo de los coeficientes de reparto
			for (int i = 0; i <numero_de_compuestos; i++) {
				Constantes_de_reparto[i]=Coeficiente_de_fugacidad_liquido[i]/Coeficiente_de_fugacidad_vapor[i];
			}

			// 8.4) 	Validacion de la constantes
			for (int i = 0; i <numero_de_compuestos; i++) {
				Validacion_de_las_constantes_de_reparto=Validacion_de_las_constantes_de_reparto+(Constantes_de_reparto[i]/constantes_Ki_iniciales[i]-1);
			}

			// 	8.5) 	Calculo de la tolerancia permitida para los coeficientes de reparto
			tolerancia_Ki=0;
			for (int i = 0; i <numero_de_compuestos; i++) {
				if(Composicion[i]!=0){
					if (Math.abs(constantes_Ki_iniciales[i]-Constantes_de_reparto[i])>0.009){
						tolerancia_Ki=tolerancia_Ki+10;
					}
				}
			}

			// 8.6) 	Reasignación del valor del las constantes de reparto Ki inicial = Ki calculada. Cuando ambos valores coincidan se dará la convergencia.
			for (int i = 0; i <numero_de_compuestos; i++) {
				if(Composicion[i]!=0){
					constantes_Ki_iniciales[i]=Constantes_de_reparto[i];
				}
			}

			// 8.7) 	Impresión de los coeficientes de fugacidad y de las constantes de reparto.
			//System.out.println(tolerancia_Ki);
			//System.out.println(Funcion_PSI_liquido[0]);
			//System.out.println(Funcion_PSI_vapor[0]);
			for (int i = 0; i <numero_de_compuestos; i++) {
				if(Composicion[i]!=0){
					//System.out.println("K "+i +":	"+Constantes_de_reparto[i]);
				}
			}
			for (int i = 0; i <numero_de_compuestos; i++) {
				if(Composicion[i]!=0){
					//System.out.println("coeficente de fugacidad del liquido:  "+Coeficiente_de_fugacidad_liquido [i] );
				}
			}
		}









		//=============================================================================================================================================
		// 9) ESTANDARIZACION DE LA COMPOSICIÓN DE LA FASE LÍQUIDA Y LA FASE VAPOR



		//9.1) 	Calculo de la sumatiora de las fracciones molares en cada fase
		Double Suma_composicion_liquido=(double)0;
		Double Suma_composicion_vapor=(double)0;
		for (int i = 0; i < numero_de_compuestos; i++) {
			Suma_composicion_liquido=Suma_composicion_liquido+Composicion_molar_liquido[i];
			Suma_composicion_vapor=Suma_composicion_vapor+Composicion_molar_vapor[i];
		}

		//9.2) 	Calculo de la composición molar estandarizada para cada fase
		for (int i = 0; i < numero_de_compuestos; i++) {
			Composicion_molar_liquido[i]=Composicion_molar_liquido[i]/Suma_composicion_liquido;
			Composicion_molar_vapor[i]=Composicion_molar_vapor[i]/Suma_composicion_vapor;
		}

		//9.3) 	Impresión de la fracción molar estandarizada
		for (int i = 0; i < numero_de_compuestos; i++) {
			//System.out.println(Composicion_molar_liquido[i]);
			//System.out.println(Composicion_molar_vapor[i]);
		}









		//=============================================================================================================================================
		// 10)PROPIEDADES DE LA FASE LÍQUIDA Y DE LA FASE VAPOR


		// 10.1) 	Propiedades de la fase líquida.
		Double Peso_molecular_MW_liquido =(double)0;
		Double Densidad_liquido =(double)0;
		for (int i = 0; i < numero_de_compuestos; i++) {
			Peso_molecular_MW_liquido=Peso_molecular_MW_liquido+(Composicion_molar_liquido[i]*Peso_molecular_MW[i]);
		}
		Densidad_liquido=Peso_molecular_MW_liquido*Presion_liquido/(Parametro_ZL*Constante_gas_ideal_R*Temperatura_liquido);

		// 10.2) 	Propiedades de la fase vapor
		Double Peso_molecular_MW_vapor =(double)0;
		Double Densidad_vapor =(double)0;
		for (int i = 0; i < numero_de_compuestos; i++) {
			Peso_molecular_MW_vapor=Peso_molecular_MW_vapor+(Composicion_molar_vapor[i]*Peso_molecular_MW[i]);
		}
		Densidad_vapor=Peso_molecular_MW_vapor*Presion_vapor/(Parametro_ZV*Constante_gas_ideal_R*Temperatura_vapor);

		// 10.3) 	Impresión de las propiedades de la fase líquida y la fase vapor
		for (int i = 0; i < numero_de_compuestos; i++) {
			if (Composicion[i]!=0){
				//System.out.println("Composición molar del vapor	"+Composicion_molar_vapor[i]);
			}
		}
		for (int i = 0; i < numero_de_compuestos; i++) {
			if (Composicion[i]!=0){
				//System.out.println("Composición molar del liquido	"+Composicion_molar_liquido[i]);
			}
		}
				System.out.println("Densidad del líquido: " +Densidad_liquido);
				System.out.println("Peso molecular del líquido: " + Peso_molecular_MW_liquido);
				System.out.println("Densidad del vapor: " +Densidad_vapor);
				System.out.println("Peso molecular del vapor: " + Peso_molecular_MW_vapor);
	}					
}




