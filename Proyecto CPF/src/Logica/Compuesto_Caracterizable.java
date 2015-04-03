package Logica;

public class Compuesto_Caracterizable {

	String nombre;
	String formula;
	Double masa_molecular;
	Double punto_de_ebullicion_estandar;
	Double Densidad_Ideal_del_liquido;
	Double Presion_de_Vapor;
	Double Temperatura_critica_Tc;
	Double Presión_critica_Pc;
	Double Volumen_critico_Vc;
	Double factor_acentrico_w;
	Double Factor_de_compresibilidad_critico_Zc;
	Double fraccion_molar_alimento;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
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
	public Double getDensidad_Ideal_del_liquido() {
		return Densidad_Ideal_del_liquido;
	}
	public void setDensidad_Ideal_del_liquido(Double densidad_Ideal_del_liquido) {
		Densidad_Ideal_del_liquido = densidad_Ideal_del_liquido;
	}
	public Double getPresion_de_Vapor() {
		return Presion_de_Vapor;
	}
	public void setPresion_de_Vapor(Double presion_de_Vapor) {
		Presion_de_Vapor = presion_de_Vapor;
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
	public Double getFactor_de_compresibilidad_critico_Zc() {
		return Factor_de_compresibilidad_critico_Zc;
	}
	public void setFactor_de_compresibilidad_critico_Zc(
			Double factor_de_compresibilidad_critico_Zc) {
		Factor_de_compresibilidad_critico_Zc = factor_de_compresibilidad_critico_Zc;
	}
	public Double getFraccion_molar_alimento() {
		return fraccion_molar_alimento;
	}
	public void setFraccion_molar_alimento(Double fraccion_molar_alimento) {
		this.fraccion_molar_alimento = fraccion_molar_alimento;
	}
	public Compuesto_Caracterizable(String nombre, String formula,
			Double masa_molecular, Double punto_de_ebullicion_estandar,
			Double densidad_Ideal_del_liquido, Double presion_de_Vapor,
			Double temperatura_critica_Tc, Double presión_critica_Pc,
			Double volumen_critico_Vc, Double factor_acentrico_w,
			Double factor_de_compresibilidad_critico_Zc,
			Double fraccion_molar_alimento) {
		super();
		this.nombre = nombre;
		this.formula = formula;
		this.masa_molecular = masa_molecular;
		this.punto_de_ebullicion_estandar = punto_de_ebullicion_estandar;
		Densidad_Ideal_del_liquido = densidad_Ideal_del_liquido;
		Presion_de_Vapor = presion_de_Vapor;
		Temperatura_critica_Tc = temperatura_critica_Tc;
		Presión_critica_Pc = presión_critica_Pc;
		Volumen_critico_Vc = volumen_critico_Vc;
		this.factor_acentrico_w = factor_acentrico_w;
		Factor_de_compresibilidad_critico_Zc = factor_de_compresibilidad_critico_Zc;
		this.fraccion_molar_alimento = fraccion_molar_alimento;
	}
	public Compuesto_Caracterizable(String nombre, String formula,
			Double masa_molecular, Double punto_de_ebullicion_estandar,
			Double densidad_Ideal_del_liquido, Double presion_de_Vapor,
			Double temperatura_critica_Tc, Double presión_critica_Pc,
			Double volumen_critico_Vc, Double factor_acentrico_w,
			Double factor_de_compresibilidad_critico_Zc) {
		super();
		this.nombre = nombre;
		this.formula = formula;
		this.masa_molecular = masa_molecular;
		this.punto_de_ebullicion_estandar = punto_de_ebullicion_estandar;
		Densidad_Ideal_del_liquido = densidad_Ideal_del_liquido;
		Presion_de_Vapor = presion_de_Vapor;
		Temperatura_critica_Tc = temperatura_critica_Tc;
		Presión_critica_Pc = presión_critica_Pc;
		Volumen_critico_Vc = volumen_critico_Vc;
		this.factor_acentrico_w = factor_acentrico_w;
		Factor_de_compresibilidad_critico_Zc = factor_de_compresibilidad_critico_Zc;
	}
	
	

}
