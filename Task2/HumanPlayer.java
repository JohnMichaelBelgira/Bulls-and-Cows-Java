package Task2;
import java.util.Scanner;
public class HumanPlayer extends Player
{
    private Scanner in=new Scanner(System.in);
    @Override
    public void setSecretCode()
    {
        this.secretCode="0000";
    }
    @Override
    public String generateGuess()
    {
        System.out.print("Enter your 4-digit guess: ");
        return in.nextLine();
    }
}
