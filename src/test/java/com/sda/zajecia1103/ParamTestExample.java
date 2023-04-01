package com.sda.zajecia1103;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

public class ParamTestExample {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void test(int number){
        System.out.println(number);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"test","valie"})
    void test2(String item){
        System.out.println(item);
    }
}
