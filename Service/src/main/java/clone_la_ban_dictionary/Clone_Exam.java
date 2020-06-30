package clone_la_ban_dictionary;

import clone_la_ban_dictionary.exam_model.*;
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

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Clone_Exam {
    int time = 3;
    static int index = 1;
    String laravel_session = "eyJpdiI6Im9RMVJsb0ZTOVZBRTZJOFZ2R3JhNGc9PSIsInZhbHVlIjoiM0N0dEw0c2lJdWd5QmFVanYybzdzTm1UekltQ3lsWEx3dVJUdll3NmJ6UytGUEtRQXlvMmlEdkxtMlZDdTZWaSIsIm1hYyI6ImY4OTM2YjkyMmVmOGI4NmQ1OTdlMWY0MzhjZGJjNTUwODVhM2ExM2Q4MDUxOGZiNTI4MzUxOWQyNzgzNzg1NTUifQ%3D%3D";
    String XSRF_TOKEN = "eyJpdiI6Inc3UCtxMXVKdzM4NlFjMDhVemhRQ2c9PSIsInZhbHVlIjoieUpMRUNFRWt3VUtMakNqUmxBUU9IeVNUVU5CcWVBWGxXODhtYnAyV3l0QktmM1FcL1VmamJkTkkwTnRKaDl3eVwvIiwibWFjIjoiNjI3Mzg0YWEwNjQ4YjM2YmEwMzJmYmNlNTAxY2QxYmMwZmYzNWZmYmEyNGIwMzAyNDJjYTliMTUxMGE0N2FmMiJ9";


    public void startClone() throws IOException {
        System.out.println("=====================READ WORD CLONED=====================");

        System.out.println("=====================START CLONE=====================");
        cloneExam();
        System.out.println("=====================CLONE SUCCESS=====================");
    }

    private void cloneExam() throws IOException {

        Exam exam = new Exam();
        exam.parts = new ArrayList<>();
        System.out.println("=====================START CLONE PART ONE=====================");
        Part partOne = clonePartOne();
        exam.parts.add(partOne);
        System.out.println("=====================START CLONE PART TWO=====================");
        Part partTwo = clonePartTwo();
        exam.parts.add(partTwo);
        System.out.println("=====================START CLONE PART THREE=====================");
        Part partThree = clonePartThree();
        exam.parts.add(partThree);
        System.out.println("=====================START CLONE PART FOUR=====================");
        Part partFour = clonePartFour();
        exam.parts.add(partFour);
        System.out.println("=====================START CLONE PART FIVE=====================");
        Part partFive = clonePartFive();
        exam.parts.add(partFive);
        System.out.println("=====================START CLONE PART SIX=====================");
        Part partSix = clonePartSix();
        exam.parts.add(partSix);
        System.out.println("=====================START CLONE PART SEVEN=====================");
        Part partSeven = clonePartSeven();
        exam.parts.add(partSeven);
        writeToFile(exam);
    }

    private Part clonePartOne() throws IOException {
        HttpGet httpGet = new HttpGet("https://toeic24.vn/exam/part-1?questionGroupNum=0");
        HttpClient client = HttpClients.createDefault();
        httpGet.addHeader("Cookie", "laravel_session=" + laravel_session + ";");
        httpGet.addHeader("Cookie", "XSRF-TOKEN=" + XSRF_TOKEN + ";");
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");

        Part partOne = new Part(1L, "Photo");
        cloneAnswerPartOne(partOne, document);
        cloneImagePartOne(partOne, document);
        cloneMp3PartOne(partOne, document);
        return partOne;
    }

    private void cloneImagePartOne(Part partOne, Document document) throws IOException {
        Elements images = document.select("img");
        int i = 0;
        for (Element image : images) {
            System.out.println(image.attributes().get("src"));
            partOne.questions.get(i).linkImageResource = saveImage(image.attributes().get("src"));
            i++;
        }
    }

    private void cloneMp3PartOne(Part partOne, Document document) throws IOException {
        Elements mp3s = document.select("source");
        int i = 0;
        for (Element mp3 : mp3s) {
            System.out.println(mp3.attributes().get("src"));
            cloneMP3(mp3.attributes().get("src"));
            partOne.questions.get(i).linkMp3Resource = cloneMP3(mp3.attributes().get("src"));
            i++;
        }

    }

    private void cloneAnswerPartOne(Part partOne, Document document) throws IOException {
        Elements answerGroups = document.select("ul.answer-list");
        partOne.questions = new ArrayList<>();
        for (Element answerGroup : answerGroups) {
            Question question = new Question();
            int correctAnswer = 0;
            question.answers = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Elements li = answerGroup.getElementsByTag("li");
                String content = li.get(i).text();
                if (li.get(i).getElementsByTag("i").size() == 2) {
                    correctAnswer = i;
                }
                question.answers.add(new Answer(content, "" + (char) ((int) 'A' + i)));
            }
            question.correctAnswer = correctAnswer;
            question.partId = 1;
            partOne.questions.add(question);
        }
    }

    private Part clonePartTwo() throws IOException {
        HttpGet httpGet = new HttpGet("https://toeic24.vn/exam/part-2?questionGroupNum=1");
        HttpClient client = HttpClients.createDefault();
        httpGet.addHeader("Cookie", "laravel_session=" + laravel_session + ";");
        httpGet.addHeader("Cookie", "XSRF-TOKEN=" + XSRF_TOKEN + ";");
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");

        Part partTwo = new Part(2L, "Question-Response");
        cloneAnswerPartTwo(partTwo, document);
        cloneMp3PartTwo(partTwo, document);
        return partTwo;
    }

    private void cloneMp3PartTwo(Part partOne, Document document) throws IOException {
        Elements mp3s = document.select("source");
        int i = 0;
        for (Element mp3 : mp3s) {
            System.out.println(mp3.attributes().get("src"));
            cloneMP3(mp3.attributes().get("src"));
            partOne.questions.get(i).linkMp3Resource = cloneMP3(mp3.attributes().get("src"));
            i++;
        }

    }

    private void cloneAnswerPartTwo(Part partTwo, Document document) {
        Elements answerGroups = document.select("ul.answer-list");
        Elements explainAnswer = document.select("div.answer").select("b");
        partTwo.questions = new ArrayList<>();
        int position = 0;
        for (Element answerGroup : answerGroups) {
            Question question = new Question();
            int correctAnswer = 0;
            question.answers = new ArrayList<>();
            question.explainAnswer = explainAnswer.get(position).text();
            for (int i = 0; i < 3; i++) {
                Elements li = answerGroup.getElementsByTag("li");
                String content = li.get(i).text();
                if (li.get(i).getElementsByTag("i").size() == 2) {
                    correctAnswer = i;
                }
                question.answers.add(new Answer(content, "" + (char) ((int) 'A' + i)));
            }
            question.correctAnswer = correctAnswer;
            question.partId = 2;
            partTwo.questions.add(question);
            position++;
        }
    }

    private Part clonePartThree() throws IOException {
        Part partThree = new Part(3L, "Short conversation");
        partThree.groupQuestions = new ArrayList<>();
        for (int index = 2; index < 15; index++) {
            cloneQuestionPartThree(partThree, index);
        }
        return partThree;
    }

    private void cloneQuestionPartThree(Part partThree, int index) throws IOException {
        HttpGet httpGet = new HttpGet("https://toeic24.vn/exam/part-3?questionGroupNum=" + index);
        HttpClient client = HttpClients.createDefault();
        httpGet.addHeader("Cookie", "laravel_session=" + laravel_session + ";");
        httpGet.addHeader("Cookie", "XSRF-TOKEN=" + XSRF_TOKEN + ";");
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");
        GroupQuestion group_question = new GroupQuestion();
        group_question.partId = 3;
        cloneExplainQuestion(group_question, document);
        cloneMp3PResource(group_question, document);
        cloneImageResourcePart3(group_question, document);
        cloneQuestionAndAnswer(group_question, document);
        partThree.groupQuestions.add(group_question);
    }

    private void cloneImageResourcePart3(GroupQuestion groupQuestion, Document document) throws IOException {
        Element div = document.select("div.text-center").first();
        if (div != null) {
            Element image = div.children().first();
            groupQuestion.linkImageResource = saveImage(image.attributes().get("src"));
        }

    }

    private void cloneExplainQuestion(GroupQuestion groupQuestion, Document document) {
        Element paragraphDiv = document.select("div.text-justify").first();
        groupQuestion.explainAnswer = paragraphDiv.html().replace("\n", "");
    }

    private void cloneMp3PResource(GroupQuestion groupQuestion, Document document) throws IOException {
        Element mp3 = document.getElementsByTag("source").first();
        groupQuestion.linkMp3Resource = cloneMP3(mp3.attributes().get("src"));
    }

    private void cloneQuestionAndAnswer(GroupQuestion groupQuestion, Document document) {
        groupQuestion.questions = new ArrayList<>();
        Elements answerGroups = document.select("ul.answer-list");
        Elements questions = document.select("div.answer").select("b");
        int position = 0;
        for (Element answerGroup : answerGroups) {
            Question question = new Question();
            int correctAnswer = 0;
            question.answers = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                Elements li = answerGroup.getElementsByTag("li");
                String content = li.get(i).text();
                if (li.get(i).getElementsByTag("i").size() == 2) {
                    correctAnswer = i;
                }
                question.answers.add(new Answer(content, "" + (char) ((int) 'A' + i)));
            }
            question.question = questions.get(position).text();
            question.correctAnswer = correctAnswer;
            groupQuestion.questions.add(question);
            position++;
        }
    }

    private Part clonePartFour() throws IOException {
        Part partFour = new Part(4L, "Short talk");
        partFour.groupQuestions = new ArrayList<>();
        for (int index = 15; index < 25; index++) {
            cloneQuestionPartFour(partFour, index);
        }
        return partFour;
    }

    private void cloneQuestionPartFour(Part partFour, int index) throws IOException {
        HttpGet httpGet = new HttpGet("https://toeic24.vn/exam/part-4?questionGroupNum=" + index);
        HttpClient client = HttpClients.createDefault();
        httpGet.addHeader("Cookie", "laravel_session=" + laravel_session + ";");
        httpGet.addHeader("Cookie", "XSRF-TOKEN=" + XSRF_TOKEN + ";");
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");
        GroupQuestion group_question = new GroupQuestion();
        group_question.partId = 4;
        cloneExplainQuestion(group_question, document);
        cloneMp3PResource(group_question, document);
        clonrImageResourcePart4(group_question, document);
        cloneQuestionAndAnswer(group_question, document);
        partFour.groupQuestions.add(group_question);
    }

    private void clonrImageResourcePart4(GroupQuestion groupQuestion, Document document) throws IOException {
        Element div = document.select("div.text-center").first();
        if (div != null) {
            Element image = div.children().first();
            groupQuestion.linkImageResource = saveImage(image.attributes().get("src"));
        }
    }

    private Part clonePartFive() throws IOException {
        HttpGet httpGet = new HttpGet("https://toeic24.vn/exam/part-5");
        HttpClient client = HttpClients.createDefault();
        httpGet.addHeader("Cookie", "laravel_session=" + laravel_session + ";");
        httpGet.addHeader("Cookie", "XSRF-TOKEN=" + XSRF_TOKEN + ";");
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");

        Part partFive = new Part(5L, "Incomplete Sentences");
        cloneQuestionPartFive(partFive, document);
        return partFive;
    }

    private void cloneQuestionPartFive(Part partFive, Document document) {
        Elements answerGroups = document.select("ul.answer-list");
        Elements explainAnswer = document.select("div.answer").select("b");
        partFive.questions = new ArrayList<>();
        int position = 0;
        for (Element answerGroup : answerGroups) {
            Question question = new Question();
            int correctAnswer = 0;
            question.answers = new ArrayList<>();
            question.question = explainAnswer.get(position).text();
            for (int i = 0; i < 4; i++) {
                Elements li = answerGroup.getElementsByTag("li");
                String content = li.get(i).text();
                if (li.get(i).getElementsByTag("i").size() == 2) {
                    correctAnswer = i;
                }
                question.answers.add(new Answer(content, "" + (char) ((int) 'A' + i)));
            }
            question.correctAnswer = correctAnswer;
            question.partId = 5;
            partFive.questions.add(question);
            position++;
        }
    }


    private Part clonePartSix() throws IOException {
        Part partSix = new Part(6L, "Text Completion");
        partSix.groupQuestions = new ArrayList<>();
        for (int index = 25; index < 29; index++) {
            cloneQuestionPartSix(partSix, index);
        }
        return partSix;
    }

    private void cloneQuestionPartSix(Part partSix, int index) throws IOException {
        HttpGet httpGet = new HttpGet("https://toeic24.vn/exam/part-6?questionGroupNum=" + index);
        HttpClient client = HttpClients.createDefault();
        httpGet.addHeader("Cookie", "laravel_session=" + laravel_session + ";");
        httpGet.addHeader("Cookie", "XSRF-TOKEN=" + XSRF_TOKEN + ";");
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");
        GroupQuestion groupQuestion = new GroupQuestion();
        groupQuestion.partId = 6;
        cloneQuestionAndAnswerPartSix(groupQuestion, document);
        cloneParagraph(groupQuestion, document);
        partSix.groupQuestions.add(groupQuestion);
    }

    private void cloneQuestionAndAnswerPartSix(GroupQuestion group_question, Document document) {
        Elements answerGroups = document.select("ul.answer-list");
        Elements explainAnswer = document.select("div.answer").select("b");
        group_question.questions = new ArrayList<>();
        int position = 0;
        for (Element answerGroup : answerGroups) {
            Question question = new Question();
            int correctAnswer = 0;
            question.answers = new ArrayList<>();
            question.question = explainAnswer.get(position).text();
            for (int i = 0; i < 4; i++) {
                Elements li = answerGroup.getElementsByTag("li");
                String content = li.get(i).text();
                if (li.get(i).getElementsByTag("i").size() == 2) {
                    correctAnswer = i;
                }
                question.answers.add(new Answer(content, "" + (char) ((int) 'A' + i)));
            }
            question.correctAnswer = correctAnswer;
            question.partId = 5;
            group_question.questions.add(question);
            position++;
        }
    }

    private void cloneParagraph(GroupQuestion groupQuestion, Document document) {
        Element select = document.select("div.question-content").first();
        String s = select.html().replace("\n", "");
        int first = s.indexOf("<hr>");
        int last = s.indexOf("<form");
        groupQuestion.resourceParagraph = s.substring(first, last);
    }

    private Part clonePartSeven() throws IOException {
        Part partSeven = new Part(7L, "Reading Comprehension");
        partSeven.groupQuestions = new ArrayList<>();
        for (int index = 29; index < 44; index++) {
            cloneQuestionPartSeven(partSeven, index);
        }
        return partSeven;
    }

    private void cloneQuestionPartSeven(Part partSeven, int index) throws IOException {
        HttpGet httpGet = new HttpGet("https://toeic24.vn/exam/part-7?questionGroupNum=" + index);
        HttpClient client = HttpClients.createDefault();
        httpGet.addHeader("Cookie", "laravel_session=" + laravel_session + ";");
        httpGet.addHeader("Cookie", "XSRF-TOKEN=" + XSRF_TOKEN + ";");
        HttpResponse httpResponse = client.execute(httpGet);
        String content = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
        Document document = Jsoup.parse(content, "UTF-8");
        GroupQuestion groupQuestion = new GroupQuestion();
        groupQuestion.partId = 7;
        cloneQuestionAndAnswerPartSeven(groupQuestion, document);
        cloneImagePart7(groupQuestion, document);
        cloneParagraphPartSeven(groupQuestion, document);
        partSeven.groupQuestions.add(groupQuestion);
    }

    private void cloneImagePart7(GroupQuestion groupQuestion, Document document) throws IOException {
        cloneFirstImage(groupQuestion, document);
        cloneSecondImage(groupQuestion, document);
        cloneThirdImage(groupQuestion, document);
    }

    private void cloneFirstImage(GroupQuestion groupQuestion, Document document) throws IOException {
        if (document.select("img").size() >= 1) {
            Element img = document.select("img").first();
            if (img != null) {
                if (groupQuestion.linkImageResource == null) {
                    groupQuestion.linkImageResource = "";
                }
                groupQuestion.linkImageResource += saveImageType2(img.attributes().get("src")) + " , ";
            }
        }
    }

    private void cloneSecondImage(GroupQuestion groupQuestion, Document document) throws IOException {
        if (document.select("img").size() >= 2) {
            Element img = document.select("img").get(1);
            if (img != null) {
                if (groupQuestion.linkImageResource == null) {
                    groupQuestion.linkImageResource = "";
                }
                groupQuestion.linkImageResource += saveImageType2(img.attributes().get("src")) + " , ";
            }
        }
    }

    private void cloneThirdImage(GroupQuestion groupQuestion, Document document) throws IOException {
        if (document.select("img").size() >= 3) {
            Element img = document.select("img").get(2);
            if (img != null) {
                if (groupQuestion.linkImageResource == null) {
                    groupQuestion.linkImageResource = "";
                }
                groupQuestion.linkImageResource += saveImageType2(img.attributes().get("src")) + " , ";
            }
        }
    }

    private void cloneQuestionAndAnswerPartSeven(GroupQuestion groupQuestion, Document document) {
        Elements answerGroups = document.select("ul.answer-list");
        Elements explainAnswer = document.select("div.answer").select("b");
        groupQuestion.questions = new ArrayList<>();
        int position = 0;
        for (Element answerGroup : answerGroups) {
            Question question = new Question();
            int correctAnswer = 0;
            question.answers = new ArrayList<>();
            question.question = explainAnswer.get(position).text();
            for (int i = 0; i < 4; i++) {
                Elements li = answerGroup.getElementsByTag("li");
                String content = li.get(i).text();
                if (li.get(i).getElementsByTag("i").size() == 2) {
                    correctAnswer = i;
                }
                question.answers.add(new Answer(content, "" + (char) ((int) 'A' + i)));
            }
            question.correctAnswer = correctAnswer;
            question.partId = 5;
            groupQuestion.questions.add(question);
            position++;
        }
    }

    private void cloneParagraphPartSeven(GroupQuestion groupQuestion, Document document) {
        Element div = document.select("div.question-content").first();
        if (div != null) {
            String s = div.html();
            int first = s.indexOf("<hr>");
            int last = s.indexOf("<form");
            if (first != -1 && last != -1) {
                s = s.substring(first, last);
                int img = s.indexOf("img");
                if (img != -1) {
                    groupQuestion.resourceParagraph = s.substring(0, img - 5);
                }
                else{
                    groupQuestion.resourceParagraph = s;
                }
            }
        }
    }

    public String saveImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        FileOutputStream fos = new FileOutputStream("image_exam_" + time + "/" + imageUrl.split("/")[6]);
        fos.write(response);
        fos.close();
        System.out.println("clone image success");
        return "image_exam_" + time + "/" + imageUrl.split("/")[6];
    }

    public String saveImageType2(String imageUrl) throws IOException {
        String urlImage = "https://toeic24.vn" + imageUrl;
        System.out.println(urlImage);
        URL url = new URL(urlImage);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();
        String newName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1).substring(0, 10) + time + index;
        String fileName = "image_exam_" + time + "/" + newName + ".png";
        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(response);
        fos.close();
        System.out.println("clone image success");
        index++;
        return fileName;
    }

    private void writeToFile(Object obj) throws IOException {
        System.out.println("Start write to file : " + time);
        Gson gson = new Gson();
        String wordJson = gson.toJson(obj);
        OutputStream outStream = new FileOutputStream(new File("exam" + time + ".txt"));
        outStream.write(wordJson.getBytes());
        System.out.println("End write to file : " + time);
    }

    private String cloneMP3(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        InputStream is = conn.getInputStream();

        OutputStream outStream = new FileOutputStream(new File("mp3_exam_" + time + "/" + url.split("/")[6]));
        byte[] buffer = new byte[4096];
        int len;
        while ((len = is.read(buffer)) > 0) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        System.out.println("clone mp3 success");
        return "mp3_exam_" + time + "/" + url.split("/")[6];
    }

}
