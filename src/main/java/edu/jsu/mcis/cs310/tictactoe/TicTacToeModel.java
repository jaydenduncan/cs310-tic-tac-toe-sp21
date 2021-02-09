package edu.jsu.mcis.cs310.tictactoe;

/**
* TicTacToeModel implements the Model for the Tic-Tac-Toe game.
*
* @author  Jayden Duncan
* @version 2.0
*/
public class TicTacToeModel {
    
    /**
     * The contents of the Tic-Tac-Toe game board
     */ 
    private TicTacToeSquare[][] board;
    
    /**
     * xTurn is true if X is the current player, or false if O is the current
     * player
     */
    private boolean xTurn;
    
    /**
     * The dimension (width and height) of the game board
     */
    private int dimension;

    /**
    * Default Constructor (uses the default dimension)
    */    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_DIMENSION);
        
    }
    
    /**
    * Constructor (uses specified dimension)
    * 
    * @param dimension The <em>dimension</em> (width and height) of the new
    * Tic-Tac-Toe board.
    */
    public TicTacToeModel(int dimension) {
        
        /* Initialize dimension; X goes first */
        
        this.dimension = dimension;
        xTurn = true;
        
        /* Create board as a 2D TicTacToeSquare array */
        
        board = new TicTacToeSquare[dimension][dimension];

        /* Initialize board (fill with TicTacToeSquare.EMPTY) */
        
        // INSERT YOUR CODE HERE
        for(int i=0; i<dimension; i++){

            for(int j=0; j<dimension; j++){
                board[i][j] = TicTacToeSquare.EMPTY;
            }

        }
        
    }
    /**
    * Use isValidSquare(int, int) to check if the specified square is in range,
    * and use isSquareMarked(int, int) to check if the square is currently
    * empty.  If both conditions are satisfied, create a mark in the square for
    * the current player, then toggle xTurn from true to false (or vice-versa)
    * to switch to the other player before returning TRUE.  Otherwise, return
    * FALSE.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value representing the result of the attempt to
    * make this mark: true if the attempt was successful, and false otherwise
    * @see         TicTacToeSquare
    */
    public boolean makeMark(int row, int col) {
        
        // INSERT YOUR CODE HERE
        boolean inRange;
        boolean marked;

        inRange = isValidSquare(row, col);
        if(!inRange){
            return false;
        }

        marked = isSquareMarked(row, col);

        if(!marked){

            if(xTurn){
                board[row][col] = TicTacToeSquare.X;
            }
            else{
                board[row][col] = TicTacToeSquare.O;
            }

            if(xTurn){
                xTurn = false;
            }
            else{
                xTurn = true;
            }

            return true;
        }
        else{
            return false;
        }
        
    }
    
    /**
    * Checks if the specified square is within range (that is, within the bounds
    * of the game board).
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value: true if the square is in range, and false
    * if it is not
    */
    private boolean isValidSquare(int row, int col) {
        
        // INSERT YOUR CODE HERE
        boolean validRow = false;
        boolean validColumn = false;

        if((row >= 0) && (row < dimension)){
            validRow = true;
        }

        if((col >= 0) && (col < dimension)){
            validColumn = true;
        }

        if(validRow && validColumn){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /**
    * Checks if the specified square is marked.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      a Boolean value: true if the square is marked, and false
    * if it is not
    */
    private boolean isSquareMarked(int row, int col) {
                
        // INSERT YOUR CODE HERE
        if(getSquare(row, col) != TicTacToeSquare.EMPTY){
            return true;
        }
        else{
            return false;
        }
            
    }
    
    /**
    * Returns a {@link TicTacToeSquare} object representing the content of the
    * specified square of the Tic-Tac-Toe board.
    *
    * @param  row  the row (Y coordinate) of the square
    * @param  col  the column (X coordinate) of the square
    * @return      the content of the specified square
    * @see         TicTacToeSquare
    */
    public TicTacToeSquare getSquare(int row, int col) {
        
        // INSERT YOUR CODE HERE
        TicTacToeSquare content = board[row][col];
        
        return content; 
            
    }
    
    /**
    * Use isMarkWin() to determine if X or O is the winner, if the game is a
    * tie, or if the game is still in progress. Return the current state of the
    * game as a {@link TicTacToeState} object.
    *
    * @return      the current state of the Tic-Tac-Toe game
    * @see         TicTacToeState
    */
    public TicTacToeState getState() {
        
        // INSERT YOUR CODE HERE
        TicTacToeState gameState;
        TicTacToeSquare xPlayer = TicTacToeSquare.X;
        TicTacToeSquare oPlayer = TicTacToeSquare.O;

        if(isMarkWin(xPlayer)){
            gameState = TicTacToeState.X;
        }
        else if(isMarkWin(oPlayer)){
            gameState = TicTacToeState.O;
        }
        else if(isTie()){
            gameState = TicTacToeState.TIE;
        }
        else{
            gameState = TicTacToeState.NONE;
        }
        
        return gameState;
        
    }
    
    /**
    * Check the squares of the Tic-Tac-Toe board to see if the specified player
    * is the winner.
    *
    * @param  mark  the mark representing the player to be checked (X or O)
    * @return       true if the specified player is the winner, or false if not
    * @see          TicTacToeSquare
    */
    private boolean isMarkWin(TicTacToeSquare mark) {
        
        // INSERT YOUR CODE HERE
        boolean horizontalWin = false;
        boolean verticalWin = false;
        boolean diagonalFromLeftWin = false;
        boolean diagonalFromRightWin = false;
        boolean diagonalWin = false;
        int count;
        int leftDiagonalCount = 0;
        int rightDiagonalCount = 0;

        //check for horizonal win
        for(int i=0; i<dimension; i++){
            count = 0;

            for(int j=0; j<dimension; j++){

                if(getSquare(i, j).toString().equals(mark.toString())){
                    count++;
                }

                if(count == dimension){
                    horizontalWin = true;
                }

            }
        }

        //check for vertical win
        for(int i=0; i<dimension; i++){
            count = 0;

            for(int j=0; j<dimension; j++){

                if(getSquare(j, i).toString().equals(mark.toString())){
                    count++;
                }

                if(count == dimension){
                    verticalWin = true;
                }

            }
        }

        //check for diagonal win from the left
        for(int i=0, j=0; i<dimension; i++, j++){

            if(getSquare(i, j).toString().equals(mark.toString())){
                leftDiagonalCount++;
            }

            if(leftDiagonalCount == dimension){
                diagonalFromLeftWin = true;
            }

        }

        //check for diagonal win from the right
        for(int i=0, j=dimension-1; i<dimension; i++, j--){

            if(getSquare(i, j).toString().equals(mark.toString())){
                rightDiagonalCount++;
            }

            if(rightDiagonalCount == dimension){
                diagonalFromRightWin = true;
            }

        }

        if(diagonalFromLeftWin || diagonalFromRightWin){
            diagonalWin = true;
        }

        //Check for horizontal, vertical, or diagonal win
        if(horizontalWin || verticalWin || diagonalWin){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    /**
    * Check the squares of the board to see if the Tic-Tac-Toe game is currently
    * in a tie state.
    *
    * @return  true if the game is currently a tie, or false otherwise
    */	
    private boolean isTie() {
        
        // INSERT YOUR CODE HERE
        int takenSpaces = 0;
        int boardSpaces = dimension * dimension;
        boolean fullBoard = false;

        for(int i=0; i<dimension; i++){

            for(int j=0; j<dimension; j++){
                if(getSquare(i, j) != TicTacToeSquare.EMPTY){
                    takenSpaces++;
                }
            }

        }

        if(takenSpaces == boardSpaces){
            fullBoard = true;
        }

        if(fullBoard){
            return true;
        }
        else{
            return false;
        }
        
    }

    /**
    * Uses {@link #getState() getState} to checks if the Tic-Tac-Toe game is
    * currently over, either because a player has won or because the game is
    * in a tie state.
    *
    * @return  true if the game is currently over, or false otherwise
    */	
    public boolean isGameover() {
        
        return TicTacToeState.NONE != getState();
        
    }

    /**
    * Getter for xTurn.
    *
    * @return  true if X is the current player, or false if O is the current
    * player
    */
    public boolean isXTurn() {
        
        return xTurn;
        
    }
    
    /**
    * Getter for dimension.
    *
    * @return  the <em>dimension</em> (width and height) of the Tic-Tac-Toe
    * game board
    */
    public int getDimension() {
        
        return dimension;
        
    }
    
    /**
    * <p>Returns the current content and state of the Tic-Tac-Toe game board as
    * a formatted String.  This method <em>must</em> use a {@link StringBuilder}
    * to compose the output String, which should not include any empty lines.</p>
    * <p>Here is an example of how the output for a 3x3 game board should
    * appear (also see the example output on Canvas):</p>
    * <code>&nbsp;&nbsp;012<br>0&nbsp;O&nbsp;&nbsp;<br>1&nbsp;&nbsp;X&nbsp;<br>2&nbsp;O&nbsp;X</code>
    * @return      the representation of the Tic-Tac-Toe game board
    * @see         StringBuilder
    */
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder();
        
        // INSERT YOUR CODE HERE
        StringBuilder columnLabel = new StringBuilder();
        StringBuilder rows = new StringBuilder();
        columnLabel.append("  ");
        for(int i=0; i<dimension; i++){
            columnLabel.append(String.valueOf(i));
        }

        for(int i=0; i<dimension; i++){

            rows.append(String.valueOf(i) + " ");

            for(int j=0; j<dimension; j++){
                rows.append(getSquare(i, j).toString());
            }

            rows.append("\n");

        }

        output.append(columnLabel.toString() + " ");
        output.append("\n" + rows.toString());
        
        return output.toString();
        
    }
    
}