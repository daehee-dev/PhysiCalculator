package physiCalculator;

// 사용한 모듈
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
	// 필요 레이아웃 선언
	JLabel topLabel, weight, height, age, sex;
	JTextField heightField, weightField, ageField;
	JPanel heightPanel, weightPanel, agePanel, sexPanel, submitPanel;
	JRadioButton male, female;
	ButtonGroup sexGroup;
	JButton submit;

	
    public PhysiCalculator() 
    {
    	// 윈도우의 이름 설정
        super("PhysiCalculator");
        // 레이아웃 부착방법을 GridLayout으로 설정
        setLayout( new GridLayout(0,1,5,5) );
        
        // 최상단에 표시 될 topLabel(JLabel) 객체 생성 / 경계선 설정 / 가운데 정렬
        topLabel = new JLabel("신체정보를 입력해 주세요");
        topLabel.setBorder(new EtchedBorder());
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // sexPanel과 RadioButton 객체 생성
        sexPanel = new JPanel();
        male = new JRadioButton("남자");
        female = new JRadioButton("여자");
        
        // RadioButton 그룹화를 위한 ButtonGruop 객체 생성 및 그룹화
        sexGroup = new ButtonGroup();
        male.setSelected(true);
        sexGroup.add(male);
        sexGroup.add(female);
        
        // "성별" Label 객체 생성과 Panel에 부착
        sex = new JLabel("성별");
        sexPanel.add(sex);
        sexPanel.add(male);
        sexPanel.add(female);    
        
        // 신장정보를 얻기 위한 Panel, TextField, Label 객체 생성 및 Panel에 부착
        heightPanel = new JPanel();
        heightField = new JTextField(9);
        height = new JLabel("신장");
        heightPanel.add(height);
        heightPanel.add(heightField);

        // 체중정보를 얻기 위한 Panel, TextField, Label 객체 생성 및 Panel에 부착        
        weightPanel = new JPanel();
        weightField = new JTextField(9);
        weight = new JLabel("체중");
        weightPanel.add( weight );
        weightPanel.add( weightField );
        
        // 나이정보를 얻기 위한 Panel, TextField, Label 객체 생성 및 Panel에 부착        
        agePanel=new JPanel();
        ageField = new JTextField(9);
        age = new JLabel("나이");
        agePanel.add(age);
        agePanel.add(ageField);  
        
        // 입력된 정보를 처리하기 위한 Panel, Button 객체 생성 Panel에 부착
        submitPanel = new JPanel();
        submit = new JButton("결과 보기");
        submitPanel.add(submit);
        
        // Panel을 메인 프레임에 부착
        add(topLabel);
        add(sexPanel);
        add(heightPanel);
        add(weightPanel);
        add(agePanel);
        add(submitPanel);
        
        // 폰트 설정
        topLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        sex.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        height.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        weight.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        age.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        male.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        female.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        submit.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
        UIManager.put("OptionPane.messageFont", new Font("맑은 고딕", Font.PLAIN, 13));
        
        // submit 버튼에 대한 actionlistener 추가와 actionPerformed overriding
        submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) 
        	{
        	
        	// 모든 field가 채워져 있을 때 동작
    		if(!heightField.getText().isEmpty() & !weightField.getText().isEmpty() & !ageField.getText().isEmpty()) 
    		{
    			
    			// 기초대사량 계산
    			String message = "기초대사량 : ";
    			if(male.isSelected()) {
    				message += Math.round(66 + 13.7 * Integer.parseInt(weightField.getText())
    						+ 5 * Integer.parseInt(heightField.getText())
    						- 6.5 * Integer.parseInt(ageField.getText()));
    			}
    			else message += Math.round(655 + 9.6 * Integer.parseInt(weightField.getText())
    						 + 1.8 * Integer.parseInt(heightField.getText())
    						 - 4.7 * Integer.parseInt(ageField.getText()));
    			
    			
    			// 신체질량지수 계산
    			message += " kCal\n신체질량지수 : ";
    			int BMI = Math.round(10000 * Integer.parseInt(weightField.getText()) / 
    					Integer.parseInt(heightField.getText()) / Integer.parseInt(heightField.getText()));
    			
    			if(BMI < 20)
    				message += BMI + " 저체중";
    			else if(BMI < 24)
    				message += BMI + " 정상체중";
    			else if(BMI < 29)
    				message += BMI + " 과체중";
    			else
    				message += BMI + " 비만";
    			
    			
    			// 권장 수분 섭취량 계산
    			message += " \n권장 수분 섭취량 : ";
    			if(Integer.parseInt(weightField.getText()) <= 10)
    				message += Integer.parseInt(weightField.getText())*100;
    			else if(Integer.parseInt(weightField.getText()) <= 20)
    				message += 10*100 + (Integer.parseInt(weightField.getText())-10)*50;
    			else
    				message += 10*100 + 10*50 + (Integer.parseInt(weightField.getText())-20)*20;


    			// 권장 수면시간 계산
    			message += " mL\n권장 수면시간 : ";
    			if(ageField.getText().equals("1"))
    				message += "12 ~ 17 시간";
    			else if(ageField.getText().equals("2"))
    				message += "11 ~ 14 시간";
    			else if(Integer.parseInt(ageField.getText()) >= 3 && Integer.parseInt(ageField.getText()) <= 5)
    				message += "10 ~ 13 시간";
    			else if(Integer.parseInt(ageField.getText()) >= 6 && Integer.parseInt(ageField.getText()) <= 13)
    				message += "9 ~ 11 시간";
    			else if(Integer.parseInt(ageField.getText()) >= 14 && Integer.parseInt(ageField.getText()) <= 17)
    				message += "8 ~ 10 시간";
    			else if(Integer.parseInt(ageField.getText()) >= 18 && Integer.parseInt(ageField.getText()) <= 64)
    				message += "7 ~ 9 시간";
    			else
    				message += "7 ~ 8 시간";
    			
    			
    			// 계산 결과 messagedialog 통해서 알림
                JOptionPane.showMessageDialog(weightPanel,message,"신체 계산기",JOptionPane.INFORMATION_MESSAGE);
    		}
    		
    		// 모든 필드가 채워져 있지 않을 때 "모든 정보를 입력해 주세요" messagedialog 출력
    		else JOptionPane.showMessageDialog(weightPanel,"모든 정보를 입력해 주세요","신체 계산기",JOptionPane.INFORMATION_MESSAGE);
        	}
        });
        
        
        // heightField에 글자 수, 숫자 이외 입력 제한을 위한 KeyListener 추가
        heightField.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		if(((JTextField) e.getSource()).getText().length() > 2 
        				|| e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9 ) {
        			e.consume();
        		}
        	}
        });
        
        
        // heightField를 벗어났을 때 입력된 값을 제한하기 위한 FocusListener 추가
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
        
        
        // weightField에 글자 수, 숫자 이외 입력 제한을 위한 KeyListener 추가
        weightField.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		if(((JTextField) e.getSource()).getText().length() > 2 
        				|| e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9 ) {
        			e.consume();
        		}
        	}
        });
        
        // weightField를 벗어났을 때 입력된 값을 제한하기 위한 FocusListener 추가
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
        
        // ageField에 글자 수, 숫자 이외 입력 제한을 위한 KeyListener 추가
        ageField.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		if(((JTextField) e.getSource()).getText().length() > 1 
        				|| e.getKeyChar() < e.VK_0 || e.getKeyChar() > e.VK_9 ) {
        			e.consume();
        		}
        	}
        });
        
        // ageField를 벗어났을 때 입력된 값을 제한하기 위한 FocusListener 추가
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
        
        // 전체 윈도우 사이즈 설정
        setSize( 270, 350 );
        // 윈도우 가시화
        setVisible(true);
        // x 눌러서 끄기
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
	public static void main(String[] args)
    {
		// PysiCalculator 객체 생성
        new PhysiCalculator();        
    }
}