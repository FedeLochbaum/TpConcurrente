package ConcurVectors;
import java.util.ArrayList;
import java.util.List;

import Threads.Operacion;
import Threads.ThreadGenerico;


public class ConcurVectorT {

	private int threads;
	private int load;
	private ConcurVector vector;
	
	
	public ConcurVectorT(int dimension,int threads,int load)
	{
		this.threads = threads;
		this.load = load;
		this.vector = new ConcurVector(dimension);
	}

	public int dimension() { return vector.dimension();}

	public double get(int i) { return vector.get(i);}

	public void set(int i, double d) { vector.set(i,d);}
	
	
	public void aplicarOpConThread(Operacion opEnum,ConcurVector vector2,double setElem,ConcurVector mask){
		List<Integer> list = this.calcularDivisionDeSubtareas();
		int inicio = 0;
		for (int i : list)
		{
			int fin = inicio + i;
			ThreadGenerico thread = new ThreadGenerico(inicio,fin,this.vector,opEnum,vector2,setElem,mask);
			thread.start();
			inicio = i;
		}
	}
	//para no tener que castear el double, cuando no se usa.. en ves de null se pone 0 .
	public void set(double d) {
		Operacion op = Operacion.Set;
		this.aplicarOpConThread(op,null, d, null);
	}
	public void assign(ConcurVector v) {
		Operacion op = Operacion.Assing;
		this.aplicarOpConThread(op, v,0, null);
	}
	public void assign(ConcurVector mask, ConcurVector v) {
		Operacion op = Operacion.AssingWithMask;
		this.aplicarOpConThread(op, v,0, mask);
	}
	public void abs() {
		Operacion op = Operacion.Abs;
		this.aplicarOpConThread(op, null, 0, null);
	}
	public void add(ConcurVector v) {
		Operacion op = Operacion.Add;
		this.aplicarOpConThread(op, v, 0, null);
	}
	public void sub(ConcurVector v) {
		Operacion op = Operacion.Sub;
		this.aplicarOpConThread(op,v, 0, null);
	}
	public void mul(ConcurVector v) {
		Operacion op = Operacion.Mul;
		this.aplicarOpConThread(op, v,0, null);
	}
	public void div(ConcurVector v) {
		Operacion op = Operacion.Div;
		this.aplicarOpConThread(op, v,0, null);
	}
	
	public double sum() {
		return 2 ; //falta
	}
	
	public double prod(ConcurVector v) {
		return 2; //falta
	}
	
	public double norm() {
		return 2; //falta
	}
		
	public void normalize() {
		//falta
	}
	
	public void max(ConcurVector v) {
		//falta
	}
	
	public void min(ConcurVector v) {
		//falta
	}
	
	public List<Integer> calcularDivisionDeSubtareas()
	{
		int cantElem = this.dimension();
		int cantThread = this.threads;
		int promedio = cantElem / cantThread;
		int resto = cantElem % cantThread;
		List<Integer> resultado = new ArrayList<Integer>();
		
		for(int i=0; i!=(cantThread-1); i++){ resultado.add(promedio);}
		
		if (resto <= this.load)
			resultado.add(resto);
		else{
			resultado.add(promedio);
			if(resto <= cantThread)
			{
				for(int i=0; i!=resto; i++){
					resultado.add(resultado.get(i)+1);
				}
			}
			//nose.. si se puede llegar a esto(else)
		}
		return resultado;	
	}
	
}
