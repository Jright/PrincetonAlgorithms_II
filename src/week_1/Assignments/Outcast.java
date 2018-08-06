package week_1.Assignments;

public class Outcast {

    WordNet wordNet;
    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int[] distances = new int[nouns.length];

        for(int i = 0; i < nouns.length; i++){
            for(int j = i; j < nouns.length; j++){
                int dist = wordNet.distance(nouns[i], nouns[j]);
                distances[i] += dist;
                if(i != j){
                    distances[j] += dist;
                }
            }
        }

        int maxDistance = 0;
        int maxIndex = 0;
        for(int i = 0; i < distances.length; i++){
            if(distances[i] > maxDistance){
                maxDistance = distances[i];
                maxIndex = i;
            }
        }

        return nouns[maxIndex];
    }

    // see test client below
    public static void main(String[] args) {

    }
}
