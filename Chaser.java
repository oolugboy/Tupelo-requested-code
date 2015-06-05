/*
* Name: Olujimi Olugboyega 
* Login: cs11fgr 
* Date: 09/06/2014
* File: chaser.java
* Sources of Help: Lab Tutors, Ka Chan
* This contains the chaser
* it reacts to events.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import objectdraw.*;
import java.util.*;

public class Chaser extends Critter
{
   private Location location;
   private static final double R_DIM = 15;
   private FilledOval oval;
   private ArrayList<Location> chaserLoc;
   private  Critter copyCritter;
   private  Location finalLoc;
/*
* Name: This is the chaser constructor
* Purpose: This initilazed the chaser object
* Parameters: location, canvas.
* Return:void.
*/ 
   public Chaser(Location location, DrawingCanvas canvas)
   {      
      super(location, canvas);
      oval = new FilledOval(location, R_DIM, R_DIM, canvas); 
      oval.setColor(Color.MAGENTA);
   
   }
/*
* Name: This is the closestLoc method
* Purpose: This calculates the closest coordinates for
* the chaser to move to.
* Parameters:None.
* Return:void.
*/  
   public void closestLoc()
   {
      
      double distance = 0;
      double smallestDistance = 1000000;
      chaserLoc = new ArrayList<Location>();// the array list
      // below are all the possible directions to move
      Location loc1 = new Location(oval.getX() + 1,oval.getY() + 1);
      chaserLoc.add(loc1);// add them to the array list
      Location loc2 = new Location(oval.getX() + 1,oval.getY());
      chaserLoc.add(loc2);
      Location loc3 = new Location(oval.getX() + 1,oval.getY() -1);
      chaserLoc.add(loc3);
      Location loc4 = new Location(oval.getX(), oval.getY() - 1);
      chaserLoc.add(loc4);
      Location loc5 = new Location(oval.getX() - 1,oval.getY() - 1);
      chaserLoc.add(loc5);
      Location loc6 = new Location(oval.getX() - 1, oval.getY());
      chaserLoc.add(loc6);
      Location loc7 = new Location(oval.getX() - 1, oval.getY() + 1);
      chaserLoc.add(loc7);
      Location loc8 = new Location(oval.getX() , oval.getY() + 1);
      chaserLoc.add(loc8);
      // This is the closest critter which is the target location
      Location targetLoc = new Location(copyCritter.getLoc().getX()
      ,copyCritter.getLoc().getY());
      // the for loop to compare all the directions to the target
      for (int i = 0; i < chaserLoc.size(); i++)
      {
         distance = chaserLoc.get(i).distanceTo(targetLoc);
         if(distance < smallestDistance)
         {
            smallestDistance = distance;
            finalLoc = chaserLoc.get(i);
               
         }    
      }               
      
   }
/*
* Name: This is the reactTo method
* Purpose: This controls how the chaser should react when a critter
* is on the canvas
* Parameters: other.
* Return:void.
*/  
   public void reactTo(Critter other)
   {   
      if((other instanceof Chaser))// to check if it is a chaser
      return;
      
      if( other != null)  
      {     
          
         copyCritter = other; // variable to be used in the
                              // closes loc method
         closestLoc();//call the closes loc

         double dx = finalLoc.getX() - oval.getX(); 
         double dy = finalLoc.getY() - oval.getY(); 
      
         // if statement to make sure that it is in the boundaries
         if(oval.getX() + dx > 0 && 
         oval.getX() + dx <= canvas.getWidth() - R_DIM/2
         && oval.getY() + dy > 0  && 
         oval.getY() + dy <= canvas.getHeight() - R_DIM/2)
         { 
            oval.move(dx, dy); 
            setLoc(new Location(oval.getX() + dx, oval.getY() + dy));
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
      oval.removeFromCanvas();
   }
}   

