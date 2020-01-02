package ie.gmit.sw;

public class Request {
    String query;
    String taskNumber;

    public String getQuery() {
        return query;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public Request(String query, String taskNumber) {
        this.query = query;
        this.taskNumber = taskNumber;
    }
}
