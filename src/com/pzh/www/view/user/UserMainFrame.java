package com.pzh.www.view.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.pzh.www.view.CheckDialog;
import com.pzh.www.view.login.LoginFrame;
import com.pzh.www.constant.UserFrameConstant;
import com.pzh.www.po.Teacher;
import com.pzh.www.service.TeacherService;

/**
 * 用户登录成功显示的窗体
 * @author Pan梓涵
 *
 */
public class UserMainFrame extends JFrame{
	
	private JPanel panelNorth;						//北部面板
	private JPanel panelSouth;						//南部面板
	private JPanel panelNorthSecend;			//北部副面板
	private JScrollPane scrollPane;				//JScrollPane面板
	private JTable table;								//table表
	private DefaultTableModel tableModel; 	//表格模型
	private JButton addButton;						//增加按钮
	private JButton findButton;						//查找按钮
	private JButton firstPageButton;				//首页按钮
	private JButton trailerPageButton;			//尾页按钮
	private JButton lastPageButton;				//上一页按钮
	private JButton nextPageButton;				//下一页按钮
	private JButton exitButton;						//退出按钮
	private JButton findAllButton;					//老师列表按钮
	private List<Teacher> list = null;				//用于存放查询结果集
	private List<Teacher> listShow = null;		//用于存放展示结果集
	private Teacher teacher = null;				//用于存放老师对象
	private TeacherService teacherService;		//老师Action
	private JLabel pageLabel ;						//页码标签
	private JLabel titleLabel;							//标题标签
	
	public UserMainFrame(String userStr) {
		initialize();													//初始化窗口
		this.teacherService = TeacherService.getInstance(); //初始化teacherAction
		this.add(getPanelNorth(userStr));				//添加北部面板
		this.add(getPanelSouth()); 						//添加南部面板
		this.add(getScrollPane()); 							//添加中部面板
		this.setVisible(true);									//设置窗口可见
		this.setResizable(false);								//设置大小不可变
	}
	
	/*
	 * 初始化北部面板
	 */
	public JPanel getPanelNorth(String userStr) {
		titleLabel = new JLabel("欢迎  " + userStr + "  使用本系统！",JLabel.CENTER);//初始化顶部标签
		titleLabel.setFont(new Font("微软雅黑",Font.BOLD,16));									//设置顶部标签字体
		panelNorth = new JPanel();																			//出事北部面板
		panelNorth.setLayout(new GridLayout(2, 1));													//设置为网格布局
		panelNorth.add(titleLabel);																			//在北部面板添加标题
		panelNorth.add(getPanelNorthSecend());														//添加北部负面板
		panelNorth.setLocation(240, 10);																	//设置所在位置
		panelNorth.setBackground(new Color(121,205,241,0));									//设置北部面板的背景颜色
		panelNorth.setSize(360,80);																			//设置大小
		return panelNorth;																						//返回引用
	}
	
	/*
	 * 初始化南部面板
	 */
	public JPanel getPanelSouth() {
		panelSouth = new JPanel();											    //初始化						
		pageLabel = new JLabel("第  页/共  页");							//初始化页码标签
		pageLabel.setForeground(Color.BLACK);							//设置页码标签的前景色
		pageLabel.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);//设置页码标签的字体
		panelSouth.add(getFirstPageButton());								//在南部面板添加 首页 按钮
		panelSouth.add(getLastPageButton());								//在南部面板添加 上一页 按钮
		panelSouth.add(pageLabel);												//添加页码标签
		panelSouth.add(getNextPageButton());								//在南部面板添加 下一页 按钮
		panelSouth.add(getTrailerPageButton());							//在南部面板添加 尾页 按钮
		panelSouth.add(getExitButton());										//在南部面板添加 退出 按钮
		panelSouth.setLocation(200, 420);										//设置南部面板位置
		panelSouth.setSize(450, 70);												//设置南部面板大小
		panelSouth.setBackground(new Color(210,206,180));			//设置南部面板背景颜色
		return panelSouth;
	}

	/*
	 * 初始化北部副面板
	 */
	public JPanel getPanelNorthSecend() {
		panelNorthSecend = new JPanel();					//初始化北部副面板
		panelNorthSecend.add(getFindButton());			//在北部副面板添加 查找 按钮
		panelNorthSecend.add(getFindAllButton());		//在北部副面板添加 老师列表 按钮
		panelNorthSecend.add(getAddButton());			//在北部副面板添加 增加 按钮
		panelNorthSecend.setBackground(new Color(121,205,241,0));//设置背景颜色
		return panelNorthSecend;
	}
	
	/*
	 * 初始化ScrollPane面板
	 */
	public JScrollPane getScrollPane() {
		scrollPane = new JScrollPane(getTable());			//初始化滚动面板
		scrollPane.setLocation(65, 100);							//设置滚动面板的位置
		scrollPane.setSize(760, 275);								//设置滚动面板的大小
		scrollPane.setBackground(Color.white);			//设置滚动面板的背景颜色
		return scrollPane;
	}
	
	/*
	 * 初始化Table
	 */
	public JTable getTable() {
		table = new JTable(getTableModel());												//创建表格
		table.setRowHeight(25); 																	//设置行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN); 	//设置表格的自动调整模式
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 	//设置为单选
		table.addMouseListener(new MouseAdapter() {
			//发生了单击事件
			@Override
			public void mouseClicked(MouseEvent e) {
				//获得被选中行的索引
				int selectedRow = table.getSelectedRow();
				UpdateTeacherDialog updateDialog = new UpdateTeacherDialog(getUserMainFrame(),
						tableModel.getValueAt(selectedRow, 0).toString());
				updateDialog.getTeacherNoLabel2().setText("" + tableModel.getValueAt(selectedRow, 0));
				updateDialog.getTeacherNameTextField().setText("" + tableModel.getValueAt(selectedRow, 1));
				updateDialog.getTeacherGradeTextField().setText("" + tableModel.getValueAt(selectedRow, 2));
				updateDialog.getTeacherClassTextField().setText("" + tableModel.getValueAt(selectedRow, 3));
				updateDialog.getTeacherPhoneTextField().setText("" + tableModel.getValueAt(selectedRow, 4));
				updateDialog.getTeacherEmailTextField().setText("" + tableModel.getValueAt(selectedRow, 5));
				updateDialog.setVisible(true);
			}
		});
		return table;
	}
	
	/*
	 * 初始化表格模型
	 */
	public DefaultTableModel getTableModel() {
		String[] columnName = UserFrameConstant.USER_MAIN_FRAME_TABLEMODEL_COLUMNAME;  //表格模型的标题
		String[][] tableValues = null;																											//表格模型的内容
		//设置单元格不可被编辑
		tableModel = new DefaultTableModel(tableValues,columnName) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
		return tableModel;
	}

	/*
	 * 初始化 添加 按钮
	 */
	public JButton getAddButton() {
		addButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_ADDBUTTON);											
		addButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);	//设置添加按钮的字体
		addButton.setBackground(new Color(1,158,151));					//设置按钮的背景颜色
		addButton.setForeground(Color.WHITE);								//设置按钮的字体颜色
		addButton.setLocation(50, 50);												//设置位置
		addButton.setSize(50, 50);														//设置大小
		addButton.addActionListener(new ActionListener() {
			//增加老师 按钮的监听事件
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddTeacherDialog(getUserMainFrame()).setVisible(true);
			}
		});
		return addButton;
	}

	/*
	 * 初始化 查找 按钮
	 */
	public JButton getFindButton() {
		findButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_FINDBUTTON);									
		findButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);	//设置字体
		findButton.setBackground(new Color(1,158,151));					//设置查找按钮的背景颜色
		findButton.setForeground(Color.WHITE);								//设置查找按钮的字体颜色
		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FindTeacherDialog(getUserMainFrame());
			}
		});
		return findButton;
	}
	
	/*
	 * 初始化 首页 按钮
	 */
	public JButton getFirstPageButton() {
		firstPageButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_FIRSTPAGE_BUTTON);								
		firstPageButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);	//字体
		firstPageButton.setBackground(new Color(1,158,151));					//背景颜色
		firstPageButton.setForeground(Color.WHITE);								//字体颜色
		firstPageButton.addActionListener(new PageButtonActionListener());
		return firstPageButton;
	}

	/*
	 * 初始化 尾页 按钮
	 */
	public JButton getTrailerPageButton() {
		trailerPageButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_TRAILERPAGE_BUTTON);				
		trailerPageButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);	//字体
		trailerPageButton.setBackground(new Color(1,158,151));				//背景颜色
		trailerPageButton.setForeground(Color.WHITE);							//字体颜色
		trailerPageButton.addActionListener(new PageButtonActionListener());
		return trailerPageButton;
	}
	
	/*
	 * 初始化 上一页 按钮
	 */
	public JButton getLastPageButton() {
		lastPageButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_LASTPAGE_BUTTON);					
		lastPageButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);//字体
		lastPageButton.setBackground(new Color(1,158,151));				//背景颜色
		lastPageButton.setForeground(Color.WHITE);							//字体颜色
		lastPageButton.addActionListener(new PageButtonActionListener());
		return lastPageButton;
	}

	/*
	 * 初始化 下一页 按钮
	 */
	public JButton getNextPageButton() {
		nextPageButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_NEXTPAGE_BUTTON);						
		nextPageButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);		//字体
		nextPageButton.setBackground(new Color(1,158,151));						//背景颜色
		nextPageButton.setForeground(Color.WHITE);									//字体颜色
		nextPageButton.addActionListener(new PageButtonActionListener());
		return nextPageButton;
	}
	
	/*
	 * 初始化 退出 按钮
	 */
	public JButton getExitButton() {
		exitButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_EXIT_BUTTON);		
		exitButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);	//字体
		exitButton.setBackground(new Color(1,158,151));					//背景颜色
		exitButton.setForeground(Color.WHITE);								//字体颜色
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);								//设置不可见
				dispose();											
				JFrame login = new LoginFrame();
				login.setVisible(true);						//设置可见
			}
		});
		return exitButton;
	}

	/*
	 * 初始化  老师列表 按钮
	 */
	public JButton getFindAllButton() {
		findAllButton = new JButton(UserFrameConstant.USER_MAIN_FRAME_FINDALL_BUTTON);	//初始化
		findAllButton.setFont(UserFrameConstant.USER_MAIN_FRAME_FONT);	//字体
		findAllButton.setBackground(new Color(1,158,151));					//背景颜色
		findAllButton.setForeground(Color.WHITE);								//字体颜色
		findAllButton.addActionListener(new ActionListener() {
			//老师列表 按钮的监听事件
			@Override
			public void actionPerformed(ActionEvent e) {
				setList(teacherService.findAllTeacher());
			}
		});
		return findAllButton;
		
	}
	
	/*
	 * set查询到的结果集
	 */
	public void setList(List<Teacher> list) {
		this.list = list;																						//查询结果集
		teacherService.setCurrentPage(1);															//设置当前页码
		if(list.size() % teacherService.getPageSize() == 0) {									//设置总页码
			teacherService.setAllPage(list.size() / teacherService.getPageSize());
		}else {
			teacherService.setAllPage(list.size() / teacherService.getPageSize() + 1);
		}
		if(list.size() > teacherService.getPageSize()) {											//设置展示结果集ListShow
			listShow = list.subList(0, teacherService.getPageSize());
		}else {
			listShow = list.subList(0, list.size());
		}
		setDefaultTableModel(listShow);															//将结果集展示在表格中
		pageLabel.setText("第 " + teacherService.getCurrentPage() + " 页/共 " + teacherService.getAllPage() + " 页");//设置页码标签
	}
	
	/*
	 * 返回一个本类对象的引用
	 */
	public UserMainFrame getUserMainFrame() {
		return this;
	}
	
	public TeacherService getTeacherService() {
		return teacherService;
	}

	/*
	 * 初始化登录成功窗体
	 */
	private void initialize() {
		this.setContentPane(new UserMainPanel());
		this.setTitle("信息管理系统");		 							 //设置窗体的标题
		this.setBounds(50, 50, 903, 534);							 //设置窗体的位置和大小
		this.setLayout(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗体的默认关闭方式
	}
	
	public void setDefaultTableModel(List<Teacher> list) {
		if(list != null) {
			tableModel.setRowCount(0);										//清除原先表格数据
			int i = 0;
			while(i < list.size()) {
				teacher = list.get(i);												//获取List容器中的老师对象
				String[] rowValues = {teacher.getTeacherNo(),
									  teacher.getTeacherName(),
									  teacher.getTeacherGrade(),
									  teacher.getTeacherClass(),
									  teacher.getTeacherPhone(),
									  teacher.getTeacherEmail()
									  };														//获取老师的相关数据
				tableModel.addRow(rowValues);							//添加数据到表格中
				i++;
			}
		}
	}

	/*
	 * pageButton的监听事件
	 */
	class PageButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//首页按钮的监听事件
			if(e.getActionCommand().equals(UserFrameConstant.USER_MAIN_FRAME_FIRSTPAGE_BUTTON)) {
				teacherService.setCurrentPage(1);
				if(list.size() > teacherService.getPageSize()) {
					listShow = list.subList(0, teacherService.getPageSize());
				}else {
					listShow = list.subList(0, list.size());
				}
				pageLabel.setText("第 " + teacherService.getCurrentPage() + " 页/共 " + teacherService.getAllPage() + " 页");
				setDefaultTableModel(listShow);
			}
			//尾页按钮的监听事件
			if(e.getActionCommand().equals(UserFrameConstant.USER_MAIN_FRAME_TRAILERPAGE_BUTTON)) {
				listShow = list.subList((teacherService.getAllPage()-1) * teacherService.getPageSize(),
						list.size());
				teacherService.setCurrentPage(teacherService.getAllPage());
				pageLabel.setText("第 " + teacherService.getCurrentPage() + " 页/共 " + teacherService.getAllPage() + " 页");
				setDefaultTableModel(listShow);
			}
			//上一页按钮的监听事件
			if(e.getActionCommand().equals(UserFrameConstant.USER_MAIN_FRAME_LASTPAGE_BUTTON)) {
				if(teacherService.getCurrentPage() == 1) {
					new CheckDialog(getUserMainFrame(), "已经是第一页！").setVisible(true);
				}else {
					teacherService.setCurrentPage(teacherService.getCurrentPage() - 1);
					listShow = list.subList((teacherService.getCurrentPage()-1) * teacherService.getPageSize(),
							teacherService.getCurrentPage() * teacherService.getPageSize());
					pageLabel.setText("第 " + teacherService.getCurrentPage() + " 页/共 " + teacherService.getAllPage() + " 页");
					setDefaultTableModel(listShow);
				}
			}
			//下一页按钮的监听事件
			if(e.getActionCommand().equals(UserFrameConstant.USER_MAIN_FRAME_NEXTPAGE_BUTTON)) {
				if(teacherService.getCurrentPage() == teacherService.getAllPage()) {
					new CheckDialog(getUserMainFrame(), "已经是最后一页！").setVisible(true);
				}else {
					teacherService.setCurrentPage(teacherService.getCurrentPage() + 1);
					if(teacherService.getCurrentPage() * teacherService.getPageSize() > list.size()) {
						listShow = list.subList((teacherService.getCurrentPage()-1) * teacherService.getPageSize(),
								list.size());
					}else {
						listShow = list.subList((teacherService.getCurrentPage()-1) * teacherService.getPageSize(),
								teacherService.getCurrentPage() * teacherService.getPageSize());
					}
					pageLabel.setText("第 " + teacherService.getCurrentPage() + " 页/共 " + teacherService.getAllPage() + " 页");
					setDefaultTableModel(listShow);
				}
			}
		}
		
	}
}
