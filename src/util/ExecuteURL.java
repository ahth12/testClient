package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ExecuteURL {
    public static void executeURL(HttpURLConnection con) throws IOException {
        InputStream content = con.getInputStream();
        String inputLine;
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
        while ((inputLine = bufferedReader.readLine()) != null){
            stringBuffer.append(inputLine);
        }
        System.out.println(stringBuffer);
    }
}
