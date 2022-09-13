class TicTacToeTest2
{
	public static void main(String[]args)
	{
		TicTacToeBoard board1 = new TicTacToeBoard(); 
		int[][]a1 = {{1,0,0},{1,0,0},{1,0,2}};
		TicTacToeBoard board2 = new TicTacToeBoard(a1);
		TicTacToeBoard board3 = new TicTacToeBoard();
		
		System.out.println(board2);
		System.out.println(board3);
		for(int i =0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.println(board2.setSpace(i,j,i+1));
			}
		}
		//board2.setSpace(0,0,1);
		System.out.println(board2);
		System.out.println(board2.isWin());
		System.out.println(board2.isDraw());
		System.out.println(board2.winner());
		System.out.println(board2.isBlank());
		System.out.println(board2.getSpace(0,0));
		System.out.println(board2.getSpace(0,1));

	}
}
