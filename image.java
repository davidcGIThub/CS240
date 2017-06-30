
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;



public class image {
	private pixel[][] pic;
	private int width;
	private int height;
	private final int MAX_CV = 255;
	
	public image(String file)
	{
		inputImage(file);
	}
	
	public void invert()
	{
		for(int i = 0; i < height; i++)
		 {
			for(int j = 0; j < width; j++)
			{
				pic[i][j].inv();
			}
		 }
	}
	
	public void grayscale()
	{

		for(int i = 0; i < height; i++)
		 {
			for(int j = 0; j < width; j++)
			{
				pic[i][j].gray();
			}
		 }
	}
	
	public void emboss()
	{
		//make a copy of the array
		pixel[][] copy = new pixel[height][width];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				pixel p = new pixel(pic[i][j].red,pic[i][j].green,pic[i][j].blue);
				copy[i][j] = p;
			}
		}

		//emboss
		for(int i = 0; i < height; i++)
		 {
			for(int j = 0; j < width; j++)
			{
				if(i == 0 | j == 0)
				{
					pic[i][j].red = 128;
					pic[i][j].green = 128;
					pic[i][j].blue = 128;
				}
				else
				{
					pic[i][j].setDiff(copy[(i-1)][(j-1)]);
					pic[i][j].setMax();
				}
				
			}
		 }
		
	}
	
	public void motionblur(int num)
	{
		//make a copy of the array
		pixel[][] copy = new pixel[height][width];
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				pixel p = new pixel(pic[i][j].red,pic[i][j].green,pic[i][j].blue);
				copy[i][j] = p;
			}
		}

		for(int i = 0; i < height; i++)
		 {
			for(int j = 0; j < width; j++)
			{
				for(int k = 1; k < num; k++)
				{
					if( (j+k) >= width)
					{
						k = num;
					}
					else
					{
						pic[i][j].setAdd(copy[i][j+k]);
					}
				}
				pic[i][j].setAve(num);
			}
		 }

		
		
	}
	

	
private void inputImage(String fileName)
{
	try
	{
		File file = new File(fileName);
		String[] size = new String[4];
		int k = 0;

		Scanner s = new Scanner(file);
		while(s.hasNext() & k < 4)
		{
			String token = s.next();
			if(token.equals("#"))
			{
				s.nextLine();
			}
			else
			{
				size[k] = token;
				k++;
			}
		}
		this.width = Integer.parseInt(size[1]);
		this.height = Integer.parseInt(size[2]);

		pic = new pixel[height][width];
		for(int i = 0; i < height; i++)
		 {
			for(int j = 0; j < width; j++)
			{
 				int[] rgb = new int[3];
				for(int t = 0; t < 3; )
				{
					String token = s.next();
					if(token.equals("#"))
					{
						s.nextLine();
					}
					else
					{
						rgb[t] = Integer.parseInt(token);
						t++;
					}
				}
				pixel p = new pixel(rgb[0],rgb[1],rgb[2]);
				pic[i][j] = p;
				
			}
		 }
	}
	catch (FileNotFoundException e) 
	{
	       	 e.printStackTrace();
	}
					
}
	
	public void makeFile(String fileName)
	{
		try
		{
		    PrintWriter newPic = new PrintWriter(fileName);
		    newPic.println("P3");
		    newPic.println(width);
		    newPic.println(height);
		    newPic.println(MAX_CV);
		    for(int i = 0; i < height; i++)
			 {
				for(int j = 0; j < width; j++)
				{
					newPic.println(pic[i][j].red);
					newPic.println(pic[i][j].green);
					newPic.println(pic[i][j].blue);
				}
			 }
		    newPic.close();
		}
		catch (IOException e) 
		{
		   System.out.println("Error");
		}
	}
	
	
}
