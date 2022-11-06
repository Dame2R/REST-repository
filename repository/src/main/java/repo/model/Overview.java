package repo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="overview")
public class Overview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "department")
	private String department;

	@Column(name = "process_type")
	private String process_type;

	@Column(name = "priority")
	private String priority;

	@Column(name = "energy_all")
	private double energy_all;

	@Column(name = "energy_month")
	private double energy_month;




}
