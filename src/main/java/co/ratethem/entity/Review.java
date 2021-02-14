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

	@ManyToOne(optional = false)
	@JoinColumn(name = "company_id")
	private Company company;

	private String vacancyName;

	@ManyToOne(optional = false)
	@JoinColumn(name = "city_id")
	private City city;

	private Date startDate;
	private Date endDate;

	//HR Start
	private String hrName;
	private int hrIceBrake;
	private int hrAttitude;
	private int hrPunctuality;
	private int hrImpression;
	private String hrComment;

	private double hrRatingTotal;
	//HR End

	//Tech Start
	private String techInterviewerName;
	private int techIceBrake;
	private int techAttitude;
	private int techQuestionsQuality;
	private int techImpression;
	private String techComment;

	private double techRatingTotal;
	//Tech End

	//Feedback Start
	private int feedbackOnTime;
	private int feedbackDetailization;
	private String feedbackComment;

	private double feedbackRatingTotal;
	//Feedback End

	private double companyRatingTotal;

	private Date created;
	private Date updated;

}