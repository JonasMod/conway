package conway;

import java.util.Scanner;

public class Conway {
	
	public static void print(IModel model) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < model.y(); i++) {
			for(int j = 0; j < model.x(); j++) {
				stringBuilder.append(model.at(j, i) ? '#' : '-');
			}
			stringBuilder.append('\n');
		}
		System.out.print(stringBuilder.toString());
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Welcome to Conway's!");
		System.out.println("Three examples are statically built-in. Type 1, 2 or 3 to select one!");
		System.out.print("> ");
		String selection = scan.nextLine();

		IModel model = null;
		if(selection.equals("1")) {
			//note: in a dynamically created seed we would have to catch potential exceptions!
			model = new Model(15, 6, 
					  "fffftffffffffff"
					+ "ffffftfffffffff"
					+ "ffftttfffffffff"
					+ "fffffffffffffff"
					+ "fffffffffffffff"
					+ "fffffffffffffff");
		} else if(selection.equals("2")) {
			model = new Model(18, 5,
					  "ffffffffffffffffff"
					+ "ffffffffttffffffff"
					+ "ffffffffttffffffff"
					+ "ffffffffffttffffff"
					+ "ffffffffffttffffff");
		} else if(selection.equals("3")) {
			model = new Model(25, 5,
					  "fffffffffffffffffffffffff"
					+ "fffffffffffffffffffffffff"
					+ "fffffffffffffffftttffffff"
					+ "ffffffffffffffftttfffffff"
					+ "fffffffffffffffffffffffff");
		} else {
			System.out.println("Don't play games with me, son. I'm out.");
			return;
		}
		
		System.out.println("This is the playing field:");
		print(model);
		System.out.println("Your only option is pressing Enter, so why don't you try that out?");
		
		while(true) {
			scan.nextLine();
			
			model.tick();
			print(model);
		}
	}

}
