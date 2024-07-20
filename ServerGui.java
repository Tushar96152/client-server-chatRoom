import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Server extends JFrame {
    Container c;
    JTextField type;
    JButton enter;
    JTextArea msg;

    Server() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());


            setTitle("Server");
            setBounds(150, 0, 600, 950);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(null);


            c = getContentPane();
            Font f1 = new Font("Ariel",Font.ITALIC,20);


            ImageIcon img = new ImageIcon("pxfuel (1) (1).jpg");
            JLabel bk = new JLabel(img);
            bk.setSize(600,950);
            c.add(bk);



            type = new JTextField();
            type.setBounds(50, 50, 500, 50);
            type.setFont(f1);
            bk.add(type);

            enter = new JButton("Enter");
            enter.setBounds(430, 120, 100, 50);
            bk.add(enter);



            msg = new JTextArea();
            msg.setBounds(50,200,500,600);
            msg.setFont(f1);
            bk.add(msg);




            setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class ServerGui {
    public static void main(String[] args) {


        Server sera = new Server();

        try {
            ServerSocket ss = new ServerSocket(5001);
            Socket s = ss.accept();


            Scanner read = new Scanner(s.getInputStream());
            PrintStream write = new PrintStream(s.getOutputStream());


            sera.enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String sendmsg = sera.type.getText();
                    write.println(sendmsg);
                    sera.msg.append("Server :-" + sendmsg + "\n");
                    sera.type.setText(null);
                }
            });

            while (true) {
                String msgread = read.nextLine();
                if (msgread.equalsIgnoreCase("Bye")) {
                    sera.dispose();
                    break;

                }
                if (msgread.equalsIgnoreCase("on")) {
                    sera.msg.append("Connection Established\n");
                } else {
                    sera.msg.append("Client :- " + msgread + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}