/*
* Name: Olujimi Olugboyega 
* Login: cs11fgr 
* Date: 09/06/2014
* File: Critter.java
* Sources of Help: Lab Tutors
* This controls the slots machines program.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import objectdraw.*;

/*
* Name: This is the critter method
* Purpose: Thisis the abstract class for the critter
* Parameters:None.
* Return:void.
*/  
public abstract class Critter  
{
   protected Location point;// the critter's center
   protected DrawingCanvas canvas;

 /*
* Name: This is the contructor
* Purpose: This initializes the critter objects
* Parameters: loc, canvas.
* Return:void.
*/  
   public Critter(Location loc, DrawingCanvas canvas)
   {
      setLoc(loc);
      this.canvas = canvas; 
   }
   // the abstract methods that the critter classes should use
   public abstract void reactTo(Critter other);
   
   public abstract void kill();
  // the getLoc method 
   public Location getLoc()
   {
      return point;
   }
   // the setLoc method
   public void setLoc(Location loc)
   {
      point = new Location(loc);				
   }

}
