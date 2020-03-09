package physiCalculator;

// ����� ���
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.JOptionPane;

public class PhysiCalculator extends JFrame
{   
	// �ʿ� ���̾ƿ� ����
	JLabel topLabel, weight, height, age, sex;
	JTextField heightField, weightField, ageField;
	JPanel heightPanel, weightPanel, agePanel, sexPanel, submitPanel;
	JRadioButton male, female;
	ButtonGroup sexGroup;
	JButton submit;

	
    public PhysiCalculator() 
    {
    	// �������� �̸� ����
        super("PhysiCalculator");
        // ���̾ƿ� ��������� GridLayout���� ����
        setLayout( new GridLayout(0,1,5,5) );
        
        // �ֻ�ܿ� ǥ�� �� topLabel(JLabel) ��ü ���� / ��輱 ���� / ��� ����
        topLabel = new JLabel("��ü������ �Է��� �ּ���");
        topLabel.setBorder(new EtchedBorder());
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // sexPanel�� RadioButton ��ü ����
        sexPanel = new JPanel();
        male = new JRadioButton("����");
        female = new JRadioButton("����");
        
        // RadioButton �׷�ȭ�� ���� ButtonGruop ��ü ���� �� �׷�ȭ
        sexGroup = new ButtonGroup();
        male.setSelected(true);
        sexGroup.add(male);
        sexGroup.add(female);
        
        // "����" Label ��ü ������ Panel�� ����
        sex = new JLabel("����");
        sexPanel.add(sex);
        sexPanel.add(male);
        sexPanel.add(female);    
        
        // ���������� ��� ���� Panel, TextField, Label ��ü ���� �� Panel�� ����
        heightPanel = new JPanel();
        heightField = new JTextField(9);
        height = new JLabel("����");
        heightPanel.add(height);
        heightPanel.add(heightField);

        // ü�������� ��� ���� Panel, TextField, Label ��ü ���� �� Panel�� ����        
        weightPanel = new JPanel();
        weightField = new JTextField(9);
        weight = new JLabel("ü��");
        weightPanel.add( weight );
        weightPanel.add( weightField );
        
        // ���������� ��� ���� Panel, TextField, Label ��ü ���� �� Panel�� ����        
        agePanel=new JPanel();
        ageField = new JTextField(9);
        age = new JLabel("����");
        agePanel.add(age);
        agePanel.add(ageField);  
        
        // �Էµ� ������ ó���ϱ� ���� Panel, Button ��ü ���� Panel�� ����
        submitPanel = new JPanel();
        submit = new JButton("��� ����");
        submitPanel.add(submit);
        
        // Panel�� ���� �����ӿ� ����
        add(topLabel);
        add(sexPanel);
        add(heightPanel);
        add(weightPanel);
        add(agePanel);
        add(submitPanel);
        
        // ��Ʈ ����
        topLabel.setFont(new Font("���� ���", Font.BOLD, 15));
        sex.setFont(new Font("���� ���", Font.PLAIN, 14));
        height.setFont(new Font("���� ���", Font.PLAIN, 14));
        weight.setFont(new Font("���� ���", Font.PLAIN, 14));
        age.setFont(new Font("���� ���", Font.PLAIN, 14));
        male.setFont(new Font("���� ���", Font.PLAIN, 13));
        female.setFont(new Font("���� ���", Font.PLAIN, 13));
        submit.setFont(new Font("���� ���", Font.PLAIN, 13));
        UIManager.put("OptionPane.messageFont", new Font("���� ���", Font.PLAIN, 13));
        
        // submit ��ư�� ���� actionlistener �߰��� actionPerformed overriding
        submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) 
        	{
        	
        	// ��� field�� ä���� ���� �� ����
    		if(!heightField.getText().isEmpty() & !weightField.getText().isEmpty() & !ageField.getText().isEmpty()) 
    		{
    			
    			// ���ʴ�緮 ���
    			String message = "���ʴ�緮 : ";
    			if(male.isSelected()) {
    				message += Math.round(66 + 13.7 * Integer.parseInt(weightField.getText())
    						+ 5 * Integer.parseInt(heightField.getText())
    						- 6.5 * Integer.parseInt(ageField.getText()));
    			}
    			else message += Math.round(655 + 9.6 * Integer.parseInt(weightField.getText())
    						 + 1.8 * Integer.parseInt(heightField.getText())
    						 - 4.7 * Integer.parseInt(ageField.getText()));
    			
    			
    			// ��ü�������� ���
    			message += " kCal\n��ü�������� : ";
    			int BMI = Math.round(10000 * Integer.parseInt(weightField.getText()) / 
    					Integer.parseInt(heightField.getText()) / Integer.parseInt(heightField.getText()));
    			
    			if(BMI < 20)
    				message += BMI + " ��ü��";
    			else if(BMI < 24)
    				message += BMI + " ����ü��";
    			else if(BMI < 29)
    				message += BMI + " ��ü��";
    			else
    				message += BMI + " ��";
    			
    			
    			// ���� ���� ���뷮 ���
    			message += " \n���� ���� ���뷮 : ";
    			if(Integer.parseInt(weightField.getText()) <= 10)
    				message += Integer.parseInt(weightField.getText())*100;
    			else if(Integer.parseInt(weightField.getText()) <= 20)
    				message += 10*100 + (Integer.parseInt(weightField.getText())-10)*50;
    			else
    				message += 10*100 + 10*50 + (Integer.parseInt(weightField.getText())-20)*20;


    			// ���� ����ð� ���
    			message += " mL\n���� ����ð� : ";
    			if(ageField.getText().equals("1"))
    				message += "12 ~ 17 �ð�";
    			else if(ageField.getText().equals("2"))
    				message += "11 ~ 14 �ð�";
    			else if(Integer.parseInt(ageField.getText()) >= 3 && Integer.parseInt(ageField.getText()) <= 5)
    				message += "10 ~ 13 �ð�";
    			else if(Integer.parseInt(ageField.getText()) >= 6 && Integer.parseInt(ageField.getText()) <= 13)
    				message += "9 ~ 11 �ð�";
    			else if(Integer.parseInt(ageField.getText()) >= 14 && Integer.parseInt(ageField.getText()) <= 17)
    				message += "8 ~ 10 �ð�";
    			else if(Integer.parseInt(ageField.getText()) >= 18 && Integer.parseInt(ageField.getText()) <= 64)
    				message += "7 ~ 9 �ð�";
    			else
    				message += "7 ~ 8 �ð�";
    			
    			
    			// ��� ��� messagedialog ���ؼ� �˸�
                JOptionPane.showMessageDialog(weightPanel,message,"��ü ����",JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    		// ��� �ʵ尡 ä���� ���� ���� �� "��� ������ �Է��� �ּ���" messagedialog ���
    		else JOptionPane.showMessageDialog(weightPanel,"��� ������ �Է��� �ּ���","��ü ����",JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        
        // heightField�� ���� ��, ���� �̿� �Է� ������ ���� KeyListener �߰�
        heightField.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		if(((JTextField) e.getSource()).getText().length() > 2 
        				|| e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9 ) {
        			e.consume();
        		}
        	}
        });
        
        
        // heightField�� ����� �� �Էµ� ���� �����ϱ� ���� FocusListener �߰�
        heightField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent fe) {}
			public void focusLost(FocusEvent fe) {
				try {
					if(Integer.parseInt((((JTextField) fe.getSource()).getText())) < 50)
						((JTextField) fe.getSource()).setText("50");
					else if(Integer.parseInt((((JTextField) fe.getSource()).getText())) > 230)
						((JTextField) fe.getSource()).setText("230");
				}
				catch (java.lang.NumberFormatException e) {
				}
			}
        });
        
        
        // weightField�� ���� ��, ���� �̿� �Է� ������ ���� KeyListener �߰�
        weightField.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		if(((JTextField) e.getSource()).getText().length() > 2 
        				|| e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9 ) {
        			e.consume();
        		}
        	}
        });
        
        // weightField�� ����� �� �Էµ� ���� �����ϱ� ���� FocusListener �߰�
        weightField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent fe) {}
			public void focusLost(FocusEvent fe) {
				try {
					if(Integer.parseInt((((JTextField) fe.getSource()).getText())) < 3)
						((JTextField) fe.getSource()).setText("3");
					else if(Integer.parseInt((((JTextField) fe.getSource()).getText())) > 200)
						((JTextField) fe.getSource()).setText("200");
				}
				catch (java.lang.NumberFormatException e) {
				}
			}
        });
        
        // ageField�� ���� ��, ���� �̿� �Է� ������ ���� KeyListener �߰�
        ageField.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		if(((JTextField) e.getSource()).getText().length() > 1 
        				|| e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9 ) {
        			e.consume();
        		}
        	}
        });
        
        // ageField�� ����� �� �Էµ� ���� �����ϱ� ���� FocusListener �߰�
        ageField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent fe) {}
			public void focusLost(FocusEvent fe) {
				try {
					if(Integer.parseInt((((JTextField) fe.getSource()).getText())) < 1)
						((JTextField) fe.getSource()).setText("1");
				}
				catch (java.lang.NumberFormatException e) {
				}
			}
        });
        
        // ��ü ������ ������ ����
        setSize( 270, 350 );
        // ������ ����ȭ
        setVisible(true);
        // x ������ ����
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
	public static void main(String[] args)
    {
		// PysiCalculator ��ü ����
        new PhysiCalculator();        
    }
}