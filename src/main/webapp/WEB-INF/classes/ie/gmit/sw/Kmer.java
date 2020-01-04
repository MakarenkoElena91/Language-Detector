package ie.gmit.sw;

public class Kmer {
    private long kmer;
    private String kmerString;
    private int frequency;


    public Kmer(String kmerString) {
        this.kmerString = kmerString;
        this.kmer = kmerString.hashCode();
        this.frequency = 1;
    }

    public int getFrequency() {
        return frequency;
    }

    public void increaseFrequency() {
        frequency++;
    }

    @Override
    public String toString() {
        return "Kmer{" +
                "kmerString='" + kmerString + '\'' +
                "frequency=" + frequency +
                ", kmer=" + kmer +
                '}';
    }
}
