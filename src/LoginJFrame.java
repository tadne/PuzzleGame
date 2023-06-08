import com.itheima.ui.User;
import util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


    public class LoginJFrame extends JFrame implements MouseListener
    {
        static String Path=RegisterJFrame.Path;

        //创建登陆按钮
        JButton login = new JButton();
        //创建注册按钮
        JButton register = new JButton();

        JPasswordField password=new JPasswordField();
        JTextField username = new JTextField();
        JTextField code = new JTextField();
        //正确的验证码
        JLabel rightCode = new JLabel();

        //创建一个集合存储正确的用户名和密码
        static ArrayList<User> list = RegisterJFrame.list;

        public LoginJFrame() {
            //初始化界面
            initJFrame();

            //在这个界面中添加内容
            initView();

            //让当前界面显示出来
            this.setVisible(true);
        }

        public void initView() {
            //1. 添加用户名文字
            JLabel usernameText = new JLabel(new ImageIcon(Path+"src/image/login/用户名.png"));
            usernameText.setBounds(116, 135, 47, 17);
            this.getContentPane().add(usernameText);

            //2.添加用户名输入框
            username.setBounds(195, 134, 200, 30);
            this.getContentPane().add(username);

            //3.添加密码文字
            JLabel passwordText = new JLabel(new ImageIcon(Path+"src/image/login/密码.png"));
            passwordText.setBounds(130, 195, 32, 16);
            this.getContentPane().add(passwordText);

            //4.密码输入框
            password.setBounds(195, 195, 200, 30);
            this.getContentPane().add(password);

            //验证码提示
            JLabel codeText = new JLabel(new ImageIcon(Path+"src/image/login/验证码.png"));
            codeText.setBounds(133, 256, 50, 30);
            this.getContentPane().add(codeText);

            //验证码的输入框
            code.setBounds(195, 256, 100, 30);
            this.getContentPane().add(code);

            String codeStr = CodeUtil.getCode();

            //设置内容
            rightCode.setText(codeStr);
            //绑定鼠标事件
            rightCode.addMouseListener(this);
            //位置和宽高
            rightCode.setBounds(300, 256, 50, 30);
            //添加到界面
            this.getContentPane().add(rightCode);

            //5.添加登录按钮
            login.setBounds(123, 310, 128, 47);
            login.setIcon(new ImageIcon(Path+"src/image/login/登录按钮.png"));
            //去除按钮的默认边框
            login.setBorderPainted(false);
            //去除按钮的默认背景
            login.setContentAreaFilled(false);
            this.getContentPane().add(login);
            //给登录按钮绑定鼠标事件
            login.addMouseListener(this);
            this.getContentPane().add(login);

            //6.添加注册按钮
            register.setBounds(256, 310, 128, 47);
            register.setIcon(new ImageIcon(Path+"src/image/login/注册按钮.png"));
            //去除按钮的默认边框
            register.setBorderPainted(false);
            //去除按钮的默认背景
            register.setContentAreaFilled(false);
            this.getContentPane().add(register);
            //给注册按钮绑定鼠标事件
            register.addMouseListener(this);
            this.getContentPane().add(register);

            //7.添加背景图片
            JLabel background = new JLabel(new ImageIcon(Path+"src/image/login/background.png"));
            background.setBounds(0, 0, 470, 390);
            this.getContentPane().add(background);
        }


        public void initJFrame() {
            this.setSize(488, 430);//设置宽高
            this.setTitle("拼图游戏 V1.0登录");//设置标题
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭模式
            this.setLocationRelativeTo(null);//居中
            this.setAlwaysOnTop(true);//置顶
            this.setLayout(null);//取消内部默认布局
        }

        //鼠标点击
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getSource()==login){
                //获取用户输入的验证码，用户名，密码
                String usernameInput = username.getText();
                String passwordInput = password.getText();
                String CodeInput= code.getText();
                //创建一个对象
                User userInfo=new User(usernameInput,passwordInput);

                if (CodeInput.length()==0){
                    showJDialog("验证码不能为空");
                }else if (usernameInput.length() == 0 || passwordInput.length() == 0){
                    showJDialog("用户名或密码不能为空");
                }
                else if (!CodeInput.equalsIgnoreCase(rightCode.getText()))
                {
                    showJDialog("验证码输入错误");
                }else if (Contains(userInfo)){
                    showJDialog("用户名和密码正确，可以玩游戏了");
                    //关闭登陆界面
                    this.setVisible(false);
                    //打开游戏的主界面
                    //需要把当前登录的用户名传递给游戏界面
                    new GameJFrame();
                }else {
                    showJDialog("用户名或密码错误");
                }
            }else if (e.getSource() == rightCode) {
                System.out.println("更换验证码");
                //获取一个新的验证码
                String code = CodeUtil.getCode();
                rightCode.setText(code);
            }else if (e.getSource()==register)
            {
                new RegisterJFrame();
            }

        }

        //要展示用户名或密码错误
        public void showJDialog(String content) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //给弹框设置大小
            jDialog.setSize(200, 150);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭永远无法操作下面的界面
            jDialog.setModal(true);

            //创建Jlabel对象管理文字并添加到弹框当中
            JLabel warning = new JLabel(content);
            warning.setBounds(0, 0, 200, 150);
            jDialog.getContentPane().add(warning);

            //让弹框展示出来
            jDialog.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            if (e.getSource() == login) {
                login.setIcon(new ImageIcon(Path+"src/image/login/登录按下.png"));
            } else if (e.getSource() == register) {
                register.setIcon(new ImageIcon(Path+"src/image/login/注册按钮.png"));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            if (e.getSource() == login) {
                login.setIcon(new ImageIcon(Path+"src/image/login/登录按钮.png"));
            } else if (e.getSource() == register) {
                register.setIcon(new ImageIcon(Path+"src/image/login/注册按钮.png"));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }
        //账户密码验证
        public boolean Contains(User UserInput){
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).getName().equals(UserInput.getName())&&list.get(i).getPassword().equals(UserInput.getPassword())){
                    return true;
                }
            }
            return false;
        }

    }
