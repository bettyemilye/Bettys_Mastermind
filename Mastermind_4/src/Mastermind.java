//import necessary classes to program 
import java.util.Random;
import java.util.Arrays;

public class Mastermind {
	int n = 9999;														//number of 'rows' in the allSolutions array
	int validSolutions = n + 1;										//the number of valid solutions in allSolutions
	int[][] allSolutions = new int[validSolutions][4];				//the array where every possible solution will be stored
	private static final int INVALID_SOLUTION = -1;
	int[] guess = new int[4];
	double checkScore;
	
	//constructor to fill the allSolutions array with every possible solution
	Mastermind() {
		for (int i = 0; i <= n; i++) {								//loop to fill allSolutions
			allSolutions[i][0] = (i / 1000) % 10;					//fill the thousands place
			allSolutions[i][1] = (i / 100) % 10;					//fill the hundreds place
			allSolutions[i][2] = (i / 10) % 10;						//fill the tens place
			allSolutions[i][3] = i % 10;							//fill the ones place
		}//for i	
	}//mastermind constructor
	
	//method to reorganize the array with all the valid solutions at the beginning 
	void compactArray() {
		int lastValidSolution = -1;									//stores
		for(int i = 0; i <= validSolutions - 1; i++) {
			if (allSolutions[i][0] != INVALID_SOLUTION) {
				lastValidSolution += 1;
				
				/*
				 * allSolutions[lastValidSolution] = allSolutions[i];
				 * command above does not do what for loop below does
				 */
				allSolutions[lastValidSolution] = Arrays.copyOf(allSolutions[i],4);
				
				if (lastValidSolution != i) {
					allSolutions[i][0] = INVALID_SOLUTION; 
				}//if
			}//if
		}//for i
		this.validSolutions = lastValidSolution + 1;
    }//compactArray method
	
	//method to select a random guess from the allSolutions array
	int[] getGuess() {
		Random rand = new Random();									//create a new instance of the random class
		int random = rand.nextInt(validSolutions);
		guess = allSolutions[random];
		return guess;
	}

	//method to mark invalid solutions
	void eliminateBadGuesses(double score) {
		double checkScore;
		//loop to go through every possible solution in the allSolutions array
		for (int i = 0; i <= validSolutions; i++) {
			//score the possible solution against the guess
			checkScore = scoreSolution(allSolutions[i], guess);
			//are the scores the same
			if (checkScore != score) {
				allSolutions[i][0] = -1;
			}//if
		}//for i
	}//eliminateBadGuesses method
	
	//method to score a single solution against the guess
	double scoreSolution(int[] solution, int[] guess) {
		boolean[] usedSolution = {false, false, false, false};
		boolean[] usedGuess = {false, false, false, false};
		double checkScore = 0;
		
		//are any digits the same in the same placement
		for (int i = 0; i <= 3; i++) {
			if (solution[i] == guess[i] && usedSolution[i] == false) {
				usedSolution[i] = true;
				usedGuess[i] = true;
				checkScore += 10;
			}//if
		}//for i
		
		//are any digits the same in different placement
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				if (solution[i] == guess[j] && usedSolution[i] == false && usedGuess[j] == false) {
					usedSolution[i] = true;
					usedGuess[j] = true;
					checkScore += 1;
					break;
				}//if
			}//for j
		}//for i
		//return the score
		return checkScore/10;
	}//scoreSolution method

}//mastermind class


