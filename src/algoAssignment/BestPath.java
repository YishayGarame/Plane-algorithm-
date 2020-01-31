package algoAssignment;

import java.util.ArrayList;

public class BestPath {
	Node[][] mat;
	int high;
	int width;
	int teta;

	Node end;
	int minTurns;
	int secMinTurns;

	ArrayList<String> cheapestPath;
	ArrayList<String> optimalPath;

	ArrayList<String> cheapestPath2;
	ArrayList<String> optimalPath2;

	public static void main(String[] args) {
		Node[][] mat = new Node[4][4];

		mat[0][0] = new Node(1, 1);
		mat[0][1] = new Node(1, 1);
		
		mat[0][2] = new Node(1, 1);
		mat[0][3] = new Node(1, 1);
		
		mat[1][0] = new Node(1, 1);
		mat[1][1] = new Node(1, 1);
		mat[1][2] = new Node(1, 1);
		mat[1][3] = new Node(1, 1);
		
		mat[2][0] = new Node(1, 1);
		mat[2][1] = new Node(2, 2);
		mat[2][2] = new Node(3, 3);
		mat[2][3] = new Node(1, 1);
		
		
		mat[3][0] = new Node(1, 1);
		mat[3][1] = new Node(1, 1);
		mat[3][2] = new Node(1, 1);
		mat[3][3] = new Node(1, 1);

		BestPath bp = new BestPath(mat, 60);

				System.out.println("num of cheapest paths: " + bp.getNumOfCheapestPaths());
				System.out.println("the cheapets price is: " + bp.getCheapestPrice());
				System.out.println("the paths: " + bp.getAllCheapestPaths());
				System.out.println("num of optimal paths: " + bp.getNumOfOptimalPaths());
				System.out.println("the optimal paths: " + bp.getAllOptimalPaths());
				System.out.println("num of minimum turns: " + bp.printNumOfTurns());
				System.out.println();
				System.out.println("num of sec cheapest paths: " + bp.getNumOfCheapestPaths2());
				System.out.println("the sec cheapets price is: " + bp.getCheapestPrice2());
				System.out.println("the sec paths: " + bp.getAllCheapestPaths2());
				System.out.println("num of sec optimal paths: " + bp.getNumOfOptimalPaths2());
				System.out.println("the sec optimal paths: " + bp.getAllOptimalPaths2());
				System.out.println("num of sec minimum turns: " + bp.getNumOfTurns2());
		
//		System.out.println(bp.mat[3][3].getSecBestPath());
//		System.out.println(bp.mat[3][2].getSecBestPath());
//		System.out.println(bp.mat[3][1].getSecBestPath());
//		System.out.println(bp.mat[2][1].getSecBestPath());
//		System.out.println(bp.mat[2][1].getSecPrice());









	}

	// -------------------- question 1 --------------------

	public BestPath(Node[][] mat, int teta)

	{
		buildMat(mat);
		this.mat = mat;
		this.teta = teta;

		this.high = mat.length;
		this.width = mat[0].length;
		this.end = this.mat[high - 1][width - 1];

		this.minTurns = Integer.MAX_VALUE;
		this.secMinTurns = Integer.MAX_VALUE;

		this.cheapestPath = new ArrayList<String>();
		this.optimalPath = new ArrayList<String>();
		this.cheapestPath2 = new ArrayList<String>();
		this.optimalPath2 = new ArrayList<String>();
		calcCheapestPath();
		calcCheapestPath2();
	}

	public void buildMat(Node mat[][]) {
		int rows = mat.length; // i
		int col = mat[0].length; // j

		mat[0][0].setPrice(0);
		mat[0][0].setSecPrice(0);
		mat[0][0].setPath(1);
		mat[0][0].setSecPath(1);
		for (int i = 1; i < rows; i++) // col
		{
			mat[i][0].setPrice(mat[i - 1][0].price + mat[i - 1][0].gety());
			mat[i][0].setSecPrice(mat[i - 1][0].secPrice + mat[i - 1][0].gety());
			mat[i][0].setPath(1);
			mat[i][0].setSecPath(1);
			mat[i][0].setBestPath(1, mat[i - 1][0]);
			mat[i][0].setSecBestPath(1, mat[i - 1][0]);

		}
		for (int j = 1; j < col; j++) // row
		{
			mat[0][j].setPrice(mat[0][j - 1].price + mat[0][j - 1].getx());
			mat[0][j].setSecPrice(mat[0][j - 1].secPrice + mat[0][j - 1].getx());
			mat[0][j].setPath(1);
			mat[0][j].setSecPath(1);
			mat[0][j].setBestPath(0, mat[0][j - 1]);
			mat[0][j].setSecBestPath(0, mat[0][j - 1]);
		}

		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < col; j++) 
			{
				double a = mat[i][j - 1].price + mat[i][j - 1].getx(); // came from left ----> 0
				double b = mat[i - 1][j].price + mat[i - 1][j].gety();// came from down ------> 1

				if (a < b) // came from left
				{
					mat[i][j].setPrice(a);
					mat[i][j].setPath(mat[i][j - 1].getPath());
					mat[i][j].setBestPath(0, mat[i][j - 1]);

					// price a == secprice a
					if (mat[i][j - 1].secPrice == mat[i][j - 1].price ) 
					{
						// b price + y
						mat[i][j].secPrice = mat[i-1][j].price + mat[i-1][j].gety();
						mat[i][j].setSecPath( mat[i-1][j].getPath());
						mat[i][j].setSecBestPath(0, mat[i][j-1]);
					} 

					else  // price a != secprice a
					{
						// secprice a+x < b first price+y

						if (mat[i][j - 1].secPrice + mat[i][j - 1].getx() < mat[i - 1][j].price+ mat[i - 1][j].gety())
						{
							mat[i][j].setSecPrice(mat[i][j - 1].secPrice + mat[i][j - 1].getx());
							mat[i][j].setSecPath(mat[i][j - 1].getSecPath());
							mat[i][j].setSecBestPath(0, mat[i][j - 1]);
						}

						// secprice a +x> b first price+y
						else if(mat[i][j - 1].secPrice + mat[i][j - 1].getx() > mat[i - 1][j].price+ mat[i - 1][j].gety())
						{
							mat[i][j].setSecPrice(mat[i - 1][j].price + mat[i - 1][j].gety());
							mat[i][j].setSecBestPath(1, mat[i - 1][j]);
							mat[i][j].setSecPath(mat[i - 1][j].getSecPath());
						}
						else  // secprice a +x == b first price+y
						{
							mat[i][j].setSecPrice(mat[i - 1][j].price + mat[i - 1][j].gety());
							mat[i][j].setSecBestPath(0, mat[i][j-1]);
							mat[i][j].setSecBestPath(1, mat[i - 1][j]);
							mat[i][j].setSecPath(mat[i - 1][j].getSecPath()+mat[i][j-1].getSecPath());
						}

					}
				}


				else if (a > b) // came from down
				{
					mat[i][j].setPrice(b);
					mat[i][j].setPath(mat[i - 1][j].getPath());
					mat[i][j].setBestPath(1, mat[i - 1][j]);

					// price b == secprice b
					if (mat[i-1][j].secPrice == mat[i-1][j].price) 
					{
						// a price + x

						mat[i][j].secPrice = mat[i][j-1].price + mat[i][j-1].getx();
						mat[i][j].setSecPath( mat[i][j-1].getPath());
						mat[i][j].setSecBestPath(1, mat[i-1][j]);
					}

					else  // price b != secprice b
					{
						// secprice b+y < a first price+x
						if (mat[i-1][j].secPrice + mat[i-1][j].gety() < mat[i][j-1].price+ mat[i][j-1].getx())
						{
//							System.out.println("a is "+a+ " i is "+ i + " j is "+j);
							mat[i][j].setSecPrice(mat[i-1][j].secPrice + mat[i-1][j].gety());
							mat[i][j].setSecBestPath(1, mat[i-1][j]);
							mat[i][j].setSecPath(mat[i-1][j].getSecPath());
						}

						// secprice b +y> b first price+y
						else if(mat[i][j - 1].price + mat[i][j - 1].getx() < mat[i - 1][j].secPrice+ mat[i - 1][j].gety())
						{
							mat[i][j].setSecPrice(mat[i][j-1].price + mat[i][j-1].getx());
							mat[i][j].setSecBestPath(0, mat[i][j-1]);
							mat[i][j].setSecPath(mat[i][j-1].getSecPath());
						}
						else  // secprice a +x == b first price+y
						{
							mat[i][j].setSecPrice(mat[i - 1][j].price + mat[i - 1][j].gety());
							mat[i][j].setSecBestPath(0, mat[i][j-1]);
							mat[i][j].setSecBestPath(1, mat[i - 1][j]);
							mat[i][j].setSecPath(mat[i - 1][j].getSecPath()+mat[i][j-1].getSecPath());
						}
					}
				}


			else // a=b equals
			{
				mat[i][j].setPrice(a);
				mat[i][j].setPath(mat[i][j - 1].getPath() + mat[i - 1][j].getPath());

				mat[i][j].setBestPath(0, mat[i][j - 1]);
				mat[i][j].setBestPath(1, mat[i - 1][j]);

				// a secprice < b secprice
				if (mat[i][j - 1].secPrice + mat[i][j - 1].getx() < mat[i - 1][j].secPrice + mat[i - 1][j].gety()) 
				{
					mat[i][j].setSecPrice(mat[i][j - 1].secPrice + mat[i][j - 1].getx());
					mat[i][j].setSecPath(mat[i][j - 1].getSecPath());
					mat[i][j].setSecBestPath(0, mat[i][j - 1]);
				}

				// a secprice >b secprice
				else if (mat[i][j - 1].secPrice + mat[i][j - 1].getx() > mat[i - 1][j].secPrice	+ mat[i - 1][j].gety())
				{
					mat[i][j].setSecPrice(mat[i - 1][j].secPrice + mat[i - 1][j].gety());
					mat[i][j].setSecPath(mat[i - 1][j].getSecPath());
					mat[i][j].setSecBestPath(1, mat[i - 1][j]);

				}

				// a secprice==b secprice
				else 
				{
					mat[i][j].setSecPrice(mat[i][j - 1].secPrice + mat[i][j - 1].getx());
					mat[i][j].setSecPath(mat[i - 1][j].getSecPath()+mat[i][j-1].getSecPath());
					mat[i][j].setSecBestPath(0, mat[i][j-1]);
					mat[i][j].setSecBestPath(1, mat[i - 1][j]);


					

				}
			}

		}
	}

}

	
	
	
	
	
	
	
public int getNumOfCheapestPaths() {
	return mat[high - 1][width - 1].getPath();
}

public int getNumOfOptimalPaths() {
	return optimalPath.size();
}

public double getCheapestPrice() {
	return mat[high - 1][width - 1].price;

}

public int printNumOfTurns() {
	return minTurns;
}

public ArrayList<String> getAllCheapestPaths() {
	return this.cheapestPath;
}

public ArrayList<String> getAllOptimalPaths() {
	return this.optimalPath;
}

// --------------------- question 2 -----------------------

public int getNumOfCheapestPaths2() {

	return mat[high - 1][width - 1].secPath;
}

public double getCheapestPrice2() {
	return mat[high - 1][width - 1].secPrice;
}

public int getNumOfOptimalPaths2() {
	return this.optimalPath2.size();
}

public int getNumOfTurns2() {
	return secMinTurns;
}

public ArrayList<String> getAllCheapestPaths2() {
	return this.cheapestPath2;
}

public ArrayList<String> getAllOptimalPaths2() {
	return this.optimalPath2;
}

// -------------------- myfunctions --------------------

public void calcCheapestPath() {
	char s[] = new char[high + width - 2];
	calcCheapestPath(end, s, s.length - 1);
}


public void calcCheapestPath(Node n, char[] c, int deep)
{
	if (cheapestPath.size() > teta)
		return;
	if (n.getBestPath().isEmpty()) 
	{
		String road = new String(c); // the path
		cheapestPath.add(road); // add the String path to the paths array

		int tmpturns=checkDiffChar(road);

		if(tmpturns==minTurns) 
		{
			optimalPath.add(road);
		}
		if(tmpturns<minTurns) 
		{
			optimalPath.clear();
			minTurns=tmpturns;
			optimalPath.add(road);
		}

	}	

	if (n.getBestPath().get(1) != null) 
	{
		c[deep] = '1';
		calcCheapestPath(n.getBestPath().get(1), c, deep - 1);
	}

	if (n.getBestPath().get(0) != null) 
	{
		c[deep] = '0';
		calcCheapestPath(n.getBestPath().get(0), c, deep - 1);
	}


}

public int checkDiffChar(String s)
{
	int count=0;
	for (int i = 1; i < high+width-2; i++)
	{
		if(s.charAt(i-1)!=s.charAt(i)) {
			count++;
		}
	}
	return count;
}


public void calcCheapestPath2() {
	char s[] = new char[high + width - 2];
	calcCheapestPath2(end, s, s.length - 1);
}


public void calcCheapestPath2(Node n, char[] c, int deep)
{
	if (cheapestPath2.size() > teta)
		return;
	if (n.getSecBestPath().isEmpty()) 
	{
		String road = new String(c); // the path
		cheapestPath2.add(road); // add the String path to the paths array

		int tmpturns=checkDiffChar(road);

		if(tmpturns==secMinTurns) 
		{
			optimalPath2.add(road);
		}
		if(tmpturns<secMinTurns) 
		{
			optimalPath2.clear();
			secMinTurns=tmpturns;
			optimalPath2.add(road);
		}

	}
	if (n.getSecBestPath().get(0) != null) 
	{
		c[deep] = '0';
		calcCheapestPath2(n.getSecBestPath().get(0), c, deep - 1);
	}

	if (n.getSecBestPath().get(1) != null) {
		c[deep] = '1';
		calcCheapestPath2(n.getSecBestPath().get(1), c, deep - 1);
	}
}


}
