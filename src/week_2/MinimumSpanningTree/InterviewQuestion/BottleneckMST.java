package week_2.MinimumSpanningTree.InterviewQuestion;

import edu.princeton.cs.algs4.*;

import java.util.Iterator;

/**
 * Based on LazyPrimMST's implementation.
 */
public class BottleneckMST {

    private static final double FLOATING_POINT_EPSILON = 1E-12;
    private int bottleneck;
    private MinPQ<Edge> minPQ;
    private Queue<Edge> mst;
    private boolean[] marked;
    private double weight;       // total weight of MST

//    Bottleneck minimum spanning tree. Given a connected edge-weighted graph, design an efficient algorithm to find
//    a minimum bottleneck spanning tree. The bottleneck capacity of a spanning tree is the weights of its largest edge.
//    A minimum bottleneck spanning tree is a spanning tree of minimum bottleneck capacity.
    public BottleneckMST(EdgeWeightedGraph G){

        marked = new boolean[G.V()];

        Iterable<Edge> edges = G.edges();
        Iterator<Edge> iterator = edges.iterator();

        bottleneck = Integer.MIN_VALUE;
        minPQ = new MinPQ<>();
        while(iterator.hasNext()){
            Edge next = iterator.next();
            minPQ.insert(next);
            if(next.weight() > bottleneck){
                bottleneck = (int) next.weight();
            }
        }

        for(int v = 0; v < G.V(); v++){
            if(!marked[v]){
                prim(G, v);
            }
        }

        assert check(G);
    }

    private void prim(EdgeWeightedGraph G, int s){

        scan(G, s);

        while(mst.size() > G.V() - bottleneck){ // The key.
            Edge edge = minPQ.delMin();
            int v = edge.either();
            int w = edge.other(v);
            assert marked[v] || marked[w];
            if(marked[v] && marked[w]){
                continue;
            }
            mst.enqueue(edge);
            weight += edge.weight();
            if(!marked[v]){
                scan(G, v);
            }

            if(!marked[w]){
                scan(G, w);
            }
        }
    }


    private void scan(EdgeWeightedGraph G, int s){
        assert !marked[s];
        marked[s] = true;
        for(Edge e : G.adj(s)){
            if(!marked[e.other(s)]){
                minPQ.insert(e);
            }
        }
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // check weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

    /**
     * Unit tests the {@code LazyPrimMST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }

}
