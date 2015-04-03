package Logica;

public class Fracciones_No_Caracterizables {

	String nombre;
	Double masa_molecular;
	Double punto_de_ebullicion_estandar;
	Double gravedad_especifica;
	Double Temperatura_critica_Tc;
	Double Presión_critica_Pc;
	Double Volumen_critico_Vc;
	Double factor_acentrico_w;
	Double fraccion_molar_alimento;
	
	public Fracciones_No_Caracterizables(String nombre, Double masa_molecular,
			Double punto_de_ebullicion_estandar, Double gravedad_especifica,
			Double temperatura_critica_Tc, Double presión_critica_Pc,
			Double volumen_critico_Vc, Double factor_acentrico_w) {
		super();
		this.nombre = nombre;
		this.masa_molecular = masa_molecular;
		this.punto_de_ebullicion_estandar = punto_de_ebullicion_estandar;
		this.gravedad_especifica = gravedad_especifica;
		Temperatura_critica_Tc = temperatura_critica_Tc;
		Presión_critica_Pc = presión_critica_Pc;
		Volumen_critico_Vc = volumen_critico_Vc;
		this.factor_acentrico_w = factor_acentrico_w;
	}
	public Fracciones_No_Caracterizables(String nombre, Double masa_molecular,
			Double punto_de_ebullicion_estandar, Double gravedad_especifica,
			Double temperatura_critica_Tc, Double presión_critica_Pc,
			Double volumen_critico_Vc, Double factor_acentrico_w,
			Double fraccion_molar_alimento) {
		super();
		this.nombre = nombre;
		this.masa_molecular = masa_molecular;
		this.punto_de_ebullicion_estandar = punto_de_ebullicion_estandar;
		this.gravedad_especifica = gravedad_especifica;
		Temperatura_critica_Tc = temperatura_critica_Tc;
		Presión_critica_Pc = presión_critica_Pc;
		Volumen_critico_Vc = volumen_critico_Vc;
		this.factor_acentrico_w = factor_acentrico_w;
		this.fraccion_molar_alimento = fraccion_molar_alimento;
	}
	public Fracciones_No_Caracterizables() {
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getMasa_molecular() {
		return masa_molecular;
	}
	public void setMasa_molecular(Double masa_molecular) {
		this.masa_molecular = masa_molecular;
	}
	public Double getPunto_de_ebullicion_estandar() {
		return punto_de_ebullicion_estandar;
	}
	public void setPunto_de_ebullicion_estandar(Double punto_de_ebullicion_estandar) {
		this.punto_de_ebullicion_estandar = punto_de_ebullicion_estandar;
	}
	public Double getGravedad_especifica() {
		return gravedad_especifica;
	}
	public void setGravedad_especifica(Double gravedad_especifica) {
		this.gravedad_especifica = gravedad_especifica;
	}
	public Double getTemperatura_critica_Tc() {
		return Temperatura_critica_Tc;
	}
	public void setTemperatura_critica_Tc(Double temperatura_critica_Tc) {
		Temperatura_critica_Tc = temperatura_critica_Tc;
	}
	public Double getPresión_critica_Pc() {
		return Presión_critica_Pc;
	}
	public void setPresión_critica_Pc(Double presión_critica_Pc) {
		Presión_critica_Pc = presión_critica_Pc;
	}
	public Double getVolumen_critico_Vc() {
		return Volumen_critico_Vc;
	}
	public void setVolumen_critico_Vc(Double volumen_critico_Vc) {
		Volumen_critico_Vc = volumen_critico_Vc;
	}
	public Double getFactor_acentrico_w() {
		return factor_acentrico_w;
	}
	public void setFactor_acentrico_w(Double factor_acentrico_w) {
		this.factor_acentrico_w = factor_acentrico_w;
	}
	public Double getFraccion_molar_alimento() {
		return fraccion_molar_alimento;
	}
	public void setFraccion_molar_alimento(Double fraccion_molar_alimento) {
		this.fraccion_molar_alimento = fraccion_molar_alimento;
	}
	
	
	
	

}
