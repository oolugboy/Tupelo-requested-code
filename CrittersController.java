/*
* Name: Olujimi Olugboyega 
* Login: cs11fgr 
* Date: 09/06/2014
* File: CrittersController.java
* Sources of Help: Lab Tutors
* This controls the critters program.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import objectdraw.*;
import java.util.*;

public class CrittersController extends WindowController 
implements ActionListener
{
   //Below are the buttons and booleans used to control
   //how the user interacts with the program
   private JButton startButton = new JButton("Start");
   private JButton stopButton = new JButton("Stop");
   private JButton clearButton = new JButton("Clear");
   private JButton chaserButton = new JButton("Chaser");
   private JButton runnerButton = new JButton("Runner");
   private JButton randomButton = new JButton("Random");
   private JLabel title;
   private JLabel prompt; 
   private boolean cleared;
   private boolean stopped;
   private boolean chaserClicked, runnerClicked, 
   randomClicked, stopClicked;
   private int critters;
   private ArrayList<Critter> crits;
   private CrittersSimulator critterSim;
   private Chaser chaserCrit;
   private Runner runnerCrit;
   private Random randomCrit;
   private boolean chaserExists, runnerExists, randomExists;


   /*
* Name: This is the begin method
* Purpose: This contains the beginning instructions for the program
* Parameters:None.
* Return:void.
*/  
   public void begin()
   {
      title = new JLabel("Please add two or more critters");
      prompt = new JLabel("Select which critter to place:");
      JPanel northPanel = new JPanel();
      JPanel southPanel = new JPanel();
      JPanel innerNorthPanel = new JPanel();
      JPanel innerSouthPanel = new JPanel();
      JPanel titlePanel = new JPanel();
      JPanel promptPanel = new JPanel();
 
      // Registering the buttons as action Listeners
      runnerButton.addActionListener(this);
      randomButton.addActionListener(this);
      chaserButton.addActionListener(this);
      startButton.addActionListener(this);
      stopButton.addActionListener(this);
      clearButton.addActionListener(this);
      
      //Layout for the panels
      innerNorthPanel.setLayout(new GridLayout(1, 3));
      innerSouthPanel.setLayout(new GridLayout(1, 3));
     
      innerNorthPanel.add(startButton);
      innerNorthPanel.add(stopButton);
      innerNorthPanel.add(clearButton);

      titlePanel.add(title, BorderLayout.CENTER);
      northPanel.setLayout(new GridLayout(1, 2));
      
      northPanel.add(titlePanel);
      northPanel.add(innerNorthPanel);
     
      promptPanel.add(prompt, BorderLayout.CENTER);
      innerSouthPanel.add(chaserButton);
      innerSouthPanel.add(runnerButton);
      innerSouthPanel.add(randomButton);
      
      southPanel.setLayout(new GridLayout(2, 1));
      southPanel.add(promptPanel);
      southPanel.add(innerSouthPanel); 
      //add the panels to the canvas
      this.add( northPanel, BorderLayout.NORTH);
      this.add( southPanel, BorderLayout.SOUTH);
      this.validate();    
      
      // Inititializing the array Lists 
      crits = new ArrayList<Critter>();
      critterSim = new CrittersSimulator(crits);
      status();
           
      
   }
/*
* Name: onMouseClick
* Purpose: This is a set of instructions that controls what happens when
* the mouse is clicked
* Parameters: Location point
* Return: void.
*/   
   public void onMouseClick(Location point)
   {
      //what happens if each of the buttons is placed
      if(chaserClicked == true)
      { 
         chaserCrit = new Chaser(point, canvas);
         randomClicked = false;  
         runnerClicked = false;
         chaserExists = true;
         critters++;
         crits.add(chaserCrit); 
         status(); 
      }
      // what happens if the runner was selected
      else if(runnerClicked == true)
      {
         runnerCrit = new Runner(point, canvas);
         chaserClicked = false;
         randomClicked = false;
         runnerExists = true;
         crits.add(runnerCrit);
         critters++;
         status();
      }
      //what happens if the random was selected
      else if(randomClicked == true)
      {
         randomCrit = new Random(point, canvas);
         chaserClicked = false;
         runnerClicked = false;
         randomExists = true;
         crits.add(randomCrit);
         critters++; 
         status();
      }
        
   }  
    // other methods neede to use the windowsController
   public void onMousePress()
   {}
   public void onMouseDrag(){}
/*
* Name: actionPerformed
* Purpose: This is a set of instructions that control what happens
* when an action is performed
* Parameters: evt
* Return: void.
*/ 
   public void actionPerformed( ActionEvent evt ) 
   {
      // if the start button is pressed
      if(evt.getSource() == startButton)
      {
         stopped = false; 
         status();//calling the status method    
         stopClicked = false;  
        
      }
      else if(evt.getSource() == stopButton)
      {
         stopped = true;
         stopClicked = true;//keep track of which state button was clicked
         status();

      }
      else if(evt.getSource() == clearButton)
      {
         cleared = true;   
         stopClicked = false;
         stopped = true;
         status();
      }
      else if(evt.getSource() == runnerButton)
      {
         //keeps track of which critter was clicked
         runnerClicked = true;
         chaserClicked = false;
         randomClicked = false;
         status();
         
      }
      else if(evt.getSource() == randomButton)
      {
         randomClicked = true;
         runnerClicked = false;
         chaserClicked = false;
         
         status();
      }
      else if(evt.getSource() == chaserButton)
      {
         
         chaserClicked = true;
         randomClicked = false;
         runnerClicked = false;
         status();
        
      }
   }
 /*
* Name: status
* Purpose: This is a set of instructions that controls what happens when
* on the state of the program whether it is started or stopped
* Parameters: None
* Return: void.
*/    
   public void status()
   {
      if(critters < 2)
      {
         stopped = true;
         //change the boolean in the simulator
         critterSim.setStop(true);
      }
      else if(stopped == true && stopClicked == false)
      {
         stopped = false;
         critterSim.setStop(false);
         title.setText("The simulation is running");
      }
      else if(stopped == true && stopClicked == true )
      { 
         //change the texts
         title.setText(" The simulation is stopped ");         
         critterSim.setStop(true); 
         
      }
      else if(stopped == false && critters >= 2)
      {
         title.setText(" Simulation is running "); 
         critterSim.setStop(false);
      }			
      else if(critters < 2)
      {
         title.setText(" Please add two or more critters "); 
      }
      if(runnerClicked == true)
      {
         //change the prompts
         prompt.setText(" Click on the canvas to place a Runner"); 
      }
      else if(chaserClicked == true)
      {
         prompt.setText(" Click on the canvas to place a Chaser");
      }
      else if(randomClicked == true)
      {
         prompt.setText(" Click on the canvas to place a Random");
      }
      // what happens when the clear button is pressed
      if(cleared == true)
      {     
            
         title.setText(" Please add two or more critters ");
         critterSim.setStop(true);
         //make sure that they are critters in the array list
         if (crits.size() > 0)
         {
            for(Critter a : crits)
            {
               a.kill();
                           
               critters = 0;
            }
            crits.clear(); 
         }
         //reset the booleand and counters
         if( critters == 0)
         {
            cleared = false;
            runnerExists = false;
            chaserExists = false;
            randomExists = false;
         }
      
      }
   
   }  
   
}
