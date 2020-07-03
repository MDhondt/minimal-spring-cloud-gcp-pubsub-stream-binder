package minimal.example;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class Main {

    private static final Logger LOG = getLogger(Main.class);

    private boolean firstExecution = true;

    @Autowired
    private SuccessSwitch consumerSuccessSwitch;
    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Scheduled(fixedDelay = 10000)
    public void doSomethingAfterStartup() {
        if (firstExecution) {
            firstExecution = false;
            consumerSuccessSwitch.letFail();

            pubSubTemplate.publish("topic", "payload");
            LOG.info("Message published");
        } else {
            consumerSuccessSwitch.letSucceed();
        }
    }
}
