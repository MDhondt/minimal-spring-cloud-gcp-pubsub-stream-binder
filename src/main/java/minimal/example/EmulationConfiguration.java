package minimal.example;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmulationConfiguration {

    @Bean
    public CredentialsProvider credentialsProvider() {
        return NoCredentialsProvider.create();
    }
}
