import java.util.Random;
//import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

/** 
 * @author Anna-Oleksandra Chanieva, 2616332
 * @version 1.0
 * 
 * Lottery class which contain all fields and methods which are described in the requements 
 * **/
public class Lottery
{
    // 1.d task, define three constants for the list size and lottery min-max range numbers    
    private static final int LOTTERY_LIST_SIZE = 6;
    private static final int MIN_LOTTERY_NUMBER = 1;
    private static final int MAX_LOTTERY_NUMBER = 50;

    //1.d task userLotteryList - array for the user entered lottery numbers 
    private int [] userLotteryList;
    //1.d task generatedLotteryList - array for the generated lottery numbers 
    private int [] generatedLotteryList;

    /** 
     * this is the constractor of the class, which creates lotteries lists
    **/
    public Lottery()
    {
        this.userLotteryList = new int [LOTTERY_LIST_SIZE];
        this.generatedLotteryList = new int [LOTTERY_LIST_SIZE];
    }

    /** 
     * task 1.c
     * Generate() - method that generates and return the integer array with a lottery list
     * @return integer array
    **/
    public int [] Generate()
    {
        Random r = new Random();
        int randNum;
        boolean unique = false;
        for (int i=0; i<generatedLotteryList.length; i++)
        {
            do
            {
                randNum = r.nextInt(MAX_LOTTERY_NUMBER)+1;
                unique = isUnique(randNum, generatedLotteryList);
            } while(!unique);

            generatedLotteryList[i] = randNum;
        }
        return generatedLotteryList;
    }

    /** 
     * isUnique() - method which checks if the num is a unique in the array 
     * @return boolean
    **/
    public boolean isUnique (int num, int[] list )
    {
        boolean res = true;
        for (int i=0; i<list.length; i++)
        {
            if (list[i] == num)
            {
                res = false;
                //System.out.println("NOT unique" +num);
                break;
            }
        }
        return res;
    }

    /**
     * PrintLottery() - prints Lottery list to console
     * @return void 
     * **/
    public void PrintLottery(int [] lotteryList)
    {
        for (int i=0; i<lotteryList.length; i++)
        {
            System.out.println("Lottery number " + i + " value = " + lotteryList[i]);
        }

    }

    /** 
     * getGeneratedLotteryList() - gets generatedLotteryList field
     * @return int []
     * **/
    public int [] getGeneratedLotteryList()
    {
        return this.generatedLotteryList;
    }

    /**
     * getUserLotteryList() - gets getUserLotteryList field
     * @return int []
     * **/
    public int [] getUserLotteryList()
    {
        return this.userLotteryList;
    }

    /** 
     * clearUserList() - method to set to zero all values of the userLotteryList array
     * It is needed when user will enter data for more than one time
     * @return void
     * **/
    public void clearLotteryList(int[] list)
    {
        for (int i=0; i<list.length; i++)
        {
            list[i] = 0;
        }
    }

    /**
     * task 1.c
     * userInput() - method to recieve user's numbers and saves them to userLotteryList
     * @return int[]
     *  **/
    public int[] userInput()
    {
        int lotteryNumber = 0;

        clearLotteryList(userLotteryList);

        System.out.print(ConsoleColors.GREEN);
        System.out.println("Enter six unique numbers from 1 to 50: ");
        System.out.print(ConsoleColors.RESET);
        for (int i=0; i<userLotteryList.length; i++)
        {
            boolean keeplooping = true;
            do
            {
                System.out.print("Lottery number " + i + ": ");
                lotteryNumber = Genio.getInteger();
                if (isUnique(lotteryNumber, userLotteryList) && inRange(lotteryNumber))
                {
                    userLotteryList[i] = lotteryNumber ;
                    keeplooping = false;
                }
                else
                {
                    System.out.print(ConsoleColors.RED);
                    System.out.println("Entered number " + lotteryNumber + " already exists or is out of range [1-50]! Please enter another number!");
                    System.out.print(ConsoleColors.RESET);
                }
            }while(keeplooping);
        }
        System.out.print(ConsoleColors.PURPLE_BRIGHT);
        System.out.println("Congrats you've entered 6 lottery numbers!");
        System.out.print(ConsoleColors.RESET);
        return userLotteryList;
    }

    /** 
     * task 1.c 
     * inRange() - checks if num is in allowed range (MIN_LOTTERY_NUMBER, MAX_LOTTERY_NUMBER)
     * @return boolean
     * **/
    public boolean inRange(int num)
    {
        boolean res = false;
        if( (num>=MIN_LOTTERY_NUMBER) && (num<=MAX_LOTTERY_NUMBER) )
        {
            res = true;
        } 
        return res;
    }

    /** 
     * trask 1.d 
     * compareLotteryLists() - method that compares two arays of the same type and lengh, and returns number of matches 
     * @return int 
     * **/
    public int compareLotteryLists(int[] theUserLotteryList, int[] theGeneratedLotteryList)
    {
        int matches = 0;
        for (int i=0; i<theUserLotteryList.length; i++)
        {
            for (int j=0; j<theGeneratedLotteryList.length; j++)
            {
                if(theUserLotteryList[i] == theGeneratedLotteryList[j])
                {
                    matches++;
                }
            }
        }
        return matches;
    }

    /** 
     * task 1.f
     * writeLotteries() - saves user's and generated lottery lists to a text file Lottery.txt
     * @return void
     * **/
    public void writeLotteries()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("Lottery.txt", false);
            PrintWriter pw = new PrintWriter(fos); 

            pw.print("User: ");
            for (int i=0; i<userLotteryList.length; i++)
            {
                pw.print(userLotteryList[i] + " ");
            }

            pw.println("");
            pw.print("Generated: ");
            for (int j=0; j<generatedLotteryList.length; j++)
            {
                pw.print(generatedLotteryList[j] + " ");

            }
            pw.println("");
            pw.close();
            System.out.println("File written successfully");
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /** 
     * PrintMatchesResults() - Prints amount of matches 
     * @return void 
     * **/
    public void PrintMatchesResults(int matches)
    {
        switch (matches) {
            case 0:
                System.out.println("No matches!!! Please try again!");
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6: 
                System.out.print(ConsoleColors.GREEN);
                System.out.println("Congratulations!!! The number of matches equals to: " + matches);
                System.out.println(ConsoleColors.RESET);
                break;
            default:
                System.out.print(ConsoleColors.RED);
                System.out.println("Error: number of matches can't be less than 0 and more than 6!");
                System.out.println(ConsoleColors.RESET);
                break;
        }
    }

    /** 
     * task 1.g 
     * readLotteries() -  reads user and generated lottery list from the Lottery.txt file and saves them into lottery arrays correspondingly
     * @return void 
     * **/
    public void readLotteries() 
    {
        // lottery.txt file has 2 lines for user and generated lotteries correspondingly,  
        String[] line = new String[2];

        try 
        {
            FileReader fr = new FileReader("Lottery.txt");        
            BufferedReader buffReader  = new BufferedReader(fr);
            
            line[0] = buffReader.readLine();
            System.out.println(line[0]);
            line[1] = buffReader.readLine();
            System.out.println(line[1]);
    
            buffReader.close();
        }
        catch(Exception fe)
        {
            System.out.print(fe);
        }

        String[] tmp = line[0].split(" ");
        for (int i=0; i<userLotteryList.length; i++)
        {
            userLotteryList[i] = Integer.parseInt (tmp[i+1]);
        }

        tmp = line[1].split(" ");
        for (int i=0; i<generatedLotteryList.length; i++)
        {
            generatedLotteryList[i] = Integer.parseInt (tmp[i+1]);
        }
    }

}