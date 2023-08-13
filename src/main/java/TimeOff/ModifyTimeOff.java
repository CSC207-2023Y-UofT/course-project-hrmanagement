package TimeOff;

import javax.swing.*;

public class ModifyTimeOff {

    public void Modify(String str, JFrame frame, String name, String id, String start, String end){
        TimeOffEntity request = new TimeOffEntity();
        if (str.equals("Request")){
            request.CreateTimeOffEntity(name, id, start, end, "Pending");
            new RequestTimeOff().Submit(frame, request);
        }
        else if (str.equals("Approve")){
            request.CreateTimeOffEntity(name, id, start, end, "Approved");
            new AcceptTimeOff().Approve(frame, request);
        }
        else if (str.equals("Deny")){
            request.CreateTimeOffEntity(name, id, start, end, "Denied");
            new RejectTimeOff().Deny(frame, request);
        }
    }
}
