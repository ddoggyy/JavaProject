import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class CafeOrderFrame extends JFrame {
	private String[] drinkNameList = {"아메리카노(2000)", "라떼(2500)", "주스(3000)"};
	private String[] optionList = {"샷 추가(+500)", "시럽(+300)", "휘핑(+400)"};
	private String[] sizeList = {"Small +0", "Medium +500", "Large +1000"};
	
	private JRadioButton[] chooseDrink = new JRadioButton[3]; 
	private JCheckBox[] chooseOption = new JCheckBox[3];
	private JComboBox<String> chooseSize = new JComboBox<String>(sizeList);
	private JSlider qtySlider = new JSlider(1, 5, 1);
	private JLabel qtyLabel;
	private JLabel totalPrice = new JLabel();
	private JTextArea orderList = new JTextArea(7, 30);
	private int drinkPrice;
	
	private String drinkName;
	private String option;
	private String size;
	
	private boolean isShot;
	private boolean isSyrup;
	private boolean isWhipping;
	private boolean isMedium;
	private boolean isLarge;
	
	private int calcTotalPrice() {
		int optionPrice = 0;
		int sizePrice = 0;
		int totalPriceValue = 0;
		
		int qty = qtySlider.getValue();
		
		if(isShot) optionPrice += 500;
		if(isSyrup) optionPrice += 300;
		if(isWhipping) optionPrice += 400;
		
		if(isMedium) sizePrice += 500;
		if(isLarge) sizePrice += 1000;
		
		totalPriceValue = (drinkPrice + optionPrice + sizePrice) * qty;
		totalPrice.setText("현재 금액: " + totalPriceValue + "원");
		
		return totalPriceValue;
	}
	
	public CafeOrderFrame() {
		setTitle("Java Cafe 주문 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		// == 타이틀 ==
		JLabel titleLabel = new JLabel("Java Cafe 주문 프로그램", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		
		// == 센터 ==
		JPanel centerPanel = new JPanel(new GridLayout(3, 1));
		
		// == 센터 1 ==
		JPanel drinkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		drinkPanel.setBorder(BorderFactory.createTitledBorder("음료 선택"));
		
		ButtonGroup drink = new ButtonGroup();
		for(int i=0; i<chooseDrink.length; i++) {
			chooseDrink[i] = new JRadioButton(drinkNameList[i]);
			drink.add(chooseDrink[i]);
			drinkPanel.add(chooseDrink[i]);
			chooseDrink[i].addItemListener(new drinkListener());
		}
		chooseDrink[0].setSelected(true);
		
		// == 센터 2 ==
		JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		optionPanel.setBorder(BorderFactory.createTitledBorder("옵션 / 사이즈"));
		for(int i=0; i<chooseOption.length; i++) {
			chooseOption[i] = new JCheckBox(optionList[i]);
			optionPanel.add(chooseOption[i]);
			chooseOption[i].addItemListener(new optionListener());
		}
		optionPanel.add(chooseSize);
		chooseSize.addActionListener(new sizeListener());
		
		// == 센터 3 ==
		JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		qtyPanel.setBorder(BorderFactory.createTitledBorder("수량"));
        qtySlider.setMajorTickSpacing(1);
        qtySlider.setPaintTicks(true);
        qtySlider.setPaintLabels(true);
        qtyLabel = new JLabel("수량: 1");
        qtyPanel.add(qtySlider);
        qtyPanel.add(qtyLabel);
        qtySlider.addChangeListener(new qtyListener());

		centerPanel.add(drinkPanel);
		centerPanel.add(optionPanel);
		centerPanel.add(qtyPanel);
	
		// 하단 레이블
		JPanel southPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton order = new JButton("주문 추가");
		JButton reset = new JButton("초기화");
		
		order.addActionListener(new orderListener());
		reset.addActionListener(new resetListener());
		
		buttonPanel.add(totalPrice);
		buttonPanel.add(order);
		buttonPanel.add(reset);
		southPanel.add(buttonPanel, BorderLayout.NORTH);
		southPanel.add(new JScrollPane(orderList), BorderLayout.SOUTH);
		
		c.add(titleLabel, BorderLayout.NORTH);
		c.add(centerPanel, BorderLayout.CENTER);
		c.add(southPanel, BorderLayout.SOUTH);
		
		setSize(500, 400);
		setVisible(true);	
	}
	
	class drinkListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(chooseDrink[0].isSelected()) {
				drinkName = "아메리카노";
				drinkPrice = 2000;
			}
			else if(chooseDrink[1].isSelected()) {
				drinkName = "라떼";
				drinkPrice = 2500;
			}
			else if(chooseDrink[2].isSelected()) {
				drinkName = "주스";
				drinkPrice = 3000;
			}
			calcTotalPrice();
		}
	}
	
	class optionListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				if(e.getItem() == chooseOption[0])
					isShot = true;
				else if(e.getItem() == chooseOption[1])
					isSyrup = true;
				else if(e.getItem() == chooseOption[2])
					isWhipping = true;
			}
			else {
				if(e.getItem() == chooseOption[0])
					isShot = false;
				else if(e.getItem() == chooseOption[1])
					isSyrup = false;
				else if(e.getItem() == chooseOption[2])
					isWhipping = false;
			}
			calcTotalPrice();
		}
	}
	
	class sizeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			isMedium = false;
			isLarge = false;
			
			int index = chooseSize.getSelectedIndex();
			if(index == 1) isMedium = true;
			if(index == 2) isLarge = true;
			calcTotalPrice();
		}
		
	}
	
	class qtyListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			int qty = qtySlider.getValue();
			qtyLabel.setText("수량: " + qty);
			calcTotalPrice();
		}
	}
	
	class orderListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        String sizeText = "Small";
	        if (isMedium) sizeText = "Medium";
	        else if (isLarge) sizeText = "Large";

	        String optionText = "";
	        if (isShot) optionText += " 샷";
	        if (isSyrup) optionText += " 시럽";
	        if (isWhipping) optionText += " 휘핑";
	        if (optionText.equals("")) optionText = " 없음";

	        int qty = qtySlider.getValue();
	        
	        String result = drinkName + " [옵션:" + optionText + "] / 수량: " + qty + " / 사이즈: " + sizeText + " /  금액: " + calcTotalPrice() + "\n";
	        orderList.append(result);
	    }
	}

	class resetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

	        chooseDrink[0].setSelected(true);
	        drinkName = "아메리카노";
	        drinkPrice = 2000;

	        for (int i = 0; i < chooseOption.length; i++) {
	            chooseOption[i].setSelected(false);
	        }
	        isShot = false;
	        isSyrup = false;
	        isWhipping = false;

	        chooseSize.setSelectedIndex(0);
	        isMedium = false;
	        isLarge = false;

	        qtySlider.setValue(1);
	        qtyLabel.setText("수량: 1");

	        totalPrice.setText("현재 금액: 0원");
	        orderList.setText("");
	    }
	}

	public static void main(String[] args) {
		new CafeOrderFrame();
	}
}