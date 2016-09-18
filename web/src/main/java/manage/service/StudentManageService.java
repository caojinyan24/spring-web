package manage.service;


import manage.entity.StudentInfo;

/**
 * Created by jinyan.cao on 2016/4/18.
 */
public interface StudentManageService {
    StudentInfo queryStudentInfo();
    void setName(String name);
    String getName();
}
