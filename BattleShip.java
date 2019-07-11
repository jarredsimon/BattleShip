package battleShip;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BattleShip2 {
	
	public static boolean version = true; //this controls which version of the game is played. true = classic false = Jarred's 
	public static boolean torpedo = true; //used to turn on and off special attack option for the torpedo 
	public static boolean radar = true;   //used to turn on and off special attack option for the radar
	public static boolean cpuHit = false; //switch for cpuAI, false = off, true = on
	
	
	public static int direction = 0; //used with cpuAi
	public static int qq = 1; //used with cpuAi
	public static int cpuAX = 0; //used for cpuAI
	public static int cpuAY = 0; //used for cpuAI
	public static int cpuBX = 0; //used for cpuAI
	public static int cpuBY = 0; //used for cpuAI
	public static int cpuCX = 0; //used for cpuAI
	public static int cpuCY = 0; //used for cpuAI
	public static int cpuDX = 0; //used for cpuAI
	public static int cpuDY = 0; //used for cpuAI
	public static int cpuSX = 0; //used for cpuAI
	public static int cpuSY = 0; //used for cpuAI
	
	public static int randomNumberX = 0;  //used for cpu attacks and ship placement
	public static int randomNumberY = 0;
	public static int randomNumberDirection = 0;//used to randomly place ships vertical or horizontal ( even = horizontal odd = vertical) 
	
	public static Scanner playerInput = new Scanner(System.in);
	public static Scanner pause = new Scanner(System.in); // used to pause program and continues when enter has been pressed
	public static int boardX = 10;
	public static int boardY = 10;

	
    public static String topGrid[] = {"A","B","C","D","E","F","G","H","I","J"}; //used to show Y coords
    public static int leftGrid[] = {1,2,3,4,5,6,7,8,9,10}; //used to show X coords
    public static String topAtkGrid[] = {" "," "," "," "," "," "," "," "," "," "};  //used for special attack 
	
	public static String mainBoard[][] = new String[boardX][boardY] ;  // main board used for game
	public static String playerBoard[][] = new String[boardX][boardY] ;  // player's board that holds player's ships
	public static String cpuBoard[][] = new String[boardX][boardY] ;  // cpu's board that holds cpu's ships
	
	public static int playerXCoord; //used with playerYCoord for attacking the enemies ships
	public static int playerYCoord; //used with playerXCoord for attacking the enemies ships
	public static int randomX = 0; //X coord to attack player
	public static int randomY = 0; //Y coord to attack player
	public static String lastShipHit = ""; //used to keep track of the last successful attack from the CPU
	
	public static String acCarrier[] = {"A","A","A","A","A"};  //used to place ship on the board for player and CPU
	public static String battleship[] = {"B","B","B","B"};
	public static String cruiser[] = {"C","C","C",};
	public static String submarine[] = {"S","S","S"};
	public static String destroyer[] = {"D","D"};
	
	public static int pAcCarrier = 5;    //used to track Player ships that have been hit;
	public static int pBattleship = 4;
	public static int pCruiser = 3;
	public static int pSubmarine = 3;
	public static int pDestroyer = 2;
	
	public static int eAcCarrier = 5;    //used to track Enemy ships that have been hit;
	public static int eBattleship = 4;
	public static int eCruiser = 3;
	public static int eSubmarine = 3;
	public static int eDestroyer = 2;
	
	public static int pAcCarrierHitCount = 0;  //Used to keep track of player ships that have been hit
	public static int pBattleShipHitCount = 0;  
	public static int pCruiserHitCount = 0;  
	public static int pSubmarineHitCount = 0;  
	public static int pDestroyerHitCount = 0;  
	
	public static int eAcCarrierHitCount = 0;  //Used to keep track of CPU ships that have been hit
	public static int eBattleShipHitCount = 0;  
	public static int eCruiserHitCount = 0;  
	public static int eSubmarineHitCount = 0;  
	public static int eDestroyerHitCount = 0;
	

	

//******************MAIN METHOD*****************************************
  public static void main(String[] args) throws IOException, InterruptedException
  {
	  fillBoardSpace();
	  startMenu(); //controls the start screen and player placement options. Also selects version to play
	  playerShipRandomPlacement(acCarrier, battleship, cruiser, submarine, destroyer, 1); //used to randomly place ships for the cpu (1 = cpu) 
	  
	 while(true) {
	  printMainBoard(mainBoard,"*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eSubmarineHitCount, eDestroyerHitCount);
	  playerAttack();
	  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pSubmarineHitCount, pDestroyerHitCount);
	  cpuAttack();
	 }

  }//end main


//++++++++++++++++++++PLACE PLAYER SHIPS+++++++++++++++++++++++++++++++++
  
  public static void playerShipRandomPlacement(String[] acCarrier,String[] battleship,String[] cruiser,String[] submarine,String[] destroyer, int playerCpuBoard) 
  {
	  boolean avoidOtherShips = false;  //used as a switch, if other ships are on the placement path then this is false and new random numbers are generated
	  
	  //random placement of AirCraft Carrier
	  while(true) {
		  randomNumberX = randomNumber();
		  randomNumberDirection = randomNumber();
		  avoidOtherShips = checkRandomPlacement(acCarrier, randomNumberX, randomNumberDirection, playerCpuBoard); //checks to see if placements has another ship, if so re-rolls for different placement
			if (avoidOtherShips) {  
			  	try {
					  for (int i=0;i<acCarrier.length;i++) {
						  if (randomNumberDirection % 2 == 0) {
						  	if (playerCpuBoard == 0){playerBoard[randomNumberX][i] = acCarrier[i];} //if 0, this places player ships
						  	else {cpuBoard[randomNumberX][i] = acCarrier[i];}                       // this places cpu ships
						  	}
						  else 
						  {
							  if (playerCpuBoard == 0) {playerBoard[i][randomNumberX] = acCarrier[i];}
							  else {cpuBoard[i][randomNumberX] = acCarrier[i];}
						  }
					  }
					  avoidOtherShips = false;   //Resets the switch to false
					  break;
				  }
				  catch(Exception e) {
					  
				  }
			}//end avoidOtherShips
		}//end while
	  
	  //random placement of Battle Ship 
	  while(true) {
		  randomNumberX = randomNumber();
		  randomNumberDirection = randomNumber();
		  avoidOtherShips = checkRandomPlacement(acCarrier, randomNumberX, randomNumberDirection, playerCpuBoard); //checks to see if placements has another ship, if so re-rolls for different placement
		  if (avoidOtherShips) {
			  try {
				  for (int i=0;i<battleship.length;i++) {
					  if (randomNumberDirection % 2 == 0) {
						  if (playerCpuBoard == 0) {playerBoard[randomNumberX][i] = battleship[i];}
						  else {cpuBoard[randomNumberX][i] = battleship[i];}
					  }
					  else {
						  if (playerCpuBoard == 0) {playerBoard[i][randomNumberX] = battleship[i];}
						  else {cpuBoard[i][randomNumberX] = battleship[i];}
					  }
				  }
				  avoidOtherShips = false;   //Resets the switch to false
				  break;
			  }
			  catch(Exception e) {
				  
			  }
		  }//end avoidOtherShips
		}//end while
	  
	  //random placement of Submarine
	  while(true) {
		  randomNumberX = randomNumber();
		  randomNumberDirection = randomNumber();
		  avoidOtherShips = checkRandomPlacement(acCarrier, randomNumberX, randomNumberDirection, playerCpuBoard); //checks to see if placements has another ship, if so re-rolls for different placement
		  if (avoidOtherShips) {
			  try {
				  for (int i=0;i<submarine.length;i++) {
					  if (randomNumberDirection % 2 == 0) {
						  if (playerCpuBoard == 0) {playerBoard[randomNumberX][i] = submarine[i];}
						  else {cpuBoard[randomNumberX][i] = submarine[i];}
					  }
					  else {
						  if (playerCpuBoard == 0) {playerBoard[i][randomNumberX] = submarine[i];}
						  else {cpuBoard[i][randomNumberX] = submarine[i];}
					  }
				  }
				  avoidOtherShips = false;  //Resets the switch to false
				  break;
			  }
			  catch(Exception e) {
				  
			  }
		  }//end avoidOtherShips
		}//end while
	  
	  //random placement of Cruiser
	  while(true) {
		  randomNumberX = randomNumber();
		  randomNumberDirection = randomNumber();
		  avoidOtherShips = checkRandomPlacement(acCarrier, randomNumberX, randomNumberDirection, playerCpuBoard); //checks to see if placements has another ship, if so re-rolls for different placement
		  if (avoidOtherShips) {
			  try {
				  for (int i=0;i<cruiser.length;i++) {
					  if (randomNumberDirection % 2 == 0) {
						  if (playerCpuBoard == 0) {playerBoard[randomNumberX][i] = cruiser[i];}
						  else {cpuBoard[randomNumberX][i] = cruiser[i];}
					  }
					  else {
						 if (playerCpuBoard == 0) {playerBoard[i][randomNumberX] = cruiser[i];}
						 else {cpuBoard[i][randomNumberX] = cruiser[i];}
					  }
				  }
				  avoidOtherShips = false;   //Resets the switch to false
				  break;
			  }
			  catch(Exception e) {
				  
			  }
		  }//end avoidOtherShips
		}//end while
	  
	  //random placement of Destroyer
	  while(true) {
		  randomNumberX = randomNumber();
		  randomNumberDirection = randomNumber();
		  avoidOtherShips = checkRandomPlacement(acCarrier, randomNumberX, randomNumberDirection, playerCpuBoard); //checks to see if placements has another ship, if so re-rolls for different placement
		  if (avoidOtherShips) {
			  try {
				  for (int i=0;i<destroyer.length;i++) {
					  if (randomNumberDirection % 2 == 0) {
						  if (playerCpuBoard == 0) {playerBoard[randomNumberX][i] = destroyer[i];}
						  else {cpuBoard[randomNumberX][i] = destroyer[i];}
					  }
					  else {
						  if (playerCpuBoard == 0) {playerBoard[i][randomNumberX] = destroyer[i];}
						  else {cpuBoard[i][randomNumberX] = destroyer[i];}
					  }
				  }
				  avoidOtherShips = false;   //Resets the switch to false
				  break;
			  }
			  catch(Exception e) {
				  
			  }
			}//end while
	  }
	}
  
//++++++++++++++++++++FILL BOARD WITH SPACES++++++++++++++++++++++++++++++

  public static void fillBoardSpace() {
	  //Fills all Main / Player / CPU boards with spaces
	  for (int i = 0; i < mainBoard.length; i++) {
		  for (int j = 0; j < mainBoard.length; j++) {
			  mainBoard[i][j] = ".";
			  playerBoard[i][j] = ".";
			  cpuBoard[i][j] = ".";
		  }//inner loop  
	  }//outer loop
  }
  
//++++++++++++++++++++PRINT PLAYER BOARD++++++++++++++++++++++++++++++++++

  public static void printPlayerBoard() {
	 //prints the players board with grids
	     
	  System.out.print("\n                  ");
	  for(int z=0; z<topGrid.length; z++)
	      {
	          System.out.print(topGrid[z]+"|");
	      }
	      System.out.println();

	      int p =0;
	      
	      for (int i=0; i < playerBoard.length; i++)
	      {
	          System.out.print("               ");
	          if(leftGrid[i]==10)
	          {
	              System.out.print(leftGrid[p]+" ");
	          }
	          else
	          {
	              System.out.print(leftGrid[p]+"  ");
	          }

	          for(int j=0; j < playerBoard.length; j++)
	          {
	              System.out.print(playerBoard[i][j] + "|");
	          }
	          System.out.println("");
	          p++;
	      }
  }
 
//+++++++++++++++++++ADD BILLBOARD TO THE TOP OF BOARD+++++++++++++++++++++
  
  //used to show the battleships that have been hit and the hit count
  public static void printBillBoard(String name, int acCarrier, int battleship, int cruiser, int destroyer, int submarine) {
	  System.out.println("*****************"+name+"****************\n"
              + " Aircraft Carrier: "+acCarrier+" out of 5"+
              " Battleship: " +battleship+ " out of 4\n"+"      Cruiser: " +cruiser+ " out of 3"
      + " Destroyer: " +destroyer+ " out of 2\n"+"                Submarine: "+submarine+" out of 3"
      
      +"\n-----------------------------------------------------");

      System.out.print("                  ");
  }
  
//*******************MAIN GAMEBOARD*****************************************
  
  //Prints out the main GameBoard 
  public static void printMainBoard(String arrBoard[][], String name, int acCarrier, int battleship, int cruiser, int destroyer, int submarine)
  {
	  
	  //the below for loop is used to print both boards on top
	  System.out.println("      CPU BOARD                       PLAYER BOARD      ");
       for(int i = 0; i < cpuBoard.length; i++) //mainBoard is used to cover CPU ships
        {
           for(int j = 0; j < mainBoard[i].length; ++j)
            {
	           System.out.print(" " + mainBoard[i][j]);
	        }
	           System.out.print("             ");
	           for(int j = 0; j < playerBoard[i].length; ++j)
	           System.out.print(" " + playerBoard[i][j]);
               System.out.println();
	    }//end for
	    
      System.out.println();  //added for space
	  
      printBillBoard(name,acCarrier,battleship,cruiser, destroyer, submarine);
     

      for(int i=0; i<topGrid.length; i++)
      {
          System.out.print(topAtkGrid[i]+" ");
      }
      System.out.print("\n                  ");
      
      for(int z=0; z<topGrid.length; z++)
      {
          System.out.print(topGrid[z]+"|");
      }
      System.out.println();

      int p =0;
      
      for (int i=0; i < arrBoard.length; i++)
      {
          System.out.print("               ");
          if(leftGrid[i]==10)
          {
              System.out.print(leftGrid[p]+" ");
          }
          else
          {
              System.out.print(leftGrid[p]+"  ");
          }

          for(int j=0; j < arrBoard.length; j++)
          {
              System.out.print(arrBoard[i][j] + "|");
          }
          System.out.println("");
          p++;
      }
  }

//+++++++++++++++++++PLAYER COORD CONVERSION+++++++++++++++++++++++++++++++
  public static void playerCoordConversion(String[] playerCoordXY) {
	  	
	  try {
	  	//checks to see if the number 10 is located by checking the size of the array. a size 3 array means the number 10 is there
	  	if (playerCoordXY.length == 3) {
	  		if (playerCoordXY[0].matches("[A-J]")) {
	  			playerYCoord = letterToNumber(playerCoordXY[0]);
	  			playerXCoord = 9;
	  		}
	  		else {
	  			playerXCoord = 9;
	  			playerYCoord = letterToNumber(playerCoordXY[2]);
	  		}
	  	}
	  	else if (playerCoordXY.length == 2) {
	  		if (playerCoordXY[0].matches("[A-J]")) {
	  			playerYCoord = letterToNumber(playerCoordXY[0]);  //checks if the letter came first and assigns into playerCoordX and playerCoordY variables
	  			playerXCoord = Integer.valueOf(playerCoordXY[1])-1;
	  			//System.out.println("1st");   used for testing
	  		}
	  		else {
	  			playerYCoord = letterToNumber(playerCoordXY[1]);
	  			playerXCoord = Integer.valueOf(playerCoordXY[0])-1; //checks if the number came first and assigns into playerCoordX and playerCoordY variables
	  			//System.out.println("2nd");   used for testing
	  		}
	  	}
	  }//end try
	  catch(Exception e) {
		  
	  }
	  //System.out.println(playerXCoord+" "+playerYCoord);  used for testing
  }

  
//+++++++++++++++++++LETTER TO NUMBER CONVERSION A-J+++++++++++++++++++++++
  
  public static int letterToNumber(String letter) {
	  String converted = "0";
	  switch(letter.toUpperCase()) {
	 
	  case "A":
		  converted = "0";
		  break;
	  case "B":
		  converted = "1";
		  break;
	  case "C":
		  converted = "2";
		  break;
	  case "D":
		  converted = "3";
		  break;
	  case "E":
		  converted = "4";
		  break;
	  case "F":
		  converted = "5";
		  break;
	  case "G":
		  converted = "6";
		  break;
	  case "H":
		  converted = "7";
		  break;
	  case "I":
		  converted = "8";
		  break;
	  case "J":
		  converted = "9";
		  break;
		}
	return Integer.parseInt(converted);
  }

  
//+++++++++++++++++++RANNDOM NUMBER++++++++++++++++++++++++++++++++++++++
  
  //used for a random number( used with cpu attack and ship placement)
  public static int randomNumber() {
	  return  ((int) (Math.random()*11)-1);  //Generates random number 0 - 9
  }
  
//+++++++++++++++++++CLEAR SCREEN++++++++++++++++++++++++++++++++++++++++
  
  public static void clearScreen() throws IOException, InterruptedException
  {
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
  }
//+++++++++++++++++++SLEEP+++++++++++++++++++++++++++++++++++++++++++++++
  
  public static void sleep(int timePerMillSec) throws InterruptedException
  {
	  for (int i = 0; i < 5; i++) {                  //used for the added effect of one "." every timePerMillSec
		  TimeUnit.MILLISECONDS.sleep(timePerMillSec);
		  System.out.print(".");}
  }
 
//++++++++++++++++++++++++++PAUSE++++++++++++++++++++++++++++++++++++++++
  
  public static void pause()
  {
	  System.out.println("Press Enter to Continue");
    String userPause = pause.nextLine();
  }  
  
//+++++++++++++++++++CHECK RANDOM PLACEMENT++++++++++++++++++++++++++++++
  
  //checks location prior to setting random ship, if the location has another ship return false, if not return true
  public static boolean checkRandomPlacement(String [] arr, int coord, int direction, int playerCpuBoard) {
	 try {
		 if (playerCpuBoard == 0) {
			 if (direction % 2 == 0) {
				  for (int i = 0; i < arr.length; i++) {
					   if (playerBoard[coord][i] != "." ) {
						  return false;
						}
					}//end for
			}
			  else {
				  for (int i = 0; i < arr.length; i++) {
					   if (playerBoard[i][coord] != "." ) {
						  return false;
						}
					}//end for
			  }
			  return true;
		 }
		 else
		 {
			 if (direction % 2 == 0) {
				  for (int i = 0; i < arr.length; i++) {
					   if (cpuBoard[coord][i] != "." ) {
						  return false;
						}
					}//end for
				}
				else {
					for (int i = 0; i < arr.length; i++) {
					   if (cpuBoard[i][coord] != "." ) {
						  return false;
						}
					}//end for
				}
				  return true; 
		}//end outer else
	 }//end try
	 catch(Exception e) {
		 
	 }//end catch
	 return false;
  }//end checkRandomPlacement
  
//+++++++++++++++++++PLAYER ATTACK+++++++++++++++++++++++++++++++++++++++
  
  public static void playerAttack() throws InterruptedException {
	do { // when false, Jarred's version is active
	 if (!version) { //False = Jarred's version
		 specialAttack(); // shows special attacks menu
		 String attackCoords;
		 System.out.println("Enter coords to attack ex(1A): ");
		 attackCoords = playerInput.next().toUpperCase();
		 String attackCoordsArr [] = attackCoords.split("");
		 playerCoordConversion(attackCoordsArr);
	 }
	 else {
		 String attackCoords;
		 System.out.println("Enter coords to attack ex(1A): ");
		 attackCoords = playerInput.next().toUpperCase();
		 String attackCoordsArr [] = attackCoords.split("");
		 playerCoordConversion(attackCoordsArr);
	 }
	  
	  sleep(300); //used to show "." every 300th millisecond for 5 "."
	  
	  if (cpuBoard[playerXCoord][playerYCoord] == "A"||cpuBoard[playerXCoord][playerYCoord] == "B"||cpuBoard[playerXCoord][playerYCoord] == "C"||
		  cpuBoard[playerXCoord][playerYCoord] == "S"||cpuBoard[playerXCoord][playerYCoord] == "D") 
	  {
		  mainBoard[playerXCoord][playerYCoord] = "X";
		  countCpuHits();
		  pause();
	  }
	  else if(mainBoard[playerXCoord][playerYCoord] == "X" || mainBoard[playerXCoord][playerYCoord] == "O") {
		  System.out.println("WAKE UP!!! You have already fired at these coords");
		  pause();
		  break;
	  }
	  else{	
		  	mainBoard[playerXCoord][playerYCoord] = "O";
		  	countCpuHits();
		  	printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
	  		System.out.println("Enemy ships were missed");
	  		pause();
	  		break;
	  }
	 }while(!version); // false = Jarred's Version
 }

//+++++++++++++++++++COUNT CPU HITS+++++++++++++++++++++++++++++++++++++++
  //This keeps track of the CPU ships that have been hit
  public static void countCpuHits() throws InterruptedException {
	  if (cpuBoard[playerXCoord][playerYCoord] == "A") {
			eAcCarrierHitCount ++;
			if (eAcCarrierHitCount >= 5) {
				printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eSubmarineHitCount, eCruiserHitCount, eDestroyerHitCount);
				System.out.println("The Enemy's Aircraft Carrier has been sunk!");
			}
			else {
				printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eSubmarineHitCount, eCruiserHitCount, eDestroyerHitCount);
				  System.out.println("The Enemy's Aircraft Carrier has been hit "+eAcCarrierHitCount+" times.");
			  }
	  }
	  if (cpuBoard[playerXCoord][playerYCoord] == "B") {
		  eBattleShipHitCount ++; 
		  if (eBattleShipHitCount >= 4) {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eSubmarineHitCount, eCruiserHitCount, eDestroyerHitCount);
				System.out.println("The Enemy's Battleship has been sunk!");
			}
		  else {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
			  System.out.println("The Enemy's Battleship has been hit "+eBattleShipHitCount+" times.");
		  }
	  }
	  if (cpuBoard[playerXCoord][playerYCoord] == "C") {
		  eCruiserHitCount ++;
		  if (eCruiserHitCount >= 3) {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
				System.out.println("The Enemy's Cruiser has been sunk!");
			}
		  else {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
			  System.out.println("The Enemy's Cruiser has been hit "+eCruiserHitCount+" times.");
		  }
	  }
	  if (cpuBoard[playerXCoord][playerYCoord] == "D") {
		  eDestroyerHitCount ++;
		  if (eDestroyerHitCount >= 2) {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
				System.out.println("The Enemy's Destroyer has been sunk!");
			}
		  else {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
			  System.out.println("The Enemy's Destroyer has been hit "+eDestroyerHitCount+" times.");
		  }
	  }
	  if (cpuBoard[playerXCoord][playerYCoord] == "S") {
		  eSubmarineHitCount ++;
		  if (eSubmarineHitCount >= 3) {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
				System.out.println("The Enemy's Submarine has been sunk!");
			}
		  else {
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount);
			  System.out.println("The Enemy's Submarine has been hit "+eSubmarineHitCount+" times.");
		  }
	  }
	  
	  checkWin();
}  
  
//+++++++++++++++++++COUNT PLAYER HITS+++++++++++++++++++++++++++++++++++++++
  //This keeps track of the player ships that have been hit
  public static void countPlayerHits() throws InterruptedException {
	  if (playerBoard[playerXCoord][playerYCoord] == "A") {
			pAcCarrierHitCount ++;
			if (pAcCarrierHitCount >= 5) {
				printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
				System.out.println("Our Aircraft Carrier has been sunk!");
				cpuHit = false;
			}
			else {
				printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
				  System.out.println("Our Aircraft Carrier has been hit "+pAcCarrierHitCount+" times.");
			  }
	  }
	  if (playerBoard[playerXCoord][playerYCoord] == "B") {
		  pBattleShipHitCount ++;
		  if (pBattleShipHitCount >= 4) {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
				System.out.println("Our Battleship has been sunk!");
				cpuHit = false;
			}
		  else {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
			  System.out.println("Our Battleship has been hit "+pBattleShipHitCount+" times.");
		  }
	  }
	  if (playerBoard[playerXCoord][playerYCoord] == "C") {
		  pCruiserHitCount ++;
		  if (pCruiserHitCount >= 3) {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
				System.out.println("Our Cruiser has been sunk!");
				cpuHit = false;
			}
		  else {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
			  System.out.println("Our Cruiser has been hit "+pCruiserHitCount+" times.");
		  }
	  }
	  if (playerBoard[playerXCoord][playerYCoord] == "D") {
		  pDestroyerHitCount ++;
		  if (pDestroyerHitCount >= 2) {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
				System.out.println("Our Destroyer has been sunk!");
				cpuHit = false;
			}
		  else {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
			  System.out.println("Our Destroyer has been hit "+pDestroyerHitCount+" times.");
		  }
	  }
	  if (playerBoard[playerXCoord][playerYCoord] == "S") {
		  pSubmarineHitCount ++;
		  if (pSubmarineHitCount >= 3) {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
				System.out.println("Our Submarine has been sunk!");
				cpuHit = false;
			}
		  else {
			  printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
			  System.out.println("Our Submarine has been hit "+pSubmarineHitCount+" times.");
		  }
	  }
	  
	  checkWin();
} 
  
//+++++++++++++++++++++SPECIAL ATTACK+++++++++++++++++++++++++++++++++++++++
  
  public static void specialAttack() throws InterruptedException {
	  Scanner specialOption = new Scanner(System.in);
	  String specialSelect = "";
	 if (torpedo && radar) {
		  System.out.println("Select and option:\n"
		  		           + "1 = Torpedo\n"
		  		           + "2 = Radar\n"
		  		           + "3 = Normal Attack");
		  specialSelect = specialOption.next();
		  switch(specialSelect) {
		  case "1":
			  specialAttackTorpedo();
			  torpedo = false;
			  break;
		  case "2":
			  specialAttackRadar();
			  radar = false;
			  break;
		  case "3":
			  break;
		  }
	 }
	 else if (torpedo && !radar) { //radar is no longer an option
		 System.out.println("Select and option:\n"
		           + "1 = Torpedo\n"
		           + "2 = Normal Attack");
		 specialSelect = specialOption.next();
		  switch(specialSelect) {
		  case "1":
			  specialAttackTorpedo();
			  torpedo = false;
			  break;
		  case "2":
			  break;
		  }
	 }
	 else if (!torpedo && radar) { //Torpedo is no longer an option
		 System.out.println("Select and option:\n"
		           + "1 = Radar\n"
		           + "2 = Normal Attack");
		 specialSelect = specialOption.next();
		  switch(specialSelect) {
		  case "1":
			  specialAttackRadar();
			  radar = false;
			  break;
		  case "2":
			  break;
		  }
	 }
}
  
//+++++++++++++++++++CPU ATTACK+++++++++++++++++++++++++++++++++++++++++++
  
  public static void cpuAttack() throws InterruptedException  {
	  
	 do {
	  System.out.println("The Enemy is plotting their distruction!!!");
	  pause();  // forces player to hit Enter before continuing
	  
	  sleep(300); //used to show "." every 300th millisecond for 5 "."
	  
	  
		 if(!cpuHit) { 
			 
			 while (true) {
				 randomX = randomNumber();
				 randomY = randomNumber();
				//checks to make sure the random coords are between -1 and 10 and that the coords haven't been used before.
				 if (randomX < 10 && randomY < 10 && randomX > -1 && randomY > -1 && playerBoard[randomX][randomY] != "X" && playerBoard[randomX][randomY] != "O") 
				 {
					 break;
				 }
			 }//end while
			 
			  	if (playerBoard[randomX][randomY] == "A"||playerBoard[randomX][randomY] == "B"||playerBoard[randomX][randomY] == "C"||
						playerBoard[randomX][randomY] == "S"||playerBoard[randomX][randomY] == "D") 
					{
			  			if(playerBoard[randomX][randomY] == "A") {lastShipHit = "A";}
			  			if(playerBoard[randomX][randomY] == "B") {lastShipHit = "B";}
			  			if(playerBoard[randomX][randomY] == "C") {lastShipHit = "C";}
			  			if(playerBoard[randomX][randomY] == "D") {lastShipHit = "D";}
			  			if(playerBoard[randomX][randomY] == "S") {lastShipHit = "S";}
			  			
				  		playerBoard[randomX][randomY] = "X";
				  		cpuHit = true;  // turns on cpuAi
				  		countPlayerHits();
				  		printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
				  		pause();
				  		
					 }
					 else
					 {
						playerBoard[randomX][randomY] = "O";
						countPlayerHits();
					  	printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
					  	//printMainBoard(mainBoard, "*********CPU", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eSubmarineHitCount, eDestroyerHitCount);
					  	System.out.println("The Enemy MISSED!");
					  	cpuHit = false; //turns off cpuAi
					  	pause();
					  	break;
					  	
					 }//end else
		 }
		 else
		 {
			 cpuAi();
			 countPlayerHits();
		  	 printMainBoard(playerBoard,"***Jarred'S Ships***", pAcCarrierHitCount, pBattleShipHitCount, pCruiserHitCount, pDestroyerHitCount, pSubmarineHitCount);
		  	 pause();
			 
		 }
	}while(!version); // while version is false, Jarred's Version is in play. 
} 
  
//+++++++++++++++++++SPECIAL ATTACK TORPEDO+++++++++++++++++++++++++++++++++++
  
  public static void specialAttackTorpedo() throws InterruptedException  {
	  boolean again = true;
	  int cell = 0;
	  topAtkGrid[cell] = "X";
	  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount );
	  Scanner topAtkGridInput = new Scanner(System.in);
	  while(again) {
		  System.out.println("Choose an option:\nL = Left move  R=Right move\nF = Fire");
		  String playerOption = topAtkGridInput.next().toUpperCase();
		  
		  switch(playerOption) {
		  case "L":
			  if (topAtkGrid[0] != "X") {
				  topAtkGrid[cell] = " ";
				  topAtkGrid[cell - 1] = "X";
				  cell--;
			  }
			  
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount );
			  break;
		  case "R":
			  if (topAtkGrid[9] != "X") {
				  topAtkGrid[cell] = " ";
				  topAtkGrid[cell + 1] = "X";
				  cell++;
			  }
			  
			  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount );
			  break;
		  case "F":
			  for (int i = 0; i < 10; i ++) {
				  TimeUnit.MILLISECONDS.sleep(300);
				  if (cpuBoard[i][cell] == ".") {
					  mainBoard[i][cell] = "O";
				  }                                          //searches straight down for an enemy ship, if one is found the search stops and an "X" is placed
				  else if(cpuBoard[i][cell] == "A"||cpuBoard[i][cell] == "B"||cpuBoard[i][cell] == "C"||cpuBoard[i][cell] == "D"||cpuBoard[i][cell] == "S") {
					  mainBoard[i][cell] = "X";
					  playerXCoord = i;
					  playerYCoord = cell;
					  i = 10;
					  topAtkGrid[cell] = " ";
				  }
				  if (topAtkGrid[cell] == "X") {
					  topAtkGrid[cell] = " ";
				  }
				  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount );
				  again = false;
			  }
			  countCpuHits();
			  break;
			}
		}
}
  
//+++++++++++++++++++SPECIAL ATTACK RADAR+++++++++++++++++++++++++++++++++++++
  
  public static void specialAttackRadar() throws InterruptedException  {
	//Searches a 3X3 grid for enemy ships
	  int ii = 1;
	  int searchCount = 0;
	  Scanner radarCoords = new Scanner(System.in); 
	 System.out.println("Enter a center point (coord) and a 3X3 grid will be searched for any any enemy ships.\nIf located, a ? will indicate "
	 		+ "there is at least one ship near by.2\nOtherwise O will indicate the grid is clear.");
	 String radarSearch = radarCoords.next().toUpperCase();
	 String attackCoordsArr [] = radarSearch.split("");
	  playerCoordConversion(attackCoordsArr);
	  
	  
	  if (cpuBoard[playerXCoord][playerYCoord] == "A" || cpuBoard[playerXCoord][playerYCoord] == "B"||
		  cpuBoard[playerXCoord][playerYCoord] == "C"||  cpuBoard[playerXCoord][playerYCoord] == "D"||
		  cpuBoard[playerXCoord][playerYCoord] == "S") {
		  searchCount++;
		  
	  }
	  
	  if (cpuBoard[playerXCoord+ii][playerYCoord] == "A" || cpuBoard[playerXCoord+ii][playerYCoord+1] == "B"||
		  cpuBoard[playerXCoord+ii][playerYCoord] == "C"||  cpuBoard[playerXCoord+ii][playerYCoord+1] == "D"||
		  cpuBoard[playerXCoord+ii][playerYCoord] == "S") {
		  searchCount++;
		  
		  }
	  
	  if (cpuBoard[playerXCoord][playerYCoord+ii] == "A" || cpuBoard[playerXCoord][playerYCoord+ii] == "B"||
		  cpuBoard[playerXCoord][playerYCoord+ii] == "C"||  cpuBoard[playerXCoord][playerYCoord+ii] == "D"||
		  cpuBoard[playerXCoord][playerYCoord+ii] == "S") {
		  searchCount++;  
		  
		  }
	  
	  if (cpuBoard[playerXCoord+ii][playerYCoord+ii] == "A" || cpuBoard[playerXCoord+ii][playerYCoord+ii] == "B"||
		  cpuBoard[playerXCoord+ii][playerYCoord+ii] == "C"||  cpuBoard[playerXCoord+ii][playerYCoord+ii] == "D"||
		  cpuBoard[playerXCoord+ii][playerYCoord+ii] == "S") {
		  searchCount++;
		  
		  }
	  
	  if (cpuBoard[playerXCoord-ii][playerYCoord+ii] == "A" || cpuBoard[playerXCoord-ii][playerYCoord+ii] == "B"||
		  cpuBoard[playerXCoord-ii][playerYCoord+ii] == "C"||  cpuBoard[playerXCoord-ii][playerYCoord+ii] == "D"||
		  cpuBoard[playerXCoord-ii][playerYCoord+ii] == "S") {
		  searchCount++;
		  
		  }
	  
	  if (cpuBoard[playerXCoord-ii][playerYCoord] == "A" || cpuBoard[playerXCoord-ii][playerYCoord] == "B"||
		  cpuBoard[playerXCoord-ii][playerYCoord] == "C"||  cpuBoard[playerXCoord-ii][playerYCoord] == "D"||
		  cpuBoard[playerXCoord-ii][playerYCoord] == "S") {
		  searchCount++;
		  
		  }
	  
	  if (cpuBoard[playerXCoord-ii][playerYCoord-ii] == "A" || cpuBoard[playerXCoord-ii][playerYCoord-ii] == "B"||
		  cpuBoard[playerXCoord-ii][playerYCoord-ii] == "C"||  cpuBoard[playerXCoord-ii][playerYCoord-ii] == "D"||
		  cpuBoard[playerXCoord-ii][playerYCoord-ii] == "S") {
		  searchCount++;
		  
		  }
	  
	  if (cpuBoard[playerXCoord][playerYCoord-ii] == "A" || cpuBoard[playerXCoord][playerYCoord-ii] == "B"||
		  cpuBoard[playerXCoord][playerYCoord-ii] == "C"||  cpuBoard[playerXCoord][playerYCoord-ii] == "D"||
		  cpuBoard[playerXCoord][playerYCoord-ii] == "S") {
		  searchCount++;
		  
		  }
	  
	  if (cpuBoard[playerXCoord+ii][playerYCoord-ii] == "A" || cpuBoard[playerXCoord+ii][playerYCoord-ii] == "B"||
		  cpuBoard[playerXCoord+ii][playerYCoord-ii] == "C"||  cpuBoard[playerXCoord+ii][playerYCoord-ii] == "D"||
		  cpuBoard[playerXCoord+ii][playerYCoord-ii] == "S") {
		  searchCount++;
		  
		  }
	  
	  if(searchCount > 0) {
		  mainBoard[playerXCoord][playerYCoord] = "?";
		  mainBoard[playerXCoord+ii][playerYCoord] = "?";
		  mainBoard[playerXCoord][playerYCoord+ii] = "?";
		  mainBoard[playerXCoord+ii][playerYCoord+ii] = "?";
		  mainBoard[playerXCoord-ii][playerYCoord+ii] = "?";
		  mainBoard[playerXCoord-ii][playerYCoord] = "?";
		  mainBoard[playerXCoord-ii][playerYCoord-ii] = "?";
		  mainBoard[playerXCoord][playerYCoord-ii] = "?";
		  mainBoard[playerXCoord+ii][playerYCoord-ii] = "?";
		  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount );
		  System.out.println(searchCount + " Enemy ship(s) has/have been detected!");
	  }
	  else{
		  mainBoard[playerXCoord][playerYCoord] = "O";
		  mainBoard[playerXCoord+ii][playerYCoord] = "O";
		  mainBoard[playerXCoord][playerYCoord+ii] = "O";
		  mainBoard[playerXCoord+ii][playerYCoord+ii] = "O";
		  mainBoard[playerXCoord-ii][playerYCoord+ii] = "O";
		  mainBoard[playerXCoord-ii][playerYCoord] = "O";
		  mainBoard[playerXCoord-ii][playerYCoord-ii] = "O";
		  mainBoard[playerXCoord][playerYCoord-ii] = "O";
		  mainBoard[playerXCoord+ii][playerYCoord-ii] = "O";
		  printMainBoard(mainBoard, "*****CPU'S Ships****", eAcCarrierHitCount, eBattleShipHitCount, eCruiserHitCount, eDestroyerHitCount, eSubmarineHitCount );
		  System.out.println("No Enemy ships were detected!");
	  }
}

//++++++++++++++++++++++CPU AI+++++++++++++++++++++++++++++++++++++++++++
  
  public static void cpuAi() throws InterruptedException  {
	  //used for smarter guesses by the CPU for attacks against the player
	  //used to make sure the while loop doesn't turn into an endless loop
	  boolean test = false;
	  int largeBoundary = 10;
	  int smallBoundary = 1;
	  
	  if (lastShipHit == "A") {
		  test = false;
		  if (pAcCarrierHitCount < 2) {
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+1 < largeBoundary) {
						  if (playerBoard[randomX+1][randomY] == "A") {
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+1][randomY] == ".") {
							  playerBoard[randomX+1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX+1][randomY]);
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-1 > smallBoundary ) {
						  if (playerBoard[randomX-1][randomY] == "A") {
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-1][randomY] == ".") {
							  playerBoard[randomX-1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX-1][randomY]);
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 3:
					  if (randomY+1 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+1] == "A") {
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+1] == ".") {
							  playerBoard[randomX][randomY+1] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX][randomY+1]);
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 4:
					  if (randomY-1 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-1] == "A") {
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-1] == ".") {
							  playerBoard[randomX][randomY-1] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX][randomY-1]);
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
					  
				  }
				}//end while

		  }
		  else if (pAcCarrierHitCount < 3) {
			  test = false;
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+2 < largeBoundary ) {
						  if (playerBoard[randomX+2][randomY] == "A") {
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+2][randomY] == ".") {
							  playerBoard[randomX+2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX+2][randomY]);
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-2 > smallBoundary ) {
						  if (playerBoard[randomX-2][randomY] == "A") {
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-2][randomY] == ".") {
							  playerBoard[randomX-2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX-2][randomY]);
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 3:
					  if (randomY+2 < largeBoundary) {
						  if (playerBoard[randomX][randomY+2] == "A") {
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+2] == ".") {
							  playerBoard[randomX][randomY+2] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX][randomY+2]);
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 4:
					  if (randomY-2 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-2] == "A") {
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-2] == ".") {
							  playerBoard[randomX][randomY-2] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX][randomY-2]);
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
					  
				  }
				}//end while
		  }
		  else if (pAcCarrierHitCount < 4) {
			  test = false;
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+3 < largeBoundary ) {
						  if (playerBoard[randomX+3][randomY] == "A") {
							  playerBoard[randomX+3][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+3][randomY] == ".") {
							  playerBoard[randomX+3][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX+3][randomY]);
							  playerBoard[randomX+3][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-3 > smallBoundary ) {
						  if (playerBoard[randomX-3][randomY] == "A") {
							  playerBoard[randomX-3][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-3][randomY] == ".") {
							  playerBoard[randomX-3][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX-3][randomY]);
							  playerBoard[randomX-3][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 3:
					  if (randomY+3 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+3] == "A") {
							  playerBoard[randomX][randomY+3] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+3] == ".") {
							  playerBoard[randomX][randomY+3] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX][randomY+3]);
							  playerBoard[randomX][randomY+3] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 4:
					  if (randomX-3 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-3] == "A") {
							  playerBoard[randomX][randomY-3] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-3] == ".") {
							  playerBoard[randomX][randomY-3] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX][randomY-3]);
							  playerBoard[randomX][randomY-3] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
					  
				  }
				}//end while
		  }
		  else if (pAcCarrierHitCount < 5) {
			  test = false;
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+4 < largeBoundary ) {
						  if (playerBoard[randomX+4][randomY] == "A") {
							  playerBoard[randomX+4][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+4][randomY] == ".") {
							  playerBoard[randomX+4][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX+4][randomY]);
							  playerBoard[randomX+4][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-4 > smallBoundary ) {
						  if (playerBoard[randomX-4][randomY] == "A") {
							  playerBoard[randomX-4][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-4][randomY] == ".") {
							  playerBoard[randomX-4][randomY] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX-4][randomY]);
							  playerBoard[randomX-4][randomY] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 3:
					  if (randomY+4 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+4] == "A") {
							  playerBoard[randomX][randomY+4] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+4] == ".") {
							  playerBoard[randomX][randomY+4] = "O";
							  test = true;
							  break;
						  }
						  else {
							  cpuOffCount(playerBoard[randomX][randomY+4]);
							  playerBoard[randomX][randomY+4] = "X";
							  test = true;
							  break;
						  }
					  }
					  else {break;}
				  case 4:
					  if (randomY-4 > smallBoundary ) { 
						  if (playerBoard[randomX][randomY-4] == "A") {
							  playerBoard[randomX][randomY-4] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-4] == ".") {
							  playerBoard[randomX][randomY-4] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-4]);
							  playerBoard[randomX][randomY-4] = "X";
							  test = true;
							  break;
						  	}
					  	  }
					  	else {break;}
				 }
				}//end while
		  }
	  }//end lastShip if
	  
	  if (lastShipHit == "B") {
		  test = false;
		  if (pBattleShipHitCount < 2) {
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+1 < largeBoundary ) {
						  if (playerBoard[randomX+1][randomY] == "B") {
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+1][randomY] == ".") {
							  playerBoard[randomX+1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+1][randomY]);
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-1 > smallBoundary ) {
						  if (playerBoard[randomX-1][randomY] == "B") {
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-1][randomY] == ".") {
							  playerBoard[randomX-1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-1][randomY]);
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+1 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+1] == "B") {
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+1] == ".") {
							  playerBoard[randomX][randomY+1] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY+1]);
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 4:
					  if (randomY-1 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-1] == "B") {
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-1] == ".") {
							  playerBoard[randomX][randomY-1] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-1]);
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					  
				  }
				}//end while

		  }
		  else if (pBattleShipHitCount < 3) {
			  test = false;
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+2 < largeBoundary ) {
						  if (playerBoard[randomX+2][randomY] == "B") {
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+2][randomY] == ".") {
							  playerBoard[randomX+2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+2][randomY]);
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-2 > smallBoundary ) {
						  if (playerBoard[randomX-2][randomY] == "B") {
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-2][randomY] == ".") {
							  playerBoard[randomX-2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-2][randomY]);
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+2 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+2] == "B") {
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+2] == ".") {
							  playerBoard[randomX][randomY+2] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY+2]);
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 4:
					  if (randomY-2 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-2] == "B") {
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-2] == ".") {
							  playerBoard[randomX][randomY-2] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-2]);
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					  
				  }
				}//end while
		  }
		  else if (pBattleShipHitCount < 4) {
			  test = false;
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+3 < largeBoundary ) {
						  if (playerBoard[randomX+3][randomY] == "B") {
							  playerBoard[randomX+3][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+3][randomY] == ".") {
							  playerBoard[randomX+3][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+3][randomY]);
							  playerBoard[randomX+3][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX+3 > smallBoundary ) {
						  if (playerBoard[randomX-3][randomY] == "B") {
							  playerBoard[randomX-3][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-3][randomY] == ".") {
							  playerBoard[randomX-3][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-3][randomY]);
							  playerBoard[randomX-3][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+3 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+3] == "B") {
							  playerBoard[randomX][randomY+3] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+3] == ".") {
							  playerBoard[randomX][randomY+3] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY+3]);
							  playerBoard[randomX][randomY+3] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 4:
					  if (randomY-3 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-3] == "B") {
							  playerBoard[randomX][randomY-3] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-3] == ".") {
							  playerBoard[randomX][randomY-3] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-3]);
							  playerBoard[randomX][randomY-3] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  }
				}//end while
		 }
	  }//end lastShip if
	  
	  
	  if (lastShipHit == "C") {
		  test = false;
		  if (pCruiserHitCount < 2) {
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+1 < largeBoundary ) {
						  if (playerBoard[randomX+1][randomY] == "C") {
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+1][randomY] == ".") {
							  playerBoard[randomX+1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+1][randomY]);
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-1 > smallBoundary ) {
						  if (playerBoard[randomX-1][randomY] == "C") {
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-1][randomY] == ".") {
							  playerBoard[randomX-1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-1][randomY]);
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+1 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+1] == "C") {
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+1] == ".") {
							  playerBoard[randomX][randomY+1] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY+1]);
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 4:
					  if (randomY-1 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-1] == "C") {
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-1] == ".") {
							  playerBoard[randomX][randomY-1] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-1]);
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					  
				  }
				}//end while

		  }
		  else if (pCruiserHitCount < 3) {
			  test = false;
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+2 < largeBoundary ) {
						  if (playerBoard[randomX+2][randomY] == "C") {
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+2][randomY] == ".") {
							  playerBoard[randomX+2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+2][randomY]);
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX+2 > smallBoundary ) {
						  if (playerBoard[randomX-2][randomY] == "C") {
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-2][randomY] == ".") {
							  playerBoard[randomX-2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-2][randomY]);
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+2 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+2] == "C") {
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+2] == ".") {
							  playerBoard[randomX][randomY+2] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY+2]);
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 4:
					  if (randomY-2 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-2] == "C") {
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-2] == ".") {
							  playerBoard[randomX][randomY-2] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-2]);
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  }
				}//end while
		  }
	  }//end lastShip if
	  
	  if (lastShipHit == "S") {
		  test = false;
		  test = false;
		  if (pSubmarineHitCount < 2) {
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+1 < largeBoundary ) {
						  if (playerBoard[randomX+1][randomY] == "S") {
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+1][randomY] == ".") {
							  playerBoard[randomX+1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+1][randomY]);
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-1 > smallBoundary ) {
						  if (playerBoard[randomX-1][randomY] == "S") {
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-1][randomY] == ".") {
							  playerBoard[randomX-1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-1][randomY]);
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+1 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+1] == "S") {
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+1] == ".") {
							  playerBoard[randomX][randomY+1] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY+1]);
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 4:
					  if (randomY-1 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-1] == "S") {
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-1] == ".") {
							  playerBoard[randomX][randomY-1] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-1]);
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					  
				  }
				}//end while

		  }
		  else if (pSubmarineHitCount < 3) {
			  test = false;
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+2 < largeBoundary ) {
						  if (playerBoard[randomX+2][randomY] == "S") {
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+2][randomY] == ".") {
							  playerBoard[randomX+2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+2][randomY]);
							  playerBoard[randomX+2][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-2 > smallBoundary ) {
						  if (playerBoard[randomX-2][randomY] == "S") {
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-2][randomY] == ".") {
							  playerBoard[randomX-2][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-2][randomY]);
							  playerBoard[randomX-2][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+2 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+2] == "S") {
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+2] == ".") {
							  playerBoard[randomX][randomY+2] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY+2]);
							  playerBoard[randomX][randomY+2] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 4:
					  if (randomY-2 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-2] == "S") {
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-2] == ".") {
							  playerBoard[randomX][randomY-2] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-2]);
							  playerBoard[randomX][randomY-2] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  }
				}//end while
		  }
	  }//end lastShip if
	  
	  if (lastShipHit == "D") {
		  test = false;
		  if (pDestroyerHitCount < 2) {
			  while (!test) {
				  int direction = (int) (Math.random()*4)+1;
				  switch (direction) {
				  case 1:
					  if (randomX+1 < largeBoundary ) {
						  if (playerBoard[randomX+1][randomY] == "D") {
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX+1][randomY] == ".") {
							  playerBoard[randomX+1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX+1][randomY]);
							  playerBoard[randomX+1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					
				  case 2:
					  if (randomX-1 > smallBoundary ) {
						  if (playerBoard[randomX-1][randomY] == "D") {
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX-1][randomY] == ".") {
							  playerBoard[randomX-1][randomY] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX-1][randomY]);
							  playerBoard[randomX-1][randomY] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
				  case 3:
					  if (randomY+1 < largeBoundary ) {
						  if (playerBoard[randomX][randomY+1] == "D") {
							  playerBoard[randomX][randomY+1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY+1] == ".") {
							  playerBoard[randomX][randomY+1] = "O";
							  test = true;
							  break;
						  }
						  else {break;}
					  }
					  else {break;}
				  case 4:
					  if (randomY-1 > smallBoundary ) {
						  if (playerBoard[randomX][randomY-1] == "D") {
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
						  }
						  else if (playerBoard[randomX][randomY-1] == ".") {
							  playerBoard[randomX][randomY-1] = "O";
							  test = true;
							  break;
						  }
						  else{
							  cpuOffCount(playerBoard[randomX][randomY-1]);
							  playerBoard[randomX][randomY-1] = "X";
							  test = true;
							  break;
						  	}
					  }
					  else {break;}
					  
				  }
				}//end while
		  	}
	  }//end lastShip if
}
  
////++++++++++++++++++++++DIRECTION+++++++++++++++++++++++++++++++++++++++++
  
  public static void cpuOffCount(String letter) throws InterruptedException  {
	  switch (letter) {
	  case "A":
		  pAcCarrierHitCount ++;
		  break;
	  case "B":
		  pBattleShipHitCount ++;
		  break;
	  case "C":
		  pCruiserHitCount ++;
		  break;
	  case "D":
		  pDestroyerHitCount ++;
		  break;
	  case "S":
		  pSubmarineHitCount ++;
		  break;
		  
	  }
}  
  
//++++++++++++++++++++PLAYER PLACE SHIPS+++++++++++++++++++++++++++++++++
  
  public static void playerPlaceShips()  {
	  String ship = " ";
	  String attackCoords;
	  String shipArr[] = {"Aircraft Carrier","Battleship","Cruiser","Submarine","Destroyer"};
	  String shipArrLetter[] = {"A","B","C","S","D"};
	  
	  for (int i = 0; i < shipArr.length; i++) {
		  boolean placement = true;
		  while (placement) {
			  System.out.println("Place your "+shipArr[i]+"!");
			  System.out.println("enter coords for starting position ex(1A):");
			  attackCoords = playerInput.next().toUpperCase();
			  String attackCoordsArr [] = attackCoords.split("");
			  playerCoordConversion(attackCoordsArr);
			
			  ship = shipArrLetter[i];
			  
			  System.out.println("Which direction do you want to place this ship?\n"
	 		           + "L = Left, R = Right, U = Up, D = Down:");
			  char[] directionCoord = playerInput.next().toUpperCase().toCharArray();
			  
			  //for testing
			  System.out.println(playerXCoord+","+playerYCoord+","+directionCoord[0]+","+ship);
		  
			  switch(ship) {
			  case "A":
				  
				  	if (directionCoord[0] == 'U' && playerXCoord > 3) {
				  		playerBoard[playerXCoord][playerYCoord] = "A";
				  		playerBoard[playerXCoord-1][playerYCoord] = "A";
				  		playerBoard[playerXCoord-2][playerYCoord] = "A";
				  		playerBoard[playerXCoord-3][playerYCoord] = "A";
				  		playerBoard[playerXCoord-4][playerYCoord] = "A";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'D' && playerXCoord < 6)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "A";
				  		playerBoard[playerXCoord+1][playerYCoord] = "A";
				  		playerBoard[playerXCoord+2][playerYCoord] = "A";
				  		playerBoard[playerXCoord+3][playerYCoord] = "A";
				  		playerBoard[playerXCoord+4][playerYCoord] = "A";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'L' && playerYCoord > 3)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "A";
				  		playerBoard[playerXCoord][playerYCoord-1] = "A";
				  		playerBoard[playerXCoord][playerYCoord-2] = "A";
				  		playerBoard[playerXCoord][playerYCoord-3] = "A";
				  		playerBoard[playerXCoord][playerYCoord-4] = "A";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'R' && playerYCoord < 6)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "A";
				  		playerBoard[playerXCoord][playerYCoord+1] = "A";
				  		playerBoard[playerXCoord][playerYCoord+2] = "A";
				  		playerBoard[playerXCoord][playerYCoord+3] = "A";
				  		playerBoard[playerXCoord][playerYCoord+4] = "A";
				  		placement = false;
				  		break;
				  	}
				  	else {
				  	    //System.out.println("A");
				  		System.out.println("Invalid Placement, try again:");
				  		break;
				  	}
				  
			  case "B":
				  	if (directionCoord[0] == 'U' && playerXCoord > 2) {
				  		playerBoard[playerXCoord][playerYCoord] = "B";
				  		playerBoard[playerXCoord-1][playerYCoord] = "B";
				  		playerBoard[playerXCoord-2][playerYCoord] = "B";
				  		playerBoard[playerXCoord-3][playerYCoord] = "B";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'D' && playerXCoord < 7)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "B";
				  		playerBoard[playerXCoord+1][playerYCoord] = "B";
				  		playerBoard[playerXCoord+2][playerYCoord] = "B";
				  		playerBoard[playerXCoord+3][playerYCoord] = "B";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'L' && playerYCoord > 2)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "B";
				  		playerBoard[playerXCoord][playerYCoord-1] = "B";
				  		playerBoard[playerXCoord][playerYCoord-2] = "B";
				  		playerBoard[playerXCoord][playerYCoord-3] = "B";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'R' && playerYCoord < 7)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "B";
				  		playerBoard[playerXCoord][playerYCoord+1] = "B";
				  		playerBoard[playerXCoord][playerYCoord+2] = "B";
				  		playerBoard[playerXCoord][playerYCoord+3] = "B";
				  		placement = false;
				  		break;
				  	}
				  	else {
				  		//System.out.println("B");
				  		System.out.println("Invalid Placement, try again:");
				  		break;
				  	}
				  
			  case "C":
				  	if (directionCoord[0] == 'U' && playerXCoord > 1) {
				  		playerBoard[playerXCoord][playerYCoord] = "C";
				  		playerBoard[playerXCoord-1][playerYCoord] = "C";
				  		playerBoard[playerXCoord-2][playerYCoord] = "C";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'D' && playerXCoord < 8)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "C";
				  		playerBoard[playerXCoord+1][playerYCoord] = "C";
				  		playerBoard[playerXCoord+2][playerYCoord] = "C";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'L' && playerYCoord > 1)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "C";
				  		playerBoard[playerXCoord][playerYCoord-1] = "C";
				  		playerBoard[playerXCoord][playerYCoord-2] = "C";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'L' && playerYCoord < 8)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "C";
				  		playerBoard[playerXCoord][playerYCoord+1] = "C";
				  		playerBoard[playerXCoord][playerYCoord+2] = "C";
				  		placement = false;
				  		break;
				  	}
				  	else {
				  		//System.out.println("C");
				  		System.out.println("Invalid Placement, try again:");
				  		break;
				  	}
				 
			  case "S":
				  	if (directionCoord[0] == 'U' && playerXCoord > 1) {
				  		playerBoard[playerXCoord][playerYCoord] = "S";
				  		playerBoard[playerXCoord-1][playerYCoord] = "S";
				  		playerBoard[playerXCoord-2][playerYCoord] = "S";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'D' && playerXCoord < 8)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "S";
				  		playerBoard[playerXCoord+1][playerYCoord] = "S";
				  		playerBoard[playerXCoord+2][playerYCoord] = "S";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'L' && playerYCoord > 1)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "S";
				  		playerBoard[playerXCoord][playerYCoord-1] = "S";
				  		playerBoard[playerXCoord][playerYCoord-2] = "S";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'R' && playerYCoord < 8)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "S";
				  		playerBoard[playerXCoord][playerYCoord+1] = "S";
				  		playerBoard[playerXCoord][playerYCoord+2] = "S";
				  		placement = false;
				  		break;
				  	}
				  	else {
				  		//System.out.println("S");
				  		System.out.println("Invalid Placement, try again:");
				  		break;
				  	}
				  
			  case "D":
				  	if (directionCoord[0] == 'U' && playerXCoord > 0) {
				  		playerBoard[playerXCoord][playerYCoord] = "D";
				  		playerBoard[playerXCoord-1][playerYCoord] = "D";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'D' && playerXCoord < 9)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "D";
				  		playerBoard[playerXCoord+1][playerYCoord] = "D";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'L' && playerYCoord > 0)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "D";
				  		playerBoard[playerXCoord][playerYCoord-1] = "D";
				  		placement = false;
				  		break;
				  	}
				  	else if(directionCoord[0] == 'R' && playerYCoord < 9)
				  	{
				  		playerBoard[playerXCoord][playerYCoord] = "D";
				  		playerBoard[playerXCoord][playerYCoord+1] = "D";
				  		placement = false;
				  		break;
				  	}
				  	else {
				  		//System.out.println("D");
				  		System.out.println("Invalid Placement, try again:");
				  		break;
				  	}
				 
				}//end switch
		  }//end while
		  printPlayerBoard();
	}//end for
}
  
//+++++++++++++++++++CHECK WIN+++++++++++++++++++++++++++++++++++++++++++
  
  public static void checkWin() throws InterruptedException  {
	  if (pAcCarrierHitCount == 5 && pBattleShipHitCount == 4 && pCruiserHitCount == 3 && pSubmarineHitCount == 3 && pDestroyerHitCount == 2) {
		  System.out.println("OH NO!!!, the Enemy has sunk all of your battleships.\n      GAME OVER!!!");
	  }
	  
	  if (eAcCarrierHitCount == 5 && eBattleShipHitCount == 4 && eCruiserHitCount == 3 && eSubmarineHitCount == 3 && eDestroyerHitCount == 2) {
		  System.out.println("Congratulations: You have beaten the Enemy!.\n      GAME OVER!!!");
	  }
	  
} 
  

  

  
//++++++++++++++++++++++CPU COORD TRACKER+++++++++++++++++++++++++++++++++++++++++++
  public static void cpuCoordTracker(String letter) {
	  //sets the last known hit for the ship into CPU memory until a new hit is made on that ship
	  switch (letter) {
	  case "A":
		  cpuAX = randomX;
		  cpuAY = randomY;
		  break;
	  case "B":
		  cpuBX = randomX;
		  cpuBY = randomY;
		  break;
	  case "C":
		  cpuCX = randomX;
		  cpuCY = randomY;
		  break;
	  case "D":
		  cpuDX = randomX;
		  cpuDY = randomY;
		  break;
	  case "S":
		  cpuSX = randomX;
		  cpuSY = randomY;
		  break;
		  
	  }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
//******************************************************************************
public static void gameOptions() throws IOException, InterruptedException
{

             System.out.println(""
  + "              ******************************************************\n"
  + "              *                     WELCOME TO                     *\n"
  + "              *                                                    *\n"
  + "              *                B A T T L E  S H I P                *\n"
  + "              *                                                    *\n"
  + "              *                    Game Options                    *\n"
  + "              *                                                    *\n"
  + "              *         Your opponents ships are randomly          *\n"        
  + "              *         placed. You have the option to place       *\n"
  + "              *         your ships on the grid how you want        *\n"        
  + "              *         them, or also have them randomly placed.   *\n" 
  + "              *                                                    *\n"
  + "              *                 Choose Your Option:                *\n"
  + "              *                                                    *\n"                   
  + "              *               1) Randomly Place Ships              *\n"
  + "              *                                                    *\n" 
  + "              *               2) User Places Ships                 *\n"             
  + "              *                                                    *\n"              
  + "              *                     SIMONgAMES                     *\n"
  + "              ******************************************************");
             
             Scanner shipPlacementOption = new Scanner(System.in);
             System.out.print("              :");
             String placementOption = shipPlacementOption.next();
             
             switch (placementOption) {
             case "1":
            		 playerShipRandomPlacement(acCarrier, battleship, cruiser, submarine, destroyer, 0); //used to randomly place ships for the player (0 = player)
            		 break;
             case "2":
            		 printPlayerBoard();
            		 playerPlaceShips();
            		 break;
             default:
            		 gameOptions();
            	 
             }
     
}
//****************************************************************************** 

//******************************************************************************
public static void versionMenu(String playerOption) throws IOException, InterruptedException
{
 
   if (playerOption == "00")
   {
            System.out.println(""
  + "              ******************************************************\n"
  + "              *                     WELCOME TO                     *\n"
  + "              *                                                    *\n"
  + "              *                B A T T L E  S H I P                *\n"
  + "              *                                                    *\n"
  + "              *                  Classic  Version                  *\n"
  + "              *                                                    *\n"
  + "              *         In the classic verison of Battle Ship,     *\n"        
  + "              *         turns are taken one at a time. After a     *\n"
  + "              *         shot has been fired the turn ends and      *\n"        
  + "              *         the next turn starts. It doesn't matter    *\n" 
  + "              *         if the shot hit or missed in this          *\n"
  + "              *         version. The first person to sink all      *\n"
  + "              *         of their opponents ships WINS!!!           *\n"
  + "              *                                                    *\n" 
  + "              *                       ENJOY!                       *\n"             
  + "              *                                                    *\n"              
  + "              *                     SIMONgAMES                     *\n"
  + "              ******************************************************");
            System.out.println("Press Enter to Continue:");
            String enter = pause.nextLine();
            gameOptions();
            
   }
   else if (playerOption == "01")
  {
       System.out.println(""
  + "              ******************************************************\n"
  + "              *                     WELCOME TO                     *\n"
  + "              *                                                    *\n"
  + "              *                B A T T L E  S H I P                *\n"
  + "              *                                                    *\n"
  + "              *                  Jarred's Version                  *\n"
  + "              *                                                    *\n"
  + "              *         In this version of Battle Ship there       *\n"        
  + "              *         are a few new special attacks.             *\n"
  + "              *         Special Attacks can only be used once      *\n"        
  + "              *         per game. Search Area will search a        *\n" 
  + "              *         3x3 grid for all nearby enemy ships.       *\n"
  + "              *         If any are found, you will be alerted      *\n"
  + "              *         to how many and the grid will display      *\n"
  + "              *         question marks to show that there are      *\n" 
  + "              *         enemy ships there, somewhere.              *\n"
  + "              *                                                    *\n"
  + "              *         In Jarred'S Version if you hit an enemy    *\n"             
  + "              *         or the enemy hits you, an extra turn is    *\n" 
  + "              *         taken until a missed shot.                 *\n"
  + "              *                                                    *\n"
  + "              *         The first person to sink all of their      *\n"
  + "              *         opponents ships WINS!!!                    *\n"
  + "              *                                                    *\n"             
  + "              *                       ENJOY!                       *\n"             
  + "              *                                                    *\n"              
  + "              *                     SIMONgAMES                     *\n"
  + "              ******************************************************");
       System.out.println("Press Enter to Continue:");
       String enter = pause.nextLine();
       version = false;  // turns Jarred's version on
       gameOptions();
}
}//end versionMenu
//****************************************************************************** 

//******************************************************************************
public static void startMenu() throws IOException, InterruptedException
{
    
  System.out.println(""
  + "              ******************************************************\n"
  + "              *                     WELCOME TO                     *\n"
  + "              *                                                    *\n"
  + "              *                B A T T L E  S H I P                *\n"
  + "              *                                                    *\n"
  + "              *                  Choose Game Mode:                 *\n"
  + "              *                                                    *\n"
  + "              *               1) Classic BattleShip                *\n"        
  + "              *                                                    *\n"
  + "              *                2) Jarred'S Version                 *\n"        
  + "              *                                                    *\n"        
  + "              *                     SIMONgAMES                     *\n"
  + "              ******************************************************");
  
  Scanner gameType = new Scanner(System.in);
  System.out.print("              :");
  System.out.print("");
  String gameMode = gameType.next();
  
  switch(gameMode) {
  case "1":
		  versionMenu("00");
		  break;
	  
  case "2":
		  versionMenu("01");
		  break;
	  
  default: 
		  startMenu();
  }
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


}//End Class

