package repo.dtos;

public class OverviewDto {

    private String id;

    private String parentProcess;

    private String startKnoten;

    private String endKnoten;

    private int energySumYear;

    private String processType;

    private String department;

    private String processDescription;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentProcess() {
        return parentProcess;
    }

    public void setParentProcess(String parentProcess) {
        this.parentProcess = parentProcess;
    }

    public String getStartKnoten() {
        return startKnoten;
    }

    public void setStartKnoten(String startKnoten) {
        this.startKnoten = startKnoten;
    }

    public String getEndKnoten() {
        return endKnoten;
    }

    public void setEndKnoten(String endKnoten) {
        this.endKnoten = endKnoten;
    }

    public int getEnergySumYear() {
        return energySumYear;
    }

    public void setEnergySumYear(int energySumYear) {
        this.energySumYear = energySumYear;
    }

    public String getProcessType() {
        return processType;
    }

    public void setProcessType(String processType) {
        this.processType = processType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }
}
