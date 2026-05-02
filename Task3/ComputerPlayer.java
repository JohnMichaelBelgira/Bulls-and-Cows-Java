package Task3;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ComputerPlayer extends Player 
{
    public ComputerPlayer()
    {
        super();
    }
    @Override
    public void setSecretCode()
    {
        this.secretCode=generateRandomCode();
    }
    @Override
    public String generateGuess()
    {
        //Easy AI generates a random valid guess
        return generateRandomCode();
    }
    private String generateRandomCode()
    {
        List<Integer> digits=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            digits.add(i);
        }   
        Random rand=new Random();
        StringBuilder sb=new StringBuilder();
        //Loop exactly 4 times to pull 4 unique digits
        for(int i=0;i<4;i++)
        {
            //Pick a random index based on the curent size of the list
            int index=rand.nextInt(digits.size());
            sb.append(digits.get(index));
            //Remove the used digit so it cannot be selected again
            digits.remove(index);
        }
        return sb.toString();
    }
}