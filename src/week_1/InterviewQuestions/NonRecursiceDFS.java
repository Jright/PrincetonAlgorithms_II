package week_1.InterviewQuestions;


import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

public class NonRecursiceDFS {

    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public NonRecursiceDFS(Graph G, int s){
        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);
        while(!stack.isEmpty()){
            Integer peek = stack.peek();
            for(Integer adj : G.adj(peek)){
                if(!marked[adj]){
                    marked[adj] = true;
                    stack.push(adj);
                }
            }
            stack.pop();
        }
    }
}
