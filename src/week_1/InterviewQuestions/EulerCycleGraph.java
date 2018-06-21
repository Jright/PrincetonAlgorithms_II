package week_1.InterviewQuestions;


import java.util.LinkedList;
import java.util.ListIterator;

//Euler cycle. An Euler cycle in a graph is a cycle (not necessarily simple) that uses every edge in the graph exactly one.
//        Show that a connected graph has an Euler cycle if and only if every vertex has even degree.
//        Design a linear-time algorithm to determine whether a graph has an Euler cycle, and if so, find one.
public class EulerCycleGraph {

    private int V;
    private LinkedList<Integer> adj[];

    public EulerCycleGraph(int v){
        V = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; ++i){
            adj[i] = new LinkedList<>();
        }
    }

    //Undirected graph
    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
    }


    public void DFSUtil(int v, boolean visited[]){
        visited[v] = true;

        ListIterator<Integer> integerListIterator = adj[v].listIterator();
        while(integerListIterator.hasNext()){
            int n = integerListIterator.next();
            if(!visited[n]){
                DFSUtil(n, visited);
            }
        }
    }

    public boolean isConnected(){
        boolean[] visited = new boolean[V];
        int i;
        for(i = 0; i < V; i++){
            visited[i] = false;
        }

        for(i = 0; i < V; i++){
            if(adj[i].size() != 0){
                break;
            }
        }

        if(i == V){
            return true;
        }

        DFSUtil(i, visited);
        for(i = 0; i < V; i++){
            if(visited[i] == false && adj[i].size() > 0){
                return false;
            }
        }
        return true;
    }

    public int isEulerian(){
        if(isConnected() == false){
            return 0;
        }

        int odd = 0;
        for(int i = 0; i < V; i++){
            if(adj[i].size() % 2 != 0){
                odd++;
            }
        }

        if(odd > 2){
            return 0;
        }

        return (odd == 2) ? 1 : 2;
    }

    // Function to run test cases
    void test()
    {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
            System.out.println("graph has a Euler cycle");
    }

    // Driver method
    public static void main(String args[])
    {
        // Let us create and test graphs shown in above figures
        EulerCycleGraph g1 = new EulerCycleGraph(5);
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.test();

        EulerCycleGraph g2 = new EulerCycleGraph(5);
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
        g2.test();

        EulerCycleGraph g3 = new EulerCycleGraph(5);
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(1, 3);
        g3.test();

        // Let us create a graph with 3 vertices
        // connected in the form of cycle
        EulerCycleGraph g4 = new EulerCycleGraph(3);
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        g4.test();

        // Let us create a graph with all veritces
        // with zero degree
        EulerCycleGraph g5 = new EulerCycleGraph(3);
        g5.test();
    }

}
