# Language-Detector
Features added:
1. Interface Storagable with Generic Type <T>
Each class that implements this interface specifies the type:
  
class LanguageDatabase implements Storagable<LanguageEntry>
  
class KmerDatabase implements Storagable<Kmer> 
Both classes add kmers but LanguageDatabase apart from kmers has a language as well.
  
2. Singleton pattern for both databases is used.
3. Delegation to LanguageDatabase in Parser.
4. Dependencies:

- LanguageEntry HAS A Language.
- KmerDatabase HAS A map of Kmers
- LanguageDatabase HAS A map of Language AND KmerDatabase

5. All special symbols are removed both from subject and query texts, all 
letters are lower case in order to decrease "noise" in prediction.

