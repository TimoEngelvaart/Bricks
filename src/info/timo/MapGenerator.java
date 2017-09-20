package info.timo;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class MapGenerator {
	// Deze class roept de bricks op. 
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for(int i = 0; i < map.length; i++) {
			for(int j=0; j< map[0].length; j++) {
				map[i][j] = 1;
				// Dit zorgt voor de zichtbaarheid van de bricks. Daarnaast zorgt het ervoor dat de bricks aangeraakt kunnen worden door de bal. 
			}
		}
		brickWidth = 540/col;
		brickHeight = 150/row;
		// Dit bepaalt de afmetingen van de bricks. 
	}
	public void draw(Graphics2D g) {
		for(int i = 0; i < map.length; i++) {
			for(int j=0; j< map[0].length; j++) {
				if(map[i][j] > 0) {
					g.setColor(new Color(246, 127, 0));
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					// Dit gedeelte bepaalt waar de bricks geraakt kunnen worden. 
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					// Dit bepaalt de ruimte tussen de bricks. 
				}
				
			}
		}
	}
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
		// dit zorgt ervoor dat de bal zorgt dat de bricks verdwijnen. 
	}
	
}