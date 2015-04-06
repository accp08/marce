package Logica;

import java.awt.font.NumericShaper;
import java.util.LinkedList;

import javax.crypto.spec.PSource;

import Logica.Objeto_Compuestos_Caracterizables;
import Logica.Objeto_constante_calculo_entalpia;
import Persistencia.Lectura_HC_Compuestos_Caracterizables;
import Persistencia.Lectura_constantes_calculo_entalpias;

public class Caracterizacion_gas {

	public static LinkedList<Objeto_Compuestos_Caracterizables> lista_de_compuestos = new LinkedList<Objeto_Compuestos_Caracterizables>();
	public static LinkedList<Objeto_constante_calculo_entalpia> lista_constantes_calculo_entlpias = new LinkedList<Objeto_constante_calculo_entalpia>();

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub

		//=====================================================================================================================================		
		//   1) VARIABLES, PARAMETROS Y CONSTANTES CONOCIDAS.

		//		1.1)	Variables del sistema; son ingresadas en la pantalla donde se piden los datos para el calculo de equilibrio
		lista_constantes_calculo_entlpias = Lectura_constantes_calculo_entalpias.carga();
		lista_de_compuestos = Lectura_HC_Compuestos_Caracterizables.carga();
		int numero_de_compuestos_no_caracterizables= (int)1;
		Double Temperatura_Gas =(double)560;
		Double Presion_Gas =(double)1000;
		Double Flujo_de_gas=(double)500;
		int numero_de_compuestos =lista_de_compuestos.size()+numero_de_compuestos_no_caracterizables;
		double [] Fraccion_molar_fase_gaseosa= new double [numero_de_compuestos];

		//		2.3)	Propiedades de los compuestos no caracterizables y vectores generales

		double [] T_criticas_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] P_criticas_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] V_criticos_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] Factor_acentrico_w_no_caracterizables = new double [numero_de_compuestos_no_caracterizables];
		double [] Peso_molecular_MW_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];

		double [] Temperatura_Gas_critica_componetes_gas=new double [numero_de_compuestos];
		double [] Presion_Gas_critica_componetes_gas=new double [numero_de_compuestos];
		double [] Volumen_critico_gas=new double [numero_de_compuestos];
		double [] Factor_acentrico_w_gas=new double [numero_de_compuestos];
		double [] Peso_molecular_gas=new double [numero_de_compuestos];
		double [] Fraccion_masica_gas=new double[numero_de_compuestos];
		double [] Parametro_m_caracterizables = new double [numero_de_compuestos];


		//OJO!! ESTA MATRIZ SE DEBE LLENAR CON LOS DATOS PROVENIENTES DE LA CARACTERIZACIÓN DE LOS PSEUDOCOMPONENTES.
		for (int i=0; i<numero_de_compuestos_no_caracterizables;i++){
			T_criticas_no_caracterizables [i] =(double)1185.3;
			P_criticas_no_caracterizables [i]=(double)265;
			V_criticos_no_caracterizables [i]=(double)11.42;
			Factor_acentrico_w_no_caracterizables [i] =(double)0.5620;
			Peso_molecular_MW_no_caracterizables[i]=170.3;
		}


		//Llenado vectores de propiedades conocidas.

		for (int i=0; i<lista_de_compuestos.size();i++){
			Temperatura_Gas_critica_componetes_gas[i]= lista_de_compuestos.get(i).getTemperatura_critica_Tc()+460;
			Presion_Gas_critica_componetes_gas[i]= lista_de_compuestos.get(i).getPresión_critica_Pc();
			Volumen_critico_gas[i]=lista_de_compuestos.get(i).getVolumen_critico_Vc();
			Peso_molecular_gas[i]= lista_de_compuestos.get(i).getMasa_molecular();	
			Factor_acentrico_w_gas [i]=lista_de_compuestos.get(i).getFactor_acentrico_w();
		}

		for (int i=lista_de_compuestos.size(); i<numero_de_compuestos;i++){
			Temperatura_Gas_critica_componetes_gas[i]= T_criticas_no_caracterizables[i-lista_de_compuestos.size()];
			Presion_Gas_critica_componetes_gas[i]= P_criticas_no_caracterizables[i-lista_de_compuestos.size()];
			Volumen_critico_gas[i]=V_criticos_no_caracterizables[i-lista_de_compuestos.size()];
			Peso_molecular_gas[i]= Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()];	
			Factor_acentrico_w_gas [i]=Factor_acentrico_w_no_caracterizables[i-lista_de_compuestos.size()];
		}

		//Las propiedades criticas provienen de la etapa de caracterización y la composición proviene de la etapa de equilibrio.
		//=====================================================================================================================================

		// INICIALIZACIÓN PARA PRUEBAS DE ESCRITORIO

		Fraccion_molar_fase_gaseosa[0]=0.853532609569478;
		Fraccion_molar_fase_gaseosa [1]=6.89028303478793e-002;
		Fraccion_molar_fase_gaseosa [2]=3.87590419477411e-002;
		Fraccion_molar_fase_gaseosa [3]=2.06413578380401e-002;
		Fraccion_molar_fase_gaseosa [4]=1.19367903319133e-002;
		Fraccion_molar_fase_gaseosa [5]=4.67081142948684e-003;
		Fraccion_molar_fase_gaseosa [27]=1.55655853546152e-003;









		//=====================================================================================================================================

		System.out.println("tem. critica");

		for (int i = 0; i <numero_de_compuestos; i++) {
			if (Fraccion_molar_fase_gaseosa[i]!=0) {
				System.out.println(i+ " "+ Temperatura_Gas_critica_componetes_gas[i]);
			}
		}


		// VARIABLES DETERMINADAS PARA LA CARACTERIZACIÓN DE LA FASE GASEOSA.

		Double Peso_molecular_MW_gas =(double)0;
		Double Temperatura_Gas_pseudocritica_Tc= (double)0;
		Double Presion_Gas_pseudocritica_Pc= (double)0;
		Double Temperatura_Gas_pseudoreducida_Tr=(double)0;
		Double Presion_Gas_pseudoreducida_Pr = (double)0;		
		Double Factor_de_compresibilidad_gas= (double)0;
		Double compresibilidad_del_gas_ideal=(double)0;
		Double Gravedad_especifica_gas=(double)0;
		Double Densidad_gas=(double)0;
		Double Densidad_reducida_del_gas=(double)0;
		Double Flujo_de_gas_estandar=(double)0;
		Double Viscosidad_del_gas=(double)0;
		Double Viscosidad_del_gas_condiciones_estandar=(double)0;


		//=====================================================================================================================================		

		//1) DETERMINACIÓN DEL PESO MOLECULAR DE LA FASE GASEOSA

		for(int i=0;i<numero_de_compuestos;i++){
			Peso_molecular_MW_gas=Peso_molecular_MW_gas+Fraccion_molar_fase_gaseosa[i]*Peso_molecular_gas[i];
		}

		System.out.println("Peso molecular"+ Peso_molecular_MW_gas+"  g/gmol");

		//=====================================================================================================================================		

		//2) DETERMINACIÓN DE LA FRACCION MÁSICA DE LA FASE GASEOSA

		for(int i=0;i<numero_de_compuestos;i++){
			Fraccion_masica_gas[i]=Fraccion_molar_fase_gaseosa[i]*Peso_molecular_gas[i]/Peso_molecular_MW_gas;
			// System.out.println(i+"   fracción másica:" + Fraccion_masica_gas[i] );
		}

		//=====================================================================================================================================		

		//3) DETERMINACIÓN DEL FACTOR DE LAS PROPIEDADES REDUCIDAS DE LA FASE GASEOSA.


		//3.1) DETERMINACIÓN DE LA TEMEPRATURA Y LA PRESIÓN CRÍTICA

		for (int i=0;i<numero_de_compuestos;i++){
			Temperatura_Gas_pseudocritica_Tc=Temperatura_Gas_pseudocritica_Tc+Temperatura_Gas_critica_componetes_gas[i]*Fraccion_molar_fase_gaseosa[i];
			Presion_Gas_pseudocritica_Pc=Presion_Gas_pseudocritica_Pc+Presion_Gas_critica_componetes_gas[i]*Fraccion_molar_fase_gaseosa[i];

		}
		//3.1)Corrección por la presencia de fracciones pesadas

		Double parametro_J=(double)0;
		Double parametro_K=(double)0;
		double [] parametro_Fj=new double[numero_de_compuestos];
		double [] parametro_ej=new double[numero_de_compuestos];
		double [] parametro_ek=new double[numero_de_compuestos];
		Double sumatoria_1=(double)0;
		Double sumatoria_2=(double)0;
		Double parametro_Fj_=(double)0;
		Double parametro_ej_=(double)0;
		Double parametro_ek_=(double)0;

		for(int i=0;i<numero_de_compuestos;i++){
			sumatoria_1=sumatoria_1+Fraccion_molar_fase_gaseosa[i]*Temperatura_Gas_critica_componetes_gas[i]/Presion_Gas_critica_componetes_gas[i];
			sumatoria_2=sumatoria_2+Fraccion_molar_fase_gaseosa[i]*Math.pow(Temperatura_Gas_critica_componetes_gas[i]/Presion_Gas_critica_componetes_gas[i],0.5);
		}

		parametro_J=0.33333333*sumatoria_1+0.66666667*Math.pow(sumatoria_2,2);
		parametro_K=sumatoria_2;

		//Para cada fraccion de compuestos pesados...
		sumatoria_1=(double)0;
		sumatoria_2=(double)0;

		for(int i=lista_de_compuestos.size();i<numero_de_compuestos;i++){
			sumatoria_1=sumatoria_1+Fraccion_molar_fase_gaseosa[i]*Temperatura_Gas_critica_componetes_gas[i]/Presion_Gas_critica_componetes_gas[i];
			sumatoria_2=sumatoria_2+Fraccion_molar_fase_gaseosa[i]*Math.pow(Temperatura_Gas_critica_componetes_gas[i]/Presion_Gas_critica_componetes_gas[i],0.5);
			parametro_Fj [i]=0.33333*sumatoria_1+0.666667*Math.pow(sumatoria_2, 0.5);
			parametro_ej [i]=0.6081*parametro_Fj[i]+1.1325*Math.pow(parametro_Fj[i], 2)-14.004*parametro_Fj[i]*Fraccion_molar_fase_gaseosa[i]+64.434*parametro_Fj[i]*Math.pow(Fraccion_molar_fase_gaseosa[i], 2);
			parametro_ek [i]=(Temperatura_Gas_critica_componetes_gas[i]/Math.pow(Presion_Gas_critica_componetes_gas[i],0.5))*(0.3129*Fraccion_molar_fase_gaseosa[i]-4.8156*Math.pow(Fraccion_molar_fase_gaseosa[i], 2)+27.3751*Math.pow(Fraccion_molar_fase_gaseosa [i], 3));
		}
		for(int i=lista_de_compuestos.size();i<numero_de_compuestos;i++){
			parametro_Fj_=parametro_Fj_+parametro_Fj[i];
			parametro_ej_=parametro_ej_+parametro_ej[i];
			parametro_ek_=parametro_ek_+parametro_ek[i];
		}
		parametro_J=parametro_J-parametro_ej_;
		parametro_K=parametro_K-parametro_ek_;

		Temperatura_Gas_pseudocritica_Tc=Temperatura_Gas_pseudocritica_Tc+Math.pow(parametro_K,2)/parametro_J;
		Presion_Gas_pseudocritica_Pc=Presion_Gas_pseudocritica_Pc+(Math.pow(parametro_K,2)/parametro_J)/parametro_J;


		//3.2) Correción por presencia de no hidrocarbros.

		//Preguntar por la fracción molar de  CO2, H2S y N2... presentes en la freccion de caracterizbles. remplazar las variables por las posiciones correspondientes en el vector 
		Double fraccion_molar_Co2=(double)0;
		Double fraccion_molar_H2S=(double)0;
		Double fraccion_molar_N2=(double)0;

		Temperatura_Gas_pseudocritica_Tc=Temperatura_Gas_pseudocritica_Tc-80*fraccion_molar_Co2+130*fraccion_molar_H2S-250*fraccion_molar_N2;
		Presion_Gas_pseudocritica_Pc=Presion_Gas_pseudocritica_Pc+440*fraccion_molar_Co2+600*fraccion_molar_H2S-170*fraccion_molar_N2;

		Temperatura_Gas_pseudoreducida_Tr=Temperatura_Gas/Temperatura_Gas_pseudocritica_Tc;
		Presion_Gas_pseudoreducida_Pr=Presion_Gas/Presion_Gas_pseudocritica_Pc;


		//System.out.println("Tpc = "+ Temperatura_Gas_pseudocritica_Tc+"  ºR");
		//System.out.println("Ppc = "+ Presion_Gas_pseudocritica_Pc+"  psia");
		//		System.out.println("Tpr = "+ Temperatura_Gas_pseudoreducida_Tr);
		//		System.out.println("Ppr = "+ Presion_Gas_pseudoreducida_Pr);

		//=====================================================================================================================================		

		//3) DETERMINACIÓN DEL FACTOR DE COMPRESIBILIDAD. 

		Double C_A1=(double)0.3265;
		Double C_A2=(double)-1.07;
		Double C_A3=(double)-0.5339;
		Double C_A4=(double)0.01569;
		Double C_A5=(double)-0.05165;
		Double C_A6=(double)0.5475;
		Double C_A7=(double)-0.7361;
		Double C_A8=(double)0.1844;
		Double C_A9=(double)0.1056;
		Double C_A10=(double)0.6134;
		Double C_A11=(double)0.721;

		Double X=(double)10;
		Double delta =(double)0.05;
		Double X_1=(double)(X+delta);
		Double X_2=(double)(X-delta);
		Double Ecuacion_1=((-0.27*Presion_Gas_pseudoreducida_Pr/(X*Temperatura_Gas_pseudoreducida_Tr))+(C_A1+C_A2/Temperatura_Gas_pseudoreducida_Tr+C_A3/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 3)+C_A4/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 4)+C_A5/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 5))*X+(C_A6+C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X,2)-C_A9*(C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X,5)+C_A10*(1+C_A11*Math.pow(X,2))*Math.pow(X,2)/Math.pow(Temperatura_Gas_pseudoreducida_Tr,3)*Math.exp(-C_A11*Math.pow(X,2))+1);
		Double D_1=((-0.27*Presion_Gas_pseudoreducida_Pr/(X_1*Temperatura_Gas_pseudoreducida_Tr))+(C_A1+C_A2/Temperatura_Gas_pseudoreducida_Tr+C_A3/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 3)+C_A4/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 4)+C_A5/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 5))*X_1+(C_A6+C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_1,2)-C_A9*(C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_1,5)+C_A10*(1+C_A11*Math.pow(X_1,2))*Math.pow(X_1,2)/Math.pow(Temperatura_Gas_pseudoreducida_Tr,3)*Math.exp(-C_A11*Math.pow(X_1,2))+1);
		Double D_2=((-0.27*Presion_Gas_pseudoreducida_Pr/(X_2*Temperatura_Gas_pseudoreducida_Tr))+(C_A1+C_A2/Temperatura_Gas_pseudoreducida_Tr+C_A3/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 3)+C_A4/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 4)+C_A5/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 5))*X_2+(C_A6+C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_2,2)-C_A9*(C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_2,5)+C_A10*(1+C_A11*Math.pow(X_2,2))*Math.pow(X_2,2)/Math.pow(Temperatura_Gas_pseudoreducida_Tr,3)*Math.exp(-C_A11*Math.pow(X_2,2))+1);
		Double Tolerancia=0.0005;
		Double X_nuevo=(double)0;
		Double Derivada=(double)(D_1-D_2)/(2*delta);
		while (Math.abs(Ecuacion_1)>Tolerancia || X<0 ){
			X_1=X+delta;
			X_2=X-delta;
			Ecuacion_1=((-0.27*Presion_Gas_pseudoreducida_Pr/(X*Temperatura_Gas_pseudoreducida_Tr))+(C_A1+C_A2/Temperatura_Gas_pseudoreducida_Tr+C_A3/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 3)+C_A4/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 4)+C_A5/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 5))*X+(C_A6+C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X,2)-C_A9*(C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X,5)+C_A10*(1+C_A11*Math.pow(X,2))*Math.pow(X,2)/Math.pow(Temperatura_Gas_pseudoreducida_Tr,3)*Math.exp(-C_A11*Math.pow(X,2))+1);
			D_1=((-0.27*Presion_Gas_pseudoreducida_Pr/(X_1*Temperatura_Gas_pseudoreducida_Tr))+(C_A1+C_A2/Temperatura_Gas_pseudoreducida_Tr+C_A3/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 3)+C_A4/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 4)+C_A5/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 5))*X_1+(C_A6+C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_1,2)-C_A9*(C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_1,5)+C_A10*(1+C_A11*Math.pow(X_1,2))*Math.pow(X_1,2)/Math.pow(Temperatura_Gas_pseudoreducida_Tr,3)*Math.exp(-C_A11*Math.pow(X_1,2))+1);
			D_2=((-0.27*Presion_Gas_pseudoreducida_Pr/(X_2*Temperatura_Gas_pseudoreducida_Tr))+(C_A1+C_A2/Temperatura_Gas_pseudoreducida_Tr+C_A3/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 3)+C_A4/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 4)+C_A5/Math.pow(Temperatura_Gas_pseudoreducida_Tr, 5))*X_2+(C_A6+C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_2,2)-C_A9*(C_A7/Temperatura_Gas_pseudoreducida_Tr+C_A8/Math.pow(Temperatura_Gas_pseudoreducida_Tr,2))*Math.pow(X_2,5)+C_A10*(1+C_A11*Math.pow(X_2,2))*Math.pow(X_2,2)/Math.pow(Temperatura_Gas_pseudoreducida_Tr,3)*Math.exp(-C_A11*Math.pow(X_2,2))+1);
			Derivada=(D_1-D_2)/(2*delta);
			X_nuevo=X-Ecuacion_1/Derivada;
			if(X_nuevo<0){
				X_nuevo=Math.abs(X_nuevo);
			}
			//System.out.println("X inicial = "+X+"Derivada= "+ Derivada+"función= "+Ecuacion_1);
			X=X_nuevo;
		}

		Densidad_reducida_del_gas=X;
		Factor_de_compresibilidad_gas=0.27*Presion_Gas_pseudoreducida_Pr/(Temperatura_Gas_pseudoreducida_Tr*Densidad_reducida_del_gas);
		System.out.println("factor de compresibilidad = "+ Factor_de_compresibilidad_gas);

		//=====================================================================================================================================		

		//4) DENSIDAD DE LA FASE GASEOSA

		Densidad_gas=Presion_Gas*Peso_molecular_MW_gas/(Factor_de_compresibilidad_gas*10.73*Temperatura_Gas);
		Densidad_reducida_del_gas=0.27*(Presion_Gas_pseudoreducida_Pr)/(Factor_de_compresibilidad_gas*Temperatura_Gas_pseudoreducida_Tr);
		System.out.println("densidad del gas=  "+ Densidad_gas+ "   lb/ft^3");
		System.out.println("densidad reducida del gas= "+ Densidad_reducida_del_gas+ "   lb/ft^3");

		//=====================================================================================================================================		

		//5) GRAVEDAD ESPECIFICA.

		Gravedad_especifica_gas=Peso_molecular_MW_gas/28.96;
		System.out.println("gravedad especifica del gas= "+ Gravedad_especifica_gas);

		//=====================================================================================================================================		

		//7) FLUJO DE GAS ESTÁNDAR

		Flujo_de_gas_estandar=35.37*Presion_Gas/(Factor_de_compresibilidad_gas*Temperatura_Gas)*Flujo_de_gas;
		//Flujo de gas estandar a condiciones de 14,7psia y 60ºF.
		System.out.println("Flujo_de_gas_estandar= "+Flujo_de_gas_estandar+ "  scf/day");

		//=====================================================================================================================================		

		//8) VISCOSIDAD DEL GAS. - Ecuación de Dean Stiel method _ (pag. 121)

		//8.1) Viscosidad a condiciones estándar

		Double Parametro_em=(5.4402*Math.pow(Temperatura_Gas_pseudocritica_Tc,0.16666667)/(Math.pow(Peso_molecular_MW_gas,0.5)*Math.pow(Presion_Gas_pseudocritica_Pc, 0.666667)));

		if (Temperatura_Gas_pseudoreducida_Tr<=1.5){
			Viscosidad_del_gas_condiciones_estandar=(34e-5*Math.pow(Temperatura_Gas_pseudoreducida_Tr,0.8888889)/Parametro_em);
		}
		else{
			Viscosidad_del_gas_condiciones_estandar=(166.8e-5*Math.pow((0.1338*Temperatura_Gas_pseudoreducida_Tr-0.0932), 0.5555556)/Parametro_em);
		}

		Viscosidad_del_gas=Viscosidad_del_gas_condiciones_estandar+(10.8e-5*(Math.exp(1.329*Densidad_reducida_del_gas)-Math.exp(-1.111*Math.pow(Densidad_reducida_del_gas, 1.8888))))/(Parametro_em);

		System.out.println("viscosidad del gas" + Viscosidad_del_gas + " cP" );

		//=====================================================================================================================================		

		//8) CALOR ESPECÍFICO DEL GAS

		Double Calor_especifico_Gas=(double)0;

		//=====================================================================================================================================		

		//8) EXPORTACION DE LAS PROPIEDADES DE LA FASE GASEOSA PARA LA CORRIENTE INICIAL

		new Mezcla_corriente_alimento_sin_agua(Temperatura_Gas,Presion_Gas,Flujo_de_gas,Fraccion_molar_fase_gaseosa,Densidad_gas,Viscosidad_del_gas,Calor_especifico_Gas,Gravedad_especifica_gas, Calor_especifico_Gas);

		//==================================================================================================================
		//Debe generarse otra exportación como reporte de las condiciones de la corriente que sea visible al usuario 
		//new Exportar_a_ventana(Temperatura_Gas,Presion_Gas,Flujo_de_crudo,Fraccion_molar_crudo_sin_agua,Densidad_crudo,Viscosidad_del_crudo,Calor_especifico,Gravedad_especifica,Grados_API, Fraccion_masica, );
		//==================================================================================================================


	}
}
