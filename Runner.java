/*
* Name: Olujimi Olugboyega 
* Login: cs11fgr 
* Date: 09/06/2014
* File: Runner.java
* Sources of Help: Lab Tutors
* This controls the runner.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import objectdraw.*;
import java.util.*;

public class Runner extends Critter
{
   private Location location;
   private static final double R_DIM = 15;
   private FilledRect rect;
   private ArrayList<Location>chaserLoc;
   private Critter copyCritter;
   private Location finalLoc;
   private RandomDoubleGenerator randomX;
   private RandomDoubleGenerator randomY;
   private double randX, randY;
/*
* Name: This is the Runner constructor
* Purpose: This initilazed the runner object
* Parameters: location, canvas.
* Return:void.
*/ 
   public Runner(Location location, DrawingCanvas canvas)
   {      
      super(location, canvas);
      rect = new FilledRect(location, R_DIM, R_DIM, canvas); 
      rect.setColor(Color.CYAN);
   }
/*
* Name: This is the farthestLoc method
* Purpose: This calculates the closest coordinates for
* the runner to move to.
* Parameters:None.
* Return:void.
*/ 
   public void farthestLoc()
   {
      double distance = 0;
      double largestDistance = 0;
      chaserLoc = new ArrayList<Location>();//the array list
      // Below are all the possibel locations
      Location loc1 = new Location(rect.getX() + 1,rect.getY() + 1);
      chaserLoc.add(loc1);// add them to the array list
      Location loc2 = new Location(rect.getX() + 1,rect.getY());
      chaserLoc.add(loc2);
      Location loc3 = new Location(rect.getX() + 1,rect.getY() -1);
      chaserLoc.add(loc3);
      Location loc4 = new Location(rect.getX(), rect.getY() - 1);
      chaserLoc.add(loc4);
      Location loc5 = new Location(rect.getX() - 1,rect.getY() - 1);
      chaserLoc.add(loc5);
      Location loc6 = new Location(rect.getX() - 1, rect.getY());
      chaserLoc.add(loc6);
      Location loc7 = new Location(rect.getX() - 1, rect.getY() + 1);
      chaserLoc.add(loc7);
      Location loc8 = new Location(rect.getX() , rect.getY() + 1);
      chaserLoc.add(loc8);
      //This is the closest critter which is the target location
      Location targetLoc = new Location(copyCritter.getLoc().getX()
      ,copyCritter.getLoc().getY());
      // The loop to compare all the directions to the target
      for (int i = 0; i < chaserLoc.size(); i++)
      {
         distance = chaserLoc.get(i).distanceTo(targetLoc);
         if(distance > largestDistance)
         {
            largestDistance = distance;
            finalLoc = chaserLoc.get(i);
         }
 
      }

   }
/*
* Name: This is the randLoc method
* Purpose: This calculates the random coordinates for
* the chaser to move to.
* Parameters:None.
* Return:void.
*/ 
   public void randLoc()
   {
      randomX = new RandomDoubleGenerator(1, canvas.getWidth());
      randomY = new RandomDoubleGenerator(1, canvas.getHeight());
      randX = randomX.nextValue();
      randY = randomY.nextValue();
   }
/*
* Name: This is the reactTo method
* Purpose: This controls how the runner should react when a critter
* is on the canvas
* Parameters: other.
* Return:void.
*/  
   public void reactTo(Critter other)
   {
      if(other != null)
      {
         copyCritter = other;// variables to be used in 
                             // the farther loc method 
         farthestLoc();

         double dx = finalLoc.getX() - rect.getX();
         double dy = finalLoc.getY() - rect.getY();
            
         // if statement to make sure that it is in the boundaries
         if(rect.getX() > 0 && rect.getX() <= canvas.getWidth() - R_DIM
         && rect.getY() > 0 && rect.getY() <= canvas.getHeight() - R_DIM)
         {
            rect.move(dx, dy);  
            setLoc(new Location(rect.getX() + dx, rect.getY() + dy));
         }
         else
         {
            randLoc();
            rect.moveTo(randX, randY);
            setLoc(new Location(rect.getX(),rect.getY()));
        
         }
          
      }     
   }
/*
* Name: This is the kill method
* Purpose: This contains the instruction on what happens
* when the killed method is called
* Parameters:None.
* Return:void.
*/  
   public void kill()
   {
      rect.removeFromCanvas();
   }
}
