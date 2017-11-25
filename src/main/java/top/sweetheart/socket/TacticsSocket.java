package top.sweetheart.socket;

import org.springframework.stereotype.Repository;
import top.sweetheart.util.JSON;
import top.sweetheart.util.byteUtle;
import top.sweetheart.util.socket.CHeader;
import top.sweetheart.util.socket.Es;
import top.sweetheart.util.socket.socketReceive;
import top.sweetheart.util.socket.socketSend;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Repository
public class TacticsSocket {

    // 建立Socket连接
    private Socket socket;
    // 发送器
    private socketSend ss;
    // 接收器
    private socketReceive sr;

    public TacticsSocket() throws IOException {
        socket = new Socket("192.168.1.245 ", 23396);
        ss = new socketSend(socket);
        sr = new socketReceive(socket);
    }


    public void entry() throws Exception {
        // 连接进游戏大厅对象
        CHeader header = new CHeader();
        header.setCmd_(Es.CMD_TACTICSHANDLE_ENTER);

        // 发送连接请求
        ss.send(header.toByte());

        // 接受请求返回
        sr.setIndex(header.toSize());
        sr.start();
        byte[] rqbys = sr.getReceive();
        header.add(rqbys);

        if (header.getSubcmd_() != Es.SCMD_TACTICSHANDLE_ENTER_SUCCESS) {
            throw new Exception("进入游戏大厅失败！");
        }

        System.out.println("进入游戏大厅成功！");
    }

    public JSON login(String userName, String passWord) throws Exception {
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update("5555".getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        String passWordMd5 = new BigInteger(1, md.digest()).toString(16);

        JSON csitInfo = new JSON();
        csitInfo.put("user_name", userName);
        csitInfo.put("pass_word", passWordMd5);


        CHeader header = new CHeader();
        // 消息头
        header.setCmd_(Es.CMD_TACTICSHANDLE_LOGIN);
        // 消息体
        // 其它东西
        byte[] csitInfoByte = csitInfo.toString().getBytes();
        header.setDatasize_(csitInfoByte.length);
        byte[] headerByte = header.toByte();

        // 将消息头和消息体塞入data里面去
        byte[] data = new byte[headerByte.length + csitInfoByte.length];
        byteUtle.add(data, headerByte, 0);
        byteUtle.add(data, csitInfoByte, headerByte.length);

        // 将data传送给对方
        ss.send(data);

        // 接受返回信息
        byte[] rqbys = sr.getReceive();

        header.add(byteUtle.slip(rqbys, 0, header.toSize()));
        switch (header.getSubcmd_()) {
            case Es.SCMD_TACTICSHANDLE_LOGIN_FAILED:
                throw new Exception("登录验证拒绝");
            case Es.SCMD_TACTICSHANDLE_LOGIN_NONICK:
                throw new Exception("登录验证失败，无昵称");
            case Es.SCMD_TACTICSHANDLE_LOGIN_CONFLICT:
                throw new Exception("登录验证失败，其它错误");
        }

        return new JSON(new String(byteUtle.slip(rqbys, header.toSize(), rqbys.length - header.toSize())));
    }

    public JSON search() {
        return null;
    }

    /**
     * 5.1.5.	客户端拉取所有策略方案
     *
     * @return
     */
    public JSON getAll(String uid) {
        return null;
    }


    public static void main(String[] args) throws Exception {
        TacticsSocket tacticsSocket = new TacticsSocket();

        tacticsSocket.entry();

        JSON userInfo = tacticsSocket.login("z0", "5555");

        JSON cUserTacticsInfo = tacticsSocket.getAll(userInfo.getStr("uid_"));

        System.out.println(cUserTacticsInfo);
    }
}
