package week_1.Assignments;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.Stack;

import java.util.Arrays;

public class RecordedBFS {
    private static final int INFINITY = Integer.MAX_VALUE;

    private boolean[] marked;
    private int[] edgeTo;//edgeTo[v] = last edge on shortest s -> v path
    private int[] distTo;//distTo[v] = shortest length of s -> v path

    public RecordedBFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        bfs(G, s);
    }

    public RecordedBFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        bfs(G, sources);
    }

    private void bfs(Digraph G, int v) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue(v);
        marked[v] = true;
        distTo[v] = 0;
        while (!linkedQueue.isEmpty()) {
            Integer dequeue = linkedQueue.dequeue();
            for (int w : G.adj(dequeue)) {
                if (!marked[w]) {
                    marked[dequeue] = true;
                    edgeTo[w] = dequeue;
                    distTo[w] = distTo[dequeue] + 1;
                    linkedQueue.enqueue(w);
                }
            }
        }
    }

    private void bfs(Digraph G, Iterable<Integer> sources) {
        LinkedQueue<Integer> linkedQueue = new LinkedQueue<>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            linkedQueue.enqueue(s);
        }
        while (!linkedQueue.isEmpty()) {
            Integer dequeue = linkedQueue.dequeue();
            for (int w : G.adj(dequeue)) {
                if (!marked[w]) {
                    marked[dequeue] = true;
                    edgeTo[w] = dequeue;
                    distTo[w] = distTo[dequeue] + 1;
                    linkedQueue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public boolean[] getMarked() {
        boolean[] returnMarked = Arrays.copyOf(marked, marked.length);
        return returnMarked;
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> shortestPathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        int x;
        Stack<Integer> pathStack = new Stack<>();
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            pathStack.push(x);
        }
        pathStack.push(x);
        return pathStack;
    }
}
