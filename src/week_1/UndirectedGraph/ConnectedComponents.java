package week_1.UndirectedGraph;

import edu.princeton.cs.algs4.Graph;

//Use DFS to find connected components inside a graph
public class ConnectedComponents {

    private boolean[] marked;
    private int[] id; //each vertex inside a cc has same id[];
    private int count = 0;

    public void findConnectedComponent(Graph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for(int v = 0; v < g.V(); v++){
            dfs(g, v); // After this dfs function finished executing, all possible connected vertexes of v are handled.
            count++; // So renew count for next connected components
        }
    }

    public void dfs(Graph g, int v){
        marked[v] = true;
        id[v] = count;
        for(int w : g.adj(v)){
            if(!marked[w]){
                dfs(g, w);
            }
        }
    }

}
