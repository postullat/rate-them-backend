package co.ratethem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the Review database table.
 * 
 */
@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String companyName;
	private String vacancyName;
	private String cityName;
	private Date startDate;
	private Date endDate;

	//HR Start
	private String hrName;
	private double hrIceBrake;
	private double hrAttitude;
	private double hrPunctuality;
	private double hrImpression;
	private String hrComment;
	//HR End

	//Tech Start
	private String techInterviewerName;
	private double techIceBrake;
	private double techAttitude;
	private double techQuestionsQuality;
	private double techImpression;
	private String techComment;
	//Tech End

	//Feedback Start
	private double feedbackOnTime;
	private double feedbackDetailization;
	private String feedbackComment;
	//Feedback End

	private Date created;
	private Date updated;

}