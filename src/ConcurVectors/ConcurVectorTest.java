package ConcurVectors;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConcurVectorTest {

private ConcurVector vector1 ;
private ConcurVector vector2 ;
private ConcurVector mascara ;

	
@Before
public void setUp(){
	vector1 = new ConcurVector(5);
	mascara = new ConcurVector(5);
	vector2 = new ConcurVector(5);
	
	mascara.set(0,2);
	mascara.set(1,4);
	mascara.set(2,-4);
	mascara.set(3,40);
	mascara.set(4,-1);
	
	vector1.set(0,2);
	vector1.set(1,4);
	vector1.set(2,-4);
	vector1.set(3,40);
	vector1.set(4,-1);
	
	vector2.set(10);
}

@Test
public void dimensionTest()
{
	int esperado = 5;
	int Aevaluar = vector1.dimension();
	assertEquals(esperado,Aevaluar);
}


@Test
public void setAndGetTest()
{
	vector1.set(1,4);
	double esperado = 4;
	double Aevaluar = vector1.get(1);
	assertTrue(esperado==Aevaluar);
}
@Test
public void getTest()
{
	vector1.set(0);
	for (int i = 0; i < vector1.dimension(); ++i)
	{
		double esperado = 0;
		double Aesperar = vector1.get(i);
		assertTrue(esperado==Aesperar);
	}	
}

@Test
public void assignTest()
{
	
	vector1.assign(vector2);
	for (int i = 0; i < vector1.dimension(); ++i)
	{
		double esperado = 10;
		double Aesperar = vector1.get(i);
		assertTrue(esperado==Aesperar);
	}	
}

@Test
public void assignWithMask()
{	
	vector1.set(3);

	vector1.assign(mascara,vector2);
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==10);
	assertTrue(pos2 == 10);
	assertTrue(pos3 == 3);
	assertTrue(pos4 == 10);
	assertTrue(pos5 == 3);
	
}

@Test
public void absTest()
{

	
	vector1.abs();
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==2);
	assertTrue(pos2 == 4);
	assertTrue(pos3 == 4);
	assertTrue(pos4 == 40);
	assertTrue(pos5 == 1);	
}

@Test
public void addTest()
{
	vector1.add(vector2);
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==12);
	assertTrue(pos2 == 14);
	assertTrue(pos3 == 6);
	assertTrue(pos4 == 50);
	assertTrue(pos5 == 9);
}

@Test
public void subTest()
{
	vector1.sub(vector2);
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==-8);
	assertTrue(pos2 == -6);
	assertTrue(pos3 == -14);
	assertTrue(pos4 == 30);
	assertTrue(pos5 == -11);
}

@Test
public void mulTest()
{

	vector1.mul(vector2);
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==20);
	assertTrue(pos2 == 40);
	assertTrue(pos3 == -40);
	assertTrue(pos4 == 400);
	assertTrue(pos5 == -10);
}

@Test
public void divTest()
{
	vector1.div(vector2);
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==0.2);
	assertTrue(pos2 == 0.4);
	assertTrue(pos3 == -0.4);
	assertTrue(pos4 == 4);
	assertTrue(pos5 == -0.1);
}

@Test
public void sumTest()
{
	double aEvaluar = vector1.sum();
	
	double esperado = 41;
	
	assertTrue(esperado == aEvaluar);
}

@Test
public void prodTest()
{
	double aEvaluar = vector1.prod(vector2);
	
	double esperado = 410;
	
	assertTrue(esperado == aEvaluar);
	
	
}

@Test
public void normTest()
{
	double aEvaluar = vector1.norm();

	double esperado = 40.45985664828782;
	
	assertTrue(esperado == aEvaluar);
}

@Test
public void normalizeTest()
{
	vector1.normalize();
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==0.04943171245972855);
	assertTrue(pos2 == 0.0988634249194571);
	assertTrue(pos3 == -0.0988634249194571);
	assertTrue(pos4 == 0.988634249194571);
	assertTrue(pos5 == -0.024715856229864275);
}

@Test
public void maxTest()
{
	vector1.max(vector2);
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==10);
	assertTrue(pos2 == 10);
	assertTrue(pos3 == 10);
	assertTrue(pos4 == 40);
	assertTrue(pos5 == 10);
}

@Test
public void minTest()
{
	vector1.min(vector2);
	
	double pos1 = vector1.get(0);
	double pos2 = vector1.get(1);
	double pos3 = vector1.get(2);
	double pos4 = vector1.get(3);
	double pos5 = vector1.get(4);
	
	assertTrue(pos1==2);
	assertTrue(pos2 == 4);
	assertTrue(pos3 == -4);
	assertTrue(pos4 == 10);
	assertTrue(pos5 == -1);
}

}
