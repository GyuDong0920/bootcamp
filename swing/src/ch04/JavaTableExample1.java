package ch04;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JavaTableExample1{

    JFrame fr;
    JTable jt;
    JScrollPane jsp;


    public JavaTableExample1() {
        initData();
        setInitLayout();
    }

    public void initData() {
        fr = new JFrame();
        fr.setTitle("JTable Sample");
        fr.setSize(500, 200);


        String[][] d = {
                { "Gyudong", " 27" ,"efficiency apartment" },
                { "Dongneahyung", " 29 ", "Happy Villa" },
                { "Ahnenuna", " 28 ", "Happy house" },
                };

        String[] cn =  { "이름", "나이", "주소" };

        jt = new JTable(d, cn);
        jt.setBounds(30, 40, 200, 300);

        jsp = new JScrollPane(jt);


    }

    public void setInitLayout() {
        fr.setVisible(true);
        fr.add(jsp);
    }

    public static void main(String[] args) {
        new JavaTableExample1();
    }


}