package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class AccessingDataJPA {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJPA.class);

    public static void main(String[] args) {

        SpringApplication.run(AccessingDataJPA.class, args);
    }

    /***
    @Bean
    public CommandLineRunner demo(BuddyInfoRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new BuddyInfo("Jack", 162323));
            repository.save(new BuddyInfo("Chloe", 812823));
            repository.save(new BuddyInfo("Kim", 128328));
            repository.save(new BuddyInfo("David", 192323));
            repository.save(new BuddyInfo("Michelle", 12929));

            // fetch all customers
            log.info("Buddy found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(buddy -> {
                log.info(buddy.toString());
            });
            log.info("");

            // fetch an individual customer by ID
            BuddyInfo buddy = repository.findById(1L);
            log.info("Buddy found with findById(1L):");
            log.info("--------------------------------");
            log.info(buddy.toString());
            log.info("");

            // fetch customers by last name
            log.info("Buddy found with findByName('Michelle'):");
            log.info("--------------------------------------------");
            repository.findByName("Michelle").forEach(michelle -> {
                log.info(michelle.toString());
            });
            log.info("");
        };
    }

    @Bean
    public CommandLineRunner demo2(AddressBookRepository repository) {
        return (args) -> {
            AddressBook a = new AddressBook();
            BuddyInfo  b = new BuddyInfo("Jack", 162323);

            a.addBuddy(b);

            AddressBook a2 = new AddressBook();
            BuddyInfo  b2 = new BuddyInfo("Kate", 123930);
            BuddyInfo b3 = new BuddyInfo("Shannon", 192999);

            a2.addBuddy(b2);
            a2.addBuddy(b3);
            repository.save(a);
            repository.save(a2);

            log.info("Saved AddressBook with buddy: " + a.getBuddyList());
            log.info("Saved AddressBook2 with buddy: " + a2.getBuddyList());


        };
    }
    ***/

}
