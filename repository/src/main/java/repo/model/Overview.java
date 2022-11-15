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

	@Column(name = "processType")
	private String processType;

	@Column(name = "energySumYear")
	private int energySumYear;

	@Column(name = "department")
	private String department;

	//Wie unterscheidet sich das vom predecessor und successor?
	@Column(name = "parentProcess")
	private long parentProcess;

	//Es ist noch zu klären, wie bei mehreren Vorgängern/ Nachfolgern vorgegangen wird.
	@Column(name = "predecessor")
	private long predecessor;

	@Column(name = "successor")
	private long successor;

	@Column(name = "processDescription")
	private String processDescription;

}
