import java.io.PrintStream;
import java.util.ArrayList;

/**
 * A játék elemeit (mezők és játékosok) és lefutásának legfontosabb függvényeit
 * tartalmazza.
 */
public class Game
{
    /**
     * Engedelyezett-e a kommunikacio
     */
    protected static boolean debugEnabled;
    /**
     * az osztály minden példánya ugyanazt a debugOutput objektumot használja
     */
    protected static PrintStream debugOutput;
    /**
     * Szerelők pontjai
     */
    private int mechanicPoints;
    /**
     * Szabotőrök pontjai
     */
    private int saboteurPoints;
    /**
     * A fields eszköztár
     */
    protected ArrayList<Field> fields;
    /**
     * players eszköztár
     */
    protected ArrayList<Player> players;

    /**
     * metódus az adott osztályon belül hívható meg, és az osztály szintű
     * debugEnabled adattagot állítja be
     *
     * @param debugEnabled ennek az értéke állítódik be az osztályokban
     */
    public static void SetDebugEnabled(boolean debugEnabled)
    {
        Game.debugEnabled = debugEnabled;
        Field.debugEnabled = debugEnabled;
        Player.debugEnabled = debugEnabled;
    }

    /**
     * Konstruktor
     */
    public Game(PrintStream debugOutput)
    {
        SetDebugEnabled(false);
        Game.debugOutput = debugOutput;
        Field.debugOutput = debugOutput;
        Player.debugOutput = debugOutput;
        Drain.SetGame(this);
        Pipe.SetGame(this);
        mechanicPoints = 0;
        saboteurPoints = 0;
        fields = new ArrayList<>();
        players = new ArrayList<>();
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        fields.add(new Pipe());
        Field field;
        field = new Source();
        field.ConnectPipe((Pipe) GetFieldByID("Pipe1"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe2"));
        fields.add(field);
        field = new Source();
        field.ConnectPipe((Pipe) GetFieldByID("Pipe3"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe4"));
        fields.add(field);
        field = new Pump(10);
        field.ConnectPipe((Pipe) GetFieldByID("Pipe1"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe6"));
        field.SetPumpDirection((Pipe) GetFieldByID("Pipe1"), (Pipe) GetFieldByID("Pipe6"));
        fields.add(field);
        field = new Pump(10);
        field.ConnectPipe((Pipe) GetFieldByID("Pipe2"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe5"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe7"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe8"));
        field.SetPumpDirection((Pipe) GetFieldByID("Pipe5"), (Pipe) GetFieldByID("Pipe8"));
        fields.add(field);
        field = new Pump(10);
        field.ConnectPipe((Pipe) GetFieldByID("Pipe5"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe9"));
        field.SetPumpDirection((Pipe) GetFieldByID("Pipe9"), (Pipe) GetFieldByID("Pipe5"));
        fields.add(field);
        field = new Pump(10);
        field.ConnectPipe((Pipe) GetFieldByID("Pipe7"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe11"));
        field.SetPumpDirection((Pipe) GetFieldByID("Pipe7"), (Pipe) GetFieldByID("Pipe11"));
        fields.add(field);
        field = new Pump(10);
        field.ConnectPipe((Pipe) GetFieldByID("Pipe3"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe6"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe10"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe12"));
        field.SetPumpDirection((Pipe) GetFieldByID("Pipe3"), (Pipe) GetFieldByID("Pipe12"));
        fields.add(field);
        field = new Pump(10);
        field.ConnectPipe((Pipe) GetFieldByID("Pipe4"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe8"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe9"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe10"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe13"));
        field.SetPumpDirection((Pipe) GetFieldByID("Pipe8"), (Pipe) GetFieldByID("Pipe9"));
        fields.add(field);
        field = new Drain();
        field.ConnectPipe((Pipe) GetFieldByID("Pipe12"));
        fields.add(field);
        field = new Drain();
        field.ConnectPipe((Pipe) GetFieldByID("Pipe11"));
        field.ConnectPipe((Pipe) GetFieldByID("Pipe13"));
        fields.add(field);
       /*players.add(new Mechanic("Mechanic1", GetFieldByID("Drain1")));
        players.add(new Mechanic("Mechanic2", GetFieldByID("Drain2")));
        players.add(new Saboteur("Saboteur1", GetFieldByID("Source1")));
        players.add(new Saboteur("Saboteur2", GetFieldByID("Source2")));
        */
    }

    /**
     * lekéri a mező azonosítóját
     *
     * @param ID A keresett azonosito
     * @return A meghatarozott azonositoju mezo
     */
    public Field GetFieldByID(String ID)
    {
        for (Field field : fields)
        {
            if (field.GetID().equals(ID))
            {
                return field;
            }
        }
        return null;
    }

    /**
     * lekéri a játékos azonosítóját
     *
     * @param ID A keresett azonosito
     * @return A meghatarozott azonositoju jatekos
     */
    public Player GetPlayerByID(String ID)
    {
        for (Player player : players)
        {
            if (player.GetID().equals(ID))
            {
                return player;
            }
        }
        return null;
    }

    /**
     * Lépteti a játékosokat
     */
    public void Step()
    {
        for (Field field : fields)
        {
            field.Step();
        }
        if (debugEnabled)
        {
            debugOutput.println("");
        }
    }

    /**
     * A mechanic csapatnak pontot ad
     *
     * @param points a hozzaadando pontok
     */
    public void AddMechanicPoint(int points)
    {
        if (debugEnabled)
        {
            debugOutput.println("Game: " + points + " pont a szereloknek");
        }
        mechanicPoints += points;
    }

    /**
     * A saboteur csapatnak pontot ad
     *
     * @param points a hozzaadando pontok
     */
    public void AddSaboteurPoint(int points)
    {
        if (debugEnabled)
        {
            debugOutput.println("Game: " + points + " pont a szabotoroknek");
        }
        saboteurPoints += points;
    }

    public int GetMechanicPoint()
    {
        return mechanicPoints;
    }

    public int GetSaboteurPoint()
    {
        return saboteurPoints;
    }

    /**
     * A játékból való kilépés
     */
    public void Exit()
    {
        debugOutput.println("");
        debugOutput.println("");
        debugOutput.println("Game: A szereloknek " + mechanicPoints + " pontja van.");
        debugOutput.println("Game: A szabotoroknek " + saboteurPoints + " pontja van.");
        debugOutput.println("");
        if (mechanicPoints > saboteurPoints)
        {
            debugOutput.println("Game: A szerelok nyertek!");
        }
        else if (mechanicPoints < saboteurPoints)
        {
            debugOutput.println("Game: A szabotorok nyertek!");
        }
        else
        {
            debugOutput.println("Game: Dontetlen!");
        }
    }
}
