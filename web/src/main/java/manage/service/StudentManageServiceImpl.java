package manage.service;


import manage.entity.StudentInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
@Service
public class StudentManageServiceImpl implements StudentManageService {
    private static final Logger logger = LoggerFactory.getLogger(StudentManageServiceImpl.class);
    private String name;


    public StudentInfo queryStudentInfo() {
        logger.info("querying");
        return new StudentInfo();
    }

    public void updateStudentInfo(StudentInfo stuentInfo) {
        logger.info("updating");
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
