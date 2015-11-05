package Threads;

import ConcurVectors.ConcurVector;
import ConcurVectors.ConcurVectorT;

public class ThreadGenerico extends Thread{
	
	private int inicio;
	private int fin;
	private ConcurVector vector1;
	private Operacion opEnum;
	private ConcurVector vector2;
	private double element;
	private ConcurVector mask;
	private ConcurVectorT concurVectorT;
	private int indice;
	
	public ThreadGenerico(int inicio,int fin,ConcurVector vector1,Operacion opEnum,ConcurVector vector2,double element,ConcurVector mask,ConcurVectorT vt,int indice)
	{
		this.inicio = inicio ;
		this.fin = fin;
		this.vector1 = vector1;
		this.opEnum = opEnum;
		this.vector2 = vector2;
		this.element = element;
		this.mask = mask;
		this.concurVectorT = vt;
		this.indice = indice;
	}
	
    public void run() {
		switch(opEnum){
		//aca tambien repito codigo, hay que mejorarlo..
			case Abs :
				while (inicio < fin){
					double e = Math.abs(vector1.get(inicio));
					vector1.set(inicio,e);
					this.inicio++;
					}
				break;	
			case Add :
				while (inicio < fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1+elementv2);
					this.inicio++;
				}
				break;
			case Assing :
				while (inicio < fin){
					double elem = vector2.get(inicio);
					vector1.set(inicio,elem);
					this.inicio++;
				}
				break;
			case AssingWithMask :
				while (inicio < fin){
					double elem = vector2.get(inicio);
					double elemMask = mask.get(inicio);
					if (elemMask >= 0)
					{
						vector1.set(inicio,elem);	
					}	
					this.inicio++;
				}
				break;
			case Div :
				while (inicio < fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1/elementv2);
					this.inicio++;
				}
				break;
			case Mul :
				while (inicio < fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1*elementv2);
					this.inicio++;
				}
				break;
			case Set :
				while (inicio < fin){
					vector1.set(inicio,element);
					this.inicio++;
				}
				break;
			case Sub :
				while (inicio < fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1-elementv2);
					this.inicio++;
				}
				break;
			case Max :
				while (inicio < fin){
					vector1.set(inicio, Math.max(vector1.get(inicio), vector2.get(inicio)));
					this.inicio++;
				}
				break;
			case Min :
				while (inicio < fin){
					vector1.set(inicio, Math.min(vector1.get(inicio), vector2.get(inicio)));
					this.inicio++;
				}
				break;
			case Sum :
				double res = 0;
				while (inicio < fin){
					res = res + vector1.get(inicio); 
					this.inicio++;
					}
				concurVectorT.AddAux(res,indice);
				break;	
		}
		concurVectorT.actualizar();
	}
}
