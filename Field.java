import java.io.PrintStream;
import java.util.ArrayList;

/**
 * A játékpálya egy eleme/mezője. Ősosztálya az aktív elemeknek és a csőnek. Egy
 * játékos megkísérelhet rálépni. Tartalmaz alapértelmezett implemetnációkat
 */
public abstract class Field
{
    /**
     * Engedelyezett-e a kommunikacio
     */
    protected static boolean debugEnabled;
    /**
     * debug információ kimenetének céljára szolgál, hibakereséshez vagy az
     * alkalmazás állapotának ellenőrzéséhez
     */
    protected static PrintStream debugOutput;
    /**
     * Azonosító
     */
    protected String id;
    /**
     * Játékosok
     */
    protected ArrayList<Player> players;

    

    /**
     * Konstruktor
     */
    public Field()
    {
        players = new ArrayList<>();
    }

    public ArrayList<String> getdirection()
    {
        ArrayList<String> dir = new ArrayList<>();
        return dir;
    }

    public ArrayList<Integer> getstates()
    {   
        
        return new ArrayList<Integer>();
    }

    /**
     * az objektum szöveges reprezentációjának visszaadása
     *
     * @return A mezo szoveges megfeleloje
     */
    @Override
    public String toString()
    {
        return id;
    }

    /**
     * A mezo azonositójának lekérése
     *
     * @return A mezo azonositoja
     */
    public String GetID()
    {
        return id;
    }

    public ArrayList<Active> getends()
    {
        return null;
    }
    /**
     * lépés
     */
    public abstract void Step();

    /**
     * PLACEHOLDER
     *
     * @return PLACEHOLDER
     */
    protected boolean IsPlayerRemoveable()
    {
        return true;
    }

    /**
     * PLACEHOLDER
     *
     * @return PLACEHOLDER
     */
    protected boolean IsPlayerAddable()
    {
        return true;
    }

    /**
     * Player típusú objektumot vár, amelyet a metódus hozzáad a players nevű
     * listához
     *
     * @param player játékos
     * @return Hibakod
     */
    public int InitAddPlayer(Player player)
    {
        players.add(player);
        if (debugEnabled)
        {
            debugOutput.println("Field - " + this + ": Sikeres hozzaadas");
        }
        return 0;
    }

    /**
     * Player típusú objektumot vár, amelyet a metódus hozzáad a players nevű
     * listához
     *
     * @param player játékos
     * @return Hibakod
     */
    protected int AddPlayer(Player player)
    {
        players.add(player);
        if (debugEnabled)
        {
            debugOutput.println("Field - " + this + ": Sikeres hozzaadas");
        }
        return 0;
    }

    /**
     * Ha rajta all a player akkor leveszi Siker esetén 0 visszatérés
     *
     * @param player játékos
     * @return Hibakod
     */
    protected int RemovePlayer(Player player)
    {
        if (players.remove(player))
        {
            if (debugEnabled)
            {
                debugOutput.println("Field - " + this + ": Sikeres eltavolitas");
            }
            return 0;
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Field - " + this + ": Nincs ilyen jatekos");
            }
            return 2;
        }
    }

    /**
     * PLACEHOLDER
     *
     * @param player játékos
     * @return PLACEHOLDER
     */
    public Field MovePlayer(Player player)
    {
        if (IsNeighbour(player.position))
        {
            if (IsPlayerAddable() && player.position.IsPlayerRemoveable())
            {
                AddPlayer(player);
                player.position.RemovePlayer(player);
                return this;
            }
            else
            {
                if (debugEnabled)
                {
                    debugOutput.println("Field - " + this + ", " + player.position
                            + ": Nem lehetseges a mozgatas");
                }
                return null;
            }
        }
        else
        {
            if (debugEnabled)
            {
                debugOutput.println("Field - " + this + ": Nem szomszedos mezo");
            }
            return null;
        }
    }

    /**
     * Azt kell eldöntenie, hogy az adott mező szomszédos-e az általa kapott field
     * mezővel
     *
     * @param field mező
     */
    public abstract boolean IsNeighbour(Field field);

    /**
     * Kiírja a szomszédokat
     */
    public abstract void ListNeighbours();

    /**
     * Beállítja a pumpa ki-és bemenetet
     *
     * @param input  bemenet
     * @param output kimenet
     * @return Hibakod
     */
    public int SetPumpDirection(Pipe input, Pipe output)
    {
        return 1;
    }

    /**
     * Megjavítja az aktív elemet
     *
     * @return Hibakod
     */
    public int Repair()
    {
        return 1;
    }

    /**
     * Elrontja az aktív elemet
     *
     * @return Hibakod
     */
    public int Damage()
    {
        return 1;
    }

    /**
     * Egy pumpa pályárahelyezése
     *
     * @param pump pumpa
     * @return Hibakod
     */
    public int PlacePump(Pump pump)
    {
        return 1;
    }

    /**
     * Egy pumpa felvétele
     *
     * @return A felvett pumpa
     */
    public Pump PickUpPump()
    {
        return null;
    }

    /**
     * Egy cső csúszóssátétele
     *
     * @return Hibakod
     */
    public int MakeSlippy()
    {
        return 1;
    }

    /**
     * Egy cső ragadóssátétele
     *
     * @return Hibakod
     */
    public int MakeSticky()
    {
        return 1;
    }

    /**
     * Csö csatlakoztatása
     *
     * @param pipe csö
     * @return Hibakod
     */
    public int ConnectPipe(Pipe pipe)
    {
        return 1;
    }

    /**
     * Cső lecsatlakoztatása
     *
     * @param pipe csö
     * @return Hibakod
     */
    public int DisconnectPipe(Pipe pipe)
    {
        return 1;
    }

    /**
     * Csö felvétele
     *
     * @return A felvett cso
     */
    public Pipe PickUpPipe()
    {
        return null;
    }
}
