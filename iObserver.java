import java.util.ArrayList;

public interface iObserver
{
	
	public void NotifyGameUpdated(Game g);

	public void notifyGameStart(int players, int water);

	public void NotifyPlayerUpdated(ArrayList<Player> players);

	public void NotifyCommandSent(String command);

	public void NotifyGameEnded(String winner);
}
