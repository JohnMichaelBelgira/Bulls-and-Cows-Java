package Task8;
public class CodeEvaluator
{
    public int getBulls(String guess, String target)
    {
        int bulls=0;
        for (int i=0;i<GameManager.CONFIG_CODE_LENGTH;i++)
        {
            if (guess.charAt(i)==target.charAt(i))
            {
                bulls++;
            }
        }
        return bulls;
    }
    public int getCows(String guess, String target)
    {
        int cows=0;
        for (int i=0;i<GameManager.CONFIG_CODE_LENGTH;i++)
        {
            //If it is not a bull, but the target contains the guessed digit
            if (guess.charAt(i)!=target.charAt(i)&&target.indexOf(guess.charAt(i))!=-1)
            {
                cows++;
            }
        }
        return cows;
    }
}