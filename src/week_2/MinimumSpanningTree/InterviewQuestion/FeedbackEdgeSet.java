package week_2.MinimumSpanningTree.InterviewQuestion;

/**
 * Minimum-weight feedback edge set.
 * A feedback edge set of a graph is a subset of edges that contains at least one edge from every cycle in the graph.
 * If the edges of a feedback edge set are removed, the resulting graph is acyclic.
 * Given an edge-weighted graph, design an efficient algorithm to find a feedback edge set of minimum weight.
 * Assume the edge weights are positive.
 */
public class FeedbackEdgeSet {


}


/*
*
*  public Queue<Edge> MFES(EdgeWeightedGraph G) {
        MaxPQ<Edge> pq = new MaxPQ<Edge>();
        Queue<Edge> mfes = new Queue<Edge>();
        int size = 0;

        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty()) {
            Edge e = pq.delMax();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
            }
            else {
                mfes.enqueue(e);
            }
        }
        return mfes;

    }
*
* */