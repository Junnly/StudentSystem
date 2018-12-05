package com.pzh.www.dao;

import java.util.List;
import java.util.Map;

import com.pzh.www.po.SelectCourse;

/**
 * 选课关系的接口
 *
 * @author Pan梓涵
 * 创建时间 2018/4/21
 */
public interface SelectCourseDao {

    /**
     * 根据学生id获取课程id
     * @param id 学生id
     * @return 课程id
     */
    int[] getCourseIdById(int id);

    /**
     * 根据课程id获取学生id
     * @param courseId 课程id
     * @return 学生id
     */
    int[] getStudentIdByCourseId(int courseId);

    /**
     * 根据学生id获取课程成绩
     * @param studentId 学生id
     * @return 课程成绩
     */
    List<SelectCourse> findSelectCourseByStudentId(int studentId);

    /**
     * 添加课程
     * @param sc 课程信息
     * @return 成功返回true,否则返回false
     */
    boolean addSelectCourse(SelectCourse sc);

    /**
     * 根据课程id获取选课表资料
     * @param courseId 课程id
     * @return 课表资料
     */
    Map<Integer, SelectCourse> findSelectCourseByCourseId(int courseId);

    /**
     * 设置学生成绩
     * @param selectCourse 学生成绩
     * @return 成功返回true,否则返回false
     */
    boolean setStudentGrade(SelectCourse selectCourse);

}
