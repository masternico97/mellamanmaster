package es.uam.eps.ads.p5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tester del apartado 1 de la practica que prueba la funcionalidad de
 * IMatrix<T> e IMatrixElement<T>
 * @author <a href="mailto:nicolas.serranos@estudiante.uam.es">Nicolas Serrano</a>
 * @author <a href="mailto:alvaro.sanchezromero@estudiante.uam.es">Alvaro Sanchez</a>
 * Grupo de practicas: 2213
 */

public class TesterApartado1 {

	IMatrix<String> stringMatrix;
	IMatrix<Integer> intMatrix;

    @Before
	public void setUp() {
    	stringMatrix = new Matrix<>(4, 8);
    	intMatrix = new Matrix<>(6, 7);
	}
    
    /**
	 * Test get cols.
	 */
	@Test
	public void testGetCols() {
		assertEquals(stringMatrix.getCols(), 8);
		assertEquals(intMatrix.getCols(), 7);
	}
	
	/**
	 * Test get rows.
	 */
	@Test
	public void testGetRows() {
		assertEquals(stringMatrix.getRows(), 4);
		assertEquals(intMatrix.getRows(), 6);
	}
    
	/**
	 * Test isLegalPosition.
	 */
	@Test
	public void testIsLegalPosition() {
		assertTrue(stringMatrix.isLegalPosition(0, 0));
		assertFalse(stringMatrix.isLegalPosition(5, 6));
		assertFalse(stringMatrix.isLegalPosition(3, 9));
		assertTrue(intMatrix.isLegalPosition(3, 6));
	}
	
	/**
	 * Test addElement.
	 * @throws IllegalPositionException 
	 */
	@Test
	public void testAddElement() {
		try {
			IMatrixElement<String> elemento1 = new MatrixElement<>(0, 0, "Tester");
			
			stringMatrix.addElement(elemento1);
		} catch(IllegalPositionException e) {
			fail("IllegalPositionException");
		} try {
			IMatrixElement<Integer> elemento3 = new MatrixElement<>(10, 10, 12);
			intMatrix.addElement(elemento3);
			fail("Se debería haber producido IllegalPositionException");
		} catch(IllegalPositionException e) {	
		}		
	}
	
	/**
	 * Test getElementAt.
	 */
	@Test
	public void testGetElementAt() {
		try {
			IMatrixElement<String> elemento1 = new MatrixElement<>(0, 0, "Tester");
			
			stringMatrix.addElement(elemento1);
			assertSame(elemento1, stringMatrix.getElementAt(0, 0));
		} catch(IllegalPositionException e) {
			fail("IllegalPositionException");
		} try {
			IMatrixElement<Integer> elemento3;
			assertNull(intMatrix.getElementAt(0, 0));
			elemento3 = intMatrix.getElementAt(6, 7);
			fail("Se debería haber producido IllegalPositionException");
		} catch(IllegalPositionException e) {	
		}			
	}
	
	/**
	 * Test getNeighboursAt.
	 * @throws IllegalPositionException 
	 */
	@Test
	public void testGetNeighboursAt() throws IllegalPositionException {
		stringMatrix.addElement(new MatrixElement<>(1, 2, "N"));
		stringMatrix.addElement(new MatrixElement<>(3, 2, "S"));
		stringMatrix.addElement(new MatrixElement<>(2, 3, "E"));
		stringMatrix.addElement(new MatrixElement<>(2, 1, "W"));
		
		assertFalse(stringMatrix.getNeighboursAt(2, 2).isEmpty());
		assertTrue(intMatrix.getNeighboursAt(2, 2).isEmpty());

	}
	
	/**
	 * Test asList.
	 * @throws IllegalPositionException 
	 */
	@Test
	public void testAsList() throws IllegalPositionException {
		stringMatrix.addElement(new MatrixElement<>(0, 0, "Test"));
		
		assertFalse(stringMatrix.asList().isEmpty());
		assertTrue(intMatrix.asList().isEmpty());
	}
	
	/**
	 * Test getElementAt.
	 * @throws IllegalPositionException 
	 */
	@Test
	public void testToString() throws IllegalPositionException {
		stringMatrix.addElement(new MatrixElement<>(1, 2, "N"));
		stringMatrix.addElement(new MatrixElement<>(3, 2, "S"));
		stringMatrix.addElement(new MatrixElement<>(2, 3, "E"));
		stringMatrix.addElement(new MatrixElement<>(2, 1, "W"));
		
		for(int i = 0; i < intMatrix.getRows(); i+=2) {
			for (int j = 0; j < intMatrix.getCols(); j+=3 ) {
				intMatrix.addElement(new MatrixElement<>(i, j, (i*intMatrix.getCols()+j)));
			}
		}
		
		System.out.println(stringMatrix);
		System.out.println(intMatrix);	
	}
	
}
