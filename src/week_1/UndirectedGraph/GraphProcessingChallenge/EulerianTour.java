package week_1.UndirectedGraph.GraphProcessingChallenge;

import edu.princeton.cs.algs4.Graph;

import java.util.HashMap;
import java.util.List;

//TODO Untested
//Find a (general) cycle that uses every edge exactly once.
public class EulerianTour {

    private boolean[] marked;
    private HashMap<Integer, List<Integer>> edgeVisited;
    private List<Integer> eulerianPath;

    public EulerianTour(Graph g){
        if(g.V() > 0){
            eulerianTourDFS(g, 0);
        }else{
            throw new IllegalArgumentException("Cannot process a graph with 0 vertex.");
        }
    }

    private void eulerianTourDFS(Graph g, int v){
        eulerianPath.add(v);
        marked[v] = true;
        for(int w : g.adj(v)){
            if(marked[w]){
                List<Integer> list = edgeVisited.get(w);
                if(!list.contains(v)){
                    eulerianTourDFS(g, w);
                    list.add(v);
                }
            }else{
                eulerianTourDFS(g, w);
            }
        }
    }



}
