
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener{
	final int WIDTH = 800;
	final int HEIGHT = 800;
	int gridArray[][] = new int[9][9];
	private JButton submit = new JButton("Submit");
	JPanel panel1 = new JPanel(new GridLayout(9,9));
	JPanel panel2 = new JPanel(new FlowLayout());
	JTextField[][] textField = new JTextField[9][9];
	String[][] key = new String[9][9];
	String temp = "";
	int count = 0;
	Color c = new Color(122,122,122);
	public GUI(String title)
	{
	super(title);
	
	setSize(WIDTH, HEIGHT);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.CreateGUI();
	this.setVisible(true);
	this.StartGame();
	
	}
	
	public void CreateGUI()
	{
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		con.add(panel1, BorderLayout.CENTER);
		con.add(panel2, BorderLayout.SOUTH);
		
		
		for(int row=1;row<=9;row++)
		{
			for(int col=1;col<=9;col++)
			{
				textField[row-1][col-1] = new JTextField(1);
			}
		}
		
		
		for(int row=1;row<=9;row++)
		{
			for(int col=1;col<=9;col++)
			{
				panel1.add(textField[row-1][col-1]);
			}
		}
		panel2.add(submit);

		submit.setToolTipText("Click on me to check the answer");
		submit.addActionListener(this);
	}

	
	
	public void StartGame()
	{
		int guess;
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i=1;i<=9;i++)
		{
			nums.add(i);
		}
		Collections.shuffle(nums);
		//1st row
		//1st block start
		for(int col=1;col<=9;col++)
		{
			guess = (int)Math.random() * nums.size();
			textField[0][col-1].setText(nums.get(guess).toString());
			nums.remove(guess);
		}
		for(int col=0;col<3;col++)
		{
			textField[1][col+6].setText(textField[0][col].getText());
			textField[2][col+3].setText(textField[0][col].getText());
		}
		for(int col=3;col<=5;col++)
		{
			textField[1][col-3].setText(textField[0][col].getText());
			textField[2][col+3].setText(textField[0][col].getText());
		}
		for(int col=6;col<9;col++)
		{
			textField[1][col-3].setText(textField[0][col].getText());
			textField[2][col-6].setText(textField[0][col].getText());
		}
		//1st block end
		
		
		//2nd block start
		
		for(int row=3;row<6;row++)
		{
			for(int col=0;col<8;col++)
			{
				textField[row][col].setText(textField[row-3][col+1].getText());
				
			}
			textField[row][8].setText(textField[row-3][0].getText());
		}
		
		// end of 2nd block
		
		
		//3rd block start
		
		for(int row=6;row<9;row++)
		{
			for(int col=0;col<7;col++)
			{
				textField[row][col].setText(textField[row-6][col+2].getText());
				
			}
			textField[row][7].setText(textField[row-6][0].getText());
			textField[row][8].setText(textField[row-6][1].getText());
		}
		
		//3rd block end
		
		for(int row=0;row<9;row++)
		{
			for(int col=0;col<9;col++)
			{
				key[row][col] = "";
			}
		}
		for(int row=0;row<9;row++)
		{
			for(int col=0;col<9;col++)
			{
				if(row ==0 & (col==3 | col == 6| col == 8)|
					row ==3 & (col==3 | col == 6| col == 8)|
					row ==8 & (col==2 | col == 4| col == 6)
						)
				{
					key[row][col] += textField[row][col].getText();
					textField[row][col].setText("");
					
					textField[row][col].setBackground(Color.cyan);
					
				}
				
			}
		}
		
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		for(int row=0;row<9;row++)
		{
			for(int col=0;col<9;col++)
			{
				if(row ==0 & (col==3 | col == 6| col == 8)|
						row ==3 & (col==3 | col == 6| col == 8)|
						row ==8 & (col==2 | col == 4| col == 6)
							)
				{
					
					temp = textField[row][col].getText();
					if(temp.equals(key[row][col]))
					{
						textField[row][col].setBackground(Color.green);
						count++;
						
					}
					else
					{
						textField[row][col].setBackground(Color.red);
						
					}
					
				}
				
			}
			
		}
		if(this.count== 9)
		{
			JOptionPane.showMessageDialog(null, "You Won!!!!!");
			System.exit(0);
		
		}
	else
	{
		JOptionPane.showMessageDialog(null, "Sorry, You lost!");
		System.exit(0);
	}
		
		
		
	}

	

	

}
