package week_1.InterviewQuestions;


//Shortest directed cycle.
//        Given a digraph G, design an efficient algorithm to find a directed cycle with the minimum number of edges (or report that the graph is acyclic).
//        The running time of your algorithm should be at most proportional to V(E + V)
//        and use space proportional to E + V, where V is the number of vertices and E is the number of edges.


import edu.princeton.cs.algs4.*;

public class ShortestDirectedCycle {

//    ShortestDirectedCycle.java
//
//    Below is the syntax highlighted version of ShortestDirectedCycle.java from §4.2 Directed Graphs.
//

    /******************************************************************************
     *  Compilation:  javac ShortestDirectedCycle.java
     *  Execution:    java DirectedCycle < input.txt
     *  Dependencies: Digraph.java BreadthFirstDirectedPaths.java Stack.java StdOut.java In.java
     *  Data files:   https://algs4.cs.princeton.edu/42digraph/tinyDG.txt
     *                https://algs4.cs.princeton.edu/42digraph/tinyDAG.txt
     *
     *  Finds a shortest directed cycle in a digraph.
     *  Runs in O(V * (E + V)) time.
     *
     *  % java ShortestDirectedCycle tinyDG.txt
     *  Shortest directed cycle: 2 3 2
     *
     *  %  java ShortestDirectedCycle tinyDAG.txt
     *  No directed cycle
     *
     ******************************************************************************/

        private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)
        private int length;

        public ShortestDirectedCycle(Digraph G) {
            Digraph R = G.reverse();
            length = G.V() + 1;//The possible maximum length of a path
            for (int v = 0; v < G.V(); v++) {
                //In order to check whether there exist some cycle, reverse the digraph and check every bfs of each verticies of the reverse digraph
                BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(R, v);
                for (int w : G.adj(v)) {
                    //bfs has path to w in reversed digraph, so there is a cycle between v and w. also we have to check whether it is the shortest cycle.
                    if (bfs.hasPathTo(w) && (bfs.distTo(w) + 1) < length) {
                        length = bfs.distTo(w) + 1;
                        cycle = new Stack<>();
                        for (int x : bfs.pathTo(w))
                            cycle.push(x);
                        cycle.push(v);
                    }
                }
            }
        }


        public boolean hasCycle()        { return cycle != null;   }
        public Iterable<Integer> cycle() { return cycle;           }
        public int length()              { return length;          }

        public static void main(String[] args) {
            Digraph G;
            if (args.length == 1) {
                In in = new In(args[0]);
                G = new Digraph(in);
            }
            else {
                int V = Integer.parseInt(args[0]);
                int E = Integer.parseInt(args[1]);
                G = DigraphGenerator.simple(V, E);
            }

            ShortestDirectedCycle finder = new ShortestDirectedCycle(G);
            if (finder.hasCycle()) {
                StdOut.print("Shortest directed cycle: ");
                for (int v : finder.cycle()) {
                    StdOut.print(v + " ");
                }
                StdOut.println();
            }

            else {
                StdOut.println("No directed cycle");
            }
        }

    }


//    Copyright © 2000–2017, Robert Sedgewick and Kevin Wayne.
//    Last updated: Fri Oct 20 12:50:46 EDT 2017.

