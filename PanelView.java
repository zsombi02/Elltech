
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class PanelView extends View
{	
	int numberOfPlayers;
	ArrayList<String> commands = new ArrayList<>();
	JPanel playerPanel;
	JPanel commandsPanel;
	JPanel buttonsPanel;
	JButton bReset;
	JButton bDone;
	JLabel fullcommandLabel;
	TextField command = new TextField("", 15);
	private GameModel model;
	JPanel playerinventory;
	JPanel inventory;
	JLabel lInventory;

	public PanelView(int n, GameModel model)
	{	
		this.model = model;
		UIManager.put("Label.font", UIManager.getFont("Label.font").deriveFont((float) 16));
		numberOfPlayers = n;

		commands.addAll(Arrays.asList("Wait", "Damage", "Repair", "Sticky", "Slippy", "Move", "PickUpPipe", "PickUpPump", "ChangeDirection", "ConnectPipe", "DisconnectPipe", "PlacePump"));
		setPreferredSize(new Dimension(300, 600));
        setBackground(Color.BLACK);
		setLayout(new GridLayout(3, 1));
		RenderPanel();
		UpdatePlayer(model.game.players);
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        setVisible(true);

		bDone.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                // Notify observers of the state change

                model.NotifyObserversCommandSent(command.getText());
            }
		  }); 
		
		  bReset.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                // Notify observers of the state change

                command.setText("");
            }
		  }); 

	}

	public void DisplayPlayers()
	{	playerinventory = new JPanel(new GridLayout(2, 1));
		playerPanel = new JPanel(new GridLayout(numberOfPlayers, 2));
		playerPanel.setBackground(Color.BLACK);
		for (int i = 1; i <= numberOfPlayers; i++) {
			
            JLabel label = new JLabel("Mechanic" + i);
			label.setForeground(Color.WHITE);
            label.setName("Mechanic" + i); // Set a unique name for each label
            playerPanel.add(label);

			JLabel label2 = new JLabel("Saboteur" + i);
			label2.setForeground(Color.WHITE);
            label2.setName("Saboteur" + i); // Set a unique name for each label
            playerPanel.add(label2);

			label.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					label.setForeground(Color.RED);
				}
	
				@Override
				public void mouseExited(MouseEvent e) {
					label.setForeground(Color.WHITE);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {addtexttolabel(label.getText());}
			
			});

			
			label2.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					label2.setForeground(Color.RED);
				}
	
				@Override
				public void mouseExited(MouseEvent e) {
					label2.setForeground(Color.WHITE);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {addtexttolabel(label2.getText());}
			
			});

        }
		inventory = new JPanel();
		inventory.setBackground(Color.BLACK);
		lInventory = new JLabel();
		inventory.add(lInventory);
		playerinventory.add(playerPanel);
		playerinventory.add(inventory);
		add(playerinventory);

	}

	private void addtexttolabel(String text) {
		command.setText(command.getText() + text + " ");
	}
	
	public void DisplayActions()
	{
		commandsPanel = new JPanel(new GridLayout(commands.size()/2 +1, 2));
		commandsPanel.setBackground(Color.BLACK);
		for(int i = 0; i<=commands.size()-1; i++)
		{
			JLabel command = new JLabel(commands.get(i));
			command.setForeground(Color.WHITE);
			command.setName(commands.get(i));
			commandsPanel.add(command);
			//System.out.println(commands.get(i));
			
			command.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseEntered(MouseEvent e) {
					command.setForeground(Color.RED);
				}
	
				@Override
				public void mouseExited(MouseEvent e) {
					command.setForeground(Color.WHITE);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {addtexttolabel(command.getText());}
			
			});

		}	
		add(commandsPanel);		
	}
	
	public void RenderPanel()
	{
		DisplayPlayers();
		DisplayActions();
		JPanel otherPanel = new JPanel(new GridLayout(3, 1)); 
		otherPanel.setBackground(Color.BLACK);

		bReset = new JButton("Reset");
		bReset.setPreferredSize(new Dimension(80, 20));
		bReset.setBorder(new LineBorder(Color.WHITE));
		bReset.setOpaque(true);

		bDone = new JButton("Done");
		bDone.setPreferredSize(new Dimension(100, 40));
		bDone.setBorder(new LineBorder(Color.WHITE));
		bDone.setOpaque(true);

		fullcommandLabel = new JLabel("Mechanic1 Repair");
		fullcommandLabel.setForeground(Color.WHITE);

		otherPanel.add(bDone);
		otherPanel.add(bReset);
		//otherPanel.add(fullcommandLabel);
		otherPanel.add(command);
		add(otherPanel);
	}
	
	public void UpdatePlayer(ArrayList<Player> players) {
		// Build the HTML formatted text
		StringBuilder htmlText = new StringBuilder("<html><table>");
		ArrayList<String> headers = new ArrayList<>();
		ArrayList<ArrayList<String>> data = new ArrayList<>();
	
		for (Player p : players) {
			if (p.GetID().startsWith("Mechanic")) {
				headers.add(p.GetID());
					data.add(p.getInventory());

			}
		}
	
		// Determine the maximum number of rows
		int maxRows = 0;
		for (ArrayList<String> row : data) {
			maxRows = Math.max(maxRows, row.size());
		}
	
		// Add the header row
		htmlText.append("<tr>");
		for (String header : headers) {
			htmlText.append("<th>").append(header).append("</th>");
		}
		htmlText.append("</tr>");
	
		// Add the data rows as columns
		for (int i = 0; i < maxRows; i++) {
			htmlText.append("<tr>");
			for (ArrayList<String> row : data) {
				if (i < row.size()) {
					htmlText.append("<td>").append(row.get(i)).append("</td>");
				} else {
					htmlText.append("<td></td>");
				}
			}
			htmlText.append("</tr>");
		}
	
		// Close the HTML table
		htmlText.append("</table></html>");
	
		// Set the HTML formatted text to the label
		lInventory.setText(htmlText.toString());
	}
	

	
}
