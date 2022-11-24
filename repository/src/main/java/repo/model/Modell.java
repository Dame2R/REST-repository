package repo.model;

import javax.persistence.*;

import lombok.Data;

import java.sql.Blob;
import java.util.Date;

@Data
@Entity
@Table(name="modell")
public class Modell {
	
	@Id
	private String id;

	@Lob
	private Blob xml;

	@Column(name = "parentProcess")
	private String parentProcess;

	@Column(name = "startKnoten")
	private String startKnoten;

	@Column(name = "endKnoten")
	private String endKnoten;

	@Column(name = "co2")
	private String co2;






}
