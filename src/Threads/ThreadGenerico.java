package Threads;

import ConcurVectors.ConcurVector;

public class ThreadGenerico extends Thread{
	
	private int inicio;
	private int fin;
	private ConcurVector vector1;
	private Operacion opEnum;
	private ConcurVector vector2;
	private double element;
	private ConcurVector mask;
	
	public ThreadGenerico(int inicio,int fin,ConcurVector vector1,Operacion opEnum,ConcurVector vector2,double element,ConcurVector mask)
	{
		this.inicio = inicio ;
		this.fin = fin;
		this.vector1 = vector1;
		this.opEnum = opEnum;
		this.vector2 = vector2;
		this.element = element;
		this.mask = mask;
	}
	
	public void run(){
		switch(opEnum){
		//aca tambien repito codigo, hay que mejorarlo..
			case Abs :
				while (inicio > fin){
					double e = Math.abs(vector1.get(inicio));
					vector1.set(inicio,e);
					this.inicio++;
					}
				break;	
			case Add :
				while (inicio > fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1+elementv2);
					this.inicio++;
			}
			case Assing :
				while (inicio > fin){
					double elem = vector2.get(inicio);
					vector1.set(inicio,elem);
					this.inicio++;
				}	
			case AssingWithMask :
				while (inicio > fin){
					double elem = vector2.get(inicio);
					double elemMask = mask.get(inicio);
					if (elemMask >= 0)
					{
						vector1.set(inicio,elem);
						this.inicio++;
					}	
				}
			case Div :
				while (inicio > fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1/elementv2);
					this.inicio++;
			}
			case Mul :
				while (inicio > fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1*elementv2);
					this.inicio++;
			}	
			case Set :
				while (inicio > fin){
					vector1.set(inicio,element);
					this.inicio++;
				}
				break;
			case Sub :
				while (inicio > fin){
					double elementv1 = vector1.get(inicio);
					double elementv2 = vector2.get(inicio);
					vector1.set(inicio,elementv1-elementv2);
					this.inicio++;
			}
			break;
			case Max :
				while (inicio > fin){
					vector1.set(inicio, Math.max(vector1.get(inicio), vector2.get(inicio)));
					this.inicio++;
				}
				break;
			case Min :
				while (inicio > fin){
					vector1.set(inicio, Math.min(vector1.get(inicio), vector2.get(inicio)));
					this.inicio++;
				}
				break;
		}
	}
}
