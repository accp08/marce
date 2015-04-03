package Vista;

import java.io.ObjectInputStream.GetField;
import java.sql.PseudoColumnUsage;
import java.util.*;

import javax.swing.ListSelectionModel;


import sun.security.action.GetBooleanAction;

import com.sun.org.apache.bcel.internal.generic.FNEG;

import Logica.Compuesto_Caracterizable;
import Persistencia.Lectura_HC_Compuestos_Caracterizables;
import Persistencia.Lectura_constantes_calculo_entalpias;
import Logica.Complejo;
import Logica.Graeffe;
import Logica.Propiedades_calculo_entalpia;

import java.io.ObjectInputStream.GetField;
import java.sql.PseudoColumnUsage;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ListSelectionModel;

import com.sun.org.apache.bcel.internal.generic.FNEG;

import Logica.Compuesto_Caracterizable;
import Logica.Graeffe;
import Persistencia.Lectura_HC_Compuestos_Caracterizables;

public class Equilibrio_fases_SRW {

	public static LinkedList<Compuesto_Caracterizable> lista_de_compuestos= new LinkedList<Compuesto_Caracterizable>();
	public static LinkedList<Propiedades_calculo_entalpia> lista_constantes_calculo_entlpias= new LinkedList<Propiedades_calculo_entalpia>();

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
		lista_constantes_calculo_entlpias = Lectura_constantes_calculo_entalpias.carga();
		lista_de_compuestos = Lectura_HC_Compuestos_Caracterizables.carga();
		int numero_de_compuestos_no_caracterizables= (int)1;
		Double Temperatura_alimento =(double)460;
		Double Presion_alimento =(double)14.7;
		Double Composicion []= new Double [lista_de_compuestos.size()+numero_de_compuestos_no_caracterizables];

		//	1.2)	Constante universal, parámetros de la ecuación elegida para el cálculo.

		Double Constante_gas_ideal_R =(double)10.73;
		//Constantes según la EOS seleccionada
		Float Parametro_omega_a_SRW = (float)0.42747;
		Float Parametro_omega_b_SRW= (float)0.08664;

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

		Composicion[0]=0.45;
		Composicion[1]=0.05;
		Composicion[2]=0.05;		
		Composicion[4]=0.03;
		Composicion[6]=0.01;
		Composicion[7]=0.01;
		Composicion[27]=0.4;


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
		double [] Factor_acentrico_w_no_caracterizables = new double [numero_de_compuestos_no_caracterizables];
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
			T_criticas_no_caracterizables [i] =(double)1160;
			P_criticas_no_caracterizables [i]=(double)285;
			V_criticos_no_caracterizables [i]=(double)2.37073211055279;
			Factor_acentrico_w_no_caracterizables [i] =(double)0.52;
			Peso_molecular_MW_no_caracterizables[i]=215;
		}

		// 2.7)		Calculo de los parámetros a, b y m para compuestos caracterizables y no caracterizables.
		for (int i=0; i<(lista_de_compuestos.size());i++){
			Parametro_a_caracterizables [i]= Parametro_omega_a_SRW*Math.pow(Constante_gas_ideal_R,2)*Math.pow(T_criticas_caracterizables[i],2)/P_criticas_caracterizables[i];
			Parametro_b_caracterizables [i]= Parametro_omega_b_SRW*Constante_gas_ideal_R*T_criticas_caracterizables[i]/P_criticas_caracterizables[i];
			Parametro_m_caracterizables [i]=(0.480+1.574*Factor_acentrico_w_caracterizables[i]-0.176*(float)(Math.pow(Factor_acentrico_w_caracterizables[i],2)));
		}
		for (int i=0; i<numero_de_compuestos_no_caracterizables;i++){
			Parametro_a_no_caracterizables [i]= Parametro_omega_a_SRW*Math.pow(Constante_gas_ideal_R,2)*Math.pow(T_criticas_no_caracterizables[i],2)/P_criticas_no_caracterizables[i];
			Parametro_b_no_caracterizables [i]= Parametro_omega_b_SRW*Constante_gas_ideal_R*T_criticas_no_caracterizables[i]/P_criticas_no_caracterizables[i];
			Parametro_m_no_caracterizables [i]=(0.480+1.574*Factor_acentrico_w_no_caracterizables[i]-0.176*(float)(Math.pow(Factor_acentrico_w_no_caracterizables[i],2)));
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
			if (Composicion[i]!=0){
				//System.out.println(Temperatura_critica[i]);
				//System.out.println(Presion_critica[i]);
				//System.out.println(Volumen_critco [i]);
				//System.out.println(Factor_acentrico_w[i]);
				//System.out.println(Peso_molecular_MW[i]);
				//System.out.println("Parametro_ai  "+i+" : "+Parametro_a[i]);
				//System.out.println("Parametro_bi  "+i+" : "+Parametro_b[i]);
				//System.out.println(Parametro_m [i]);
			}
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

		// 		3.5) 	Propiedades del alimento
		Double Peso_molecular_MW_alimento =(double)0;
		for (int i = 0; i < numero_de_compuestos; i++) {
			Peso_molecular_MW_alimento=Peso_molecular_MW_alimento+(Composicion[i]*Peso_molecular_MW[i]);
		}

		// 		3.6) 	Impresión de las propiedades del alimento
		//System.out.println("Peso molecular del alimento: " + Peso_molecular_MW_alimento);

















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
				Parametro_m [i]=(0.480+1.574*Factor_acentrico_w[i]-0.176*(float)(Math.pow(Factor_acentrico_w[i],2)));
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
					//System.out.println("Composición i= "+Composicion_molar_liquido[i]);
					//System.out.println("T. reducida del liquido = "+Temperatura_reducida_liquido[i]);
					//System.out.println("Presión reducida del liquido ="+ Presion_reducida_liquido[i]);
					//System.out.println("parametro alfa del liquido= "+Parametro_alfa_liquido[i]);
				}
			}
			//System.out.println("parametro a alfa del liquido= "+ parametro_a_alfa_liquido);
			//System.out.println("parametro b del liquido= "+ parametro_b_liquido);
			//System.out.println(" parametro A del liquido= "+ Parametro_A_liquido);
			//System.out.println(" parametro B del liquido = "+ Parametro_B_liquido);









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
			Graeffe g_L=new Graeffe(Vector_coeficientes_L);
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
			Graeffe g_V=new Graeffe(Vector_coeficientes_V);
			Vector vec_V = g_V.mostrarRaices();
			Parametro_ZV=(Double) vec_V.get(vec_V.size()-1);

			// 	7.3) Impresion de los factores de compresibilidad de la fase líquida y la fase vapor
			//			System.out.println("Factor de compresibilidad del líquido: "+Parametro_ZL);
			//			System.out.println("Factor de compresibilidad del vapor: "+Parametro_ZV);









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

		//		System.out.println("Densidad del líquido: " +Densidad_liquido);
		//		System.out.println("Peso molecular del líquido: " + Peso_molecular_MW_liquido);
		//		System.out.println("Densidad del vapor: " +Densidad_vapor);
		//		System.out.println("Peso molecular del vapor: " + Peso_molecular_MW_vapor);








		//=============================================================================================================================================
		// DETERMINACIÓN DE LA TEMPERATURA DE CADA CORRIENTE DE PROCESO (USO DE LA ECUACIÓN DE LEE-KESLER).

		//=============================================================================================================================================








		//=============================================================================================================================================
		// 11) CALCULO DE LAS ENTALPIAS DE CADA CORRIENTE


		double[] Entalpia_molar_compuestos_alimento=new double [numero_de_compuestos];
		double[]Entalpia_molar_compuestos_liquido=new double [numero_de_compuestos];
		double[] Entalpia_molar_compuestos_vapor=new double [numero_de_compuestos];


		Double  Temperatura_Kelvin_alimento=(double)(5*(Temperatura_alimento-460-32)/9+273.15);
		Double Temperatura_Kelvin_liquido=(double)(5*(Temperatura_liquido-460-32)/9+273.15);
		Double Temperatura_Kelvin_vapor=(double)(5*(Temperatura_vapor-460-32)/9+273.15);

		// 	11.1) Entalpias molares ideales.
		// 11.1.1) 	Entalpias molres ideles de los compuestos caracterizables.
		for (int i = 0; i < lista_de_compuestos.size(); i++) {
			Entalpia_molar_compuestos_alimento[i]=(lista_constantes_calculo_entlpias.get(i).getConstante_A_entalpia()+lista_constantes_calculo_entlpias.get(i).getConstante_B_entalpia()*(5*(Temperatura_alimento-460-32)/9+273.15)+lista_constantes_calculo_entlpias.get(i).getConstante_C_entalpia()*Math.pow((5*(Temperatura_alimento-460-32)/9+273.15), 2)+lista_constantes_calculo_entlpias.get(i).getConstante_D_entalpia()*Math.pow((5*(Temperatura_alimento-460-32)/9+273.15),3)+lista_constantes_calculo_entlpias.get(i).getConstante_E_entalpia()*Math.pow((5*(Temperatura_alimento-460-32)/9+273.15),4)+lista_constantes_calculo_entlpias.get(i).getConstante_F_entalpia()*Math.pow((5*(Temperatura_alimento-460-32)/9+273.15), 5))*Peso_molecular_MW_caracterizables[i]*0.94781712/2.20462262185;
			Entalpia_molar_compuestos_liquido[i]=(lista_constantes_calculo_entlpias.get(i).getConstante_A_entalpia()+lista_constantes_calculo_entlpias.get(i).getConstante_B_entalpia()*(5*(Temperatura_liquido-460-32)/9+273.15)+lista_constantes_calculo_entlpias.get(i).getConstante_C_entalpia()*Math.pow((5*(Temperatura_liquido-460-32)/9+273.15), 2)+lista_constantes_calculo_entlpias.get(i).getConstante_D_entalpia()*Math.pow((5*(Temperatura_liquido-460-32)/9+273.15),3)+lista_constantes_calculo_entlpias.get(i).getConstante_E_entalpia()*Math.pow((5*(Temperatura_liquido-460-32)/9+273.15),4)+lista_constantes_calculo_entlpias.get(i).getConstante_F_entalpia()*Math.pow((5*(Temperatura_liquido-460-32)/9+273.15), 5))*Peso_molecular_MW_caracterizables[i]*0.94781712/2.20462262185;
			Entalpia_molar_compuestos_vapor[i]=(lista_constantes_calculo_entlpias.get(i).getConstante_A_entalpia()+lista_constantes_calculo_entlpias.get(i).getConstante_B_entalpia()*(5*(Temperatura_vapor-460-32)/9+273.15)+lista_constantes_calculo_entlpias.get(i).getConstante_C_entalpia()*Math.pow((5*(Temperatura_vapor-460-32)/9+273.15), 2)+lista_constantes_calculo_entlpias.get(i).getConstante_D_entalpia()*Math.pow((5*(Temperatura_vapor-460-32)/9+273.15),3)+lista_constantes_calculo_entlpias.get(i).getConstante_E_entalpia()*Math.pow((5*(Temperatura_vapor-460-32)/9+273.15),4)+lista_constantes_calculo_entlpias.get(i).getConstante_F_entalpia()*Math.pow((5*(Temperatura_vapor-460-32)/9+273.15), 5))*Peso_molecular_MW_caracterizables[i]*0.94781712/2.20462262185;
		}

		// 11.1.2) 	Entalpias molares ideles de los compuestos no caracterizables.
		for (int i = lista_de_compuestos.size(); i <numero_de_compuestos; i++) {
			Entalpia_molar_compuestos_alimento[i]=((100*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+800)+(32.15+Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+186.5)*(Temperatura_Kelvin_alimento/100)+(3.708*Peso_molecular_MW_no_caracterizables[i-lista_constantes_calculo_entlpias.size()]-26.42)*Math.pow(Temperatura_Kelvin_alimento/100, 2)+(-0.0859*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+0.4)*Math.pow(Temperatura_Kelvin_alimento/100, 3));
			Entalpia_molar_compuestos_liquido[i]=((100*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+800)+(32.15+Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+186.5)*(Temperatura_Kelvin_liquido/100)+(3.708*Peso_molecular_MW_no_caracterizables[i-lista_constantes_calculo_entlpias.size()]-26.42)*Math.pow(Temperatura_Kelvin_liquido/100, 2)+(-0.0859*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+0.4)*Math.pow(Temperatura_Kelvin_liquido/100, 3));
			Entalpia_molar_compuestos_vapor[i]=((100*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+800)+(32.15+Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+186.5)*(Temperatura_Kelvin_vapor/100)+(3.708*Peso_molecular_MW_no_caracterizables[i-lista_constantes_calculo_entlpias.size()]-26.42)*Math.pow(Temperatura_Kelvin_vapor/100, 2)+(-0.0859*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]+0.4)*Math.pow(Temperatura_Kelvin_vapor/100, 3));
		}

		// 11.1.3) 	Entalpias molres ideles de cada corriente de proceso.
		Double Entalpia_molar_alimento=(double)0;
		Double Entalpia_molar_liquido=(double)0;
		Double Entalpia_molar_vapor=(double)0;
		for (int i = 0; i < numero_de_compuestos; i++) {
			Entalpia_molar_alimento=Entalpia_molar_alimento+ Entalpia_molar_compuestos_alimento[i]*Composicion[i];
			Entalpia_molar_liquido=Entalpia_molar_liquido+ Entalpia_molar_compuestos_liquido[i]*Composicion_molar_liquido[i];
			Entalpia_molar_vapor=Entalpia_molar_vapor+ Entalpia_molar_compuestos_vapor[i]*Composicion_molar_vapor[i];	
		}

		// 11.1.4) 	Entalpias másicas ideles de cada corriente de proceso.
		Double Entalpia_masica_alimento=(double)0;
		Double Entalpia_masica_liquido=(double)0;
		Double Entalpia_masica_vapor=(double)0;
		Entalpia_masica_alimento=Entalpia_molar_alimento/Peso_molecular_MW_alimento;
		Entalpia_masica_liquido=Entalpia_molar_liquido/Peso_molecular_MW_liquido;
		Entalpia_masica_vapor=Entalpia_molar_vapor/Peso_molecular_MW_vapor;

		// 11.2)	Calculo del factor acentrico de cada corriente de proceso.
		Double Factor_acentrico_w_mezcla_alimento=(double)0;
		Double Factor_acentrico_w_mezcla_liquido=(double)0;
		Double Factor_acentrico_w_mezcla_vapor=(double)0;
		for (int i = 0; i <numero_de_compuestos; i++) {
			Factor_acentrico_w_mezcla_alimento=Factor_acentrico_w_mezcla_alimento+Factor_acentrico_w[i]*Composicion[i];			
			Factor_acentrico_w_mezcla_liquido=Factor_acentrico_w_mezcla_liquido+Factor_acentrico_w[i]*Composicion_molar_liquido[i];			
			Factor_acentrico_w_mezcla_vapor=Factor_acentrico_w_mezcla_vapor+Factor_acentrico_w[i]*Composicion_molar_vapor[i];			
		}

		// 11.3)	Calculo de las propiedades Pseudocriticas parac cada corriente de proceso.

		// 11.3.1)	Calculo del Pseudo-Volumen Crítico  de cada corriente de proceso.
		Double Volumen_Critico_Vc_mezcla_alimento=(double)0;
		Double Volumen_Critico_Vc_mezcla_liquido=(double)0;
		Double Volumen_Critico_Vc_mezcla_vapor=(double)0;
		for (int i = 0; i <numero_de_compuestos; i++) {
			for (int j = 0; j <numero_de_compuestos; j++) {
				Volumen_Critico_Vc_mezcla_alimento=Volumen_Critico_Vc_mezcla_alimento+(Composicion[j]*Composicion[i]*Math.pow(Math.pow(Volumen_critco[i], 0.3333)+Math.pow(Volumen_critco[j],0.3333),3))/8;			
				Volumen_Critico_Vc_mezcla_liquido=Volumen_Critico_Vc_mezcla_liquido+(Composicion_molar_liquido[j]*Composicion_molar_liquido[i]*Math.pow(Math.pow(Volumen_critco[i], 0.3333)+Math.pow(Volumen_critco[j],0.3333),3))/8;			
				Volumen_Critico_Vc_mezcla_vapor=Volumen_Critico_Vc_mezcla_vapor+(Composicion_molar_vapor[j]*Composicion_molar_vapor[i]*Math.pow(Math.pow(Volumen_critco[i], 0.3333)+Math.pow(Volumen_critco[j],0.3333),3))/8;			
			}
		}
		// 11.3.2)	Calculo de la Pseudo-Temperatura Crítica  de cada corriente de proceso.
		Double Temperatura_Critica_Tc_mezcla_alimento=(double)0;
		Double Temperatura_Critica_Tc_mezcla_liquido=(double)0;
		Double Temperatura_Critica_Tc_mezcla_vapor=(double)0;
		for (int i = 0; i <numero_de_compuestos; i++) {
			for (int j = 0; j <numero_de_compuestos; j++) {
				Temperatura_Critica_Tc_mezcla_alimento=Temperatura_Critica_Tc_mezcla_alimento+(Composicion[j]*Composicion[i]*Math.pow(Math.pow(Volumen_critco[i], 0.3333)+Math.pow(Volumen_critco[j],0.3333),3)*Math.pow(Temperatura_critica[i]*Temperatura_critica[j],0.5))/(8*Volumen_Critico_Vc_mezcla_alimento);			
				Temperatura_Critica_Tc_mezcla_liquido=Temperatura_Critica_Tc_mezcla_liquido+(Composicion_molar_liquido[j]*Composicion_molar_liquido[i]*Math.pow(Math.pow(Volumen_critco[i], 0.3333)+Math.pow(Volumen_critco[j],0.3333),3)*Math.pow(Temperatura_critica[i]*Temperatura_critica[j],0.5))/(8*Volumen_Critico_Vc_mezcla_liquido);			
				Temperatura_Critica_Tc_mezcla_vapor=Temperatura_Critica_Tc_mezcla_vapor+(Composicion_molar_vapor[j]*Composicion_molar_vapor[i]*Math.pow(Math.pow(Volumen_critco[i], 0.3333)+Math.pow(Volumen_critco[j],0.3333),3)*Math.pow(Temperatura_critica[i]*Temperatura_critica[j],0.5))/(8*Volumen_Critico_Vc_mezcla_vapor);			

			}
		}
		// 11.3.3)		Calculo de la Pseudo-Presion Crítica  de cada corriente de proceso.
		Double Presion_Critica_Pc_mezcla_alimento=(double)0;
		Double Presion_Critica_Pc_mezcla_liquido=(double)0;
		Double Presion_Critica_Pc_mezcla_vapor=(double)0;
		for (int i = 0; i <numero_de_compuestos; i++) {
			for (int j = 0; j <numero_de_compuestos; j++) {
				Presion_Critica_Pc_mezcla_alimento=(0.2905-0.085*Factor_acentrico_w_mezcla_alimento)*Constante_gas_ideal_R*Temperatura_Critica_Tc_mezcla_alimento/Volumen_Critico_Vc_mezcla_alimento;
				Presion_Critica_Pc_mezcla_liquido=(0.2905-0.085*Factor_acentrico_w_mezcla_liquido)*Constante_gas_ideal_R*Temperatura_Critica_Tc_mezcla_liquido/Volumen_Critico_Vc_mezcla_liquido;
				Presion_Critica_Pc_mezcla_vapor=(0.2905-0.085*Factor_acentrico_w_mezcla_vapor)*Constante_gas_ideal_R*Temperatura_Critica_Tc_mezcla_vapor/Volumen_Critico_Vc_mezcla_vapor;
			}
		}

		// 11.4)	Calculo de las propiedades reducidas para cada corriente de proceso.
		Double Temperatura_reducida_mezcla_alimento=Temperatura_alimento/Temperatura_Critica_Tc_mezcla_alimento;
		Double Temperatura_reducida_mezcla_liquido=Temperatura_liquido/Temperatura_Critica_Tc_mezcla_liquido;
		Double Temperatura_reducida_mezcla_vapor=Temperatura_vapor/Temperatura_Critica_Tc_mezcla_vapor;
		Double Presion_reducida_mezcla_alimento=Presion_alimento/Presion_Critica_Pc_mezcla_alimento;
		Double Presion_reducida_mezcla_liquido=Presion_liquido/Presion_Critica_Pc_mezcla_liquido;
		Double Presion_reducida_mezcla_vapor=Presion_vapor/Presion_Critica_Pc_mezcla_vapor;

		// 11.5)	Impresión de las propiedades pseudocríticas y propiedades reducidas para las corrientes de proceso
		//System.out.println("Volumen_Critico_Vc_mezcla_alimento: "+Volumen_Critico_Vc_mezcla_alimento);
		//System.out.println("Temperatura_Critica_Tc_mezcla_alimento: "+Temperatura_Critica_Tc_mezcla_alimento);
		//System.out.println("Presion_Critica_Pc_mezcla_alimento: "+Presion_Critica_Pc_mezcla_alimento);
		//System.out.println("Volumen_Critico_Vc_mezcla_liquido: "+Volumen_Critico_Vc_mezcla_liquido);
		//System.out.println("Temperatura_Critica_Tc_mezcla_liquido: "+Temperatura_Critica_Tc_mezcla_liquido);
		//System.out.println("Presion_Critica_Pc_mezcla_liquido: "+Presion_Critica_Pc_mezcla_liquido);
		//System.out.println("Volumen_Critico_Vc_mezcla_vapor: "+Volumen_Critico_Vc_mezcla_vapor);
		//System.out.println("Temperatura_Critica_Tc_mezcla_vapor: "+Temperatura_Critica_Tc_mezcla_vapor);
		//System.out.println("Presion_Critica_Pc_mezcla_vapor: "+Presion_Critica_Pc_mezcla_vapor);
		//System.out.println("Temperatura_reducida_mezcla_alimento:  "+Temperatura_reducida_mezcla_alimento);
		//System.out.println("Presion_reducida_mezcla_alimento: "+Presion_reducida_mezcla_alimento);
		//System.out.println("Temperatura_reducida_mezcla_liquido: "+Temperatura_reducida_mezcla_liquido);
		//System.out.println("Presion_reducida_mezcla_liquido: " + Presion_reducida_mezcla_liquido);
		//System.out.println("Temperatura_reducida_mezcla_vapor: "+Temperatura_reducida_mezcla_vapor);
		//System.out.println("Presion_reducida_mezcla_vapor:"+Presion_reducida_mezcla_vapor);

		// 11.6)	Calculo de las entalpías residuales para cada una de las corrientes.

		// 11.6.1) Declaración de los parámetros para el fluido simple y el fluido de referencia

		Double Parametro_b1_fluido_simple=(double)0.1181193;
		Double Parametro_b2_fluido_simple=(double)0.265728;
		Double Parametro_b3_fluido_simple=(double)0.154790;
		Double Parametro_b4_fluido_simple=(double)0.030323;
		Double Parametro_c1_fluido_simple=(double)0.0236744;
		Double Parametro_c2_fluido_simple=(double)0.0186984;
		Double Parametro_c3_fluido_simple=(double)0;
		Double Parametro_c4_fluido_simple=(double)0.042724;
		Double Parametro_d1_fluido_simple=(double)0.155488e-4;
		Double Parametro_d2_fluido_simple=(double)0.623689e-4;
		Double Parametro_beta_fluido_simple=(double)0.65392;
		Double Parametro_gamma_fluido_simple=(double)0.060167;

		Double Parametro_b1_fluido_referencia=(double)0.2026579;
		Double Parametro_b2_fluido_referencia=(double)0.331511;
		Double Parametro_b3_fluido_referencia=(double)0.027655;
		Double Parametro_b4_fluido_referencia=(double)0.203488;
		Double Parametro_c1_fluido_referencia=(double)0.0313385;
		Double Parametro_c2_fluido_referencia=(double)0.0503618;
		Double Parametro_c3_fluido_referencia=(double)0.016901;
		Double Parametro_c4_fluido_referencia=(double)0.041577;
		Double Parametro_d1_fluido_referencia=(double)0.48736e-4;
		Double Parametro_d2_fluido_referencia=(double)0.0740336e-4;
		Double Parametro_beta_fluido_referencia=(double)1.226;
		Double Parametro_gamma_fluido_referencia=(double)0.03754;

		// 11.6.2)	Evaluación del factor del volumen reducido para el fluido simple y el fluido de referencia.
		Double Parametro_BLK_fluido_referencia_alimento =(double)0;
		Double Parametro_CLK_fluido_referencia_alimento =(double)0;
		Double Parametro_DLK_fluido_referencia_alimento =(double)0;
		Double Parametro_ELK_fluido_referencia_alimento =(double)0;
		Double Parametro_BLK_fluido_simple_alimento =(double)0;
		Double Parametro_CLK_fluido_simple_alimento =(double)0;
		Double Parametro_DLK_fluido_simple_alimento =(double)0;	
		Double Parametro_ELK_fluido_simple_alimento =(double)0;
		Double Parametro_BLK_fluido_referencia_liquido =(double)0;
		Double Parametro_CLK_fluido_referencia_liquido =(double)0;
		Double Parametro_DLK_fluido_referencia_liquido =(double)0;
		Double Parametro_ELK_fluido_referencia_liquido =(double)0;
		Double Parametro_BLK_fluido_simple_liquido =(double)0;
		Double Parametro_CLK_fluido_simple_liquido =(double)0;
		Double Parametro_DLK_fluido_simple_liquido =(double)0;
		Double Parametro_ELK_fluido_simple_liquido =(double)0;
		Double Parametro_BLK_fluido_referencia_vapor =(double)0;
		Double Parametro_CLK_fluido_referencia_vapor =(double)0;
		Double Parametro_DLK_fluido_referencia_vapor =(double)0;
		Double Parametro_ELK_fluido_referencia_vapor =(double)0;
		Double Parametro_BLK_fluido_simple_vapor =(double)0;
		Double Parametro_CLK_fluido_simple_vapor =(double)0;
		Double Parametro_DLK_fluido_simple_vapor =(double)0;
		Double Parametro_ELK_fluido_simple_vapor =(double)0;

		Double Factor_de_compresibilidad_F_fluido_simple_alimento=(double)1;
		Double Factor_de_compresibilidad_F_fluido_simple_liquido=(double)1;
		Double Factor_de_compresibilidad_F_fluido_simple_vapor=(double)1;
		Double Factor_de_compresibilidad_F_fluido_referencia_alimento=(double)1;
		Double Factor_de_compresibilidad_F_fluido_referencia_liquido=(double)1;
		Double Factor_de_compresibilidad_F_fluido_referencia_vapor=(double)1;

		Double Derivada_Factor_de_compresibilidad_F_fluido_simple_alimento=(double)0;
		Double Derivada_Factor_de_compresibilidad_F_fluido_simple_liquido=(double)0;
		Double Derivada_Factor_de_compresibilidad_F_fluido_simple_vapor=(double)0;
		Double Derivada_Factor_de_compresibilidad_F_fluido_referencia_alimento=(double)0;
		Double Derivada_Factor_de_compresibilidad_F_fluido_referencia_liquido=(double)0;
		Double Derivada_Factor_de_compresibilidad_F_fluido_referencia_vapor=(double)0;

		Double Volumen_reducido_fluido_simple_alimento=(double)1;
		Double Volumen_reducido_fluido_simple_vapor=(double)1;
		Double Volumen_reducido_fluido_simple_liquido=(double)1;
		Double Volumen_reducido_fluido_referencia_alimento=(double)1;
		Double Volumen_reducido_fluido_referencia_vapor=(double)1;
		Double Volumen_reducido_fluido_referencia_liquido=(double)1;

		Double Nuevo_Volumen_reducido_fluido_simple_alimento=(double)0;
		Double Nuevo_Volumen_reducido_fluido_simple_vapor=(double)0;
		Double Nuevo_Volumen_reducido_fluido_simple_liquido=(double)0;
		Double Nuevo_Volumen_reducido_fluido_referencia_alimento=(double)0;
		Double Nuevo_Volumen_reducido_fluido_referencia_vapor=(double)0;
		Double Nuevo_Volumen_reducido_fluido_referencia_liquido=(double)0;

		Parametro_BLK_fluido_simple_alimento=Parametro_b1_fluido_simple-Parametro_b2_fluido_simple/Temperatura_reducida_mezcla_alimento-Parametro_b3_fluido_simple/Math.pow(Temperatura_reducida_mezcla_alimento,2)-Parametro_b4_fluido_simple/Math.pow(Temperatura_reducida_mezcla_alimento,3);
		Parametro_CLK_fluido_simple_alimento=Parametro_c1_fluido_simple-Parametro_c2_fluido_simple/Temperatura_reducida_mezcla_alimento;
		Parametro_DLK_fluido_simple_alimento=Parametro_d1_fluido_simple+Parametro_d2_fluido_simple/Temperatura_reducida_mezcla_alimento;
		Parametro_BLK_fluido_referencia_alimento=Parametro_b1_fluido_referencia-Parametro_b2_fluido_referencia/Temperatura_reducida_mezcla_alimento-Parametro_b3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_alimento,2)-Parametro_b4_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_alimento,3);
		Parametro_CLK_fluido_referencia_alimento=Parametro_c1_fluido_referencia-Parametro_c2_fluido_referencia/Temperatura_reducida_mezcla_alimento+Parametro_c3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_alimento,3);
		Parametro_DLK_fluido_referencia_alimento=Parametro_d1_fluido_referencia+Parametro_d2_fluido_referencia/Temperatura_reducida_mezcla_alimento;

		Parametro_BLK_fluido_simple_liquido=Parametro_b1_fluido_simple-Parametro_b2_fluido_simple/Temperatura_reducida_mezcla_liquido-Parametro_b3_fluido_simple/Math.pow(Temperatura_reducida_mezcla_liquido,2)-Parametro_b4_fluido_simple/Math.pow(Temperatura_reducida_mezcla_liquido,3);
		Parametro_CLK_fluido_simple_liquido=Parametro_c1_fluido_simple-Parametro_c2_fluido_simple/Temperatura_reducida_mezcla_liquido;
		Parametro_DLK_fluido_simple_liquido=Parametro_d1_fluido_simple+Parametro_d2_fluido_simple/Temperatura_reducida_mezcla_liquido;
		Parametro_BLK_fluido_referencia_liquido=Parametro_b1_fluido_referencia-Parametro_b2_fluido_referencia/Temperatura_reducida_mezcla_liquido-Parametro_b3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_liquido,2)-Parametro_b4_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_liquido,3);
		Parametro_CLK_fluido_referencia_liquido=Parametro_c1_fluido_referencia-Parametro_c2_fluido_referencia/Temperatura_reducida_mezcla_liquido+Parametro_c3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_liquido,3);
		Parametro_DLK_fluido_referencia_liquido=Parametro_d1_fluido_referencia+Parametro_d2_fluido_referencia/Temperatura_reducida_mezcla_liquido;

		Parametro_BLK_fluido_simple_vapor=Parametro_b1_fluido_simple-Parametro_b2_fluido_simple/Temperatura_reducida_mezcla_vapor-Parametro_b3_fluido_simple/Math.pow(Temperatura_reducida_mezcla_vapor,2)-Parametro_b4_fluido_simple/Math.pow(Temperatura_reducida_mezcla_vapor,3);
		Parametro_CLK_fluido_simple_vapor=Parametro_c1_fluido_simple-Parametro_c2_fluido_simple/Temperatura_reducida_mezcla_vapor;
		Parametro_DLK_fluido_simple_vapor=Parametro_d1_fluido_simple+Parametro_d2_fluido_simple/Temperatura_reducida_mezcla_vapor;
		Parametro_BLK_fluido_referencia_vapor=Parametro_b1_fluido_referencia-Parametro_b2_fluido_referencia/Temperatura_reducida_mezcla_vapor-Parametro_b3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_vapor,2)-Parametro_b4_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_vapor,3);
		Parametro_CLK_fluido_referencia_vapor=Parametro_c1_fluido_referencia-Parametro_c2_fluido_referencia/Temperatura_reducida_mezcla_vapor+Parametro_c3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_vapor,3);
		Parametro_DLK_fluido_referencia_vapor=Parametro_d1_fluido_referencia+Parametro_d2_fluido_referencia/Temperatura_reducida_mezcla_vapor;

		while(Math.abs(Factor_de_compresibilidad_F_fluido_simple_alimento)>0.005){
			Factor_de_compresibilidad_F_fluido_simple_alimento=1-(Presion_reducida_mezcla_alimento*Volumen_reducido_fluido_simple_alimento/Temperatura_reducida_mezcla_alimento+Parametro_BLK_fluido_simple_alimento/Volumen_reducido_fluido_simple_alimento+Parametro_CLK_fluido_simple_alimento/Math.pow(Volumen_reducido_fluido_simple_alimento,2)+Parametro_DLK_fluido_simple_alimento/Math.pow(Volumen_reducido_fluido_simple_alimento,5)+(Parametro_c4_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_simple_alimento, 2)))*(Parametro_beta_fluido_simple+Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_alimento, 2))*Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_alimento,2)));
			Derivada_Factor_de_compresibilidad_F_fluido_simple_alimento=-Presion_reducida_mezcla_alimento/Temperatura_reducida_mezcla_alimento-Parametro_BLK_fluido_simple_alimento/Math.pow(Volumen_reducido_fluido_simple_alimento,2)-2*Parametro_CLK_fluido_simple_alimento/Math.pow(Volumen_reducido_fluido_simple_alimento,3)-5*Parametro_DLK_fluido_simple_alimento/Math.pow(Volumen_reducido_fluido_simple_alimento,6)+Math.exp(Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_alimento,2))*(-2*0.041557*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_simple_alimento,3))+2*Parametro_gamma_fluido_simple*Parametro_c4_fluido_simple*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_simple_alimento,5)))+Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_alimento,2))*(-4*Parametro_c4_fluido_simple*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_simple_alimento,5))+2*Math.pow(Parametro_gamma_fluido_simple,2)*Parametro_c4_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_simple_alimento,7)));
			Nuevo_Volumen_reducido_fluido_simple_alimento=Volumen_reducido_fluido_simple_alimento-Factor_de_compresibilidad_F_fluido_simple_alimento/Derivada_Factor_de_compresibilidad_F_fluido_simple_alimento;
			Volumen_reducido_fluido_simple_alimento=Nuevo_Volumen_reducido_fluido_simple_alimento;
			//			System.out.println("Factor_de_compresibilidad_F_fluido_simple_alimento=" +Factor_de_compresibilidad_F_fluido_simple_alimento);
			//			System.out.println("Derivada_Factor_de_compresibilidad_F_fluido_simple_alimento=  "+ Derivada_Factor_de_compresibilidad_F_fluido_simple_alimento);
		}

		while(Math.abs(Factor_de_compresibilidad_F_fluido_simple_vapor)>0.005){
			Factor_de_compresibilidad_F_fluido_simple_vapor=1-(Presion_reducida_mezcla_vapor*Volumen_reducido_fluido_simple_vapor/Temperatura_reducida_mezcla_vapor+Parametro_BLK_fluido_simple_vapor/Volumen_reducido_fluido_simple_vapor+Parametro_CLK_fluido_simple_vapor/Math.pow(Volumen_reducido_fluido_simple_vapor,2)+Parametro_DLK_fluido_simple_vapor/Math.pow(Volumen_reducido_fluido_simple_vapor,5)+(Parametro_c4_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_simple_vapor, 2)))*(Parametro_beta_fluido_simple+Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_vapor, 2))*Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_vapor,2)));
			Derivada_Factor_de_compresibilidad_F_fluido_simple_vapor=-Presion_reducida_mezcla_vapor/Temperatura_reducida_mezcla_vapor-Parametro_BLK_fluido_simple_vapor/Math.pow(Volumen_reducido_fluido_simple_vapor,2)-2*Parametro_CLK_fluido_simple_vapor/Math.pow(Volumen_reducido_fluido_simple_vapor,3)-5*Parametro_DLK_fluido_simple_vapor/Math.pow(Volumen_reducido_fluido_simple_vapor,6)+Math.exp(Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_vapor,2))*(-2*0.041557*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_simple_vapor,3))+2*Parametro_gamma_fluido_simple*Parametro_c4_fluido_simple*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_simple_vapor,5)))+Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_vapor,2))*(-4*Parametro_c4_fluido_simple*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_simple_vapor,5))+2*Math.pow(Parametro_gamma_fluido_simple,2)*Parametro_c4_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_simple_vapor,7)));
			Nuevo_Volumen_reducido_fluido_simple_vapor=Volumen_reducido_fluido_simple_vapor-Factor_de_compresibilidad_F_fluido_simple_vapor/Derivada_Factor_de_compresibilidad_F_fluido_simple_vapor;
			Volumen_reducido_fluido_simple_vapor=Nuevo_Volumen_reducido_fluido_simple_vapor;
			//			System.out.println("Factor_de_compresibilidad_F_fluido_simple_vapor=" +Factor_de_compresibilidad_F_fluido_simple_vapor);
			//			System.out.println("Derivada_Factor_de_compresibilidad_F_fluido_simple_vapor=  "+ Derivada_Factor_de_compresibilidad_F_fluido_simple_vapor);
		}

		while (Math.abs(Factor_de_compresibilidad_F_fluido_simple_liquido)>0.005){
			Factor_de_compresibilidad_F_fluido_simple_liquido=1-(Presion_reducida_mezcla_liquido*Volumen_reducido_fluido_simple_liquido/Temperatura_reducida_mezcla_liquido+Parametro_BLK_fluido_simple_liquido/Volumen_reducido_fluido_simple_liquido+Parametro_CLK_fluido_simple_liquido/Math.pow(Volumen_reducido_fluido_simple_liquido,2)+Parametro_DLK_fluido_simple_liquido/Math.pow(Volumen_reducido_fluido_simple_liquido,5)+(Parametro_c4_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_simple_liquido, 2)))*(Parametro_beta_fluido_simple+Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_liquido, 2))*Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_liquido,2)));
			Derivada_Factor_de_compresibilidad_F_fluido_simple_liquido=-Presion_reducida_mezcla_liquido/Temperatura_reducida_mezcla_liquido-Parametro_BLK_fluido_simple_liquido/Math.pow(Volumen_reducido_fluido_simple_liquido,2)-2*Parametro_CLK_fluido_simple_liquido/Math.pow(Volumen_reducido_fluido_simple_liquido,3)-5*Parametro_DLK_fluido_simple_liquido/Math.pow(Volumen_reducido_fluido_simple_liquido,6)+Math.exp(Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_liquido,2))*(-2*0.041557*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_simple_liquido,3))+2*Parametro_gamma_fluido_simple*Parametro_c4_fluido_simple*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_simple_liquido,5)))+Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_liquido,2))*(-4*Parametro_c4_fluido_simple*Parametro_beta_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_simple_liquido,5))+2*Math.pow(Parametro_gamma_fluido_simple,2)*Parametro_c4_fluido_simple/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_simple_liquido,7)));
			Nuevo_Volumen_reducido_fluido_simple_liquido=Volumen_reducido_fluido_simple_liquido-Factor_de_compresibilidad_F_fluido_simple_liquido/Derivada_Factor_de_compresibilidad_F_fluido_simple_liquido;
			Volumen_reducido_fluido_simple_liquido=Nuevo_Volumen_reducido_fluido_simple_liquido;		
			//			System.out.println("Factor_de_compresibilidad_F_fluido_simple_liquido=" +Factor_de_compresibilidad_F_fluido_simple_liquido);
			//			System.out.println("Derivada_Factor_de_compresibilidad_F_fluido_simple_liquido=  "+ Derivada_Factor_de_compresibilidad_F_fluido_simple_liquido);
		}

		while(Math.abs(Factor_de_compresibilidad_F_fluido_referencia_alimento)>0.005){
			Factor_de_compresibilidad_F_fluido_referencia_alimento=1-(Presion_reducida_mezcla_alimento*Volumen_reducido_fluido_referencia_alimento/Temperatura_reducida_mezcla_alimento+Parametro_BLK_fluido_referencia_alimento/Volumen_reducido_fluido_referencia_alimento+Parametro_CLK_fluido_referencia_alimento/Math.pow(Volumen_reducido_fluido_referencia_alimento,2)+Parametro_DLK_fluido_referencia_alimento/Math.pow(Volumen_reducido_fluido_referencia_alimento,5)+(Parametro_c4_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_referencia_alimento, 2)))*(Parametro_beta_fluido_referencia+Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_alimento, 2))*Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_alimento,2)));
			Derivada_Factor_de_compresibilidad_F_fluido_referencia_alimento=-Presion_reducida_mezcla_alimento/Temperatura_reducida_mezcla_alimento-Parametro_BLK_fluido_referencia_alimento/Math.pow(Volumen_reducido_fluido_referencia_alimento,2)-2*Parametro_CLK_fluido_referencia_alimento/Math.pow(Volumen_reducido_fluido_referencia_alimento,3)-5*Parametro_DLK_fluido_referencia_alimento/Math.pow(Volumen_reducido_fluido_referencia_alimento,6)+Math.exp(Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_alimento,2))*(-2*0.041557*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_referencia_alimento,3))+2*Parametro_gamma_fluido_referencia*Parametro_c4_fluido_referencia*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_referencia_alimento,5)))+Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_alimento,2))*(-4*Parametro_c4_fluido_referencia*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_referencia_alimento,5))+2*Math.pow(Parametro_gamma_fluido_referencia,2)*Parametro_c4_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_alimento,3)*Math.pow(Volumen_reducido_fluido_referencia_alimento,7)));
			Nuevo_Volumen_reducido_fluido_referencia_alimento=Volumen_reducido_fluido_referencia_alimento-Factor_de_compresibilidad_F_fluido_referencia_alimento/Derivada_Factor_de_compresibilidad_F_fluido_referencia_alimento;
			Volumen_reducido_fluido_referencia_alimento=Nuevo_Volumen_reducido_fluido_referencia_alimento;
			//			System.out.println("Factor_de_compresibilidad_F_fluido_referencia_alimento=" +Factor_de_compresibilidad_F_fluido_referencia_alimento);
			//			System.out.println("Derivada_Factor_de_compresibilidad_F_fluido_referencia_alimento=  "+ Derivada_Factor_de_compresibilidad_F_fluido_referencia_alimento);
		}

		while(Math.abs(Factor_de_compresibilidad_F_fluido_referencia_vapor)>0.005){
			Factor_de_compresibilidad_F_fluido_referencia_vapor=1-(Presion_reducida_mezcla_vapor*Volumen_reducido_fluido_referencia_vapor/Temperatura_reducida_mezcla_vapor+Parametro_BLK_fluido_referencia_vapor/Volumen_reducido_fluido_referencia_vapor+Parametro_CLK_fluido_referencia_vapor/Math.pow(Volumen_reducido_fluido_referencia_vapor,2)+Parametro_DLK_fluido_referencia_vapor/Math.pow(Volumen_reducido_fluido_referencia_vapor,5)+(Parametro_c4_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_referencia_vapor, 2)))*(Parametro_beta_fluido_referencia+Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_vapor, 2))*Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_vapor,2)));
			Derivada_Factor_de_compresibilidad_F_fluido_referencia_vapor=-Presion_reducida_mezcla_vapor/Temperatura_reducida_mezcla_vapor-Parametro_BLK_fluido_referencia_vapor/Math.pow(Volumen_reducido_fluido_referencia_vapor,2)-2*Parametro_CLK_fluido_referencia_vapor/Math.pow(Volumen_reducido_fluido_referencia_vapor,3)-5*Parametro_DLK_fluido_referencia_vapor/Math.pow(Volumen_reducido_fluido_referencia_vapor,6)+Math.exp(Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_vapor,2))*(-2*0.041557*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_referencia_vapor,3))+2*Parametro_gamma_fluido_referencia*Parametro_c4_fluido_referencia*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_referencia_vapor,5)))+Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_vapor,2))*(-4*Parametro_c4_fluido_referencia*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_referencia_vapor,5))+2*Math.pow(Parametro_gamma_fluido_referencia,2)*Parametro_c4_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_vapor,3)*Math.pow(Volumen_reducido_fluido_referencia_vapor,7)));
			Nuevo_Volumen_reducido_fluido_referencia_vapor=Volumen_reducido_fluido_referencia_vapor-Factor_de_compresibilidad_F_fluido_referencia_vapor/Derivada_Factor_de_compresibilidad_F_fluido_referencia_vapor;
			Volumen_reducido_fluido_referencia_vapor=Nuevo_Volumen_reducido_fluido_referencia_vapor;
			//			System.out.println("Factor_de_compresibilidad_F_fluido_referencia_vapor=" +Factor_de_compresibilidad_F_fluido_referencia_vapor);
			//			System.out.println("Derivada_Factor_de_compresibilidad_F_fluido_referencia_vapor=  "+ Derivada_Factor_de_compresibilidad_F_fluido_referencia_vapor);
		}

		while (Math.abs(Factor_de_compresibilidad_F_fluido_referencia_liquido)>0.005){
			Factor_de_compresibilidad_F_fluido_referencia_liquido=1-(Presion_reducida_mezcla_liquido*Volumen_reducido_fluido_referencia_liquido/Temperatura_reducida_mezcla_liquido+Parametro_BLK_fluido_referencia_liquido/Volumen_reducido_fluido_referencia_liquido+Parametro_CLK_fluido_referencia_liquido/Math.pow(Volumen_reducido_fluido_referencia_liquido,2)+Parametro_DLK_fluido_referencia_liquido/Math.pow(Volumen_reducido_fluido_referencia_liquido,5)+(Parametro_c4_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_referencia_liquido, 2)))*(Parametro_beta_fluido_referencia+Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_liquido, 2))*Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_liquido,2)));
			Derivada_Factor_de_compresibilidad_F_fluido_referencia_liquido=-Presion_reducida_mezcla_liquido/Temperatura_reducida_mezcla_liquido-Parametro_BLK_fluido_referencia_liquido/Math.pow(Volumen_reducido_fluido_referencia_liquido,2)-2*Parametro_CLK_fluido_referencia_liquido/Math.pow(Volumen_reducido_fluido_referencia_liquido,3)-5*Parametro_DLK_fluido_referencia_liquido/Math.pow(Volumen_reducido_fluido_referencia_liquido,6)+Math.exp(Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_liquido,2))*(-2*0.041557*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_referencia_liquido,3))+2*Parametro_gamma_fluido_referencia*Parametro_c4_fluido_referencia*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_referencia_liquido,5)))+Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_liquido,2))*(-4*Parametro_c4_fluido_referencia*Parametro_beta_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_referencia_liquido,5))+2*Math.pow(Parametro_gamma_fluido_referencia,2)*Parametro_c4_fluido_referencia/(Math.pow(Temperatura_reducida_mezcla_liquido,3)*Math.pow(Volumen_reducido_fluido_referencia_liquido,7)));
			Nuevo_Volumen_reducido_fluido_referencia_liquido=Volumen_reducido_fluido_referencia_liquido-Factor_de_compresibilidad_F_fluido_referencia_liquido/Derivada_Factor_de_compresibilidad_F_fluido_referencia_liquido;
			Volumen_reducido_fluido_referencia_liquido=Nuevo_Volumen_reducido_fluido_referencia_liquido;
			//	System.out.println("Factor_de_compresibilidad_F_fluido_referencia_liquido=" +Factor_de_compresibilidad_F_fluido_referencia_liquido);
			//	System.out.println("Derivada_Factor_de_compresibilidad_F_fluido_referencia_liquido=  "+ Derivada_Factor_de_compresibilidad_F_fluido_referencia_liquido);
		}


		//		System.out.println("Volumen_reducido_fluido_simple_alimento= "+Volumen_reducido_fluido_simple_alimento);
		//		System.out.println("Volumen_reducido_fluido_simple_vapor= "+Volumen_reducido_fluido_simple_vapor);
		//		System.out.println("Volumen_reducido_fluido_simple_liquido= "+Volumen_reducido_fluido_simple_liquido);
		//		System.out.println("Volumen_reducido_fluido_referencia_alimento= "+Volumen_reducido_fluido_referencia_alimento);
		//		System.out.println("Volumen_reducido_fluido_referencia_vapor= "+Volumen_reducido_fluido_referencia_vapor);
		//		System.out.println("Volumen_reducido_fluido_referencia_liquido= "+Volumen_reducido_fluido_referencia_liquido);


		// 11.6.3) Factor de compresibilidad Z.
		Double Factor_de_compresibilidad_Z_fluido_simple_alimento=(double)0;
		Double Factor_de_compresibilidad_Z_fluido_simple_liquido=(double)0;
		Double Factor_de_compresibilidad_Z_fluido_simple_vapor=(double)0;
		Double Factor_de_compresibilidad_Z_fluido_referencia_alimento=(double)0;
		Double Factor_de_compresibilidad_Z_fluido_referencia_liquido=(double)0;
		Double Factor_de_compresibilidad_Z_fluido_referencia_vapor=(double)0;

		Factor_de_compresibilidad_Z_fluido_simple_alimento=Presion_reducida_mezcla_alimento*Volumen_reducido_fluido_simple_alimento/Temperatura_reducida_mezcla_alimento;
		Factor_de_compresibilidad_Z_fluido_referencia_alimento=Presion_reducida_mezcla_alimento*Volumen_reducido_fluido_referencia_alimento/Temperatura_reducida_mezcla_alimento;
		Factor_de_compresibilidad_Z_fluido_simple_vapor=Presion_reducida_mezcla_vapor*Volumen_reducido_fluido_simple_vapor/Temperatura_reducida_mezcla_vapor;
		Factor_de_compresibilidad_Z_fluido_referencia_vapor=Presion_reducida_mezcla_vapor*Volumen_reducido_fluido_referencia_vapor/Temperatura_reducida_mezcla_vapor;
		Factor_de_compresibilidad_Z_fluido_simple_liquido=Presion_reducida_mezcla_liquido*Volumen_reducido_fluido_simple_liquido/Temperatura_reducida_mezcla_liquido;
		Factor_de_compresibilidad_Z_fluido_referencia_liquido=Presion_reducida_mezcla_liquido*Volumen_reducido_fluido_referencia_liquido/Temperatura_reducida_mezcla_liquido;

		//Impresión del factor de compresibilidad de cada corriente de proceso.
		//		System.out.println(Factor_de_compresibilidad_Z_fluido_simple_alimento);
		//		System.out.println(Factor_de_compresibilidad_Z_fluido_referencia_alimento);
		//		System.out.println(Factor_de_compresibilidad_Z_fluido_simple_vapor);
		//		System.out.println(Factor_de_compresibilidad_Z_fluido_referencia_vapor);
		//		System.out.println(Factor_de_compresibilidad_Z_fluido_simple_liquido);
		//		System.out.println(Factor_de_compresibilidad_Z_fluido_referencia_liquido);


		// 11.6.4)Determinación del parámetro EKL.
		Parametro_ELK_fluido_simple_alimento=Parametro_c4_fluido_simple/(2*Math.pow(Temperatura_reducida_mezcla_alimento,3)*Parametro_gamma_fluido_simple)*(Parametro_beta_fluido_simple+1-(Parametro_beta_fluido_simple+1+Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_alimento,2))*Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_alimento,2)));
		Parametro_ELK_fluido_referencia_alimento=Parametro_c4_fluido_referencia/(2*Math.pow(Temperatura_reducida_mezcla_alimento,3)*Parametro_gamma_fluido_referencia)*(Parametro_beta_fluido_referencia+1-(Parametro_beta_fluido_referencia+1+Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_alimento,2))*Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_alimento,2)));
		Parametro_ELK_fluido_simple_vapor=Parametro_c4_fluido_simple/(2*Math.pow(Temperatura_reducida_mezcla_vapor,3)*Parametro_gamma_fluido_simple)*(Parametro_beta_fluido_simple+1-(Parametro_beta_fluido_simple+1+Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_vapor,2))*Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_vapor,2)));
		Parametro_ELK_fluido_referencia_vapor=Parametro_c4_fluido_referencia/(2*Math.pow(Temperatura_reducida_mezcla_vapor,3)*Parametro_gamma_fluido_referencia)*(Parametro_beta_fluido_referencia+1-(Parametro_beta_fluido_referencia+1+Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_vapor,2))*Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_vapor,2)));
		Parametro_ELK_fluido_simple_liquido=Parametro_c4_fluido_simple/(2*Math.pow(Temperatura_reducida_mezcla_liquido,3)*Parametro_gamma_fluido_simple)*(Parametro_beta_fluido_simple+1-(Parametro_beta_fluido_simple+1+Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_liquido,2))*Math.exp(-Parametro_gamma_fluido_simple/Math.pow(Volumen_reducido_fluido_simple_liquido,2)));
		Parametro_ELK_fluido_referencia_liquido=Parametro_c4_fluido_referencia/(2*Math.pow(Temperatura_reducida_mezcla_liquido,3)*Parametro_gamma_fluido_referencia)*(Parametro_beta_fluido_referencia+1-(Parametro_beta_fluido_referencia+1+Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_liquido,2))*Math.exp(-Parametro_gamma_fluido_referencia/Math.pow(Volumen_reducido_fluido_referencia_liquido,2)));

		// 11.6.5)Determinación de la entalpía residual.
		Double Factor_acentrico_fluido_referencia=(double)0.3978;
		Double Entalpia_residual_fluido_simple_alimento=(double)0;
		Double Entalpia_residual_fluido_simple_vapor=(double)0;
		Double Entalpia_residual_fluido_simple_liquido=(double)0;
		Double Entalpia_residual_fluido_referencia_alimento=(double)0;
		Double Entalpia_residual_fluido_referencia_vapor=(double)0;
		Double Entalpia_residual_fluido_referencia_liquido=(double)0;
		Double Entalpia_residual_alimento=(double)0;
		Double Entalpia_residual_vapor=(double)0;
		Double Entalpia_residual_liquido=(double)0;

		Entalpia_residual_fluido_simple_alimento=-Temperatura_reducida_mezcla_alimento*(Factor_de_compresibilidad_Z_fluido_simple_alimento-1)-((Parametro_b2_fluido_simple+2*Parametro_b3_fluido_referencia/Temperatura_reducida_mezcla_alimento+3*Parametro_b4_fluido_simple/Math.pow(Temperatura_reducida_mezcla_alimento,2))/(Temperatura_reducida_mezcla_alimento*Volumen_reducido_fluido_simple_alimento))-(Parametro_c2_fluido_simple-3*Parametro_c3_fluido_simple/Math.pow(Temperatura_reducida_mezcla_alimento,2))/(2*Temperatura_reducida_mezcla_alimento*Math.pow(Volumen_reducido_fluido_simple_alimento,2))+Parametro_d2_fluido_simple/(5*Temperatura_reducida_mezcla_alimento*Math.pow(Volumen_reducido_fluido_simple_alimento,5))+3*Parametro_ELK_fluido_simple_alimento;
		Entalpia_residual_fluido_simple_vapor=-Temperatura_reducida_mezcla_vapor*(Factor_de_compresibilidad_Z_fluido_simple_vapor-1)-((Parametro_b2_fluido_simple+2*Parametro_b3_fluido_referencia/Temperatura_reducida_mezcla_vapor+3*Parametro_b4_fluido_simple/Math.pow(Temperatura_reducida_mezcla_vapor,2))/(Temperatura_reducida_mezcla_vapor*Volumen_reducido_fluido_simple_vapor))-(Parametro_c2_fluido_simple-3*Parametro_c3_fluido_simple/Math.pow(Temperatura_reducida_mezcla_vapor,2))/(2*Temperatura_reducida_mezcla_vapor*Math.pow(Volumen_reducido_fluido_simple_vapor,2))+Parametro_d2_fluido_simple/(5*Temperatura_reducida_mezcla_vapor*Math.pow(Volumen_reducido_fluido_simple_vapor,5))+3*Parametro_ELK_fluido_simple_vapor;
		Entalpia_residual_fluido_simple_liquido=-Temperatura_reducida_mezcla_liquido*(Factor_de_compresibilidad_Z_fluido_simple_liquido-1)-((Parametro_b2_fluido_simple+2*Parametro_b3_fluido_referencia/Temperatura_reducida_mezcla_liquido+3*Parametro_b4_fluido_simple/Math.pow(Temperatura_reducida_mezcla_liquido,2))/(Temperatura_reducida_mezcla_liquido*Volumen_reducido_fluido_simple_liquido))-(Parametro_c2_fluido_simple-3*Parametro_c3_fluido_simple/Math.pow(Temperatura_reducida_mezcla_liquido,2))/(2*Temperatura_reducida_mezcla_liquido*Math.pow(Volumen_reducido_fluido_simple_liquido,2))+Parametro_d2_fluido_simple/(5*Temperatura_reducida_mezcla_liquido*Math.pow(Volumen_reducido_fluido_simple_liquido,5))+3*Parametro_ELK_fluido_simple_liquido;
		Entalpia_residual_fluido_referencia_alimento=-Temperatura_reducida_mezcla_alimento*(Factor_de_compresibilidad_Z_fluido_referencia_alimento-1)-((Parametro_b2_fluido_referencia+2*Parametro_b3_fluido_referencia/Temperatura_reducida_mezcla_alimento+3*Parametro_b4_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_alimento,2))/(Temperatura_reducida_mezcla_alimento*Volumen_reducido_fluido_referencia_alimento))-(Parametro_c2_fluido_referencia-3*Parametro_c3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_alimento,2))/(2*Temperatura_reducida_mezcla_alimento*Math.pow(Volumen_reducido_fluido_referencia_alimento,2))+Parametro_d2_fluido_referencia/(5*Temperatura_reducida_mezcla_alimento*Math.pow(Volumen_reducido_fluido_referencia_alimento,5))+3*Parametro_ELK_fluido_referencia_alimento;
		Entalpia_residual_fluido_referencia_vapor=-Temperatura_reducida_mezcla_vapor*(Factor_de_compresibilidad_Z_fluido_referencia_vapor-1)-((Parametro_b2_fluido_referencia+2*Parametro_b3_fluido_referencia/Temperatura_reducida_mezcla_vapor+3*Parametro_b4_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_vapor,2))/(Temperatura_reducida_mezcla_vapor*Volumen_reducido_fluido_referencia_vapor))-(Parametro_c2_fluido_referencia-3*Parametro_c3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_vapor,2))/(2*Temperatura_reducida_mezcla_vapor*Math.pow(Volumen_reducido_fluido_referencia_vapor,2))+Parametro_d2_fluido_referencia/(5*Temperatura_reducida_mezcla_vapor*Math.pow(Volumen_reducido_fluido_referencia_vapor,5))+3*Parametro_ELK_fluido_referencia_vapor;
		Entalpia_residual_fluido_referencia_liquido=-Temperatura_reducida_mezcla_liquido*(Factor_de_compresibilidad_Z_fluido_referencia_liquido-1)-((Parametro_b2_fluido_referencia+2*Parametro_b3_fluido_referencia/Temperatura_reducida_mezcla_liquido+3*Parametro_b4_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_liquido,2))/(Temperatura_reducida_mezcla_liquido*Volumen_reducido_fluido_referencia_liquido))-(Parametro_c2_fluido_referencia-3*Parametro_c3_fluido_referencia/Math.pow(Temperatura_reducida_mezcla_liquido,2))/(2*Temperatura_reducida_mezcla_liquido*Math.pow(Volumen_reducido_fluido_referencia_liquido,2))+Parametro_d2_fluido_referencia/(5*Temperatura_reducida_mezcla_liquido*Math.pow(Volumen_reducido_fluido_referencia_liquido,5))+3*Parametro_ELK_fluido_referencia_liquido;

		Entalpia_residual_alimento=Entalpia_residual_fluido_simple_alimento+Factor_acentrico_w_mezcla_alimento/Factor_acentrico_fluido_referencia*(Entalpia_residual_fluido_referencia_alimento-Entalpia_residual_fluido_simple_alimento);
		Entalpia_residual_vapor=Entalpia_residual_fluido_simple_vapor+Factor_acentrico_w_mezcla_vapor/Factor_acentrico_fluido_referencia*(Entalpia_residual_fluido_referencia_vapor-Entalpia_residual_fluido_simple_vapor);
		Entalpia_residual_liquido=Entalpia_residual_fluido_simple_liquido+Factor_acentrico_w_mezcla_liquido/Factor_acentrico_fluido_referencia*(Entalpia_residual_fluido_referencia_liquido-Entalpia_residual_fluido_simple_liquido);

		// 11.6.6)Determinación de la entalpía másica para cada corriente
		Double Entalpia_masica_residual_alimento=(double)0;
		Double Entalpia_masica_residual_vapor=(double)0;
		Double Entalpia_masica_residual_liquido=(double)0;

		Entalpia_masica_residual_alimento=Constante_gas_ideal_R*Temperatura_Critica_Tc_mezcla_alimento*Entalpia_residual_alimento/Peso_molecular_MW_alimento;
		Entalpia_masica_residual_vapor=Constante_gas_ideal_R*Temperatura_Critica_Tc_mezcla_vapor*Entalpia_residual_vapor/Peso_molecular_MW_vapor;
		Entalpia_masica_residual_liquido=Constante_gas_ideal_R*Temperatura_Critica_Tc_mezcla_liquido*Entalpia_residual_liquido/Peso_molecular_MW_liquido;

		// 11.6.7)	Impresión de las entalpías residuales másicas 
		System.out.println("Entalpia_masica_residual_alimento"+Entalpia_masica_residual_alimento);
		System.out.println("Entalpia_masica_residual_vapor"+Entalpia_masica_residual_vapor);
		System.out.println("Entalpia_masica_residual_liquido"+Entalpia_masica_residual_liquido);

		// 11.6.7)	Impresión de las entalpías residuales másicas 
		Double Entalpia_masica_total_alimento=(double)0;
		Double Entalpia_masica_total_vapor=(double)0;
		Double Entalpia_masica_total_liquido=(double)0;
		Entalpia_masica_total_alimento=Entalpia_masica_alimento+Entalpia_masica_residual_alimento;
		Entalpia_masica_total_vapor=Entalpia_masica_vapor+Entalpia_masica_residual_vapor;
		Entalpia_masica_total_liquido=Entalpia_masica_liquido+Entalpia_masica_residual_liquido;

		// 11.6.7)	Impresión de las entalpías residuales totales
		System.out.println("Entalpia_masica_total_alimento"+Entalpia_masica_total_alimento);
		System.out.println("Entalpia_masica_total_vapor"+Entalpia_masica_total_vapor);
		System.out.println("Entalpia_masica_total_liquido"+Entalpia_masica_total_liquido);

		// 11.6.7)	Impresión de las entalpía ideal
		System.out.println("Entalpia_masica_ideal_alimento"+Entalpia_masica_alimento);
		System.out.println("Entalpia_masica_ideal_vapor"+Entalpia_masica_vapor);
		System.out.println("Entalpia_masica_ideal_liquido"+Entalpia_masica_liquido);

		for (int i = 0; i < numero_de_compuestos; i++) {
			if (Composicion[i]!=0){
			System.out.println(Composicion[i]);
			}
		}

	}

}




