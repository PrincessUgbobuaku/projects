import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    static Scanner scan = new Scanner (System.in);

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    static String randomWord = randomWordFunction(words); // can be accessed by all functions
    
    public static void main(String[] args) {

        System.out.println(randomWord); //for prgrammer - used to see the word we're guessing. for testing purposes ONLY.

        System.out.println(gallows[0]);

        char missedGuesses[] = new char [6];
        Arrays.fill(missedGuesses, ' ');

        //String placeholder = "_" + " ";
        char [] placeholderArray = new char [randomWord.length()];
        Arrays.fill(placeholderArray, '_');

        int gallowsCounter = 0; //determines which gallows element will be printed
        
        updatePlaceholders(placeholderArray);

        printMissedGuesses(missedGuesses);


        while (gallowsCounter < missedGuesses.length) {

            System.out.print("\n\nGuess: ");
            char guess = scan.next().charAt(0);

            if(checkGuess(guess) == true) {

                System.out.println(gallows[gallowsCounter]);

                int placeholderLastIndex = indexes(guess, placeholderArray);

                placeholderArray[placeholderLastIndex] = guess; 
                int placeholderDashCounter = updatePlaceholders(placeholderArray);


                    printMissedGuesses(missedGuesses);

                if (placeholderDashCounter == 0) {
                    System.out.println("\n");
                    System.out.println("GOOD WORK!");
                    System.exit(0);
                }

                placeholderDashCounter = 0;
            }

            else if (checkGuess(guess) == false) {
                missedGuesses[gallowsCounter] = guess;
                
                gallowsCounter++; //counter for gallows printing

                System.out.println("\n\n\n");
                System.out.println(gallows[gallowsCounter]);

                updatePlaceholders(placeholderArray);

                printMissedGuesses(missedGuesses);

               
            } 

        
    
}
                    System.out.println("\n");
                    System.out.println("RIP!\n");
                    System.out.println("The word was: '" + randomWord + "'");
                    System.exit(0);

                    scan.close();


    }

    public static String randomWordFunction(String[] words) {
        double decimal = Math.random() * words.length;
        int integer = (int) decimal;

        return words[integer];
    } // gets a random word

    public static void printMissedGuesses(char missedGuesses[]) {
        System.out.println("\n");
                System.out.print("Misses: ");
                for (int i = 0; i < missedGuesses.length /*6*/; i++) {
                    System.out.print(missedGuesses[i]);
                }
    } //prints missed guesses 

    public static int updatePlaceholders(char placeholderArray[]) {
        int placeholderCounter = 0;

        System.out.print("Word: ");

                for (int i = 0; i < randomWord.length(); i++) {
                    System.out.print(" " + placeholderArray[i]);
                    if(placeholderArray[i] == '_') {
                        placeholderCounter++;
                        //System.out.println(placeholderCounter);
                    }
                } 

                return placeholderCounter;
    }

    public static boolean checkGuess(char guess) {

        for(int i = 0; i < randomWord.length(); i++) { //l is at 2
            if (guess == randomWord.charAt(i)) {
                return true; //placeholderArray[i] = guess;
            } 
        }
        return false;
    }

    public static int indexes(char guess, char[] placeholderArray) {
        int placeholderCounterLastIndex = randomWord.length() - 1;
    
            for(int i = randomWord.length() - 1; i >= 0; i--) { //l is at 2
                if (guess == randomWord.charAt(i)) {
                    int firstIndex = randomWord.indexOf(guess); //function; We are getting the index of guess in RW
                    placeholderArray[firstIndex] = guess;

                    break;
                }
                placeholderCounterLastIndex--;
            }
            return placeholderCounterLastIndex;
        }
}
    

    






