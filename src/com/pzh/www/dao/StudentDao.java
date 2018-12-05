package com.pzh.www.dao;

import java.util.List;
import java.util.Map;

import com.pzh.www.po.Student;

/**
 * 学生业务逻辑接口
 *
 * @author Pan梓涵
 * 创建时间 2018/4/15
 */
public interface StudentDao {

    /**
     * 学生登录方法
     * @param s
     * @return
     */
    boolean studentLogin(Student s);

    /**
     *  查询所有学生资料
     * @return
     */
    List<Student> findAllStudent();

    /**
     * 查询所有学生资料
     * @return
     */
    Map<Integer, Student> findAllStudentMap();

    /**
     * 根据 id 查询学生资料
     * @param studentNo
     * @return
     */
    List<Student> findStudentByNo(String studentNo);

    /**
     * 根据 姓名 查询学生资料
     * @param studentName
     * @return
     */
    List<Student> findStudentByName(String studentName);

    /**
     *  根据班级编号查询学生资料
     * @param classNo
     * @return
     */
    List<Student> findStudentByClassNo(int classNo);

    /**
     * 添加学生资料
     * @param s
     * @return
     */
    boolean addStudent(Student s);

    /**
     * 更新学生资料
     * @param s
     * @return
     */
    boolean updateStudent(Student s);

    /**
     * 删除学生资料
     * @param studentNo
     * @return
     */
    boolean deleteStudent(String studentNo);
}
