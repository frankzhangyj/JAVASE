import com.microsoft.client.view.MainMenu;
// 主要靠Thread阻塞式接收服务端的消息 实现交互
public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainView();
    }
}