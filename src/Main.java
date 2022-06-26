import rest.TestClientGet;
import rest.TestClientPost;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("src/application.properties");
        properties.load(inputStream);
        int rCount = Integer.parseInt(properties.getProperty("wCount"));
        int wCount = Integer.parseInt(properties.getProperty("rCount"));
        List<Integer> idList = Arrays.stream(properties.getProperty("idList").split(",")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        if (rCount + wCount != idList.size()){
            throw new Exception("Wrong number of threads");
        }
        List<Runnable> threads = new ArrayList<>(idList.size());
        for (int i = 0; i < rCount; i++) {
            threads.add(new TestClientGet(idList.get(i)));
        }
        for (int i = 0; i < wCount; i++) {
            threads.add(new TestClientPost(idList.get(rCount+i-1), 345L));
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            Thread.sleep(200);
            for (int i = 0; i < threads.size(); i++) {
            executorService.submit(threads.get(i));
            }
        }
    }
}
