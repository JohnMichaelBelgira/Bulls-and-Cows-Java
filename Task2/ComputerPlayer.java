package Task2;
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
        return "0000";
    }
    private String generateRandomCode()
    {
        List<Integer>digits=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            digits.add(i);
        }
        Random rand=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<4;i++)
        {
            int index=rand.nextInt(digits.size());
            sb.append(digits.get(index));
            digits.remove(index);
        }
        return sb.toString();
    }
}