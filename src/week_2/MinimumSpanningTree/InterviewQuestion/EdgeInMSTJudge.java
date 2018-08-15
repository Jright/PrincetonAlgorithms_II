package week_2.MinimumSpanningTree.InterviewQuestion;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SET;

/**
 * Is an edge in a MST. Given an edge-weighted graph GG and an edge ee, design a linear-time algorithm to determine whether ee appears in some MST of GG.
 *
 * Note: Since your algorithm must take linear time in the worst case, you cannot afford to compute the MST itself.
 *
 */

//TODO: Note，it is possible that this Graph is splited into two or more separated graphs. So it is possible to have minimum spanning FOREST.
public class EdgeInMSTJudge {

    private EdgeWeightedGraph GG;
    private Edge ee;

    public EdgeInMSTJudge(EdgeWeightedGraph GG, Edge ee){
        this.GG = GG;
        this.ee = ee;
    }

    private boolean isEdgeInMST(EdgeWeightedGraph G, Edge e){
        //This algorithm is good enough. I think no need to check if this graph is acyclic.
        SET<Integer> vertices = new SET<Integer>();

        double weight = e.weight();


        for (Edge edge: G.edges()) {
            if (edge.weight() < weight) { // So edge e's two vertices will enter to SET if there's less weighted edge between them.
                int v = edge.either();
                int w = edge.other(v);
                vertices.add(v); vertices.add(w);
            }
        }
        int v = e.either();
        int w = e.other(v);
        if (vertices.contains(v) && vertices.contains(w)){
            return false;
        }
        return true;
    }

}
