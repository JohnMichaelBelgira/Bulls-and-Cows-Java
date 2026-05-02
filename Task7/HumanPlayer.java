package Task7;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class HumanPlayer extends Player
{
    private Scanner in=new Scanner(System.in);
    private Queue<String> preSuppliedGuesses=new LinkedList<>();
    public void loadGuessesFromFile(String filename) throws FileNotFoundException
    {
        File file=new File(filename);
        Scanner fileScanner=new Scanner(file);
        while (fileScanner.hasNextLine())
        {
            String line=fileScanner.nextLine().trim();
            if (!line.isEmpty())
            {
                preSuppliedGuesses.add(line);
            }
        }
        fileScanner.close();
    }
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
        if (!preSuppliedGuesses.isEmpty())
        {
            String guess=preSuppliedGuesses.poll();
            System.out.println("Auto-guessing from file: "+guess);
            return guess;
        }
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