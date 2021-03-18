package ships;

import java.awt.Color;

import listner.ListenerOpponent;

public class OpponentShips {
	static boolean grid[][] = new boolean[10][10]; //horizontal or vertical
	static int coordinatesX;
	static int coordinatesY;
	static boolean horizontal;
	static int selectShip;
	
	public static void setPoint(int x, int y) {
		grid[y][x] = true;
	}
	
	public static void colorShipDestroyed(int x, int y) {
		System.out.println("run");
		shipDestroyed(x, y);//get the ship location and size
		turnAuraToWater();  //color the aura
		ListenerOpponent.lockButtons(false);
	}
	
	
	static void turnAuraToWater() {// if the ship is destroyed color the aura
		System.out.println(coordinatesX);
		System.out.println(coordinatesY);
		System.out.println(horizontal);
		System.out.println(selectShip);
		if(!horizontal) {
			//place the aura
    		for(int auraX = coordinatesX-1; auraX < coordinatesX+3+selectShip; auraX++) {
				for(int auraY = coordinatesY-1; auraY < coordinatesY+2; auraY++) {
					try {
						listner.ListenerOwn.changeColor(view.Opponent.buttonXY[auraY][auraX], "water");
					} catch (Exception e) {
						//do nothing if the aura is not in the area
					}
				}
			}
    		//Palace the ship
			for(int ship = coordinatesX; ship < coordinatesX+2+selectShip; ship++) {
				try {
					listner.ListenerOwn.changeColor(view.Opponent.buttonXY[coordinatesY][ship], "destroyed");
//TODO					ships.Ships.setHorizontal(selectShip+2, true, coordinatesY, ship);
					
				} catch (IndexOutOfBoundsException ioobe) {
					//if everything goes Wrong this error do something
				}
			}
		}else if(horizontal){//vertical
    		for(int auraX = coordinatesX-1; auraX < coordinatesX+2; auraX++) {
				for(int auraY = coordinatesY-1; auraY < coordinatesY+3+selectShip; auraY++) {
					try {
						listner.ListenerOwn.changeColor(view.Opponent.buttonXY[auraY][auraX], "water");
					} catch (Exception e) {
						//do nothing if the aura is not in the area
					}
				}
			}

			for(int ship = coordinatesY; ship < coordinatesY+2+selectShip; ship++) {
				try {
					listner.ListenerOwn.changeColor(view.Opponent.buttonXY[ship][coordinatesX], "destroyed");
//TODO					ships.Ships.setHorizontal(selectShip+2, false, ship, coordinatesX);

				} catch (IndexOutOfBoundsException ioobe) {
					//if everything goes Wrong this error do something
				}
			}
			
		}
	}
	
	
	
	static void shipDestroyed(int x, int y) {//get the ship size
		coordinatesY = y;
		coordinatesX = x;
		selectShip = -1;

		for(int auraX = x-1; auraX <= x+1; auraX++) {
			for(int auraY = y-1; auraY <= y+1; auraY++) {
				//define Color holder 
				Color buttonColor =  Color.black;

				try {
					buttonColor = view.Opponent.buttonXY[auraY][auraX].getBackground(); // get the background
				} catch (IndexOutOfBoundsException e) {
					// Do Nothing
					continue;
				}
				if(x == auraX && y == auraY) {//The hit point is ignored
					continue;
				}else if( buttonColor == Color.RED) {	//If a place around is RED it is possible that the ship is destroyed
					selectShip++;//add one to the ship size

					if(coordinatesY > auraY || coordinatesX > auraX) {// if the new coordinates are lower then the old change them

						coordinatesY = auraY;
						coordinatesX = auraX;

						
					}
					//find out in witch direction the ship is
					if(auraX == x) {	// if auraX is the same as x the direction is horizontal
						
						horizontal = true;
						if(auraY < y) {
							shipDestroyed2(auraX, auraY, 1); // to the left
						}else {
							shipDestroyed2(auraX, auraY, 2); // to the right
						}

					}else if(auraY == y) {// if auraY is the same as y the direction is vertical
						
						horizontal = false;
						if(auraX < x) {
							shipDestroyed2(auraX, auraY, 3); // up
						}else {
							shipDestroyed2(auraX, auraY, 4); // down
						}
						
					}
				}
			}
		}
	}
	
	

	static void shipDestroyed2(int x, int y, int direction) { // direction: 0 = undefined; 1 = horizontal left; 2 = Right; 3 = vertical up; 4 = down
		int newX = x;
		int newY = y;

		switch (direction) {
			case 1: 
				newY = newY-1;
				break;
			case 2: 
				newY = newY+1;
				break;
			case 3: 
				newX = newX-1;
				break;
			case 4: 
				newX = newX+1;
				break;
		} 
		if(newX == x && newY == y) {return;}
		try {
			Color buttonColor = view.Opponent.buttonXY[newY][newX].getBackground();
			
			if(buttonColor == Color.RED) { 
				
				if(coordinatesY > newY || coordinatesX > newX) { // if the new coordinates are lower then the old change them

					coordinatesY = newY;
					coordinatesX = newX;	

				}
				selectShip++;	//add one to the ship size
				shipDestroyed2(newX, newY, direction); // do it as long as there are red buttons
			}
		} catch (IndexOutOfBoundsException e) {
			
		}

	}
	
}
