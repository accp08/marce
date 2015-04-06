package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

import Logica.Objeto_Compuestos_Caracterizables;
import Logica.Objeto_constante_calculo_entalpia;

public class Lectura_HC_Compuestos_Caracterizables {

	public Lectura_HC_Compuestos_Caracterizables() {

	}


	public static LinkedList<Objeto_Compuestos_Caracterizables> carga(){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		LinkedList<Objeto_Compuestos_Caracterizables> lista_de_compuestos = new LinkedList<Objeto_Compuestos_Caracterizables>();
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File ("compuestos_caracterizables.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while((linea=br.readLine())!=null){
				String[] vec = linea.split("\t");
				Objeto_Compuestos_Caracterizables compuesto = new Objeto_Compuestos_Caracterizables(vec[0], vec[1], Double.parseDouble(vec[2]), Double.parseDouble(vec[3]), Double.parseDouble(vec[4]), Double.parseDouble(vec[5]), Double.parseDouble(vec[6]), Double.parseDouble(vec[7]), Double.parseDouble(vec[8]),Double.parseDouble(vec[9]),Double.parseDouble(vec[10]));
				lista_de_compuestos.add(compuesto);
			}


		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}

		return lista_de_compuestos;

	}



}
