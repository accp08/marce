package Logica;

import javax.swing.JOptionPane;

public class Dimensionamiento_Deshidratador_horizontal {

	public static void main(String[] args)  {
		// TODO Auto-generated constructor stub


		//Datos que se deben preguntar al usuario
		double temperatura_inicial_del_crudo=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese la temperatura del crudo a la etapa de calentamiento")));
		double temperatura_minima=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese temperatura mínima del intervalo de estudio")));
		double temperatura_maxima=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese temperatura máxima del intervalo de estudio")));
		double tiempo_de_retencion_minimo=(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el tiempo de retención mínimo")));
		//		int numero_datos_curva_viscosidad=(int) (Double.parseDouble(JOptionPane.showInputDialog("puntos disponibles para la curva de viscosidad")));
		//		double Curva_de_viscosidad[][]=new double [numero_datos_curva_viscosidad][numero_datos_curva_viscosidad];
		//		for (int i = 0; i <numero_datos_curva_viscosidad; i++) {
		//			for (int j = 0; j < numero_datos_curva_viscosidad; j++) {
		//				Curva_de_viscosidad[i][j]=Double.parseDouble(JOptionPane.showInputDialog("ingrese el valor de la viscosidad en cP"));
		//			}
		//		}
		//
		//		for (int j = 0; j <numero_datos_curva_viscosidad; j++) {
		//			for (int i = 0; i < numero_datos_curva_viscosidad; i++) {
		//				Curva_de_viscosidad[i][j]=Double.parseDouble(JOptionPane.showInputDialog("ingrese el valor de la temeratura en ºF"));
		//			}
		//
		//		}

		temperatura_maxima=temperatura_maxima+460;
		temperatura_minima=temperatura_minima+460;

		//double Tamaño_de_gota_a_separar=(Double.parseDouble(JOptionPane.showInputDialog("Tamaño de gota a separar en micrones")));

		//Todas estas variables son provenientes de calculos anteriores.


		//===================================================================================================
		//Propiedades del alimento 
		//==================================================================================================


		//Condiciones del alimento.
		Double presion = (double) 250;
		Double Temperatura_inicial =temperatura_inicial_del_crudo+460;
		//Propiedades del crudo	
		Double flujo_del_crudo = (double) 7000;		
		Double grados_API = (double) 30;
		Double densidad_del_crudo = (double) 0;
		Double gravedad_especifica_crudo =(double)0.86;
		Double Viscosidad_del_crudo1=(double)0;
		//Propiedades de agua
		Double gravedad_especifica_agua =(double)1.06;
		Double BSandW_inlet =(double)15;
		Double BSandW_outlet =(double)1;
		Double diametro_gota_agua=(double)0;


		//===================================================================================================
		//Variables de operación
		//==================================================================================================

		//Variables de  energía 
		Double Calor_requerido=(double)0;
		Double Delta_de_temperaturas=(double)0;


		//===================================================================================================
		//Dimensionamiento del equipo.
		//==================================================================================================

		//Variables de las dimensiones del equipo
		Double longitud_efectiva=(double)0;
		tiempo_de_retencion_minimo =(double)tiempo_de_retencion_minimo;
		Double Diametro =(double)0;
		Double Restriccion_de_tiempo=(double)0;
		Double Ecuacion_sedimentacion=(double)0;

		//Intervalo de operación
		Double Temperatura_minima=(Double)temperatura_minima+460;
		Double Temperatura_maxima=(Double)temperatura_maxima+460;

		//Ajuste de unidades y propiedades apartir de los datos de alimentos
		Temperatura_inicial= Temperatura_inicial + 460;
		if (gravedad_especifica_crudo==0 && grados_API!=0 ){
			gravedad_especifica_crudo=(Double) (141.5/(grados_API+131.5));
		}
		densidad_del_crudo = ((Double)141.5/(grados_API+(Double)131.5))*(Double)62.4;
		Double delta_SG=gravedad_especifica_agua-gravedad_especifica_crudo;




		//===================================================================================================
		//Restricción con respecto al tiempo de residencia - Tiempo de retención del líquido
		//==================================================================================================
		// Ecuación de tiempo de retención del liquido. Restricción con respecto al tiempo de residencia
		Restriccion_de_tiempo=(Double)(flujo_del_crudo*tiempo_de_retencion_minimo/1.05);
		//System.out.println("restriccion tiempo= "+Restriccion_de_tiempo+"  Ecuacion de sedumentación"+Ecuacion_sedimentacion);
		System.out.println("Datos para la curva de restricción: ");
		//Datos para la curva de restricción
		for(int j=1;j<=140;j++ ){
			longitud_efectiva=(double) j;
			Double diametro_minimo=(Double)(Math.pow(Restriccion_de_tiempo/longitud_efectiva,0.5));
			//System.out.println("longitud= "+longitud_efectiva+"   Diametro= "+diametro_minimo);
		}

		System.out.println("");
		
		//===================================================================================================
		//Dimensionamiento para las diferentes temperaturas
		//==================================================================================================

		//Rango de temperaturas

		for (Double i=Temperatura_minima; i<=(Temperatura_maxima); i=i+20) {
			Double Temperatura=(Double) i;
			System.out.println("");
			System.out.println("temperatura= "+(Temperatura));
			delta_SG=gravedad_especifica_agua-gravedad_especifica_crudo;
			Viscosidad_del_crudo1=(Double)(Math.pow(10,(Math.pow(10, 3.0324-0.02023*grados_API))*(Math.pow(Temperatura-460, -1.163)))-1);
			Double diametro_gota_agua_1=(Double)(200*Math.pow(Viscosidad_del_crudo1, 0.25));
			diametro_gota_agua=(Double)(Math.pow(BSandW_outlet, 0.33)*diametro_gota_agua_1);
			//System.out.println("delta de densidades= "+delta_SG+"  viscosidad del crudo(cp) = "+viscosidad_del_crudo+"  diametro de la gota(micrones)= "+diametro_gota_agua+"   diametro*longitud(in*ft)= "+diametro_longitud);

			//Ecuación de sedimentación
			Ecuacion_sedimentacion=(Double)(438*flujo_del_crudo*Viscosidad_del_crudo1/(delta_SG*Math.pow(diametro_gota_agua,2)));


			//Ecuación del flujo de calor requerido
			Delta_de_temperaturas=(Double)(Temperatura-Temperatura_inicial);
			Calor_requerido=(Double)(16*flujo_del_crudo*Delta_de_temperaturas*(0.5*gravedad_especifica_crudo+0.1));


			for(int j=1;j<=140;j++ ){
				longitud_efectiva=(double) j;
				if (Ecuacion_sedimentacion/longitud_efectiva>= Math.pow(Restriccion_de_tiempo/longitud_efectiva,0.5)){
					Diametro=(Double)(Ecuacion_sedimentacion/longitud_efectiva);
					System.out.println("  diametro= "+Diametro+" longitud= "+longitud_efectiva+ "  Calor= "+Calor_requerido+ "	Temperatura= "+ Temperatura);
				}
			}
		}
	}
}
