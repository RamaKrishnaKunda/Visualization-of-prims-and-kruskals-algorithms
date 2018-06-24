package Myproject;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
public class MyPanel extends Panel{
   public static boolean boolpaint=false;
    public static LinkedList<Edge> le;
    public void passingEdge()
    {  //boolpaint=true;
        Graphics g=this.getGraphics();
        // update(g);
         repaint();
    }
    /*public void update(Graphics g)
    {   paint(g);
    }*/
   static {
   le=new LinkedList<Edge>();
   }	
    public void paint(Graphics g)
    {          
	Font f=new Font("Dialog",Font.BOLD,16);
	g.setFont(f);
        g.setColor(Color.red);
        g.drawString("RED NODES INDICATES RECENTLY ADDED",0,20);
        //g.setColor(Color.cyan);
        //g.drawString("INPUT GRAPH",0,40);
        if(boolpaint)
        {   
           try{
            for(int i=0;i<le.size();i++)
            {  
                Edge e=le.get(i);
                if(i==le.size()-1)
                {    Thread.sleep(100);
                    e.node1.drawEdgeLine(g, e.node1,e.node2,Color.white);
                    e.setEdgeColor(Color.red);
                     Thread.sleep(200);
                    e.node1.drawLastNodes(g);
                     Thread.sleep(200);
                    e.node2.drawLastNodes(g);
                     Thread.sleep(100);
                    e.drawWeight(g,Color.white);
                }
                else{
                Thread.sleep(100);
                e.node1.drawEdgeLine(g, e.node1,e.node2,Color.white);
                Thread.sleep(200);
                e.node1.drawNode(g,Color.white);
                Thread.sleep(200);
                e.node2.drawNode(g,Color.white);
                Thread.sleep(100);
                e.drawWeight(g,Color.white);
                //if(kout.size()==Node.n-1)
               
                }
           
            }
                          }
            catch(InterruptedException e){}
        }
    }
}
