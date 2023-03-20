package ru.load.jmh.demo.jackson;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.load.jmh.demo.jackson.data.User;

import java.io.File;
import java.io.IOException;


public class JacksonExample {
    User user = null;

    public User getUser() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(new File("D:\\load\\demo-jmh\\src/main/resources/ru/load/jmh/demo/jackson/User.json"), User.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e)  {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User getUserWithCache() {

        if (user != null) {
            return user;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            user = mapper.readValue(new File("D:\\load\\demo-jmh\\src/main/resources/ru/load/jmh/demo/jackson/User.json"), User.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e)  {
            throw new RuntimeException(e);
        }
        return user;
    }
}