/** 
 * Tic Tac Toe Assignment (Game)
 * By Lauren Smillie
 * 03/30/2022
**/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
