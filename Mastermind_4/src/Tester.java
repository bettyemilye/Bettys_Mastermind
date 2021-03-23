
public class Tester {

	public static void main(String[] args) {
		
		Mastermind game = new Mastermind();

		int[] a = new int[] {6,0,1,3};
		int[] g = new int[] {6,1,9,4};
		
		double s = game.scoreSolution(a, g);
		
		System.out.println("Score = " + s);
		
		

	}

}
