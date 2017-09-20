package info.timo;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	// Ik gebruik hier KeyListener zodat de pijltoetsen gebruikt kunnen worden om het balkje te besturen.
	// Ik gebruik hier ActionListener om de bal te laten bewegen. 
	
		private boolean play = false; 
		// Anders start de game uit zichzelf
		
		private int score = 0;
		// Omdat de startscore 0 is
		
		private int totalBricks = 21;
		// Zo start je met 21 blokken die je weg moet spelen
		
		private Timer timer;
		private int delay = 8;
		// Dit bepaalt de snelheid van de bal
		
		private int playerX = 310;
		// Dit is de startpositie van de speler
		
		private int ballposX = 120;
		private int ballposY = 350;
		// Startpositie van de bal
		
		private int ballXdir = -1;
		private int ballYdir = -2;
		// Dit bepaalt de richting van de bal
		
		private MapGenerator map;
		// Dit roept de klasse mapgenerator op. 
		
		public Gameplay() {
			map = new MapGenerator(3, 7);
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			timer = new Timer(delay, this);
			timer.start();
			// Dit gedeelte activeert de map, keylistener en setfocusable. daarnaast zorgt dit stukje code dat het spel start. 
		}
		
		public void paint(Graphics g) {
			// De achtergrond
			g.setColor(Color.black);
			g.fillRect(1,1, 692, 592);
			// Hoe groot de achtergrond is. 
			
			// De blokjes waar je tegenaan schiet
			map.draw((Graphics2D)g);
			
			// De randen rondom het spel
			g.setColor(new Color(246, 127, 0));
			g.fillRect(0, 0, 3, 592);
			g.fillRect(0, 0, 692, 3);
			g.fillRect(691, 0, 3, 592);
			
			// De score
			g.setColor(Color.white);
			g.setFont(new Font("sans serif", Font.BOLD, 25));
			g.drawString(""+score,  590,  30);
			
			// Het balkje
			g.setColor(new Color(246, 127, 0));
			// oranje is geen standaard kleur, dus die wordt op deze manier benoemd. 
			g.fillRect(playerX, 550, 100, 8);
			
			// De bal
			g.setColor(Color.white);
			g.fillOval(ballposX, ballposY, 20, 20);
			
			if(totalBricks <= 0) {
				play = false;
				ballXdir = 0;
				ballYdir = 0;
				g.setColor(Color.white);
				g.setFont(new Font("sans serif", Font.BOLD, 30));
				g.drawString("You Won",  260,  300);
			//Als iemand alle bricks wegspeelt, worden deze lijnen code in werking gesteld.  
				
				g.setFont(new Font("sans serif", Font.BOLD, 20));
				g.drawString("Press Enter to Restart",  230,  350);
			// ik heb gekozen voor een sans serif lettertype. 
			
			}
			
			if(ballposY > 570) {
				play = false;
				ballXdir = 0;
				ballYdir = 0;
				g.setColor(Color.white);
				g.setFont(new Font("sans serif", Font.BOLD, 30));
				g.drawString("Game Over, Score: " +score,  190,  300);
				// Dit wordt in werking gesteld wanneer iemand het balletje mist met het balkje. 
				
				g.setFont(new Font("sans serif", Font.BOLD, 20));
				g.drawString("Press Enter to Restart",  230,  350);
				// Ook hier heb ik gekozen voor een sans serif letterype. 
			}
			
			g.dispose();
			// Verwijdert de code hierboven als het niet in gebruik is. 
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Automatisch gegenereerd door KeyListener
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;
				// Dit zorgt ervoor dat de bal het balkje raakt en er niet doorheen vliegt. 
			}	
			
			A: for(int i = 0; i<map.map.length; i++) {
				for(int j = 0; j<map.map[0].length; j++) {
					if(map.map[i][j] > 0 ) {
						// Dit is een loopje dat wordt afgespeeld. 
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						// Dit is om de brick te detecteren. 
						
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						// Om de rectangle om de bricks en de bal te detecteren 
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,  i,  j);;
							totalBricks--;
							score += 5;
							// Dit gedeelte code zorgt ervoor dat de persoon 5 punten per weggespeelde brick krijgt. 
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}
							// Dit gedeelte zorgt ervoor dat de bal tegen de andere bricks aanketst en er niet doorheen gaat. 
							break A;
						}
					}
				
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			// Dit zorgt ervoor dat de bal de tegen de muren kaatst. 
			}
		}
		
		repaint();
		// zal paint opnieuw oproepen
			
	}

	@Override
	public void keyTyped(KeyEvent e) {}
		// Automatisch gegenereerd door ActionListener
	@Override
	public void keyReleased(KeyEvent e) {}
		// Automatisch gegenereerd door ActionListener
		
	@Override
	public void keyPressed(KeyEvent e) {
		// Automatisch gegenereerd door ActionListener
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >=600) {
				playerX = 600;
			} else {
				moveRight();
				// Als het balkje zich niet volledig aan de rechterkant van het venster bevindt, zal het balkje naar rechts verplaatsen.
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
				// // Als het balkje zich niet volledig aan de linkerkant van het venster bevindt, zal het balkje naar links verplaatsen.
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -1;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
				// Dit bepaalt de positie van de bal, het balkje, het aantal bricks en de score wanneer iemand, nadat hij is afgegaan, weer op enter drukt. 
				
				repaint();
				// dit roept de vorige lijnen code nogmaals op. 
				
			
			}
		}
		
	}
	public void moveRight() {
		play = true;
		// Play stond bovenaan op false. 
		playerX+=20;
		//Als iemand op het rechter pijltje drukt, gaat het balkje 20 pixels naar rechts. 
	}
	
	public void moveLeft() {
		play = true;
		//Play stond bovenaan op false. 
		playerX-=20;
		//Als iemand op het linker pijltje drukt, gaat het balkje 20 pixels naar links. 
	}
	
	
}
