package minimal.example;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.cloud.stream.messaging.Sink.INPUT;

@EnableBinding(Sink.class)
class Subscriber {

    private static final Logger LOG = getLogger(Subscriber.class);

    @Autowired
    private SuccessSwitch successSwitch;
    private int retryCounter = 0;

    @StreamListener(INPUT)
    public void handleMessage(Message<String> message) {
        LOG.info("Received: {} for the {} time", message.getPayload(), ++retryCounter);

        if (!successSwitch.succeeded()) {
            throw new RuntimeException();
        }
        LOG.info("Received: {} times", retryCounter);
    }
}
