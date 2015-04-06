
package Logica;

import javax.swing.JOptionPane;

public class Separador_bifasico_horizontal {
	public Separador_bifasico_horizontal() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		//Variables de ingreso al sistema:

		//Para el gas:
		//Flujo del gas= se obtiene del equilibrio.
		// Factor de compresibilidad = es la Z que da el equilibrio
		//Constante de los gases ideales= se tiene.
		// Gravedad especifica del gas = calculada para el corte en función de las propiedades calculadas en la caracterización del crudo.
		// Densidad del gas = se determina en la caracterización de las propiedades.

		//Para el crudo:
		//flujo_del_crudo = Dado por el equilibrio
		// Grados_API o densidad del liquido = LA DA EL USUARIO O SE CALCULA CON EL EQUILIBRIO
		// diametro_de_la_particula =DADA POR EL USUARIO;

		//Dimensiones del equipo y condiciones de separación
		//La presión de operación del primer separador, 
		//temperatura  de separación
		//Tamaño de gota a separar



		//Datos que se deben preguntar al usuario
		double Porcentaje_de_gas=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje del gas")));
		double Tamaño_de_gota_a_separar=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota a separar en micrones")));

		//Todas estas variables son provenientes de calculos anteriores.
		//Propiedades del gas
		Float flujo_del_gas = (float) 10;
		Float factor_de_compresibilidad = (float) 0.88;	
		Float constante_gases_ideales = (float) 10.73;
		Float peso_molecular_del_gas= (float) 15.95;
		Float gravedad_especifica_del_gas=(float)0.55;
		Float densidad_del_gas = (float) 0;
		Float viscosidad_dinamica_del_gas = (float)0;

		//Propiedades del crudo	
		Float flujo_del_crudo = (float) 2000;		
		Float grados_API = (float) 40;
		Float diametro_de_la_particula =(float) Tamaño_de_gota_a_separar;
		Float densidad_del_crudo = (float) 0;

		//Condiciones de operación
		Float presion = (float) 1200;
		Float Temperatura = (float) 120;

		//Variables del sistema
		Float coeficiente_de_arrastre_supuesto = (float) 0.34;
		Float coeficiente_de_arrastre_calculado = (float) 0;
		Float velocidad_de_asentamiento = (float) 0;
		Float velocidad_de_asentamiento_1 =(float)0;
		Float reynolds = (float) 0;
		Float capacidad_del_gas = (float) 0;
		Float velocidad_real_del_gas =(float)0;
		Float porcentaje_de_liquido=(float)0;
		Float porcentaje_de_gas=(float)Porcentaje_de_gas;

		//Variables de las dimensiones del equipo
		Float diametro_real = (float)0;
		Float capacidad_del_liquido = (float) 0;
		Float longitud_efectiva_liquido = (float) 0;
		Float longitud_efectiva_gas = (float) 0;
		Float longitud_real = (float) 0;
		Float tiempo_de_residencia =(float)0;
		Float Tiempo_de_residencia_real=(float)0;
		Float diametro_del_liquido=(float)0;
		Float diametro_del_gas=(float)0;
		Float Area_flujo_gas =(float)0;

		//Ajuste de unidades y propiedades apartir de los datos de alimentos
		Temperatura = Temperatura + 460;
		densidad_del_gas=(float)(2.7*presion*gravedad_especifica_del_gas/(Temperatura*factor_de_compresibilidad));
		densidad_del_crudo = ((float)141.5/(grados_API+(float)131.5))*(float)62.4;

		//Viscosidad del gas.
		Float Parametro_A=(float)((9.379+0.0167*peso_molecular_del_gas)*Math.pow(Temperatura,1.5)/(209.2+19.26*peso_molecular_del_gas+Temperatura));
		Float Paramtero_B=(float)(3.448+986.4/Temperatura+0.01009*peso_molecular_del_gas);		
		Float Parametro_C=(float)(2.447-0.2224*Paramtero_B);
		viscosidad_dinamica_del_gas=(float) (Parametro_A*Math.exp(Paramtero_B*Math.pow((densidad_del_gas*453/Math.pow(30.48,3)),Parametro_C))*1e-4);
		System.out.println("viscosidad dinámica= "+viscosidad_dinamica_del_gas);
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

		System.out.println("reynolds=  "+reynolds);

		System.out.println(coeficiente_de_arrastre_calculado);
		//Dimensionamiento del separador

		//Porcentajes del gas y del líquido en el recipiente.
		porcentaje_de_liquido=(float)(100-porcentaje_de_gas);

		//Determinacion del diametro y la longitud real y el tiempo de residencia.
		if(porcentaje_de_gas<=50){
			for (int i=3; i < 20; i++) {
				tiempo_de_residencia=(float)i;

				// Capacidad del gas
				capacidad_del_gas= (float)((Math.pow((50/porcentaje_de_gas),0.5))*(421.21*Temperatura*factor_de_compresibilidad*flujo_del_gas/presion)*Math.pow((densidad_del_gas*coeficiente_de_arrastre_calculado)/((densidad_del_crudo-densidad_del_gas)*diametro_de_la_particula),0.5));

				//capacidad del líquido
				capacidad_del_liquido = (float) (tiempo_de_residencia * flujo_del_crudo / ((1-0.01*porcentaje_de_gas)*1.4));

				for(int j=1;j<140;j++){
					diametro_real=(float)j;
					longitud_efectiva_liquido=(float)(capacidad_del_liquido/Math.pow(diametro_real,2));
					longitud_efectiva_gas=capacidad_del_gas/diametro_real;

					if(longitud_efectiva_gas>longitud_efectiva_liquido){
						longitud_real=longitud_efectiva_gas+diametro_real/12;
						longitud_real=(float) Math.rint(longitud_real*100/100);
						velocidad_real_del_gas=longitud_efectiva_gas/tiempo_de_residencia;
					}
					else{
						longitud_real=(float)(longitud_efectiva_liquido*4/3);
						longitud_real=(float) Math.rint(longitud_real*100/100);
						velocidad_real_del_gas=longitud_efectiva_liquido/tiempo_de_residencia;
					}

					Float relacion_Long_Diametro =(float)(longitud_real*12/diametro_real); 
					if(relacion_Long_Diametro>3 && relacion_Long_Diametro<4){

						//Ajuste de la longitud
						longitud_real=(float)(longitud_real+0.5);
						longitud_real=(float) Math.rint(longitud_real*100/100);

						//Tiempo de residencia real
						diametro_del_gas=(float)(Math.pow(porcentaje_de_gas/50,0.5)*diametro_real);
						Tiempo_de_residencia_real=(float)(((Math.PI*Math.pow(diametro_real,2)/(4*144)-(Math.PI*Math.pow(diametro_del_gas,2)/(4*2*144)))*longitud_real)/(6.49e-5*flujo_del_crudo)/60);

						//Velocidad real del gas
						Area_flujo_gas =(float)((Math.PI*Math.pow(diametro_del_gas,2)/(4*2*144)));
						velocidad_real_del_gas=(float)(0.327*Temperatura*factor_de_compresibilidad*flujo_del_gas/presion/Area_flujo_gas);

						//Variables a imprimir
						System.out.println( "tiempo de residencia= "+tiempo_de_residencia+"    longitud (ft)= "+longitud_real+"	diametro(in)=  "+diametro_real		+ "	    R/D= "+relacion_Long_Diametro+"     T. residencia (min)="+Tiempo_de_residencia_real+"   velocidad del gas(ft/s)="+velocidad_real_del_gas);

					}
				}
			}
		}

		else{
			for (int i=3; i < 20; i++) {
				tiempo_de_residencia=(float)i;
				// Capacidad del gas
				Float numerador_1=(float)((1-Math.pow(porcentaje_de_liquido/200,0.5))*(Temperatura*factor_de_compresibilidad*flujo_del_gas/presion)*(59.954));
				Float denominador_1=(float)(12*velocidad_de_asentamiento*(1-0.01*porcentaje_de_liquido));	
				capacidad_del_gas=numerador_1/denominador_1;

				//capacidad del líquido
				capacidad_del_liquido = (float) (50/porcentaje_de_liquido*tiempo_de_residencia*flujo_del_crudo/0.7);

				for(int j=1;j<140;j++){
					diametro_real=(float)j;
					longitud_efectiva_liquido=(float)(capacidad_del_liquido/Math.pow(diametro_real,2));
					longitud_efectiva_gas=capacidad_del_gas/diametro_real;

					if(longitud_efectiva_gas>longitud_efectiva_liquido){
						longitud_real=longitud_efectiva_gas+diametro_real/12;
						longitud_real=(float) Math.rint(longitud_real*100/100);
						velocidad_real_del_gas=longitud_efectiva_gas/tiempo_de_residencia;
					}
					else{
						longitud_real=(float)(longitud_efectiva_liquido*4/3);
						longitud_real=(float) Math.rint(longitud_real*100/100);
						velocidad_real_del_gas=longitud_efectiva_liquido/tiempo_de_residencia;
					}

					Float relacion_Long_Diametro =(float)(longitud_real*12/diametro_real); 
					if(relacion_Long_Diametro>3 && relacion_Long_Diametro<4){
						longitud_real=(float)(longitud_real+0.5);
						longitud_real=(float) Math.rint(longitud_real*100/100);

						//Tiempo de residencia real
						diametro_del_liquido=(float)(Math.pow(porcentaje_de_liquido/50, 2)*diametro_real);
						Tiempo_de_residencia_real=(float)(Math.PI*Math.pow(diametro_del_liquido,2)/(4*2*144)*longitud_real/(6.49e-5*flujo_del_crudo)/60);

						//Velocidad real del gas
						Float Area_gas =(float)((Math.PI*Math.pow(diametro_real,2)/(4*144))-(Math.PI*Math.pow(diametro_del_liquido,2)/(4*2*144)));
						velocidad_real_del_gas=(float)(0.327*Temperatura*factor_de_compresibilidad*flujo_del_gas/presion/Area_gas);

						//Variables a imprimir
						System.out.println( ":D tiempo de residencia= "+tiempo_de_residencia+"    longitud (ft)= "+longitud_real+"	diametro(in)=  "+diametro_real		+ "	    R/D= "+relacion_Long_Diametro+"     T. residencia (min)="+Tiempo_de_residencia_real+"   velocidad del gas(ft/s)="+velocidad_real_del_gas);
					}
				}
			}	
		}
		System.out.println(viscosidad_dinamica_del_gas);
	}
}


