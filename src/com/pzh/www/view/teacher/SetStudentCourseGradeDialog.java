package com.pzh.www.view.teacher;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.pzh.www.constant.TeacherFrameConstant;
import com.pzh.www.po.SelectCourse;
import com.pzh.www.po.Student;
import com.pzh.www.view.CheckDialog;

/**
 * 设置学生课程成绩窗口
 * @author Pan梓涵
 */
public class SetStudentCourseGradeDialog extends JDialog {
	
	private JScrollPane jscrollPane;
	private JTable table;									//table表
	private DefaultTableModel tableModel;   //表格模型
	private Map<Integer,Student> studentMap;
	private Map<Integer,SelectCourse> selectCourseMap;
	private MyTeachCourseDialog myTeachCourseDialog;
	private int courseId;
	private JLabel studentIdLabel;
	private JLabel studentNoLabel;
	private JLabel studentNameLabel;
	private JLabel gradeLabel;
	private JTextField studentIdTextField;
	private JTextField studentNoTextField;
	private JTextField studentNameTextField;
	private JTextField gradeTextField;
	private JPanel setGradePanel;
	private JButton updateButton;
	
	public SetStudentCourseGradeDialog() {
		
	}
	
	public SetStudentCourseGradeDialog(JDialog dialog,int courseId) {
		super(dialog,"设置选课成绩窗口",false);
		if(dialog instanceof MyTeachCourseDialog) {
			myTeachCourseDialog = (MyTeachCourseDialog) dialog;
		}
		this.courseId = courseId;
		this.init();
		studentMap = myTeachCourseDialog.getTeacherMainFrame().getStudentService().findAllStudentMap();
		selectCourseMap = myTeachCourseDialog.
				getTeacherMainFrame().
                getSelectCourseService().
				findSelectCourseByCourseId(courseId);
		showTable(selectCourseMap, studentMap);
		this.setResizable(false);
	}
	
	private void initJscrollPane() {
		this.jscrollPane = new JScrollPane(table);
		jscrollPane.setSize(400, 240);
		jscrollPane.setLocation(40, 40);
	}
	
	private void initTable() {
		table = new JTable(tableModel);													//创建表格
		table.setRowHeight(25); 																//设置行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); //设置表格的自动调整模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //设置为单选
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				studentIdTextField.setText(tableModel.getValueAt(selectedRow, 0) + "");
				studentNoTextField.setText(tableModel.getValueAt(selectedRow, 1) + "");
				studentNameTextField.setText(tableModel.getValueAt(selectedRow, 2) + "");
				gradeTextField.setText(tableModel.getValueAt(selectedRow, 3) + "");
			}
		});
	}
	
	private void initTableModel() {
		String[] columnName = TeacherFrameConstant.SET_STUDENT_COURSE_GRADE_TABLEMODEL_COLUMNNAME;
		//设置单元格不可被编辑
				tableModel = new DefaultTableModel(null,columnName) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}	
				};
	}
	
	private void initLabel() {
		studentIdLabel = new JLabel("学生编号:");
		studentNoLabel = new JLabel("学生学号:");
		studentNameLabel = new JLabel("学生姓名:");
		gradeLabel = new JLabel("成绩:");
		
		studentIdLabel.setFont(TeacherFrameConstant.SET_STUDENT_COURSE_GRADE_LABEL_FONT);
		studentNoLabel.setFont(TeacherFrameConstant.SET_STUDENT_COURSE_GRADE_LABEL_FONT);
		studentNameLabel.setFont(TeacherFrameConstant.SET_STUDENT_COURSE_GRADE_LABEL_FONT);
		gradeLabel.setFont(TeacherFrameConstant.SET_STUDENT_COURSE_GRADE_LABEL_FONT);
	}
	
	private void initTextField() {
		studentIdTextField = new JTextField(15);
		studentNoTextField= new JTextField(15);
		studentNameTextField= new JTextField(15);
		gradeTextField= new JTextField(15);
		
		studentIdTextField.setEditable(false);
		studentNoTextField.setEditable(false);
		studentNameTextField.setEditable(false);
	}
	
	private void initUpdateButton() {
		updateButton = new JButton(TeacherFrameConstant.SET_STUDENT_COURSE_GRADE_UPDATE_BUTTON);
		updateButton.setSize(100, 30);
		updateButton.setLocation(580, 220);
		updateButton.addActionListener(e -> {
			if(checkTextField()) {
				if(myTeachCourseDialog.getTeacherMainFrame().getSelectCourseService().setStudentGrade(
						Integer.parseInt(studentIdTextField.getText()), courseId, Integer.parseInt(gradeTextField.getText())
						)) {
					new CheckDialog(getSetStudentCourseGradeDialog(), "保存成功").setVisible(true);
					selectCourseMap = myTeachCourseDialog
							.getTeacherMainFrame()
							.getSelectCourseService()
							.findSelectCourseByCourseId(courseId);
					showTable(selectCourseMap, studentMap);
				}

			}
		});
	}
	
	private void initSetGradePanel() {
		setGradePanel = new JPanel();
		setGradePanel.setLayout(new GridLayout(5, 2));
		setGradePanel.add(studentIdLabel);
		setGradePanel.add(studentIdTextField);
		setGradePanel.add(studentNoLabel);
		setGradePanel.add(studentNoTextField);
		setGradePanel.add(studentNameLabel);
		setGradePanel.add(studentNameTextField);
		setGradePanel.add(gradeLabel);
		setGradePanel.add(gradeTextField);
		setGradePanel.setSize(200, 130);
		setGradePanel.setLocation(480, 80);
	}

	private void init() {
		this.initTableModel();
		this.initTable();
		this.initJscrollPane();
		this.initLabel();
		this.initTextField();
		this.initUpdateButton();
		this.initSetGradePanel();
		this.setLayout(null);
		this.add(jscrollPane);
		this.add(setGradePanel);
		this.add(updateButton);
		this.setSize(765, 360);
		this.setLocation(100, 100);
		this.setVisible(true);
	}

	/**
	 * 展示已选改课的学生数据
	 */
	private void showTable(Map<Integer, SelectCourse> selectCourseMap, Map<Integer, Student> studentMap) {
		tableModel.setRowCount(0);
		Set<Integer> set = selectCourseMap.keySet();
		for (Integer integer : set) {
			Student student = studentMap.get(integer);
			String[] rowValues = {
					student.getId() + "",
					student.getStudentNo(),
					student.getStudentName(),
					selectCourseMap.get(integer).getGrade() + ""
			};
			tableModel.addRow(rowValues);
		}
	}
	
	private SetStudentCourseGradeDialog getSetStudentCourseGradeDialog() {
		return this;
	}

	private boolean checkTextField() {
		if(studentIdTextField.getText().trim().length() == 0) {
			new CheckDialog(getSetStudentCourseGradeDialog(), "未选择学生").setVisible(true);
			return false;
		}
		if(gradeTextField.getText().trim().length() == 0) {
			new CheckDialog(getSetStudentCourseGradeDialog(), "未设置成绩").setVisible(true);
			return false;
		}
		return  true;
	}
}
