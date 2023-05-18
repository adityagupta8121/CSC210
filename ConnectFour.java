/**
* ConnectFour
* The program prompts the players to enter a column number, and play a game of Connect Four.
* The program also allows players to decide the color at the start of the game, and also if they
* want to play a game of Connect Three or Connect Four or Connect Five
*/
import java.util.Scanner;

public class ConnectFour{
	
	/**
	* main verifies whether the tokens match to win the game
	* asks user to input name, color, number of tokens needed to win
	* and also checks if the user wants to play another game.
	* @param args, not used here
	*/
	public static void main(String[] args) {

		System.out.println("Welcome to the game!\n");
		Scanner in = new Scanner(System.in);
        Scanner sca= new Scanner(System.in);  
		Scanner pl1= new Scanner(System.in); 
		Scanner pl2= new Scanner(System.in); 
		Scanner pl_Col1= new Scanner(System.in); 
		
		//name
		System.out.println("Player 1, please enter your name:");
		String pl1_Name = pl1.nextLine();
		System.out.println("Player 2, please enter your name:");
		String pl2_Name = pl2.nextLine();
		
		//deciding tokens
		System.out.print("\nPlayer 1 is "+ pl1_Name + " and " + "Player 2 is " + pl2_Name + "\n");  
        System.out.println("How many tokens are needed to win this game? 3 or 4 or 5");
        System.out.println("(The program will start playing Connect Four in case of invalid output.)");
        int tok = sca.nextInt();

        //deciding color
		System.out.println(pl1_Name + ", choose between colour Red and Yellow. " + pl2_Name + " obviously gets the one not chosen. :)");
		System.out.println("Enter the color name or it's initial alphabet (Red or Yellow)");
		String player_Choice = pl_Col1.nextLine();	
		
		char[][] grid = new char[6][7];
        int turn = 1;
		char player = 'Y';
		boolean winner = false;	

        if(player_Choice.equals("Y") || player_Choice.equals("Yellow") || player_Choice.equals("y") || player_Choice.equals("yellow")) {
            System.out.println("\n" + pl1_Name + " is yellow, and " + pl2_Name +" is red.");
        } else if (player_Choice.equals("R") || player_Choice.equals("Red") || player_Choice.equals("r") || player_Choice.equals("red")){
            player = 'R';
            System.out.println("\n" + pl2_Name + " is yellow, and " + pl1_Name +" is red.");
        } else{
            System.out.println("\nInvalid output, player 1 gets color yellow.");
            player = 'Y';
        }

        System.out.println("Game begins!!\n");	

		//using while loop to play the game
        boolean another_Round = true;
        boolean who_Is_Winning = false;
        while(another_Round){
            turn = 1;
            winner = false;
            grid = new char[6][7];
            
            for (int row = 0; row < grid.length; row++){
                for (int col = 0; col < grid[0].length; col++){
                    grid[row][col] = ' ';
                }
            }
            //playing one round of game
            while (winner == false && turn <= 42){
                boolean validPlay = true;
                int play;
                do {
                    display(grid);
                    //letting the user pick a column
                    System.out.print(player + " to play!" + " Pick a column (1-7): ");
                    play = in.nextInt();
                    play = play-1;
                    
                    //validating the move
                    validPlay = validate(play,grid);
                    
                }while (validPlay == false);
                
                //dropping the checker to the desired column
                for (int row = grid.length-1; row >= 0; row--){
                    if(grid[row][play] == ' '){
                        grid[row][play] = player;
                        break;
                    }
                }
                
                //using switch case to call functions according to number of tokens
                switch(tok){
                    case 3:
                        who_Is_Winning = isWinner3(player, grid);
                        break;
                        
                    //case 4:
                    //   who_Is_Winning = isWinner4(player, grid);
                    //    break;
                        
                    case 5:
                        who_Is_Winning = isWinner5(player, grid);
                        break;

                    default:
                        who_Is_Winning = isWinner4(player, grid);
                        break;
                }


                //checking if there is a winner
                winner = who_Is_Winning;
                
                //switch the players
                if (player == 'R'){
                    player = 'Y';
                }else{
                    player = 'R';
                }
                
                turn++;			
            }

            display(grid);
            
            if (winner){
                if (player=='R'){
                    System.out.println("Player Yellow is the Winner!");
                }else{
                    System.out.println("Player Red is the Winner!");
                }
            }else{
                System.out.println("It's a Tie!");
            }     
            
            //checking if players want another round of games
            Scanner sc= new Scanner(System.in); 
            System.out.println("\nDo you want to another round? (enter y/yes to play)");
            String str= sc.nextLine();
            System.out.print("Thank you, you entered: "+str);   
            
            another_Round = (str.matches("([yY]es)|(y)") ? true : false);

        }

	}
	
	/**
	* display is responsible to have the grid of game ready to display
	* at all given times
	* @param grid	Connect Four grid
	* @return grid
	*/
	public static void display(char[][] grid){
		System.out.println("\n");
		for (int row = 0; row < grid.length; row++){
			System.out.print("|");
			for (int col = 0; col < grid[0].length; col++){
				System.out.print(grid[row][col]);
				System.out.print("|");
			}
			System.out.println();
			
		}
		System.out.println("---------------");
		System.out.println(" 1 2 3 4 5 6 7");
		System.out.println();
	}
	
	/**
	* validate checks if the given input is valid
	* It also detects if the column is full and warns the user
	* @param column	the column of the grid
	* @param grid	the connect four grid
	* @return true if the grid is valid for given input
	*/
	public static boolean validate(int column, char[][] grid){
		//valid column?
		if (column < 0 || column > grid[0].length){
			return false;
		}
		
		//full column?
		if (grid[0][column] != ' '){
			System.out.println("\nColumn is full :/ Please try another column. \n");
			return false;
		}
		
		return true;
	}

	/**
	* isWinner3 checks the tokens if they have matched
	* horizontally/vertically/diagonally in the grid 
	* to determine the winner
	* @param player	Player color/position
	* @param grid	Connect Four grid
	* @return true	if condition is found true / 3 tokens match
	*/
    public static boolean isWinner3(char player, char[][] grid){
		//check for 3 horizontally
		for(int row = 0; row<grid.length; row++){
			for (int col = 0;col < grid[0].length - 4;col++){
				if (grid[row][col] == player   && 
					grid[row][col+1] == player &&
					grid[row][col+2] == player ){
					return true;
				}
			}			
		}
		//check for 3 vertically
		for(int row = 0; row < grid.length - 2; row++){
			for(int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col] == player &&
					grid[row+2][col] == player){
					return true;
				}
			}
		}
		//check diagonal / - 3
		for(int row = 4; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 4; col++){
				if (grid[row][col] == player   && 
					grid[row-1][col+1] == player &&
					grid[row-2][col+2] == player){
					return true;
				}
			}
		}
		//check diagonal \ - 3
		for(int row = 0; row < grid.length - 2; row++){
			for(int col = 0; col < grid[0].length - 2; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col+1] == player &&
					grid[row+2][col+2] == player){
					return true;
				}
			}
		}
		return false;
	}
    
	/**
	* isWinner4 checks the tokens if they have matched
	* horizontally/vertically/diagonally in the grid 
	* to determine the winner
	* @param player	Player color/position
	* @param grid	Connect Four grid
	* @return true	if condition is found true / 4 tokens match
	*/
    public static boolean isWinner4(char player, char[][] grid){
		//check for 4 horizontally
		for(int row = 0; row<grid.length; row++){
			for (int col = 0;col < grid[0].length - 3;col++){
				if (grid[row][col] == player   && 
					grid[row][col+1] == player &&
					grid[row][col+2] == player &&
					grid[row][col+3] == player){
					return true;
				}
			}			
		}
		//check for 4 vertically
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col] == player &&
					grid[row+2][col] == player &&
					grid[row+3][col] == player){
					return true;
				}
			}
		}
		//check diagonal / - 4
		for(int row = 3; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row-1][col+1] == player &&
					grid[row-2][col+2] == player &&
					grid[row-3][col+3] == player){
					return true;
				}
			}
		}
		//check diagonal \ - 4
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col+1] == player &&
					grid[row+2][col+2] == player &&
					grid[row+3][col+3] == player){
					return true;
				}
			}
		}
		return false;
	}
    
	/**
	* isWinner5 checks the tokens if they have matched
	* horizontally/vertically/diagonally in the grid 
	* to determine the winner
	* @param player	Player color/position
	* @param grid	Connect Four grid
	* @return true	if condition is found true / 5 tokens match
	*/
	public static boolean isWinner5(char player, char[][] grid){
		//check for 5 vertically
		for(int row = 0; row<grid.length; row++){
			for (int col = 0;col < grid[0].length - 2;col++){
				if (grid[row][col] == player   && 
					grid[row][col+1] == player &&
					grid[row][col+2] == player &&
					grid[row][col+3] == player &&
					grid[row][col+4] == player){
					return true;
				}
			}			
		}
		//check for 5 horizontally
		for(int row = 0; row < grid.length - 4; row++){
			for(int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col] == player &&
					grid[row+2][col] == player &&
					grid[row+3][col] == player &&
					grid[row+4][col] == player){
					return true;
				}
			}
		}
		//check diagonal / - 5
		for(int row = 2; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 2; col++){
				if (grid[row][col] == player   && 
					grid[row-1][col+1] == player &&
					grid[row-2][col+2] == player &&
					grid[row-3][col+3] == player &&
					grid[row-4][col+4] == player ){
					return true;
				}
			}
		}
		//check diagonal \ - 5
		for(int row = 0; row < grid.length - 4; row++){
			for(int col = 0; col < grid[0].length - 4; col++){
				if (grid[row][col] == player   && 
					grid[row+1][col+1] == player &&
					grid[row+2][col+2] == player &&
					grid[row+3][col+3] == player &&
					grid[row+4][col+4] == player ){
					return true;
				}
			}
		}
		return false;
	}
}