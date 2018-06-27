package week_1.DirectedGraph;

public class DFSofDirectedGraph {

    public boolean[] marked;

    public DFSofDirectedGraph(DirectedGraph dg, int v) {
        marked = new boolean[dg.V()];
        DFS(dg, v);
    }


    public void DFS(DirectedGraph dg, int v) {
        marked[v] = true;
        for (int adj : dg.adj(v)) {
            if(!marked[adj]){
                DFS(dg, adj);
            }
        }
    }

    public boolean visited(int v){
        return marked[v];
    }
}
