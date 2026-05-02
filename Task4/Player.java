package Task4;
public abstract class Player
{
    protected String secretCode;
    public String getSecretCode()
    {
        return secretCode;
    }
    public abstract void setSecretCode();
    public abstract String generateGuess();
}