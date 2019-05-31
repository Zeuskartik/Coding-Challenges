import java.util.Scanner;

 public class MainClass {

	public static void main(String[] args) {
		//input is taken from the user 
		Scanner input = new Scanner(System.in);
		int t;
		t = input.nextInt();
		//Instance of DrunkFinder
		DrunkFinder drunkFinder = new DrunkFinder();
		for (int i = 0; i < t; i++) {
			drunkFinder.finDrunk(input);
		}
		input.close();
	}

	static class DrunkFinder {
		//instance variables
		int n, k, m, values[];



		//Function to find the arrest outcome, namely, 0 , 1 or -1.
		//0=not arrested , 1 = arrested and -1=invalid input
		public void finDrunk(Scanner input) {
			n = input.nextInt();
			k = input.nextInt();
			m = input.nextInt();
			values = new int[n + 2];
			for (int i = 0; i < n; i++)
				values[i] = input.nextInt();
			if (m == 1)
				System.out.println("-1");
			else if (!isDrunk(values)) {
				System.out.println("0");
			} else {
				System.out.println(calculateResult(n));
			}
		}


		//resultCalculation of breathlyzer test
		public int calculateResult(int n) {
			int values2[] = new int[n + 2];
			//threshold time
			int threshold = 100000;
			boolean checker = false;
			for (int i = 0; i < (1 << n); i++) {
				for (int j = 0; j < n; j++) {

					if ((i & (1 << j)) == 0) {
						values2[j] = values[j];
					} else {
						values2[j] = values[j] + 1;
					}

				}

				if (!isDrunk(values2)) {
					checker = true;
					int temp = counter(i);
					if(threshold<temp){
						threshold=threshold;
					}
					else{
						threshold=temp;
					}
					
				}
			}

			if (checker == false)
				return -1;

			return threshold;
		}


		//decider function to calculate the outcome of test and "+1" operations.
		public boolean isDrunk(int[] values) {
			int maximal, count;
			for (int i = 0; i <= (n - k); i++) {
				maximal = values[i];
				count = 1;
				for (int j = i + 1; j <= (i + k - 1); j++) {
					if (values[j] > maximal) {
						maximal = values[j];
						count = 1;
					} else if (values[j] == maximal)
						count++;
				}
				if (count >= m)
					return true;
			}
			return false;
		}
		//counter function
		public int counter(int num) {
			int startCount = 0;
			int tempV = (1 << 0);
			while (tempV <= num) {
				if (num!=0 & tempV!=0)
					startCount++;
				tempV = tempV << 1;
			}
			return startCount;
		}

	}

}
