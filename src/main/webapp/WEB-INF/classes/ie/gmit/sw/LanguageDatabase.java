package ie.gmit.sw;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class LanguageDatabase implements Storagable<LanguageEntry> {
    private Map<Language, KmerDatabase> languageDb;
    private static LanguageDatabase instance = new LanguageDatabase();

    private LanguageDatabase() {
        this.languageDb = new ConcurrentHashMap<>();
    }

    public static LanguageDatabase getInstance(){
        return instance;
    }

    public Language guessLanguage(KmerDatabase query){

        TreeSet<OutOfPlaceMetric> oopm = new TreeSet<>();

        Set<Language> languages = languageDb.keySet();
         for(Language l : languages){
             oopm.add(new OutOfPlaceMetric(l, languageDb.get(l).getDistance(query)));
         }

        return oopm.first().getLanguage();

    }

    @Override
    public void add(LanguageEntry languageEntry){
        Kmer kmer = new Kmer(languageEntry.getKmer());

        if(languageDb.containsKey(languageEntry.getLanguage())){
            KmerDatabase kmerDatabase = languageDb.get(languageEntry.getLanguage());
            kmerDatabase.add(kmer);
        }
        else{
            languageDb.put(languageEntry.getLanguage(), new KmerDatabase(kmer));
        }
    }
}
