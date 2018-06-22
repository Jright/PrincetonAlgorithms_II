package week_1.DirectedGraph;

import edu.princeton.cs.algs4.Bag;

public class DirectedGraph {

    private final int V;
    private final Bag<Integer>[] adj;

    public DirectedGraph(int V){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];

        for(int v = 0; v < V; v++){
            adj[v] = new Bag<>();
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
//        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }
}
