package week_1.UndirectedGraph.GraphProcessingChallenge;

import edu.princeton.cs.algs4.Graph;

public class UndirectedGraphDetectCycle {

    private boolean[] marked;
    private boolean hasCycle;

    public UndirectedGraphDetectCycle(Graph g){
        marked = new boolean[g.V()];
        for(int v = 0; v < g.V(); v++){
            if(!marked[v]){
                dfs(g, v, v);
            }
        }
    }

    public void dfs(Graph g, int v, int u){
        marked[v] = true;
        for(int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w, v);
            }else if(w != u){ // w is the 'son 'vertex of v, u is the 'father' vertex of w. So if w is visited, and w is not v's 'father' vertex, then a cycle exists.
                hasCycle = true;
            }
        }
    }
}
