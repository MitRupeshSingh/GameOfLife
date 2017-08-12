package gameOfLife;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {

	@Test
	public void gameOfLifetest() {
		GameOfLife g1= new GameOfLife();
		
		//A live cell with two or three live neighbors survive
		String[] case0={"OO",
					   "OO"};
		String[] case0expected = {"OO",
		   "OO"};
		assertArrayEquals(case0expected,g1.life(case0));
		
		//a live cell with fewer than two live neighbors dies
		String[] casel={"O.O",
		   "..O"};
		String[] caselexpected={".O.",
		   ".O."};
		assertArrayEquals(caselexpected,g1.life(casel));
		
		//live cell with more than three live neighbors dies
		String[] case2={"OOO",
		   "OOO"};
		String[] case2expected={"O.O",
		   "O.O"};
		assertArrayEquals(case2expected,g1.life(case2));
		
		//a dead cell with exactly three live neighbors survives 
		String[] case3={"O..O",
		   "O.OO",
		   "OOOO"};
		String[] case3expected={".OOO",
		   "O...",
		   "O..O"};
		assertArrayEquals(case3expected,g1.life(case3));
	}

}
