package image_editor;

public class pixel {
	public int red;
	public int green;
	public int blue;
	
	public pixel(int r,int g,int b)
	{
		// check if pixels are 0 -255 range if need to
		this.red = r;
		this.green = g;
		this.blue = b;
		
	}
	
	public void gray()
	{
		this.red = (red + green + blue)/3;
		this.green = red;
		this.blue = red;
	}
	
	public void inv()
	{
		this.red = Math.abs(red - 255);
		this.green = Math.abs(green - 255);
		this.blue = Math.abs(blue - 255);
	}
	
	public void setAdd(pixel p)
	{
		this.red = (red + p.red);
		this.green = (green + p.green);
		this.blue = (blue + p.blue);
	}
	
	public void setAve(int num)
	{
		this.red = red/num;
		this.green = green/num;
		this.blue = blue/num;
	}
	
	public void setDiff(pixel p)
	{
		this.red = (red - p.red);
		this.green = (green - p.green);
		this.blue = (blue - p.blue);
	}
	
	public void setMax()
	{
		int diff = red;
		int max = Math.abs(red);
		if(max < Math.abs(blue))
		{
			max = Math.abs(blue);
			diff = blue;
		}
		if(max < Math.abs(green))
		{
			diff = green;
		}
		int v = diff + 128;
		if(v < 0)
		{
			v = 0;
		}
		if(v > 255)
		{
			v = 255;
		}
		this.red = v;
		this.green = v;
		this.blue = v;
		
	}
	

}
