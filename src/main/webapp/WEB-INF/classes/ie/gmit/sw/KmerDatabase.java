package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class KmerDatabase implements Storagable<Kmer> {
    private List<Kmer> kmerList;
    private Map<Kmer, Kmer> db;

    public KmerDatabase(String text, int kmerSize) {
        this.db = new ConcurrentHashMap<>();
        createDb(text, kmerSize);
    }

    public KmerDatabase(Kmer kmer) {
        this.db = new ConcurrentHashMap<>();
        add(kmer);
    }

    @Override
    public void add(Kmer kmer) {
        if(db.containsKey(kmer)){
            db.get(kmer).increaseFrequency();
        }
        else{
            db.put(kmer, kmer);
        }
    }

    public int getDistance(KmerDatabase query){
        int distance = 0;

        List<Kmer> queryValues = query.getSortedValues();
        List<Kmer> dbValues = this.getSortedValues();

        for( int queryKmerDistance = 0; queryKmerDistance < queryValues.size(); queryKmerDistance++){
            Kmer kmer = queryValues.get(queryKmerDistance);
            if(db.containsKey(kmer)){
                // TODO: fix it
                distance += dbValues.indexOf(kmer) - queryKmerDistance;
            }
            else{
                distance += dbValues.size()+1;
            }
        }
        return  distance;
    }

    private List<Kmer> getSortedValues(){
        return db.keySet().stream()
                .sorted(Comparator.comparingInt(kmer -> kmer.getFrequency() * -1))
                .limit(300)
                .collect(Collectors.toList());
    }



    private void createDb(String text, int kmerSize) {

        for (int i = 0; i <= text.length() - kmerSize; i++) {
            String kmerString = (text.substring(i, i + kmerSize));
            Kmer kmer = new Kmer(kmerString);

            if (db.containsKey(kmer)){
                db.get(kmer).increaseFrequency();
                db.put(kmer, kmer);
            }
            else{
                db.put(kmer, kmer);
            }
        }

//        return kmersMap.keySet().stream()
//                .sorted(Comparator.comparingInt(kmer -> kmer.getFrequency() * -1))
//                .collect(Collectors.toList());
    }

    private class OutOfPlaceMetric implements Comparable<OutOfPlaceMetric>{
        private Language lang;
        private int distance;

        public OutOfPlaceMetric(Language lang, int distance) {
            super();
            this.lang = lang;
            this.distance = distance;
        }

        public Language getLanguage() {
            return lang;
        }

        public int getAbsoluteDistance() {
            return Math.abs(distance);
        }

        @Override
        public int compareTo(OutOfPlaceMetric o) {
            return Integer.compare(this.getAbsoluteDistance(), o.getAbsoluteDistance());
        }

        @Override
        public String toString() {
            return "[lang=" + lang + ", distance=" + getAbsoluteDistance() + "]";
        }
    }
}
