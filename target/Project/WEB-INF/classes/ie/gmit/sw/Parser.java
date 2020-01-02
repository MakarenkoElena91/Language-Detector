package ie.gmit.sw;

import ie.gmit.sw.models.LanguageEntry;

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

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] record = line.trim().split("@");
                if (record.length != 2) continue;
                record[0] = line.replaceAll("\\p{P}", "").toLowerCase(); // TODO: do filtering
                parseDB(record[0], record[1]);//text, lang
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseDB(String text, String lang) {
        Language language = Language.valueOf(lang);
        for (int i = 0; i <= text.length() - kmerSize; i++) {
            String kmer = text.substring(i, i + kmerSize);
            LanguageEntry languageEntry = new LanguageEntry(kmer, language);
            db.add(languageEntry);
        }
    }
}