/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab3.maze;
import java.util.Scanner;

/**
 *
 * @author Michael Valentino-Manno
 */
public class Maze {
    
    int personRow = 0;  //coords for person, will be found with findStart() method
    int personCol = 0;
    int handRow = 0;  //coords for persons right hand, will be found with findStart() method
    int handCol = 0;
    Scanner in = new Scanner(System.in);  //scanner used so maze updates when user hits enter
    
    char [] [] maze = new char[] [] {                //prof said we should hard code in the maze
      { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
      { '#', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#' },
      { '.', '.', '#', '.', '#', '.', '#', '#', '#', '#', '.', '#' },
      { '#', '#', '#', '.', '#', '.', '.', '.', '.', '#', '.', '#' },
      { '#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '#' },
      { '#', '#', '#', '#', '.', '#', 'F', '#', '.', '#', '.', '#' },
      { '#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
      { '#', '#', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#' },
      { '#', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '#' },
      { '#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '.', '#' },
      { '#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '#' },
      { '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
    } ;
    
    public Maze()
    {
        findStart();   //method that finds the coords of person and hand starting position
        Solve(personRow, personCol, handRow, handCol); //call solve method with starting coords
    }
    
    private void Solve(int pRow, int pCol, int hRow, int hCol)
    {
        if(maze[pRow][pCol] != 'F')  //if position isnt on the finish
        {
        maze[pRow][pCol] = 'O';  //rep current position with an O
        print();     //print maze
        }
     
        if(maze[pRow][pCol] == 'F')   //maze is done
        {
            maze[pRow][pCol] = 'O';  //Show final spot
            step();                 //step method
            print();                //print maze
            System.out.println("Maze has been finished");
        }
        else
        {
            maze[pRow][pCol] = 'X'; //update path walked with an X
            
            if(pRow < hRow) //if your hands row is greater, you are facing east
            {
                if(maze[hRow][hCol] == '.' || maze[hRow][hCol] == 'X' || maze[hRow][hCol] == 'F')  //Right hand not on a wall
                {
                    step();   //step method
                    Solve(pRow + 1, pCol, hRow, hCol - 1);    //turn right 90 degrees and step south
                }
                else if(maze[hRow][hCol] == '#' && (maze[pRow][pCol + 1] == '.' || maze[pRow][pCol + 1] == 'X' || maze[pRow][pCol + 1] == 'F'))  //Right hand on wall and
                {                                                                   //the space in front of you isn't a wall
                    step();           
                    Solve(pRow, pCol + 1, hRow, hCol + 1);  //step east
                }
                else if(maze[hRow][hCol] == '#' && maze[pRow][pCol + 1] == '#')  //Right hand on wall and space in front is a wall
                {
                     step();
                     Solve(pRow, pCol, hRow - 1, hCol + 1);  //turn left 90 degrees
                }
            }
            else if(pRow > hRow) //if your hands row is less than your row, you are facing west
            {
                if(maze[hRow][hCol] == '.' || maze[hRow][hCol] == 'X' || maze[hRow][hCol] == 'F')  //Right hand not on a wall
                {
                    step(); 
                    Solve(pRow - 1, pCol, hRow, hCol + 1);    //turn right 90 degrees and step north
                }
                else if(maze[hRow][hCol] == '#' && (maze[pRow][pCol - 1] == '.' || maze[pRow][pCol - 1] == 'X' || maze[pRow][pCol - 1] == 'F'))  //Right hand on wall and
                {                                                                   //the space in front of you isn't a wall
                    step();           
                    Solve(pRow, pCol - 1, hRow, hCol - 1);  //step west
                }
                else if(maze[hRow][hCol] == '#' && maze[pRow][pCol - 1] == '#')  //Right hand on wall and space in front is a wall
                {
                     step();
                     Solve(pRow, pCol, hRow + 1, hCol - 1);  //turn left 90 degrees
                }
            }
            else if(pCol < hCol) //if your hands col is greater than yours, you are facing north
            {
                if(maze[hRow][hCol] == '.' || maze[hRow][hCol] == 'X' || maze[hRow][hCol] == 'F')  //Right hand not on a wall
                {
                    step();
                    Solve(pRow, pCol + 1, hRow + 1, hCol);  //turn right 90 degrees and step east
                }
                else if(maze[hRow][hCol] == '#' && (maze[pRow - 1][pCol] == '.' || maze[pRow - 1][pCol] == 'X' || maze[pRow - 1][pCol] == 'F')) //Right hand on wall and
                {                                                                   //the space in front of you isn't a wall
                    step();
                    Solve(pRow - 1, pCol, hRow - 1, hCol);  //step north
                }
                else if(maze[hRow][hCol] == '#' && maze[pRow - 1][pCol] == '#')  //right hand on wall, and space in front is a wall
                {
                    step();
                    Solve(pRow, pCol, hRow - 1, hCol - 1);  //turn left 90 degrees
                }
                
            }
            else if(pCol > hCol) //if your hands col is less than yours, you are facing south
            {
                if(maze[hRow][hCol] == '.' || maze[hRow][hCol] == 'X' || maze[hRow][hCol] == 'F')  //Right hand not on a wall
                {
                    step();
                    Solve(pRow, pCol - 1, hRow - 1, hCol);  //turn right 90 degrees and step west
                }
                else if(maze[hRow][hCol] == '#' && (maze[pRow + 1][pCol] == '.' || maze[pRow + 1][pCol] == 'X' || maze[pRow + 1][pCol] == 'F'))  //Right hand on wall
                {                                                 //and the space in front of you isn't a wall
                    step();
                    Solve(pRow + 1, pCol, hRow + 1, hCol);
                }
                else if(maze[hRow][hCol] == '#' && maze[pRow + 1][pCol] == '#')  //right hand on wall and space in front is a wall
                {
                    step();
                    Solve(pRow, pCol, hRow + 1, hCol + 1);  //turn left 90 degrees
                }
            }
        }
        
        
    }
    
    private void print()
    {
        for(int i = 0; i < maze.length; i++)  //nested for to print out entire maze
        {
            for(int j = 0; j < maze[i].length; j++)
            {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private void step()
    {
        in.nextLine(); //step method so maze wont update until user hits enter
    }
    
    private void findStart()
    {
        for(int i = 0; i < maze.length; i++)  //nested for to go through entire maze
        {
            for(int j = 0; j < maze[i].length; j++)
            {
                if((i == 0 || i == 11 || j == 0 || j == 11) && maze[i][j] == '.')  //start has to be in
                {             //the first or last row or col, and must be a '.'
                    personRow = i;   //once that one '.' is found, update the starting Row and Col
                    personCol = j;
                    if(i == 0)  //depending on the side of the maze starting on, your hands position
                    {        //is found slightly differently
                        handRow = i;  //if its in the first row, your hand starts one col less than you
                        handCol = j - 1;
                    }
                    else if(i == 11) //last row
                    {
                        handRow = i;
                        handCol = j + 1;  //hand starts one col greater than yours
                    }
                    else if(j == 0)  //first col
                    {
                        handRow = i + 1;  //hand starts one row greater than you
                        handCol = j;
                    }
                    else if(j == 11)  //last col
                    {
                        handRow = i - 1;  //hand starts one row less than you
                        handCol = j;
                    }
                }
            }
        }       
    }
}
