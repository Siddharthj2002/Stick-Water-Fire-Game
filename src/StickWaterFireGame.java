import java.util.Random;

/* This class encapsulates the state and logic required to play the 
   Stick, Water, Fire game. The game is played between a user and the computer. 
   A user enters their choice, either S for stick, F for fire, W for water, and 
   the computer generates one of these choices at random- all equally likely.
   The two choices are evaluated according to the rules of the game and the winner
   is declared.
   
   Rules of the game:
   S beats W
   W beats F
   F beats S
   no winner on a tie.
   
   Each round is executed by the playRound method. In addition to generating the computer 
   choice and evaluating the two choices, this class also keeps track of the user and computer
   scores, the number of wins, and the total number of rounds that have been played. In the case
   of a tie, neither score is updated, but the number of rounds is incremented.
   
   NOTE: Do not modify any of the code that is provided in the starter project. Additional instance variables and methods 
   are not required to make the program work correctly, but you may add them if you wish as long as
   you fulfill the project requirements.
   */
   
public class StickWaterFireGame {

   private int numRounds;
   private int playerScore;
   private int compScore;
   private boolean playerWins;
   private boolean isTie;
   private String playerMove;
   private String compMove;
   private Random rand;
   
  /*  This constructor assigns the member Random variable, rand, to 
   *  a new, unseeded Random object.
   *  It also initializes the instance variables to their default values:
   *  rounds, player and computer scores will be 0, the playerWins and isTie
   *  variables should be set to false.
   */ 
   public StickWaterFireGame() { // Unseeded constructor.
      rand = new Random();
      numRounds = 0;
      playerScore = 0;
      compScore = 0;
      playerWins = false;
      isTie = false; 
   }
       
  /*  This constructor assigns the member Random variable, rand, to 
   *  a new Random object using the seed passed in.
   *  It also initializes the instance variables to their default values:
   *  rounds, player and computer scores will be 0, the playerWins and isTie
   *  variables should be set to false.
   */    
   public StickWaterFireGame(int seed) { // Seeded constructor
      rand = new Random(seed);
      numRounds = 0;
      playerScore = 0;
      compScore = 0;
      playerWins = false;
      isTie = false; 
   }   

  /*  This method returns true if the inputStr passed in is
   *  either "S", "W", or "F", false otherwise.
   *  Note that the input can be upper or lower case.
   */ 
   public boolean isValidInput(String inputStr) {
      if(inputStr.equalsIgnoreCase("S") || inputStr.equalsIgnoreCase("F") || inputStr.equalsIgnoreCase("W")) {
         return true;   
      } else {
         //Incrementing the computer score and number of rounds even if the input is invalid 
         compScore++;
         numRounds++;
         return false;
      }
   }
       
  /*  This method carries out a single round of play of the SWF game. 
   *  It calls the isValidInput method and the getRandomChoice method. 
   *  It implements the rules of the game and updates the instance variables
   *  according to those rules.
   */
   public void playRound(String playerChoice) {
      
      // Resetting the game specific variables.
      playerWins = false;
      isTie = false;
      
      // Getting the computer's choice
      compMove = getRandomChoice();
      
      // Validating the player's input and implementing the game's rules.
      if(isValidInput(playerChoice)) { 
         playerMove = playerChoice.toLowerCase();
         numRounds++;
         if (playerWins()) {
            playerScore++;
            playerWins = true;
         }  else if (isTie()) {
            isTie = true;
         }  else {
            compScore++;
         }
      }
   }
    
   // Returns the choice of the computer for the most recent round of play.
   public String getComputerChoice() {
      return compMove;
   }
   
   // Returns true if the player has won the last round, false otherwise.    
   public boolean playerWins() {
      if ((playerMove.equalsIgnoreCase("s")) && (compMove.equalsIgnoreCase("w"))) { // Stick beats water, hence, player wins.
         return true;
      }  else if ((playerMove.equalsIgnoreCase("w")) && (compMove.equalsIgnoreCase("f"))) { // Water beats fire, hence, player wins.
         return true;
      }  else if ((playerMove.equalsIgnoreCase("f")) && (compMove.equalsIgnoreCase("s"))) { // Fire beats stick, hence, player wins.
         return true;
      }  else { // If the above conditions are not satisfied, the computer wins.
         return false;
      }
   }
        
   // Returns the player's cumulative score.    
   public int getPlayerScore() {
      return playerScore;
   }
   
   // Returns the computer's cumulative score.   
   public int getComputerScore() {
      return compScore;
   }
   
   // Returns the total nuber of rounds played.   
   public int getNumRounds() {
      return numRounds;
   }
   
   // Returns true if the player and computer have the same score on the last round, false otherwise.    
   public boolean isTie() {
      if(playerMove.equalsIgnoreCase(compMove)) {
         return true;
      }  else {
         return false;
      }
   }

  /*  This "helper" method uses the instance variable of Random to generate an integer
   *  which it then maps to a String: "S", "W", "F", which is returned.
   *  This method is called by the playRound method.
   */
   private String getRandomChoice() {
      int randCompChoice = 0; // Using an integer to map random values to the String: "S", "W", "F".
      String localCompMove = " ";
      randCompChoice = rand.nextInt(3)+1; // Generating random integers between 0 and 3 (both inclusive)
      if(randCompChoice == 1) {
         localCompMove = "S";
      }  else if(randCompChoice == 2) {
         localCompMove = "W";
      }  else if(randCompChoice == 3) {
         localCompMove = "F";
      }
      return localCompMove;
   }
}
