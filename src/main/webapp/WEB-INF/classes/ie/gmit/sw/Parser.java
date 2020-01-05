package ie.gmit.sw;

import java.io.*;

public class Parser implements Runnable {
    private File file;
    private int kmerSize;
    private static LanguageDatabase db;

    public Parser(File file, int k) {
        this.file = file;
        this.kmerSize = k;
    }

    public void setDb(LanguageDatabase db) {
        this.db = db;
    }

    /**
     * reads fi;e, splits each line at the last @ symbol into text and language the text is written in
     * all punctuation is removed, all letters are converted to lowercase
     *
     */
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;

            while ((line = br.readLine()) != null) {
                String[] record = line.trim().split("@");
                if (record.length != 2) continue;
                record[0] = line.replaceAll("\\p{P}", "").toLowerCase();
                parseDB(record[0], record[1]);//text, lang
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * texts and languages are parsed in run method are added to languageEntry
     * @param text is split according to kmer size with a shift of 1
     * @param lang the language after @ symbol in file is compared to language in Language enum, they have to be exactly the same
     */
    private void parseDB(String text, String lang) {
        Language language = Language.valueOf(lang);
        for (int i = 0; i <= text.length() - kmerSize; i++) {
            String kmer = text.substring(i, i + kmerSize);
            LanguageEntry languageEntry = new LanguageEntry(kmer, language);
            db.add(languageEntry);
        }
    }
}