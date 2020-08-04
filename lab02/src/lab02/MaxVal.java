package lab02;

public class MaxVal {
	
	public int calculate(int[] array) {
		int max = 0;
		for(int s= array.length-1; s>=3; s--)
			for(int r=s-1; r>=3; r--)
				for(int q=r-1; q>=2; q--)
					for(int p=q-1; p>=1; p--) 
						if (array[s]-array[r]+array[q]-array[p] > max)
							max = array[s]-array[r]+array[q]-array[p];
		return max;
	}

	public static void main(String[] args) {
		int[] array = {22, 3, 9, 10, 1, 30, 40, 33};
		MaxVal test = new MaxVal();
		System.out.println(test.calculate(array));

	}

}
