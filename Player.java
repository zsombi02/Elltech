public abstract class Player
{
	protected Field position;

	public Player(Field position)
	{
		this.position = position;
		position.InitAddPlayer(this);
		System.out.println("Player: Sikeres inicializalas");
	}

	/**
	 * Ha szabad a mezo, es szomszed, akkor atmozdul, es a jatekos poziciojat is
	 * beallitja a mezok mellett
	 * 
	 * @param newPosition a celmezo ahova lepni szeretne
	 * @return int
	 * 
	 */
	public int Move(Field newPosition)
	{
		if (newPosition.AddPlayer(this) == 0)
		{
			position.RemovePlayer(this);
			position = newPosition;
			System.out.println("Player: Sikeres mozgas");
			return 0;
		}
		else
		{
			System.out.println("Player: Sikertelen hozzaadas a mezohoz");
			return -1;
		}
	}
}
