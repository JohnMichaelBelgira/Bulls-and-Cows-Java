package Task8;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class GameManager
{
    // ==========================================
    // Feel free to edit these to change the rules of the game
    // ==========================================
    public static final int CONFIG_MAX_TURNS=7;
    public static final int CONFIG_CODE_LENGTH=4;
    // ==========================================
    
    private int maxAttempts=CONFIG_MAX_TURNS;
    private int currentAttempt=0;
    private HumanPlayer human;
    private ComputerPlayer computer;
    private CodeEvaluator evaluator;
    private Scanner scanner;
    private StringBuilder gameLog;
    public GameManager()
    {
        human=new HumanPlayer();
        computer=new ComputerPlayer();
        evaluator=new CodeEvaluator();
        scanner=new Scanner(System.in);
        gameLog=new StringBuilder();
    }
    public void startGame()
    {
        System.out.println("=== Welcome to Bulls and Cows ===");
        //Strict validation loop for input method
        String inputMethod="";
        while (!inputMethod.equals("M")&&!inputMethod.equals("F"))
        {
            System.out.print("Do you want to enter guesses manually (M) or from a file (F)? ");
            inputMethod=scanner.nextLine().trim().toUpperCase();
        }
        if (inputMethod.equals("F"))
        {
            boolean fileLoaded=false;
            while (!fileLoaded)
            {
                System.out.print("Enter the filename (e.g., input.txt): ");
                String filename=scanner.nextLine().trim();
                File file=new File(filename);
                if (file.exists()&&!file.isDirectory())
                {
                    try
                    {
                        human.loadGuessesFromFile(filename);
                        fileLoaded=true;
                        System.out.println("Successfully loaded guesses from "+filename);
                    }
                    catch(FileNotFoundException e)
                    {
                        System.out.println("Error reading file. Please try again.");
                    }
                } 
                else
                {
                    System.out.println("File not found! Please enter a valid filename.");
                }
            }
        }
        //Ask for difficulty
        String difficulty="";
        while (!difficulty.equals("easy")&&!difficulty.equals("medium"))
        {
            System.out.print("Choose AI opponent (easy / medium): ");
            difficulty=scanner.nextLine().trim().toLowerCase();
        }
        computer.setDifficultyLevel(difficulty);
        //Computer generates its code at the start
        computer.setSecretCode();
        System.out.println("The computer has generated a "+CONFIG_CODE_LENGTH+"-digit secret code.");
        //Player enters their secret code for the computer to guess
        human.setSecretCode();
        //Initializing log with secret codes
        gameLog.append("Bulls & Cows game result.\n");
        gameLog.append("Your code: ").append(human.getSecretCode()).append("\n");
        gameLog.append("Computer's code: ").append(computer.getSecretCode()).append("\n");
        gameLog.append("---\n");
        System.out.println("\nGame Starts! Both sides have up to "+maxAttempts+" attempts.");
        boolean humanWon=false;
        boolean computerWon=false;
        //Main game loop
        while (currentAttempt<maxAttempts&&!humanWon&&!computerWon)
        {
            currentAttempt++;
            System.out.println("\n--- Round "+currentAttempt+" ---");
            gameLog.append("Turn ").append(currentAttempt).append(":\n");
            //HUMAN TURN
            System.out.println("Your Turn:");
            String humanGuess=human.generateGuess();
            String computerSecret=computer.getSecretCode();
            int humanBulls=evaluator.getBulls(humanGuess, computerSecret);
            int humanCows=evaluator.getCows(humanGuess, computerSecret);
            System.out.println("Result: "+humanBulls+" Bulls, "+humanCows+" Cows");
            //Log human turn
            gameLog.append("You guessed ").append(humanGuess)
                   .append(", scoring ").append(humanBulls).append(humanBulls==1? " bull and ":" bulls and ")
                   .append(humanCows).append(humanCows==1? " cow\n":" cows\n");
            // Check win against dynamic code length
            if (humanBulls==CONFIG_CODE_LENGTH)
            {
                humanWon=true;
            }
            //COMPUTER TURN
            System.out.println("\nComputer's Turn:");
            String computerGuess=computer.generateGuess();
            System.out.println("Computer guessed: "+computerGuess);
            String humanSecret=human.getSecretCode();
            int computerBulls=evaluator.getBulls(computerGuess, humanSecret);
            int computerCows=evaluator.getCows(computerGuess, humanSecret);
            System.out.println("Result: "+computerBulls+" Bulls, "+computerCows+" Cows");
            //Log computer turn
            gameLog.append("Computer guessed ").append(computerGuess)
                   .append(", scoring ").append(computerBulls).append(computerBulls==1?" bull and ":" bulls and ")
                   .append(computerCows).append(computerCows==1?" cow\n":" cows\n");
            gameLog.append("---\n");
            // Check win against dynamic code length
            if (computerBulls==CONFIG_CODE_LENGTH)
            {
                computerWon=true;
            }
        }
        //GAME END
        System.out.println("\n=== Game Over ===");
        if (humanWon&&computerWon)
        {
            System.out.println("It's a Tie! Both of you guessed the correct code in round "+currentAttempt+".");
            System.out.println("Computer's code was: "+computer.getSecretCode());
            gameLog.append("It's a Tie! Both players guessed correctly.\n");
        }
        else if (humanWon)
        {
            System.out.println("Congratulations! You guessed the computer's code: "+computer.getSecretCode());
            System.out.println("You won in "+currentAttempt+" attempts!");
            gameLog.append("You win! :)\n");
        } 
        else if (computerWon)
        {
            System.out.println("The computer won! It guessed your secret code: "+human.getSecretCode());
            System.out.println("Computer's code was: "+computer.getSecretCode());
            gameLog.append("Computer wins! :(\n");
        }
        else
        {
            System.out.println("It's a draw! Both sides used all "+maxAttempts+" attempts without guessing the code.");
            System.out.println("The computer's secret code was: "+computer.getSecretCode());
            gameLog.append("It's a draw! No one guessed the code.\n");
        }
        //Save file
        saveGameLog();
    }
    private void saveGameLog()
    {
        System.out.print("\nDo you wish to save the results to a text file? (Y/N): ");
        String response=scanner.nextLine().trim().toUpperCase();
        if (response.equals("Y"))
        {
            System.out.print("Enter the filename (e.g., results.txt): ");
            String filename=scanner.nextLine().trim();
            try (PrintWriter out=new PrintWriter(new FileWriter(filename)))
            {
                out.print(gameLog.toString());
                System.out.println("Results successfully saved to "+filename);
            }
            catch (IOException e)
            {
                System.out.println("An error occurred while saving the file: "+e.getMessage());
            }
        }
    }
}