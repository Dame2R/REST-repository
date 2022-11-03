package endpoints;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Overview")
public class Overview {
    private int id;
    private String Name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String Department;
    private String process_type;
    private String priority;
    private double energy_all;
    private double enegry_month;
    
    
    public Overview(int id, String name, String description, Date startDate, Date endDate, String department,
            String process_type, String priority, double energy_all, double enegry_month) {
        this.id = id;
        this.Name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Department = department;
        this.process_type = process_type;
        this.priority = priority;
        this.energy_all = energy_all;
        this.enegry_month = enegry_month;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }


	public void setId(Integer id2) {
		this.id = id2;
		
	}
}
