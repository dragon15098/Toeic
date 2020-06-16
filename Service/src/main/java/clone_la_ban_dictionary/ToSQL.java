package clone_la_ban_dictionary;

import clone_la_ban_dictionary.exam_model.Exam;
import clone_la_ban_dictionary.exam_model.Part;
import com.google.gson.Gson;

import java.io.IOException;

public class ToSQL {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Clone clone = new Clone();
        String s = clone.readFile("exam1.txt");
        Exam exam = gson.fromJson(s, Exam.class);
        Part partOne = exam.parts.get(0);
        int questionId = 0;
        partOne.questions.forEach(question -> {
            String insertQuestionSQL = "INSERT INTO `toeic`.`question` ( " +
                    "`correct_answer`," +
                    "`link_image_resource`, " +
                    "`link_mp3_resource`, " +
                    "`part_id`," +
                    "`exam_id`) " +
                    "VALUES ( " +
                    " '" + question.correctAnswer + "', '" +
                    question.linkImageResource + "', '" + question.linkMp3Resource + "', " +
                    "'1', '1');";
            System.out.println(insertQuestionSQL);

            String insertAnswersSQL = "INSERT INTO `toeic`.`answer` ( " +
                    "`index`," +
                    "`answer`, " +
                    "`question_id`) " +
                    "VALUES ( " +
                    " '" + question.correctAnswer + "', '" +
                    question.linkImageResource + "', '" + question.linkMp3Resource + "', " +
                    "'1', '1');";
            System.out.println(insertAnswersSQL);
        });




        Part partTwo = exam.parts.get(1);

        partTwo.questions.forEach(question -> {
            String sa = "INSERT INTO `toeic`.`question` ( " +
                    "`correct_answer`," +
                    "`link_image_resource`, " +
                    "`link_mp3_resource`, " +
                    "`part_id`," +
                    "`exam_id`) " +
                    "VALUES ( " +
                    " '" + question.correctAnswer + "', '" +
                    question.linkImageResource + "', '" + question.linkMp3Resource + "', " +
                    "'1', '1');";
            System.out.println(sa);
        });

    }
}
