import java.util.*;
import java.io.*;

public class Banker
{
	int tot[],alloc[][],max[][],av[],need[][];
	int p,r;
	boolean b[];
		
	void input()
	{
		Scanner cin =  new Scanner(System.in);
		
		int i,j;
		
		System.out.println("Enter Total Processes : ");
		p = cin.nextInt();
		System.out.println("Enter Total Resources : ");
		r = cin.nextInt();
		
		alloc = new int[p][r];
		max = new int[p][r];
		need = new int[p][r];
		
		av = new int[r];
		tot = new int[r];
		
		for(i=0;i<p;i++)
		{
			for(j=0;j<r;j++)
			{
				System.out.println("Enter Allocated Resource R"+(j+1)+" for Process P"+(i+1)+" : ");
				alloc[i][j] = cin.nextInt();
			}
		}
		
		for(i=0;i<p;i++)
		{
			for(j=0;j<r;j++)
			{
				System.out.println("Enter Maximum Resource R"+(j+1)+" for Process P"+(i+1)+" : ");
				max[i][j] = cin.nextInt();
			}
		}
		
		for(i=0;i<r;i++)
		{
			System.out.println("Enter Total Number Of R"+(i+1)+" Resources : ");
			tot[i] = cin.nextInt();			
		}
		
		process();
	}
	
	
	void process()
	{
		int i,j;
		
		for(i=0;i<p;i++)
		{
			for(j=0;j<r;j++)
			{
				need[i][j] = max[i][j]-alloc[i][j];
			}
		}
		
		int sum[] = new int[r];
				
		for(i=0;i<r;i++)
		{
			sum[i] = 0;
			
			for(j=0;j<p;j++)
			{
				sum[i] += alloc[j][i];
			}
		}
		
		for(i=0;i<r;i++)
		{
			av[i] = tot[i] - sum[i];
		}
		
		b = new boolean[p];
		
		int cnt1=0,cnt2=1;
		
		int seq[] = new int[p];
		
		int k=0;
		
		while(checkb()==false && cnt2!=cnt1)
		{
			cnt2 = cnt1;
			for(i=0;i<p;i++)
			{
				if(checka(i) && b[i]==false)
				{
					for(j=0;j<r;j++)
					{
						av[j] += alloc[i][j];
					}
					seq[k] = i;
					k++;
					cnt1++;
					b[i] = true;
				}
			}
		}
		
		System.out.println("\nSAFE SEQUENCE IS : ");
		for(i=0;i<seq.length;i++)
		{
			try
			{
			 System.out.print((seq[i]+1)+"->");
			}
			catch(Exception e)
			{
				break;
			}
		}
		System.out.println();
	}
	

	boolean checka(int i)
	{
		int j;
		
		for(j=0;j<r;j++)
		{
			if(need[i][j] > av[j])
			{
				return false;
			}
		}
		
		return true;
	}
	
	boolean checkb()
	{
		int i;
		
		for(i=0;i<p;i++)
		{
			if(b[i]==false)
			{
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String args[])
	{
		Banker b = new Banker();
		b.input();
	}
}

