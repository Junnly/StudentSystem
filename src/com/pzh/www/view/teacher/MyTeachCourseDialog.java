package com.pzh.www.view.teacher;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.pzh.www.constant.TeacherFrameConstant;
import com.pzh.www.po.Course;
import com.pzh.www.view.CheckDialog;

/**
 * 教授课程窗口
 * @author Pan梓涵
 */
public class MyTeachCourseDialog extends JDialog{
	
	private JScrollPane jscrollPane;				//滚动面板
	private JTable table;								//table表
	private DefaultTableModel tableModel;  //表格模型
	private JButton addCourseButton;			//添加课程
	private JPanel eastPanel;							//东部面板
	private JLabel courseIdLabel;					//课程id标签
	private JTextField deleteTextField;			//用于删除的文本框
	private JButton deleteCourseButton;		//删除课程
	private JPanel addPanel;							//按钮面板
	private JLabel courseNameLabel;				//课程名字标签
	private JLabel courseHourJLabel;				//学时标签
	private JLabel creditJLabel;						//学分标签
	private JLabel siteJLabel;							//教学地点标签
	private JTextField courseNameTextField;  //课程名字文本框
	private JTextField courseHourTextField;	//学时文本框
	private JTextField creditTextField;			//学分文本框
	private JTextField siteTextField;				//教学地点文本框
	private TeacherMainFrame teacherMainFrame;//老师主界面的用
	private Course course;								//课程引用
	
	public MyTeachCourseDialog() {
		
	}
	
	public MyTeachCourseDialog(JFrame frame) {
		super(frame,"我的课程",true);
		if(frame instanceof TeacherMainFrame) {
			this.teacherMainFrame = (TeacherMainFrame) frame;
		}
		this.init();
		this.setLayout(null);
		this.add(jscrollPane);
		this.add(addPanel);
		this.add(addCourseButton);
		this.add(deleteCourseButton);
		this.add(eastPanel);
		this.setSize(765	, 360);
		this.setLocation(350, 100);
		this.showTable(
				this.teacherMainFrame.getCourseService().getCourseByTeacherId(teacherMainFrame.getTeacher().getId())
				);
		this.setVisible(true);
	}

	private void initTable() {
		table = new JTable(tableModel);													//创建表格
		table.setRowHeight(25); 																//设置行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); //设置表格的自动调整模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //设置为单选
		table.setRowSorter(new TableRowSorter<>(tableModel));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SetStudentCourseGradeDialog(getMyTeachCourseDialog(),
						Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0) + ""));
			}
			
		});
	}

	private void initTableModel() {
		String[] columnName = TeacherFrameConstant.MYTEACH_COURSE_DIALOG_TABLEMODEL_COLUMNNAME;
		//设置单元格不可被编辑
		tableModel = new DefaultTableModel(null,columnName) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
	}
	
	private void initJscrollPane() {
		jscrollPane = new JScrollPane(table);			//创建面板
		jscrollPane.setSize(400, 240);
		jscrollPane.setLocation(40, 40);
	}
	
	private void initEastPanel() {
		eastPanel = new JPanel();
		courseIdLabel = new JLabel("要删除的课程编号:");
		courseIdLabel.setFont(TeacherFrameConstant.MYTEACH_COURSE_DIALOG_LABEL_FONT);
		deleteTextField = new JTextField();
		eastPanel.setLayout(new GridLayout(1,2));
		eastPanel.add(courseIdLabel);
		eastPanel.add(deleteTextField);
		eastPanel.setSize(220,30);
		eastPanel.setLocation(480, 220);
	}
	
	private void initAddCourseButton() {
		addCourseButton = new JButton(TeacherFrameConstant.MYTEACH_COURSE_DIALOG_ADD_COURSE_BUTTON);
		addCourseButton.setSize(110, 30);
		addCourseButton.setLocation(590, 180);
		addCourseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkTextField()) {
					course = new Course();
					course.setCourseName(courseNameTextField.getText());
					course.setCourseHour(Integer.parseInt(courseHourTextField.getText()));
					course.setCredit(Integer.parseInt(creditTextField.getText()));
					course.setSite(siteTextField.getText());
					course.setGiveLensonTeacher(teacherMainFrame.getTeacher().getId());
					
					if(teacherMainFrame.getCourseService().addCourse(course)) {
						new CheckDialog(getMyTeachCourseDialog(), "添加成功！").setVisible(true);
						showTable(
								teacherMainFrame.getCourseService().getCourseByTeacherId(teacherMainFrame.getTeacher().getId())
								);
					};
					
				}
			}
		});
		
	}
	
	private void initDeleteCourseButton() {
		deleteCourseButton = new JButton(TeacherFrameConstant.MYTEACH_COURSE_DIALOG_DELETE_COURSE_BUTTON);
		deleteCourseButton.setSize(110, 30);
		deleteCourseButton.setLocation(590, 260);
		deleteCourseButton.addActionListener(e -> {
			if("".equals(deleteTextField.getText().trim())) {
				new CheckDialog(getMyTeachCourseDialog(), "删除文本框不能为空").setVisible(true);;
			}else {
				if(teacherMainFrame.getCourseService().deleteCourse(
						Integer.parseInt(deleteTextField.getText()),
						teacherMainFrame.getTeacher().getId())) {
					new CheckDialog(getMyTeachCourseDialog(), "删除成功！").setVisible(true);;
					showTable(
							teacherMainFrame.getCourseService().getCourseByTeacherId(teacherMainFrame.getTeacher().getId())
							);
				}
			}
		});
	}
	
	private void initAddPanel() {
		addPanel = new JPanel();
		addPanel.add(courseNameLabel);
		addPanel.add(courseNameTextField);
		addPanel.add(courseHourJLabel);
		addPanel.add(courseHourTextField);
		addPanel.add(creditJLabel);
		addPanel.add(creditTextField);
		addPanel.add(siteJLabel);
		addPanel.add(siteTextField);
		addPanel.setLayout(new GridLayout(4, 2));
		addPanel.setSize(220, 120);
		addPanel.setLocation(480, 50);
	}
	
	private void initLabel() {
		 courseNameLabel = new JLabel("课程名字:");
		 courseHourJLabel = new JLabel("学时:");
		 creditJLabel = new JLabel("学分:");
		 siteJLabel = new JLabel("地点:");
		 
		 courseNameLabel.setFont(TeacherFrameConstant.MYTEACH_COURSE_DIALOG_LABEL_FONT);
		 courseHourJLabel.setFont(TeacherFrameConstant.MYTEACH_COURSE_DIALOG_LABEL_FONT);
		 creditJLabel.setFont(TeacherFrameConstant.MYTEACH_COURSE_DIALOG_LABEL_FONT);
		 siteJLabel.setFont(TeacherFrameConstant.MYTEACH_COURSE_DIALOG_LABEL_FONT);
	}
	
	private void initTextField() {
		courseNameTextField = new JTextField(15);
		courseHourTextField = new JTextField(15);
		creditTextField = new JTextField(15);
		siteTextField = new JTextField(15);
	}
	
	private void init() {
		this.initTableModel();
		this.initTable();
		this.initAddCourseButton();
		this.initDeleteCourseButton();
		this.initJscrollPane();
		this.initLabel();
		this.initTextField();
		this.initAddPanel();
		this.initEastPanel();
	}
	
	private MyTeachCourseDialog getMyTeachCourseDialog() {
		return this;
	}

	TeacherMainFrame getTeacherMainFrame() {
		return teacherMainFrame;
	}

	/**
	 * 展示数据到表格中
	 */
	private void showTable(Map<Integer, Course> map) {
		tableModel.setRowCount(0);
		Set<Integer> set = map.keySet();
		for (Integer integer : set) {
			Course course =map.get(integer);
			String[] rowValues = {
					course.getCourseId()+"",
					course.getCourseName(),
					course.getCourseHour() + "",
					course.getCredit() + "",
					course.getSite()
					};
			tableModel.addRow(rowValues);
		}
	}
	
	/**
	 * 检查文本框
	 */
	private boolean checkTextField() {
		if("".equals(courseNameTextField.getText().trim())) {
			new CheckDialog(getMyTeachCourseDialog(), "课程名字不能为空！").setVisible(true);
			return false;
		}
		if("".equals(courseHourTextField.getText().trim())) {
			new CheckDialog(getMyTeachCourseDialog(), "学时不能为空！").setVisible(true);
			return false;
		}
		if("".equals(creditTextField.getText().trim())) {
			new CheckDialog(getMyTeachCourseDialog(), "学分不能为空！").setVisible(true);
			return false;
		}
		if("".equals(siteTextField.getText().trim())) {
			new CheckDialog(getMyTeachCourseDialog(), "教学地点不能为空！").setVisible(true);
			return false;
		}
		return true;
	}
	
	
}
