public abstract class Square
{
  private String element; // current rep of square * _ 1 2 3
  private boolean flagged; //is  the square flagged or not
  private boolean uncovered;//is the sqaure uncovered or not

  /**
   * Square constructor, sets instance variables to default values
   */
  public Square()
  {
    element = "x";
    flagged = false;
    uncovered = false;
  }

  /**
   * Alt constructor for Square that allows for instance variables to be set 
   * @param element String
   * @param flagged boolean
   * @param uncovered boolean
   */
  public Square(String element, boolean flagged, boolean uncovered)
  {
    this.element = element;
    this.flagged = flagged;
    this.uncovered = uncovered;
  }

  /**
   * isFlagged, returns if flagged
   * @return flagged boolean
   */
  public boolean isFlagged()
  {
    return flagged;
  }

  /**
   * isUncovered()
   * @return uncovered boolean
   */
  public boolean isUncovered()
  {
    return uncovered;
  }

  /**
   * flagSquare, sets flagged as true
   */
  public void flagSquare()
  {
    flagged = true;
  }

  /**
   * setUncovered, sets uncovered as true
   */
  public void setUncovered()
  {
    uncovered = true;
  }

  /**
   * setElement, sets element 
   * @param element String
   */
  public void setElement(String element)
  {
    this.element = element;
  }

  public abstract void uncover();
  public abstract boolean isMine();

  /** 
   * toString
   * @return String
   */
  @Override
  public String toString()
  {
    return String.format("%s", element);
  }

}

// javac Square.java
