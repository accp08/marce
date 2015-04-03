package Vista;

import java.awt.font.NumericShaper;
import java.util.LinkedList;

import javax.crypto.spec.PSource;

import Logica.Compuesto_Caracterizable;
import Logica.Propiedades_calculo_entalpia;
import Persistencia.Lectura_HC_Compuestos_Caracterizables;
import Persistencia.Lectura_constantes_calculo_entalpias;

public class Caracterizacion_crudo {

	public static LinkedList<Compuesto_Caracterizable> lista_de_compuestos = new LinkedList<Compuesto_Caracterizable>();
	public static LinkedList<Propiedades_calculo_entalpia> lista_constantes_calculo_entlpias = new LinkedList<Propiedades_calculo_entalpia>();

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub

		//=====================================================================================================================================		
		//   1) VARIABLES, PARAMETROS Y CONSTANTES CONOCIDAS.

		//		1.1)	Variables del sistema; son ingresadas en la pantalla donde se piden los datos para el calculo de equilibrio
		lista_constantes_calculo_entlpias = Lectura_constantes_calculo_entalpias.carga();
		lista_de_compuestos = Lectura_HC_Compuestos_Caracterizables.carga();
		int numero_de_compuestos_no_caracterizables= (int)1;
		Double Temperatura =(double)131+460;
		Double Presion =(double)1426;
		Double Flujo_de_liquido=(double)500;
		int numero_de_compuestos =lista_de_compuestos.size()+numero_de_compuestos_no_caracterizables;
		double [] Fraccion_molar_fase_liquida= new double [numero_de_compuestos];
		Double Constante_gas_ideal_R =(double)10.73;

		//		2.3)	Propiedades de los compuestos no caracterizables y vectores generales

		double [] T_criticas_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] P_criticas_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] V_criticos_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] Factor_acentrico_w_no_caracterizables = new double [numero_de_compuestos_no_caracterizables];
		double [] Peso_molecular_MW_no_caracterizables=new double [numero_de_compuestos_no_caracterizables];
		double [] gravedad_especifica_no_caracterizables=new double[numero_de_compuestos_no_caracterizables];

		double [] Temperatura_critica_componetes_liquido=new double [numero_de_compuestos];
		double [] Presion_critica_componetes_liquido=new double [numero_de_compuestos];
		double [] Volumen_critico_liquido=new double [numero_de_compuestos];
		double [] Factor_acentrico_w_liquido=new double [numero_de_compuestos];
		double [] Peso_molecular_liquido=new double [numero_de_compuestos];
		double [] Fraccion_masica_liquido=new double[numero_de_compuestos];
		double [] Parametro_m_caracterizables = new double [numero_de_compuestos];
		//te amo 

		//OJO!! ESTA MATRIZ SE DEBE LLENAR CON LOS DATOS PROVENIENTES DE LA CARACTERIZACIÓN DE LOS PSEUDOCOMPONENTES.

		for (int i=0; i<numero_de_compuestos_no_caracterizables;i++){
			T_criticas_no_caracterizables [i] =(double)979.2;
			P_criticas_no_caracterizables [i]=(double)478.6;
			V_criticos_no_caracterizables [i]=(double)6.1593;
			Factor_acentrico_w_no_caracterizables [i] =(double)0.7339;
			Peso_molecular_MW_no_caracterizables[i]=95.93;
			gravedad_especifica_no_caracterizables[i]=0.3202;
		}

		//Llenado vectores de propiedades conocidas.

		for (int i=0; i<lista_de_compuestos.size();i++){
			Temperatura_critica_componetes_liquido[i]= lista_de_compuestos.get(i).getTemperatura_critica_Tc()+460;
			Presion_critica_componetes_liquido[i]= lista_de_compuestos.get(i).getPresión_critica_Pc();
			Volumen_critico_liquido[i]=lista_de_compuestos.get(i).getVolumen_critico_Vc();
			Peso_molecular_liquido[i]= lista_de_compuestos.get(i).getMasa_molecular();	
			Factor_acentrico_w_liquido [i]=lista_de_compuestos.get(i).getFactor_acentrico_w();
		}

		for (int i=lista_de_compuestos.size(); i<numero_de_compuestos;i++){
			Temperatura_critica_componetes_liquido[i]= T_criticas_no_caracterizables[i-lista_de_compuestos.size()];
			Presion_critica_componetes_liquido[i]= P_criticas_no_caracterizables[i-lista_de_compuestos.size()];
			Volumen_critico_liquido[i]=V_criticos_no_caracterizables[i-lista_de_compuestos.size()];
			Peso_molecular_liquido[i]= Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()];	
			Factor_acentrico_w_liquido [i]=Factor_acentrico_w_no_caracterizables[i-lista_de_compuestos.size()];
		}

		//Las propiedades criticas provienen de la etapa de caracterización y la composición proviene de la etapa de equilibrio.
		//=====================================================================================================================================

		// INICIALIZACIÓN PARA PRUEBAS DE ESCRITORIO

//		Fraccion_molar_fase_liquida[0]=0.2840;
//		Fraccion_molar_fase_liquida [1]=0.0716;
//		Fraccion_molar_fase_liquida [2]=0.1048;
//		Fraccion_molar_fase_liquida [3]=0.042;
//		Fraccion_molar_fase_liquida [4]=0.042;
//		Fraccion_molar_fase_liquida [5]=0.0191;
//		Fraccion_molar_fase_liquida [6]=0.0191;
//		Fraccion_molar_fase_liquida [7]=0.0405;
//		Fraccion_molar_fase_liquida [22]=0.0164;
//		Fraccion_molar_fase_liquida [24]=0.0008;
//		Fraccion_molar_fase_liquida [27]=0.3597;

		
		Fraccion_molar_fase_liquida[7]=1;
		//=====================================================================================================================================

		// PROPIEDADES CARACTERIZABLES

		Double Peso_molecular_MW_crudo =(double)0;
		Double Volumen_molal=(double)0;
		Double Densidad_crudo =(double)0;
		Double Densidad_estandar_crudo=(double)0;
		Double Gravedad_especifica=(double)0;
		Double Grados_API=(double)0;
		Double Solubilidad_del_gas_en_el_liquido=(double)0;
		Double Compresibilidad_isotermica =(double)0;
		Double GOR=(double)0;
		Double Oil_formation_volumen_factor=(double)0;
		Double viscosidad =(double)0;
		Double Presion_de_saturacion=(double)0;
		Double Tension_superficial=(double)0;
		Double Temperatura_pseudocritica_Tc=(double)0;
		Double Presion_pseudocritica_Pc=(double)0;
		double [] fraccion_masica_caracterizables=new double[numero_de_compuestos];
		double [] fraccion_masica_no_caracterizables=new double[numero_de_compuestos];
		Double Temperatura_pseudoreducida_Tr_liquido=(double)0;
		Double Presion_pseudoreducida_Pr=(double)0;


		//=====================================================================================================================================

		// 1) DETERMINACIÓN DEL PESO MOLECULAR DEL CRUDO.

		for(int i=0;i<numero_de_compuestos;i++){
			Peso_molecular_MW_crudo=Peso_molecular_MW_crudo+Fraccion_molar_fase_liquida[i]*Peso_molecular_liquido[i];
		}
		//System.out.println("Peso molecular= "+Peso_molecular_MW_crudo+" lb/ft^3");

		//=====================================================================================================================================		

		//2) DETERMINACIÓN DE LA FRACCION MÁSICA DE LA FASE LIQUIDA

		for(int i=0;i<numero_de_compuestos;i++){
			Fraccion_masica_liquido[i]=Fraccion_molar_fase_liquida[i]*Peso_molecular_liquido[i]/Peso_molecular_MW_crudo;
			//System.out.println(i+ "  fraccion masica= "+ Fraccion_masica_liquido [i]);
		}

		//=====================================================================================================================================		

		//3) DETERMINACIÓN DEL FACTOR DE LAS PROPIEDADES REDUCIDAS DE LA FASE LIQUIDA.

		//3.1) DETERMINACIÓN DE LA TEMEPRATURA Y LA PRESIÓN CRÍTICA

		for (int i=0;i<numero_de_compuestos;i++){
			Temperatura_pseudocritica_Tc=Temperatura_pseudocritica_Tc+Temperatura_critica_componetes_liquido[i]*Fraccion_molar_fase_liquida[i];
			Presion_pseudocritica_Pc=Presion_pseudocritica_Pc+Presion_critica_componetes_liquido[i]*Fraccion_molar_fase_liquida[i];
		}
		Temperatura_pseudoreducida_Tr_liquido=Temperatura/Temperatura_pseudocritica_Tc;
		Presion_pseudoreducida_Pr=Presion/Presion_pseudocritica_Pc;
		//		System.out.println("temperatura pseudoreducida= "+Temperatura_pseudoreducida_Tr_liquido+"  ºR");
		//		System.out.println("Presión pseudocrítica= "+Presion_pseudoreducida_Pr+"  psia");

		//=====================================================================================================================================		

		//4) DETERMINACIÓN DEL FACTOR DE COMPRESIBILIDAD. 



		//=====================================================================================================================================		

		// 5) DETERMINACIÓN DE LA DENSIDAD DE LA MEZCLA DE HIDROCARBUROS.

		//Constantes requeridas para el calculo.

		//Para los compuestos caracterizables

		Double Parametro_a=(double)0;
		Double Parametro_b=(double)0;

		double[] Parametro_K=new double [numero_de_compuestos];
		double[] Parametro_N=new double [numero_de_compuestos];
		double[] Parametro_M=new double [numero_de_compuestos];
		double[] Parametro_C=new double [numero_de_compuestos];		

		if ((Temperatura-460)<300){
			Parametro_K [0]=9160.6413;
			Parametro_N [0]=61.893223;
			Parametro_M [0]=3.3162472;
			Parametro_C [0]=0.50874303;
		}
		else{
			Parametro_K [0]=147.47333;
			Parametro_N [0]=3247.4533;
			Parametro_M [0]=-14.072637;
			Parametro_C [0]=1.8326695;
		}
		if ((Temperatura-460)<250){
			Parametro_K [1]=46709.573;
			Parametro_N [1]=-404.48844;
			Parametro_M [1]=5.1520981;
			Parametro_C [1]=0.52239654;
		}
		else{
			Parametro_K [1]=17495.343;
			Parametro_N [1]=34.163551;
			Parametro_M [1]=2.8201736;
			Parametro_C [1]=0.62309877;
		}
		Parametro_K[2]=20247.757;
		Parametro_K[3]=32204.420;
		Parametro_K[4]=33016.212;
		Parametro_K[5]=37046.234;
		Parametro_K[6]=37046.234;
		Parametro_K[7]=52093.006;
		Parametro_K[23]=13200;
		Parametro_K[22]=4300;
		Parametro_K[24]=8166;

		Parametro_N[2]=190.24420;
		Parametro_N[3]=131.63171;
		Parametro_N[4]=146.15445;
		Parametro_N[5]=299.62630;
		Parametro_N[6]=299.62630;
		Parametro_N[7]=254.56097;
		Parametro_N[23]=0;
		Parametro_N[22]=2.293;
		Parametro_N[24]=126;

		Parametro_M[2]=2.1586448;
		Parametro_M[3]=3.3862284;
		Parametro_M[4]=2.902157;
		Parametro_M[5]=2.1954785;
		Parametro_M[6]=2.1954785;
		Parametro_M[7]=3.6961858;
		Parametro_M[23]=17.900;
		Parametro_M[22]=4.490;
		Parametro_M[24]=1.818;

		Parametro_C[2] =0.90832519;
		Parametro_C[3] =1.1013834;
		Parametro_C[4] =1.1168144;
		Parametro_C[5] =1.4364289;
		Parametro_C[6] =1.4364289;
		Parametro_C[7] =1.5929406;
		Parametro_C[23] =0.3945;
		Parametro_C[22] =0.3853;
		Parametro_C[24] =0.3872;

		//calculo de los parámetros a y b 
		//Para los compuestos caracterizables
		double [] parametros_a=new double[numero_de_compuestos]; 
		double [] parametros_b=new double[numero_de_compuestos]; 
		for (int i=0; i<lista_de_compuestos.size();i++){
			parametros_a [i]=Parametro_K [i]*Math.exp(Parametro_N[i]/Temperatura);
			parametros_b [i]=Parametro_M [i]/10000*Temperatura+Parametro_C [i];
		}
		//Para los compuestos no caracterizables
		for (int i=lista_de_compuestos.size(); i<numero_de_compuestos;i++){
			parametros_a [i]=Math.exp(3.8405985e-3*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]-9.5638281e-4*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]/gravedad_especifica_no_caracterizables[i-lista_de_compuestos.size()]+(261.80818/Temperatura)+7.3104464e-6*Math.pow(Peso_molecular_MW_no_caracterizables [i-lista_de_compuestos.size()],2)+10.753517);
			parametros_b [i]=0.03499274*Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]-7.2725403*gravedad_especifica_no_caracterizables[i-lista_de_compuestos.size()]+2.232395e-4*Temperatura-0.016322572*(Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()]/gravedad_especifica_no_caracterizables[i-lista_de_compuestos.size()])+6.2256545;
		}

		for(int i=0;i<numero_de_compuestos;i++){
			Parametro_a=Parametro_a+ parametros_a[i]*Fraccion_molar_fase_liquida[i];
			Parametro_b=Parametro_b+ parametros_b[i]*Fraccion_molar_fase_liquida[i];
		}
		//System.out.println("parametro a: "+ Parametro_a);
		//System.out.println("parametro b: "+ Parametro_b);
		//Determinación del volumen molal (ft^3/lbmol*ºR).

		Double X=(double)0;
		Double delta1 =(double)0.05;
		Double X_1=(double)(X+delta1);
		Double X_2=(double)(X-delta1);
		Double Ecuacion_11= Math.pow(X,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X,2)+Parametro_a*X/Presion-Parametro_a*Parametro_b/Presion;
		Double D_11=Math.pow(X_1,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X_1,2)+Parametro_a*X_1/Presion-Parametro_a*Parametro_b/Presion;
		Double D_21=Math.pow(X,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X,2)+Parametro_a*X/Presion-Parametro_a*Parametro_b/Presion;
		Double Tolerancia1=0.00005;
		Double X_nuevo=(double)0;
		Double Derivada=(double)(D_11-D_21)/(2*delta1);

		while (Math.abs(Ecuacion_11)>Tolerancia1 || X<0 ){
			X_1=X+delta1;
			X_2=X-delta1;
			Ecuacion_11= Math.pow(X,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X,2)+Parametro_a*X/Presion-Parametro_a*Parametro_b/Presion;
			D_11=Math.pow(X_1,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X_1,2)+Parametro_a*X_1/Presion-Parametro_a*Parametro_b/Presion;
			D_21=Math.pow(X_2,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X_2,2)+Parametro_a*X_2/Presion-Parametro_a*Parametro_b/Presion;
			Derivada=(D_11-D_21)/(2*delta1);
			X_nuevo=X-Ecuacion_11/Derivada;
			if(X_nuevo<0){
				X_nuevo=Math.abs(X_nuevo);
			}
			X=X_nuevo;
		}

		Volumen_molal=X;

		Densidad_crudo=Peso_molecular_MW_crudo/Volumen_molal;
		//		System.out.println("Volumen molal="+ Volumen_molal+"  ft^3/lbmol");
		//System.out.println("Densidad real= "+Densidad_crudo+"  lb/ft^3");
		//=====================================================================================================================================

		// 6) DETERMINACIÓN DE LA GRAVEDAD ESPECIFICA DEL CRUDO 

		Double correcion_densidad_presion=(double)((0.167+(16.181)*Math.pow(10, -0.04245*Densidad_crudo))*(Presion/1000)-0.01*(0.299+263*Math.pow(10,-0.0603*Densidad_crudo))*(Math.pow((Presion/1000),2)));
		Double correcion_densidad_temperatura=(double)((0.0133+152.4*Math.pow((Densidad_crudo-correcion_densidad_presion),-2.45))*(Temperatura-520)-(8.1e-6-0.0622*Math.pow(10,-0.764*(Densidad_crudo+correcion_densidad_presion)))*Math.pow(Temperatura-520,2));
		Densidad_estandar_crudo= Densidad_crudo-correcion_densidad_presion+correcion_densidad_temperatura;
		Gravedad_especifica=Densidad_crudo/62.4;
		//System.out.println("gravedad especifica=  "+Gravedad_especifica);

		//=====================================================================================================================================

		// 7) DETERMINACIÓN GRADOS API

		Grados_API=141.5/Gravedad_especifica-131.5;

		//System.out.println("Grados API="+ Grados_API);

		//=====================================================================================================================================

		// 8) DETERMINACION DE LA SOLUBILIDAD DEL GAS EN LA FASE DEL CRUDO-Marhoun's correlation - pg.181 
		Double constante_a=(double)185.843208;
		Double constante_b=(double)1.877840;
		Double constate_c=(double)-3.1437;
		Double constate_d=(double)-1.32657;
		Double constate_e=(double)-1.398441;

		Double base=(constante_a*Math.pow(Gravedad_especifica, constante_b)*Math.pow(Gravedad_especifica,constate_c)*Math.pow(Temperatura,constate_d)*Presion);
		Solubilidad_del_gas_en_el_liquido=Math.pow(base,constate_e);

		//System.out.println("Solubilidad del liquido= "+Solubilidad_del_gas_en_el_liquido );


		//=====================================================================================================================================

		// 9) DETERMINACION OIL FORMATION VOLUMEN FACTOR

		Oil_formation_volumen_factor=(62.4*Gravedad_especifica+0.0136*Solubilidad_del_gas_en_el_liquido*Gravedad_especifica)/Densidad_crudo;

		//System.out.println("Oil volumen factor= "+ Oil_formation_volumen_factor);

		//=====================================================================================================================================

		//=====================================================================================================================================

		// 10) DETERMINACION DE LA PRESIÓN DE SATURACIÓN DELA MEZCLA- Gaso-Correlation.

		Double Parametro_A_BBP_Gaso=(double)0.816;
		Double Parametro_B_BBP_Gaso=(double)0.172;
		Double Parametro_C_BBP_Gaso=(double)-0.989;

		if (Grados_API>50 ) {
			Parametro_B_BBP_Gaso=0.130;
		}

		Presion_de_saturacion=Math.pow((Solubilidad_del_gas_en_el_liquido/Gravedad_especifica),Parametro_A_BBP_Gaso)*Math.pow(Temperatura-460,Parametro_B_BBP_Gaso)*Math.pow(Grados_API,Parametro_C_BBP_Gaso);
		Presion_de_saturacion=Math.pow(10, 1.7669+1.7447*Math.log10(Presion_de_saturacion)-0.30218*Math.pow(Math.log10(Presion_de_saturacion),2 ));
		//Correccicón en función de no hidrocarburos.
		Double Correccion_presencia_nitrogeno=1+((-2.65e-4*Grados_API+5.5e-3)*(Temperatura-460)+0.039*Grados_API-0.8295)*Fraccion_molar_fase_liquida[22]+(1.954e-11*Math.pow(Grados_API,-4.699)*(Temperatura-460)-4.699*Math.pow(Grados_API, 0.027)-2.366)*Math.pow(Fraccion_molar_fase_liquida[22],2);
		Double Correcion_presencia_dioxido=1-693.8*Fraccion_molar_fase_liquida[24]*Math.pow(Temperatura-460,-1.553);
		Double Correcion_presencia_sulfuro=1-(0.9035+0.0015*Grados_API)*Fraccion_molar_fase_liquida[23]+0.019*(45-Grados_API)*Math.pow(Fraccion_molar_fase_liquida[23],2);
		Presion_de_saturacion=Presion_de_saturacion*Correccion_presencia_nitrogeno*Correcion_presencia_dioxido*Correcion_presencia_sulfuro;
		// System.out.println("Presión de saturación"+Presion_de_saturacion);
		if (Presion_de_saturacion<14.7) {
			Presion_de_saturacion=Presion;
		}

		//=====================================================================================================================================

		// 11)  VISCOSIDAD DEL CRUDO

		// 11.1) Determinación de la viscosidad sin el uso de la composición del crudo.

		//	11.1.1)  Dead oil viscosity (Calculada a la T de operación y a presióna atmosférica)- Begg-Robinson
		Double Dead_oil_viscosity_Beggs=(double)0;
		Double Z=(double)(3.0324-0.02023*Grados_API);
		Double Y=Math.pow(10,Z);
		Double PX=(double)(Y*Math.pow(Temperatura-460,-1.163));
		Dead_oil_viscosity_Beggs=Math.pow(10,PX)-1;

		//	11.1.2) Viscosidad crudos saturados.

		//  11.1.2.1) Chew-Connally correlation

		Double Viscosidad_Chew_Connally =(double)0;
		Double Parametro_a_Chew_Connally=(double)Solubilidad_del_gas_en_el_liquido*(2.2e-7*Solubilidad_del_gas_en_el_liquido-7.4e-4);
		Double Parametro_c_Chew_Connally=(double)8.62e-5*Solubilidad_del_gas_en_el_liquido;
		Double Parametro_d_Chew_Connally=(double)1.1e-3*Solubilidad_del_gas_en_el_liquido;
		Double Parametro_e_Chew_Connally=(double)3.74e-3*Solubilidad_del_gas_en_el_liquido;
		Double Parametro_b_Chew_Connally=(double)(0.68/Math.pow(10,Parametro_c_Chew_Connally))+(0.25/Math.pow(10,Parametro_d_Chew_Connally))+(0.062/Math.pow(10,Parametro_e_Chew_Connally));
		Viscosidad_Chew_Connally=Math.pow(10,Parametro_a_Chew_Connally)*Math.pow(Dead_oil_viscosity_Beggs,Parametro_b_Chew_Connally);

		//  11.2.1) Beggs_Robinson correlation

		Double Viscosidad_Beggs_Robinson =(double)0;
		Double Parametro_a_Beggs_Robinson=(double)11.715*Math.pow(Solubilidad_del_gas_en_el_liquido+100,-0.515);
		Double Parametro_b_Beggs_Robinson=(double)5.44*Math.pow(Solubilidad_del_gas_en_el_liquido+150,-0.338);
		Viscosidad_Beggs_Robinson=Parametro_a_Beggs_Robinson*Math.pow(Dead_oil_viscosity_Beggs,Parametro_b_Beggs_Robinson);

		// 11.1.3) Viscosidad crudos no saturados. 


		// 11.1.3) Beal's correlation

		Double Viscosidad_Beal =(double)0;
		Viscosidad_Beal=Dead_oil_viscosity_Beggs+0.001*(Presion-Presion_de_saturacion)*(0.024*Math.pow(Viscosidad_Chew_Connally,1.6)+0.038*Math.pow(Viscosidad_Chew_Connally,0.56));

		//11.1.3) Viscosidad de crudos no saturados.
		Double Viscosidad_Vasquez_Beggs=(double)0;
		Double parametro_a_Vasquez_Beggs=(double)-3.9e-5*Presion-5;
		Double parametro_m_Vasquez_Beggs=2.6*Math.pow(Presion,1.187)*Math.pow(10,parametro_a_Vasquez_Beggs);
		Viscosidad_Vasquez_Beggs=Viscosidad_Chew_Connally*Math.pow(Presion/(Presion_de_saturacion),parametro_m_Vasquez_Beggs);

		// 11.1.3.1) Determinación de la viscosidad de los componentes individuales.

		double [] Temperatura_reducida_por_componente= new double [numero_de_compuestos];
		double [] Viscosidad_por_componente= new double [numero_de_compuestos];
		double [] vector_parametros_ei= new double [numero_de_compuestos];
		double vector_parametro_em=0;
		Double densidad_reducida_del_crudo=(double)0;
		Double viscosidad_ol=(double)0;
		for(int i=0;i<numero_de_compuestos;i++){
			Temperatura_reducida_por_componente [i]=Temperatura/Temperatura_critica_componetes_liquido[i];
			vector_parametros_ei[i]=(5.4402*Math.pow(Temperatura_critica_componetes_liquido [i], 0.16666666666667))/(Math.pow(Peso_molecular_liquido[i],0.5)*Math.pow(Presion_critica_componetes_liquido [i], 0.666667));
		}
		for(int i=0;i<numero_de_compuestos;i++){
			if (Temperatura_reducida_por_componente[i]<1.5){ 
				Viscosidad_por_componente [i]=34e-5*Math.pow(Temperatura_reducida_por_componente[i],0.94)/vector_parametros_ei[i];
			}
			else{
				Viscosidad_por_componente [i]=(17.78e-5*Math.pow((4.58*Temperatura_reducida_por_componente[i]-1.67), 0.625))/vector_parametros_ei[i];
			}
		}
		Double numerador_fraccion=(double)0;
		Double denominador_fraccion=(double)0;

		for(int i=0;i<numero_de_compuestos;i++){
			numerador_fraccion=numerador_fraccion+Fraccion_molar_fase_liquida[i]*Viscosidad_por_componente[i]*Math.pow(Peso_molecular_liquido[i],0.5);
			denominador_fraccion=denominador_fraccion+Fraccion_molar_fase_liquida[i]*Math.pow(Peso_molecular_liquido[i],0.5);
			//System.out.println("numerador ="+  numerador_fraccion +"denoinador" + denominador_fraccion);
		}

		viscosidad_ol=numerador_fraccion/denominador_fraccion;
		//System.out.println("viscosidad_ol:  "+viscosidad_ol);
		Double sumatoria=(double)0;
		//Calculo de la densidad reducida del crudo.
		for (int i =lista_de_compuestos.size();i<numero_de_compuestos;i++){
			sumatoria=sumatoria+Fraccion_molar_fase_liquida[i]*Volumen_critico_liquido[i];
		}
		for (int i =0;i<lista_de_compuestos.size();i++){
			sumatoria=sumatoria+Fraccion_molar_fase_liquida[i]*lista_de_compuestos.get(i).getVolumen_critico_Vc()*lista_de_compuestos.get(i).getMasa_molecular();
		}

		densidad_reducida_del_crudo=(1/Peso_molecular_MW_crudo)*Densidad_crudo*sumatoria;

		//System.out.println("Densidad reducida del crudo"+densidad_reducida_del_crudo);

		vector_parametro_em=5.4402*Math.pow(Temperatura_pseudocritica_Tc, 0.16666667)/(Math.pow(Peso_molecular_MW_crudo, 0.5)*Math.pow(Presion_pseudocritica_Pc,0.666667));
		//System.out.println("ei= "+ vector_parametro_em);
		viscosidad=viscosidad_ol+ (1/vector_parametro_em)*(Math.pow(0.1023+0.023364*densidad_reducida_del_crudo+0.058533*Math.pow(densidad_reducida_del_crudo, 2)-0.0407558*Math.pow(densidad_reducida_del_crudo, 3)+0.0093724*Math.pow(densidad_reducida_del_crudo, 4),4)-0.0001);
		//System.out.println(viscosidad_ol);
		//System.out.println("Viscosidad del crudo final:"+viscosidad);

		//VISCOSIDAD PARA COMPOSICIÓN CONOCIDA - Segunda expresión para el calculo de la viscosidad

		//Determinación de las propiedades promedio de la fracción no caracterizable 
		Double masa_molecular_promedio_no_caracterizables=(double)0;
		Double gravedad_especifica_promedio_no_caracterizables=(double)0;
		Double suma_interna_masa_molecular=(double)0;
		Double suma_interna_gravedad_especifica=(double)0;
		int cont=0;
		for (int i =lista_de_compuestos.size();i<numero_de_compuestos;i++){
			cont++;
			suma_interna_masa_molecular=suma_interna_masa_molecular+ Peso_molecular_MW_no_caracterizables[i-lista_de_compuestos.size()];
			suma_interna_gravedad_especifica=suma_interna_gravedad_especifica+gravedad_especifica_no_caracterizables[i-lista_de_compuestos.size()];
		}

		masa_molecular_promedio_no_caracterizables=suma_interna_masa_molecular/cont;
		gravedad_especifica_promedio_no_caracterizables=suma_interna_gravedad_especifica/cont;

		//System.out.println(compuestos_no_caracterizables);
		//System.out.println(cont);
		//System.out.println("masa promedio=  "+masa_molecular_promedio_no_caracterizables+ "gravedad= "+gravedad_especifica_promedio_no_caracterizables);
		Double P_A=(double)21.918581-16815.621/Temperatura+0.023315983*masa_molecular_promedio_no_caracterizables-0.019216951*(masa_molecular_promedio_no_caracterizables/gravedad_especifica_promedio_no_caracterizables)+479.783669*(Densidad_crudo/Temperatura)-719.808848*Math.pow(Densidad_crudo/Temperatura, 2)-0.096858449*Peso_molecular_MW_crudo+0.54324554e-6*Math.pow(Peso_molecular_MW_crudo, 3)+0.0021040196*Peso_molecular_MW_crudo/Densidad_crudo-0.4332274341e-11*Math.pow(Peso_molecular_MW_crudo*Densidad_crudo,3)-0.0081362043*Math.pow(Densidad_crudo,2);
		Double P_B=(double)-2.6941621+3757.4919/Temperatura-0.31409829e12/Math.pow(Temperatura, 4)-33.744827*Math.pow(gravedad_especifica_promedio_no_caracterizables, 3)+31.333913*Math.pow(gravedad_especifica_promedio_no_caracterizables, 4)+0.24400196e-10*Math.pow(masa_molecular_promedio_no_caracterizables/gravedad_especifica_promedio_no_caracterizables,4)+4.632634e4*Math.pow(Densidad_crudo/Temperatura, 4)-0.037022195*Peso_molecular_MW_crudo+0.0011348044*(Peso_molecular_MW_crudo*Densidad_crudo)-0.0547665355e-15*Math.pow(Peso_molecular_MW_crudo*Densidad_crudo, 4)+0.0893548761e-3*Math.pow(Densidad_crudo, 3)-2.05018084e-6*Math.pow(Densidad_crudo,4);

		//		System.out.println(P_A);
		//		System.out.println(P_B);
		Double am=(double)(Math.exp(P_A));
		Double bm=(double)(Math.exp(P_B));



		Ecuacion_11= Math.pow(X, 3)-(bm +Presion/Temperatura)*Math.pow(X, 2)+(am/Temperatura)*X-(am*bm/Temperatura);
		D_11= Math.pow(X_1, 3)-(bm +Presion/Temperatura)*Math.pow(X_1, 2)+(am/Temperatura)*X_1-(am*bm/Temperatura);
		D_21= Math.pow(X_2, 3)-(bm +Presion/Temperatura)*Math.pow(X_2, 2)+(am/Temperatura)*X_2-(am*bm/Temperatura);
		X_nuevo=(double)0;
		Derivada=(double)(D_11-D_21)/(2*delta1);

		while (Math.abs(Ecuacion_11)>Tolerancia1 || X<0 ){
			X_1=X+delta1;
			X_2=X-delta1;
			Ecuacion_11= Math.pow(X,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X,2)+Parametro_a*X/Presion-Parametro_a*Parametro_b/Presion;
			D_11=Math.pow(X_1,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X_1,2)+Parametro_a*X_1/Presion-Parametro_a*Parametro_b/Presion;
			D_21=Math.pow(X_2,3)-(Constante_gas_ideal_R*Temperatura/Presion+Parametro_b)*Math.pow(X_2,2)+Parametro_a*X_2/Presion-Parametro_a*Parametro_b/Presion;
			Derivada=(D_11-D_21)/(2*delta1);
			X_nuevo=X-Ecuacion_11/Derivada;
			if(X_nuevo<0){
				X_nuevo=Math.abs(X_nuevo);
			}
			X=X_nuevo;

		}


		viscosidad=X_nuevo;

		System.out.println("Dead oil viscosity-Beggs=	"+Dead_oil_viscosity_Beggs);
		System.out.println("viscosidad Chew-Connally=	"+ Viscosidad_Chew_Connally);
		System.out.println("viscosidad Beggs_Robinson=	"+ Viscosidad_Beggs_Robinson);
		System.out.println("viscosidad Beal=	"+ Viscosidad_Beal);
		System.out.println("Viscosidad_Vasquez_Beggs=	"+Viscosidad_Vasquez_Beggs);
		System.out.println("Viscosidad a partir de la composición=	"+viscosidad);


	}

}
