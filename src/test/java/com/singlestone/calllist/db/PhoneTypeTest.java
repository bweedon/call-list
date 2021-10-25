package com.singlestone.calllist.db;

import com.singlestone.calllist.db.model.PhoneType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PhoneTypeTest {

    @ParameterizedTest
    @MethodSource("fromTextSource")
    void fromText(String input, PhoneType expected) {
        PhoneType actual = PhoneType.fromText(input);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> fromTextSource() {
        return Stream.of(
                Arguments.of("mobile", PhoneType.MOBILE),
                Arguments.of("work", PhoneType.WORK),
                Arguments.of("home", PhoneType.HOME),
                Arguments.of("MOBILE", PhoneType.MOBILE),
                Arguments.of("WoRK", PhoneType.WORK),
                Arguments.of("HOme", PhoneType.HOME)
        );
    }
}