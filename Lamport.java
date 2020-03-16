import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class Lamport {


	static int x[] = new int[25];
	static int y[][] = new int[5][25];
	static int p, loc;
	static String z[][] = new String[5][25];

	public static void main(String args[]) throws FileNotFoundException
	{
		int i, j, s, temp;
		Scanner sc = new Scanner(System.in);
		PrintStream console = System.out;
		File file = new File("out1.txt");
		FileOutputStream f = new FileOutputStream(file);
		PrintStream ps = new PrintStream(f);
		System.out.println("Enter the number of process (N):");
		p = sc.nextInt();
		System.out.println("Enter the number of events per process (N separate numbers):");
		for (i = 1; i <= p; i++)
		{
			x[i] = sc.nextInt();
		}
		System.out.println("Enter internal events as characters other than 's' and 'r':");
		for (i = 1; i <= p; i++)
		{
			System.out.println("For process:" + i);
			for (j = 1; j <= x[i]; j++)
			{
				System.out.println("For event:" + j);
				z[i][j] = sc.next();
				y[i][j] = -2; 
			}
		}
		
		System.out.println("Entered Input is:");
		for (i = 1; i <= p; i++)
		{
			System.out.print("P" + i + " : ");
			for (j = 1; j <= x[i]; j++)
			{
				System.out.print(z[i][j] + " ");
			}
			System.out.println();
		}
		
		for (i = 1; i <= p; i++)
		{
			for (j = 1; j <= x[i]; j++)
			{
				if ((j == 1) && (z[i][j].charAt(0) != 'r'))
					y[i][j] = 1;
				else if (z[i][j].charAt(0) != 'r')
				{
					y[i][j] = y[i][j - 1] + 1;
				}
				else
				{
					s = Character.getNumericValue(z[i][j].charAt(1));
					temp = findsend(s);
					if (temp == -5)
						System.out.println("Error in temp value -5??");
					if (temp < y[i][j - 1])
						y[i][j] = y[i][j - 1] + 1;
					else
						y[i][j] = temp + 1;
				}
			}
		}
		
		System.setOut(ps);
		for (i = 1; i <= p; i++)
		{
			System.out.print("P" + i + " : ");
			for (j = 1; j <= x[i]; j++)
			{
				System.out.print(y[i][j] + " ");
			}
			System.out.println();
		}
		System.setOut(console);
		System.out.println();
		
		System.out.println("Logical clock values:");
		for (i = 1; i <= p; i++)
		{
			System.out.print("P" + i + " : ");
			for (j = 1; j <= x[i]; j++)
			{
				System.out.print(y[i][j] + " ");
			}
			System.out.println();
		}
		sc.close();
	}
	
	static int findsend(int s)
	{
		int i, j, se = -5;	
		for (i = 1; i <= p; i++)
		{
			for (j = 1; j <= x[i]; j++)
			{
				if (z[i][j].charAt(0) == 's'&& Character.getNumericValue(z[i][j].charAt(1)) == s)
				{
					if (y[i][j] != -2)
						return y[i][j];		
					else
					{
						loc = j;
						se = logclock(i);
					}
				}
			}
		}
		return se;
	}
	static int logclock(int pr)
	{
		int j, s, temp, se = -1;
		for (j = 1; j <= x[pr]; j++)
		{
			if ((j == 1) && (z[pr][j].charAt(0) != 'r'))
			{

				y[pr][j] = 1;
			}
			else if (z[pr][j].charAt(0) != 'r')
			{
				y[pr][j] = y[pr][j - 1] + 1;
			}
			else
			{
				s = Character.getNumericValue(z[pr][j].charAt(1));
				temp = findsend(s);
				if (temp < y[pr][j - 1])
					y[pr][j] = y[pr][j - 1] + 1;
				else
					y[pr][j] = temp + 1;
			}
			if (j == loc && y[pr][j] != -2)
			{
				return y[pr][j];
			}
		}
		return se;
	}
}