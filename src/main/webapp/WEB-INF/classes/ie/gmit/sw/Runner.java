//package ie.gmit.sw;
//
//public class Runner {
//    public static final String FILE = "data/wili-2018-Edited.txt";
//    private static final int KMERSIZE = 2;
//
//    public static void main(String[] args) throws  Throwable {
//        Parser p = new Parser(FILE, KMERSIZE);
//        LanguageDatabase db = LanguageDatabase.getInstance();
//        p.setDb(db);
//        Thread t = new Thread(p);
//        t.start();
//        t.join();
////        db.resize((300));
////        System.out.println(db);
////        String text = "quick brown fox runs over the green forest";
////        String text = "ἐσήγαγον διδασϰάλια ἐς τοὺς ῞Ελληνας ϰαὶ δὴ";
////        String text = "духовенства и культовых сооружений но по некоторым опросам на начало 2015 года не по количеству верующих";
//        String text = "中國哲學書電子化計劃";
//         KmerDatabase query = new KmerDatabase(text, 2);
//
//        System.out.println(db.guessLanguage(query));
//
////        System.out.println(p.analyseQuery(text));
//    }
//
//}
