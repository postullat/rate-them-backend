package co.ratethem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "email_subscription")
@Data
@NoArgsConstructor
public class EmailSubscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private boolean isActive;

	private Date created;
	private Date updated;


}
