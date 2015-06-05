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

public class Random extends Critter
{
   private Location location;
   private static final double R_DIM = 15;
   private Line horizLine, vertLine;
   private FilledRect rect;
   private double x,y;
   private double randX,randY;
   private RandomIntGenerator randInt = new RandomIntGenerator(-10, 10);
   private RandomDoubleGenerator randomX;
   private RandomDoubleGenerator randomY;
/*
* Name: This is the Random constructor
* Purpose: This initilazed the random object
* Parameters: location, canvas.
* Return:void.
*/ 
   public Random(Location location, DrawingCanvas canvas)
   {
      super(location, canvas);
      
      horizLine = new Line(location.getX(), location.getY() + R_DIM/2,
      location.getX() + R_DIM,location.getY() + R_DIM/2, canvas);
      vertLine = new Line(location.getX() + R_DIM/2, location.getY(),
      location.getX() +R_DIM/2, location.getY() + R_DIM, canvas);
      
      horizLine.setColor(Color.ORANGE);
      vertLine.setColor(Color.ORANGE);
         
   }
/*
* Name: This is the randMove() method
* Purpose: This makes the runner to move randomly
* Parameters:None
* Return:void.
*/ 
   public void randMove()
   {
      x = randInt.nextValue();
      y = randInt.nextValue();

   }
/*
* Name: This is the reactTo method
* Purpose: This controls how the random should react when a critter
* is on the canvas
* Parameters: other.
* Return:void.
*/  
   public void reactTo(Critter other)
   {
      randMove();//call the ranMOve method
      // make sure that it is in its boundaries
      if((vertLine.getEnd().getY() + y < canvas.getHeight() - R_DIM/2 
      && vertLine.getEnd().getY() + y > 0) && ( horizLine.getEnd().getX() + 
      x < canvas.getWidth() - R_DIM/2 && 
       horizLine.getEnd().getX()  + x > 0))
      {
         // move the lines
         vertLine.move(x,y);
         horizLine.move(x,y);
         setLoc(new Location(vertLine.getEnd().getX()+ x,
         vertLine.getEnd().getY() + y));
         
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
      horizLine.removeFromCanvas();
      vertLine.removeFromCanvas();
   }
}
