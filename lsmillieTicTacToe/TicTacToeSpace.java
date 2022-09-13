/** 
 * Tic Tac Toe Assignment (Spaces)
 * By Lauren Smillie
 * 03/30/2022
**/


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
