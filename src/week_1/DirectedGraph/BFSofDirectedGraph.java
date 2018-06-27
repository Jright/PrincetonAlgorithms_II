package week_1.DirectedGraph;

import edu.princeton.cs.algs4.Stack;

public class BFSofDirectedGraph {

    public boolean[] marked;

    public BFSofDirectedGraph(DirectedGraph dg, int v){
        marked = new boolean[dg.V()];
        BFS(dg, v);
    }

    public void BFS(DirectedGraph dg, int v){
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        marked[v] = true;

        while(!stack.isEmpty()){
            Integer pop = stack.pop();
            for(int adj : dg.adj(pop)){
                if(!marked[adj]){
                    marked[adj] = true;
                    stack.push(adj);
                }
            }
        }
    }

}
