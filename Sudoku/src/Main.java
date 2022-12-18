import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GUI sudoku = new GUI("Sudoku");
		
		System.out.println("Printing the key for Sudoku for testing purposes");
		for(int row=0;row<9;row++)
		{
			for(int col=0;col<9;col++)
			{
				if(row ==0 & (col==3 | col == 6| col == 8)|
					row ==3 & (col==3 | col == 6| col == 8)|
					row ==8 & (col==2 | col == 4| col == 6)
						)
				{
					
					System.out.println("row -> "+(row+1)+"   "+"col -> "+(col+1)+"   "+"key = "+sudoku.key[row][col]);
					
					
				}
				
			}
		}


	}

}
