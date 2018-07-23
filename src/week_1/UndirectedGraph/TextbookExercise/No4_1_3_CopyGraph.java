package week_1.UndirectedGraph.TextbookExercise;

import edu.princeton.cs.algs4.Graph;

public class No4_1_3_CopyGraph extends Graph{

    public No4_1_3_CopyGraph(int V) {
        super(V);
    }


    public void copy(Graph g){
        Graph newGraph = new Graph(g.V());
        for(int v = 0; v < g.V(); v++){
            Iterable<Integer> adj = g.adj(v);
            while(adj.iterator().hasNext()){
                newGraph.addEdge(v, adj.iterator().next());
            }
        }
    }


}
