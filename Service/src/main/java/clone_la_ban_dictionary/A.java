package clone_la_ban_dictionary;

import clone_la_ban_dictionary.model.Word;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

public class A {
    static HashMap<String, Word> wordHashMap = new HashMap<>();

    public static void main(String[] args) {
        Clone clone = new Clone();
        try {
            Gson gson = new Gson();
            String s = clone.readFile("word/word.txt");
            HashMap hashMap = gson.fromJson(s, HashMap.class);
            wordHashMap.putAll(hashMap);
            System.out.println(wordHashMap.size());


            String s2 = clone.readFile("word/word_temp.txt");
            List<Word> words = gson.fromJson(s2, new TypeToken<List<Word>>() {
            }.getType());
            HashMap temp = new HashMap();
            for (Word word : words) {
                temp.put(word.word, word);
            }
            wordHashMap.putAll(temp);

            System.out.println(wordHashMap.size());


            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void writeToFile() throws IOException {
        Gson gson = new Gson();
        String wordJson = gson.toJson(wordHashMap);
        OutputStream outStream = new FileOutputStream(new File("word/word.txt"));
        outStream.write(wordJson.getBytes());
    }
}
