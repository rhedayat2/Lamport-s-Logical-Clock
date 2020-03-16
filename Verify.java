import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Verify
{
	static int x[] = { 0,0,0,0,0,0,0,0,0 }, p = 0;
	static int output[][] = new int[15][25];
	static String str[][];
	static int ss, sr = 0, f = 0, l;
	public static void main(String[] args) throws IOException {
		
		PrintStream console = System.out;
		File file = new File("out2.txt");
		FileOutputStream f = new FileOutputStream(file);
		PrintStream ps = new PrintStream(f);
		FileInputStream fstream = new FileInputStream("out1.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		int i, j;
		StringTokenizer st;
		while ((strLine = br.readLine()) != null)
		{
			st = new StringTokenizer(strLine);
			if (Arrays.asList("P1", "P2", "P3", ":").contains(st.nextToken()))
				st.nextToken();
			while (st.hasMoreTokens())
			{
				output[p][x[p]] = Integer.parseInt(st.nextToken());
				x[p]++;
			}
			p++;
		}
		System.out.println();
		System.out.println("Array from text file");
		for (i = 0; i < p; i++)
		{
			for (j = 0; j < x[i]; j++)
				System.out.print(output[i][j] + " ");
			System.out.println();
		}
		br.close();
		assignisr(p);
		
		System.setOut(ps);
		for (i = 0; i < p; i++)
		{
			for (j = 0; j < x[i]; j++)
				System.out.print(output[i][j] + " ");
			System.out.println();
		}
		
		System.out.println();
		for (i = 0; i < p; i++)
		{
			for (j = 0; j < x[i]; j++)
			{
				System.out.print(str[i][j] + " ");
			}
			System.out.println();
		}
		
		

	}
	public static void assignisr(int p)
	{
		int i, j;
		str = new String[5][25];


		for (i = 0; i < p; i++)
		{
			for (j = 0; j < (x[j]); j++)
			{
				l = output[i][j];
				f = searchl(l);
				if (f == 1)
					break;
			}
			if (f == 1)
				break;
		}
		
		for (i = 0; i < p; i++)
		{
			for (j = 0; j < x[i]; j++)
			{
				if (j == 0) 		
				{
					if (output[i][j] == 1)
					{
						continue;
					}
					else
					{
						sr++;
					}
				}
				else if (output[i][j] != output[i][j - 1] + 1)    
				{
					sr++;
				}
			}
		}
		ss = sr + 1;

		for (i = 0; i < p; i++)
		{
			for (j = 0; j < x[i]; j++)
			{
				if (j == 0) 		
				{
					if (output[i][j] == 1)
					{
						str[i][j] = "x";
					}
					else
					{
						search(i, (output[i][j] - 1));
						str[i][j] = "r" + (ss);
					}
				}
				else if (output[i][j] != output[i][j - 1] + 1)    
				{
					search(i, (output[i][j] - 1));
					str[i][j] = "r" + (ss);
				}
				else if (output[i][j] == output[i][j - 1] + 1 && str[i][j] == null)
				{
					str[i][j] = "x";
				}
			}
		}
		if (f == 1)
			System.out.println("INCORRECT");
		else
		{
			System.out.println();
			System.out.println("Printing Output");
			for (i = 0; i < p; i++)
			{
				for (j = 0; j < x[i]; j++)
				{
					System.out.print(str[i][j] + " ");
				}
				System.out.println();
			}

		}
	}




	public static int searchl(int a)
	{
		int i, j;
		for (i = 0; i < p; i++)
		{
			for (j = 0; j < x[i]; j++)
			{
				if (a == 1)
					return 0;
				else
				{
					if (output[i][j] == a - 1)
						return 0;
				}
			}
		}
		return 1;
	}


	public static void search(int pr, int lc)
	{
		int i, j;
		for (i = 0; i < p; i++)
		{
			if (i != pr)
			{
				for (j = 0; j < x[i]; j++)
				{
					if (output[i][j] == lc)
					{
						ss--;
						str[i][j] = "s" + ss;
					}
				}
			}
		}
	}
}