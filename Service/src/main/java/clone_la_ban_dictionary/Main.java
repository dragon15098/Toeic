package clone_la_ban_dictionary;

import clone_la_ban_dictionary.model.Word;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Clone_Exam clone = new Clone_Exam();
        try {
            clone.startClone();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
