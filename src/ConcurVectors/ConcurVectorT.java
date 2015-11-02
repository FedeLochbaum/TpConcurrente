package ConcurVectors;
import java.util.ArrayList;
import java.util.List;

import Threads.MutexMediador;
import Threads.Operacion;
import Threads.ThreadGenerico;


public class ConcurVectorT {

	private int threads;
	private int load;
	private ConcurVector vector;
	private int cantidadDeThreadFinalizado;
	private int cantidadDeThreadAsignado;
	private MutexMediador mutex;
	private ConcurVectorT vectorAux;
	
	public ConcurVectorT(int dimension,int threads,int load)
	{
		this.threads = threads;
		this.load = load;
		this.vector = new ConcurVector(dimension);
		this.cantidadDeThreadFinalizado = 0;
		this.mutex=new MutexMediador();
		
	}

	public int dimension() { return vector.dimension();}

	public double get(int i) { return vector.get(i);}

	public void set(int i, double d) { vector.set(i,d);}
	
	
	public void aplicarOpConThread(Operacion opEnum,ConcurVector vector2,double setElem,ConcurVector mask){
		List<Integer> list = this.calcularDivisionDeSubtareas();
		vectorAux = new ConcurVectorT(list.size(),threads,load);
		cantidadDeThreadAsignado = list.size();
		int inicio = 0;
		for (int i : list)
		{
			int fin = inicio + i;
			ThreadGenerico thread = new ThreadGenerico(inicio,fin,this.vector,opEnum,vector2,setElem,mask,this);
			thread.start();
			inicio=fin;
		}
		mutex.waitForCondition();
			
	}
	//para no tener que castear el double, cuando no se usa.. en ves de null se pone 0 .
	public synchronized void set(double d){
		Operacion op = Operacion.Set;
		this.aplicarOpConThread(op,null, d, null);
	}
	public synchronized void assign(ConcurVector v) {
		Operacion op = Operacion.Assing;
		this.aplicarOpConThread(op, v,0, null);
		}
	public synchronized void assign(ConcurVector mask, ConcurVector v) {
		Operacion op = Operacion.AssingWithMask;
		this.aplicarOpConThread(op, v,0, mask);
	}
	public synchronized void abs() {
		Operacion op = Operacion.Abs;
		this.aplicarOpConThread(op, null, 0, null);
	}
	public synchronized void add(ConcurVector v) {
		Operacion op = Operacion.Add;
		this.aplicarOpConThread(op, v, 0, null);
	}
	public synchronized void sub(ConcurVector v) {
		Operacion op = Operacion.Sub;
		this.aplicarOpConThread(op,v, 0, null);
	}
	public synchronized void mul(ConcurVector v) {
		Operacion op = Operacion.Mul;
		this.aplicarOpConThread(op, v,0, null);
	}
	public synchronized void div(ConcurVector v) {
		Operacion op = Operacion.Div;
		this.aplicarOpConThread(op, v,0, null);
	}
	
	public synchronized double sum() {
		vectorAux = new ConcurVectorT(dimension(),threads,load);
        double resultado  = 0;
        Operacion op = Operacion.Sum;
        if(this.dimension()==1)
            resultado = vectorAux.get(0);
        else 
        {
            this.aplicarOpConThread(op, null,0, null);
            if(this.dimension() == 2)
                resultado = vectorAux.get(0) + vectorAux.get(1);
            else
                resultado = vectorAux.sum();
        }
        
        return resultado ; 
	}
	
	public synchronized double prod(ConcurVector v) {
		ConcurVectorT aux = new ConcurVectorT(dimension(),threads,load);
		aux.assign(vector);
		aux.mul(v);
		return aux.sum();
	}
	
	public synchronized double norm() {
		ConcurVectorT aux = new ConcurVectorT(dimension(),threads,load);
		aux.assign(vector);
		aux.mul(vector);
		return Math.sqrt(aux.sum());
	}
		
	public synchronized void normalize() {
		ConcurVector aux = new ConcurVector(dimension());
		aux.set(norm());
		div(aux);
	}
	
	public synchronized void max(ConcurVector v) {
		Operacion op = Operacion.Max;
		this.aplicarOpConThread(op, v, 0, null);
	}
	
	public synchronized void min(ConcurVector v) {
		Operacion op = Operacion.Min;
		this.aplicarOpConThread(op, v, 0, null);
	}
	
	public  List<Integer> calcularDivisionDeSubtareas()
	{
		int cantElem = this.dimension();
		int cantThread = this.threads;
		int promedio = cantElem / cantThread;
		int resto = cantElem % cantThread;
		List<Integer> resultado = new ArrayList<Integer>();
		
		for(int i=0; i<(cantThread); i++){ 
			if(resto>0){
				resultado.add(promedio+1);
				resto--;
			}
			else{
			resultado.add(promedio);
			}
		}
		return resultado;	
	}

	public  void actualizar() {//le saque este synchronized y anda.. pero no entiedo porque.
		cantidadDeThreadFinalizado++;
		if(cantidadDeThreadFinalizado == cantidadDeThreadAsignado)
		{
			mutex.releaseCondition();
			cantidadDeThreadFinalizado = 0;
		}
	}

	public  void AddAux(double res) {
		//vectorAux.set(res);
		for(int i=0;i<vectorAux.dimension();i++){
            if(vectorAux.get(i)==0){
                vectorAux.set(i,res);
                break;
            }
        }
	}
	
}
