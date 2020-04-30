package clone_la_ban_dictionary;

import clone_la_ban_dictionary.model.Word;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Clone clone = new Clone();
        try {
            clone.startClone();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
