import java.util.*;

public class Grid 
{
  private Square[][] grid;
  private int width;
  private int height;
  private int numMines = 0;
  private int numSquaresUncovered;

  public static final int OK=77;
  public static final int WIN = 946; //win 
  public static final int MINE = 6463; //mine 

  /**
   * creates grid object params
   * @param width int
   * @param height int
   * @param numMines int
   */
  public Grid(int width, int height, int numMines)
  {
    this.width = width;
    this.height = height;
    this.numSquaresUncovered = 0;
    this.numMines = numMines;

    grid = new Square[height][width];

    /***********adds mines in random places*************/
     for (int i=1; i<=numMines; i++)
     {
       Random rand = new Random();
       int row = rand.nextInt(height);
       int col = rand.nextInt(width);
       MineSquare mine = new MineSquare();
       grid[row][col] = mine;
     }
     /***********************************************/

     /************adds numbersquare object to grid*********** */
    for (int i=0; i<height; i++)
    {
      for (int h=0; h<width; h++)
      {
        if((grid[i][h] instanceof MineSquare)==false)
        {
          NumberSquare sqr = new NumberSquare(getNeighbors(i,h), i, h);
          grid[i][h] = sqr; //row.add(sqr);
        }
      }
    }
    /***********************************************/

  }

  /**
   * gets number of mines surrounding a square
   * @param r int
   * @param c int
   */
  public int getNeighbors(int r, int c)
  {
    int mineCount = 0;
    for (int row = r-1; row<=(r+1); row++)
    {
      if (row<height && row>=0)
        for (int col = c-1; col<=(c+1); col++)
        {
          if (col<width && col>=0 )
            if (grid[row][col] != null && grid[row][col].isMine()==true)
              mineCount++;
        }
    } 
    return mineCount;
  }

  /**
   * uncovers square, returns MINE if the square is a mine, WIN if all non mines are uncovered,
   * and OK if its not a mine and there are still others squares to uncover
   * @param r int
   * @param c int
   * @return MINE
   * @return WIN
   * @return OK
   */
  public int uncoverSquare(int r, int c)
  {
    if (grid[r][c].isFlagged()==true)
    {
      System.out.println("That square is flagged, select another or unflag it.");
      return OK;
    }
    
    else{ //only uncovers sqaures if theyre not flagged

      if (grid[r][c].isMine()==true)
        return MINE;

      else if (getNeighbors(r,c) == 0)
      {
        for (int row = r-2; row<= (r+2); row++)
        {
          if (row<height && row>=0)
            for (int col = c-2; col<=(c+2); col++)
            {
              if (col<width && col>=0 && (grid[row][col].isMine()==false) && (grid[row][col].isUncovered()==false))
              {
                grid[row][col].uncover();
                numSquaresUncovered++;
              }
                  
            }
        } 
      }

      else if (getNeighbors(r,c) == 1)
      {
        for (int row = r-1; row<= (r+1); row++)
        {
          if (row<height && row>=0)
            for (int col = c-1; col<=(c+1); col++)
            {
              if (col<width && col>=0 && (grid[row][col].isMine()==false) && (grid[row][col].isUncovered()==false))
              {
                grid[row][col].uncover();
                numSquaresUncovered++;
              }   
            }
        }
      }

      else 
      {
        grid[r][c].uncover();
        numSquaresUncovered++;
      }

      if (numSquaresUncovered == ((height*width)-numMines))
        return WIN;
      return OK;
    }
  }

/**
 * exposeMines 
 * goes through the grid, if its a mine it exposes it 
 */
  public void exposeMines()
  {
    for (int i=0; i<height; i++)
    {
      for (int h=0; h<width; h++)//adds x to each row
      {
        if(grid[i][h].isMine())
          grid[i][h].uncover();
      }
    }
  }


  /**
   * flagSquare, calls the square's flagSquare method and sets the element of the square to be f
   * @param row int 
   * @param col int
   */
  public void flagSquare(int row, int col)
  {
    grid[row][col].flagSquare();
    grid[row][col].setElement("f");
  }

  /**
   * getSqr, gets the square object at location r c in the col 
   * @param r
   * @param c
   * @return Square object
   */
  public Square getSqr(int r, int c)
  {
    return grid[r][c];
  }

  /**
   * Overridden toString method
   * @return String
   */
  @Override 
  public String toString()
  {

    //column title, prints 1-11 horizontally 
    System.out.print("  ");
    for (int i =0; i<=(width-1); i++)
      System.out.printf(" %2d",i);
    System.out.print("\n");

     //prints out grid
    for (int i = 0; i < grid.length; i++) 
    {
      int count =0;
      System.out.printf("%2d",i);//row title
      for(int j = 0; j < grid[i].length; j++) 
      {
          System.out.printf("  %s",grid[i][j]);
          count++;
          if (count==(width))
              System.out.println("");
      }
    }
    return "";
  }

}


//  javac Grid.java 