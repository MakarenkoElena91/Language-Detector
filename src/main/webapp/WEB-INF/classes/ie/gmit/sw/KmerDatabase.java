package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class KmerDatabase implements Storagable<Kmer> {
    private static final int LIMIT = 300;
    private Map<Kmer, Kmer> db;

    public KmerDatabase(String text, int kmerSize) {
        this.db = new ConcurrentHashMap<>();
        createDb(text, kmerSize);
    }

    public KmerDatabase(Kmer kmer) {
        this.db = new ConcurrentHashMap<>();
        add(kmer);
    }

    /**
     * Adds kmers to database
     * @param kmer object consists of a kmer itself and its frequency
     */
    @Override
    public void add(Kmer kmer) {
        if (db.containsKey(kmer)) {
            db.get(kmer).increaseFrequency();
        } else {
            db.put(kmer, kmer);
        }
    }

    /**
     * Calculates out of place metric distance
     * @param query - kmers and their frequencies
     * @return distance = index/rank of a kmer in language database list - index/rank of a kmer in query list
     */
    public int getDistance(KmerDatabase query) {
        int distance = 0;

        List<Kmer> queryValues = query.getSortedValues();
        List<Kmer> dbValues = this.getSortedValues();

        for (int i = 0; i < queryValues.size(); i++) {
            Kmer kmer = queryValues.get(i);
            if (db.containsKey(kmer)) {
                // TODO: fix it
                distance += dbValues.indexOf(kmer) - i;
            } else {
                distance += dbValues.size() + 1;
            }
        }
        return distance;
    }

    /**
     * Sorts values in List of kmers
     * @return a List of reversed sorted kmers according to their frequency (the most frequent at top), the list contains 300 entries
     */
    private List<Kmer> getSortedValues() {
        return db.keySet().stream()
                .sorted(Comparator.comparingInt(kmer -> kmer.getFrequency() * -1))
                .limit(LIMIT)
                .collect(Collectors.toList());
    }

    /**
     * Creates a kmer database, each Kmer object contains kmer/ngram text and its kmerSize
     * @param text - query text the language of which needs to be guessed
     * @param kmerSize - size of each kmer/ngram
     */
    private void createDb(String text, int kmerSize) {

        for (int i = 0; i <= text.length() - kmerSize; i++) {
//            TODO: LIMIT CHARSEQUENCE TO 400???
//            if (text.length() <= 400) {
                String kmerString = (text.substring(i, i + kmerSize));
                Kmer kmer = new Kmer(kmerString);

                if (db.containsKey(kmer)) {
                    db.get(kmer).increaseFrequency();
                    db.put(kmer, kmer);
                } else {
                    db.put(kmer, kmer);
//                }
            }
        }
    }
}
