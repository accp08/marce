package Logica;

public class Dimensionamiento_separador_trifasico_horizontal {

	public Dimensionamiento_separador_trifasico_horizontal() {
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
		// Grados_API o densidad del liquido = LA DA EL USUARIO O SE CALCULA en la caracterización del crudo
		// diametro_de_la_particula =DADA POR EL USUARIO;
		//Viscosidad (cinematica o dinamica) del crudo
		//gravedad especifica del crudo


		//Para el agua
		//flujo de agua  = Ingresada por el usuario.
		//Densidad de la fase acuosa.


		//Dimensiones del equipo y condiciones de separación
		//La presión de operación del primer separador, 
		//Temperatura  de separación
		//Tamaño de gota de crudo a separar de la fase gaseosa = dada por el usario
		//Tamaño de gota de crudo a separar de la fase acuosa =Dada por el usuario.
		// espacio que será para el líquido y pra el gas en el porcentaje = El usuario 
		// La presión de operación del primer separador, asi como la temperatura lo ingresa el usuario.
		// Tiempo de residencia del crudo = Usuario.
		//Tiempo del residencia del gas =Usuario.


		//Datos que se deben preguntar al usuario
		//double tiempo_de_residencia_del_agua=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese tiempo de residencia del agua en min")));
		//double tiempo_de_residencia_del_crudo=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de residencia del crudo en min")));
		//double Tamaño_de_gota_de_crudo_en_agua=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota de crudo en agua a separar en micrones")));
		//double Tamaño_de_gota_de_crudo_en_gas=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota de crudo en aire a separar en micrones")));
		//double Gravedad_especifica_del_agua=(Double.parseDouble(JOptionPane.showInputDialog("gravedad especifica del agua")));
		//double Porcentaje_de_gas=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje del gas")));

		//Todas estas variables son provenientes de calculos anteriores.
		//Propiedades del gas

		Float flujo_del_gas = (float)25;
		Float factor_de_compresibilidad = (float)0.98;	
		Float constante_gases_ideales = (float) 10.73;
		Float peso_molecular_del_gas= (float)0;
		Float densidad_del_gas = (float)0;
		Float viscosidad_dinamica_del_gas = (float) 0;
		Float gravedad_especifica_del_gas=(float)0.6;

		//Propiedades del crudo	
		Float flujo_del_crudo = (float)14000;		
		Float grados_API = (float)30;
		Float densidad_del_crudo = (float) 0;
		Float viscosidad_cinematica_del_crudo = (float)0;
		Float viscosidad_dinamica_del_crudo = (float)2.55;
		Float gravedad_especifica_crudo=(float)0.876;
		//Float dp_crudo_en_gas =(float) Tamaño_de_gota_de_crudo_en_gas;
		Float dp_crudo_en_gas=(float)140;
		//Float dp_agua_en_crudo =(float) Tamaño_de_gota_de_crudo_en_agua;
		Float dp_agua_en_crudo=(float)500;

		//Propiedades de agua
		Float flujo_de_agua=(float)9000;
		Float gravedad_especifica_agua =(float)1;
		//Float gravedad_especifica_agua=(float)Gravedad_especifica_del_agua;

		//Variables del sistema
		Float coeficiente_de_arrastre_supuesto = (float) 0.34;
		Float coeficiente_de_arrastre_calculado = (float) 0;
		Float velocidad_de_asentamiento = (float) 0;
		Float velocidad_de_asentamiento_1 =(float)0;
		Float reynolds = (float) 0;
		Float capacidad_del_gas = (float) 0;
		//Float porcentaje_de_gas=(float)Porcentaje_de_gas;
		Float porcentaje_de_gas=(float)50;
		//Float Tiempo_de_residencia_del_agua =(float)tiempo_de_residencia_del_agua;
		Float Tiempo_de_residencia_del_agua=(float)10;
		//Float Tiempo_de_residencia_del_crudo =(float)tiempo_de_residencia_del_crudo;
		Float Tiempo_de_residencia_del_crudo=(float)15;
		Float delta_SG =(float)0;
		Float velocidad_real_del_gas =(float)0;
		Float porcentaje_de_liquido=(float)0;

		//Variables de las dimensiones del equipo
		Float espesor_maximo_capa_crudo=(float)0;
		Float Relacion_AreaWater_AreaTotal=(float)0;
		Float capacidad_del_crudo = (float) 0;
		Float longitud_real=(float)0;
		Float longitud_efectiva_crudo = (float) 0;
		Float longitud_efectiva_gas = (float) 0;
		Float longitud_efectiva=(float)0;
		Float diametro_del_gas=(float)0;
		Float diametro=(float)0;
		Float Area_flujo_gas =(float)0;
		Float diametro_maximo=(float)0;

		//Condiciones de operación
		Float presion = (float) 3000;
		Float Temperatura = (float) 120;

		//Ajuste de unidades y propiedades apartir de los datos de alimentos
		Temperatura = Temperatura + 460;
		if (gravedad_especifica_crudo==0 && grados_API!=0 ){
			gravedad_especifica_crudo=(float) (141.5/(grados_API+131.5));
		}
		densidad_del_gas=(float)(2.7*presion*gravedad_especifica_del_gas/(Temperatura*factor_de_compresibilidad));
		System.out.println("Densidad del gas= "+"   "+ densidad_del_gas );
		System.out.println("gravedad específica de gas="+ " 	"+gravedad_especifica_del_gas);
		densidad_del_crudo = ((float)141.5/(grados_API+(float)131.5))*(float)62.4;
		System.out.println("Densidad del crudo="+ densidad_del_crudo);
		delta_SG=gravedad_especifica_agua-gravedad_especifica_crudo;
		//Viscosidad del gas.
		Float Parametro_A=(float)((9.379+0.0167*peso_molecular_del_gas)*Math.pow(Temperatura,1.5)/(209.2+19.26*peso_molecular_del_gas+Temperatura));
		Float Paramtero_B=(float)(3.448+986.4/Temperatura+0.01009*peso_molecular_del_gas);		
		Float Parametro_C=(float)(2.447-0.2224*Paramtero_B);
		viscosidad_dinamica_del_gas=(float) (Parametro_A*Math.exp(Paramtero_B*Math.pow((densidad_del_gas*453/Math.pow(30.48,3)),Parametro_C))*1e-4);
		System.out.println("Viscosidad dinámica del gas"+"   "+viscosidad_dinamica_del_gas);

		//CALCULO DE LA CAPACIDAD Y RESTRICCIONES DEL GAS		
		//Calculo de la velocidad de asentamiento
		Float convergencia = (float)1;
		while (convergencia!= 0) {
			velocidad_de_asentamiento=(float) ((float)0.011868 *( Math.pow((densidad_del_crudo- densidad_del_gas) * dp_crudo_en_gas / (densidad_del_gas*coeficiente_de_arrastre_supuesto),(float)0.5)));
			reynolds =(((float)0.0049)*densidad_del_gas*dp_crudo_en_gas*velocidad_de_asentamiento)/viscosidad_dinamica_del_gas;
			coeficiente_de_arrastre_calculado = (float) (24/ reynolds +3/Math.pow(reynolds, 0.5) +0.34);
			velocidad_de_asentamiento_1=(float) ((float)0.011868 *( Math.pow((densidad_del_crudo- densidad_del_gas) * dp_crudo_en_gas/ (densidad_del_gas*coeficiente_de_arrastre_calculado),(float)0.5)));
			convergencia = Math.abs(velocidad_de_asentamiento-velocidad_de_asentamiento_1)/velocidad_de_asentamiento_1*100;
			coeficiente_de_arrastre_supuesto = coeficiente_de_arrastre_calculado ;
		}

		//System.out.println("reynolds=  "+reynolds);
		System.out.println(coeficiente_de_arrastre_calculado);

		// Capacidad del gas
		capacidad_del_gas= (float)((Math.pow((50/porcentaje_de_gas),0.5))*(421.21*Temperatura*factor_de_compresibilidad*flujo_del_gas/presion)*Math.pow((densidad_del_gas*coeficiente_de_arrastre_calculado)/((densidad_del_crudo-densidad_del_gas)*dp_crudo_en_gas),0.5));
		System.out.println("capacidad del gas=  "+capacidad_del_gas);

		//combinciones diametro longitud para el gas (determinar si l longitud del gas va a gobernar)
		for (int i=1; i <(capacidad_del_gas-1); i++) {
			diametro_del_gas=(float) i;
			longitud_efectiva_gas=capacidad_del_gas/diametro_del_gas;
			if (12*longitud_efectiva_gas/diametro_del_gas>=3 && 12*longitud_efectiva_gas/diametro_del_gas<=4){
				//System.out.println("diametro del gas="+diametro_del_gas+"longitud= "+longitud_efectiva_gas);
			}
		}

		//Máximo espesor de la  capa de crudo
		espesor_maximo_capa_crudo=(float) (1.28e-3*Tiempo_de_residencia_del_crudo*delta_SG*Math.pow(dp_agua_en_crudo,2)/viscosidad_dinamica_del_crudo);
		System.out.println("espesor capa límite= "+espesor_maximo_capa_crudo);

		//Diametro máximo dado por los líquidos
		Relacion_AreaWater_AreaTotal=(float) (0.5*(flujo_de_agua*Tiempo_de_residencia_del_agua)/(flujo_de_agua*Tiempo_de_residencia_del_agua+flujo_del_crudo*Tiempo_de_residencia_del_crudo));
		System.out.println("Relacion_AreaWater_AreaTotal= "+Relacion_AreaWater_AreaTotal);
		diametro_maximo=(float) (espesor_maximo_capa_crudo/(242.45*Math.pow(Relacion_AreaWater_AreaTotal,6)-404.61*Math.pow(Relacion_AreaWater_AreaTotal,5)+ 265.2*Math.pow(Relacion_AreaWater_AreaTotal,4)- 87.258*Math.pow(Relacion_AreaWater_AreaTotal,3)+ 15.62*Math.pow(Relacion_AreaWater_AreaTotal,2) - 2.4177*Relacion_AreaWater_AreaTotal + 0.4929));
		System.out.println("diametro maximo= "+diametro_maximo);

		//Restricción de la retención de los líquidos
		capacidad_del_crudo=(float)(1.42*(flujo_de_agua*Tiempo_de_residencia_del_agua+flujo_del_crudo*Tiempo_de_residencia_del_crudo));
		//System.out.println("capacidad del liquido= "+capacidad_del_crudo);

		for(int j=1;j<200;j++){
			diametro=(float)j;
			longitud_efectiva_crudo=(float)(capacidad_del_crudo/Math.pow(diametro,2));
			longitud_efectiva_gas=(float)(capacidad_del_gas/diametro);
			if (longitud_efectiva_gas>longitud_efectiva_crudo){
				longitud_efectiva=longitud_efectiva_gas;				
			}
			else{
				longitud_efectiva=longitud_efectiva_crudo;
			}
			Float longitud_real_1=(float) (longitud_efectiva/0.75);
			Float longitud_real_2=(float)(longitud_efectiva+diametro/12);	
		
			if(longitud_real_1>longitud_real_2){
				longitud_real=longitud_real_1;
				if(longitud_real*12/diametro>3 && longitud_real*12/diametro<5 ){
					//longitud_real=(float) Math.rint((longitud_real+0.5)*100/100);
					System.out.println("diametro= "+diametro+"  long. real= "+longitud_real+"	R/D= "+longitud_real*12/diametro);
				}
			}
			else{
				longitud_real=longitud_real_2;
				if(longitud_real*12/diametro>3 && longitud_real*12/diametro<5){
					//longitud_real=(float) Math.rint((longitud_real+0.5)*100/100);
					System.out.println("diametro= "+diametro+"  long. real= "+longitud_real+"	R/D= "+longitud_real*12/diametro);
				}
			}
		}

	System.out.println(viscosidad_dinamica_del_crudo + "	"+ viscosidad_cinematica_del_crudo)	;
	}
}