import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        System.out.println("客户端 " + socket.getClass());

        // 输出字符流
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("hello server");
        bufferedWriter.newLine(); // 添加换行符
        bufferedWriter.flush();   // 确保数据被发送

        // 关闭输出流，通知服务端数据发送完毕 服务端在readline时读取到EOF结束标志(避免在服务端输入时阻塞)
        socket.shutdownOutput();

        // 输入字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;

        // 读取服务端的响应
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("Received from server: " + line);
        }

        // 如果需要继续输出 因为socket输出流已经关闭 所以可以新建一个socket

        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }
}
