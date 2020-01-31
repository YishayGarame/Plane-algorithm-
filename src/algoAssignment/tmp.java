package algoAssignment;

public class tmp {

}
package algoAssignment;

import java.util.Hashtable;
import java.util.LinkedHashMap;

public class Node
{
	double x,y;
	double price,secPrice;
	int path;
	LinkedHashMap<Integer, Node> bestPath;
	LinkedHashMap<Integer, Node> secondPath;



	public Node(double x, double y)
	{
		this.x=x;
		this.y=y;
		price=price;
		path=1;
		secPrice=secPrice;
		this.bestPath=new LinkedHashMap<>();
		this.secondPath=new LinkedHashMap<>();

	}
	
	public double getx() {
		return this.x;
	}
	
	public double gety() {
		return this.y;
	}
	
	public double getPrice()
	{
		return this.price;
	}

	public void setPrice(double k)

	{
		this.price=k;
	}
	
	public double getSecPrice()
	{
		return this.secPrice;
	}

	public void setSecPrice(double k) {
		this.secPrice=k;
	}
	public int getPath()
	{
		return this.path;
	}
	
	public void setPath(int t)
	{
		this.path=t;
	}
	public void setBestPath(int i, Node n)
	{
		this.bestPath.put(i, n);
	}

	public LinkedHashMap<Integer, Node> getBestPath()
	{
		return this.bestPath;
	}
	
	public void setSecBestPath(int i, Node n)
	{
		this.secondPath.put(i, n);
	}

	public LinkedHashMap<Integer, Node> getSecBestPath()
	{
		return this.secondPath;
	}

	public void

}
