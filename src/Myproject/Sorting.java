package Myproject;
import java.util.*;
public class Sorting implements Comparator<Edge> {
    
    public int compare(Edge e1,Edge e2)
    {
        if(e1.weight>=e2.weight)
            return 1;
        else return -1;
    }
    
}
