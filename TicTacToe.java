/*
 * CHRIS RAFIAA
 * 260709325
 */
//helper method are at the end!
import java.util.Scanner;
import java.util.Random;

public class TicTacToe{
  public static void main(String[] args){
    play();
  }//end main
  
  //part A
  //method that creates an empty board with n by n dimensions
  public static char[][] createBoard(int n){
    char[][] c = new char[n][n];
    for(int i=0;i<n;i++){
      for (int j=0;j<n;j++){
        c[i][j]=' ';
      }
    }
    return c;
  }//end method createBoard
  
  //part B
  //a method that will display the board
  public static void displayBoard(char[][] c){
    for(int i=0;i<c.length;i++){
      //for loop to make upper bound line after every line
      for(int z=0; z<c.length;z++){
        System.out.print("+-");
      }
      System.out.println('+');
      for(int j=0;j<c[i].length;j++){
        System.out.print("" +'|' + c[i][j]);
      }//end inner loop that is going through every element of every array at once
      System.out.print('|');
      System.out.println();
    }//end outer for loop that is going through every array
    //for loop to make final lower bound line
    for(int z=0; z<c.length;z++){
      System.out.print("+-");
    }
    System.out.println('+');
  }//end method displayBoard
  
  
  //Part C
  //method that will write on the board
  public static void writeOnBoard(char[][] board, char c, int x, int y){
    int size = board.length;
    if(x>size-1){
      throw new IllegalArgumentException ("your row input is too big for the board, try something less than " + size);
    }
    else if(y>size-1){
      throw new IllegalArgumentException ("your column input is too big for the board, try something less than " + size);
    }
    else if(x<0){
      throw new IllegalArgumentException ("your row input is negative, try something between 0 and " + (size-1));
    }
    else if(y<0){
      throw new IllegalArgumentException ("your column input is negative, try something between 0 and " + (size-1));
    }
    else if(board[x][y]!=' '){
      throw new IllegalArgumentException ("there is already a character on this spot silly!");
    }
    //if none of the errors happen
    //the board will use the inputs given below!
    board[x][y]=c;
  }//end method writeOnBoard
  
  //PART D
  //method that takes user move as input through scanner and puts it on the board
  public static void getUserMove(char[][] board){
    System.out.print("please enter your move:");
    Scanner input = new Scanner(System.in);
    int size = board.length;
    int row = input.nextInt();
    int column = input.nextInt();
    //while loop to check if input is invalid
    while(column>size-1 || row>size-1 || row<0 || column<0|| board[row][column]!=' '){
      System.out.print("please enter a valid move:");
      row = input.nextInt();
      column = input.nextInt();
    }//end while loop
    writeOnBoard(board, 'x', row, column);
  }//end method getUserMove
  
  //PART E
  //method that checks for obvious move and if there is
  //to make the move and return true
  //if not, to return false and make no move
  public static boolean checkForObviousMove(char [][] board){
    //Helper method at the bottom
    if(obviousWinOrLoss(board,'o')){
      return true;
    }
    else if(obviousWinOrLoss(board,'x')){
      return true;
    }
    return false;
  }//end method checkForObviousMove
  
  //PART F
  //method that carries out the move for the AI
  public static void getAIMove(char[][] board){
    //checking if theres an obvious move
    //doing the obvious move if there is
    if(checkForObviousMove(board)){
    }
    else{
      //if no obvious move
      //random row and column
      Random row = new Random();
      Random column = new Random();
      int number1= row.nextInt(board.length);
      int number2=column.nextInt(board.length);
      //checking if spot is occupied on board
      while(board[number1][number2]!=' '){
        number1=row.nextInt(board.length);
        number2=column.nextInt(board.length);
      }
      //if not occupied will write on board
      writeOnBoard(board,'o',number1,number2);
    }
  }//end method getAIMove
  
  //PART G
  //method that checks for the winner
  public static char checkForWinner(char[][] board){
    char winner = winner(board, 'o');
    //means AI did not win since winner == ' '
    //if AI didnt win, all that's left
    //is user and no one winning
    if(winner == ' '){
      winner = winner(board, 'x');
    }
    return winner;
  }//end method checkForWinner
  
  
  //PART H
  //Method to stimulate the game
  public static void play(){
    //asking for name
    System.out.println("Please enter your name:");
    Scanner in1 = new Scanner(System.in);
    String name = in1.nextLine();
    System.out.println("Welcome, " + name + "! are you ready to play?");
    System.out.print("Please choose the dimension of your board: ");
    //checking if dimension is an integer
    //if not, keep asking
    //when dimension is logical, use it
    //to create board
    Scanner in2 = new Scanner(System.in);
    int dimension=0;
    while(!in2.hasNextInt()){
      System.out.print("Please choose the dimension of your board: ");
      in2 = new Scanner(System.in);
    }
    if(in2.hasNextInt()){
      dimension=in2.nextInt();
    }
    //creating the board
    char[][] board = createBoard(dimension);
    //coin toss to see who starts the game
    int coinToss = (int)(Math.random()*(2));
    System.out.println("The result of the coin toss is: "+coinToss);
    if(coinToss==0){
      System.out.println("The AI has the first move");
    }
    else if(coinToss==1){
      System.out.println("You have the first move");
    }
    
    //The actual game being played
    
    //if AI starts
    if(coinToss==0){
      for(int i = 0;i<dimension*dimension&&checkForWinner(board)==' ';i++){
        if(i%2==0){
          getAIMove(board);
          System.out.println( "The AI made its move:");
          displayBoard(board);
        }
        else{
          getUserMove(board);
          displayBoard(board);
        }
      }
    }//end AI starting
    //if user starts
    else{
      for(int i = 0;i<dimension*dimension&&checkForWinner(board)==' ';i++){
          if(i%2==0){
            getUserMove(board);
            displayBoard(board);
          }
          else{
            getAIMove(board);
            System.out.println( "The AI made its move:");
            displayBoard(board);
          }
      }
    }//end user starting
    //once a winner has been found
    //or limit of moves has been reached
    //a check for winner happens
    char winner = checkForWinner(board);
    System.out.println("GAME OVER!");
    //printing results of game
    if(winner=='o'){
      System.out.println("You lost");
    }
    else if(winner=='x'){
      System.out.println("You win!");
    }
    else{
      System.out.println("No one wins or loses, it's a tie!");
    }
    
  }//end method play
  
  //// //// //// //// //// //// HELPER METHODS //// //// //// //// //// //// 
  
  
  //Helper method for obvious move
  public static boolean obviousWinOrLoss(char[][] board, char c){
    //for checking obvious move
    int counter=0;
    int counter2=0;
    //for checking rows for obvious move
    for(int row=0;row<board.length;row++){
      counter=0;
      counter2=0;
      //for going through the elements in each row
      //checking for obvious move
      for(int i=0;i<board.length;i++){
        if(board[row][i]==c){
          counter++;
        }
        if(board[row][i]!=' '){
          counter2++;
        }
        
      }//loop goes throw each row
      if(counter2==board.length){
      }
      //if there is an obvious move, to return true
      //and replace the space with an 'o'
      else if(counter==board.length-1){
        for(int j=0;j<board.length;j++){
          if(board[row][j]==' '){
            writeOnBoard(board,'o',row,j);
          }
        }
        return true;
      }//end if for obvious row move
    }//end for checking obvious rows
    
    //for checking obvious columns move
    counter=0;
    counter2=0;
    for(int column=0;column<board.length;column++){
      counter=0;
      counter2=0;
      //for going through each element of the column
      for(int j=0;j<board.length;j++){
        if(board[j][column]==c){
          counter++;
        }
        if(board[j][column]!=' '){
          counter2++;
        }
      }
      if(counter2==board.length){
      }
      //if there is an obvious move, to return true
      //and replace the space with an 'o'
      else if(counter==board.length-1){
        for(int z=0;z<board.length;z++){
          if(board[z][column]==' '){
            writeOnBoard(board,'o',z,column);
          }
        }
        return true;
      }
    }//end of checking obvious moves columns
    
    //for checking obvious moves diagonal left to right
    counter=0;
    counter2=0;
    //for going through each element of the diagonal
    for(int a=0;a<board.length;a++){
      if(board[a][a]==c){
        counter++;
      }
      if(board[a][a]!=' '){
        counter2++;
      }
    }
    if(counter2==board.length){
    }
    //if there is an obvious move, to return true
    //and replace the space with an 'o'
    else if(counter==board.length-1){
      for(int b=0;b<board.length;b++){
        if(board[b][b]==' '){
          writeOnBoard(board,'o',b,b);
        }
      }
      return true;
    }
    
    //for checking obvious move diagonal right to left
    counter=0;
    counter2=0;
    int x=board.length-1;
    for(int g=0;g<board.length&&x>=0;g++){
      if(board[g][x]==c){
        counter++;
      }
      if(board[g][x]!=' '){
        counter2++;
      }
      x--;
    }
    
    //if there is an obvious move, to return true
    //and replace the space with an 'o'
    if(counter2==board.length){
    }
    else if(counter==board.length-1){
      x=board.length-1;
      for(int g=0;g<board.length&&x>=0;g++){
        if(board[g][x]==' '){
          writeOnBoard(board,'o',g,x);
        }
        x--;
      }
      return true;
    }
    return false;
  }// end helper class for checking obvious move
  
  //helper method to check for winner
  
  public static char winner(char[][] board, char c){
    char winner=' ';
    //checking rows for winner
    int j;
    int counter=0;
    for(j=0;j<board.length;){
      int i;
      //checking if all are equal to char c
      //char c will be 'x' or 'o'
      for(i=0;i<board.length;i++){
        if(board[j][i]==c){
          counter++;
        }
      }
      //if the winner is in that row
      if(counter==board.length){
        winner= c;
        break;
      }
      //if no winner in that row
      //check next row
      else{
        j++;
        counter=0;
      }
    }
    
    //checking columns for winner
    counter=0;
    for(j=0;j<board.length;){
      int i;
      //go through the column
      for(i=0;i<board.length;i++){
        if(board[i][j]==c){
          counter++;
        }
      }
      if(counter==board.length){
        winner = c;
        break;
      }
      //update to next column
      else{
        j++;
        counter=0;
      }
    }
    
    //checking diagonal top left to bottom right
    counter=0;
    for(j=0;j<board.length;j++){
      if(board[j][j]==c){
        counter++;
      }
    }
    if(counter==board.length){
      winner=c;
    }
    
    //checking diagonal top right to bottom left
    int i=0;
    counter=0;
    for(j=board.length-1;j>=0&&i<board.length;){
      if(board[i][j]==c){
        counter++;
      }
      j--;
      i++;
    }
    if(counter==board.length){
      winner=c;
    }
    //return result
    return winner;
  }//end method winner
}//end class