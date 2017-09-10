import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Smelling_the_Cosmos {
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);    	
    	int n = input.nextInt() + 1;
    	
    	int[] A = new int[n];
    	int[] B = new int[n];
    	
    	for (int i = 0; i < n; i++) {
    		A[i] = input.nextInt();
    	}
    	
    	for (int i = 0; i < n; i++) {
    		B[i] = input.nextInt();
    	}
        
    	int[] result = Solution.karatsuba(A, B, n);
        
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
    
	static int[] naiveMult(int[] a, int[] b, int n) {
		int[] c = new int[2*n -1];
		
		for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                c[i+j] = c[i+j] + a[i] * b[j];
            }
        }
		return c;
	}
	
	static int[] karatsuba(int[] a, int[] b, int n)
	{
		int[] a_high = new int[n/2];
		int[] a_low = new int[n/2];
		int[] b_high = new int[n/2];        
		int[] b_low = new int[n/2];
		
		int[] c = new int[2*n -1];
		
		if(n <= 8) {
			return naiveMult(a, b, n);
		}
		
		for(int i = 0; i < n/2; i++) {
			a_low[i] = a[i];
			b_low[i] = b[i];
		}
        
		for(int i = n/2; i < n; i++) {
			a_high[i-n/2] = a[i];
			b_high[i-n/2] = b[i];
		}
		
		int[] t1 = new int[n/2];
		int[] t2 = new int[n/2];
		
		for(int i = 0; i < n/2; i++) {
			t1[i] = a_low[i] + a_high[i];
			t2[i] = b_low[i] + b_high[i];
		}
			
		int[] c_mid = karatsuba(t1, t2, n/2);
		int[] c_low = karatsuba(a_low, b_low, n/2);
		int[] c_high = karatsuba(a_high, b_high, n/2);
		
	
		for(int i = 0; i < n-1; i++) {
			c[i] = c_low[i];
		}
        
		c[n-1] = 0;
        
		for(int i = 0; i < n-1; i++) {
			c[n+i] = c_high[i];
		}
        
		for(int i = 0; i < n-1; i++) {
			c[n/2 + i] += c_mid[i] - (c_low[i] + c_high[i]);
		}
        
		return c;
	}

    
}
