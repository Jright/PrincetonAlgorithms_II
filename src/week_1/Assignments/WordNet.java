package week_1.Assignments;


import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.LinkedQueue;


import java.util.ArrayList;
import java.util.List;

public class WordNet {

    private class Noun implements Comparable<Noun> {
        private String noun;

        private ArrayList<Integer> ids ;

        public Noun(String noun) {
            this.noun = noun;
            ids = new ArrayList<>();
        }

        @Override
        public int compareTo(Noun that) {
            return this.noun.compareTo(that.noun);
        }

        public List<Integer> getIds() {
            return this.ids;
        }

        public void addId(Integer x) {
            this.ids.add(x);
        }
    }

    private SET<Noun> nounSET;
    private SAP sap;
    private Digraph G;
    private ArrayList<String> idList;


    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        In inSynsets = new In(synsets);
        In inHyperNums = new In(hypernyms);
        int maxVertex = 0;
        idList = new ArrayList<>();
        nounSET = new SET<>();

        String line = inSynsets.readLine();

        while(line != null){
            maxVertex++;
            String[] synsetLine = line.split(",");
            int id = Integer.parseInt(synsetLine[0]);
            String[] nounSet = synsetLine[1].split(" ");
            for(String nounName : nounSet){
                Noun noun = new Noun(nounName);
                if(nounSET.contains(noun)){
                    noun = nounSET.ceiling(noun);
                    noun.addId(id);
                }else{
                    noun.addId(id);
                    nounSET.add(noun);
                }
            }
            idList.add(synsetLine[1]);
            line = inSynsets.readLine();
        }

        G = new Digraph(maxVertex);
        boolean[] isNotRoot = new boolean[maxVertex];
        line = inHyperNums.readLine();

        while(line != null){
            String[] hypernumsLine = line.split(",");
            int v = Integer.parseInt(hypernumsLine[0]);
            isNotRoot[v] = true;

            for(int i = 1; i < hypernumsLine.length; i++){
                G.addEdge(v, Integer.parseInt(hypernumsLine[i]));
            }
            line = inHyperNums.readLine();
        }

        DirectedCycle dC = new DirectedCycle(G);
        if(dC.hasCycle()){
            throw new IllegalArgumentException();
        }

        int rootCount = 0;
        for(boolean notRoot : isNotRoot){
            if(!notRoot){
                rootCount++;
            }
        }

        if(rootCount > 1){
            throw new IllegalArgumentException();
        }
        sap = new SAP(G);
    }

    // returns all WordNet nouns
        public Iterable<String> nouns(){
            LinkedQueue<String> nouns = new LinkedQueue<>();
            for(Noun noun : nounSET){
                nouns.enqueue(noun.noun);
            }
            return nouns;
        }

    // is the word a WordNet noun?
        public boolean isNoun(String word){
            Noun noun = new Noun(word);
            return nounSET.contains(noun);
        }

    // distance between nounA and nounB (defined below)
        public int distance(String nounA, String nounB){
            if(!isNoun(nounA) || !isNoun(nounB)){
                throw new IllegalArgumentException();
            }

            Noun nodeA = nounSET.ceiling(new Noun(nounA));
            Noun nodeB = nounSET.ceiling(new Noun(nounB));
            return sap.length(nodeA.getIds(),nodeB.getIds());
        }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
        public String sap(String nounA, String nounB){
            if(!isNoun(nounA) || !isNoun(nounB)){
                throw new IllegalArgumentException();
            }

            Noun nodeA = nounSET.ceiling(new Noun(nounA));
            Noun nodeB = nounSET.ceiling(new Noun(nounB));

            int ancestor = sap.ancestor(nodeA.getIds(), nodeB.getIds());
            return idList.get(ancestor);

        }

    // do unit testing of this class
        public static void main(String[] args){

        }
}
