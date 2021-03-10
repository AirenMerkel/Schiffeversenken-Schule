package listner;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerOwn {
	private static int selectShip = -1;

	
	public static void changeColor(Button button, String action) {
		switch (action) {
		case "hit":
			button.setBackground(Color.RED);
			break;

		case "mishit":
			button.setBackground(Color.YELLOW);
			break;
		
		case "notPressed":
			button.setBackground(Color.BLUE);
			break;
			
		case "ship":
			button.setBackground(Color.LIGHT_GRAY);
			break;

		case "selected":
			button.setBackground(Color.PINK);
			break;
		case "normal":
			button.setBackground(Color.decode("#f0f0f0"));
			break;
		default:
			break;
		}
	}
	//Place Ships
	public static void setShip(Button button, String coordinates, int coordinatesX, int coordinatesY) {
		
		
		button.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent cursor) { 
            	boolean shipSize = false;
	            if(selectShip != -1 && view.BuildOwn.numbersOfShips[selectShip] > 0 || cursor.getButton() == 2) {
	            	//if left mouse clicked then the ship will be set in the horizontal.
	            	if(cursor.getButton() == 1) {
	            		
	            		//Check if the ship is outside or against another ship
						for(int ship = coordinatesX; ship < coordinatesX+2+selectShip; ship++) {
							try {
								if(view.BuildOwn.buttonXY[coordinatesY][ship].getBackground() == Color.PINK || view.BuildOwn.buttonXY[coordinatesY][ship].getBackground() == Color.LIGHT_GRAY) {
									//If the ship is in range of an other ship it wouldn't be placed 
									//and an error message will appear
									shipSize = true;
									view.BuildOwn.errorHandling.setText("Zu nah an einem anderen Schiff");
									view.BuildOwn.errorHandling2.setText("Kein Plaz für das Schiff");
									
								}
							} catch (IndexOutOfBoundsException e) {
								//If the ship is out of the area then it wouldn't be placed
								//and an error message will appear
	
								view.BuildOwn.errorHandling.setText("Zu nah am Rand");
								view.BuildOwn.errorHandling2.setText("Kein Plaz für das Schiff");
								shipSize = true;
								break;
							}
						}
						//check if the aura of the ship is in another ship
							// if it is there is an bug
	            		for(int auraX = coordinatesX-1; auraX < coordinatesX+3+selectShip; auraX++) {
							for(int auraY = coordinatesY-1; auraY < coordinatesY+2; auraY++) {
								try {
									if(view.BuildOwn.buttonXY[auraY][auraX].getBackground() == Color.LIGHT_GRAY) {
										view.BuildOwn.errorHandling.setText("Zu nah an einem anderen Schiff");
										shipSize = true;
									}
								} catch (Exception e) {
									//do nothing if the aura is not in the area
								}
							}
						}
	            		//Place the aura and the ship
	            		if(!shipSize) {
	            			//place the aura
		            		for(int auraX = coordinatesX-1; auraX < coordinatesX+3+selectShip; auraX++) {
								for(int auraY = coordinatesY-1; auraY < coordinatesY+2; auraY++) {
									try {
										changeColor(view.BuildOwn.buttonXY[auraY][auraX], "selected");
										view.BuildOwn.buttonXY[auraY][auraX].setEnabled(false);
									} catch (Exception e) {
										//do nothing if the aura is not in the area
									}
								}
							}
		            		//Palace the ship
							for(int ship = coordinatesX; ship < coordinatesX+2+selectShip; ship++) {
								try {
									changeColor(view.BuildOwn.buttonXY[coordinatesY][ship], "ship");
									ships.Ships.setHorizontal(selectShip+2, true, coordinatesY, ship);
									
									if(ship == coordinatesX) {
										view.BuildOwn.buttonXY[coordinatesY][ship].setEnabled(true);
									}
									
								} catch (IndexOutOfBoundsException ioobe) {
									//if everything goes Wrong this error do something
								}
							}
							view.BuildOwn.numbersOfShips[selectShip]--;
							view.BuildOwn.numberShips[selectShip].setText(view.BuildOwn.numbersOfShips[selectShip].toString());
							view.BuildOwn.errorHandling.setText("Schiff gesetzt");
		            		view.BuildOwn.errorHandling2.setText("");
							
	            		}
	            	}	
	            	//if the mouse wheel is pressed the ship gets deleted
	            	else if(cursor.getButton() == 2) {
	            		boolean direction = true;//true = horizontal //false = vertical
	            		int sizeShip = 0;
	            		//Size of the ship and if it is horizontal or vertical
	            		if(view.BuildOwn.buttonXY[coordinatesY][coordinatesX].getBackground() == Color.LIGHT_GRAY) {
	            			for(int x = coordinatesX; x <= coordinatesX+5 && x <= 9 ; x++) {
	            				if(view.BuildOwn.buttonXY[coordinatesY][x].getBackground() == Color.LIGHT_GRAY) {
	            					sizeShip++;
	            				}else {
	            					break;
	            				}
	            			}
	            			if(sizeShip <= 1) {
	            				sizeShip = 0;
	            				for(int y = coordinatesY; y <= coordinatesY+5 && y <= 9 ; y++) {
	            					if(view.BuildOwn.buttonXY[y][coordinatesX].getBackground() == Color.LIGHT_GRAY) {
	            						sizeShip++;
	            					}else {
	            						break;
	            					}
	            				}
	            				direction = false;
	            			}
	            			
	            			
	            			//Delete the ship and his aura 
	            			boolean coordinatesRM[][] = new boolean[10][10];
            					for(int x = 0; x <= 9; x++) {
            						for(int y = 0; y <= 9; y++) {
            							coordinatesRM[y][x] = true;
            						}
            					}
	            			if(direction) { //horizontal
	            				
            					//Remove ship
	            				for(int ship = coordinatesX; ship < coordinatesX+sizeShip; ship++) {
									try {
										changeColor(view.BuildOwn.buttonXY[coordinatesY][ship], "normal");
										view.BuildOwn.buttonXY[coordinatesY][ship].setEnabled(true);
									} catch (IndexOutOfBoundsException ioobe) {
										//if everything goes Wrong, this error do something
									}
								}
	            				//detect other ships
	            				for(int auraX = coordinatesX-2; auraX < coordinatesX+2+sizeShip; auraX++) {
    								for(int auraY = coordinatesY-2; auraY < coordinatesY+3; auraY++) {
    									try {
    										if(view.BuildOwn.buttonXY[auraY][auraX].getBackground() == Color.LIGHT_GRAY) {
    											
    											
    											for(int auraXmini = auraX-1; auraXmini < auraX+2; auraXmini++) {
    			    								for(int auraYmini = auraY-1; auraYmini < auraY+2; auraYmini++) {
    													if(0 <= auraXmini && 9 >= auraXmini && 0 <= auraYmini && 9 >= auraYmini) {
    			    										coordinatesRM[auraYmini][auraXmini] = false;
    													}
    			    								}
    											}
    										}
    									} catch (Exception e) {
    										//do nothing if the aura is not in the area
    									}
    								}
    							}
	            				
	            				
	            				
	            				for(int auraX = coordinatesX-1; auraX < coordinatesX+3+sizeShip; auraX++) {
    								for(int auraY = coordinatesY-1; auraY < coordinatesY+2; auraY++) {
    									try {
    										if(coordinatesRM[auraY][auraX]) {
	    										changeColor(view.BuildOwn.buttonXY[auraY][auraX], "normal");
	    										view.BuildOwn.buttonXY[auraY][auraX].setEnabled(true);
    										}
    									} catch (Exception e) {
    										//do nothing if the aura is not in the area
    									}
    								}
    							}

								view.BuildOwn.errorHandling.setText("Schiff gelöscht");
								
								
							//Vertical
	            			}else {
	            				
	            				//Remove ship
	            				for(int ship = coordinatesY; ship < coordinatesY+sizeShip; ship++) {
									try {
										changeColor(view.BuildOwn.buttonXY[ship][coordinatesX], "normal");
										view.BuildOwn.buttonXY[ship][coordinatesX].setEnabled(true);
									} catch (IndexOutOfBoundsException ioobe) {
										//if everything goes Wrong, this error will do something
									}
								}
	            				
	            				
	            				//detect other ships
	            				for(int auraX = coordinatesX-2; auraX < coordinatesX+3; auraX++) {
    								for(int auraY = coordinatesY-2; auraY < coordinatesY+2+sizeShip; auraY++) {
    									try {
    										if(view.BuildOwn.buttonXY[auraY][auraX].getBackground() == Color.LIGHT_GRAY) {
    											
    											
    											for(int auraXmini = auraX-1; auraXmini < auraX+2; auraXmini++) {
    			    								for(int auraYmini = auraY-1; auraYmini < auraY+2; auraYmini++) {

    													if(0 <= auraXmini && 9 >= auraXmini && 0 <= auraYmini && 9 >= auraYmini) {
        													if(0 <= auraXmini && 9 >= auraXmini && 0 <= auraYmini && 9 >= auraYmini) {
        			    										coordinatesRM[auraYmini][auraXmini] = false;
        													}
    													}
    			    								}
    											}
    										}
    									} catch (Exception e) {
    										//do nothing if the aura is not in the area
    									}
    								}
    							}
	            				
	            				//TODO Löschen vertical
	            				for(int auraX = coordinatesX-1; auraX < coordinatesX+2; auraX++) {
									for(int auraY = coordinatesY-1; auraY < coordinatesY+3+sizeShip; auraY++) {
										
										try {
											if(coordinatesRM[auraY][auraX]) {
												changeColor(view.BuildOwn.buttonXY[auraY][auraX], "normal");
												view.BuildOwn.buttonXY[auraY][auraX].setEnabled(true);
											}
										} catch (Exception e) {
											//do nothing if the aura is not in the area
										}
											
									}
								}

	            				for(int ship = coordinatesY; ship < coordinatesY+sizeShip; ship++) {
									try {
										changeColor(view.BuildOwn.buttonXY[ship][coordinatesX], "normal");
										view.BuildOwn.buttonXY[ship][coordinatesX].setEnabled(true);
										
									} catch (IndexOutOfBoundsException ioobe) {
										//if everything goes Wrong this error do something
									}
								}
	            			}
	            			
							view.BuildOwn.numbersOfShips[sizeShip-2]++;
							view.BuildOwn.numberShips[sizeShip-2].setText(view.BuildOwn.numbersOfShips[sizeShip-2].toString());
	            		}else {
		            		view.BuildOwn.errorHandling.setText("Kein Schiff zum Löschen");
		            		view.BuildOwn.errorHandling2.setText("");

	            		}
	            		
	            	}
	            	//if right click the ship will place vertical
	            	else if(cursor.getButton() == 3) {
	            		//check for collision
	            		for(int ship = coordinatesY; ship < coordinatesY+2+selectShip; ship++) {
							try {
								if(view.BuildOwn.buttonXY[ship][coordinatesX].getBackground() == Color.PINK || view.BuildOwn.buttonXY[ship][coordinatesX].getBackground() == Color.LIGHT_GRAY) {
									//If the ship is in range of an other ship it wouldn't be placed 
									//and an error message will appear
									shipSize = true;
									view.BuildOwn.errorHandling.setText("Zu nah an einem anderen Schiff");
									view.BuildOwn.errorHandling2.setText("Kein Plaz für das Schiff");
									
								}
							} catch (IndexOutOfBoundsException e) {
								//If the ship is out of the area then it wouldn't be placed
								//and an error message will appear
	
								view.BuildOwn.errorHandling.setText("Zu nah am Rand");
								view.BuildOwn.errorHandling2.setText("Kein Plaz für das Schiff");
								shipSize = true;
							}
						}
	            		if(!shipSize) {
		            		for(int auraX = coordinatesX-1; auraX < coordinatesX+2; auraX++) {
								for(int auraY = coordinatesY-1; auraY < coordinatesY+3+selectShip; auraY++) {
									try {
										changeColor(view.BuildOwn.buttonXY[auraY][auraX], "selected");
										view.BuildOwn.buttonXY[auraY][auraX].setEnabled(false);
									} catch (Exception e) {
										//do nothing if the aura is not in the area
									}
								}
							}
		
							for(int ship = coordinatesY; ship < coordinatesY+2+selectShip; ship++) {
								try {
									changeColor(view.BuildOwn.buttonXY[ship][coordinatesX], "ship");
									ships.Ships.setHorizontal(selectShip+2, false, ship, coordinatesX);
									if(ship == coordinatesY) {
										view.BuildOwn.buttonXY[ship][coordinatesX].setEnabled(true);
									}
								} catch (IndexOutOfBoundsException ioobe) {
									//if everything goes Wrong this error do something
								}
							}
							view.BuildOwn.numbersOfShips[selectShip]--;
							view.BuildOwn.numberShips[selectShip].setText(view.BuildOwn.numbersOfShips[selectShip].toString());
		            		view.BuildOwn.errorHandling.setText("Schiff gesetzt");
		            		view.BuildOwn.errorHandling2.setText("");
	            		}
	            	}
            	}else {
	            		view.BuildOwn.errorHandling.setText("Alle Schiffe gesetzt oder");
	            		view.BuildOwn.errorHandling2.setText("kein schiff ausgewählt");
	            	}
            	
            } 
		});
		
		

	}
	

	public static void getShipListner(Button button, int ship) {

		button.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
            	for(int i=0; i<=3; i++) {
					if(ship == i) {
						changeColor(view.BuildOwn.ships[i], "selected");
						selectShip = i;
					}else {
						changeColor(view.BuildOwn.ships[i], "normal");
					}
            	}
            } 
		});
	}
	
	
	public static void lockButtons() {
		for(int x = 0; x <= 9; x++) {
			for(int y = 0; y <= 9; y++) {
				view.BuildOwn.buttonXY[y][x].setEnabled(false);
			}
		}
	}
		
	
	public static void seachOpponent(Button button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count = 0;
				for(int ship = 0; ship <= 3; ship++) {
					count += view.BuildOwn.numbersOfShips[ship];
				}
				if(count == 0 || view.Welcome.chckbxTestModus.getState()) {
					view.Main.setVisible(false, false, false, true, false, false);
					lockButtons();
            		view.BuildOwn.errorHandling.setText("Alle Schiffe Platziert");
            		view.BuildOwn.errorHandling2.setText("Suche Gegner...");
            		//networkComunication.DirectCommunication.start();
            		
            		conectionBroadcast.Conect.start();
				}else {
            		view.BuildOwn.errorHandling.setText("Nicht alle Schiffe Platziert");
            		view.BuildOwn.errorHandling2.setText("");
				}
			}
		});
	}
}
