package Task2;
public class GameManager
{
    private int maxAttempts=7;
    private int currentAttempt=0;
    private String difficultyLevel="Easy";
    private HumanPlayer human;
    private ComputerPlayer computer;
    private CodeEvaluator evaluator;
    public GameManager() 
    {
        human=new HumanPlayer();
        computer=new ComputerPlayer();
        evaluator=new CodeEvaluator();
    }
    public void startGame()
    {
        //Computer generates its code at the start
        computer.setSecretCode();
        System.out.println("=== Welcome to Bulls and Cows ===");
        System.out.println("The computer has generated a 4-digit secret code (all digits are unique).");
        System.out.println("You have "+maxAttempts+" attempts to guess it.");
        //Main game loop
        while (!checkGameOver())
        {
            playRound();
        }
    }
    public void playRound()
    {
        currentAttempt++;
        System.out.println("\nAttempt "+currentAttempt+" of "+maxAttempts);
        String guess=human.generateGuess();
        //Validation to prevent crashes and ensure unique digits
        if (guess==null||guess.length()!=4||!guess.matches("\\d+")||!hasUniqueDigits(guess))
        {
            System.out.println("Invalid input! Please enter exactly 4 unique numbers.");
            currentAttempt--;
            return;
        }
        String targetCode=computer.getSecretCode();
        int bulls=evaluator.getBulls(guess, targetCode);
        int cows=evaluator.getCows(guess, targetCode);
        System.out.println("Result: "+bulls+" Bulls, "+cows+" Cows");
        //Win condition
        if (bulls==4)
        {
            System.out.println("\nCongratulations! You guessed the secret code: "+targetCode);
            System.out.println("You won the game in "+currentAttempt+" attempts!");
            currentAttempt=maxAttempts;// Force end of loop
        } 
        else if (currentAttempt>=maxAttempts)
        {
            System.out.println("\nGame Over! You have used all your attempts.");
            System.out.println("The computer's secret code was: "+targetCode);
        }
    }
    private boolean checkGameOver()
    {
        return currentAttempt>=maxAttempts;
    }
    //Method to verify no duplicate digits exist
    private boolean hasUniqueDigits(String str) 
    {
        for (int i=0;i<str.length();i++) 
        {
            for (int j=i+1;j<str.length();j++) 
            {
                if (str.charAt(i)==str.charAt(j)) 
                {
                    return false;
                }
            }
        }
        return true;
    }
}