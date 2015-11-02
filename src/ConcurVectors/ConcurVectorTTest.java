package ConcurVectors;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConcurVectorTTest {

private ConcurVectorT vector1 ;
private ConcurVector vector2 ;
private ConcurVector mascara ;

	
@Before
public void setUp(){
	vector1 = new ConcurVectorT(5,2,1);
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
	Integer esperado = 5;
	Integer Aevaluar = vector1.dimension();
	assertEquals(esperado,Aevaluar);
}


@Test
public void setAndGetTest()
{
	vector1.set(1,4);
	Double esperado = 4d;
	Double Aevaluar = vector1.get(1);
	assertEquals(esperado,Aevaluar);
}
@Test
public void getTest()
{
	vector1.set(0);
	for (int i = 0; i < vector1.dimension(); ++i)
	{
		Double esperado = 0d;
		Double Aesperar = vector1.get(i);
		assertEquals(esperado,Aesperar);
	}	
}

@Test
public void assignTest()
{
	
	vector1.assign(vector2);
	for (int i = 0; i < vector1.dimension(); ++i)
	{
		Double esperado = 10d;
		Double Aesperar = vector1.get(i);
		assertEquals(esperado,Aesperar);
	}	
}

@Test
public void assignWithMask()
{	
	vector1.set(3);

	vector1.assign(mascara,vector2);
	
	Double pos1 = vector1.get(0);
	Double pos2 = vector1.get(1);
	Double pos3 = vector1.get(2);
	Double pos4 = vector1.get(3);
	Double pos5 = vector1.get(4);
	
	assertEquals(pos1,Double.valueOf(10));
	assertEquals(pos2,Double.valueOf(10));
	assertEquals(pos3,Double.valueOf(3));
	assertEquals(pos4,Double.valueOf(10));
	assertEquals(pos5,Double.valueOf(3));
	
}

@Test
public void absTest()
{

	
	vector1.abs();
	
	Double pos1 = vector1.get(0);
	Double pos2 = vector1.get(1);
	Double pos3 = vector1.get(2);
	Double pos4 = vector1.get(3);
	Double pos5 = vector1.get(4);
	
	assertEquals(pos1,Double.valueOf(2));
	assertEquals(pos2,Double.valueOf(4));
	assertEquals(pos3,Double.valueOf(4));
	assertEquals(pos4,Double.valueOf(40));
	assertEquals(pos5,Double.valueOf(1));
	
}

@Test
public void addTest()
{
	vector1.add(vector2);
	
	Double pos1 = vector1.get(0);
	Double pos2 = vector1.get(1);
	Double pos3 = vector1.get(2);
	Double pos4 = vector1.get(3);
	Double pos5 = vector1.get(4);
	
	assertEquals(pos1,Double.valueOf(12));
	assertEquals(pos2,Double.valueOf(14));
	assertEquals(pos3,Double.valueOf(6));
	assertEquals(pos4,Double.valueOf(50));
	assertEquals(pos5,Double.valueOf(9));
}

@Test
public void subTest()
{
	vector1.sub(vector2);
	
	Double pos1 = vector1.get(0);
	Double pos2 = vector1.get(1);
	Double pos3 = vector1.get(2);
	Double pos4 = vector1.get(3);
	Double pos5 = vector1.get(4);

	assertEquals(pos1,Double.valueOf(-8));
	assertEquals(pos2,Double.valueOf(-6));
	assertEquals(pos3,Double.valueOf(-14));
	assertEquals(pos4,Double.valueOf(30));
	assertEquals(pos5,Double.valueOf(-11));
}

@Test
public void mulTest()
{

	vector1.mul(vector2);
	
	Double pos1 = vector1.get(0);
	Double pos2 = vector1.get(1);
	Double pos3 = vector1.get(2);
	Double pos4 = vector1.get(3);
	Double pos5 = vector1.get(4);
	
	assertEquals(pos1,Double.valueOf(20));
	assertEquals(pos2,Double.valueOf(40));
	assertEquals(pos3,Double.valueOf(-40));
	assertEquals(pos4,Double.valueOf(400));
	assertEquals(pos5,Double.valueOf(-10));

}

@Test
public void divTest()
{
	vector1.div(vector2);
	
	Double pos1 = vector1.get(0);
	Double pos2 = vector1.get(1);
	Double pos3 = vector1.get(2);
	Double pos4 = vector1.get(3);
	Double pos5 = vector1.get(4);
	
	assertEquals(pos1,Double.valueOf(0.2));
	assertEquals(pos2,Double.valueOf(0.4));
	assertEquals(pos3,Double.valueOf(-0.4));
	assertEquals(pos4,Double.valueOf(4));
	assertEquals(pos5,Double.valueOf(-0.1));

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