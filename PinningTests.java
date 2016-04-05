import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.Mockito;

public class PinningTests{
	
	/*
	 * Pinning the getNumNeighbors method. The original implementation
	 * of getNumNeighbors returns the number of alive neighbors surrounding
	 * a cell. If no cells are alive then the number of neighbors should be
	 * 0.
	 */
	@Test
	public void testGetNumNeighborsNone(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    Cell mockedCell = Mockito.mock(Cell.class);
		    when(mockedCell.getAlive()).thenReturn(false);
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		assertEquals(testPanel.getNumNeighbors(2,2), 0);
		
	}
	
	/*
	 * Pinning the getNumNeighbors method. The original implementation
	 * of getNumNeighbors returns the number of alive neighbors surrounding
	 * a cell. If every cell is alive then the number of neighbors should be
	 * 8.
	 */
	@Test
	public void testGetNumNeighborsSurrounded(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    Cell mockedCell = Mockito.mock(Cell.class);
		    when(mockedCell.getAlive()).thenReturn(true);
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		assertEquals(testPanel.getNumNeighbors(2,2), 8);
		
	}
	
	/*
	 * Pinning the getNumNeighbors method. The original implementation
	 * of getNumNeighbors returns the number of alive neighbors surrounding
	 * a cell. If every even cell in a row is alive then the number of returned
	 * neighbors at 2,2 should be 2.
	 */
	@Test
	public void testGetNumNeighborsSomeNeighbs(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	
		    Cell mockedCell = Mockito.mock(Cell.class);
		    if(k%2 == 0){
		    	when(mockedCell.getAlive()).thenReturn(true);
		    }
		    else{
		    	when(mockedCell.getAlive()).thenReturn(false);
		    }
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		assertEquals(testPanel.getNumNeighbors(2,2), 2);
		
	}
	
	/*
	 * Pinning test for the runContinuous method. Method
	 * should be calling the setAlive method on every
	 * cell in the panel. If every cell is dead then
	 * the call should be setAlive(false).
	 */
	@Test
	public void testRunContinuousSetAliveAllDead(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	
		    Cell mockedCell = Mockito.mock(Cell.class);
			when(mockedCell.getAlive()).thenReturn(false);
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		testPanel.runContinuous(true);
		testCells = testPanel.getCells();
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	//System.out.println(testCells[j][k].getAlive());
		    	verify(testCells[j][k]).setAlive(false);
		    }
		}
	}
	
	/*
	 * Pinning test for the runContinuous method. Method
	 * should be calling the backup method, which
	 * should call the getAlive method on every cell. Since
	 * every cell is dead in the original cell matrix, the backup
	 * cell matrix should return false for each cell when getAlive()
	 * is called on a cell.
	 */
	@Test
	public void testRunContinuousBackedUpAllDead(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	
		    Cell mockedCell = Mockito.mock(Cell.class);
			when(mockedCell.getAlive()).thenReturn(false);
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		testPanel.runContinuous(true);
		testCells = testPanel.getBackupCells();
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	assertEquals(testCells[j][k].getAlive(), false);
		    }
		}
	}
	
	/*
	 * Pinning test for the runContinuous method. Method
	 * should be calling the backup method, which
	 * should call the getAlive method on every cell. Since
	 * every cell is alive from the original cell matrix, every
	 * cell in the backup matrix should also return true when 
	 * getAlive is called.
	 */
	@Test
	public void testRunContinuousBackedUpAllAlive(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	
		    Cell mockedCell = Mockito.mock(Cell.class);
			when(mockedCell.getAlive()).thenReturn(true);
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		testPanel.runContinuous(true);
		testCells = testPanel.getBackupCells();
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	assertEquals(testCells[j][k].getAlive(), true);
		    }
		}
	}
	
	/*
	 * Pinning the Cell toString method. All this method does
	 * is return "X" if the Cell is alive or "." if the cell is Dead.
	 * This method tests that "X" is returned if the Cell is alive.
	 */
	@Test
	public void testCellToStringAlive(){
		Cell testCell = new Cell();
		testCell.setAlive(true);
		assertEquals("X", testCell.toString());
	}
	
	/*
	 * Pinning the Cell toString method. All this method does
	 * is return "X" if the Cell is alive or "." if the cell is Dead.
	 * This method tests that "." is returned if the Cell is alive.
	 */
	@Test
	public void testCellToStringDead(){
		Cell testCell = new Cell();
		testCell.setAlive(false);
		assertEquals(".", testCell.toString());
	}
	
	/*
	 * Pinning the MainPanel toString method. This method
	 * creates a string representation of a MainPanel. If
	 * every cell is alive then the method should return
	 * all X's, with \n characters separating each row.
	 */
	@Test
	public void testMainPanelToStringAllAlive(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	
		    Cell mockedCell = Mockito.mock(Cell.class);
			when(mockedCell.getAlive()).thenReturn(true);
			when(mockedCell.toString()).thenReturn("X");
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		assertEquals(testPanel.toString(), "XXXXX\nXXXXX\nXXXXX\nXXXXX\nXXXXX\n");
		
	}
	
	/*
	 * Pinning the MainPanel toString method. This method
	 * creates a string representation of a MainPanel. If
	 * every cell is dead then the method should return
	 * all .'s, with \n characters separating each row.
	 */
	@Test
	public void testMainPanelToStringAllDead(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	
		    Cell mockedCell = Mockito.mock(Cell.class);
			when(mockedCell.getAlive()).thenReturn(false);
			when(mockedCell.toString()).thenReturn(".");
			testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		assertEquals(testPanel.toString(), ".....\n.....\n.....\n.....\n.....\n");
		
	}
	
	/*
	 * Pinning the MainPanel toString method. This method
	 * creates a string representation of a MainPanel. If each
	 * even number in a row is alive then a sequence of ".X.X."
	 * should be returned for each row, seperated by \n characters.
	 */
	@Test
	public void testMainPanelToStringSomeDead(){
		Cell[][] testCells = new Cell[5][5];
		for (int j = 0; j < 5; j++) {
		    for (int k = 0; k < 5; k++) {
		    	
		    Cell mockedCell = Mockito.mock(Cell.class);
		    if(k%2 == 0){
		    	when(mockedCell.getAlive()).thenReturn(false);
		    	when(mockedCell.toString()).thenReturn(".");
		    }
		    else{
		    	when(mockedCell.getAlive()).thenReturn(true);
				when(mockedCell.toString()).thenReturn("X");
		    }
		    testCells[j][k] = mockedCell;
		    }
		}
		MainPanel testPanel = new MainPanel(5);
		testPanel.setCells(testCells);
		assertEquals(testPanel.toString(), ".X.X.\n.X.X.\n.X.X.\n.X.X.\n.X.X.\n");
	}
	
}