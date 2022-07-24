/*
package com.example.demo_spring_boot_features.service;

import com.example.demo_spring_boot_features.domain.Address;
import com.example.demo_spring_boot_features.domain.Person;
import com.example.demo_spring_boot_features.domain.Phone;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.TypeToken;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.instancio.Select.*;

// Creating Objects
*/
/*
 * Instancio.create(Class<T> klass): create an object with class Kclass generate totally random object
 * Tạo ra object hoàn toàn là random
 * Instancio.create(TypeTokenSupplier<T> supplier): Chưa hiểu
 * Instancio.create(Model<T>): Chưa hiểu
 *   1. Builder API
 *       Instancio.of(Class<T> klass).create(): tạo với class
 *       Instancio.of(TypeTokenSupplier<T> supplier).create(): tạo instances của kiểu generic bằng việc cung cấp type token
 *       What is type token  ?
 *       Instancio.of(Model<T> model).create(): tạo với một Model, giống như tạo object từ một template nào đó
 *   2. Creating a Stream of Objects
 *       Instancio.stream(Class<T> klass): tạo một stream với class truyền vào
 *       Instancio.stream(TypeTokenSupplier<T> supplier)
 *       Builder API
 *           Instancio.of(Class<T> klass).stream()
 *           Instancio.of(TypeTokenSupplier<T> supplier).stream()
 * *//*


// Selectors
*/
/*
*   1. Select.field(String field): chọn ra một field nhất định của class được tạo
*   2. Select.field(Class<?> declaringClass, String field): chọn ra một field nhất định của class được truyền
*   3. Select.all(Class<?> type): tất cả các field của một type cho trước
*   4. Select.all(GroupableSelector... selectors): Dành cho kết hợp nhiều field query selectors
*   5. Select.allStrings() = Select.all(String.class)
*   6. Select.allInts() = Select.all(int.class), all(Integer.class)
*       Ví dụ
*       Person person = Instancio.of(Person.class)
                            .ignore(field("name")) // bỏ đi field name của Person
                            .ignore(field(Address.class, "street")) // bỏ đi field street ở trong class Address
                            .ignore(all(Phone.class)) // bỏ đi class Phone
                            .create();
    *
* *//*


// Selector Scopes
*/
/*
 *   Select.scope(Class<?> targetClass, String field)
 *   Select.scope(Class<?> targetClass)
 *
 * *//*

// Customising Objects
*/
/*

 1. generate()

 2. set()
 3. supply()
*//*


public class PersonServiceTest {


    @Test
    public void testCreateInstances() {
//        Method 1
        Person person = Instancio.create(Person.class);
        System.out.println(person);
//        Method 2
        Pair<String, Long> pair = Instancio.create(new TypeToken<>() {
        });
        System.out.println(pair);
        */
/*result: DXZMPJZIU->1504*//*

        Map<Integer, List<String>> map = Instancio.create(new TypeToken<>() {
        });
        System.out.println(map);
        */
/*result: {1074=[HHGQTSVC, KBVZG, GQTJWFFR, CGX, EPGMNJ], 7316=[CPP, SCIBNCAD, FAREFHEAA, NMJIX], 2666=[QKQWEC, FTOZJE, BFBEGWH, BMUPSDU, WBC, JBHFMAZFV]}*//*


//        Method 3 Định nghĩa field nào có, field nào không có, dữ liệu của từng field là gì ?
        Model<Person> personModel = Instancio.of(Person.class)
                .ignore(field("age"))
                .ignore(all(Address.class))
                .toModel();

        Person personWithoutAgeAndAddress = Instancio.of(personModel)
                .create();
        System.out.println(personWithoutAgeAndAddress);
    }

    @Test
    public void testSelectorScopes() {
        Person person = Instancio.of(Person.class)
                .set(allStrings().within(scope(Address.class)), "foo") // set all string in class scope class Address value is foo
                .create();
        System.out.println(person);
    }

    @Test
    public void testCreateStreamOfObjects() {
//        Person
        List<Person> personList = Instancio.stream(Person.class)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(personList);
        Map<UUID, Person> personMap = Instancio.of(new TypeToken<Person>() {
                })
                .ignore(
                        all(field("age"), all(Address.class), all(Phone.class)))
                .stream()
                .limit(3)
                .collect(Collectors.toMap(Person::getUuid, Function.identity()));
        System.out.println(personMap);

    }

    @Test
    public void testCustomisingCreateObject_Generate() {

        List<String> firstNames = Arrays.asList("Vi", "Son", "Minh", "Truong");
//    gen.ints().range(): create an integer within range
        Person person = Instancio.of(Person.class)
                .generate(field("age"), gen -> gen.ints().range(18, 65))
                .generate(field("pets"), gen -> gen.array().length(3))
                .generate(field("lastName"), generators -> generators.oneOf("Nguyen","Bui", "Hoang"))
                .generate(field("firstName"), generators -> generators.oneOf(firstNames))
                .generate(field(Phone.class, "number"), gen -> gen.text().pattern("#d#d#d-#d#d-#d#d"))
                .create();
        System.out.println(person);

    }

    @Test
    public void testCustomisingCreateObject_Set() {

        List<String> firstNames = Arrays.asList("Vi", "Son", "Minh", "Truong");
        Person person = Instancio.of(Person.class)
                .set(field("age"), 21)
                .set(field("firstName"), "Vi")
                .set(field("lastName"), "Nguyen")
                .create();
        System.out.println(person);

    }

    @Test
    public void testCustomisingCreateObject_Supply() {

        List<Integer> ages = Arrays.asList(10,20,30,32);
        List<String> firstNames = Arrays.asList("Vi", "Son", "Minh", "Truong");
        Person person = Instancio.of(Person.class)
                .supply(field("age"), () -> Collections.min(ages))
                .supply(field("firstName"), () -> "Nguyen")
                .create();
        System.out.println(person);

    }


    @Test
    public void testCreate1() {
        Person person = Instancio.of(Person.class)
                .generate(field("lastName"), generators -> generators.oneOf("Nguyen", "Hoang"))
                .generate(field("firstName"), generators -> generators.oneOf("Vi", "Trung", "Nguyen"))
                .ignore(all(
                        field("id"),
                        field("age"),
                        field(Address.class, "state"), // select specific field in the list of field of Address
                        all(Phone.class)))
//                .ignore(field("id"))
//                .ignore(field("age"))
                .create();
        System.out.println(person);
    }

}*/
