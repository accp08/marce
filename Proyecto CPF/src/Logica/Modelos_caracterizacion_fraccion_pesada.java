package Logica;

//Calculo por Riazi-Daubert modificación 1

import java.util.LinkedList;

import javax.swing.JOptionPane;


public class Modelos_caracterizacion_fraccion_pesada {

	public Modelos_caracterizacion_fraccion_pesada(){

	}

	public static LinkedList<Objeto_fracciones_no_caracterizables> RiaziDaubert1(
			LinkedList<Objeto_fracciones_no_caracterizables> lista_fracciones_no_caracterizables) {


		for (int i = 0; i < lista_fracciones_no_caracterizables.size(); i++) {
			double Peso_Molecular = (double)0;
			double Temperatura_media_de_ebullicion= lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar();
			double gravedad_especifica =lista_fracciones_no_caracterizables.get(i).getGravedad_especifica();

			if (lista_fracciones_no_caracterizables.get(i).getMasa_molecular()!=null){ 
				Peso_Molecular =lista_fracciones_no_caracterizables.get(i).getMasa_molecular();
			}
			//Propiedades para la caracterización del corte
			double Tcritica_Tc = (double)0;
			double Pcritica_Pc = (double)0;
			double Vcritico_Vc = (double)0;
			double factor_acentrico = (double)0;
			double Peso_molecular = (double)0;
			double Zcritico_Zc = (double)0;

			//Constante para los gases ideales (en unidades inglesas).
			double Constante_R = (double)10.73;

			//Conversion a temperatura absoluta
			double Temperatura = (double)(Temperatura_media_de_ebullicion);




			//Variables de la ecuación de Riazi_Daubert
			Double Riazi_a = (double)0;
			Double Riazi_b = (double)0;
			Double Riazi_c = (double)0;
			Double Riazi_d = (double)0;
			Double Riazi_e = (double)0;
			Double Riazi_f = (double)0;
			Double Riazi_Teta = (double)0;

			//Ecuación Riazi-Daubert
			// Riazi_Teta=(double)(Riazi_a*Math.pow(Temperatura, Riazi_b)*Math.pow(gravedad_especifica, Riazi_c)*Math.exp((Riazi_d*Temperatura+Riazi_e*gravedad_especifica+Riazi_f*Temperatura*gravedad_especifica)));

			//Variables a calcular.
			double Tcritica_Tc_R = (double)0;
			double Pcritica_Pc_R = (double)0;
			double Vcritico_Vc_R = (double)0;
			double Factor_acentrico_RE = (double)0;
			double Peso_molecular_R = (double)0;
			double Zcritico_Zc_R = (double)0;

			//Calculo Peso Molecular
			Riazi_a = (double)581.96;
			Riazi_b = (double)0.97476;
			Riazi_c = (double)6.51274;
			Riazi_d = (double)5.43076e-4;
			Riazi_e = (double)-9.53384;
			Riazi_f = (double)1.11056e-3;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow(Temperatura, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Temperatura+Riazi_e*gravedad_especifica+Riazi_f*Temperatura*gravedad_especifica)));
			Peso_molecular_R=Riazi_Teta;
			if(Peso_Molecular!=0){
				Peso_molecular_R=Peso_Molecular;
			}


			//Calculo Tempertura Crítica
			Riazi_a = (double)10.6443;
			Riazi_b = (double)0.81067;
			Riazi_c = (double)0.53691;
			Riazi_d = (double)-5.1747e-4;
			Riazi_e = (double)-0.54444;
			Riazi_f = (double)3.5995e-4;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow(Temperatura, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Temperatura+Riazi_e*gravedad_especifica+Riazi_f*Temperatura*gravedad_especifica)));
			Tcritica_Tc_R=Riazi_Teta;

			//Calculo Presión Crítica
			Riazi_a = (double)6.162e6;
			Riazi_b = (double)-0.4844;
			Riazi_c = (double)4.0846;
			Riazi_d = (double)-4.725e-3;
			Riazi_e = (double)-4.8014;
			Riazi_f = (double)3.1939e-3;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow(Temperatura, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Temperatura+Riazi_e*gravedad_especifica+Riazi_f*Temperatura*gravedad_especifica)));
			Pcritica_Pc_R=Riazi_Teta;

			//Volumen Crítico
			Riazi_a = (double)6.233e-4;
			Riazi_b = (double)0.7506;
			Riazi_c = (double)-1.2028;
			Riazi_d = (double)-1.4679e-3;
			Riazi_e = (double)-0.26404;
			Riazi_f = (double)1.095e-3;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow(Temperatura, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Temperatura+Riazi_e*gravedad_especifica+Riazi_f*Temperatura*gravedad_especifica)));
			Vcritico_Vc_R=Riazi_Teta;

			//Calculo Zc
			Zcritico_Zc_R=Pcritica_Pc_R*Vcritico_Vc_R*Peso_molecular_R/(Constante_R*Tcritica_Tc_R);

			//Factor acéntrico-Calculado por Edmister con las propiedades calculadas por Riazi_Daubert.
			Factor_acentrico_RE=(double)(3*Math.log10(Pcritica_Pc_R/14.7))/(7*((Tcritica_Tc_R/Temperatura)-1))-1;


			Tcritica_Tc =Tcritica_Tc_R;
			Pcritica_Pc =Pcritica_Pc_R;
			Vcritico_Vc = Vcritico_Vc_R;
			factor_acentrico = Factor_acentrico_RE;
			Peso_molecular =Peso_molecular_R;
			Zcritico_Zc =Zcritico_Zc_R;


			lista_fracciones_no_caracterizables.get(i).setPunto_de_ebullicion_estandar(lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar());
			lista_fracciones_no_caracterizables.get(i).setMasa_molecular(Peso_molecular);
			lista_fracciones_no_caracterizables.get(i).setTemperatura_critica_Tc(Tcritica_Tc);
			lista_fracciones_no_caracterizables.get(i).setPresión_critica_Pc(Pcritica_Pc);
			lista_fracciones_no_caracterizables.get(i).setVolumen_critico_Vc(Vcritico_Vc);
			lista_fracciones_no_caracterizables.get(i).setFactor_acentrico_w(factor_acentrico);



		}

		return lista_fracciones_no_caracterizables;
	}

	public LinkedList<Objeto_fracciones_no_caracterizables> RiaziDaubert2(
			LinkedList<Objeto_fracciones_no_caracterizables> lista_fracciones_no_caracterizables) {
		for (int i = 0; i < lista_fracciones_no_caracterizables.size(); i++) {
			double Peso_Molecular = lista_fracciones_no_caracterizables.get(i).getMasa_molecular();
			double Temperatura_media_de_ebullicion= 0;
			double gravedad_especifica =lista_fracciones_no_caracterizables.get(i).getGravedad_especifica();

			if (lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar()!=null){ 
				Temperatura_media_de_ebullicion =lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar();
			}


			//Propiedades para la caracterización del corte
			double Tcritica_Tc = (double)0;
			double Pcritica_Pc = (double)0;
			double Vcritico_Vc = (double)0;
			double factor_acentrico = (double)0;
			double Peso_molecular = (double)0;
			double Zcritico_Zc = (double)0;

			//Constante para los gases ideales (en unidades inglesas).
			double Constante_R = (double)10.73;

			//Conversion a temperatura absoluta
			double Temperatura = (double)(Temperatura_media_de_ebullicion);


			Peso_molecular =Peso_Molecular;

			//Variables de la ecuación de Riazi_Daubert
			double Riazi_a = (double)0;
			double Riazi_b = (double)0;
			double Riazi_c = (double)0;
			double Riazi_d = (double)0;
			double Riazi_e = (double)0;
			double Riazi_f = (double)0;
			double Riazi_Teta = (double)0;

			//Ecuación Riazi-Daubert
			// Riazi_Teta=(double)(Riazi_a*Math.pow(Peso_Molecular, Riazi_b)*Math.pow(gravedad_especifica, Riazi_c)*Math.exp((Riazi_d*Peso_Molecular+Riazi_e*gravedad_especifica+Riazi_f*Peso_Molecular*gravedad_especifica)));

			//Variables a calcular.
			double Tcritica_Tc_R2 = (double)0;
			double Pcritica_Pc_R2 = (double)0;
			double Vcritico_Vc_R2 = (double)0;
			double Factor_acentrico_RE2 = (double)0;
			double Temperatura_de_ebullición_R2 = (double)0;
			double Zcritico_Zc_R2 = (double)0;

			//Calculo Temperatura de Ebullición (ºR)
			Riazi_a = (double)6.77857;
			Riazi_b = (double)0.401673;
			Riazi_c = (double)-1.58262;
			Riazi_d = (double)3.77409e-3;
			Riazi_e = (double)2.984036;
			Riazi_f = (double)-4.25288e-3;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow( Peso_Molecular, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Peso_Molecular+Riazi_e*gravedad_especifica+Riazi_f*Peso_Molecular*gravedad_especifica)));
			Temperatura_de_ebullición_R2=Riazi_Teta;
			if (Temperatura_media_de_ebullicion==0){
				Temperatura =Temperatura_de_ebullición_R2;
			}

			//Calculo Tempertura Crítica
			Riazi_a = (double)554.4;
			Riazi_b = (double)0.2998;
			Riazi_c = (double)1.0555;
			Riazi_d = (double)-1.3478e-4;
			Riazi_e = (double)-0.61641;
			Riazi_f = (double) 0;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow( Peso_Molecular, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Peso_Molecular+Riazi_e*gravedad_especifica+Riazi_f*Peso_Molecular*gravedad_especifica)));
			Tcritica_Tc_R2=Riazi_Teta;

			//Calculo Presión Crítica
			Riazi_a = (double)4.5203e4;
			Riazi_b = (double)-0.8063;
			Riazi_c = (double)1.6015;
			Riazi_d = (double)-1.8078e-3;
			Riazi_e = (double)-0.3084;
			Riazi_f = (double)0;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow( Peso_Molecular, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Peso_Molecular+Riazi_e*gravedad_especifica+Riazi_f*Peso_Molecular*gravedad_especifica)));
			Pcritica_Pc_R2=Riazi_Teta;

			//Volumen Crítico
			Riazi_a = (double)1.206e-2;
			Riazi_b = (double)0.20378;
			Riazi_c = (double)-1.3036;
			Riazi_d = (double)-2.657e-3;
			Riazi_e = (double)0.5287;
			Riazi_f = (double)2.6012e-3;
			Riazi_Teta=(double)(Riazi_a*(double)Math.pow( Peso_Molecular, Riazi_b)*(double)Math.pow(gravedad_especifica, Riazi_c)*(double)Math.exp((Riazi_d*Peso_Molecular+Riazi_e*gravedad_especifica+Riazi_f*Peso_Molecular*gravedad_especifica)));
			Vcritico_Vc_R2=Riazi_Teta;

			//Calculo Zc
			Zcritico_Zc_R2=Pcritica_Pc_R2*Vcritico_Vc_R2*Peso_Molecular/(Constante_R*Tcritica_Tc_R2);

			//Factor acéntrico-Calculado por Edmister con las propiedades calculadas por Riazi_Daubert.
			Factor_acentrico_RE2=(double)(3*Math.log10(Pcritica_Pc_R2/14.7))/(7*((Tcritica_Tc_R2/Temperatura)-1))-1;

			Tcritica_Tc =Tcritica_Tc_R2;
			Pcritica_Pc =Pcritica_Pc_R2;
			Vcritico_Vc = Vcritico_Vc_R2;
			factor_acentrico = Factor_acentrico_RE2;
			Zcritico_Zc =Zcritico_Zc_R2;



			lista_fracciones_no_caracterizables.get(i).setMasa_molecular(Peso_molecular);
			lista_fracciones_no_caracterizables.get(i).setPunto_de_ebullicion_estandar(Temperatura);
			lista_fracciones_no_caracterizables.get(i).setTemperatura_critica_Tc(Tcritica_Tc);
			lista_fracciones_no_caracterizables.get(i).setPresión_critica_Pc(Pcritica_Pc);
			lista_fracciones_no_caracterizables.get(i).setVolumen_critico_Vc(Vcritico_Vc);
			lista_fracciones_no_caracterizables.get(i).setFactor_acentrico_w(factor_acentrico);



		}

		return lista_fracciones_no_caracterizables;
	}

	public LinkedList<Objeto_fracciones_no_caracterizables> LeeKesler(
			LinkedList<Objeto_fracciones_no_caracterizables> lista_fracciones_no_caracterizables) {
		for (int i = 0; i < lista_fracciones_no_caracterizables.size(); i++) {
			double Peso_Molecular = (double)0;
			double Temperatura_media_de_ebullicion= lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar();
			double gravedad_especifica =lista_fracciones_no_caracterizables.get(i).getGravedad_especifica();

			if (lista_fracciones_no_caracterizables.get(i).getMasa_molecular()!=null){ 
				Peso_Molecular =lista_fracciones_no_caracterizables.get(i).getMasa_molecular();
			}
			//Propiedades para la caracterización del corte
			double Tcritica_Tc = (double)0;
			double Pcritica_Pc = (double)0;
			double Vcritico_Vc = (double)0;
			double factor_acentrico = (double)0;
			double Peso_molecular = (double)0;
			double Zcritico_Zc = (double)0;

			//Constante para los gases ideales (en unidades inglesas).
			double Constante_R = (double)10.73;

			//Conversion a temperatura absoluta
			double Temperatura = (double)(Temperatura_media_de_ebullicion);

			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



			//Calculo por Lee-Kesler

			//Variables Propias de la ecuación de Lee-Kesler
			double Theta = (double)0;
			double Factor_caracterizacion_Watson = (double)0;

			//Variables a calcular.
			double Tcritica_Tc_L = (double)0;
			double Pcritica_Pc_L = (double)0;
			double Vcritico_Vc_L = (double)0;
			double Factor_acentrico_L = (double)0;
			double Peso_molecular_L = (double)0;
			double Zcritico_Zc_L = (double)0;

			//Calculo presión crítica
			double a = (double)(8.3634-0.0566/gravedad_especifica);
			double b = (double)((0.24244+2.2898/gravedad_especifica +0.11857/Math.pow(gravedad_especifica, 2))*Math.pow(10,-3)*Temperatura);
			double c = (double)((1.4685+3.648/gravedad_especifica+0.47227/Math.pow(gravedad_especifica,2))*Math.pow(10, -7)*Math.pow(Temperatura, 2));
			double d = (double)((0.42019+1.6977/Math.pow(gravedad_especifica, 2))*Math.pow(10,-10)*Math.pow(Temperatura, 3));
			Pcritica_Pc_L=(double)Math.exp(a-b+c-d);

			//Calculo temperatura crítica
			a = (double)(341.7+811.1*gravedad_especifica);
			b = (double)((0.4244+0.1174*gravedad_especifica)*Temperatura);
			c = (double)((0.4669-3.26238*gravedad_especifica)*Math.pow(10, 5)/Temperatura);
			Tcritica_Tc_L=(double)(a+b+c);

			//Calculo Peso Molecular
			a = (double)(-12272.6+9486.4*gravedad_especifica);
			b = (double)((4.6523-3.3287*gravedad_especifica)*Temperatura);
			c = (double)((1-0.77084*gravedad_especifica-0.02058*Math.pow(gravedad_especifica, 2))*(1.3437-720.79/Temperatura)*Math.pow(10,7)/Temperatura);
			d = (double)((1-0.80882*gravedad_especifica+0.02226*Math.pow(gravedad_especifica, 2))*(1.8828-181.98/Temperatura)*Math.pow(10,12)/Math.pow(Temperatura,3));
			Peso_molecular_L=(double)(a+b+c+d);
			if(Peso_Molecular!=0){
				Peso_molecular_L=Peso_Molecular;
			}


			//Calculo del factor acéntrico
			Factor_caracterizacion_Watson=(double)(Math.pow(Temperatura,0.33333)/gravedad_especifica);
			Theta=Temperatura/Tcritica_Tc_L;
			if(Theta<0.8){
				a=(double)(-Math.log(Pcritica_Pc_L/14.696)-5.92714+6.09648/Theta+1.28862*Math.log(Theta)-0.169347*Math.pow(Theta,6));
				b=(double)(+15.2518-15.6875/Theta-13.4721*Math.log(Theta)+0.43577*Math.pow(Theta, 6));
				Factor_acentrico_L = (double)(a/b);
			}
			else{
				Factor_acentrico_L = (double)(-7.904+0.1352*Factor_caracterizacion_Watson-0.007465*Math.pow(Factor_caracterizacion_Watson, 2)+8.359*Theta+(1.408-0.01063*Factor_caracterizacion_Watson)/Theta);
			}

			//Calculo del factor de compresibilidad-Método de Salerno et. al (año-1985). 
			Zcritico_Zc_L =(double) (0.2918-0.0928*Factor_acentrico_L);

			//Calculo del Volumen crítico.
			Vcritico_Vc_L = (Zcritico_Zc_L*Constante_R*Tcritica_Tc_L)/(Pcritica_Pc_L*Peso_molecular_L);		

			Tcritica_Tc =Tcritica_Tc_L;
			Pcritica_Pc =Pcritica_Pc_L;
			Vcritico_Vc = Vcritico_Vc_L;
			factor_acentrico = Factor_acentrico_L;
			Zcritico_Zc =Zcritico_Zc_L;


			lista_fracciones_no_caracterizables.get(i).setPunto_de_ebullicion_estandar(lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar());
			lista_fracciones_no_caracterizables.get(i).setMasa_molecular(Peso_molecular_L);
			lista_fracciones_no_caracterizables.get(i).setTemperatura_critica_Tc(Tcritica_Tc);
			lista_fracciones_no_caracterizables.get(i).setPresión_critica_Pc(Pcritica_Pc);
			lista_fracciones_no_caracterizables.get(i).setVolumen_critico_Vc(Vcritico_Vc);
			lista_fracciones_no_caracterizables.get(i).setFactor_acentrico_w(factor_acentrico);


		}
		return lista_fracciones_no_caracterizables;
	}

	public LinkedList<Objeto_fracciones_no_caracterizables> WinSimDaubert(
			LinkedList<Objeto_fracciones_no_caracterizables> lista_fracciones_no_caracterizables) {
		for (int i = 0; i < lista_fracciones_no_caracterizables.size(); i++) {
			double Peso_Molecular = (double)0;
			double Temperatura_media_de_ebullicion= lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar();
			double gravedad_especifica =lista_fracciones_no_caracterizables.get(i).getGravedad_especifica();

			if (lista_fracciones_no_caracterizables.get(i).getMasa_molecular()!=null){ 
				Peso_Molecular =lista_fracciones_no_caracterizables.get(i).getMasa_molecular();
			}	
			//Propiedades para la caracterización del corte
			double Tcritica_Tc = (double)0;
			double Pcritica_Pc = (double)0;
			double Vcritico_Vc = (double)0;
			double factor_acentrico = (double)0;
			double Peso_molecular = (double)0;
			double Zcritico_Zc = (double)0;

			//Constante para los gases ideales (en unidades inglesas).
			double Constante_R = (double)10.73;

			//Conversion a temperatura absoluta
			double Temperatura = (double)(Temperatura_media_de_ebullicion);

			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


			//Calculo temperatura crítica
			Double Pcritica_Pc_WSD =(double)(3.48242e9*Math.pow(Temperatura, -2.3177)*Math.pow(gravedad_especifica, 2.4853));

			//Calculo Presión Crítica
			Double Tcritica_Tc_WSD =(double)(Math.exp(3.9934718*Math.pow(Temperatura, 0.08615)*Math.pow(gravedad_especifica, 0.04614)));

			//Calculo peso molecular
			Double Peso_molecular_WSD=(double)(1.4350476e-5*Math.pow(Temperatura, 2.3776)*Math.pow(gravedad_especifica,-0.9371));
			if(Peso_Molecular!=0){
				Peso_molecular_WSD=Peso_Molecular;
			}

			//Factor acéntrico-Calculado por Edmister con las propiedades calculadas por Riazi_Daubert.
			double Factor_acentrico_WSD=(double)(3*Math.log10(Pcritica_Pc_WSD/14.7))/(7*((Tcritica_Tc_WSD/Temperatura)-1))-1;

			// Factor de compresibilidad crítico
			//Calculo del factor de compresibilidad-Método de Salerno et. al (año-1985). 
			double Zcritico_Zc_WSD1=(double)(0.291-0.08*Factor_acentrico_WSD-0.016*Math.pow(Factor_acentrico_WSD, 2));
			//Calculo del factor de compresibilidad-Método de Nath (año-1985). 
			double Zcritico_Zc_WSD =(double) (0.2918-0.0928*Factor_acentrico_WSD);

			//Calculo del Volumen crítico.
			double Vcritico_Vc_WSD = (Zcritico_Zc_WSD*Constante_R*Tcritica_Tc_WSD)/(Pcritica_Pc_WSD*Peso_molecular_WSD);		

			Tcritica_Tc =Tcritica_Tc_WSD;
			Pcritica_Pc =Pcritica_Pc_WSD;
			Vcritico_Vc = Vcritico_Vc_WSD;
			factor_acentrico = Factor_acentrico_WSD;
			Zcritico_Zc =Zcritico_Zc_WSD1;


			lista_fracciones_no_caracterizables.get(i).setPunto_de_ebullicion_estandar(lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar());
			lista_fracciones_no_caracterizables.get(i).setMasa_molecular(Peso_molecular_WSD);
			lista_fracciones_no_caracterizables.get(i).setTemperatura_critica_Tc(Tcritica_Tc);
			lista_fracciones_no_caracterizables.get(i).setPresión_critica_Pc(Pcritica_Pc);
			lista_fracciones_no_caracterizables.get(i).setVolumen_critico_Vc(Vcritico_Vc);
			lista_fracciones_no_caracterizables.get(i).setFactor_acentrico_w(factor_acentrico);


		}
		return lista_fracciones_no_caracterizables;
	}



	public LinkedList<Objeto_fracciones_no_caracterizables> WatansiriOwensStarling(
			LinkedList<Objeto_fracciones_no_caracterizables> lista_fracciones_no_caracterizables) {
		for (int i = 0; i < lista_fracciones_no_caracterizables.size(); i++) {
			double Peso_Molecular = lista_fracciones_no_caracterizables.get(i).getMasa_molecular();
			double Temperatura_media_de_ebullicion= lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar();
			double gravedad_especifica =lista_fracciones_no_caracterizables.get(i).getGravedad_especifica();

			//Propiedades para la caracterización del corte
			double Tcritica_Tc = (double)0;
			double Pcritica_Pc = (double)0;
			double Vcritico_Vc = (double)0;
			double factor_acentrico = (double)0;
			double Peso_molecular = (double)0;
			double Zcritico_Zc = (double)0;

			//Constante para los gases ideales (en unidades inglesas).
			double Constante_R = (double)10.73;

			//Conversion a temperatura absoluta
			double Temperatura = (double)(Temperatura_media_de_ebullicion);


			//Calculo temperatura crítica
			double a = (double)(-0.0650504-0.0005217*Temperatura+0.03095*Math.log(Peso_Molecular)+1.11067*Math.log(Temperatura));
			double b = (double)(Peso_Molecular*(0.078154*Math.pow(gravedad_especifica, 0.5)-0.061061*Math.pow(gravedad_especifica, 0.33333)-0.016943*gravedad_especifica));
			double Tcritica_Tc_W = (double)Math.exp(a+b);

			//Calculo del volumen crítico
			a=(double)(76.313887-129.8038*gravedad_especifica+63.1750*Math.pow(gravedad_especifica, 2)-13.175*Math.pow(gravedad_especifica, 3));
			b=(double)(+1.10108*Math.log(Peso_Molecular)+42.1958*Math.log(gravedad_especifica));
			double Vcritico_Vc_W= (double)Math.exp(a+b);
			double Vcritico_Vc_W1 = (double)(Vcritico_Vc_W/Peso_Molecular);

			//Calculo presión crítica
			a =(double)(Tcritica_Tc_W/Vcritico_Vc_W);
			b=(double)(Peso_Molecular/Tcritica_Tc_W);
			double c=(double)(Temperatura/Peso_Molecular);
			double Pcritica_Pc_W= (double)Math.exp(6.6418853+0.01617283*Math.pow(a, 0.8)-8.712*b-0.08843889*c);

			//Calculo del factor acéntrico
			double p =(double)(Temperatura/Peso_Molecular);
			double q =(double)(Temperatura/gravedad_especifica);
			b= (double)((5.12316667e-4*Temperatura)+(0.281826667*p)+(382.904/Peso_Molecular)+(0.074691e-5*(Math.pow(q,2)))-(0.12027778e-4*Temperatura*Peso_Molecular)+(0.001261*gravedad_especifica*Peso_Molecular)+(0.1265e-4*Math.pow(Peso_Molecular,2))+(0.2016e-4*gravedad_especifica*Math.pow(Peso_Molecular, 2))-((66.29959*Math.pow(Temperatura, 0.3333))/Peso_Molecular)-((0.00255452*Math.pow(Temperatura,0.66666))/Math.pow(gravedad_especifica,2)));
			c = (double)(5*Temperatura/(9*Peso_Molecular));
			double Factor_acentrico_W= (double)(b*c);

			//Compresibilidad crítica 
			double Zcritico_Zc_W=(double)(Pcritica_Pc_W*Vcritico_Vc_W/(Constante_R*Tcritica_Tc_W));


			Tcritica_Tc =Tcritica_Tc_W;
			Pcritica_Pc =Pcritica_Pc_W;
			Vcritico_Vc = Vcritico_Vc_W1;
			factor_acentrico = Factor_acentrico_W;
			Zcritico_Zc =Zcritico_Zc_W;



			lista_fracciones_no_caracterizables.get(i).setPunto_de_ebullicion_estandar(lista_fracciones_no_caracterizables.get(i).getPunto_de_ebullicion_estandar());
			lista_fracciones_no_caracterizables.get(i).setMasa_molecular(Peso_Molecular);
			lista_fracciones_no_caracterizables.get(i).setTemperatura_critica_Tc(Tcritica_Tc);
			lista_fracciones_no_caracterizables.get(i).setPresión_critica_Pc(Pcritica_Pc);
			lista_fracciones_no_caracterizables.get(i).setVolumen_critico_Vc(Vcritico_Vc);
			lista_fracciones_no_caracterizables.get(i).setFactor_acentrico_w(factor_acentrico);




		}

		return lista_fracciones_no_caracterizables;

	}
}
