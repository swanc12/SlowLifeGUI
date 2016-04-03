import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.Mockito;

public class PinningTests{
	
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
}