package clone_la_ban_dictionary;

import java.net.URL;
import java.net.URLConnection;
import java.io.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import clone_la_ban_dictionary.model.*;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Clone {

    int time = 1;
    public Map<String, Word> wordCheck = new HashMap<String, Word>();

    Map<String, String> nearWords = new HashMap<>();

    private void readLastWord() {
        try {
            Gson gson = new Gson();
            String word = readFile("word/word.txt");
            HashMap map = gson.fromJson(word, HashMap.class);
            wordCheck.putAll(map);
            System.out.println(wordCheck.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startClone() throws IOException {
        System.out.println("=====================READ WORD CLONED=====================");
        //readLastWord();
        System.out.println("=====================START CLONE=====================");
        System.out.println(nearWords.size());
        for (int i = 0; i <= 'z' - 'a'; i++) {
            for (int j = 0; j <= 'z' - 'a'; j++) {
                getSuggestion(((char) ('z' - i)) + "" + (char) ('z' - j));
            }
        }
        for (int i = 0; i <= 'z' - 'a'; i++) {
            for (int j = 0; j <= 'z' - 'a'; j++) {
                for (int z = 0; z <= 'z' - 'a'; z++) {
                    getSuggestion(((char) (i + 'a')) + "" + (char) (j + 'a') + "" + (char) (z + 'a'));
                }
            }
        }
        System.out.println("=====================CLONE SUCCESS=====================");
    }

    private void getSuggestion(String word) throws IOException {
        HttpGet httpGet = new HttpGet("https://dict.laban.vn/ajax/autocomplete?type=1&site=dictionary&query=" + word);
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
        Gson g = new Gson();
        Response response = g.fromJson(content, Response.class);
        for (Suggestion suggestion : response.suggestions) {
            if (!wordCheck.containsKey(suggestion.select)) {
                try {
                    cloneWord(suggestion.select);
                } catch (Exception e) {
                    writeToFile(time);
                    time++;
                }
            }
        }
        Set<String> strings = new HashSet<>(nearWords.keySet());
        for (String nearWord : strings) {
            if (!wordCheck.containsKey(nearWord)) {
                try {
                    cloneWord(nearWord);
                } catch (Exception e) {
                    writeToFile(time);
                    time++;
                }
            }
        }
        nearWords.clear();
    }

    private String fixSpace(String word) {
        return word.replace(",", "%2C")
                .replace(" ", "+")
                .replace("'", "%27");
    }

    private void cloneWord(String word) throws IOException {
        String fixWord = fixSpace(word);
        HttpGet httpGet = new HttpGet("https://dict.laban.vn/find?type=1&query=" + fixWord);
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");
        System.out.print("clone " + word + " with fix word: " + fixWord + ": ");
        Word obj = getData(word, document);
        if (obj != null) {
            System.out.print(" clone success\n");
            wordCheck.put(word, obj);
            writeToTempFile(obj);
            System.out.println("Word count: " + wordCheck.size());
        } else {
            System.out.print("\n");
        }
        getNearWord(document);
    }

    private void getNearWord(Document document) {
        Elements elements = document.select("a");
        for (Element element : elements) {
            if (element.attributes().get("style").equals("color:#1198B6")) {
                nearWords.put(element.text(), "");
            }
        }
    }

    private void writeToTempFile(Word word) throws IOException {
        File file = new File("word/word_temp.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        Gson gson = new Gson();
        bw.write(gson.toJson(word) + ",");
        bw.close();
        fw.close();
        System.out.println("Xong");

    }

    private Word getData(String findWord, Document document) {
        Element firstDiv = document.select("div#content_selectable.content").first();
        if (firstDiv != null) {
            try {
                Word word = readWord(firstDiv);
                word.word = findWord;
                setUS(word, document);
                setUK(word, document);
                return word;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

    private void setUS(Word word, Document document) throws IOException {
        Element element = document.select("span.color-orange").first();
        if (element != null) {
            word.us = element.text();
            getLinkMp3(word, fixSpace(word.word), "us");
        }
    }

    private void getLinkMp3(Word word, String url, String sk) throws IOException {
        HttpGet httpGet = new HttpGet("https://dict.laban.vn/ajax/getsound?accent=" + sk + "&word=" + url);
        HttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
        Gson gson = new Gson();
        MediaResponse mediaResponse = gson.fromJson(content, MediaResponse.class);
        if (mediaResponse.data != null && !"".equals(mediaResponse.data)) {
            if (sk.equals("us")) {
                word.usMp3 = cloneMP3(sk, url, mediaResponse.data);
            } else {
                word.ukMp3 = cloneMP3(sk, url, mediaResponse.data);
            }
        }
    }

    private void setUK(Word word, Document document) throws IOException {
        Element element = document.select("span.color-black").first();
        if (element != null) {
            word.uk = element.text();
            getLinkMp3(word, fixSpace(word.word), "uk");
        }
    }

    private void writeToFile(Integer time) throws IOException {
        System.out.println("Start write to file : " + time);
        Gson gson = new Gson();
        String wordJson = gson.toJson(wordCheck);
        OutputStream outStream = new FileOutputStream(new File("word/word " + time + ".txt"));
        outStream.write(wordJson.getBytes());
        System.out.println("End write to file : " + time);
    }

    private Word readWord(Element firstDiv) {
        Word word = new Word();
        word.description = firstDiv.html().replaceAll("\n", "").replaceAll("href=\"javascript:void\\(0\\);\"", "");
        return word;
    }

    private String cloneMP3(String sk, String word, String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        InputStream is = conn.getInputStream();
        String mp3Name = "mp3/" + sk + "/" + word + ".mp3";
        OutputStream outStream = new FileOutputStream(new File(mp3Name));
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > 0) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        System.out.println("clone mp3 " + sk + " : " + word + " success");
        return mp3Name;
    }


    public String readFile(String url) throws IOException {
        return new String(Files.readAllBytes(Paths.get(url)),
                StandardCharsets.UTF_8);
    }
}
