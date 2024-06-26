package algot.emil.kafkamessageapi.processor;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class KafkaStreamsEventProcessor {

    @Autowired
    private StreamsBuilder streamsBuilder;

    /**
     * events processor. This one isnt used. 
     */
    @PostConstruct
    public void streamTopology() {
        KStream<String, String> kStream = streamsBuilder.stream("spring.boot.kafka.stream.input", Consumed.with(Serdes.String(), Serdes.String()));
        kStream.filter((key, value) -> value.startsWith("Message_")).mapValues((k, v) -> v.toUpperCase()).peek((k, v) -> System.out.println("Key : " + k + " Value : " + v)).to("spring.boot.kafka.stream.output", Produced.with(Serdes.String(), Serdes.String()));
    }
}

