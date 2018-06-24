package Myproject;
import java.util.*;
class PrimsOutput
{
	int p;
	LinkedList<Edge> in;
	public LinkedList<Edge> outll;
        int m[][];
        Node n;
        Iterator<Edge> it;
        boolean Reached[];
	public PrimsOutput(LinkedList<Edge> in)
	{
            p=9999;
            outll=new LinkedList<Edge>();
            this.in=in;
            List<Edge> list=this.in;
            Collections.sort(list,new Sorting());
            m=new int[Node.n][Node.n];
                for(int i=0;i<Node.n;i++)
                {
                    m[i]=new int[Node.n];
                    
                }
		Reached=new boolean[Node.n];
		for(int i=0;i<Node.n;i++)
		{
			Reached[i]=false;
		}
		for(int i=0;i<Node.n;i++)
		{
			for(int j=0;j<Node.n;j++)
			{
				m[i][j]=p;
			}
		}
		//for(int i=0;i<Node.n;i++)
                it=(this.in).iterator();
                while(it.hasNext())
		{
                    Edge ed=(Edge)it.next();
                    String s1=ed.node1.getNodeName();
                    String s2= ed.node2.getNodeName();
                    int v1=(int)s1.charAt(0);
                    int v2=(int)s2.charAt(0);
			m[v1-65][v2-65]=ed.weight;
                        m[v2-65][v1-65]=ed.weight;
		}               
                Prims_Output();
	}
	public void Prims_Output()
	{
		Reached[0]=true;
		for(int k=1;k<Node.n;k++)
		{
		int x=0,y=0;
			for(int i=0;i<Node.n;i++)
			{       
				for(int j=0;j<Node.n;j++)
				{
					if(Reached[i]&&!Reached[j]&&m[i][j]<m[x][y])
					{
						x=i;
						y=j;
					}
				}
			}
                        if(x!=y)
                        {
			Reached[y]=true;
                        //Node n1=getNode(x);
                        //Node n2=getNode(y);
			Edge ed=getEdge(x,y);
                        outll.add(ed);
                        }
		}
	}
        Edge getEdge(int x,int y)
        {
            x+=65;
            y+=65;
            String str1=String.valueOf((char)x);
            String str2=String.valueOf((char)y);
            Iterator<Edge> it=in.iterator();
            Edge ed=null;
            while(it.hasNext())
            {
              ed=(Edge)it.next();
               if(str1.equals(ed.node1.getNodeName()) && str2.equals(ed.node2.getNodeName()))
               {    break;
                   //return ed;
               }
               if(str2.equals(ed.node1.getNodeName()) && str1.equals(ed.node2.getNodeName()))
               {    break;
                  // return ed;
               }
            }
          return ed;
        }
}