package Logica;


import javax.swing.JOptionPane;

public class Dimensionamiento_Deshidratador_vertical {

	public static void main(String[] args)  {
		// TODO Auto-generated constructor stub


		//Datos que se deben preguntar al usuario
		double temperatura_minima=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese temperatura mínima del intervalo de estudio")));
		double temperatura_maxima=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese temperatura máxima del intervalo de estudio")));
		double tiempo_de_retencion_minimo=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el tiempo de retención mínimo")));

		//double Tamaño_de_gota_a_separar=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota a separar en micrones")));

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
		Float grados_API = (float)40;
		Float densidad_del_crudo = (float) 0;
		Float gravedad_especifica_crudo =(float)0.825;
		Float viscosidad_del_crudo=(float)0;

		//Propiedades de agua
		Float gravedad_especifica_agua =(float)1.04;
		Float BSandW_inlet =(float)10;
		Float BSandW_outlet =(float)1;
		Float diametro_gota_agua=(float)0;

		//Condiciones de operación
		Float presion = (float) 1000;
		Float Temperatura_inicial = (float) 90;


		//Variables de  energía 
		Float Calor_requerido=(float)0;
		Float Delta_de_temperaturas=(float)0;



		//Variables de las dimensiones del equipo
		Float longitud_efectiva=(float)0;
		tiempo_de_retencion_minimo =(float)tiempo_de_retencion_minimo;
		Float Diametro =(float)0;
		Float Altura =(float)0;
		Float Restriccion_de_tiempo=(float)0;
		Float Material_requerido=(float)0;
		Float Material_requerido_1=(float)0;
		Float Volumen =(float)0;
		Float Volumen_1 =(float)0;
		Float Ecuacion_sedimentacion=(float)0;
		Float Tiempo_de_residencia_real=(float)0;
		Float diametro_del_liquido=(float)0;
		Float diametro_del_gas=(float)0;
		Float Area_flujo_gas =(float)0;
		Float diametro_longitud=(float)0;
		Float diametro2_longitud=(float)0;

		//Intervalo de operación
		Float Temperatura_minima=(float)temperatura_minima+460;
		Float Temperatura_maxima=(float)temperatura_maxima+460;


		//Ajuste de unidades y propiedades apartir de los datos de alimentos
		Temperatura_inicial= Temperatura_inicial + 460;
		if (gravedad_especifica_crudo==0 && grados_API!=0 ){
			gravedad_especifica_crudo=(float) (141.5/(grados_API+131.5));
		}
		densidad_del_gas=(float)(2.7*presion*gravedad_especifica_del_gas/(Temperatura_inicial*factor_de_compresibilidad));
		densidad_del_crudo = ((float)141.5/(grados_API+(float)131.5))*(float)62.4;
		Float delta_SG=gravedad_especifica_agua-gravedad_especifica_crudo;

		//Viscosidad del gas.
		Float Parametro_A=(float)((9.379+0.0167*peso_molecular_del_gas)*Math.pow(Temperatura_inicial,1.5)/(209.2+19.26*peso_molecular_del_gas+Temperatura_inicial));
		Float Paramtero_B=(float)(3.448+986.4/Temperatura_inicial+0.01009*peso_molecular_del_gas);		
		Float Parametro_C=(float)(2.447-0.2224*Paramtero_B);
		viscosidad_dinamica_del_gas=(float) (Parametro_A*Math.exp(Paramtero_B*Math.pow((densidad_del_gas*453/Math.pow(30.48,3)),Parametro_C))*1e-4);

		//Rango de temperaturas
		for (Float i=Temperatura_inicial; i<=(Temperatura_maxima); i=i+10) {

			Float Temperatura=(float) i;
			//System.out.println(Temperatura-460);

			delta_SG=gravedad_especifica_agua-gravedad_especifica_crudo;
			viscosidad_del_crudo=(float)(Math.pow(10,(Math.pow(10, 3.0324-0.02023*grados_API))*(Math.pow(Temperatura-460, -1.163)))-1);
			Float diametro_gota_agua_1=(float)(200*Math.pow(viscosidad_del_crudo, 0.25));
			diametro_gota_agua=(float)(Math.pow(BSandW_outlet, 0.33)*diametro_gota_agua_1);
			//System.out.println("delta de densidades= "+delta_SG+"  viscosidad del crudo(cp) = "+viscosidad_del_crudo+"  diametro de la gota(micrones)= "+diametro_gota_agua);

			//Ecuación de sedimentación
			Ecuacion_sedimentacion=(float)(81.8*Math.pow(flujo_del_crudo*viscosidad_del_crudo/(delta_SG*Math.pow(diametro_gota_agua,2)),0.5));
			//System.out.println("  Ecuacion de sedimentación"+Ecuacion_sedimentacion);

			// Ecuación de tiempo de retención del liquido. Restricción con respecto al tiempo de residencia
			Restriccion_de_tiempo=(float)(flujo_del_crudo*tiempo_de_retencion_minimo/0.12);
			//System.out.println("restriccion tiempo= "+Restriccion_de_tiempo+"  Ecuacion de sedumentación"+Ecuacion_sedimentacion);

			//Ecuación del flujo de calor requerido
			Delta_de_temperaturas=(float)(Temperatura-Temperatura_inicial);
			Calor_requerido=(float)(16*flujo_del_crudo*Delta_de_temperaturas*(0.5*gravedad_especifica_crudo+0.1));
			Diametro=Ecuacion_sedimentacion;
			Altura=(float)(Restriccion_de_tiempo/Math.pow(Diametro,2));
			Altura=(float) Math.rint((Altura+0.5)*100/100);
			Diametro=(float) Math.rint((Diametro+0.5)*100/100);
			
			System.out.println("  diametro= "+Diametro+" longitud= "+Altura+ "  Calor= "+Calor_requerido);
		}
	}
}

