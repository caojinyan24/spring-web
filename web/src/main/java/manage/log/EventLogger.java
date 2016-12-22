package manage.log;

import org.slf4j.ext.EventData;

/**
 * Created by jinyancao on 12/21/16.
 */
public class EventLogger {
    private  EventData eventData;

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }
}
