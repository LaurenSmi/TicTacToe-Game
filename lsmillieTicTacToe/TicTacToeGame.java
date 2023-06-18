/** 
 * Tic Tac Toe Game
 * By Lauren Smillie
**/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class TicTacToeSpace
{
	// Private field
	private int value;
	
	// Public values
	public static final int BLANK =0;
	public static final int X =1;
	public static final int O =2;
	
	// Default Space Constructor
	public TicTacToeSpace()
	{
		value = BLANK;
	}
	
	// Constructor using a value
	public TicTacToeSpace(int value)
	{
		this();
		if (value<=2 && value>=0)
		{
			this.value = value;
		}
	}
	
	// Accessor for value of a space
	public int getValue()
	{
		return this.value;
	}
	
	// Mutator for the value of a space
	public void setValue(int value)
	{
		if (value<=2 && value>=0)
		{
			this.value = value;
		}
	}
	
	// Accessor for the String version of the value
	public static String getValueString(int value)
	{
		if (value<=2 && value>=0)
		{
			if (value == BLANK)
			{
				return "-";
			}
			else if(value == X)
			{
				return "X";
			}
			return "O";
		}
		return "invalid";
	}
	
	
	// toString method for a TicTacToeSpace
	@Override
	public String toString()
	{
		return this.getValueString(value);
	}

}


class TicTacToeBoard
{
	// Private field, a 3x3 array of TicTacToeSpaces
	private TicTacToeSpace[][] spaces = new TicTacToeSpace[3][3];
	
	// Static class to check validity of a value for a TicTacToeSpace
	private static boolean validity(int val)
	{
		if(val<=2 && val>=0)
		{
			return true;
		}
		return false;
	}
	
	// Defauly Board Constructor
	public TicTacToeBoard()
	{
		for(int i=0;i<spaces.length;i++)
		{
			for(int j=0;j<spaces[i].length;j++)
			{
				spaces[i][j] = new TicTacToeSpace();
			}
		}
	}
	
	// Board Constructor using a 2D array of integers
	public TicTacToeBoard(int[][]board)
	{
		this();
		boolean a = true;
		if (board.length == 3 && board[0].length == 3)
		{	
			for(int i=0;i<board.length;i++)
			{
				for(int j=0;j<board[i].length;j++)
				{
					if(board[i].length==3)
					{
						int val = board[i][j];
						spaces[i][j].setValue(val);
						if (!validity(val))
						{
							a =false;				
						}
					}
					else
					{
						a =false;
					}	
				}
			}
		}
		if (a == false)
		{
			clear();
		}
	}
	
	// Board Constructor for a 2D array of TicTacToeSpaces
	public TicTacToeBoard(TicTacToeSpace[][] board)
	{
		this();
		boolean a = true;
		if (board.length == 3 && board[0].length == 3)
		{
			for(int i =0;i<board.length;i++)
			{
				for(int j=0;j<board[i].length;j++)
				{
					if (board[i].length==3)
					{	
						int val = board[i][j].getValue();
						setSpace(i,j,val);
						if (!validity(val))
						{
							a = false;
						}
					}
					else
					{
						a= false;
					}
				}
			}		
		}
		if (a == false)
		{
			clear();
		}	
	}
	
	// Board Constructor to duplicate another TicTacToeBoard
	public TicTacToeBoard(TicTacToeBoard board)
	{
		this();
		boolean a = true;
		for(int i=0;i<this.spaces.length;i++)
		{
			for(int j=0;j<this.spaces[i].length;j++)
			{
				if (board.spaces[i].length==3)
				{
					int val = board.spaces[i][j].getValue();
					setSpace(i,j,val);
					if(!validity(val))
					{
						a = false;
					}	
				}
				else
				{
					a=false;
				}
			}
		}
		if (a == false)
		{
			clear();
		}
			
	}

	// Method to see if board is a win (three Xs or Os in a row in any direction)
	public boolean isWin()
	{		
		int[]row = new int[3];
		int []col = new int[3];
		for(int i=0;i<spaces.length;i++)
		{
			row = rows(spaces,i);
			boolean ans = same(row);
			if (ans == true)
			{
				return true;
			}
			
			col = cols(spaces,i);
			boolean ans2 = same(col);
			if (ans2 == true)
			{
				return true;
			}
		}
		
		int dia1 [] = diag1(spaces);
		int dia2 [] = diag2(spaces);
		
		if (same(dia1) == true || same(dia2) == true)
		{
			return true;
		}
		
		return false;	
	}
	
	// Method to return the winner (X or O)
	public int winner()
	{
		if (isWin())
		{
			int[]row = new int[3];
			int []col = new int[3];
			for(int i=0;i<spaces.length;i++)
			{
				row = rows(spaces,i);
				col = cols(spaces,i);
				boolean ans = same(row);
				if (same(col))
				{
					return col[i];
				}
				if (same(row))
				{
					return row[i];
				}
			}
			return spaces[1][1].getValue();
		}
		return 0;
	}

	// Method to check if board is a draw (all spaces are filled with Xs/Os with no win)
	public boolean isDraw()
	{
		if(!(isWin()))
		{
			int counter = 0;
			for(int i=0;i<spaces.length;i++)
			{
				for(int j=0;j<spaces[i].length;j++)
				{
					if (spaces[i][j].getValue()==TicTacToeSpace.BLANK)
					{
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}

	// Method to check if board is blank
	public boolean isBlank()
	{
		for(int i=0;i<spaces.length;i++)
		{
			for(int j=0;j<spaces[i].length;j++)
			{
				if (spaces[i][j].getValue()!=TicTacToeSpace.BLANK)
				{
					return false;
				}
			}
		}
		return true;	
	}

	// Method to find and return value of a TicTacToeSpace in a board
	public TicTacToeSpace getSpace(int row, int col)
	{
		TicTacToeSpace space = new TicTacToeSpace();
		if (row<=2 && row>=0)
		{
			if (col<=2 && col>=0)
			{
				space.setValue(spaces[row][col].getValue());
			}
		}
		return space;
	}

	// Mutator method to change the value of a TicTacToeSpace on the board
	public boolean setSpace(int row, int col, int val)
	{
		if (row<=2 && row>=0 && col<=2 && col>=0)
		{
			if (validity(val))
			{
				spaces[row][col].setValue(val);
				return true;
			}
		}
		return false;
	}

	// Method to set all of the spaces of the board to blank
	public void clear()
	{
		for(int i=0;i<spaces.length;i++)
		{
			for(int j=0;j<spaces[i].length;j++)
			{
				spaces[i][j].setValue(TicTacToeSpace.BLANK);
			}
		}
	}
	
	// Static method to create an array from a row of a 2D array
	private static int[] rows(TicTacToeSpace[][] a1, int index)
	{
		int [] row = new int [3];
		for( int j =0; j<3; j++)
		{
			row[j] = a1[index][j].getValue();
			
		} 
		return row;
	}
	
	// Static method to create an array from a column of a 2D array
	private static int[] cols(TicTacToeSpace[][] a1, int index)
	{
		int [] col = new int[3];
		for(int i =0; i<3;i++)
		{
			col[i] = a1[i][index].getValue();
		}
		return col;
	}
	
	// Static method 1 to create an array from a diagonal of a 2D array
	private static int[] diag1(TicTacToeSpace[][] a1)
	{
		int [] dia = new int [3];
		for(int i =0;i<3;i++)
		{
			dia[i] = a1[i][i].getValue();
		}
		
		return dia;
	}
	
	// Static method 2 to create an array from a diagonal of a 2D array
	private static int[] diag2(TicTacToeSpace[][] a1)
	{
		int [] dia = new int[3];
		for(int i =2;i>=0;i--)
		{
			dia[i] = a1[2-i][i].getValue();
		}
		return dia;
	}
	
	// Static method to check if the values of a row/column are equal
	private static boolean same(int [] a1)
	{
		int counter = 0;
		int num = a1[0];
		for(int i =1; i<a1.length; i++)
		{
			if(num==a1[i] && num!=0)
			{
				counter++;
			}
		}
		if(counter ==2)
		{
			return true;
		}
		return false;	
	}
	
	
	// toString Method for a TicTacToeBoard
	@Override
	public String toString()
	{
		String boardOut = new String ();
		boardOut = "   0 1 2\n";
		for(int i =0; i<spaces.length; i++)
		{
			boardOut = boardOut + i+" ";
			for(int j=0;j<spaces[i].length;j++)
			{
				boardOut = boardOut + "|"+ spaces[i][j].toString();
			}
			boardOut = boardOut + "|\n";
		}
		return boardOut;
	}
}



final class TicTacToeGame extends JFrame implements ActionListener
{
	// Private fields
	// counter controls whose turn it is (X or O)
	private int counter = 0;
	
	// board stores the information from the game
	private TicTacToeBoard board;
	
	//Some possible states the game can be in.  Add or delete as needed
	private static final int STOPPED = 0;
	private static final int RUNNING = 1;
	
	//some colors to use
	private static final Color lightPurp = new Color(152,106,244);
	private static final Color lightBlue = new Color(176,157,224);
	
	//some of the UI components used 
	private JButton[][] spaces;
	private JTextField msgArea;
	private Font msgFont;
	private Font buttonFont;
	private JButton resetButton;
	private int state = STOPPED;
	
	//Panels used to create the layout
	private JPanel buttonPanel;
	private JPanel UiPanel;
	private JPanel spacePanel;
	private JPanel basePanel;
	
	
	//CONSTRUCTOR (used to initialize the GUI)
	public TicTacToeGame()
	{
		board = new TicTacToeBoard();
		//init the JButtons for the 9 spaces using 3x3 array
		spaces = new JButton[3][3];
		
		//font uses for the spaces
		buttonFont = new Font("Arial",Font.BOLD,14);
		
		//create each of the 9 space buttons and add a listener
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				spaces[i][j] = new JButton("-");
				spaces[i][j].setFont(buttonFont);
				spaces[i][j].addActionListener(this);
			}
		}
		
		
		//create the message box and set properties
		msgFont = new Font("Arial",Font.BOLD,20);
		msgArea = new JTextField("Welcome to TIC TAC TOE!");
		msgArea.setFont(msgFont);
		msgArea.setBackground(lightPurp);
		msgArea.setMargin(new Insets(10,5,10,5));
		msgArea.setHorizontalAlignment(JTextField.CENTER);
		msgArea.setEditable(false);
		
		//create the start/reset button and set properties
		resetButton = new JButton("Start Game");
		resetButton.setFont(msgFont);
		resetButton.addActionListener(this);
		
		//create the base panel 2 rows 1 col
		basePanel = new JPanel();
		basePanel.setLayout(new GridLayout(2,1));
		basePanel.setPreferredSize(new Dimension(300,400));
		
		//panel for the start/reset button
		buttonPanel = new JPanel();
		buttonPanel.add(resetButton);
		buttonPanel.setBackground(lightBlue);
		
		//panel to combine the message box and start/reset button
		UiPanel = new JPanel();
		UiPanel.setLayout(new BorderLayout());
		
		//panel to hold the 9 space buttons
		spacePanel = new JPanel();
		spacePanel.setLayout(new GridLayout(3,3));
		spacePanel.setBackground(lightBlue);
		
		//add each of the 9 buttons to the spacePanel grid
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				spacePanel.add(spaces[i][j]);
			}
		}
		
		//add message area and buttonPanel to the UiPanel
		UiPanel.add(msgArea,BorderLayout.CENTER);
		UiPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		//add the upper UiPanel and lower spacePanel to the base grid
		basePanel.add(UiPanel);
		basePanel.add(spacePanel);
		
		//add base panel to the JFrame
		add(basePanel);
		
		//make JFrame visible and set properties
        setResizable(false);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//This method must be used to implement the ActionListener interface
	public void actionPerformed(ActionEvent e)
	{
		//System.out.println(e);
		if(e.getSource() == resetButton)
		{
			if(state == STOPPED)
				{
					state = RUNNING;
					startGame();
					System.out.println("Game Starting...");
				}
				else if(state == RUNNING)
				{
					state = STOPPED;
					stopGame();
					System.out.println("Game Stopping...");
				}
			}
			else if(e.getSource() instanceof JButton && state == RUNNING && !board.isWin())
			{
				
				for(int i = 0; i < 3; i++)
				{
					for(int j = 0; j < 3; j++)
					{
						int a = 0;
						if(e.getSource() == spaces[i][j] && board.getSpace(i,j).getValue() == TicTacToeSpace.BLANK)
						{	
							// determines whose turn it is based on counter's value
							if (counter%2 == 0)
							{
								a = 1;
							}
							else
							{
								a = 2;
							}
							// will change the text displayed by the button
							spaceButtonPressed(i,j,a);
							
							// will update the board with new move
							board.setSpace(i,j,a);
							counter ++;
							System.out.println(board);
						}
					}
				}
			}
			// checks if board is win
			if (board.isWin())
			{
			msgArea.setText(TicTacToeSpace.getValueString(board.winner())+" is the winner!");
			System.out.println("Winner");
			}
			
			// checks if board is draw
			else if (board.isDraw())
			{
				msgArea.setText("Draw");
				System.out.println("Draw");
			}
	}
	
	
	//code to start a new game
	private void startGame()
	{
		resetButton.setText("Reset Game");
		msgArea.setText("X's Turn");
	}
	
	//code to clear current game and stop
	private void stopGame()
	{
		if (board.isDraw())
		{
			msgArea.setText("Draw");
		}
		resetButton.setText("Start Game");
		board.clear();
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				spaces[i][j].setText("-");
			}
		}
		msgArea.setText("Welcome to TIC TAC TOE!");
		counter = 0;
	}
	
	//this method will be sent the row and col of the space that was pressed
	private void spaceButtonPressed(int row, int col, int a)
	{
		if (a == TicTacToeSpace.X)
		{
			spaces[row][col].setText("X");
			msgArea.setText("O's Turn");
		}
		else
		{
			spaces[row][col].setText("O");
			msgArea.setText("X's Turn");
		}
	}
	
	
	//start program by creating the frame
	public static void main(String[] args)
	{
		TicTacToeGame sample = new TicTacToeGame();
	}
	
}
