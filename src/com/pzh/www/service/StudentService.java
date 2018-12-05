package com.pzh.www.service;

import java.util.List;
import java.util.Map;

import com.pzh.www.constant.MyConstant;
import com.pzh.www.dao.StudentDao;
import com.pzh.www.dao.impl.StudentDaoImpl;
import com.pzh.www.po.Student;

/**
 * 对学生表的service类
 * @author Pan梓涵
 * 创建时间：2018/4/15 16:28
 */
public class StudentService {

    private static final StudentService INSTANCE = new StudentService();

    private StudentService() {}

    public static StudentService getInstance() {
        return INSTANCE;
    }

    private StudentDao studentDao = new StudentDaoImpl();

    private int pageSize = MyConstant.PAGE_SIZE;                        // 每页显示记录的条数
    private int currentPage = MyConstant.PAGE_CURRENT;            // 当前页
    private int allPage = MyConstant.PAGE_ALL;                            // 总页数

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAllPage() {
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }

    /**
     * 查找所有学生资料
     */
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();

    }

    /**
     * 查找所有学生资料
     */
    public Map<Integer, Student> findAllStudentMap() {
        return studentDao.findAllStudentMap();
    }

    /**
     * 根据学生姓名的查询动作
     * @param studentName
     * @return
     */
    public List<Student> findStudentByName(String studentName) {
        return studentDao.findStudentByName(studentName);
    }

    /**
     * 根据学生学号的查询动作
     * @param studentNo
     * @return
     */
    public List<Student> findStudentByNo(String studentNo) {
        return studentDao.findStudentByNo(studentNo);
    }

    /*
     *
     */

    /**
     * 根据班级编号的查询动作
     * @param classNo
     * @return
     */
    public List<Student> findStudentByClassNo(int classNo) {
        return studentDao.findStudentByClassNo(classNo);
    }

    /**
     * 添加学生资料
     * @param t
     * @return
     */
    public boolean addStudent(Student t) {
        return studentDao.addStudent(t);
    }

    /**
     * 更新 学生 资料动作
     * @param s
     * @return
     */
    public boolean updateStudent(Student s) {
        return studentDao.updateStudent(s);
    }

    /**
     * 删除 学生 资料动作
     * @param studentNo
     * @return
     */
    public boolean deleteStudent(String studentNo) {
        return studentDao.deleteStudent(studentNo);
    }

    /**
     * 学生登录验证
     * @param studentNo
     * @param studentPassword
     * @return
     */
    public boolean studentLogin(String studentNo, String studentPassword) {
        Student s = new Student();
        s.setStudentNo(studentNo);
        s.setStudentPassword(studentPassword);
        return studentDao.studentLogin(s);
    }

}
