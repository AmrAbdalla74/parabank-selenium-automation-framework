package utils;

import net.datafaker.Faker;

import java.util.UUID;

public class DataGenerator {

    private static final Faker faker = new Faker();

    public static String randomUsername(){
        String name = faker.name().firstName().replaceAll("[^a-zA-Z]", "").toLowerCase();
        String unique = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return name + "_" + unique;
    }
    public static String password(){
        return "Test@" + faker.number().digits(6);
    }


    public static String firstName(){
        return faker.name().firstName();
    }

    public static String lastName(){
        return faker.name().lastName();
    }

    public static String address(){
        return faker.address().streetAddress();
    }

    public static String city(){
        return faker.address().city();
    }

    public static String state(){
        return faker.address().state();
    }

    public static String zipCode(){
        return faker.number().digits(5);
    }

    public static String phone(){
        return faker.number().digits(11);
    }

    public static String ssn(){
        return faker.number().digits(9);
    }
}
