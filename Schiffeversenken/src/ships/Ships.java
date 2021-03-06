package ships;

public class Ships {
	static boolean raster[][] = new boolean[10][10]; //horizontal or vertical
	static int ships[][] = new int[10][10];	//length of ship
	

	
	public static void setHorizontal(int lenght, boolean horitontal, int y, int x) {
		raster[y][x] = horitontal;
		ships[y][x] = lenght;
	}
	
	
	public static boolean getHorizontal(int y, int x) {
		return raster[y][x];
	}
	
	public static int getLength(int y, int x) {
		return ships[y][x];
	}
}
