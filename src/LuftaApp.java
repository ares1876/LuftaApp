import java.util.Random;
import java.util.Scanner;

//grid[x][y] = 0 -> Idicates the sea is empty in those coordinates
//grid[x][y] = 1 -> Idicates that there is a USER's SHIP in that coordinate
//                  When the Sea is shown, the user's ships are marked with "u"
//grid[x][y] = 2 -> Idicates that there is a COMPUTER's SHIP in that coordinate
//                  When the sea is shown, the computer's ships remain hidden, so the user cannot cheat
//grid[x][y] = 3 -> Idicates that USER shot in those coordinates once and MISSED
//                  When the Sea is shown, the coordinates are marked with  "-"
//grid[x][y] = 4 -> Idicates that USER shot in those coordinates and DESTROYED its OWN SHIP
//                  When the Sea is shown, the coordinates are marked with  "!"
//grid[x][y] = 5 -> Idicates that USER shot in those coordinates once and DESTROYED COMPUTER SHIP
//                  When the Sea is shown, the coordinates are marked with  "x"
//grid[x][y] = 6 -> Idicates that COMPUTER shot in those coordinates once and MISSED
//                  When the sea is shown, nothing is marked in these coordinates, so USER doesn't know where COMPUTER shot
//grid[x][y] = 7 -> Idicates that COMPUTER shot in those coordinates and DESTROYED USER's SHIP
//                  When the sea is shown, the coordinates are marked with "!"
//grid[x][y] = 8 -> Idicates that COMPUTER shot in those coordinates once and DESTROYED its OWN SHIP
//                  When the sea is shown, the coordinates are marked with "x"

public class LuftaApp {

    public static void main(String[] args) {
        int[][] grid = new int[5][5];                                           //The area of battle is declared, grid with 5 rows and 5 columns
        int[] fleets = new int[2];                                              //Two fleets are declared, USER's fleet and COMPUTER's fleet
        fleets[0]=3;                                                            //Number of USER'S fleets
        fleets[1]=3;                                                            //Number of COMPUTER's fleets

        intro();
        delay(1);

        for(int i=1; i<=fleets[0];i++){
            grid = deployShips(grid, i);                                        //Deploy the 3 USER's fleets
        }
        delay(1);

        showNewSea(grid);                                                       //Show the updated sea with the new USER's ships
        delay(1);

        for(int i=1; i<=fleets[1];i++){
            grid = deployComputerShips(grid, i);                                //Deploy 3 COMPUTER's fleets
        }

        delay(2);
        System.out.println("\n\nLet the BATTLE begin\n\n");


        while(fleets[0]!=0&&fleets[1]!=0){                                      //Checks if computer or the user has no ship left undestroyed

            delay(2);
            grid = userShot(grid);                                              //USER fires its missile
            showNewSea(grid);

            delay(2);
            grid = compShot(grid);                                              //COMPUTER fires its missile
            showNewSea(grid);

            delay(2);
            fleets = showShips(grid);                                           //Show how many ships are left in both fleets
        }

        //End of the game message
        delay(3);
        if(fleets[0] == 0){                                                     //If the USER'S fleet has no ships left, COMPUTER wins
            System.out.println
                    ("All your ships have been destroyed. You LOOSE!");
        } else if (fleets[1]==0){                                               //If the COMPUTER'S fleet has no ships left, USER wins
            System.out.println
                    ("You have destroyed all enemy ships. You WIN!");
        }
    }

    //The intro which just prints the battle grid in the console
    public static void intro() {
        System.out.println("***Welcome to the Battleship game!\n\nRight now the sea is empty!\n\n");
        System.out.println("   01234");
        for (int i=0;i<5;i++){
            System.out.println(i+ " !     ! " +i);
        }
        System.out.println("   01234");

    }
    
    //The function which takes care for the user to deploy his ships
    public static int[][] deployShips(int[][] grid, int i) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease insert the coordinates where you want to deploy your number "+i+" ship!\n");
        int x, y;
        System.out.print("X coordinate: ");
        x= takeCoordinate();
        System.out.print("X coordinate: ");
        y= takeCoordinate();
        while(grid[x][y]==1){
            System.out.println("Please make sure to use other coordinates. You already have placed one ship here");
            System.out.print("X coordinate: ");
            x= takeCoordinate();
            System.out.print("X coordinate: ");
            y= takeCoordinate();
        }
        grid[x][y]=1;                                                          //The coordinates are set at "1" to indicate that there is a USER's SHIP there
        return grid;
    }
    

    //The function which deploys randomly the computer's ships
    public static int[][] deployComputerShips(int[][] grid, int i){
        System.out.println("Computer is deploying its number"+i+" ship!");
        Random rand = new Random();
        int x, y;
        x= rand.nextInt(4);                                             //X coordinate is generated randomly with border at 4
        y= rand.nextInt(4);                                             //Y coordinate is generated randomly with border at 4
        while(grid[x][y]==1||grid[x][y]==2){                                   //Does not allow that a new ship to be deployed in the same coordinates where the already exists another ship
            x= rand.nextInt(4);
            y= rand.nextInt(4);
        }
        grid[x][y]=2;                                                           //The coordinates ares set "2" to indicate that there is a COMPUTER SHIP there
        return grid;
    }
    
    
    //The function which makes possible for the user to shoot
    public static int[][] userShot(int[][] grid) {
        Scanner input = new Scanner(System.in);
        System.out.println("It is your turn!\n");
        System.out.print("Enter your X coordinate: ");
        int x=takeCoordinate();                                                 //X coordinates are asked to be inserted by the user
        System.out.print("\nEnter your Y coordinate: ");
        int y=takeCoordinate();                                                 //Y coordinates are asked to be inserted by the user
        grid = checkUserResult(grid, x, y);                                     //Checks the result of the shot, if it failed, succeeded or destroyed its own ship
        return grid;
    }
    
    //The function which checks the result of the shot made from the user
    public static int[][] checkUserResult(int[][] grid, int x, int y) {
        if (grid[x][y]==0){                                                 //If the sea is empty, the shot is missed.
            System.out.println("\nYou missed!\n");
            grid[x][y]=3;                                                   //The grid cell is set to 3, to indicate that USER shot in those coordinates once and missed
        } else if (grid[x][y]==1){                                          //If there is a USER's ship in these coordinates, he destroys its own ship
            System.out.println("\nUPPS! You destroyed your own ship!\n");
            grid[x][y]=4;                                                   //The grid cell is set to 3, to indicate that USER shot in those coordinates and shot its own ship
        } else if (grid[x][y]==2){                                          //If there is a COMPUTER's ship in these coordinates, he destroys its ship
            System.out.println("\nBRAVO! You destroyed an enemy ship!\n");
            grid[x][y]=5;                                                   // The grid cell is set to 3, to indicate that USER shot in those coordinates and shot COMPUTERS's ship
        }
        return grid;
    }

    //The function which makes possible for the COMPUTER to shoot
    public static int[][] compShot(int[][] grid) {
        Random rand = new Random();
        System.out.println("It is Computers turn!\n");
        System.out.println("Computer choosed its coordinates1\n");
        int x=rand.nextInt(4);                                          //X coordinate is generated randomly with border at 4
        int y=rand.nextInt(4);                                          //Y coordinate is generated randomly with border at 4
        while(grid[x][y]==6||grid[x][y]==7||grid[x][y]==8){                    //It is not allowed for the COMPUTER to shoot twice in the same coordinates
            x=rand.nextInt(4);
            y=rand.nextInt(4);
        }
        grid = checkCompResult(grid, x, y);                                     //Checks the result of the shot, if it failed, succeeded or destroyed its own ship
        return grid;
    }

    //The function which checks the result of the COMPUTER shot
    public static int[][] checkCompResult(int[][] grid, int x, int y) {
        if (grid[x][y]==0){                                                 //If the sea is empty, the shot is missed.
            System.out.println
                    ("Computer missed!");
            grid[x][y]=6;                                                   //The grid cell is set to 6, to indicate that COMPUTER shot in those coordinates once and missed
        } else if (grid[x][y]==1){                                          //If there is a USER's ship in these coordinates, COMPUTER destroys his ship
            System.out.println
                    ("UPPS! Computer destroyed your ship!");
            grid[x][y]=7;                                                   //The grid cell is set to 7, to indicate that COMPUTERS shot in those coordinates and shot USER's ship
        } else if (grid[x][y]==2){                                          //If there is a COMPUTER's ship in these coordinates, COMPUTER destroys its OWN SHIP
            System.out.println
                    ("HURRAY! Computer destroyed its own ship!");
            grid[x][y]=8;                                                   //The grid cell is set to 8, to indicate that COMPUTERS  shot in those coordinates and shot ITS OWN ship
        }
        return grid;
    }

    //The functions which checks the value inside the grid coordinates and decides how to mark each cell according to the value
    public static int[] showShips(int[][] grid) {
        int[] ships = new int[2];
        int myShips=0, compShips=0;
        for(int i=0; i<=4; i++){
            for(int j=0; j<=4; j++){
                if(grid[i][j]==1){
                    myShips++;                                              //Counts how many USER's ship are left undestroyed
                } else if (grid[i][j]== 2){
                    compShips++;                                            //Counts how many COMPUTER's ship are left undestroyed;
                }
            }
        }
        ships[0]= myShips;                                                  //Sets the new value of undestroyed USER's ships after each round
        ships[1]= compShips;                                                //Sets the new value of undestroyed COMPUTER's ships after each round
        System.out.println
                ("Your ships: " + myShips + " | Computer Ships: " +compShips);
        System.out.println
                ("----------------------------------------------------");
        return ships;
    }

    //The function which askes the user to insert the coordinates
    public static int takeCoordinate(){
        String check = "0 1 2 3 4 ";
        Scanner in = new Scanner(System.in);
        String temp = in.next();

        while(!check.contains(temp)){
            System.out.println("Please insert a NUMBER 0-4");
            temp = in.next();
        }

        return  Integer.parseInt(temp);
    }

    //The function which shows the Sea and that what is inside
    public static void showNewSea(int[][] grid) {
        System.out.println("   01234  ");
        for(int i=0;i<=4;i++){
            System.out.print(i+ " !");
            for(int j=0;j<=4;j++){
                if(grid[i][j]==0||grid[i][j]==2||grid[i][j]==6){
                    System.out.print(" ");                                      //Prints a space where there is nothing to show
                } else if (grid[i][j]==1) {
                    System.out.print("u");                                      //Prints "u" where there is a USER's ship
                } else if (grid[i][j]==3){
                    System.out.print("-");                                      //Prints "-" where USER shoots but misses
                } else if (grid[i][j]==4){
                    System.out.print("!");                                      //Prints "!" where USER shoots its OWN SHIP
                } else if (grid[i][j]==5){
                    System.out.print("x");                                      //Prints "x" where USER shoots ENEMY SHIP
                } else if (grid[i][j]==7){
                    System.out.print("!");                                      //Prints "!" where COMPUTER shoots USER's SHIP
                } else if (grid[i][j]==8){
                    System.out.print("x");                                      //Prints "x" where COMPUTER shoot its own ship
                }
            }
            System.out.println("! " +i);
        }
        System.out.println("   01234  ");

    }

    //The function which delays the execution of the system to make it more user friendly
    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
