import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener, ActionListener
{
    static String Path=RegisterJFrame.Path;

    //JFrame 界面，窗体
    //规定，GameJFrame这个界面比爬山的就是游戏的主界面
    //和游戏的所以逻辑都写在这个类中

    //创建一个二维数组
    //目的：管理数据
    //加载图片时，会根据二维数组中的数据进行加载；
    int[][] arr=new int[4][4];
    int x=0;int y=0;//记录空白方块在二维数组里的位置
    //给更新数据和更换图片提供
    Random r=new Random();


    //定义一个变量，记录当前展示图片的路径
    String path=Path+"src/image/animal/animal3/";
    //定义一个二维数组，存储正确的数据
    int [][] win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    //定义变量，用来统计步数
    int step=0;

    //创建选项下的条目对象
    //
    JMenuItem reGameItem=new JMenuItem("重新游戏");
    JMenuItem reLoginItem=new JMenuItem("重新登陆");
    JMenuItem closeItem=new JMenuItem("关闭游戏");

    JMenuItem accountItem=new JMenuItem("公众号");

    JMenuItem beautyItem=new JMenuItem("美丽");
    JMenuItem animalItem=new JMenuItem("动物");
    JMenuItem sportItem=new JMenuItem("运动");




    public GameJFrame(){
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMneBar();
        //初始化数据
        initdate();
        //初始化图片
        initImage();
        //让界面显示
        this.setVisible(true);
    }


    //初始化数据
     private void initdate()
    {

        int[] tempArr={0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        for (int i = 0; i < tempArr.length; i++)
        {
            int count =r.nextInt(tempArr.length);
            int temp=tempArr[i];
            tempArr[i]=tempArr[count];
            tempArr[count]=temp;
        }


        for (int i = 0; i < tempArr.length ; i++)
        {
            if (tempArr[i]==0)
            {
                x=i/4;
                y=i%4;
            }
            arr[i/4][i%4]=tempArr[i];
        }

    }


    //初始化图片
    //添加图片时，就要按着二维数组中管理的数据添加图片
    private void initImage()
    {
        //再次加载时，要清空已经出现的图片
        this.getContentPane().removeAll();

        if (victory()){
            JLabel winJLabel=new JLabel(new ImageIcon(Path+"src/image/win.png"));
            winJLabel.setBounds(290-98,300-36,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount=new JLabel("步数："+step);
        stepCount.setBounds(70,50,100,20);
        this.getContentPane().add(stepCount);
        //路径有两种
        //绝对路径：一定是从盘符开始
        //相对路径：相对当前项目下的路径，

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
               int number=arr[i][j];
                //创建一个JLabel的对象(一个管理容器,可以添加图片)


                JLabel jLabel=new JLabel(new ImageIcon(path+number+".jpg"));
                //指定图片的位置"\"
                jLabel.setBounds(105*j+82,105*i+93,105,105);
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);
                //0:让图片凸起。1：让图片凹下去
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));//1
            }
        }
        JLabel backGround=new JLabel(new ImageIcon(Path+"src/image/background.png"));
        backGround.setBounds(38,0,508,560);
        this.getContentPane().add(backGround);



        //再次加载完后要刷新一次界面
        this.getContentPane().repaint();
    }


    //初始化菜单
    private void initJMneBar()
    {
        //初始化菜单
        //创建整个的菜单对象
        JMenuBar jMenuBar=new JMenuBar();

        //创建菜单上面的两个选项的对象(功能，关于我们)
        JMenu functionJMenu=new JMenu("功能");
        JMenu aboutJMenu=new JMenu("关于我们");
        JMenu changePhotoMenu=new JMenu("更换图片");

        //将每一个选项下面的条目添加到选项中去
        functionJMenu.add(reGameItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        functionJMenu.add(changePhotoMenu);

        aboutJMenu.add(accountItem);

        changePhotoMenu.add(beautyItem);
        changePhotoMenu.add(animalItem);
        changePhotoMenu.add(sportItem);
        //给每一个条目绑定事件
        reGameItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);

        accountItem.addActionListener(this);

        beautyItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        //将菜单里的两个选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }
    //初始化界面
    private void initJFrame()
    {
        //设置页面的宽高
        this.setSize(580,600);//界面默认是隐藏的，这个时候要调用setVisble方法
        //设置标题
        this.setTitle("拼图单机版 v1.o");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        //0:什么都不做的默认窗口关闭方式
        //1：默认窗口关闭方式
        //2：当开启了多个界面后，只要关闭最后一个虚拟机才停止(但是要求每一个页面都如此操作才行）
        //3：只要关闭一个界面窗口就全部停止
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//3
        //取消默认的居中放置，只有取消了默认，才能按着XY轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);

    }


    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    //按键不松
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code=e.getKeyCode();
        if (code==65)
        {
            //删除界面中的所有图片
            this.getContentPane().removeAll();
            //加载一张完整图片
            JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(82,93,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            JLabel backGround=new JLabel(new ImageIcon(Path+"src/image/background.png"));
            backGround.setBounds(38,0,508,560);
            this.getContentPane().add(backGround);
            //再次加载完后要刷新一次界面
            this.getContentPane().repaint();
        }
    }

    //松开按键
    @Override
    public void keyReleased(KeyEvent e)
    {
        if (victory())
        {
            return;
        }
        //上：37，上：38，右：39，下：40
        int code=e.getKeyCode();
        if (code==37){
            if (y==3){
                return;
            }
            arr[x][y]=arr[x][y+1];
            arr[x][y+1]=0;
            y++;
            //每移动一次，步数要自增一次
            step++;
            //调用方法按着最新数组加载图片
            initImage();
        }
      else if (code==38)
        {
            if (x==3){
                return;
            }
            arr[x][y] = arr[x + 1][y];
            arr[x + 1][y] = 0;
            x++;
            //每移动一次，步数要自增一次
            step++;

            //调用方法按着最新数组加载图片
            initImage();
        }
        else if (code==39) {
            if (y==0){
                return;
            }
            arr[x][y]=arr[x][y-1];
            arr[x][y-1]=0;
            y--;
            //每移动一次，步数要自增一次
            step++;
            //调用方法按着最新数组加载图片
            initImage();
        }
        else if (code==40) {
            if (x==0){
                return;
            }
            arr[x][y]=arr[x-1][y];
            arr[x-1][y]=0;
            x--;
            //每移动一次，步数要自增一次
            step++;
            //调用方法按着最新数组加载图片
            initImage();
        }else if(code==65){
            initImage();
        }else if(code==87){
            arr=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    //判断胜利
    public boolean victory()
    {
        for (int i = 0; i < arr.length; i++)
        {
            //i:一次表示二维数组arr里的索引
            for (int j = 0; j < arr[i].length; j++)
            {
                if (arr[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    //点击
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //获取当前被点击的条目对象
        Object obj=e.getSource();
        //判断
       if (obj==reGameItem){
           System.out.println("重新游戏");
           //再次打乱二维数组的图片
            initdate();
           //计步器清零
           step=0;
           //重新加载图片
            initImage();


        }else if(obj==reLoginItem){
           System.out.println("重新登陆");
           //关闭游戏界面
           this.setVisible(false);
            //打开登陆界面
           new LoginJFrame();


       }else if(obj==closeItem){
           System.out.println("关闭游戏");
           System.exit(0);
       }else if(obj==accountItem){
           System.out.println("公众号");
           JDialog jDialog=new JDialog();
           //加载我的微信图片
           JLabel jlabel=new JLabel(new ImageIcon(Path+"src/image/66.jpg"));
           jlabel.setBounds(0,0,600,600);
           //在弹框中添加图片
           jDialog.getContentPane().add(jlabel);
           //给弹框设置大小
           jDialog.setSize(800,800);
           //让弹框置顶
           jDialog.setAlwaysOnTop(true);
           //让弹框居中
           jDialog.setLocationRelativeTo(null);
           //弹框不关闭就无法操作下面的界面
           jDialog.setModal(true);
           //让弹框显示出来
           jDialog.setVisible(true);
       }else if (obj==beautyItem){
           int beautyCount=r.nextInt(12)+1;
           path=Path+"src/image/girl/girl"+beautyCount+"/";
            initdate();
            initImage();

       }else if (obj==animalItem)
       {
           int animalCount=r.nextInt(7)+1;
           path=Path+"src/image/animal/animal"+animalCount+"/";
           initdate();
           initImage();


       }else if (obj==sportItem)
       {
           int sportCount=r.nextInt(9)+1;
           path=Path+"src/image/sport/sport"+sportCount+"/";
           initdate();
           initImage();
       }
    }
}
