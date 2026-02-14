package ru.DNSshop;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import steps.BaseSteps;

import static helpers.Properties.testsProperties;

@ExtendWith(SoftAssertionsExtension.class)
public class Tests extends BaseTests {

    @DisplayName("Открытие сайта Stepik")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerGetRecourse")
    public void testSearchPopularCourse(String course, String type, String language, SoftAssertions softly) {
        BaseSteps step = new BaseSteps(chromeDriver);
        step.openSite(testsProperties.stepikUrl());
        step.enterKeywordSearchbar(course);
        step.filteringByParameters(type, language);
        step.checkFiveCoursesContainWord(softly, course);
        step.clickTopCourse(course);
        step.searchButtonInCourseCard(softly);
    }
}
