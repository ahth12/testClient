package rest;

import lombok.SneakyThrows;
import util.ExecuteURL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestClientPost implements Runnable {
    private String urlString = "http://localhost:8080?id=";
    private Integer id;
    private Long amount;

    public TestClientPost() throws MalformedURLException {
    }

    public TestClientPost(Integer id) throws MalformedURLException {
        this.id = id;
    }

    public TestClientPost(Integer id, Long amount) {
        this.id = id;
        this.amount = amount;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @SneakyThrows
    @Override
    public void run() {
        URL url = new URL(urlString + id + "&amount=" + amount);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            ExecuteURL.executeURL(con);
    }

}
