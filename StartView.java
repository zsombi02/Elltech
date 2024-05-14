import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartView extends View 
{	
	JLabel players;
	JTextField tfPlayers;
	JLabel water;
	JTextField tfWater;
	JButton startButton;
	JPanel playerPanel;
	JPanel waterPanel;
	JPanel buttonPanel;
	private GameModel model;


	public StartView(GameModel model){
		this.model = model;
		RenderStart();

		
	}

	
	public void RenderStart()
	{
		
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Create the label and text field
        players = new JLabel("Players:");
        players.setForeground(Color.WHITE);
        tfPlayers = new JTextField("", 10);
		water = new JLabel("Water amount:");
		water.setForeground(Color.WHITE);
		tfWater = new JTextField("", 10);

        // Create the start button
    	startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 30));

        // Create panels for organizing components
        playerPanel = new JPanel();
        playerPanel.setBackground(Color.BLACK);
        playerPanel.add(players);
        playerPanel.add(tfPlayers);

		waterPanel = new JPanel();
		waterPanel.setBackground(Color.BLACK);
		waterPanel.add(water);
		waterPanel.add(tfWater);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(startButton);

		// Create a container panel to hold the input panels
        JPanel containerPanel = new JPanel(new GridLayout(2, 1));
        containerPanel.setBackground(Color.BLACK);
        containerPanel.add(playerPanel);
        containerPanel.add(waterPanel);

		  // Add ActionListener to the startButton
		  startButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                // Notify observers of the state change
				int playernumber;
				try
				{playernumber = Integer.parseInt(tfPlayers.getText());}
				catch(NumberFormatException ex)
				{playernumber = 2;}

				if(playernumber > 10 || playernumber < 0)
				playernumber = 2;

				int water;
				try
				{water = Integer.parseInt(tfWater.getText());}
				catch(NumberFormatException ee)
				{water = 750;}
				if(water > 3000 || water < 0)
				water = 750;
                model.NotifyObserversGameStarted(playernumber, water);
            }
		  }); 
            
     

        // Add the panels to the main frame
		add(containerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
	}


}
