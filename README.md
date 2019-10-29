# LuftaApp
A very simple version of the famous game BATTLESHIP built in JAVA and played in Console.

# Game Rules
This is a game played between a USER and COMPUTER. The game consists of both Player having a fleet of 3 ships. These ships are
deployed in a 5x5 grid, which imitates the sea. Firstly the USER deploys its ships on the grid, by choosing X and Y coordinates (0-4).
Then the COMPUTER randomly generates some coordinates to deploy its own ships. In case the computer ship is deployed in the same
coordinates where happens to be another ship, the computer generates some new coordinates.

After the ships are deployed in the grid the game is played in turns. First the USER insert two coordinates, where he believes 
that COMPUTER has deployed its ships (nor the USER nor COMPUTER do not know where the enemy ships are placed). In case in those coordinates is a ship that ship is destroye, otherwise the shot is unsuccessful. After the USER it is the COMPUTER turn. The procedure is the same, just this time COMPUTER generates randomly the coordinates it wants to shoot.

The game finishes when all the 3 ships of one of the fleets have been destroyed. In case USER manage to destroy all the COMPUTER's ship before the COMPUTER destroyes his ships, the USER WINS, otherwise, he LOOSES.

# Prerequisites
You need to have at least JDK8 installed in your machine, in order for the application to run. You can [click here](https://www3.ntu.edu.sg/home/ehchua/programming/howto/JDK_Howto.html)
for reading a tutorial on how to install JDK in Windows and/or Ubuntu

# Installation
This Application does not require any specific installation. You just need to download its source code locally, and after that you simple run the code.

# Running the Game
  1. Running from Console
    a. After you have download the app, you open your CMD and go to the Downloads\Lufta Anijeve\out\production\Lufta Anijeve (in case          you save the application somewhere else rather than download folder, you just go in that directory).
    b. You simple run the pre-complied class LuftaApp.class because there is where the MAIN function is.
          
          java LuftaApp
    
  2. You can open the project from you favourite IDE, and then run the application from inside your IDE
  
 # Built With
* [JAVA 8](https://www.java.com/en/download/) - The programming language used

