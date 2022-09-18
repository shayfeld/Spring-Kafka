package com.springkafka;

import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaProducer {

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
        final Path[] data = new Path[1];
        final List<String>[] csvRows = new List[]{null};
        return args -> {
            //try
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path directory = Paths.get("/home/project/Documents");
            WatchKey watchKey = directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            // Waiting for new csv file
            while (true) {
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    data[0] = directory.resolve((Path) event.context());
                    if(data[0].toString().endsWith("csv")){
                        File file = new File(data[0].toString());
                        InputStream inputStream = new DataInputStream(new FileInputStream(file));

                        String csvAsString = new BufferedReader(new InputStreamReader(inputStream))
                                .lines().collect(Collectors.joining("\n"));
                        try {
                            String json = CDL.toJSONArray(csvAsString).toString();
                            JSONArray list = new JSONArray(CDL.toJSONArray(csvAsString));
                            for (Object o:list) {
                                kafkaTemplate.send("my_topic", o.toString());
                            }

                        }catch (Exception e){
                            System.out.println(e);
                        }

                    }
                }
            }
        };
    }
}
