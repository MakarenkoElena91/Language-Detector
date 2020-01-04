package ie.gmit.sw;

public class LanguageEntry {
    private String kmer;
    private Language language;

    public LanguageEntry(String kmer, Language language) {
        this.kmer = kmer;
        this.language = language;
    }

    public String getKmer() {
        return kmer;
    }

    public Language getLanguage() {
        return language;
    }
}
