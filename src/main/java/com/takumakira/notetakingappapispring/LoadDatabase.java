package com.takumakira.notetakingappapispring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(NoteRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Note("Note 1", "This is note #1.", "2021-01-01T00:00:00Z")));
      log.info("Preloading " + repository.save(new Note("Note 2", "This is note #2.", "2021-01-02T00:00:00Z")));
    };
  }

}
