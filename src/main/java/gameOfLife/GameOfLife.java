package gameOfLife;

import java.rmi.RemoteException;
import java.util.Scanner;

public class GameOfLife{
	public static void main(String[] args) throws RemoteException{
		
        Scanner numState = new Scanner(System.in);
        System.out.println("Enter Number Of States or Generation to be shown");
        int states = numState.nextInt();
        numState.nextLine();
        System.out.println("Enter the states of each cell having 8 rows and fixed number of columns");
        System.out.println("Live cell is denoted by O or shift(o) and dead by .");
		String[] cells= new String[3];
		for(int i=0;i<cells.length;i++){
			cells[i]=numState.nextLine();
		}
		numState.close();
		int col=cells[0].length();
		for(int i= 1;i < cells[0].length();i++){
			if(col!=cells[i].length()){
				throw new RemoteException("Columns should be of same length");
			}
		}

		print(cells);
		for(int i=1;i<=states;i++){
			System.out.println("State " + i + ":");			
			cells= life(cells);
			print(cells);
		}

	}
 
	public static String[] life(String[] cells){
		String[] nextState= new String[cells.length];
		for(int row= 0;row < cells.length;row++){//each row
			nextState[row]= "";
			String[] Neighbors= new String[3]; 
			for(int i= 0;i < cells[row].length();i++){//each char in the row
				
				if(i == cells[row].length() - 1){//cells in the mid between first cell and last cell

					Neighbors=cellAtMid(cells,row,i);
					
				}else{//anywhere else
					
					Neighbors=cellAtCorner(cells, row, i);
				}
				int neighbors= FindNeighbors(Neighbors);

				if(neighbors < 2 || neighbors > 3){
					nextState[row]+= ".";//cell dies having less than 2 or greater than 3 neighbors
				}else if(neighbors == 3){
					nextState[row]+= "O";//3 neighbors: dead->live, live ->live
				}else{
					nextState[row]+= cells[row].charAt(i);//2 neighbors: live cell stay live
				}
			}
		}
		return nextState;
	}
	public static String[] cellAtCorner(String[] cell, int row, int i){
		
		String[] neighbors = new String[3];
		if(i==0){
			neighbors[0]= (row == 0) ? null : cell[row - 1].substring(i,
							i + 2);
			
			//grab neighbors on the same row
			neighbors[1]= cell[row].substring(i + 1, i + 2);
	
			//if not the cell on the last row, grab the neighbors from below
			neighbors[2]= (row == cell.length - 1) ? null : cell[row + 1]
							.substring(i, i + 2);
		}else{
			neighbors[0]= (row == 0) ? null : cell[row - 1].substring(i - 1,
					i + 2);
	
			//grab neighbors on the same row
			neighbors[1]= cell[row].substring(i - 1, i)
					+ cell[row].substring(i + 1, i + 2);
	
			//if not the cell on the last row, grab the neighbors from below
			neighbors[2]= (row == cell.length - 1) ? null : cell[row + 1]
					.substring(i - 1, i + 2);
		}
		//if not the cell on the first row, grab the neighbors from above

		return neighbors;
		
	}
	
	public static String[] cellAtMid(String[] cell, int row, int i){
		
		String[] neighbors = new String[3];
		//if not the cell on the first row, grab the neighbors from above
		neighbors[0]= (row == 0) ? null : cell[row - 1].substring(i - 1,
						i + 1);
		
		//grab neighbors on the same row
		neighbors[1]= cell[row].substring(i - 1, i);

		//if not the cell on the last row, grab the neighbors from below
		neighbors[2]= (row == cell.length - 1) ? null : cell[row + 1]
						.substring(i - 1, i + 1);
		return neighbors;
		
	}
	public static int FindNeighbors(String[] Neighbors){
		int liveneighbor= 0;
		if(Neighbors[0] != null){//neighbors above row
			for(char x: Neighbors[0].toCharArray()){//each neighbor from above
				if(x == 'O') liveneighbor++;//count the neighbors
			}
		}
		for(char x: Neighbors[1].toCharArray()){ //neighbors on same row
			if(x == 'O') liveneighbor++; //count the neighbors
		}
		if(Neighbors[2]!= null){//neighbors below row
			for(char x: Neighbors[2].toCharArray()){ //each neighbor below
				if(x == 'O') liveneighbor++; //count the neighbors
			}
		}
		return liveneighbor;
	}
	
 
	public static void print(String[] cells){
		for(String s: cells){
			System.out.println(s);
		}
	}
}