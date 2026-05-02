package Task3;
import java.util.Scanner;
public class HumanPlayer extends Player
{
    private Scanner in=new Scanner(System.in);
    @Override
    public void setSecretCode()
    {
        String code;
        while (true)
        {
            System.out.print("Enter your 4-digit secret code for the computer to guess (unique digits): ");
            code=in.nextLine();
            if (isValid(code))
            {
                this.secretCode=code;
                break;
            } 
            else
            {
                System.out.println("Invalid code! Please ensure it is exactly 4 unique digits.");
            }
        }
    }
    @Override
    public String generateGuess()
    {
        String guess;
        while (true)
        {
            System.out.print("Enter your 4-digit guess: ");
            guess=in.nextLine();
            if (isValid(guess))
            {
                return guess;
            }
            else
            {
                System.out.println("Invalid guess! Please ensure it is exactly 4 unique digits.");
            }
        }
    }
    //Validation helper to ensure it's exactly 4 unique numerical digits
    private boolean isValid(String code)
    {
        if (code==null||code.length()!=4||!code.matches("\\d+"))
        {
            return false;
        }
        // Check for unique digits
        for (int i=0;i<code.length();i++) 
        {
            for (int j=i+1;j<code.length();j++) 
            {
                if (code.charAt(i)==code.charAt(j)) 
                {
                    return false;
                }
            }
        }
        return true;
    }
}