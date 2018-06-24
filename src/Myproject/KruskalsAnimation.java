package Myproject;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
public class KruskalsAnimation extends Frame implements Runnable{
    
    LinkedList<Edge> ed;//
    LinkedList<Node> Anodes;//input Nodes which are used in graph
    LinkedList<Rect1> rect1s;//collection of upper Rects in table which conatins the input edges  
    LinkedList<Rect2> rect2s;//collection of lower Rects in table which contains the corresponding edge weights
    LinkedList<Edge> Aedge;//collection of edges (kruskals output edges)
    KruskalsOutput1 ko;//kruskalsOutput Object used for checking isCycle() while connecting the edges
    int status;//status for painting (initial(0),non cycle edge(1),Cycle edge(2))
    int count1,count2;//variables for moving to the next Rect and finding the next edge to be connected
    boolean bool;//set(true) when cycle is formed 
    Color Wcolor;
    Graphics g1;    //get the graphics context
    Label l1;
    Thread t1;
    KruskalsAnimation(LinkedList<Node> lt,LinkedList<Edge> le)//initialising all the variables
    {   super("KRUSKALS ANIMATION");
        setVisible(true);
        setSize(1000,750);
        setLocation(200,0);
        setResizable(false);
        Font f=new Font("Arial",Font.BOLD,16);
        setFont(f);
        t1=new Thread(this);
        count1=0;
        count2=0;
        ko=new KruskalsOutput1(le);
        Aedge=new LinkedList<Edge>();
        rect1s=new LinkedList<Rect1>();
        rect2s=new LinkedList<Rect2>();
        ed=ko.inll;
        Anodes=lt;
        status=0;
        Wcolor=Color.green;
        setLayout(new FlowLayout());
        l1=new Label();
        l1.setForeground(Color.pink);
        l1.setPreferredSize(new Dimension(500,30));
        add(l1);
        setTable();
        g1=this.getGraphics();
        addWindowListener(new WindowAdapter() {//windowListner for closing the Main Frame Window
	public void windowClosing(WindowEvent evt) 
		{
                         Rect1.changeX=151;
                        Rect2.changeX2=151;
                	dispose(); //exitForm(evt) can also be used

		}
        	});
        t1.start();
    }
    void setTable()//set the table for edges...
    {
        for(int i=0;i<ed.size();i++)
        {  Edge e=(Edge)ed.get(i);
            String s=(e.node1.getNodeName())+(e.node2.getNodeName());
            rect1s.add(new Rect1(s));
            rect2s.add(new Rect2(String.valueOf(e.weight)));
        }
    }

    public void run()//running the Thread
    {   //repaint();
        try{
            Thread.sleep(4000);
            while(count1<Anodes.size()-1)
            {      
                Aedge.add(ed.get(count2));
                cycleCheck(count2);
                
                if(!bool)
                {   
                    ko.findEdge(count2);
                    status=1;
                    count1++;
                  
                }
                else
                {  
                     status=2;
                }
                update(g1);
                count2++;
            }
            l1.setForeground(Color.blue);
            l1.setText("***RESULTANT MINIMUM SPANNING TREE***");
        }
        catch(InterruptedException e){}
        
        
    
    }
    
    void cycleCheck(int i)//checking the whether cycle is forming or not
    {
        Node n1=ed.get(i).node1;
        Node n2=ed.get(i).node2;
        if(ko.iscycle(n1,n2))
            bool=true;
        else bool=false;
       
    }
    public void update(Graphics g)
    {
        paint(g);
    }
    public void paint(Graphics g)
    {   try{    
        if(status==0)//initial condition where it display the nodes
            {   l1.setText("ARRANGING NODES IN ASCENDING ORDER");
                FontMetrics obj=g.getFontMetrics();
		int a=obj.getAscent();
		int d=obj.getDescent();
                int h=obj.getHeight();
		int w1=obj.stringWidth("Edge");
		int x1=50+(100-w1)/2;
                int y1=70+a+(30-h)/2;
                g.setColor(Color.black);
		g.drawRect(50,70,100,30);
		g.drawString("Edge",x1,y1);
		g.drawRect(50,101,100,30);
		int w2=obj.stringWidth("Weight");
		int x2=50+(100-w2)/2;
		int y2=101+a+(40-h)/2;
		g.drawString("Weight",x2,y2);
                drawTable(g);//drawing the table which contains edges in ascending  order of weights
                drawGraph(g);//draw the initial graph with zero edges(contains only nodes)
            }
    
                
            
            
                if(status==1)//for non cycling edges
                {   l1.setForeground(Color.green);
                    l1.setText("FORMING MINIMUM SPANNING TREE.....");
                    Thread.sleep(2000);
                    rect1s.get(count2).drawRect1(g,Color.green);
                    rect2s.get(count2).drawRect2(g,Color.green);
                    Thread.sleep(1500);
                    drawWeights(g,Color.green);
                    
                }
                if(status==2)//for cycling edges
                {
                    Thread.sleep(2000);
                    rect1s.get(count2).drawRect1(g,Color.red);
                    rect2s.get(count2).drawRect2(g,Color.red);
                    Thread.sleep(1000);
                    drawWeights(g,Color.red);
                    Thread.sleep(2000);
                    g.setColor(Color.red);
                    Thread.sleep(500);
                    l1.setForeground(Color.red);
                    l1.setText("CYCLE ..?!!!!!");
                    g.drawLine(50,250,700,650);
                    g.drawLine(50,650,700,250);
                    Thread.sleep(2000);
                    Aedge.remove(count1);
                    rect1s.get(count2).drawRect1(g,Color.red);
                    rect2s.get(count2).drawRect2(g,Color.red);
                    g.setColor(Color.white);
                    g.drawLine(50,250,700,650);
                    g.drawLine(50,650,700,250);
                    drawWeights(g,Color.green);
                }
                
                
            }
            catch(InterruptedException e){}
        
    }
    void drawWeights(Graphics g,Color c)//drawing the output edges
    {   System.out.println("drawWeights=="+Aedge.size());
        for(int i=0;i<Aedge.size();i++)
        {   Edge e1=(Edge)Aedge.get(i);            
                e1.drawWeight(g,Color.red);
                Node.drawEdgeLine(g, e1.node1, e1.node2,c);
                e1.node1.drawNode(g,Color.orange);
                e1.node2.drawNode(g,Color.orange);
            
        }
    }
    void drawTable(Graphics g)//drawing the table for displaying the edges and corresponding weights
    {
        try{
         for(int i=0;i<rect1s.size();i++)
        {   
            Rect1 r1=rect1s.get(i);
            Rect2 r2=rect2s.get(i);
            g.setColor(Color.black);
            Thread.sleep(180);
            r1.drawRect1(g,Color.black);
            Thread.sleep(180);
            r2.drawRect2(g,Color.black);
        }
        }
        catch(InterruptedException e){}
    }
    void drawGraph(Graphics g)
    {
        for(int i=0;i<Anodes.size();i++)
        {
            Anodes.get(i).drawNode(g,Color.red);
        }
    }

    
}

class Rect1{//class for rectangles to display edges
        Color c;
	String s1=null;
	int x;
        static int changeX;
	final int wid=30,hei=30,y=70;
	static{changeX=151;}
        Rect1(String s1)
        {
            this.s1=s1;
            x=changeX;
            changeX+=31;
        }
        void drawRect1(Graphics g,Color c)
	{
		FontMetrics obj=g.getFontMetrics();
		int a=obj.getAscent();
		int d=obj.getDescent();
                int h=obj.getHeight();
		g.setColor(c);
		g.drawRect(x,y,wid,hei);
		int w1=obj.stringWidth(s1);
		int x1=x+(wid-w1)/2;
		int y1=y+a+(hei-h)/2;
		g.drawString(s1,x1,y1);
	}
        void changeColor(Color c)
        {
            this.c=c;
        }
}
class Rect2{//class for rectangles to display weights of corresponding edges
        Color c;
	String s1=null;
	int x;
        static int changeX2;
	final int wid=30,hei=30,y=101;
	static{changeX2=151;}
        Rect2(String s1)
        {
            this.s1=s1;
            x=changeX2;
            changeX2+=31;
        }
        void drawRect2(Graphics g,Color c)
	{
		FontMetrics obj=g.getFontMetrics();
		int a=obj.getAscent();
		int d=obj.getDescent();
                int h=obj.getHeight();
		g.setColor(c);
		g.drawRect(x,y,wid,hei);
		int w1=obj.stringWidth(s1);
		int x1=x+(wid-w1)/2;
		int y1=y+a+(hei-h)/2;
		g.drawString(s1,x1,y1);
	}
        void changeColor(Color c)
        {
            this.c=c;
        }
}