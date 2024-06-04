package frpc.server.tcp;

import frpc.config.RegistryConfig;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.parsetools.RecordParser;
import lombok.extern.slf4j.Slf4j;

/**
 * Vertx TCP 服务器
 */
@Slf4j
public class VertxTcpServer {
//    private byte[] handleRequest(byte[] requestData) {
//        return requestData;
//    }

    public void doStart(int port) {
        // 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 TCP 服务器
        NetServer server = vertx.createNetServer();

        // 处理请求
        server.connectHandler(new TcpServerHandler());
//        server.connectHandler(socket -> {
//            // 构造parser，处理半包、粘包问题
//            RecordParser parser = RecordParser.newFixed(8); // 先获取消息头
//            parser.setOutput(new Handler<Buffer>() {
//                // 初始化
//                int size = -1;
//                Buffer resultBuffer = Buffer.buffer();
//                @Override
//                public void handle(Buffer buffer) {
//                    if (-1 == size) {
//                        // 读取消息体长度
//                        size = buffer.getInt(4);
//                        parser.fixedSizeMode(size);
//                        // 写入头信息到结果
//                        resultBuffer.appendBuffer(buffer);
//                    } else {
//                        resultBuffer.appendBuffer(buffer);
//                        System.out.println(resultBuffer.toString());
//                        parser.fixedSizeMode(8);
//                        size = -1;
//                        resultBuffer = Buffer.buffer();
//                    }
//                }
//            });
//
//            socket.handler(parser);
//
////            netSocket.handler(buffer -> {
////                // 处理接收到的字节数组
////                byte[] requestData = buffer.getBytes();
////                // 在这里进行自定义的字节数组处理逻辑，比如解析请求、调用服务、构造响应等
////                byte[] responseData = handleRequest(requestData);
////                // 发送响应
////                netSocket.write(Buffer.buffer(responseData));
////            });
//        });


        // 启动 TCP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                log.info("TCP server started on port " + port);
            } else {
                log.info("Failed to start TCP server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
