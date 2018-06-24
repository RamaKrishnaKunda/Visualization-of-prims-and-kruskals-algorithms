package Myproject;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
public class MyPanel2 extends Panel
{
   public static boolean boolpaint=false;
    public static LinkedList<Edge> le;
    public static LinkedList<Edge> kout;
    public static LinkedList<Node> ln;//collection containing node objects
    public static LinkedList<Edge> pout;
    public static int wtop;//decides what to paint kruskals or prims
    public void passingEdge()
    {  
	//boolpaint=true;
        Graphics g=this.getGraphics();
         //update(g);
         repaint();
    }
//    public void update(Graphics g)
//    {   paint(g);
//    }
   static 
   { 
    ln=new LinkedList<Node>();
   le=new LinkedList<Edge>();
   kout=new LinkedList<Edge>();
   pout=new LinkedList<Edge>();
   wtop=0;
   }	
    public void paint(Graphics g)
    {          
	Font f=new Font("Dialog",Font.BOLD,16);
	g.setFont(f);
        g.setColor(Color.red);
        //g.drawString("RED NODES INDICATES RECENTLY ADDED",0,20);
        if(wtop==1)
               showKruskalsOutput(g);
        if(wtop==2)
            showPrimsOutput(g);
    }
    void showKruskalsOutput(Graphics g)
    {
        try{    
                 //   Node.changeTheNodesPosition(KruskalsOutput1.outnodes,MyPanel.kout,-400); 
            g.setColor(Color.red);
            //g.drawString("KRUSKALS_OUTPUT",0,20);
            for(int i=0;i<kout.size();i++)
            {   
                if(i==0)
                    g.drawString("KRUSKALS_OUTPUT",0,20);
                Edge e1=kout.get(i);
                
                Thread.sleep(100);
                e1.node1.drawEdgeLine(g, e1.node1,e1.node2,Color.white);
               Thread.sleep(200);
                e1.node1.drawNode(g,Color.white);
                Thread.sleep(200);
                e1.node2.drawNode(g,Color.white);
                Thread.sleep(100);
                e1.drawWeight(g,Color.white);
            }
          

            }
            catch(InterruptedException e){}
    }
    void showPrimsOutput(Graphics g)
    {
        try{    
                 //   Node.changeTheNodesPosition(KruskalsOutput1.outnodes,MyPanel.kout,-400); 
            g.setColor(Color.red);
            //g.drawString("KRUSKALS_OUTPUT",0,20);
            for(int i=0;i<pout.size();i++)
            {   Edge e1=pout.get(i);
                if(i==0)
                {
                    g.drawString("PRIMS_OUTPUT",0,20);
                    Thread.sleep(100);
                e1.node1.drawEdgeLine(g, e1.node1,e1.node2,Color.white);
               Thread.sleep(200);
                e1.node1.drawNode(g,Color.white);
                Thread.sleep(200);
                e1.node2.drawNode(g,Color.white);
                Thread.sleep(100);
                e1.drawWeight(g,Color.white);
                }
                    
                else
                {
                
                Thread.sleep(100);
                e1.node1.drawEdgeLine(g, e1.node1,e1.node2,Color.white);
               Thread.sleep(200);
                e1.node1.drawNode(g,Color.white);
                Thread.sleep(200);
                e1.node2.drawNode(g,Color.white);
                Thread.sleep(100);
                e1.drawWeight(g,Color.white);
                }
            }
            }
            catch(InterruptedException e){}
    
}
}
