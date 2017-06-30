import pixel
import java.io.File;
import java.util.Scanner;

package image_editor;

public class image {
	private ArrayList<ArrayList<pixel>> pic;
	private int width;
	private int height;
	private final int MAX_CV = 255;
	
	public image(String file)
	{
		inputImage(file);
	}
	
	public void invert()
	{
		
	}
	
	public void grayscale()
	{
		
	}
	
	public void emboss()
	{
		
	}
	
	public void motionblur(int num)
	{
		
	}
	
	private void inputImage(String fileName)
	{
		// parse through file and create array pic
		 File file = new File(fileName);
		 Scanner s = new Scanner(input).useDelimiter("#(\\S*\\s*)*\n|\\s");
		 s.next;
		 this.width = s.nextInt();
		 this.height = s.nextInt();
		 s.next();
		 for(int i = 0; i < height; i++)
		 {
			 ArrayList<pixel> row;
			for(int i = 0; i < width; i++)
			{
				pixel p = new pixel(s.nextInt(),s.nextInt(),s.nextInt());
				row.add(p);
			}
			this.pic.add(row);
		 }
	}
}
