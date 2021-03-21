public class Main {

	public static void main(String[] args) {
		
		Mastermind game = new Mastermind();
		
		game.compactArray();
		game.getGuess();
		
		System.out.println("Guess: " + game.guess[0] + game.guess[1] + game.guess[2] + game.guess[3]);
		
		double score = 3.0;
		boolean gameOver = game.eliminateBadGuesses(score);
		
		if (gameOver == true) {
			System.out.println(" Winner!!");
		}
		
	}//main method

}//class main

//CODE TO PRINT OUT ALLSOLUTIONS
/*for (int i = 0; i <= game.n; i++) {
	for(int j = 0; j <= 3; j++) {
		System.out.print("" + game.allSolutions[i][j]);
	}
	System.out.println();
}*/