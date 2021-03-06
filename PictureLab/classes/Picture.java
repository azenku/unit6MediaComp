import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
   public void mirrorVerticalRightToLeft()

  {

   Pixel[][] pixels = this.getPixels2D();

   Pixel leftPixel = null;

   Pixel rightPixel = null;
  

   int width = pixels[0].length;

   for (int row = 0; row < pixels.length; row++)

   {

     for (int col = 0; col < width / 2; col++)

     {

       leftPixel = pixels[row][col];

       rightPixel = pixels[row][width - 1 - col];
       
       leftPixel.setColor(rightPixel.getColor());

     }

   } 

 }
  
 public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[0].length; col++)
      {
        if (col < pixels.length)
        {
            leftPixel = pixels[row][col];
            rightPixel = pixels[col][row];
            leftPixel.setColor(rightPixel.getColor());
        
        }
        
      }
    } 
  }

   public void mirrorTopToBot()

  {

    Pixel[][] pixels = this.getPixels2D();

   Pixel leftPixel = null;
   Pixel rightPixel = null;
   int width = pixels.length;

   for (int row = 0; row < pixels.length; row++)

   {

     for (int col = 0; col < pixels[0].length; col++)

     {

       leftPixel = pixels[row][col];

       rightPixel = pixels[width-1-row][col];
       
       leftPixel.setColor(rightPixel.getColor());

     }

   } 
 }
  
 
 public void mirrorArms()

  {
   //Picture man = new Picture("snowman.jpg");
   Pixel[][] pixels = this.getPixels2D();
   int mirrorPoint = 193;
   Pixel topPixel = null;
   Pixel bottomPixel = null;
   int count = 0;
   int width = pixels.length;
   //left arm
   for (int row = 165; row < mirrorPoint; row++)
   {

     for (int col = 100; col < 170; col++)
     {
         topPixel=pixels[row][col]; 
         bottomPixel=pixels[mirrorPoint-row+mirrorPoint][col];
         bottomPixel.setColor(topPixel.getColor());
      
       
     }

   } 

    for (int row = 170; row < mirrorPoint; row++)
   {

     for (int col = 235; col <296 ; col++)
     {
         topPixel=pixels[row][col]; 
         bottomPixel=pixels[mirrorPoint-row+mirrorPoint][col];
         bottomPixel.setColor(topPixel.getColor());
      
       
     }

   } 
 }
 
 
 
 public void mirrorGull()

  {
   //Picture man = new Picture("seagull.jpg");
   Pixel[][] pixels = this.getPixels2D();
   int mirrorPoint = 193;
   Pixel topPixel = null;
   Pixel bottomPixel = null;
   int count = 0;
   int width = pixels.length;
   //left arm
   for (int row = 165; row < mirrorPoint; row++)
   {

     for (int col = 100; col < 170; col++)
     {
         topPixel=pixels[row][col]; 
         bottomPixel=pixels[mirrorPoint-row+mirrorPoint][col];
         bottomPixel.setColor(topPixel.getColor());
      
       
     }

   }
}
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
    beach.mirrorVerticalRightToLeft();
  }
  
  public Picture keepOnlyBlue(Picture picture)

 {

     Pixel[][] pixels = picture.getPixels2D();

     for (Pixel[] rowArray : pixels)

     {

         for (Pixel pixelObj : rowArray)

         {

             pixelObj.setGreen(0);
             pixelObj.setRed(0);
            }

        }
        return picture;
 }
  
  public Picture negate(Picture picture)

 {

     Pixel[][] pixels = picture.getPixels2D();

     for (Pixel[] rowArray : pixels)

     {

         for (Pixel pixelObj : rowArray)

         {
             pixelObj.setBlue(255-pixelObj.getBlue());
             pixelObj.setGreen(255-pixelObj.getGreen());
             pixelObj.setRed(255-pixelObj.getRed());
            }

        }
     return picture;
 }
 
  public void grayscale()

 {

     Pixel[][] pixels = this.getPixels2D();

     for (Pixel[] rowArray : pixels)

     {

         for (Pixel pixelObj : rowArray)

         {
             int mix = pixelObj.getBlue()+pixelObj.getGreen()+pixelObj.getRed();
             pixelObj.setBlue(mix/3);
             pixelObj.setGreen(mix/3);
             pixelObj.setRed(mix/3);
            }

        }

 }
 
 void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
         int startDestRow, int startDestCol )
 {
     
     
     
     Pixel[][] pixels = sourcePicture.getPixels2D();
     Pixel[][] endpix = this.getPixels2D();
     Pixel leftPixel = null;
     Pixel rightPixel = null;
     
     int c1=0;
     int c2=0;
     for (int row = startSourceRow;row < endSourceRow;row++)
     {
         for(int col = startSourceCol;col< endSourceCol;col++)
         {
             leftPixel=pixels[row][col];
             rightPixel=endpix[startDestRow + c1][startDestCol + c2];
             rightPixel.setColor(leftPixel.getColor());
             c2++;
            }
            c1++;
            c2=0;
        }
     
     
    }
    
public Picture scaleByHalf(Picture picture)
{
    //Picture picture = new Picture(1152, 1536);
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] picturePixels = picture.getPixels2D();
    Pixel origPixel = null;
    int c1 = 0;
    int c2 = 0;

    for (int i = 0; i < pixels.length; i += 2)
    {
        for (int j = 0; j < pixels[i].length; j += 2)
        {
            origPixel = pixels[i][j];
            picturePixels[c1][c2].setColor(origPixel.getColor());
            c2++;
        }
        c1++;
        c2 = 0;
    }

    return picture;
}    
} // this } is the end of class Picture, put all new methods before this
