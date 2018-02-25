package mawaqaajo.com.coffeedistrict.model;

import org.json.JSONArray;

/**
 * Created by HP on 12/31/2017.
 */

public class ScheduleModle {

    private String scheduleId;
    private String scheduleDate;
    private JSONArray scheduleTime;

    public ScheduleModle(String scheduleId, String scheduleDate) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
    }

    public ScheduleModle(String scheduleId, String scheduleDate, JSONArray scheduleTime) {
        this.scheduleId = scheduleId;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public JSONArray getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(JSONArray scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
