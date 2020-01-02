package ie.gmit.sw.JH;

import java.util.Objects;

public class LanguageEntry implements Comparable<LanguageEntry> {
	private int kmer;
	private int frequency;
	private int rank;

	public LanguageEntry(int kmer, int frequency, int rank) {
		this.kmer = kmer;
		this.frequency = frequency;
		this.rank = rank;
	}

	public LanguageEntry(int kmer, int frequency) {
		this.kmer = kmer;
		this.frequency = frequency;
	}

    public LanguageEntry(int kmer) {
        this.kmer = kmer;
        this.frequency = 1;
    }

    public int getKmer() {
		return kmer;
	}

	public void setKmer(int kmer) {
		this.kmer = kmer;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public LanguageEntry increaseFrequency(){
	    frequency++;
	    return  this;
    }


	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(LanguageEntry next) {
		return - Integer.compare(frequency, next.getFrequency());
	}
	
	@Override
	public String toString() {
		return "[" + kmer + "/" + frequency + "/" + rank + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LanguageEntry that = (LanguageEntry) o;
		return kmer == that.kmer &&
				frequency == that.frequency &&
				rank == that.rank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(kmer, frequency, rank);
	}
}