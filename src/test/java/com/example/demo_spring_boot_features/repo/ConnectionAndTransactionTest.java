package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.model.Author;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.engine.spi.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;

@SpringBootTest
class ConnectionAndTransactionTest {


    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void doTimeConsumingTask() throws InterruptedException {
        System.out.println("Waiting for a time-consuming task that doesn 't need a database connection ...");
        // we use a sleep of 40 seconds just to capture HikariCP logging status
        // which take place at every 30 seconds - this will reveal if
        // the connection was opened (acquired from the connection pool) or not
        Thread.sleep(5000);
        System.out.println("Done, now query the database ...");
        System.out.println("The database connection should be acquired now ...");
        Author author = authorRepo.findById(2147L).get();
        // at this point, the connection should be open
        Thread.sleep(5000);
        author.setAge(44);
    }


    @Test
    @Transactional
    public void fetchAuthorReadWriteMode() {
        Author author = authorRepo.findFirstByGenre("FEMALE");
        displayInformation("After Fetch", author);
        author.setAge(40);
        displayInformation("After Update Entity", author);
        // force flush - triggering manual flush is
        // a code smell and should be avoided
        // in this case, by default, flush will take
        // place before transaction commit
        authorRepo.flush();
        displayInformation("After Flush", author);
    }

    private void displayInformation(String phase, Author author) {
        System.out.println("Phase:" + phase);
        System.out.println("Entity: " + author);
        PersistenceContext persistenceContext = getPersistenceContext();
        System.out.println("Has only non read entities : "
                + persistenceContext.hasNonReadOnlyEntities());
        EntityEntry entityEntry = persistenceContext.getEntry(author);
        Object[] loadedState = entityEntry.getLoadedState();
        Status status = entityEntry.getStatus();
        System.out.println("Entity entry : " + entityEntry);
        System.out.println("Status: " + status);
        System.out.println("Loaded state: " + Arrays.toString(loadedState));
        System.out.println("=========================");
    }
    /*Case 1: Withoud @Transactional                       => java.lang.IllegalStateException: No transactional EntityManager available*/
    /*Case 2: With @Transactional but with readOnly = true => Loaded State is null  */
    /*Case 3: With @Transactional                          => Loaded State is null  */

    private PersistenceContext getPersistenceContext() {
        SharedSessionContractImplementor sharedSession = entityManager.unwrap(SharedSessionContractImplementor.class);
        return sharedSession.getPersistenceContext();
    }


    @Transactional(timeout = 10)
    public void newAuthor() throws InterruptedException {
        Author author = new Author();
        author.setAge(23);
        author.setGender("Anthology");
        author.setName("Mark Janel");
        authorRepo.saveAndFlush(author);
        Thread.sleep(15000); // 15 seconds
        System.out.println("The end!");
    }
}
