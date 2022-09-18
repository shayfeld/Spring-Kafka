package com.springkafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class KafkaConsumer {

    public static int count = 0;
    public static int outputCount = 0;
    private final int limits = 3;
    public static JSONArray newFile = new JSONArray();
    @KafkaListener(topics = "my_topic",
            groupId = "my_group_id"
    )
    void listener(String data){
        JSONObject json = new JSONObject(data);

        newFile.put(json);
        System.out.println(newFile);
        count++;
        System.out.println("****");
        if(count == limits){
            try{
                Files.write(Path.of("/home/project/output"+outputCount+".csv"),CDL.toString(newFile).getBytes(StandardCharsets.UTF_8));
            }catch(IOException e){
                e.printStackTrace();
            }
            outputCount++;
            count=0;
            newFile.clear();
            System.out.println(newFile);
            System.out.println("*** Start new file ***");
        }
        //System.out.println("Listener received: " + data + " :)");
    }
}
