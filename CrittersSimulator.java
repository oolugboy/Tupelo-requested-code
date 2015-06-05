/*
* Name: Olujimi Olugboyega 
* Login: cs11fgr 
* Date: 09/06/2014
* File: CrittersSimulator.java
* Sources of Help: Lab Tutors
* This controls the slots machines program.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import objectdraw.*;
import java.util.*;

public class CrittersSimulator extends ActiveObject
{
   //Below are the instance method needed
   private static final int DELAY = 40; 
   private ArrayList<Critter>critters;
   private boolean stopped;
   private Random random;
   private Chaser chaser;
   private Runner runner;
   private static double distance; 
    
/*
* Name: This is the CrittersSimulator constructor
* Purpose: This constructs the simulator arrayList
* Parameters:None.
* Return:void.
*/  
   public CrittersSimulator( ArrayList<Critter> critters)
   {    
      this.critters = critters;   
      start();
   }
/*
* Name: This is the  run method
* Purpose: This contains the beginning instructions for the program
* on run time
* Parameters:None.
* Return:void.
*/  

   public void run()
   {
      
      while(true)
      {
         pause(DELAY);
         setStop(stopped);     
         if(stopped == false)//Boolean to make sure that it 
                              //  is in its started state
         {
            for(int i = 0; i < critters.size(); i++)
            {
              
               Critter closest = getClose(critters.get(i));
               critters.get(i).reactTo(closest);//react to the closes critter
             
            }                
         }
      }
   
   }  
/*
* Name: This is the setStop method
* Purpose: This initializes the stopped boolean to the 
* controller stopped
* Parameters: stopped.
* Return:void.
*/ 
   public void setStop(boolean stopped)
   {
      this.stopped = stopped;
           
   }
/*
* Name: This is the getClose method
* Purpose: This gets the closes critter to the one passed into the 
* method
* Parameters: origin.
* Return:void.
*/  
   public Critter getClose(Critter origin)
   {
      distance = 0;
      double smallestDistance =  100000;
      Critter closest = null;
      
           
         for(int j = 0;j < critters.size(); j++)
         {
            if(origin != critters.get(j))// make sure that they are not 
                                         // the same critter
            {
               // calculate the distane between the critters
               distance = origin.getLoc().distanceTo(critters.get(j).getLoc());
            
               if(origin instanceof Chaser)//if the selected critter is a chaser

               {
                  // if statement to make sure that it is not compared to another chaser;
                  if(!(critters.get(j)instanceof Chaser) && distance < smallestDistance)
                  {
                     smallestDistance = distance;
                     closest = critters.get(j);
                  }
                  // what happens if it is compared to another critter
                  else if( critters.get(j)instanceof Chaser)
                  {
                     continue;
                  }
 
               }
            // When the critter is not a chaser
            else if(origin != critters.get(j) && distance < smallestDistance)
            {
               smallestDistance = distance;
               closest = critters.get(j); 
            } 
            }  
         }
      
        
      
      return closest;
          
   } 
} 
