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
@Table(name="modell")
public class Modell {
	
	@Id
	private String id;
	
	@Column(name = "xml")
	private String xml;

	@Column(name = "parentProcess")
	private String parentProcess;

	@Column(name = "startKnoten")
	private String startKnoten;

	@Column(name = "endKnoten")
	private String endKnoten;

	@Column(name = "co2")
	private String co2;






}
