public class Main {

	public static void main(String[] args) {
		
		Mastermind game = new Mastermind();
		
		game.compactArray();
		game.getGuess();
		
		double score = 3.0;
		
	}//main method

}//class main

//CODE TO PRINT OUT ALLSOLUTIONS
/*for (int i = 0; i <= game.n; i++) {
	for(int j = 0; j <= 3; j++) {
		System.out.print("" + game.allSolutions[i][j]);
	}
	System.out.println();
}*/