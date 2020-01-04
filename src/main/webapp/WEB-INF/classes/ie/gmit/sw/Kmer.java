package ie.gmit.sw;

public class Kmer {
    private int kmer;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kmer)) return false;
        Kmer kmer1 = (Kmer) o;
        return kmer == kmer1.kmer;
    }

    @Override
    public int hashCode() {
        return kmer;
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
