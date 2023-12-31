package TimeOff.UseCases;

import TimeOff.DataAccess.AcceptTimeOff;
import TimeOff.DataAccess.RejectTimeOff;
import TimeOff.DataAccess.RequestTimeOff;
import TimeOff.Entities.TimeOffEntity;

import javax.swing.*;

/**
 * The ModifyTimeOff class provides a baseline handler to manage all the various use cases provided by the Time Off
 * request system and manages the usage of core functionality.
 */

public class ModifyTimeOff {

    public void Modify(String str, JFrame frame, String name, String id, String start, String end){
        TimeOffEntity request = new TimeOffEntity();
        String db = "data/Database.csv";
        if (str.equals("Request")){
            request.CreateTimeOffEntity(name, id, start, end, "Pending");
            new RequestTimeOff().Submit(frame, request, db);
        }
        else if (str.equals("Approve")){
            request.CreateTimeOffEntity(name, id, start, end, "Approved");
            new AcceptTimeOff().Approve(frame, request, db);
        }
        else if (str.equals("Deny")){
            request.CreateTimeOffEntity(name, id, start, end, "Denied");
            new RejectTimeOff().Deny(frame, request, db);
        }
    }
}
