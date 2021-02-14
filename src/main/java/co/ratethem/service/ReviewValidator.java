package co.ratethem.service;

import co.ratethem.controller.rest_exception_handler.exception.EmptyValueException;
import co.ratethem.controller.rest_exception_handler.exception.InvalidValueException;
import co.ratethem.payload.ReviewRequest;
import co.ratethem.repository.CityRespository;
import co.ratethem.repository.CompanyRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class ReviewValidator {

    @Value("${fieldsRestrictions.vacancyMin}")
    private Integer VACANCY_NAME_MIN;

    @Value("${fieldsRestrictions.vacancyMax}")
    private Integer VACANCY_NAME_MAX;

    @Value("${fieldsRestrictions.cityMin}")
    private Integer CITY_NAME_MIN;

    @Value("${fieldsRestrictions.cityMax}")
    private Integer CITY_NAME_MAX;

    @Value("${fieldsRestrictions.companyMin}")
    private Integer COMPANY_NAME_MIN;

    @Value("${fieldsRestrictions.companyMax}")
    private Integer COMPANY_NAME_MAX;

    @Autowired
    private CityRespository cityRespository;

    @Autowired
    private CompanyRepository companyRepository;

    public void validateRequest(ReviewRequest reviewJson) throws EmptyValueException, InvalidValueException {

        validateVacancyName(reviewJson.getVacancyName());

        long cityId = reviewJson.getCityId();
        long companyId = reviewJson.getCompanyId();

        validateCompanyId(companyId);
        validateCityId(cityId);

        //this is not used since we use city id and company id
        //validateCompanyName(reviewJson.getCompanyName());
        //validateCityName(reviewJson.getCityName());

        validateDatesFromTo(reviewJson.getStartDate(), reviewJson.getEndDate(), new Date()/*now()*/);

        //HR validation Start
        if (StringUtils.isEmpty(reviewJson.getHr().getName())) {
            throw new EmptyValueException("HR Name can't be null or empty");
        }

        if (reviewJson.getHr().getIceBrake() <= 0 || reviewJson.getHr().getIceBrake() > 5) {
            throw new InvalidValueException("HR Ice Brake value should be set between 1 and 5");
        }

        if (reviewJson.getHr().getAttitude() <= 0 || reviewJson.getHr().getAttitude() > 5) {
            throw new InvalidValueException("HR Attitude value should be set between 1 and 5");
        }

        if (reviewJson.getHr().getPunctuality() <= 0 || reviewJson.getHr().getPunctuality() > 5) {
            throw new InvalidValueException("HR Punctuality value should be set between 1 and 5");
        }

        if (reviewJson.getHr().getImpression() <= 0 || reviewJson.getHr().getImpression() > 5) {
            throw new InvalidValueException("HR Impression value should be set between 1 and 5");
        }

        if (!StringUtils.isEmpty(reviewJson.getHr().getComment())) {
            if (reviewJson.getHr().getComment().length() > 250)
                throw new EmptyValueException("Comment about HR can't be more than 250 symbols");
        }
        //HR validation End

        //Tech validation Start
        if (StringUtils.isEmpty(reviewJson.getTech().getInterviewerName())) {
            throw new EmptyValueException("Tech Interviewer Name can't be null or empty");
        }

        if (reviewJson.getTech().getIceBrake() <= 0 || reviewJson.getTech().getIceBrake() > 5) {
            throw new InvalidValueException("Tech Interviewer Ice Brake value should be set between 1 and 5");
        }

        if (reviewJson.getTech().getAttitude() <= 0 || reviewJson.getTech().getAttitude() > 5) {
            throw new InvalidValueException("Tech Interviewer Attitude value should be set between 1 and 5");
        }

        if (reviewJson.getTech().getQuestionsQuality() <= 0 || reviewJson.getTech().getQuestionsQuality() > 5) {
            throw new InvalidValueException("Tech Interviewer Questions Quality value should be set between 1 and 5");
        }

        if (reviewJson.getTech().getImpression() <= 0 || reviewJson.getTech().getImpression() > 5) {
            throw new InvalidValueException("Tech Interviewer Impression value should be set between 1 and 5");
        }

        if (!StringUtils.isEmpty(reviewJson.getTech().getComment())) {
            if (reviewJson.getTech().getComment().length() > 250)
                throw new EmptyValueException("Comment about Tech Interviewer can't be more than 250 symbols");
        }
        //Tech validation End

        //Feedback validation Start
        if (reviewJson.getFeedback().getOnTime() <= 0 || reviewJson.getFeedback().getOnTime() > 5) {
            throw new InvalidValueException("Feedback On Time value should be set between 1 and 5");
        }

        if (reviewJson.getFeedback().getDetailization() <= 0 || reviewJson.getFeedback().getDetailization() > 5) {
            throw new InvalidValueException("Feedback Detailization value should be set between 1 and 5");
        }

        if (!StringUtils.isEmpty(reviewJson.getFeedback().getComment())) {
            if (reviewJson.getFeedback().getComment().length() > 250)
                throw new EmptyValueException("Comment about Feedback can't be more than 250 symbols");
        }
        //Feedback validation End
    }

    private void validateCompanyId(long companyId) throws InvalidValueException {
        if(companyId <= 0) {
            throw new InvalidValueException("Company ID can't be <= 0");
        }

        if(!companyRepository.existsById(companyId)) {
            throw new InvalidValueException("Company with ID "+companyId+" doesn't exist in the system");
        }
    }

    private void validateCityId(long cityId) throws InvalidValueException {
        if(cityId <= 0) {
            throw new InvalidValueException("City ID can't be <= 0");
        }

        if(!cityRespository.existsById(cityId)) {
            throw new InvalidValueException("City with ID "+cityId+" doesn't exist in the system");
        }
    }


    public void validateVacancyName(String vacancyName) throws EmptyValueException {
        //Vacancy Start
        if (StringUtils.isEmpty(vacancyName)) {
            throw new EmptyValueException("Vacancy name can't be null or empty");
        }
        if (vacancyName.length() < VACANCY_NAME_MIN || vacancyName.length() > VACANCY_NAME_MAX) {
            throw new IllegalArgumentException("Vacancy name should be from " + VACANCY_NAME_MIN + " till " + VACANCY_NAME_MAX + " symbols");
        }
        //Vacancy End
    }

    public void validateCityName(String cityName) throws EmptyValueException {
        //City Start
        if (StringUtils.isEmpty(cityName)) {
            throw new EmptyValueException("City name can't be null or empty");
        }
        if (cityName.length() < CITY_NAME_MIN || cityName.length() > CITY_NAME_MAX) {
            throw new IllegalArgumentException("City name should be from " + CITY_NAME_MIN + " till " + CITY_NAME_MAX + " symbols");
        }
        //City End
    }

    public void validateCompanyName(String companyName) throws EmptyValueException {
        //Company Name Start
        if (StringUtils.isEmpty(companyName)) {
            throw new EmptyValueException("Company name can't be null or empty");
        }
        if (companyName.length() < COMPANY_NAME_MIN || companyName.length() > COMPANY_NAME_MAX) {
            throw new IllegalArgumentException("Company name should be from " + COMPANY_NAME_MIN + " till " + COMPANY_NAME_MAX + " symbols");
        }
        //Company Name End
    }

    public void validateDatesFromTo(Date startDate, Date endDate, Date now) throws InvalidValueException, EmptyValueException {
        if (startDate == null) {
            throw new EmptyValueException("Start date can't be null or empty");
        }

        if (endDate == null) {
            throw new EmptyValueException("End date can't be null or empty");
        }

        if (now == null) {
            throw new EmptyValueException("Now date can't be null or empty");
        }

        DateTime nowYoda = new DateTime(now);
        DateTime startDateYoda = new DateTime(startDate);
        DateTime endDateYoda = new DateTime(endDate);

        if (startDateYoda.isAfter(nowYoda)) {
            throw new InvalidValueException("Interview Start Date can't be after Now");
        }

        if (endDateYoda.isAfter(nowYoda)) {
            throw new InvalidValueException("Interview End Date can't be after Now");
        }

        if (startDateYoda.isAfter(endDateYoda)) {
            throw new InvalidValueException("Interview Start Date can't be after End Date");
        }

        if (startDateYoda.isBefore(nowYoda.minusMonths(1))) {
            throw new InvalidValueException("Interview Start Date can't be more than 1 months ago");
        }

        if (endDateYoda.isBefore(nowYoda.minusMonths(1))) {
            throw new InvalidValueException("Interview End Date can't be more than 1 months ago");
        }
    }
}
