

public class imageEditor {
	public static void main(String[] args)
	{
		image pic = new image(args[0]);
	
		
		if(args.length < 3)
		{
			System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
		}
		else if(args[2].equals("invert"))
		{
			pic.invert();
			pic.makeFile(args[1]);
		}
		else if(args[2].equals("grayscale"))
		{
			pic.grayscale();
			pic.makeFile(args[1]);
		}
		else if(args[2].equals("emboss"))
		{
			pic.emboss();
			pic.makeFile(args[1]);
		}
		else if(args[2].equals("motionblur"))
		{
			
			try {
					int n = Integer.parseInt(args[3]);
					pic.motionblur(n);
					pic.makeFile(args[1]);
		    	}
			catch (NumberFormatException e) 
			{
				System.out.println("USAGE: java ImageEditor in-file out-file motion-blur length : length must be an integer");
			}
			catch (ArrayIndexOutOfBoundsException d)
			{
				System.out.println("USAGE: java ImageEditor in-file out-file motion-blur length");
			}
		}
		else 
		{
			System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
		}
		
	}

}
