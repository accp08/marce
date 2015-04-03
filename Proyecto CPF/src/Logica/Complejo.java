package Logica;

public class Complejo{
    private double real;
    private double imag;

 public Complejo() {
    real=0.0;
    imag=0.0;
 }
 public Complejo(double real, double imag){
    this.real=real;
    this.imag=imag;
 }
 public static Complejo conjugado(Complejo c){
    return new Complejo(c.real, -c.imag);
 }
 public static Complejo opuesto(Complejo c){
    return new Complejo(-c.real, -c.imag);
 }
 public double modulo(){
    return Math.sqrt(real*real+imag*imag);
 }
//devuelve el ángulo en grados
 public double argumento(){
    double angulo=Math.atan2(imag, real); 
    if(angulo<0)  angulo=2*Math.PI+angulo;
    return angulo*180/Math.PI;
 }
//suma de dos números complejos
 public static Complejo suma(Complejo c1, Complejo c2){
    double x=c1.real+c2.real;
    double y=c1.imag+c2.imag;
    return new Complejo(x, y);
 }
//producto de dos números complejos
public static Complejo producto(Complejo c1, Complejo c2){
    double x=c1.real*c2.real-c1.imag*c2.imag;
    double y=c1.real*c2.imag+c1.imag*c2.real;
    return new Complejo(x, y);
 }
//producto de un complejo por un número real
 public static Complejo producto(Complejo c, double d){
    double x=c.real*d;
    double y=c.imag*d;
    return new Complejo(x, y);
}
//producto de un número real  por un complejo
 public static Complejo producto(double d, Complejo c){
    double x=c.real*d;
    double y=c.imag*d;
    return new Complejo(x, y);
}
//cociente de dos números complejos
//excepción cuando el complejo denominador es cero
 public static Complejo cociente(Complejo c1, Complejo c2)
   throws ExcepcionDivideCero{
    double aux, x, y;
    if(c2.modulo()==0.0){
         throw new ExcepcionDivideCero("Divide entre cero");
    }else{
         aux=c2.real*c2.real+c2.imag*c2.imag;
         x=(c1.real*c2.real+c1.imag*c2.imag)/aux;
         y=(c1.imag*c2.real-c1.real*c2.imag)/aux;
    }
    return new Complejo(x, y);
 }
//cociente entre un número complejo y un número real
 public static Complejo cociente(Complejo c, double d)
    throws ExcepcionDivideCero{
   double x, y;
   if(d==0.0){
         throw new ExcepcionDivideCero("Divide entre cero");
   }else{
       x=c.real/d;
       y=c.imag/d;
   }
    return new Complejo(x, y);
 }
//el número e elevado a un número complejo
 public static Complejo exponencial(Complejo c){
    double x=Math.cos(c.imag)*Math.exp(c.real);
    double y=Math.sin(c.imag)*Math.exp(c.real);
    return new Complejo(x, y);
 }
//raíz cuadrada de un número positivo o negativo
 public static Complejo csqrt(double d){
    if(d>=0) return new Complejo(Math.sqrt(d), 0);
    return new Complejo(0, Math.sqrt(-d));
 }
//función auxiliar  para la potencia de un número complejo
 private static double potencia(double base, int exponente){
   double resultado=1.0;
   for(int i=0; i<exponente; i++){
       resultado*=base;
   }
   return resultado;
 }
//función auxiliar para la potencia de un número complejo
 private static double combinatorio(int m, int n){
   long num=1;
   long den=1;
   for(int i=m; i>m-n; i--){
       num*=i;
   }
   for(int i=2; i<=n; i++){
       den*=i;
   }
   return (double)num/den;
 }
//potencia de un número complejo
 public static Complejo potencia(Complejo c, int exponente){
   double x=0.0, y=0.0;
   int signo;
   for(int i=0; i<=exponente; i++){
       signo=(i%2==0)?+1:-1;
//parte real
       x+=combinatorio(exponente, 2*i)*potencia(c.real, exponente-2*i)
          *potencia(c.imag, 2*i)*signo;
       if(exponente==2*i)  break;
//parte imaginaria
       y+=combinatorio(exponente, 2*i+1)*potencia(c.real, exponente-(2*i+1))
          *potencia(c.imag, 2*i+1)*signo;
   }
   return new Complejo(x, y);
 }
//representa un número complejo como un string
 public String toString(){
    if(imag>0)     return new String((double)Math.round(100*real)/100
        +" + "+(double)Math.round(100*imag)/100+"*i");
    return new String((double)Math.round(100*real)/100+" - "
       +(double)Math.round(-100*imag)/100+"*i");
 }
}

class ExcepcionDivideCero extends Exception {

 public ExcepcionDivideCero() {
        super();
 }
 public ExcepcionDivideCero(String s) {
        super(s);
 }
}