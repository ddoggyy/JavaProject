package ex10_02;

public class InnerClassListener extends JFrame { public InnerClassListener() {
setTitle("Action 이벤트 리스너 예제"); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); Container c = getContentPane();
c.setLayout(new FlowLayout());
JButton btn = new JButton("Action");
btn.addActionListener(new MyActionListener()); c.add(btn);
setSize(350, 150); setVisible(true);
}
• Action 리스너를 내부 클래스로 작성
• private으로 선언하여 InnerClassListener 외부에서 사용할 수 없게 함
• 리스너에서 InnerClassListener의 멤버에 대한 접근 용이
private class               private class MyActionListener                   MyActionListenerimplements            implements ActionListener             ActionListener {   { public void            public void actionPerformed                 actionPerformed((ActionEvent             ActionEvent e) {  e) {
JButton    JButton b = (    b = (JButton      JButton))e.getSource           e.getSource(); (); if(if(b.getText         b.getText().equals("Action"))                     ().equals("Action"))
b.setText       b.setText("("액션액션"); "); else else
b.setText       b.setText("Action");           ("Action");	
}}
}}
15
public static void main(String [] args) { new InnerClassListener();
}} 