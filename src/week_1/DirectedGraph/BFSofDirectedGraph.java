package week_1.DirectedGraph;

import edu.princeton.cs.algs4.LinkedQueue;

public class BFSofDirectedGraph {

    public boolean[] marked;

    public BFSofDirectedGraph(DirectedGraph dg, int v){
        marked = new boolean[dg.V()];
        BFS(dg, v);
    }

    public void BFS(DirectedGraph dg, int v){
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(v);
        marked[v] = true;

        while(!linkedQueue.isEmpty()){
            Integer pop = linkedQueue.dequeue();
            for(int adj : dg.adj(pop)){
                if(!marked[adj]){
                    marked[adj] = true;
                    linkedQueue.enqueue(adj);
                }
            }
        }
    }

}
