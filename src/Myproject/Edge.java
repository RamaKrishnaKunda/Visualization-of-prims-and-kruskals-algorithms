package Myproject;
import java.awt.Color;
import java.awt.Graphics;
public class Edge{
	public Node node1,node2;
	public int weight;
	int wtX,wtY;
        Color c;
	public Edge(Node node1,Node node2,int weight)
	{
		this.node1=node1;
		this.node2=node2;
		this.weight=weight;
		calWeightCoordinates();
	}
	void calWeightCoordinates()
	{ int m=2,n=3;
		wtX=(int)(((m*node1.centerX)+(node2.centerX*n)))/5;
		wtY=(int)(((m*node1.centerY)+(node2.centerY*n)))/5;
	}
        void drawWeight(Graphics g,Color c1)
        {   g.setColor(c1);
            g.drawString(String.valueOf(weight),wtX,wtY);
        }
        void setEdgeColor(Color c)
        {
            this.node1.setNodeColor(c);
            this.node2.setNodeColor(c);
        }
        
}
