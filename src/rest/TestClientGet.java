package rest;

import lombok.SneakyThrows;
import util.ExecuteURL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestClientGet implements Runnable {
    private String urlString = "http://localhost:8080?id=";
    private Integer id;

    public TestClientGet() throws MalformedURLException {
    }

    public TestClientGet(Integer id) throws MalformedURLException {
        this.id = id;
    }

    public String getUrl() {
        return urlString;
    }

    public void setUrl(String urlString) {
        this.urlString = urlString;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SneakyThrows({MalformedURLException.class, IOException.class})
    @Override
    public void run() {
        URL url = new URL(urlString + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            ExecuteURL.executeURL(con);
    }

}

