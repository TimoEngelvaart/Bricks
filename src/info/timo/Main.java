package info.timo;


import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// Vanaf de main methode hierboven, start de applicatie. Dit is het hart. 
		// 'public' betekent dat andere variabelen en functies toegang hebben tot deze main. 
		// 'static' staat ervoor dat je methode statisch is. Dit is de enige methode die je hebt binnen je klasse. 
		// 'void' betekent dat hij niks terug zal geven.
		// Door eclipse wordt de 'main' methode 'main' genoemd. 
		// 'String [] args' zorgt ervoor dat hetgeen wat invoer een argument teruggeeft.
		
		JFrame obj = new JFrame();
		// Jframe levert een venster waarin ik mijn game kan weergeven. Deze heeft de bibliotheek 'JFrame' nodig om te functioneren. Deze is in de derde regel opgeroepen. 
		
		Gameplay gamePlay = new Gameplay();
		// Dit verwijst naar mijn Gameplay klasse. 
		
		obj.setBounds(10, 10, 700, 600);
		// Hier bepaal ik de afmetingen van de game in het venster .
		
		obj.setTitle("Breakout Ball");
		// Hier geef ik een naam voor het spel
		
		obj.setResizable(false);
		// Dit betekent dat het formaat van het venster niet kan worden gewijzigd. 
		
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Dit zorgt ervoor dat het programma zichzelf helemaal afsluit als je op kruisje drukt. Er zal geen proces meer runnen in de taskmanager.
		
		obj.add(gamePlay);
		// Hiermee heb ik gameplay toegevoegd aan Jframe.
		
		obj.setVisible(true);
		// Hier zorg ik ervoor dat de game zichtbaar is. 

	}

}
