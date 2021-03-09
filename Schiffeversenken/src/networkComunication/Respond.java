package networkComunication;

import java.awt.Button;
import java.awt.Color;

public class Respond {
	
	
	public static int destryedShipCounter = 0;
	
	
	public static int[] getCoordinates(String msg){
		String[] target = msg.split(",");

		int[] coordinates = new int[2];
		char coordinatesY = target[1].charAt(1);
		char coordinatesX = target[2].charAt(1);

		coordinates[0] = -1;
		coordinates[1] = -1;
		
		char Y = 'A';
		char X = '0';
		
		
		for(int i = 0; i <= 9; i++) {
			if(coordinatesX == X+i) {
				coordinates[0] = i;
			}
			if(coordinatesY == Y+i) {
				coordinates[1] = i;
			}
		}
		
		//System.out.println(coordinates[0] + "    " + coordinates[1]);
		
		return coordinates;
	}
	
	
	public static String getHit(int x, int y) {
		Button btn = view.BuildOwn.buttonXY[y][x];
		Color buttonColor =  view.BuildOwn.buttonXY[y][x].getBackground();
		
		String respond = "Err";
		if(buttonColor == Color.LIGHT_GRAY) {
			listner.ListenerOwn.changeColor(btn, "hit");
			respond = messages.Messages.hit;
			
			view.BuildOwn.errorHandling.setText("Schiff getroffen");
    		view.BuildOwn.errorHandling2.setText("");
		}
		else {
			
			if(buttonColor == Color.RED) {
				
			}else {
				listner.ListenerOwn.changeColor(btn, "mishit");
			}
			respond = messages.Messages.misshit;

			view.BuildOwn.errorHandling.setText("Schiff verfehlt");
    		view.BuildOwn.errorHandling2.setText("");
		}
		
		//Ship destroyed
		if(respond == messages.Messages.hit) {
			respond = shipDestroyed(x,y);
		}
		
		if(respond == messages.Messages.shipDestroyed) {
			destryedShipCounter++;
			if(destryedShipCounter >= 10) {
				respond = messages.Messages.lastShip;
				view.BuildOwn.errorHandling.setText("Alle Schiff zerstört");
        		view.BuildOwn.errorHandling2.setText("");
			}

			view.BuildOwn.errorHandling.setText("Ein Schiff wurde zerstört");
    		view.BuildOwn.errorHandling2.setText("");
		}
		
//		System.out.println("destryedShipCounter:   " + destryedShipCounter);
		return respond;
	}
	
	static String shipDestroyed(int x, int y) {
		String answerDestroyed = messages.Messages.shipDestroyed;
		String answerNotDestroyed = messages.Messages.hit;
		for(int auraX = x-1; auraX <= x+1; auraX++) {
			for(int auraY = y-1; auraY <= y+1; auraY++) {
				//define Color holder 
				Color buttonColor =  Color.black;
				
				try {//Try to get the color of the nearby areas
					buttonColor = view.BuildOwn.buttonXY[auraY][auraX].getBackground();
//					System.out.println("Main:  Color:" + buttonColor);
//					System.out.println("Main:  AuraX:" + auraX);
//					System.out.println("Main:  AuraY:" + auraY);
				} catch (IndexOutOfBoundsException e) {
					System.out.println(e);
					// Do Nothing
				}
				if(x == auraX && y == auraY) {//The hit point is ignored
					
				}else if(buttonColor == Color.LIGHT_GRAY) {//if a place near is LIGHT_GRAY, The ship is not destroyed
//					System.out.println(answerNotDestroyed + "\t\t Zeile 95");
					return answerNotDestroyed;
					
				}else if( buttonColor == Color.RED) {	//If a place around is RED it is possible that the ship is destroyed
					//redCounter = redCounter +1;
					
					//find out in witch direction the ship is
					if(auraX == x) {	// if auraX is the same as x the direction is horizontal
//						System.out.println("Aura X");
						String respond = null;
						if(auraY < y) {
							respond = shipDestroyed2(auraX, auraY, 1);
						}else {
							respond = shipDestroyed2(auraX, auraY, 2);
						}
						if(respond == messages.Messages.hit) {
//							System.out.println("Test1");
							return answerNotDestroyed;
						}
					}else if(auraY == y) {
//						System.out.println("Aura Y");
						String respond = null;
						if(auraX < x) {
//							System.out.println("1");
							respond = shipDestroyed2(auraX, auraY, 3);
						}else {
//							System.out.println("2");
							respond = shipDestroyed2(auraX, auraY, 4);
						}
						
						if(respond == messages.Messages.hit) {
							//System.out.println("Test2");
							return answerNotDestroyed;
						}
					}
				}
			}
		}
		return answerDestroyed;
	}
	
	

	static String shipDestroyed2(int x, int y, int direction) { // direction: 0 = undefined; 1 = horizontal left; 2 = Right; 3 = vertical up; 4 = down
		int newX = x;
		int newY = y;
//		System.out.println("X:          " +x);
//		System.out.println("Y:          " +y);
//		System.out.println("Direction:  " +direction);
		
		
		switch (direction) {
			case 1: 
//				System.out.println("direction1: " + direction);
				newY = newY-1;
				break;
			case 2: 
//				System.out.println("direction2: " + direction);
				newY = newY+1;
				break;
			case 3: 
//				System.out.println("direction3: " + direction);
				newX = newX-1;
				break;
			case 4: 
//				System.out.println("direction4: " + direction);
				newX = newX+1;
				break;
		} 
		if(newX == x && newY == y) {
//			System.out.println("Error");
			return null;
		}
		try {
			Color buttonColor = view.BuildOwn.buttonXY[newY][newX].getBackground();
//			System.out.println("Aura:  " + buttonColor);
			
			if(buttonColor == Color.LIGHT_GRAY) {
//				System.out.println("LIGHT_GRAY");
				return messages.Messages.hit;
				
			}else if(buttonColor == Color.RED) {
//				System.out.println("RED");
				String answer = shipDestroyed2(newX, newY, direction);
				return answer;
			}else if(buttonColor == Color.PINK) {
				return messages.Messages.shipDestroyed;
				
			}
		} catch (IndexOutOfBoundsException e) {
//			System.out.println(e);
			return messages.Messages.shipDestroyed;
		}

		return messages.Messages.shipDestroyed;
	}
}
