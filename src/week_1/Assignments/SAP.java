package week_1.Assignments;

import edu.princeton.cs.algs4.Digraph;

public class SAP {

    private final Digraph G;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = G;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        int[] ints = shortestLengthAndAncestor(v, w);
        return ints[0];
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        int[] ints = shortestLengthAndAncestor(v, w);
        return ints[1];
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        int[] ints = shortestLengthAndAncestor(v, w);
        return ints[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        int[] ints = shortestLengthAndAncestor(v, w);
        return ints[1];
    }

    // do unit testing of this class
    public static void main(String[] args) {

    }

    private int[] shortestLengthAndAncestor(int v, int w){
        RecordedBFS vBfs = new RecordedBFS(G, v);
        RecordedBFS wBfs = new RecordedBFS(G, w);

        int shortestLength = Integer.MAX_VALUE;
        int minAncestor = Integer.MAX_VALUE;

        int[] answer = new int[2];
        answer[0] = shortestLength;
        answer[1] = minAncestor;

        boolean[] vMarked = vBfs.getMarked();
        boolean[] wMarked = wBfs.getMarked();

        for(int i = 0; i < vMarked.length; i++){
            if(vMarked[i] && wMarked[i]){
                int pathLength = vBfs.distTo(i) + wBfs.distTo(i);
                if(pathLength < shortestLength){
                    shortestLength = pathLength;
                    minAncestor = i;
                }
            }
        }

        if(shortestLength == Integer.MAX_VALUE){
            answer[0] = -1;
            answer[1] = -1;
            return answer;
        }

        answer[0] = shortestLength;
        answer[1] = minAncestor;
        return answer;
    }

    private int[] shortestLengthAndAncestor(Iterable<Integer> vSources, Iterable<Integer> wSources){
        int shortestLength = Integer.MAX_VALUE;
        int shortestAncestor = Integer.MAX_VALUE;

        int[] answer = new int[2];
        for(int v : vSources){
            for(int w : wSources){
                int[] tempAnswer = shortestLengthAndAncestor(v, w);
                if(tempAnswer[0] < shortestLength){
                    shortestLength = tempAnswer[0];
                    shortestAncestor = tempAnswer[1];
                }

            }
        }

        if(shortestLength == Integer.MAX_VALUE){
            answer[0] = -1;
            answer[1] = -1;
            return answer;
        }

        answer[0] = shortestLength;
        answer[1] = shortestAncestor;

        return answer;
    }


}
