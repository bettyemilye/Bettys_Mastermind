//import necessary classes to program 
import java.util.Random;
import java.util.Arrays;

public class Mastermind {
	int n = 5;														//number of 'rows' in the allSolutions array
	int validSolutions = n + 1;										//the number of valid solutions in allSolutions
	int[][] allSolutions = new int[validSolutions][4];				//the array where every possible solution will be stored
	private static final int INVALID_SOLUTION = -1;
	int[] guess = new int[4];
	
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
	boolean eliminateBadGuesses(double score) {
		
		boolean debug = true;
		
		double checkScore = 0.0;							//in checkScore i will store the score of the guess i'm checking
		int[] checkSolution = new int[4];					//in the check array i will store the 4 digits of the guess i'm checking
		
		//is the game over
		if (score == 4.0) {
			//return true because the game is over
			
			/* BETTY - I'M NOT REALLY SURE YOU SHOULD BE DOING THIS
			 * IT SEEMS TO ME THAT THIS METHOD SHOULD PROCESS ALL
			 * THE GUESSES JUST THE SAME AS IT WOULD WITH ANY OTHER
			 * GUESS. THE ONLY THINGS THAT IS REALLY SPECIAL ABOUT
			 * A SCORE OF 4.0 IS THAT ONLY ONE SOLUTION IN THE ALL
			 * SOLUTIONS ARRAY
			 * 
			 * PROBABLY THIS METHOD SHOIULD NEVER BE CALLED WITH
			 * A SCORE OF 4.0. THE CALLING METHOD SHOULD HAVE
			 * REALIZED THAT THE GAME WAS OVER
			 * 
			 * THIS JUST FEELS WRONG
			 * 
			 */
			
			return true;
			
		} else {
			//for i loop goes through every valid solution
			for (int i = 0; i < validSolutions; i++) {
				
				
				checkSolution = Arrays.copyOf(allSolutions[i], 4);
				
				System.out.println("\nGuess        : " + this.guess[0] + this.guess[1] + this.guess[2] + this.guess[3]);
				System.out.println("checkSolution: " + checkSolution[0] + checkSolution[1] + checkSolution[2] + checkSolution[3]+"\n");

				//reset checkScore
				checkScore = 0;
				//for j loop goes through each digit in the guess
				for (int j = 0; j <= 3; j++) {	
					
					//System.out.println("i = " + i + ", j = " + j);
					
					//for k loop goes through each digit of the solution being checked
					for (int k = 0; k <= 3; k++) {
						
						if (debug) System.out.print("guess" + j + " -> checkSolution" + k + " ; comparing the " + guess[j] + " in 'guess' to " + checkSolution[k] + " in 'checkSolution'");

						//System.out.print("k = " + k + " ");
						
						if(guess[j] == checkSolution[k]) {
							//digit is in both the guess and the possible solution
							if (debug) System.out.print(" digits match ; ");
							if (j == k) {
								//digit is in correct position
								
								/*
								 * checkScore is a double
								 * why are you adding 10s and 1s
								 * why not 1s and 0.1s
								 */
								checkScore += 10;
								if (debug) System.out.print(" in correct position; checkScore = " + checkScore + " ; now processing next digit of guess");
								
							} else {
								//digit is in wrong position
								checkScore += 1;
								if (debug) System.out.print(" in wrong position; checkScore = " + checkScore);
							}//else if 
							checkSolution[k] = -1;
						}//outside if
						
						if (debug) System.out.print("\n");
						
						//System.out.println("\ni = " + i + ", j = " + j + ", k = " + k + ", cs = " + checkScore + "\n");
						
					}//for k
					
				}//for j
				
				//System.out.println("checkScore = " + checkScore/10);
				if (score == checkScore/10) {
					//System.out.println("Possible");
				}

			}//for i
			
		}//else
		
		//return false because the game isn't over
		return false;
	}//eliminateBadGuesses method
	
	
	

}//mastermind class