package TimeOff;

public class TimeOffEntity {
    public String name;
    public String id;
    public String start;
    public String end;
    public String status;

    public void CreateTimeOffEntity(String name, String id, String start, String end, String status){
        this.name = name;
        this.id = id;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public String toString(){
        return name + "," +
                id + "," +
                start + "," +
                end + "," +
                status;
    }
}
