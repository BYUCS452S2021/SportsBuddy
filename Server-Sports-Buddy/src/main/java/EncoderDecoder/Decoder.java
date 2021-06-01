package EncoderDecoder;

import Requests.LoginRequest;
import Requests.RegisterRequest;
import com.google.gson.Gson;

import java.io.Reader;

public class Decoder {
    public Decoder() {}

    public static RegisterRequest decodeRegisterRequest(Reader json) {
        RegisterRequest request;
        Gson gson = new Gson();
        request = gson.fromJson(json, RegisterRequest.class);
        return request;
    }

    public static LoginRequest decodeLoginRequest(Reader json) {
        LoginRequest request;
        Gson gson = new Gson();
        request = gson.fromJson(json, LoginRequest.class);
        return request;
    }

    public static String decodeString(Reader json) {
        String str = "";
        Gson gson = new Gson();
        str = gson.fromJson(json, String.class);
        return str;
    }
}
