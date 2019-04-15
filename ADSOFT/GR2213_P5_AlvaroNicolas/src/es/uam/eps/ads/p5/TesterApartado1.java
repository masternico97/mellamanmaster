package es.uam.eps.ads.p5;

import static org.junit.Assert.*;

import java.util.List;

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
	public void testAddElement() throws IllegalPositionException {
		IMatrixElement<String> elemento1 = new MatrixElement<>(0, 0, "Tester");
		IMatrixElement<String> elemento2;
		stringMatrix.addElement(elemento1);
		elemento2 = stringMatrix.getElementAt(0, 0);
		assertSame(elemento1, elemento2);
		
	}
	
	/**
	 * Test getElementAt.
	 */
	@Test
	public void testGetElementAt() {
	
	}
	
	/**
	 * Test getNeighboursAt.
	 */
	@Test
	public void testGetNeighboursAt() {
	
	}
	
	/**
	 * Test asList.
	 */
	@Test
	public void testAsList() {
	
	}
	
	
    
    //void addElement(IMatrixElement<T> element) throws IllegalPositionException;
    //IMatrixElement<T> getElementAt(int i, int j) throws IllegalPositionException;
    //List<IMatrixElement<T>> getNeighboursAt(int i, int j) throws IllegalPositionException;
    //List<IMatrixElement<T>> asList();
}
