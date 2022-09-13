/** 
 * Tic Tac Toe Assignment (Board)
 * By Lauren Smillie
 * 03/30/2022
**/

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
