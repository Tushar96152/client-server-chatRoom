import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client extends JFrame {
    Container c;
    JTextField type;
    JButton enter;
    JTextArea msg;

    Client() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());


            setTitle("Client");
            setBounds(750, 0, 600, 950);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(null);


            c = getContentPane();
            Font f1 = new Font("Ariel",Font.ITALIC,20);

            ImageIcon img = new ImageIcon("chat.jpg");
            JLabel bk = new JLabel(img);
            bk.setSize(600,950);
            c.add(bk);

            JLabel note = new JLabel("Type on for connect the server");
            note.setBounds(50,10,2000,30);
            note.setFont(f1);
            bk.add(note);

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




public class ClientGui {
    public static void main(String[] args)
    {
        Client cli = new Client();

        while(true)
        {
            if(cli.type.getText()!=null )
            {
                if( cli.type.getText().equalsIgnoreCase("on"))
                {
                    break;
                }
            }
        }
        try{

            Socket s = new Socket("localhost", 5001);


            Scanner read = new Scanner(s.getInputStream());
            PrintStream write = new PrintStream(s.getOutputStream());

            cli.enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String sendmsg = cli.type.getText();
                    write.println(sendmsg);
                    cli.msg.append("Client :- "+sendmsg + "\n");

                    write.flush();
                    cli.type.setText(null);

                }
            });


            while (true) {
                String msgread = read.nextLine();
                cli.msg.append("Server :-"+msgread + "\n");
            }



        }catch(Exception w ){
            System.out.println(w);
        }
    }}
