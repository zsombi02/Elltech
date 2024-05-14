import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameFieldView extends View
{
	private JPanel mainPanel;
	private Map<String, Point> pumps;
    private Map<String, Point> drains;
    private Map<String, Point> sources;
	private Map<String, String[]> pipes; // HashMap to store pipe connections
    private Map<String, String> players;
    private Map<String, ArrayList<Integer>> states;
    private Map<String, ArrayList<String>> directions;
	private String selectedItem; // Track the currently selected item for repositioning


	public GameFieldView(Game game)
	{
		pumps = new HashMap<>();
		drains = new HashMap<>();
		sources = new HashMap<>();  
		pipes = new HashMap<>();
        players = new HashMap<>();
        states = new HashMap<>();
        directions = new HashMap<>();
        getStarterFields(game);

		mainPanel = new JPanel(){
			protected void paintComponent(Graphics g) {
                super.paintComponent(g);

				RenderGameField(g);
			}

		};

		mainPanel.setPreferredSize(new Dimension(800, 600));
		mainPanel.setBackground(Color.BLACK);
		setBackground(Color.BLACK);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                selectedItem = getItemAtPosition(x, y);
            }

		});

		addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Update the position of the selected item based on the mouse drag
                if (selectedItem != null) {
                    int x = e.getX();
                    int y = e.getY();
                    updateItemPosition(selectedItem, x, y);
                    repaint();
                }
            }
        });

		
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
		setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        setVisible(true);

	}

    public void getStarterFields(Game game)
    {
        pumps.clear();
        drains.clear();
        sources.clear();
        pipes.clear();

        Point[] pumpPoints = {
            new Point(200, 200),
            new Point(400, 200),
            new Point(600, 200),
            new Point(200, 350),
            new Point(400, 350),
            new Point(600, 350),
           
        };

        Point[] drainPoints = {
            new Point(300, 500),
            new Point(500, 500),
            
        };

        Point[] sourcePoints = {
            new Point(300, 100),
            new Point(500, 100),
           
        };

        int pumpIndex = 0;
        int drainIndex = 0;
        int sourceIndex = 0;


        for(Field field : game.fields)
        {
            if (field.GetID().startsWith("Pump")) {
                pumps.put(field.GetID(), pumpPoints[pumpIndex]);
                directions.put(field.GetID(), field.getdirection());
                pumpIndex++;
            } 
            else if (field.GetID().startsWith("Drain")) {
                drains.put(field.GetID(), drainPoints[drainIndex]);
                drainIndex++;
            } 
            else if (field.GetID().startsWith("Source")) {
                sources.put(field.GetID(), sourcePoints[sourceIndex]);
                sourceIndex++;            } 
            else if (field.GetID().startsWith("Pipe")) {
                pipes.put(field.GetID(), new String[]{field.getends().get(0).GetID(), field.getends().get(1).GetID()});
            }
        }
        for(Player player : game.players)
        {
            players.put(player.GetID(), player.position.GetID());
            //System.out.println(player.GetID()+ "   "+player.position.GetID());
        }


    }


    
	public void RenderGameField(Graphics g)
	{	
          // Render the pipes
		   for (Map.Entry<String, String[]> entry : pipes.entrySet()) 
           {  
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(Color.WHITE);

                ArrayList<Integer> pipestate = states.get(entry.getKey());
                if(pipestate != null){
                if(pipestate.get(0)==1 && pipestate.get(1) == 0 && pipestate.get(2) == 0)
                g2d.setColor(Color.RED);
                if(pipestate.get(0)==1 && pipestate.get(1) == 1 && pipestate.get(2) == 0)
                g2d.setColor(Color.ORANGE);
                if(pipestate.get(0)==1 && pipestate.get(1) == 1 && (pipestate.get(2) == 1))
                g2d.setColor(Color.decode("#AA8B00"));
                if(pipestate.get(0)==0 && pipestate.get(1) == 1 && pipestate.get(2) == 0)
                g2d.setColor(Color.YELLOW);
                if(pipestate.get(0)==0 && pipestate.get(1) == 1 && pipestate.get(2) == 1)
                g2d.setColor(Color.decode("#9ACD32"));
                if(pipestate.get(0)==0 && pipestate.get(1) == 0 && pipestate.get(2) == 1)
                g2d.setColor(Color.GREEN);
                if(pipestate.get(0)==1 && pipestate.get(1) == 0 && pipestate.get(2) == 1)
                g2d.setColor(Color.decode("#B7410E"));
                }
                

                String id = entry.getKey();
                String[] pipeEnds = entry.getValue();
                String startItem = pipeEnds[0];
                String endItem = pipeEnds[1];
    
                Point startPoint = getItemPosition(startItem);
                Point endPoint = getItemPosition(endItem);
              
              
              if (startPoint != null && endPoint != null) {
                 //drawPipe(g2d, startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                 g2d.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                
                g.drawString(id, (startPoint.x + endPoint.x)/2 -10, (startPoint.y + endPoint.y)/2 -10 );
              }
           }
        //g.setFont(UIManager.getDefaults().getFont("Label.font"));
		 // Render the pumps as white circles
		 g.setColor(Color.WHITE);
		 for (Map.Entry<String, Point> entry : pumps.entrySet()) {
			 String id = entry.getKey();
			 Point pump = entry.getValue();

			 g.fillOval(pump.x-20, pump.y-20, 40, 40);
			 // Render the ID near the item
			 g.drawString(id, pump.x -10, pump.y + 40);
		 }
 
		 // Render the drains as blue triangles
		 g.setColor(Color.BLUE);
		 for (Map.Entry<String, Point> entry : drains.entrySet()) {
			 String id = entry.getKey();
			 Point drain = entry.getValue();
			 int[] xPoints = {drain.x, drain.x + 30, drain.x - 30};
			 int[] yPoints = {drain.y, drain.y + 60, drain.y + 60};
			 g.fillPolygon(xPoints, yPoints, 3);
			 // Render the ID near the item
			 g.drawString(id, drain.x -15, drain.y + 75);
		 }
 
		 // Render the sources as blue upside-down triangles
		 for (Map.Entry<String, Point> entry : sources.entrySet()) {
			 String id = entry.getKey();
			 Point source = entry.getValue();
			 int[] xPoints = {source.x, source.x + 30, source.x - 30};
			 int[] yPoints = {source.y, source.y - 60, source.y - 60};
			 g.fillPolygon(xPoints, yPoints, 3);
			 // Render the ID near the item
			 g.drawString(id, source.x - 15, source.y + 15);
		 }
         
         for (Map.Entry<String, ArrayList<String>> entry : directions.entrySet()) {
           // System.out.println(entry.getValue().get(0)+ "  " +entry.getValue().get(1));
            if (!entry.getValue().get(0).equals("N")) {
                Point input = getItemPosition(entry.getValue().get(0));
                Point item = getItemPosition(entry.getKey());
                Point moveditem = movePoint(item, input, 20);
                drawArrow(g, input, moveditem);
               
            }
            if (!entry.getValue().get(1).equals("N")) {
                Point output = getItemPosition(entry.getValue().get(1));
                Point item = getItemPosition(entry.getKey());
                Point moveditem = movePoint(item, output, 30);
                drawArrow(g, item, moveditem);
            }

        }
        

         //draw players
         for (Map.Entry<String, String> entry : players.entrySet()) 
         {  
            String positionID = entry.getValue();
            Point itemPosition = getItemPosition(positionID);
            
            if (positionID != null) {
                // Render the player ID(s) below each other
                ArrayList<String> playerIdsAtPosition = getPlayerIdsAtPosition(positionID);
                int yOffset = 0;
                for (String playerIdAtPosition : playerIdsAtPosition) {
   
                    g.setColor(Color.RED);
                    g.drawString(playerIdAtPosition, itemPosition.x - 5, itemPosition.y + 5 + yOffset);
                    yOffset += 15; // Adjust the vertical offset for the next player ID
                }
            }



         }

	}

    private void drawArrow(Graphics g, Point startPoint, Point endPoint) {
        Graphics2D g2d2 = (Graphics2D) g;
        g2d2.setStroke(new BasicStroke(3));
        g2d2.setColor(Color.GREEN);
        int dx = endPoint.x - startPoint.x;
        int dy = endPoint.y - startPoint.y;
        double angle = Math.atan2(dy, dx);
        int len = 10;
        int arrowX = (int) (endPoint.x - len * Math.cos(angle - Math.PI / 6));
        int arrowY = (int) (endPoint.y - len * Math.sin(angle - Math.PI / 6));
        g2d2.drawLine(endPoint.x, endPoint.y, arrowX, arrowY);
        arrowX = (int) (endPoint.x - len * Math.cos(angle + Math.PI / 6));
        arrowY = (int) (endPoint.y - len * Math.sin(angle + Math.PI / 6));
        g2d2.drawLine(endPoint.x, endPoint.y, arrowX, arrowY);
    }

    
    private Point movePoint(Point pointToMove, Point referencePoint, int distance) {
        double dx = referencePoint.x - pointToMove.x;
        double dy = referencePoint.y - pointToMove.y;
        double length = Math.sqrt(dx * dx + dy * dy);
        double directionX = dx / length;
        double directionY = dy / length;

        int moveX = (int) (directionX * distance);
        int moveY = (int) (directionY * distance);
        

        return new Point(pointToMove.x + moveX, pointToMove.y + moveY);
    }

    private ArrayList<String> getPlayerIdsAtPosition(String itemId) {
        ArrayList<String> playerIds = new ArrayList<>();
    
        // Iterate through the players to find matching item positions
        for (Map.Entry<String, String> entry : players.entrySet()) {
            String playerId = entry.getKey();
            String playerItemId = entry.getValue();
    
            if (playerItemId.equals(itemId)) {
                playerIds.add(playerId);
            }
        }
    
        return playerIds;
    }
    


	private Point getItemPosition(String itemId) {
        if (pumps.containsKey(itemId)) {
            return pumps.get(itemId);
        } else if (drains.containsKey(itemId)) {
            return drains.get(itemId);
        } else if (sources.containsKey(itemId)) {
            return sources.get(itemId);
        }
        else if (pipes.containsKey(itemId)){
            String[] ends = pipes.get(itemId);
            Point startPoint = getItemPosition(ends[0]);
            Point endPoint = getItemPosition(ends[1]);
            return new Point((startPoint.x + endPoint.x)/2, (startPoint.y + endPoint.y)/2);

        }
        return null;
    }
	


	private boolean isWithinRange(int x, int y, int itemX, int itemY) {
        int range = 15; // Define the range for selection
        return (x >= itemX - range && x <= itemX + range && y >= itemY - range && y <= itemY + range);
    }

	private String getItemAtPosition(int x, int y) {

        for (String id : pumps.keySet()) {
            Point pump = pumps.get(id);
            if (isWithinRange(x, y, pump.x, pump.y)) {
                return id;
            }
        }

        for (String id : drains.keySet()) {
            Point drain = drains.get(id);
            if (isWithinRange(x, y, drain.x, drain.y+20)) {
                return id;
            }
        }

        for (String id : sources.keySet()) {
            Point source = sources.get(id);
            if (isWithinRange(x, y, source.x, source.y-20)) {
                return id;
            }
        }

        return null;
    }

	private void updateItemPosition(String id, int x, int y) {
        if (pumps.containsKey(id)) {
            pumps.put(id, new Point(x, y));
        } else if (drains.containsKey(id)) {
            drains.put(id, new Point(x, y));
        } else if (sources.containsKey(id)) {
            sources.put(id, new Point(x, y));
        }
    }

	public void UpdateView (Game game)
    {
        Random random = new Random();
    
        for (Field field : game.fields) {
            if (field.GetID().startsWith("Pump")) {
                if (!pumps.containsKey(field.GetID())) {    
                    pumps.put(field.GetID(), new Point(random.nextInt(600) + 100, random.nextInt(250) + 150));
                }
                directions.put(field.GetID(), field.getdirection());    
            } else if (field.GetID().startsWith("Drain")) {
                if (!drains.containsKey(field.GetID())) {
                    drains.put(field.GetID(), new Point(random.nextInt(600) + 100, random.nextInt(100) + 450));
                }
            } else if (field.GetID().startsWith("Source")) {
                if (!sources.containsKey(field.GetID())) {
                    sources.put(field.GetID(), new Point(random.nextInt(600) + 100, random.nextInt(150) + 50));
                }
            } else if (field.GetID().startsWith("Pipe")) {

                String pipeID = field.GetID();
                if(pipes.containsKey(pipeID) &&  field.getends().size() <2)
                pipes.remove(pipeID);
                else if (!pipes.containsKey(pipeID) && field.getends().size() ==2) {
                    pipes.put(pipeID, new String[]{field.getends().get(0).GetID(), field.getends().get(1).GetID()});
                } 
                if(pipes.containsKey(pipeID)) {
                    String[] ends = pipes.get(pipeID);
                    if (!ends[0].equals(field.getends().get(0).GetID()) || !ends[1].equals(field.getends().get(1).GetID())) {
                        pipes.put(pipeID, new String[]{field.getends().get(0).GetID(), field.getends().get(1).GetID()});
                    }
                }
                if(!field.getstates().isEmpty())
                states.put(pipeID, field.getstates());
            }

        }
        for (Player player : game.players) {
            players.put(player.GetID(), player.position.GetID());
        }

        repaint();
    }

}
