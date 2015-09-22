import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String strForCalc;
		System.out.println("+***************************************+");
		System.out.println("*                                       *");
		System.out.println("*       Welcome to DIT948 Calculator    *");
		System.out.println("*                                       *");
		System.out.println("+***************************************+");

		while (true) {
			System.out.print("Press \"E\" to exit or any other button to continue> ");
			char userResponse = input.nextLine().charAt(0);

			if (userResponse == 'e' || userResponse == 'E') {
				System.out.println("Bye bye");
				input.close();
				return;
			}
			else {

				System.out.print("Please enter an arithmetic expression to evaluate> ");

				strForCalc = input.nextLine();
				strForCalc = strForCalc.replaceAll(" ", "");
        
				strForCalc = RPN.format(strForCalc);

				System.out.println("The RPN representation of your expression is> "+strForCalc.replaceAll(",", " "));

				String result = RPN.calc(strForCalc);

				System.out.println("The final result is> "+result+"\n");

			}	
		}	
	}
}

