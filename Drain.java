/**
 * A városokban található ciszterna. A szerelők csapata ide gyűjti a vizet. Egy
 * speciális példánya jelképezi a homokot. Ez a példány tartja nyilván a
 * szabotőrök által gyűjtött vizet.
 */
public class Drain extends Active
{
    /**
     * Az aktív példányok száma, amelyeket már létrehoztak az adott osztályból
     */
    protected static int instanceNr = 0;
    /**
     * game létrehozása
     */
    protected static Game game;

    /**
     * az osztályon belüli és az osztályon kívüli metódusok hívják, hogy elérjék és
     * beállítsák a game változó értékét
     *
     * @param game a jatek entitasa
     */
    public static void SetGame(Game game)
    {
        Drain.game = game;
    }

    /**
     * Konstruktor
     */
    public Drain()
    {
        instanceNr++;
        id = "Drain" + instanceNr;
    }

    /**
     * lépéseket hajt végre a játékban, meghívja a PullWater nevű metódust, mely
     * vizsgálatot hajt végre a játék vízének lefolyásával kapcsolatban, ha a
     * logikai változó értéke igaz, egy új sor kerül a kimenetre
     */
    @Override
    public void Step()
    {
        PullWater();
        if (debugEnabled)
        {
            debugOutput.println("");
        }
    }

    /**
     * Szivja a vizet
     */
    protected void PullWater()
    {
        for (Pipe pipe : connectedPipes)
        {
            game.AddMechanicPoint(pipe.TransmitWater(Integer.MAX_VALUE));
        }
        if (debugEnabled)
        {
            debugOutput.println("Drain - " + this + ": Az osszes kapcsolt cso leszivva");
        }
    }

    /**
     * akkor hívódik meg, ha egy szerelő felvesz egy pumpát
     *
     * @return A felvett pumpa
     */
    @Override
    public Pump PickUpPump()
    {
        Pump newPump = new Pump(5);
        //game.fields.add(newPump);
        if (debugEnabled)
        {
            debugOutput.println("Drain - " + this + ": Sikeres pumpa felvetel");
        }
        return newPump;
    }

    /**
     * akkor hívódik meg, ha egy szerelő felvesz egy csövet
     *
     * @return A felvett cso
     */
    @Override
    public Pipe PickUpPipe()
    {
        Pipe newPipe = new Pipe();
        ConnectPipe(newPipe);
        game.fields.add(newPipe);
        if (debugEnabled)
        {
            debugOutput.println("Drain - " + this + ": Sikeres cso felvetel");
        }
        return newPipe;
    }
}
