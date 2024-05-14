import java.util.ArrayList;

public class GameModel
{

	public static Game game = new Game(System.out);
	protected ArrayList<iObserver> observers;
    int watertarget;

	public GameModel()
	{
		observers = new ArrayList<>();
	}

	public void AddObserver(iObserver o)
	{
		observers.add(o);
	}
	
	public void RemoveObserver(iObserver o)
	{
		observers.remove(o);
	}
	
	public void NotifyObserversGameUpdated(Game game)
	{	
		for(iObserver observer : observers)
		{
			observer.NotifyGameUpdated(game);
		}
	}
	
	public void NotifyObserversPlayerUpdated(ArrayList<Player> players)
	{	
		for(iObserver observer : observers)
		{
			observer.NotifyPlayerUpdated(players);
		}
	}

	public void NotifyObserversGameStarted(int n, int water)
	{	
        watertarget = water;
		for(iObserver observer : observers)
		{
			observer.notifyGameStart(n, water);;
		}
	}

	public void NotifyObserversCommandSent(String command)
	{
		for(iObserver observer : observers)
		{
			observer.NotifyCommandSent(command);;
		}
	}

    public void NotifyObserversGameEnded(String winner)
    {
        for(iObserver observer : observers)
        {
            observer.NotifyGameEnded(winner);
        }
    }
	
	
	public void PerformAction(String action)
	{
		System.out.println(action);
		if(Interpreter(action) == 0)
		{
			String[] command = action.split(" ");
			

			NotifyObserversGameUpdated(game);
            NotifyObserversPlayerUpdated(game.players);
            game.Step();
            System.out.println(game.GetMechanicPoint());
            System.out.println(game.GetSaboteurPoint());
            if(game.GetMechanicPoint() >= watertarget)
            NotifyObserversGameEnded("Mechanic");
            if(game.GetSaboteurPoint() >= watertarget)
            NotifyObserversGameEnded("Saboteur");
            
		}
	}

	public int Interpreter(String input)
    {
        return Interpreter(input, null);
    }

    /**
     * Az Interpreter metódus felelős a játékos parancsok értelmezéséért és
     * végrehajtásáért a játékban.
     */
    public int Interpreter(String input, Player player)
    {
        String[] command = input.split(" ");
        if (player == null && command.length > 1)
        {
            player = game.GetPlayerByID(command[1]);
        }
        
        switch (command[0].toLowerCase())
        {
     
        case "wait":
        {
            return 0;
        }
        case "listinventory":
        {
            return player.ListInventory();
        }
        case "listneighbours":
        {
            return player.ListNeighbours();
        }
        case "move":
        {	
            return player.Move(game.GetFieldByID(command[2]));
        }
        case "repair":
        {
            return player.Repair();
        }
        case "damage":
        {
            return player.Damage();
        }
        case "changedirection":
        {
            return player.SetPumpDirection((Pipe) game.GetFieldByID(command[2]),
                    (Pipe) game.GetFieldByID(command[3]));
        }
        case "placepump":
        {
            return player.PlacePump(player.GetPumpFromInventoryByID(command[2]));
        }
        case "pickuppump":
        {
            return player.PickUpPump();
        }
        case "slippy":
        {
            return player.MakeSlippy();
        }
        case "sticky":
        {
            return player.MakeSticky();
        }
        case "connectpipe":
        {
            return player.ConnectPipe(player.GetPipeFromInventoryByID(command[2]));
        }
        case "disconnectpipe":
        {
            return player.DisconnectPipe((Pipe) game.GetFieldByID(command[2]));
        }
        case "pickuppipe":
        {
            return player.PickUpPipe();
        }
        
        default:
        {
            System.out.println("Ervenytelen parancs: " + input);
            return -1;
        }
        }


    }
}
