package Logica;

import javax.swing.JOptionPane;

public class Dimensionamiento_separador_bifasico_vertical {

	public static void main(String[] args) {

		//Variables de ingreso al sistema:

		//Para el gas:
		//Flujo del gas= se obtiene del equilibrio.
		// Factor de compresibilidad = es la Z que da el equilibrio
		//Constante de los gases ideales= se tiene.
		// Gravedad especifica del gas = calculada para el corte en función de las propiedades calculadas en la caracterización del crudo.
		// Densidad del gas = se determina en la caracterización de las propiedades.

		//Para el liquido:

		//flujo_del_crudo = Dado por el equilibrio
		// Grados_API o densidad del liquido = LA DA EL USUARIO O SE CALCULA CON EL EQUILIBRIO
		// diametro_de_la_particula =DADA POR EL USUARIO;
		// densidad_del_crudo = (float) 0;

		//Dimensiones del equipo y condiciones de separación
		//La presión de operación del primer separador, 
		//temperatura  de separación
		//Tamaño de gota a separar


		//Datos que se deben preguntar al usuario
		double Tamaño_de_gota_a_separar=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota a separar en micrones")));

		//Todas estas variables son provenientes de calculos anteriores.
		//Propiedades del gas
		Float flujo_del_gas = (float) 10;
		Float factor_de_compresibilidad = (float) 0.84;	
		Float constante_gases_ideales = (float) 10.73;
		Float peso_molecular_del_gas= (float) 17.4;
		Float gravedad_especifica_del_gas=(float)0.6;
		Float densidad_del_gas = (float) 0;
		Float viscosidad_dinamica_del_gas = (float)0;

		//Propiedades del crudo	
		Float flujo_del_crudo = (float) 2000;		
		Float grados_API = (float) 40;
		Float diametro_de_la_particula =(float) Tamaño_de_gota_a_separar;
		Float densidad_del_crudo = (float) 0;

		//Condiciones de operación
		Float presion = (float) 1000;
		Float Temperatura = (float) 60;

		//Variables del sistema
		Float coeficiente_de_arrastre_supuesto = (float) 0.34;
		Float coeficiente_de_arrastre_calculado = (float) 0;
		Float velocidad_de_asentamiento = (float) 0;
		Float velocidad_de_asentamiento_1 =(float)0;
		Float reynolds = (float) 0;
		Float capacidad_del_liquido = (float) 0;

		//Variables de las dimensiones del equipo
		Float diametro_real = (float)0;
		Float longitud_requerida_liquido = (float) 0;
		Float longitud_efectiva_1 = (float) 0;
		Float longitud_efectiva_2 = (float) 0;
		Float longitud_real = (float) 0;
		Float tiempo_de_residencia =(float)0;
		Float diametro_minimo=(float)0;
		Float relacion_longitud_diametro=(float)0;

		//Ajuste de unidades y propiedades apartir de los datos de alimentos
		Temperatura = Temperatura + 460;
		densidad_del_gas=(float)(2.7*presion*gravedad_especifica_del_gas/(Temperatura*factor_de_compresibilidad));
		densidad_del_crudo = ((float)141.5/(grados_API+(float)131.5))*(float)62.4;

		//Viscosidad del gas.
		Float Parametro_A=(float)((9.379+0.0167*peso_molecular_del_gas)*Math.pow(Temperatura,1.5)/(209.2+19.26*peso_molecular_del_gas+Temperatura));
		Float Paramtero_B=(float)(3.448+986.4/Temperatura+0.01009*peso_molecular_del_gas);		
		Float Parametro_C=(float)(2.447-0.2224*Paramtero_B);
		viscosidad_dinamica_del_gas=(float) (Parametro_A*Math.exp(Paramtero_B*Math.pow((densidad_del_gas*453/Math.pow(30.48,3)),Parametro_C))*1e-4);

		//Calculo de la velocidad de asentamiento
		Float convergencia = (float)1;
		while (convergencia!= 0) {
			velocidad_de_asentamiento=(float) ((float)0.011868 *( Math.pow((densidad_del_crudo- densidad_del_gas) * diametro_de_la_particula / (densidad_del_gas*coeficiente_de_arrastre_supuesto),(float)0.5)));
			reynolds =(((float)0.0049)*densidad_del_gas*diametro_de_la_particula*velocidad_de_asentamiento)/viscosidad_dinamica_del_gas;
			coeficiente_de_arrastre_calculado = (float) (24/ reynolds +3/Math.pow(reynolds, 0.5) +0.34);
			velocidad_de_asentamiento_1=(float) ((float)0.011868 *( Math.pow((densidad_del_crudo- densidad_del_gas) * diametro_de_la_particula / (densidad_del_gas*coeficiente_de_arrastre_calculado),(float)0.5)));
			convergencia = Math.abs(velocidad_de_asentamiento-velocidad_de_asentamiento_1)/velocidad_de_asentamiento_1*100;
			coeficiente_de_arrastre_supuesto = coeficiente_de_arrastre_calculado ;
		}

		//Dimensionamiento del separador
		//Calculo del diametro mínimo.
		diametro_minimo=(float)(Math.pow((5055*Temperatura*factor_de_compresibilidad*flujo_del_gas/presion)*Math.pow((densidad_del_gas*coeficiente_de_arrastre_calculado)/((densidad_del_crudo-densidad_del_gas)*diametro_de_la_particula),0.5), 0.5));
		diametro_minimo= (float) Math.rint(diametro_minimo*100/100);
		diametro_real= diametro_minimo+1;

		for (int i =1; i < 20; i++) {
			tiempo_de_residencia=(float)i;
			//capacidad del líquido
			capacidad_del_liquido = (float) ((60/7)*tiempo_de_residencia * flujo_del_crudo);

			for(int j=1;j<140;j++){
				diametro_real = (float)j;
				longitud_requerida_liquido=(float)(capacidad_del_liquido/Math.pow(diametro_real, 2));
				longitud_efectiva_1= (float)((longitud_requerida_liquido+76)/12);
				longitud_efectiva_2= (float)((longitud_requerida_liquido+diametro_real+40)/12);

				if (longitud_efectiva_1<=longitud_efectiva_2){
					longitud_real=(float) Math.rint(longitud_efectiva_2*100/100);
					relacion_longitud_diametro=12*longitud_real/diametro_real;
					if(relacion_longitud_diametro>=3 && relacion_longitud_diametro<=4){
						System.out.println("tiempo de residencia (min)="+tiempo_de_residencia+ "   diametro (in)=  "+diametro_real		+ "	   h(in)"+longitud_requerida_liquido+"   Lss(ft)"+longitud_real+"   R/D= "+relacion_longitud_diametro);
					}
				}

				else{
					longitud_real=(float) Math.rint(longitud_efectiva_1*100/100);
					relacion_longitud_diametro=12*longitud_real/diametro_real;
					if(relacion_longitud_diametro>=3 && relacion_longitud_diametro<=4){
						System.out.println("tiempo de residencia (min)="+tiempo_de_residencia+ "	diametro (in)=  "+diametro_real		+ "	   h(in)"+longitud_requerida_liquido+"   Lss(ft)"+longitud_real+"   R/D= "+relacion_longitud_diametro);
					}
				}	
			}
		}
	}	
}