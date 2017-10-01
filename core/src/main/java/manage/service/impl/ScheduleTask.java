package manage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jinyan on 8/19/17 1:48 PM.
 */
@Component
public class ScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    @Scheduled(fixedRate = 3000)
    public void schedule() {
        System.out.println("executing:" + System.currentTimeMillis());
    }

}
