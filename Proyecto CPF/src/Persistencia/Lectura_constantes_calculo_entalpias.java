package Persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

import Logica.Objeto_Compuestos_Caracterizables;
import Logica.Objeto_constante_calculo_entalpia;

public class Lectura_constantes_calculo_entalpias {

	public Lectura_constantes_calculo_entalpias() {
		// TODO Auto-generated constructor stub
	
	}

	public static LinkedList<Objeto_constante_calculo_entalpia> carga(){
		  File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      LinkedList<Objeto_constante_calculo_entalpia> lista_constantes_entalpia = new LinkedList<Objeto_constante_calculo_entalpia>();
	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("Constantes_calculo_entalpias.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	 
	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null){
	        	 String[] vec = linea.split("\t");
	        	 Objeto_constante_calculo_entalpia compuesto = new Objeto_constante_calculo_entalpia(vec[0],  Double.parseDouble(vec[1]), Double.parseDouble(vec[2]), Double.parseDouble(vec[3]), Double.parseDouble(vec[4]), Double.parseDouble(vec[5]), Double.parseDouble(vec[6]));
	        	 lista_constantes_entalpia.add(compuesto);
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
	      
	      return lista_constantes_entalpia;

	}
	
	
}
