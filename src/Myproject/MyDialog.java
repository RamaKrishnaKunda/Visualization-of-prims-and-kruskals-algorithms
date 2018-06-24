package Myproject;
import java.awt.*;
import java.awt.event.*;
public class MyDialog extends Dialog
{
    Font ft;
    String str;
    int sizex;
    MyDialog(Frame f,String n,boolean b,String s,int sizex)
    {
        super(f,n,b);
        str=s;
        this.sizex=sizex;
        Font ft=new Font("Arial",Font.BOLD,22);
        setFont(ft);
        
        addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent we)
        {
            dispose();
        }
        });
        setSize(this.sizex,100);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void paint(Graphics g)
    {    g.setColor(Color.red);  
         g.drawString(str,50,50);
    }
    
    
}
