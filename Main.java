import java.io.IOException;

/** 
* @author Anna-Oleksandra Chanieva, 2616332
* @version 1.0 
* Main class which contains main method to execute the programm 
**/
public class Main {

    public static void main(String[] args)
    {
        Main m = new Main();
        m.run();
    }

    /** 
     * run() - director method which contains programm menu and executes all programm logic
     * @return void 
     * **/
    public void run()
    {
        Lottery lottery = new Lottery();
        lottery.Generate();
        clrscr();

        /* 
         * main programm loop, designed to print menu and gives user ability to select a menu item
         */
        int menuItem = 0;
        boolean keeplooping = true;
        int matches;
        do 
        {
            PrintProgramTitle();
            PrintMenu();
            menuItem=Genio.getInteger();
            switch (menuItem) {
                case 1:
                    System.out.println("\n***Entering users lottery list***");
                    lottery.userInput();
                    clearScreen();
                    break;
                case 2:
                    System.out.println("\n***Comparing lottery lists...***");
                    matches = lottery.compareLotteryLists(lottery.getUserLotteryList(), lottery.getGeneratedLotteryList());
                    lottery.PrintMatchesResults(matches);
                    clearScreen();
                    break;
                case 3: 
                    System.out.println("\n***Printing lottery numbers entered by the user***");
                    lottery.PrintLottery(lottery.getUserLotteryList());
                    clearScreen();
                    break;
                case 4: 
                    System.out.println("\n***Printing lottery generated by the program***");
                    lottery.PrintLottery(lottery.getGeneratedLotteryList());
                    clearScreen();
                    break;
                case 5: 
                    System.out.println("\n***Saving a lottery lists to a file***");
                    lottery.writeLotteries();
                    clearScreen();
                    break;
                case 6: 
                    System.out.println("\n***Reading a lottery lists from the file***");
                    lottery.readLotteries();
                    clearScreen();
                    break;
                case 7: 
                    keeplooping=false;
                    break;                                                    
                default:
                    System.out.println("Please enter an integer number from 1 to 7! ");
                    clearScreen();
                    break;
            }   

        } while (keeplooping);
    }

    /** 
     * PrintProgramTitle() - prints programm title 
     * @return void 
     * **/
    private void PrintProgramTitle() {
        System.out.print(ConsoleColors.CYAN);
        System.out.println("|-----------------------------------------------|");
        System.out.println("|---Welcome to the Lottery simulator program!---|");
        System.out.println("|               Test your luck!                 |");
        System.out.println("|-----------------------------------------------|");
        System.out.print(ConsoleColors.RESET);
    }
    

    /** 
     * PrintMenu() - prints main programm menu
     * @return void 
     * **/
    public void PrintMenu()
    {
        System.out.print(ConsoleColors.GREEN_BRIGHT);
        System.out.println("Lottery simulator program menu");
        System.out.println("1. Enter 6 lottery numbers within the range 1-50");
        System.out.println("2. Test your luck! Compare your numbers with a Lottery list!");
        System.out.println("3. Print lottery numbers you have entered");
        System.out.println("4. Print generated Lottery numbers");
        System.out.println("5. Save a lottery lists to a file");
        System.out.println("6. Read lottery lists from the file");
        System.out.println("7. Exit");
        System.out.println("");
        System.out.print(ConsoleColors.RESET);

        System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT);
        //System.out.print(ConsoleColors.CYAN_BACKGROUND);
        System.out.print("Please select an option [1-7]: ");
        System.out.print(ConsoleColors.RESET);
    }

    /* 
     * This method was taken from the internet, it is used to clear the console screen
     * Source link: https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
     * @return void 
     */
    public static void clrscr(){
    //Clears Screen in java
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
        else {
            System.out.print("\033\143");
        }
    } catch (IOException | InterruptedException ex) {}
    }

    /* 
     * clearScreen()  This method promts user to press 'y' to continue programm and clear the screen 
     * @return void
     */
    public static void clearScreen()
    {
        char c; 
        do {
            System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT + "Enter 'y' to continue: " + ConsoleColors.RESET);
            c = Genio.getCharacter();       
        } while ((c != 'y')); // 
        clrscr();   
    }

}
