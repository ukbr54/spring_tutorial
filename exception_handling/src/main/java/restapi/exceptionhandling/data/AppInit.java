package restapi.exceptionhandling.data;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AppInit {

    private SampleData sampleData;

    @Bean
    CommandLineRunner init() {
        return (s) -> {
            log.info("Loading sample DATA into DB...");

            sampleData.createSampleData();
        };
    }
}
