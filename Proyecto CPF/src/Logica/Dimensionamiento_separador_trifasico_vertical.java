package Logica;

public class Dimensionamiento_separador_trifasico_vertical {

	public Dimensionamiento_separador_trifasico_vertical() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Variables de ingreso al sistema
		// El usuario debe establecer el valor del diametro  de partícula a retirar.
		// El usuario debe establecer el espacio que será para el líquido y pra el gas en el porcentaje.
		//La presión de operación del primer separador, asi como la temperatura lo ingresa el usuario.
		//De la caracterización de las corrientes que se calculan para el equilibrio se determina la densidad de la fase liquida y del vapor.
		//la ecuación de la viscosidad dinamica está en el cap 3 del libro

		//Datos que se deben preguntar al usuario
		//double tiempo_de_residencia_del_agua=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese tiempo de residencia del agua en min")));
		//double tiempo_de_residencia_del_crudo=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de residencia del crudo en min")));
		//double Tamaño_de_gota_de_crudo_en_agua=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota de crudo en agua a separar en micrones")));
		//double Tamaño_de_gota_de_crudo_en_gas=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota de crudo en aire a separar en micrones")));
		//double Gravedad_especifica_del_agua=(Double.parseDouble(JOptionPane.showInputDialog("gravedad especifica del agua")));
		//double Porcentaje_de_gas=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el porcentaje del gas")));

		//Todas estas variables son provenientes de calculos anteriores.
		//Propiedades del gas

		Float flujo_del_gas = (float)5;
		Float factor_de_compresibilidad = (float)0.98;	
		Float constante_gases_ideales = (float) 10.73;
		Float peso_molecular_del_gas= (float)17.4;
		Float densidad_del_gas = (float)0;
		Float viscosidad_dinamica_del_gas = (float) 0;
		Float gravedad_especifica_del_gas=(float)0.6;


		//Propiedades del crudo	
		Float flujo_del_crudo = (float)5000;		
		Float grados_API = (float)30;
		Float densidad_del_crudo = (float) 0;
		Float viscosidad_cinematica_del_crudo = (float)0;
		Float viscosidad_dinamica_del_crudo = (float)10;
		Float gravedad_especifica_crudo=(float)0;
		//Float dp_crudo_en_gas =(float) Tamaño_de_gota_de_crudo_en_gas;
		Float dp_crudo_en_gas=(float)140;
		//Float dp_agua_en_crudo =(float) Tamaño_de_gota_de_crudo_en_agua;
		Float dp_agua_en_crudo=(float)500;

		//Propiedades de agua
		Float flujo_de_agua=(float)3000;
		Float gravedad_especifica_agua =(float)1.07;
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
		Float Tiempo_de_residencia_del_crudo=(float)10;
		Float delta_SG =(float)0;
		Float velocidad_real_del_gas =(float)0;
		Float porcentaje_de_liquido=(float)0;

		//Variables de las dimensiones del equipo
		Float espesor_capa_crudo=(float)0;
		Float espesor_capa_agua=(float)0;
		Float espesor_capas_de_liquidos=(float)0;
		Float Relacion_AreaWater_AreaTotal=(float)0;
		Float capacidad_del_liquido = (float) 0;
		Float longitud=(float)0;
		Float longitud_efectiva_liquido = (float) 0;
		Float longitud_efectiva_gas = (float) 0;
		Float diametro_del_liquido=(float)0;
		Float diametro_del_gas=(float)0;
		Float diametro=(float)0;
		Float Area_flujo_gas =(float)0;
		Float capacidad_mayor=(float)0;
		Float diametro_maximo=(float)0;
		Float diametro_minimo_crudo_en_gas=(float)0;
		Float diametro_minimo_agua_en_crudo=(float)0;


		//Condiciones de operación
		Float presion = (float) 100;
		Float Temperatura = (float) 90;

		Temperatura = Temperatura + 460;
		if (gravedad_especifica_crudo==0 && grados_API!=0 ){
			gravedad_especifica_crudo=(float) (141.5/(grados_API+131.5));
		}
		densidad_del_gas=(float)(2.7*presion*gravedad_especifica_del_gas/(Temperatura*factor_de_compresibilidad));
		densidad_del_crudo = ((float)141.5/(grados_API+(float)131.5))*(float)62.4;
		delta_SG=gravedad_especifica_agua-gravedad_especifica_crudo;
		//Viscosidad del gas.
		Float Parametro_A=(float)((9.379+0.0167*peso_molecular_del_gas)*Math.pow(Temperatura,1.5)/(209.2+19.26*peso_molecular_del_gas+Temperatura));
		Float Paramtero_B=(float)(3.448+986.4/Temperatura+0.01009*peso_molecular_del_gas);		
		Float Parametro_C=(float)(2.447-0.2224*Paramtero_B);
		viscosidad_dinamica_del_gas=(float) (Parametro_A*Math.exp(Paramtero_B*Math.pow((densidad_del_gas*453/Math.pow(30.48,3)),Parametro_C))*1e-4);


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
		//System.out.println(coeficiente_de_arrastre_calculado);

		//Dimensionamiento del separador
		//Calculo del diametro mínimo para la capacidad del gas
		diametro_minimo_crudo_en_gas=(float)(Math.pow((5055*Temperatura*factor_de_compresibilidad*flujo_del_gas/presion)*Math.pow((densidad_del_gas*coeficiente_de_arrastre_calculado)/((densidad_del_crudo-densidad_del_gas)*dp_crudo_en_gas),0.5), 0.5));
		System.out.println(diametro_minimo_crudo_en_gas);
		diametro_minimo_crudo_en_gas= (float) Math.rint(diametro_minimo_crudo_en_gas*100/100);
		diametro_minimo_crudo_en_gas= diametro_minimo_crudo_en_gas+1;

		//Diametro mínimo para el sentamiento de la gota de agua
		diametro_minimo_agua_en_crudo=(float)Math.pow((6690*flujo_del_crudo*viscosidad_dinamica_del_crudo/(delta_SG*Math.pow(dp_agua_en_crudo,2))), 0.5);
		//System.out.println("diametro minino agua en crudo= "+diametro_minimo_agua_en_crudo );


		for(int j=1;j<200;j++){
			diametro=(float)j;
			//Restricción en la retención de líquidos
			espesor_capa_crudo=(float)(Tiempo_de_residencia_del_crudo*flujo_del_crudo/(0.12*Math.pow(diametro,2)));
			espesor_capa_agua=(float)(Tiempo_de_residencia_del_agua*flujo_de_agua/(0.12*Math.pow(diametro,2)));
			espesor_capas_de_liquidos=espesor_capa_agua+espesor_capa_crudo;


			Float longitud_liquidos_1=(float) ((espesor_capas_de_liquidos+76)/12);
			Float longitud_liquidos_2=(float)((espesor_capas_de_liquidos+diametro+40)/12);	


			if (longitud_liquidos_1>longitud_liquidos_2){
				longitud=longitud_liquidos_1;				
			}
			else{
				longitud=longitud_liquidos_2;
			}

			if(longitud*12/diametro>1.5 && longitud*12/diametro<3 ){
				longitud=(float) Math.rint((longitud+0.5)*100/100);
				System.out.println("<3 diametro= "+diametro+"  long. real= "+longitud +"	R/D= "+longitud*12/diametro);
			}
		}
	}
}