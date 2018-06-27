package week_1.DirectedGraph;

import edu.princeton.cs.algs4.Stack;

public class TopologicalSort {
    public boolean[] marked;
    public Stack<Integer> reversePost;

    public TopologicalSort(DirectedGraph dg, int v) {
        marked = new boolean[dg.V()];
        reversePost = new Stack<>();
        DFS(dg, v);
    }

    public void DFS(DirectedGraph dg, int v) {
        marked[v] = true;

        for (int adj : dg.adj(v)) {
            if (!marked[adj]) {
                marked[adj] = true;
            }
        }
        reversePost.push(v);
    }

    //Return all vertices in reverse DFS postorder.
    public Iterable<Integer> reversePost(){
        return reversePost;
    }

}
