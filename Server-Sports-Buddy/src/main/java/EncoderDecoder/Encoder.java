package EncoderDecoder;

import com.google.gson.Gson;

public class Encoder {

    public Encoder() {}

    public static String encodeObject(Object o) {
        Gson gson = new Gson();
        String str = gson.toJson(o);
        return str;
    }
}
