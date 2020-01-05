package ie.gmit.sw;
/*
LanguageEntry has a Language which contains a kmer and a language
 */
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
