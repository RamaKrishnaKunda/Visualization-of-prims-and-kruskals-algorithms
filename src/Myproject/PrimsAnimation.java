package Myproject;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
public class PrimsAnimation extends Frame implements Runnable {
    LinkedList<Edge> edtable;
    LinkedList<Node> Anodes;
    LinkedList<Rect1> rect1s;
    LinkedList<Rect2> rect2s;
    LinkedList<Edge> Aedge;
    int key;
    boolean keyVisited[]=new boolean[Node.n];
    boolean edges[];
    boolean bool;
    PrimsOutput po;
    KruskalsOutput1 ko1;//used for checking isCycle
    Thread t1;
    int status;    
    int prev;
    Label l1;
    Graphics g1;
    boolean edm[][];
    ArrayList<Integer> cyedges;
    ArrayList<LinkedList<Node>> arrt;
    boolean a[];
    Iterator<LinkedList<Node>> ita;
    Iterator<Node> itl;
    PrimsAnimation(LinkedList<Node> lt,LinkedList<Edge> le)
    {   super("PRIMS ANIMATION");
        setVisible(true);
        setSize(1000,750);
        setLocation(200,0);
        setResizable(false);
        arrt=new ArrayList<LinkedList<Node>>();
        Font f=new Font("Arial",Font.BOLD,16);
        setFont(f);
        a=new boolean[Node.n];
            for(int i=0;i<Node.n;i++)
            {
		a[i]=false;
                keyVisited[i]=false;
            }
            keyVisited[0]=true;
        t1=new Thread(this);
        Aedge=new LinkedList<Edge>();
        rect1s=new LinkedList<Rect1>();
        rect2s=new LinkedList<Rect2>();
        edtable=new LinkedList<Edge>();
        cyedges=new ArrayList<Integer>();
        Anodes=lt;
        po=new PrimsOutput(le);
        ko1=new KruskalsOutput1(le);
        status=0;
        prev=0;
        bool=false;
        g1=this.getGraphics();
        edges=new boolean[FrontEnd.noedges];
        for(int i=0;i<FrontEnd.noedges;i++)
            edges[i]=false;
        edm=new boolean[Node.n][Node.n];
        for(int i=0;i<Node.n;i++)
            edm[i]=new boolean[Node.n]; 
        for(int i=0;i<Node.n;i++)
	{
            for(int j=0;j<Node.n;j++)
            {       edm[i][j]=false;
                       edm[i][i]=true;
            }      
        }
        setLayout(new FlowLayout());
        l1=new Label();
        l1.setForeground(Color.pink);
        l1.setPreferredSize(new Dimension(500,30));
        add(l1);
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
    public void run()
    {   int k=0;
        //update(g1);
        //System.out.println("no edges::"+FrontEnd.noedges);
        try{
            l1.setText("STARTING FROM A");
            Thread.sleep(3000);
            key=0;
            
        while(k<Node.n-1)
        {      Thread.sleep(1000);
                l1.setText(" EDGES CONNECTED WITH "+String.valueOf((char)(key+65)));
                setTable(key);
                status=1;
                update(g1);
                //repaint();
                if(!bool)
                {
                    k++;
                }
        }
        l1.setForeground(Color.blue);
        l1.setText("*****RESULTANT PRIMS OUTPUT***** ");
        }
        catch(InterruptedException e){}
        
    }
     public void setTable(int k)
    {   int j=0;
        for(int i=0;i<Node.n;i++)
        {   if(po.m[k][i]!=9999 && edm[k][i]==false && edm[i][k]==false)
            {
                j=i;
            
            Edge e=po.getEdge(k,j);
            edtable.add(e);
            String s=(e.node1.getNodeName())+(e.node2.getNodeName());
            rect1s.add(new Rect1(s));
            rect2s.add(new Rect2(String.valueOf(e.weight)));
            edm[k][j]=true;
            edm[j][k]=true;
            }
        }
    }
    public void update(Graphics g)
    {
        paint(g);
    }
    public void paint(Graphics g)
    {
        //try
        {
            if(status==0)
            {   
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
                drawGraph(g);
            }
            if(status==1)
            {   
                drawTable(g);
                Edge e=findEdge(g);
                if(!iscycle(e.node1,e.node2))
                {   
                    String s1=e.node1.getNodeName();
                    String s2=e.node2.getNodeName();
				int v1=(int)s1.charAt(0);
				int v2=(int)s2.charAt(0);
				a[v1-65]=true;
				a[v2-65]=true;
                    Aedge.add(e);bool=false;}
                else 
                {//   edges[prev]=true;
                    bool=true;
                    System.out.println("acdf");
                }
                drawWeights(g,Color.green);
            }
            
        }
        //catch(InterruptedException e){}
    }
    void drawTable(Graphics g)
    {
        try{
         for(int i=0;i<rect1s.size();i++)
        {   Rect1 r1=rect1s.get(i);
            Rect2 r2=rect2s.get(i);
            if(edges[i]==true)
            {
                r1.drawRect1(g,Color.green);
                Thread.sleep(500);
                r2.drawRect2(g,Color.green);
            }
            else{
            //g.setColor(Color.black);
            Thread.sleep(500);
            r1.drawRect1(g,Color.black);
            Thread.sleep(500);
            r2.drawRect2(g,Color.black);
            }
        }
         for(int i=0;i<cyedges.size();i++)
         {
             Rect1 r1=rect1s.get(cyedges.get(i));
            Rect2 r2=rect2s.get(cyedges.get(i));
            r1.drawRect1(g,Color.red);
            r2.drawRect2(g,Color.red);
         }    
        }
        catch(InterruptedException e){}
    }
    Edge findEdge(Graphics g)
    {   //int value=10000;
        int minv=10000;
         prev=-1;
        Rect1 r1;
        Rect2 r2;
          l1.setText("*****FINDING THE LEAST WEIGHT EDGE..... ");
        try
        {
            B:for(int i=0;i<rect1s.size();i++)
            {   
          
                 while(edges[i]==true && i<rect1s.size())
                {
                   i++;
                   if(i==rect1s.size())
                     break B;
                }
                 r2=(Rect2)rect2s.get(i);
                 r1=(Rect1)rect1s.get(i);
                Thread.sleep(1000);
                r1.drawRect1(g,Color.red);
                r2.drawRect2(g,Color.red);
                if(Integer.parseInt(r2.s1)<minv)
                {   
                    minv=Integer.parseInt(r2.s1);
                    //Thread.sleep(500);
                    if(prev!=-1&& edges[prev]==false)
                    {
                        rect1s.get(prev).drawRect1(g,Color.black);
                        rect2s.get(prev).drawRect2(g, Color.black);
                    }
                    Thread.sleep(1000);
                    r1.drawRect1(g,Color.blue);
                    r2.drawRect2(g,Color.blue);
                    prev=i;
              
                }
                else{
                   Thread.sleep(1000);
                    r1.drawRect1(g,Color.black);
                    r2.drawRect2(g,Color.black);
        
                }
            }
        }
        catch(InterruptedException e){}
       edges[prev]=true;
       Edge out=(Edge)edtable.get(prev);
       if(iscycle(out.node1,out.node2))
       {
             cyedges.add(prev);
       }
       key=getKey(out);
       keyVisited[key]=true;
       return out;
    }
    int getKey(Edge e)
    {
        String s1=e.node1.getNodeName();
        String s2=e.node2.getNodeName();
        int v1=(int)s1.charAt(0);
        int v2=(int)s2.charAt(0);
        int retun=key;
        if(key==v1-65 && keyVisited[v2-65]==false)
            retun=v2-65;
        if(key==v2-65 && keyVisited[v1-65]==false)
            retun=v1-65;
        return retun;
    }
    void drawGraph(Graphics g)
    {
        for(int i=0;i<Anodes.size();i++)
        {
            Anodes.get(i).drawNode(g,Color.red);
        }
    }
    
     void drawWeights(Graphics g,Color c)
    {   
        for(int i=0;i<Aedge.size();i++)
        {   Edge e1=(Edge)Aedge.get(i);            
                e1.drawWeight(g,Color.red);
                Node.drawEdgeLine(g, e1.node1, e1.node2,c);
                e1.node1.drawNode(g,Color.orange);
                e1.node2.drawNode(g,Color.orange);
        }
    }
     boolean iscycle(Node node1,Node node2)
	{
		String s1=node1.getNodeName();
		String s2=node2.getNodeName();
		int v1=(int)s1.charAt(0);
		int v2=(int)s2.charAt(0);
		if(a[v1-65]==false && a[v2-65]==false)
		{
			//this is used when both nodes are not present
			LinkedList<Node> ts=new LinkedList<Node>();
			ts.add(node1);
			ts.add(node2);
			arrt.add(ts);
			return false;
		}
		else if(a[v1-65]==false && a[v2-65]==true)
		{
			//this is used when first node is not present and second node is present
			LinkedList<Node> ts;
			ita=arrt.iterator();
			int i=0;
			while(ita.hasNext())
			{
				ts=(LinkedList<Node>)ita.next();
				itl=ts.iterator();
				while(itl.hasNext())
				{
					if(node2.getNodeName().equals(itl.next().getNodeName()))
					{
						(arrt.get(i)).add(node1);
						return false;
					}
				}
				i++;
			}
		}
		else if(a[v1-65]==true && a[v2-65]==false)
		{
			//this is used when first node is present and second node is node present
			LinkedList<Node> ts;
			ita=arrt.iterator();
			int i=0;
			while(ita.hasNext())
			{
				ts=(LinkedList<Node>)ita.next();
				itl=ts.iterator();
				while(itl.hasNext())
				{
					if((node1.getNodeName()).equals(itl.next().getNodeName()))
					{
						(arrt.get(i)).add(node2);
						return false;
					}
				}
				i++;
			}
		}
		else
		{
			//this is used when both the nodes are created but to check whether they are forming cycle or not
			boolean b1=false;//used for if both these are true in the same iteration the it forms a cycle else those TreeSet objects should be clubbed
			boolean b2=false;
			LinkedList<Node> ts;
			ita=arrt.iterator();
			int wtoadd=0;//this variable is used to know where to add the clubbed TreeSet object
			int wtorem=0;// this variable is used to know where to remove the TreeSet<Node> object
			int i=0;//this will assign values to wtoadd and wtorem
			int j=0;//used when to assing i to wtoadd and wtorem
			LinkedList<Node> t1=new LinkedList<Node>();
			LinkedList<Node> t2=new LinkedList<Node>();
			while(ita.hasNext())
			{
				ts=(LinkedList<Node>)ita.next();
				itl=ts.iterator();
				while(itl.hasNext())
				{
					String s=((Node)itl.next()).getNodeName();
					if(node1.getNodeName().equals(s))
					{
						b1=true;
					}	
					if(node2.getNodeName().equals(s))
					{
						b2=true;
					}	
				}
				if(b1==true && b2==true && j==0)// if both are present in the same TreeSet object both b1 and b2 will be true and true is returned  
				{
						return true;
				}
				if(b1==true && t1.isEmpty())
				{
					if(j==0)
					{
						wtoadd=i;
						j++;
					}
					t1=(LinkedList<Node>)ts.clone();
				}
				if(b2==true && t2.isEmpty())
				{
					if(j==0)
					{
						wtoadd=i;
						j++;
					}
					t2=(LinkedList<Node>)ts.clone();
				}
				if(!(t1.isEmpty()) && !(t2.isEmpty()))
				{
					wtorem=i;
					break;
				}
				i++;
			}
			t1.addAll(t2);
			t2.clear();
			//ita=arr.iterator();
			//i=0;
                        arrt.set(wtoadd,t1);
                        arrt.get(wtorem).clear();
			/*while(ita.hasNext())
			{
				if(i==wtoadd)
				{
					((TreeSet<Node>)ita.next())=t1;
				}
				if(i==wtorem)
				{
					((TreeSet<Node>)ita.next()).clear();
				}
				i++;
			}*/
                        return false;
		}
             return false;
	}
}

