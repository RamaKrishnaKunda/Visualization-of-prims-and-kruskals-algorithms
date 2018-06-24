package Myproject;
import java.util.*;
public class KruskalsOutput1
{
	ArrayList<LinkedList<Node>> arrt;
        LinkedList<Edge> outll;//this linked list stors output linked list
	Iterator<LinkedList<Node>> ita;//Iterator on arraylist
	LinkedList<Edge> inll;//thisis linkedlist stores the input edges
        public static LinkedList<Node> outnodes;//contains all the nodes which are present in outll
	boolean a[];//this array is used so that if both nodes didn't appear even once directly they are added to the arrt
	int count;//for number of edges
	Iterator<Node> itl;//used to traverse on TreeSet object
	public KruskalsOutput1(LinkedList<Edge> inll)
	{
		arrt=new ArrayList<LinkedList<Node>>();
		outll=new LinkedList<Edge>();
                outnodes=new LinkedList<Node>();
                List<Edge> list=inll;
                Collections.sort(list,new Sorting());
                this.inll=inll;
		a=new boolean[Node.n];
		for(int i=0;i<Node.n;i++)
		{
			a[i]=false;
		}
		count=0;
        }
        void findEdge(int i)
        {
            Edge ed=(Edge)inll.get(i);
                                 String s1=ed.node1.getNodeName();
				String s2=ed.node2.getNodeName();
				int v1=(int)s1.charAt(0);
				int v2=(int)s2.charAt(0);
				a[v1-65]=true;
				a[v2-65]=true;
				outll.add(ed);
				count++;
            
        }
        public void kruskals_output()
	{
		Iterator<Edge> it2=inll.iterator();//used to iterate on input linked list
		Edge ed;
		while(count<(Node.n)-1 && it2.hasNext())
		{
			ed=(Edge)it2.next();
			if(!iscycle(ed.node1,ed.node2))
			{
				String s1=ed.node1.getNodeName();
				String s2=ed.node2.getNodeName();
				int v1=(int)s1.charAt(0);
				int v2=(int)s2.charAt(0);
				a[v1-65]=true;
				a[v2-65]=true;
				outll.add(ed);
				count++;
                                if(!(outnodes.contains(ed.node1)))
                                    outnodes.add(ed.node1);
                                if(!(outnodes.contains(ed.node2)))
                                    outnodes.add(ed.node2);

			}
		}
	}	
	public boolean iscycle(Node node1,Node node2)
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