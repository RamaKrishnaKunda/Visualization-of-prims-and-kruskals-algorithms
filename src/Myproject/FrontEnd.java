package Myproject;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class FrontEnd extends Frame 
{	
    Label no_nodes;
    TextField tnodes;
    Button set_nodes;
    Panel panel1;
    MyPanel panel2;
    Label node;
    Label V1;
    Label V2;
    Label W;
    Choice V1c;
    Choice V2c;
    TextField Wt;
    Button Add;
    Button Prims_Output;
    Button Kruskals_Output;
    Button Prims_Animation;
    Button Kruskals_Animation;
    LinkedList<Edge> edge_tree;
    LinkedList<Node> node_tree;
    static int noedges;
    static{noedges=0;}
    boolean bool[];//for comparing the nodes 
    
    public FrontEnd()
    {   
        super("FRONT END");//This constructor gives the title to the project
        //setLayout(new FlowLayout());
        edge_tree=new LinkedList<Edge>();//for Storing the edges(Edge objects)
        node_tree=new LinkedList<Node>();
        initComponents();
    }
    private void initComponents()//This will add all the components in MainFrame
    {  
        Frame Frame1=new Frame();
        setPreferredSize(this.getMaximumSize());
        //panel-1 for components
        panel1 =new Panel();
        Dimension p=this.getMaximumSize();
	int width=p.width;
        int height=p.height;
        panel1.setPreferredSize(new Dimension(width,105));
	panel1.setBackground(Color.gray);
        
        
        no_nodes=new Label("NO.OF VERTICES");  //creates a label named node
	no_nodes.setFont(new Font("Dialog", 3, 14));
       	no_nodes.setForeground(Color.red);
	panel1.add(no_nodes);//Adding this label to the panel-1
        
        //For taking the input (no.of nodes) in the graph
	tnodes=new TextField();  //creates a TextField for the no.of Nodes
	tnodes.setPreferredSize(new Dimension(40, 20));
	panel1.add(tnodes);//Adding this TextField to the panel-1
	
        set_nodes=new Button("set_nodes");
        set_nodes.setFont(new Font("Dialog", 3, 14));
        set_nodes.setForeground(Color.black);
        set_nodes.setPreferredSize(new Dimension(80, 20));
	panel1.add(set_nodes);
       
        node=new Label("NODE:");  //creates a label named node
	node.setFont(new Font("Dialog", 3, 14));
       	node.setForeground(Color.red);
	panel1.add(node);//Adding this Label("Node") to the panel-1
		
        
	V1=new Label("V1:");  //creates a label named V1:
	V1.setFont(new Font("Dialog", 3, 12));
        panel1.add(V1);
	
        
        V1c=new Choice();
        V1c.setPreferredSize(new Dimension(60,20));
        panel1.add(V1c);
        
       	V2=new Label("V2:");//creates a Label for v2		
	V2.setFont(new Font("Dialog", 3, 12)); 
       	panel1.add(V2);
	
        V2c=new Choice();
        V2c.setPreferredSize(new Dimension(60,20));
        panel1.add(V2c);
	
	W=new Label("W:");//Creates a Label for Weight
	W.setFont(new Font("Dialog", 3, 12));
       	W.setForeground(Color.darkGray);
       	panel1.add(W);
			
	//TextField for Weight of the Edge	
	Wt = new TextField();
	Wt.setPreferredSize(new Dimension(40, 20));
	panel1.add(Wt);
		
	//Creates a Button for Adding the Edge to the current Graph	
	Add= new Button("Add");
	Add.setFont(new Font("Dialog", 3, 12));
        Add.setForeground(Color.black);
	panel1.add(Add);
        
	
        //Creates a Button for displaying the Prims Output Graph
       	Prims_Output = new Button("Prims_Output");
	Prims_Output.setFont(new java.awt.Font("Dialog", 3, 12)); 
        Prims_Output.setForeground(Color.black);
	panel1.add(Prims_Output);
		
        //Creates a Button for Displaying the Kruskals Output Graph
	Kruskals_Output = new Button("Kruskals_Output");
	Kruskals_Output.setFont(new java.awt.Font("Dialog", 3, 12));
        Kruskals_Output.setForeground(Color.black);
	panel1.add(Kruskals_Output);
		
        //Creates a Button for Showing Animation Involved in Prims
	Prims_Animation = new Button("Prims_Animation");
	Prims_Animation.setFont(new Font("Dialog", 3, 12)); 
        Prims_Animation.setForeground(Color.black);
	panel1.add(Prims_Animation);
		
        //Creates a Button for Showing Animation Involved in Kruskals
	Kruskals_Animation = new Button("Kruskals_Animation");
	Kruskals_Animation.setFont(new Font("Dialog", 1, 12)); 
        Kruskals_Animation.setForeground(Color.black);
	panel1.add(Kruskals_Animation);
	
        //Adding the Panel-2 for Drawing the Graphics
	add(panel1,BorderLayout.NORTH);
        panel2=new MyPanel();
        add(panel2,BorderLayout.CENTER);
        panel2.setBackground(Color.black);
        MyPanel2 panel3=new MyPanel2();
        panel3.setBackground(Color.BLACK);
        panel3.setPreferredSize(new Dimension(700,300));
        add(panel3,BorderLayout.EAST);    
            // panel3.setVisible(false);
        setInitial();

        tnodes.addTextListener(new TextListener()
        {
            public void textValueChanged(TextEvent te)
            {
                if(tnodes.getText().equals(" "))
                    set_nodes.setEnabled(false);
                else
                    set_nodes.setEnabled(true);
            }
        });
        set_nodes.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Node.n=Integer.parseInt(tnodes.getText());
                bool=new boolean[Node.n];//This loop initialiseS the bool(comparing nodes)to false
                for(int i=0;i<Node.n;i++)
                    bool[i]=false;
                for(int i=0;i<Node.n;i++)
                {   char ch=(char)(65+i);
                    V1c.add(String.valueOf(ch));
                    V2c.add(String.valueOf(ch));
                }
                V1c.setEnabled(true);
                V2c.setEnabled(true);
                Wt.setEnabled(true);
                Add.setEnabled(true);
                tnodes.setEnabled(false);
                set_nodes.setEnabled(false);
                
            }
        });
        addWindowListener(new WindowAdapter() {//windowListner for closing the Main Frame Window
	public void windowClosing(WindowEvent evt) 
		{
                	System.exit(1); //exitForm(evt) can also be used 
		}
        	});	
	Add.addActionListener(new ActionListener() {//Action Listener For Add Button		
        public void actionPerformed(ActionEvent evt)//Action be Performed when the Add Button pressed
        {   Kruskals_Output.setEnabled(true);
            Prims_Output.setEnabled(true);
            Prims_Animation.setEnabled(true);
            Kruskals_Animation.setEnabled(true);
            
            MyPanel.boolpaint=true; 
            String ch1=V1c.getSelectedItem();
            String ch2=V2c.getSelectedItem();
            int v1=(int)ch1.charAt(0);
            int v2=(int)ch2.charAt(0);
            String str1=Wt.getText();
            int weight=0;
            int draw=0;
            if(str1.length()==0)
                new MyDialog(Frame1,"Exception",true,"WEIGHT NOT ENTERED",400);
            else 
            {
                weight=Integer.parseInt(Wt.getText());
                if(weight==0)
                    new MyDialog(Frame1,"Exception",true,"WEIGHT SHOULD BE A NON-ZERO VALUE",600);
            }
            if(v1==v2 && weight<0)
            {
                new MyDialog(Frame1,"Exception",true,"NO SELF LOOPS and NEGATIVE WEIGHTS ARE NOT ALLOWED",800);
            }
            else if(v1==v2)
            {
                new MyDialog(Frame1,"Exception",true,"NO SELF LOOPS",350);
            }
            else if(weight<0)
            {
                new MyDialog(Frame1,"Exception",true,"NEGATIVE WEIGHTS ARE NOT ALLOWED",600);
            }
            else if(edgeAlreadyPresent(ch1,ch2))
            {
            if(weight!=0)
            {
                Iterator<Edge> ite=edge_tree.iterator();
                while(ite.hasNext())
                {
                    Edge ed=(Edge)ite.next();
                    if(ch1.equals(ed.node1.getNodeName()) && ch2.equals(ed.node2.getNodeName()))
                    {
                        if(weight>ed.weight)
                        {
                            weight=ed.weight;
                        }
                        if(weight<ed.weight);
                        {
                            ed.weight=weight;
                            break;
                        }
                    }
                    else if(ch1.equals(ed.node2.getNodeName()) && ch2.equals(ed.node1.getNodeName()))
                    {
                        if(weight>ed.weight)
                        {
                            weight=ed.weight;
                        }
                        if(weight<ed.weight);
                        {
                            ed.weight=weight;
                            break;
                        }
                    }
                }
            MyPanel.le=edge_tree;
          //  MyPanel.ln=node_tree;
            panel2.passingEdge();
            draw++;
            }
            }
            else{
            noedges++;
            Node n1=null,n2=null,n3=null,n4=null;
            ListIterator it1=edge_tree.listIterator();
            if(bool[v1-65]==false && bool[v2-65]==false)
            {				 
                n1=new Node(ch1);
		n2=new Node(ch2);
                node_tree.add(n1);
                node_tree.add(n2);
            }
            else if(bool[v1-65]==true && bool[v2-65]==true)
            {	
                for(int i=0;i<edge_tree.size();i++)
		{
                    Edge e1=edge_tree.get(i);
                    if(ch1.equals(e1.node1.getNodeName()))
                    {		
                        n1=e1.node1;
                        break;
                    }
                    else if(ch1.equals(e1.node2.getNodeName()))
                    {
                        n1=e1.node2;
                        break;
                    }
							
                }
		for(int j=0;j<edge_tree.size();j++)
		{
                    Edge e1=edge_tree.get(j);
                    if(ch2.equals(e1.node1.getNodeName()))
                    {
                        n2=e1.node1;
                        break;
                    }
                    else if(ch2.equals(e1.node2.getNodeName()))
                    {
                        n2=e1.node2;
                        break;
                    }
							
                }
            }
					
            else
            {
                n1=compare(ch1,ch2);
                if(n1.getNodeName().equals(ch1))
                {   n2=new Node(ch2);
                }
                else
                {   n2=new Node(ch1);
                }
                node_tree.add(n2);
            }
            if(weight!=0 && draw==0)
            {
                Edge ed1=new Edge(n1,n2,weight);
                bool[v1-65]=true;
                bool[v2-65]=true;
                edge_tree.add(ed1);
                MyPanel.le=edge_tree;
               // MyPanel.ln=node_tree;
                panel2.passingEdge();
            }				
            }				
        }
	});
       Kruskals_Output.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {  
               Kruskals_Animation.setVisible(true);
             KruskalsOutput1 out=new KruskalsOutput1(edge_tree);
             out.kruskals_output();
               MyPanel2.kout=out.outll;
               MyPanel2.wtop=1;
             panel3.passingEdge();
            }
       }
       );
        Prims_Output.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {  
             PrimsOutput out=new PrimsOutput(edge_tree);
               MyPanel2.pout=out.outll;
               MyPanel2.wtop=2;
             panel3.passingEdge();
             }
       }
       );
       Kruskals_Animation.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {   
               new KruskalsAnimation(node_tree,edge_tree);
           }
       });
        Prims_Animation.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e)
           {   
               new PrimsAnimation(node_tree,edge_tree);
           }
       });
	setLocation(0,0);
        setVisible(true);
        pack();

    }
    //Mrthod for Comparing the Entered Vertices with the Existence Vertices
     void setInitial()
      {  
        tnodes.setEnabled(true);
        set_nodes.setEnabled(false);
        V1c.setEnabled(false);
        V2c.setEnabled(false);
        Wt.setEnabled(false);
        Add.setEnabled(false);
        Prims_Output.setEnabled(false);
        Kruskals_Output.setEnabled(false);
        Prims_Animation.setEnabled(false);
        Kruskals_Animation.setEnabled(false);
      }
    Node compare(String ch1,String ch2)
    {	
        Node temp=null;
        ListIterator it1=edge_tree.listIterator();
        while(it1.hasNext() )
        {
            Object obj=it1.next();
            Edge e=(Edge)obj;
            if(ch1.equals(e.node1.getNodeName()) || ch2.equals(e.node1.getNodeName()))
            {   temp=e.node1;
                break;
            }
            else if(ch1.equals(e.node2.getNodeName()) || ch2.equals(e.node2.getNodeName()))
            {
                temp=e.node2;
                break;
            }
        }
        return temp;					
    }
    boolean edgeAlreadyPresent(String ch1,String ch2)
    {
        Iterator<Edge> ite=edge_tree.iterator();
        while(ite.hasNext())
        {
            Edge ed=(Edge)ite.next();
            if(ch1.equals(ed.node1.getNodeName()) && ch2.equals(ed.node2.getNodeName()))
            {
                return true;
            }
            else if(ch1.equals(ed.node2.getNodeName()) && ch2.equals(ed.node1.getNodeName()))
            {
                return true;
            }
        }
        return false;
    }
    public static void main(String args[])
    {
       new FrontEnd();
    }    
}

	