import java.util.*;

public class Minesweeper{

    private Grid g;
    private String level; 

    public static final int OK=77;
    public static final int WIN = 946; 
    private static final int numNEEDED = 20;



    /**
     * minesweepr constructor, creates grid with different difficulty settings
     */
    public Minesweeper()
    {
        Scanner setup = new Scanner(System.in);
        System.out.print("Select level:\n Beginner(B), Intermediate(I), Expert(E):  ");
        level = setup.nextLine();
        level = level.trim();
        if (level.equals("b") || level.equals("B"))
            g =  new Grid(8, 8, 8);
        else if (level.equals("i") || level.equals("I"))
            g =  new Grid(12, 10, 10);
        else if (level.equals("e") || level.equals("E"))
            g =  new Grid(20, 16, 50);
        else 
        {
            System.out.println("Please enter a valid letter.");
            restart();
        }
 
        
        
    }

    /**
     * menu, prints grid and offers options for user to enter 
     */
    public void menu()
    {
        g.toString();
        Scanner keys = new Scanner(System.in);
        System.out.println("\n"+"Whats next?"+"\n"+
            "Options: (U)ncover r c, (F)lag r c, (Q)uit");
        String userInput = keys.nextLine();
        userInput = userInput.trim();
        
        play(userInput); 
            
        
    }

    /**
     * play method
     * sanitzies user input and calls methods depending on what input is
     * 
     * @param input String
     * 
     */
    public void play(String input)
    {
        
            input = input + " ";
            String action = "";
            int spaceCount=0;
            String row= "";
            String col= "";
        try{
            /************input sanitation*****************/
            for (int i=0; i<(input.length()); i++)
            {
                Character c = input.charAt(i);
                if (i==0)
                    action = input.substring(i,i+1);
                else if (c.equals(' ') && spaceCount==0)
                {
                    spaceCount++;
                    row = input.substring(i+1,i+3);
                }
                else if (c.equals(' ') && spaceCount==1)
                {
                    spaceCount++;
                    col = input.substring(i+1,i+3);
                }
            }

            row = row.trim();
            col = col.trim();
            int r = 0;
            int c = 0;
            for (int i=0; i<=numNEEDED; i++)
            {
                String s ="";
                String s2="";
                if (row.equals(s+i))
                    r = i;
                if (col.equals(s2+i))
                    c = i;
            }
            /*******************end of input sanitation***************** */
            
            if (action.equals("u") || action.equals("U"))
            {
                result(g.uncoverSquare(r, c));
            }
            else if (action.equals("f") || action.equals("F"))
            {
                g.flagSquare(r, c);
                menu();
            }
        }

        catch(StringIndexOutOfBoundsException e)
        {
            if (action.equals("q") || action.equals("Q"))
            {
                System.out.println("You selected quit, thank you for playing!");
                g.exposeMines();
                g.toString();
                setPlayAgain();
            }
            else {
                menu();
            }
        }
    }

    /**
     * gets result of the previous move, ends game if won or if mine is uncovered, continues if not
     * @param num
     */
    public void result(int num)
    {
        if (num == OK)
            menu();
        else if(num == WIN)
        {
            System.out.println("You win!");
            g.exposeMines();
            g.toString();
            setPlayAgain();
        }        
        else
        {
            g.exposeMines();
            g.toString();
            System.out.println("Thats a mine, you lose!\n");
            setPlayAgain();
        }
    }

    /**
     * if user selects to play again, calls restart() method, if not game over
     */
    public void setPlayAgain()
    {
        Scanner goAgain = new Scanner(System.in);
        System.out.print("Do you want to play again?(y/n) ");
        String ans = goAgain.nextLine();
        //goAgain.close();
        ans = ans.trim();
        if (ans.equals("y") || ans.equals("Y"))
            restart();
        else System.out.println("Play again soon!");
        
    }

    /**
     * shows diffculty selection again and creates new grid
     */
    public void restart()
    {
        Scanner setup = new Scanner(System.in);
        System.out.print("Select level:\n Beginner(B), Intermediate(I), Expert(E):  ");
        level = setup.nextLine();
        level = level.trim();
        if (level.equals("b") || level.equals("B"))
            g =  new Grid(8, 8, 8);
        else if (level.equals("i") || level.equals("I"))
            g =  new Grid(12, 10, 10);
        else if (level.equals("e") || level.equals("E"))
            g =  new Grid(20, 16, 50);
        else 
        {
            System.out.println("Please enter a valid letter.");
            restart();
        }
        menu();
        
    }

}

// javac Minesweeper.java 