package top.sweetheart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.sweetheart.socket.TacticsSocket;
import top.sweetheart.util.JSON;

@Service
public class TacticsService {

    @Autowired
    private TacticsSocket tacticsSocket;

    public JSON login(String userName, String passWord) throws Exception {

        tacticsSocket.entry();

        return tacticsSocket.login(userName, passWord);
    }
}
