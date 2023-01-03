public class NumberSquare extends Square //reps a square thats not a mine
{
  private int neighborMines; //num of adjacent squares that are mines
  private int myRow; //sqaures row
  private int myCol; //squares col

  /**
   * NumberSquare constructor
   * @param neighborMines int
   * @param myRow int
   * @param myCol int
   */
  public NumberSquare(int neighborMines, int myRow, int myCol)
  {
    super();
    this.neighborMines = neighborMines;
    this.myRow = myRow;
    this.myCol = myCol;
  }

  /* uncover, sets the element in the square to be either _ if it borders no mines
   * or the number of mines it borders
   */
  @Override
  public void uncover()
  {

    if (super.isFlagged() == false)
    {
      super.setUncovered();
      if (neighborMines == 0)
      {
        super.setElement("_");
      }
      
      else
        for (int i=1; i<=9; i++)
        {
          String s = "";
          if (neighborMines == i)
          {
            super.setElement(s+i);
          }
        }
    }

  }

  /**
   * getNeighborMines, returns the number of mines surrounds a square
   * @return neighbhorMines int
   */
  public int getNeighborMines()
  {
    return neighborMines;
  }

  /* isMine, returns if square object is mine or not
   * @return boolean 
   */
  @Override
  public boolean isMine()
  {
    return false;
  }

}

// javac NumberSquare.java
