/**
 * A hegyekben található források. Innen származik a végtelen mennyiségű víz,
 * melyet a csapatok a játék során gyűjtenek.
 */
public class Source extends Active
{
    /**
     * Az adattag értéke határozza meg, hogy az adott osztályból eddig mennyi
     * példányosítás történt
     */
    protected static int instanceNr = 0;

    /**
     * Konstruktor
     */
    public Source()
    {
        instanceNr++;
        id = "Source" + instanceNr;
    }

    /**
     * lépés a játékban
     */
    @Override
    public void Step()
    {
        PushWater();
        if (debugEnabled)
        {
            debugOutput.println("");
        }
    }

    /**
     * Nyomja a vizet
     */
    protected void PushWater()
    {
        for (Pipe pipe : connectedPipes)
        {
            pipe.RecieveWater(Integer.MAX_VALUE);
        }
        if (debugEnabled)
        {
            debugOutput.println("Source - " + this + ": Az osszes kapcsolt cso feltoltve");
        }
    }
}
