package com.pzh.www.view.teacher;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.pzh.www.service.CourseService;
import com.pzh.www.service.SelectCourseService;
import com.pzh.www.constant.TeacherFrameConstant;
import com.pzh.www.po.MyClass;
import com.pzh.www.po.Student;
import com.pzh.www.po.Teacher;
import com.pzh.www.service.MyClassService;
import com.pzh.www.service.StudentService;
import com.pzh.www.service.TeacherService;
import com.pzh.www.view.CheckDialog;
import com.pzh.www.view.login.LoginFrame;

/**
 * 老师界面主窗口
 * @author Pan梓涵
 */
public class TeacherMainFrame extends JFrame{
	
	private JPanel panelNorthMain;					//北部主面板
	private JPanel panelSouth;							//南部面板
	private JPanel panelNorthMinor;					//北部副面板
	private JScrollPane scrollPane;					//JScrollPane面板
	private JTable table;									//table表
	private DefaultTableModel tableModel;   	//表格模型
	private JButton addButton;							//增加按钮
	private JButton findButton;							//查找按钮
	private JButton firstPageButton;					//首页按钮
	private JButton trailerPageButton;				//尾页按钮
	private JButton lastPageButton;					//上一页按钮
	private JButton nextPageButton;					//下一页按钮
	private JButton exitButton;							//退出按钮
	private JButton myTeachCourseButton;		//我的教授课程按钮
	private JButton findAllStudentButton;			//学生列表按钮
	
	private List<Student> list;							//用于存放查询结果集
	private List<Student> listShow;					//用于存放展示结果集
	private JLabel pageLabel ;							//页码标签
	private JLabel titleLabel;								//标题标签
	private Teacher teacher;								
	private StudentService studentService;
	private TeacherService teacherService;
	private com.pzh.www.service.CourseService courseService;
	private MyClassService myClassService;
	private SelectCourseService selectCourseService;
	private List<MyClass> myClassList;
	
	public TeacherMainFrame(String userStr) {
		initialize();
		this.initializeButton();
		this.initializeTableAndModel();
		this.initializeAction();
		this.teacher = teacherService.findTeacherByNo(userStr).get(0);
		this.initializePanel(teacher.getTeacherName());
		this.add(panelNorthMain);				//添加北部面板
		this.add(panelSouth); 						//添加南部面板
		this.add(scrollPane); 							//添加中部面板
		this.setVisible(true);
	}
	
	/**
	 * 初始化Action；
	 */
	public void initializeAction() {
		this.teacherService = TeacherService.getInstance();
		this.studentService = StudentService.getInstance();
		this.myClassService = MyClassService.getInstance();
		this.courseService = CourseService.getInstance();
		this.selectCourseService = SelectCourseService.getInstance();
		this.myClassList = myClassService.findAllClass();
	}
	
	/**
	 * 初始化北部主面板
	 */
	public void setPanelNorthMain(String userStr) {
		titleLabel = new JLabel("欢迎： " + userStr + "  使用本系统！",JLabel.CENTER);
		titleLabel.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_PANEL_FONT);
		panelNorthMain = new JPanel();
		panelNorthMain.setLayout(new GridLayout(2, 1));
		panelNorthMain.add(titleLabel);
		panelNorthMain.add(panelNorthMinor);
		panelNorthMain.setLocation(240, 10);
		panelNorthMain.setBackground(new Color(121,205,241,0));
		panelNorthMain.setSize(360,80);
	}

	/**
	 * 初始化南部面板
	 */
	public void setPanelSouth() {
		panelSouth = new JPanel();
		pageLabel = new JLabel();
		pageLabel.setForeground(Color.BLACK);
		pageLabel.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_PANEL_FONT);
		panelSouth.add(firstPageButton);				//在南部面板添加 首页 按钮
		panelSouth.add(lastPageButton);				//在南部面板添加 上一页 按钮
		panelSouth.add(pageLabel);
		panelSouth.add(nextPageButton);				//在南部面板添加 下一页 按钮
		panelSouth.add(trailerPageButton);			//在南部面板添加 尾页 按钮
		panelSouth.add(exitButton);						//在南部面板添加 退出 按钮
		panelSouth.setLocation(200, 420);
		panelSouth.setSize(450, 70);
		panelSouth.setBackground(new Color(210,206,180));
	}
	
	/*
	 * 初始化北部副面板
	 */
	public void setPanelNorthMinor() {
		panelNorthMinor = new JPanel();
		panelNorthMinor.add(findButton);						//在北部副面板添加 查找 按钮
		panelNorthMinor.add(findAllStudentButton);		//在北部副面版添加 学生列表 按钮
		panelNorthMinor.add(myTeachCourseButton);		//在北部副面板添加 教授课程 按钮
		panelNorthMinor.add(addButton);						//在北部副面板添加 增加 按钮
		panelNorthMinor.setBackground(new Color(121,205,241,0));
	}

	/*
	 * 初始化ScrollPane面板
	 */
	public void setJScrollPane() {
		scrollPane = new JScrollPane(table);
		scrollPane.setLocation(65, 100);
		scrollPane.setSize(760, 275);
		scrollPane.setBackground(Color.white);
	}
	
	/*
	 * 初始化Table
	 */
	public void setTable() {
		table = new JTable(tableModel);															//创建表格
		table.setRowHeight(25); 																		//设置行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); 		//设置表格的自动调整模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 		//设置为单选
		table.addMouseListener(new MouseAdapter() {
			//发生了单击事件
			@Override
			public void mouseClicked(MouseEvent e) {
				//获得被选中行的索引
				int selectedRow = table.getSelectedRow();
				UpdateStudentDialog updateStudentDialog = new UpdateStudentDialog(getTeacherMainFrame(), 
						tableModel.getValueAt(selectedRow, 0).toString());
				updateStudentDialog.getStudentNoLabel2().setText("" + tableModel.getValueAt(selectedRow, 0));
				updateStudentDialog.getStudentNameTextField().setText("" + tableModel.getValueAt(selectedRow, 1));
				updateStudentDialog.getStudentGenderTextField().setText("" + tableModel.getValueAt(selectedRow, 2));
				updateStudentDialog.getStudentPhoneTextField().setText("" + tableModel.getValueAt(selectedRow, 3));
				updateStudentDialog.getStudentEmailTextField().setText("" + tableModel.getValueAt(selectedRow, 4));
				updateStudentDialog.setVisible(true);
			}
			
		});
	}
	
	/*
	 * 初始化表格模型
	 */
	public void setTableModel() {
		String[] columnName = TeacherFrameConstant.TEACHER_MAIN_FRAME_TABLEMODEL_COLUMNNAME;
		String[][] tableValues = null;
		//设置单元格不可被编辑
		tableModel = new DefaultTableModel(tableValues,columnName)
		{
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
	}
	
	/*
	 * 初始化 添加 按钮
	 */
	public void setAddButton() {
		addButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_ADD_BUTTON);
		addButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		addButton.setBackground(new Color(1,158,151));
		addButton.setForeground(Color.WHITE);
		addButton.setLocation(50, 50);
		addButton.setSize(50, 50);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddStudentDialog(getTeacherMainFrame()).setVisible(true);;
			}
		});
	}
	
	/*
	 * 初始化 查找 按钮
	 */
	public void setFindButton() {
		findButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_FIND_BUTTON);
		findButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		findButton.setBackground(new Color(1,158,151));
		findButton.setForeground(Color.WHITE);
		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FindStudentDialog(getTeacherMainFrame());
			}
		});
	}
	
	/*
	 * 初始化我的教授课程按钮
	 */
	public void setMyTeachCourseButton() {
		myTeachCourseButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_TEACH_COURSE_BUTTON);
		myTeachCourseButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		myTeachCourseButton.setBackground(new Color(1,158,151));
		myTeachCourseButton.setForeground(Color.WHITE);
		myTeachCourseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MyTeachCourseDialog(getTeacherMainFrame()).setResizable(false);
			}
		});
	}
	
	/*
	 * 初始化 首页 按钮
	 */
	public void setFirstPageButton() {
		firstPageButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_FIRST_PAGE_BUTTON);
		firstPageButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		firstPageButton.setBackground(new Color(1,158,151));
		firstPageButton.setForeground(Color.WHITE);
		firstPageButton.addActionListener(new PageButtonActionListener());
	}
	
	/*
	 * 初始化 尾页 按钮
	 */
	public void setTrailerPageButton() {
		trailerPageButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_TRAILER_PAGE_BUTTON);
		trailerPageButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		trailerPageButton.setBackground(new Color(1,158,151));
		trailerPageButton.setForeground(Color.WHITE);
		trailerPageButton.addActionListener(new PageButtonActionListener());
	}
	
	/*
	 * 初始化 上一页 按钮
	 */
	public void setLastPageButton() {
		lastPageButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_LAST_PAGE_BUTTON);
		lastPageButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		lastPageButton.setBackground(new Color(1,158,151));
		lastPageButton.setForeground(Color.WHITE);
		lastPageButton.addActionListener(new PageButtonActionListener());
	}
	
	/*
	 * 初始化 下一页 按钮
	 */
	public void setNextPageButton() {
		nextPageButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_NEXT_PAGE_BUTTON);
		nextPageButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		nextPageButton.setBackground(new Color(1,158,151));
		nextPageButton.setForeground(Color.WHITE);
		nextPageButton.addActionListener(new PageButtonActionListener());
	}
	
	/*
	 * 初始化 退出 按钮
	 */
	public void setExitButton() {
		exitButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_EXIT_BUTTON);
		exitButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		exitButton.setBackground(new Color(1,158,151));
		exitButton.setForeground(Color.WHITE);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getTeacherMainFrame().setVisible(false);
				getTeacherMainFrame().dispose();
				new LoginFrame().setVisible(true);
			}
		});
	}
	
	/*
	 * 初始化  学生列表 按钮
	 */
	public void setFindAllStudentButton() {
		findAllStudentButton = new JButton(TeacherFrameConstant.TEACHER_MAIN_FRAME_FINDALL_STUDENT_BUTTON);
		findAllStudentButton.setFont(TeacherFrameConstant.TEACHER_MAIN_FRAME_BUTTON_FONT);
		findAllStudentButton.setBackground(new Color(1,158,151));
		findAllStudentButton.setForeground(Color.WHITE);
		findAllStudentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setListShow(studentService.findAllStudent());
			}
		});
	}
	
	/*
	 * 返回一个本类对象的引用
	 */
	public TeacherMainFrame getTeacherMainFrame() {
		return this;
	}
	
	public List<MyClass> getMyClassList() {
		return myClassList;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public CourseService getCourseService() {
		return courseService;
	}
	
	public SelectCourseService getSelectCourseService() {
		return selectCourseService;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	
	public List<Student> getList() {
		return list;
	}

	/*
	 * 初始所有按钮
	 */
	public void initializeButton() {
		setAddButton(); 							//增加按钮
		setFindButton(); 							//查找按钮
		setFirstPageButton(); 					//首页按钮
		setTrailerPageButton(); 					//尾页按钮
		setLastPageButton(); 					//上一页按钮
		setNextPageButton(); 					//下一页按钮
		setExitButton(); 							//退出按钮
		setFindAllStudentButton();			//学生列表按钮
		setMyTeachCourseButton(); 			//教授课程按钮
	}
	
	/*
	 * 初始表格及表格模型
	 */
	public void initializeTableAndModel() {
		setTableModel();           				//初始化表格模型
		setTable(); 									//初始化表格
	}
	
	/*
	 * 初始所有面板
	 */
	public void initializePanel(String userStr) {
		setPanelSouth(); 							//南部面板
		setPanelNorthMinor(); 					//北部副面板
		setPanelNorthMain(userStr); 		//北部主面板
		setJScrollPane(); 							//JScrollPane面板		
	}
	
	/*
	 * 初始化登录成功窗体
	 */
	private void initialize() {
		this.setContentPane(new TeacherMainPanel());
		this.setTitle("信息管理系统");		 							 //设置窗体的标题
		this.setBounds(50, 50, 903, 534);							 //设置窗体的位置和大小
		this.setLayout(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体的默认关闭方式
	}
	
	/*
	 * set listShow(设置展示的结果集)
	 */
	public void setListShow(List<Student> list) {
		this.list = list;
		studentService.setCurrentPage(1);
		if(list.size() % studentService.getPageSize() == 0) {
			studentService.setAllPage(list.size() / studentService.getPageSize());
		}else {
			studentService.setAllPage(list.size() / studentService.getPageSize() + 1);
		}
		if(list.size() > studentService.getPageSize()) {
			listShow = list.subList(0, studentService.getPageSize());
		}else {
			listShow = list.subList(0, list.size());
		}
		showTable(listShow);
		pageLabel.setText("第 " + studentService.getCurrentPage() + " 页/共 " + studentService.getAllPage() + " 页");
	}
	
	/*
	 * 展示数据到表格中
	 */
	public void showTable(List<Student> list) {
		tableModel.setRowCount(0);									//清除原先表格数据
		for (Student student : list) {
			String[] rowValues = {
					student.getStudentNo(),
					student.getStudentName(),
					student.getStudentGender(),
					student.getStudentPhone(),
					student.getStudentEmail(),
					myClassList.get(student.getClassId() - 1).getClassName()
			};
			tableModel.addRow(rowValues);			
		}	
	}
	
	/*
	 * pageButton的监听事件
	 */
	class PageButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//首页按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.TEACHER_MAIN_FRAME_FIRST_PAGE_BUTTON)) {
				studentService.setCurrentPage(1);
				if(list.size() > studentService.getPageSize()) {
					listShow = list.subList(0, studentService.getPageSize());
				}else {
					listShow = list.subList(0, list.size());
				}
				pageLabel.setText("第 " + studentService.getCurrentPage() + "页/共 " + studentService.getAllPage() + " 页");
				showTable(listShow);
			}
			//尾页按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.TEACHER_MAIN_FRAME_TRAILER_PAGE_BUTTON)) {
				listShow = list.subList((studentService.getAllPage()-1) * studentService.getPageSize(), list.size());
				studentService.setCurrentPage(studentService.getAllPage());
				pageLabel.setText("第 " + studentService.getCurrentPage() + "页/共 " + studentService.getAllPage() + " 页");
				showTable(listShow);
			}
			//上一页按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.TEACHER_MAIN_FRAME_LAST_PAGE_BUTTON)) {
				if(studentService.getCurrentPage() == 1) {
					new CheckDialog(getTeacherMainFrame(), "已经是第一页！").setVisible(true);
				}else {
					studentService.setCurrentPage(studentService.getCurrentPage() - 1);
					listShow = list.subList((studentService.getCurrentPage() - 1) * studentService.getPageSize(),
							studentService.getCurrentPage() * studentService.getPageSize());
					pageLabel.setText("第 " + studentService.getCurrentPage() + "页/共 " + studentService.getAllPage() + " 页");
					showTable(listShow);
				}
			}
			//下一页按钮的监听事件
			if(e.getActionCommand().equals(TeacherFrameConstant.TEACHER_MAIN_FRAME_NEXT_PAGE_BUTTON)) {
				if(studentService.getCurrentPage() == studentService.getAllPage()) {
					new CheckDialog(getTeacherMainFrame(), "已经是最后一页！").setVisible(true);
				}else {
					studentService.setCurrentPage(studentService.getCurrentPage() + 1);
					if(studentService.getCurrentPage() * studentService.getPageSize() > list.size()) {
						listShow = list.subList((studentService.getCurrentPage() - 1) * studentService.getPageSize(), list.size());
					}else {
						listShow = list.subList((studentService.getCurrentPage() - 1) * studentService.getPageSize(),
								studentService.getCurrentPage() * studentService.getCurrentPage());
					}
					pageLabel.setText("第 " + studentService.getCurrentPage() + "页/共 " + studentService.getAllPage() + " 页");
					showTable(listShow);
				}
			}
		}
		
	}
}
