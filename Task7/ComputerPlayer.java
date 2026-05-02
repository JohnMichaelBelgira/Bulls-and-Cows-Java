package Task7;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ComputerPlayer extends Player
{
    private String difficultyLevel="easy";
    private ArrayList<String> previousGuesses=new ArrayList<>();
    public void setDifficultyLevel(String difficultyLevel)
    {
        this.difficultyLevel=difficultyLevel.toLowerCase();
    }
    @Override
    public void setSecretCode()
    {
        this.secretCode=generateRandomCode();
    }
    @Override
    public String generateGuess()
    {
        String guess;
        if (difficultyLevel.equals("medium"))
        {
            //Medium AI keeps generating until we find a guess we haven't made yet
            do
            {
                guess=generateRandomCode();
            }
            while(previousGuesses.contains(guess));
            //Record the guess so we don't use it again
            previousGuesses.add(guess);
        }
        else
        {
            //Easy AI generates a random valid guess
            guess=generateRandomCode();
        }
        return guess;
    }
    private String generateRandomCode()
    {
        List<Integer> digits=new ArrayList<>();
        for (int i=0;i<10;i++) 
        {
            digits.add(i);
        }
        // Shuffle the list to randomize
        Collections.shuffle(digits);
        // Take the first 4 digits to guarantee they are unique
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<4;i++)
        {
            sb.append(digits.get(i));
        }
        return sb.toString();
    }
}