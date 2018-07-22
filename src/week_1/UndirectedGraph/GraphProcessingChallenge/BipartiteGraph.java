package week_1.UndirectedGraph.GraphProcessingChallenge;

import edu.princeton.cs.algs4.Graph;

public class BipartiteGraph {

    private boolean[] marked;
    private boolean[] color;
    private boolean isBipartite = false;


    public boolean graphBipartite(Graph g){
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for(int v = 0; v < g.V(); v++){
            if(!marked[v]){
                dfs(g, v);
            }
        }
        return isBipartite;
    }

    public void dfs(Graph g, int v){
        marked[v] = true;

        for(int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w);
                color[w] = !color[v];
            }else if(color[w] == color[v]){
                isBipartite = false;
            }
        }
    }
}
