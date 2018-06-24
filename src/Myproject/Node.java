package Myproject;
import java.awt.*;
import java.util.*;
public class Node{
	String nodename;
        Color c;
	int centerX,centerY;
	int X,Y;
	public static int n;
	int no;
	static double theta;
	static final  int wid=40,hei=40;
         int pcenterX=350,pcenterY=360,radius=230;
	static{
		theta=-90;
	}
	public Node(String nodename)
	{	
		this.nodename=nodename;
                c=Color.white;
                no=n;
		calcCoordinates();
	}
	void calcCoordinates()
	{   //no=n;
		X=pcenterX+(int)(radius*Math.cos(Math.toRadians(theta)));
		Y=pcenterY+(int)(radius*Math.sin(Math.toRadians(theta)));
		theta=theta+(360/no);
		calCenter();
	}
	public String getNodeName()
	{
		return nodename;		
	}
	void  calCenter()
	{	
                this.centerX=X+(wid/2);
		this.centerY=Y+(hei/2);
	}
        void setNodeColor(Color c)
        {
            this.c=c;
        }
        void drawNode(Graphics g,Color c)
        {      //calcCoordinates();
               g.setColor(c);
               g.fillRect(X,Y,wid,hei);
               
               this.drawChar(g,this.getNodeName());
        }
        void drawLastNodes(Graphics g)
        {
            try{
                Thread.sleep(100);
                g.setColor(Color.red);
                g.drawRect(X, Y, wid, hei);
                drawNode(g,this.c);
                c=Color.white;
            }
            catch(InterruptedException e){}
        }
      static  void drawEdgeLine(Graphics g,Node n1,Node n2,Color c)
        {   g.setColor(c);
           // System.out.println("cenx::"+n1.centerX);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2));
            g.drawLine(n1.centerX,n1.centerY,n2.centerX,n2.centerY);
        }
        void drawChar(Graphics g,String  c)
        {
            FontMetrics obj=g.getFontMetrics();
		int a=obj.getAscent();
		int d=obj.getDescent();
                int h=obj.getHeight();
		int w1=obj.stringWidth(c);
		int x1=X+(Node.wid-w1)/2,y1=Y+a+(Node.hei-h)/2;
                g.setColor(Color.blue);
                g.drawString(c,x1,y1);
        }	
}
