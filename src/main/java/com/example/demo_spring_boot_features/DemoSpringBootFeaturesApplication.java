package com.example.demo_spring_boot_features;

import com.example.demo_spring_boot_features.component.ChildBean;
import com.example.demo_spring_boot_features.component.ParentBean;
import com.example.demo_spring_boot_features.component.SimpleBean;
import com.example.demo_spring_boot_features.domain.Author;
import com.example.demo_spring_boot_features.domain.Book;
import com.example.demo_spring_boot_features.repo.AuthorRepository;
import com.example.demo_spring_boot_features.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@RequiredArgsConstructor
@Slf4j
public class DemoSpringBootFeaturesApplication  {


    private final ApplicationContext applicationContext;

    private final SimpleBean simpleBean;

    private final ParentBean parentBean;

    private final ChildBean childBean;

    private final List<ParentBean> parentBeans;

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootFeaturesApplication.class, args);
    }

   /* @Override
    @Transactional
    public void run(String... args) {


        List<Book> books = bookRepository.findAll();
        log.info("Size of books :" + books.size());
        books.stream().map(book -> book.setAuthor(null)).forEach(book -> bookRepository.delete(book));


        List<Author> authors = authorRepository.findAll();
        log.info("Size of authors:" + authors.size());
        authorRepository.deleteAll();


        Author author = new Author().setName("Nguyen Vi").setAge(20).setGenre("FEMALE");
        authorRepository.save(author);

        Book book = new Book().setTitle("Spring Hibernate").setIsbn("02620006430").setAuthor(author);
        bookRepository.save(book);

        author.setAge(21);


    }

    private void listOfBean() {
        parentBeans.forEach(System.out::println);
    }

    private void injectingDependenciesThatAreNotBean() {
        listOfBean();
    }

    private void setUpStringToLocalDateConverter() {
        System.out.println("String to local date converter: ");
        System.out.println("    " + parentBean.getBirthDay());
    }

    private void beanDeclarationInheritance() {
        System.out.println("Parent Bean: ");
        System.out.println("    " + parentBean);
        System.out.println("Child Bean: ");
        System.out.println("    " + childBean);
    }*/
}
