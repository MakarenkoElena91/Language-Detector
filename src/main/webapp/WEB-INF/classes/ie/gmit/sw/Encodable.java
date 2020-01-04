package ie.gmit.sw;

public interface Encodable {
    default long encode (String kmer) throws Exception {
        long sequence = 0x0000000000000000l;//16
        if (kmer.length() < 1 || kmer.length() > 4) throw new Exception("Can only encode ngrams with 1, 2, 3, or 4 characters");
        for (int i = 0; i < kmer.length(); i++){
            sequence <<=16;
            sequence |= kmer.charAt(i);
        }
        return sequence;
    }
}
