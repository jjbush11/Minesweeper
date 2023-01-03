public class MineSquare extends Square //reps a square that has a mine
{
  /**
   * MineSquare constructor
   * calls super class 
   */
  public MineSquare()
  {
    super("x", false, false);
  }
  
  /**
   * uncover, calls super setUncover method and super setElement method
   */
  @Override
  public void uncover()
  {
    super.setUncovered();
    super.setElement("*");
  }

  
  /**
   * isMine
   * @return boolean
   */
  @Override
  public boolean isMine()
  {
    return true;
  }
}

// javac MineSquare.java
