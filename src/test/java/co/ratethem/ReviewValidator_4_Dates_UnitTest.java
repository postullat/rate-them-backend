package co.ratethem;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.controller.rest_exception_handler.exception.InvalidValueException;
import co.ratethem.payload.ReviewRequest;
import co.ratethem.service.ReviewValidator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class ReviewValidator_4_Dates_UnitTest {

    private Integer VACANCY_NAME_MIN = 5;

    private Integer VACANCY_NAME_MAX = 100;

    private Integer CITY_NAME_MIN = 3;

    private Integer CITY_NAME_MAX = 100;

    private Integer COMPANY_NAME_MIN = 3;

    private Integer COMPANY_NAME_MAX = 100;

    @Spy
    private final ReviewValidator reviewValidator = new ReviewValidator();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(reviewValidator, "VACANCY_NAME_MIN", VACANCY_NAME_MIN);
        ReflectionTestUtils.setField(reviewValidator, "VACANCY_NAME_MAX", VACANCY_NAME_MAX);

        ReflectionTestUtils.setField(reviewValidator, "CITY_NAME_MIN", CITY_NAME_MIN);
        ReflectionTestUtils.setField(reviewValidator, "CITY_NAME_MAX", CITY_NAME_MAX);

        ReflectionTestUtils.setField(reviewValidator, "COMPANY_NAME_MIN", COMPANY_NAME_MIN);
        ReflectionTestUtils.setField(reviewValidator, "COMPANY_NAME_MAX", COMPANY_NAME_MAX);
    }

    //Dates Start
    @Test
    public void whenStartDateIsNull_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("Java Developer");
        //req.setCityName("Lviv");
        //req.setCompanyName("System X");
        req.setStartDate(null);

        Exception exception = assertThrows(EmptyValueException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "Start date can't be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenEndDateIsNull_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("Java Developer");
        //req.setCityName("Lviv");
        //req.setCompanyName("System X");
        req.setStartDate(new Date());
        req.setEndDate(null);

        Exception exception = assertThrows(EmptyValueException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "End date can't be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenNowDateIsNull_thenThrowException_And_assertionSucceeds() {

        Exception exception = assertThrows(EmptyValueException.class, () -> {
            reviewValidator.validateDatesFromTo(new Date(), new Date(), null);
        });

        String expectedMessage = "Now date can't be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenStartDateIsAfterNow_thenThrowException_And_assertionSucceeds() {

        DateTime now = DateTime.now();
        DateTime start = new DateTime(now.plusDays(5));
        DateTime end = new DateTime(now.plusDays(7));

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            reviewValidator.validateDatesFromTo(start.toDate(), end.toDate(), now.toDate());
        });

        String expectedMessage = "Interview Start Date can't be after Now";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenEndDateIsAfterNow_thenThrowException_And_assertionSucceeds() {

        DateTime now = DateTime.now();
        DateTime start = new DateTime(now.minusDays(5));
        DateTime end = new DateTime(now.plusDays(2));

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            reviewValidator.validateDatesFromTo(start.toDate(), end.toDate(), now.toDate());
        });

        String expectedMessage = "Interview End Date can't be after Now";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenStartDateIsAfterEndDate_thenThrowException_And_assertionSucceeds() {

        DateTime now = DateTime.now();
        DateTime start = new DateTime(now.minusDays(5));
        DateTime end = new DateTime(now.minusDays(7));

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            reviewValidator.validateDatesFromTo(start.toDate(), end.toDate(), now.toDate());
        });

        String expectedMessage = "Interview Start Date can't be after End Date";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenStartDateIsMoreThan_1_monthAgo_thenThrowException_And_assertionSucceeds() {

        DateTime now = DateTime.now();

        DateTime start = new DateTime(now.minusMonths(2));
        DateTime end = new DateTime(now.minusDays(1));

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            reviewValidator.validateDatesFromTo(start.toDate(), end.toDate(), now.toDate());
        });

        String expectedMessage = "Interview Start Date can't be more than 1 months ago";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

/*    @Test
    public void whenEndDateIsBefore2020_thenThrowException_And_assertionSucceeds() {

        DateTime now = DateTime.now();

        DateTime start = new DateTime(now.minusDays(15));
        DateTime end = new DateTime(now.minusDays(18));

        Exception exception = assertThrows(InvalidValueException.class, () -> {
            reviewValidator.validateDatesFromTo(start.toDate(), end.toDate(), now.toDate());
        });

        String expectedMessage = "Interview End Date can't be more than 1 months ago";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }*/
    //Dates End

}
