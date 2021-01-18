package co.ratethem;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.payload.ReviewRequest;
import co.ratethem.repository.ReviewRepository;
import co.ratethem.service.ReviewValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(MockitoJUnitRunner.class)
public class ReviewValidator_1_VacancyNameUnitTest {

    private Integer VACANCY_NAME_MIN = 5;

    private Integer VACANCY_NAME_MAX = 100;

    @Spy
    private final ReviewValidator reviewValidator = new ReviewValidator();
    @Before
    public void setUp() {
        ReflectionTestUtils.setField(reviewValidator, "VACANCY_NAME_MIN", VACANCY_NAME_MIN);
        ReflectionTestUtils.setField(reviewValidator, "VACANCY_NAME_MAX", VACANCY_NAME_MAX);

    }

    //Vacancy Start
    @Test
    public void whenVacancyNameIsEmpty_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("");

        Exception exception = assertThrows(EmptyValueException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "Vacancy name can't be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenVacancyNameIsNull_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName(null);

        Exception exception = assertThrows(EmptyValueException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "Vacancy name can't be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenVacancyNameIsLessThanFiveSymbols_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("iOS");

        assertEquals(req.getVacancyName().length() < VACANCY_NAME_MIN, true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "Vacancy name should be from "+VACANCY_NAME_MIN+" till "+VACANCY_NAME_MAX+" symbols";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenVacancyNameIsMoreThan_100_Symbols_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<7; i++) {
            //15 symbols * 7 = 105
            sb.append("React Developer");
        }
        req.setVacancyName(sb.toString());

        assertEquals(req.getVacancyName().length() > 100, true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "Vacancy name should be from 5 till 100 symbols";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    //Vacancy End

}
