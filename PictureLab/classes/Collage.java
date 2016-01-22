

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import javax.swing.border.*;
/**
 * Write a description of class Collage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collage
{
    /** description of instance variable x (add comment for each instance variable) */
    private int x;

    /**
     * Default constructor for objects of class Collage
     */
    
    public static void main(String args[])
    {
        Picture base = new Picture(1720,2738);
        Picture pict = new Picture("shark.jpg");
        Picture pict1 = new Picture("shark.jpg");
        Picture pict2 = new Picture("shark.jpg");
        Picture pict3 = new Picture("shark.jpg");
        //860,1369
        //pict.mirrorVerticalRightToLeft();
        for(int i =1; i<5;i++)
        {
            //pict.scaleByHalf(pict);
            
            //pict = pict.negate(pict);
            base.cropAndCopy(pict,0,860,0,1369,0,1369);
            pict.scaleByHalf(pict);
        }  
            
            pict1.mirrorTopToBot();
            pict1.mirrorVerticalRightToLeft();
            base.cropAndCopy(base.negate(pict1),0,860,0,1369,860,1369);
            
            //for( int i = 0; i<4;i++)
            //{
            //pict2.mirrorDiagonal();
            //pict2.mirrorTopToBot();
            pict2.mirrorVerticalRightToLeft(); 
        //}
            
            base.cropAndCopy(base.keepOnlyBlue(pict2),0,860,0,1369,0,0);
            //if(i
        //}
        //base.cropAndCopy(pict,0,860,0,1369,0,0);
        
        base.cropAndCopy(pict3,0,860,0,1369,860,0);
        
        
        //510,575
        base.explore();
        
        base.write("MyCollage.jpg");
        
    }

    

}
