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
public class ReviewValidator_3_CityNameUnitTest {

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


    //City Start
    @Test
    public void whenCityNameIsEmpty_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("Java Developer");
        //req.setCompanyName("NiX");
        //req.setCityName("");

        Exception exception = assertThrows(EmptyValueException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "City name can't be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenCityNameIsNull_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("Java Developer");
        //req.setCompanyName("NiX");
        //req.setCityName(null);

        Exception exception = assertThrows(EmptyValueException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "City name can't be null or empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenCityNameIsLessThanFiveSymbols_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("Java Developer");
        //req.setCompanyName("NiX");
        //req.setCityName("Ky");

        //assertEquals(req.getCityName().length() < CITY_NAME_MIN, true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "City name should be from "+CITY_NAME_MIN+" till "+CITY_NAME_MAX+" symbols";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void whenCityNameIsMoreThan_100_Symbols_thenThrowException_And_assertionSucceeds() {
        ReviewRequest req = new ReviewRequest();
        req.setVacancyName("Java Developer");
        //req.setCompanyName("NiX");

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<21; i++) {
            //5 symbols * 21 = 105
            sb.append("Kyiv ");
        }
        //req.setCityName(sb.toString());

        //assertEquals(req.getCityName().length() > CITY_NAME_MAX, true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reviewValidator.validateRequest(req);
        });

        String expectedMessage = "City name should be from "+CITY_NAME_MIN+" till "+CITY_NAME_MAX+" symbols";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    //City End
}
