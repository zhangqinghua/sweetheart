package top.sweetheart.util.socket;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginContext;

public class Es {
    public final static int CMD_LOBBY_ENTER = 0x40000010;                    //进入大厅协议
    public final static int CMD_LOBBY_GET_VERCODE = 0x41000208;               //客户端请求发送短信验证码或验证短信验证码
    public final static int CMD_LOBBY_USER_REGISTER = 0x40000300;             //玩家通过APP注册账号
    public final static int CMD_LOBBY_LOGIN = 0x40000040;                     // 登录大厅协议，用户和密码在payload中
    public final static int CMD_LOBBY_GET_USER_INFO = 0x41000204;             //客户端请求用户详细信息
    public final static int CMD_LOBBY_CHARGE = 0x40000530;                      //用户充值

    //Connect
    public final static int CMD_TACTICSHANDLE_ENTER = 0x45000001;           //玩家连接
    public final static int SCMD_TACTICSHANDLE_ENTER_SUCCESS = 0x45000002;  //连接成功
    public final static int SCMD_TACTICSHANDLE_ENTER_FAILED = 0x45000002;  //连接失败
    //Login
    public final static int CMD_TACTICSHANDLE_LOGIN = 0x45000005;           //to Server Login
    public final static int SCMD_TACTICSHANDLE_LOGIN_SUCCESS = 0x45000006;  //to Client Login Success
    public final static int SCMD_TACTICSHANDLE_LOGIN_FAILED = 0x45000007;   //Login Fail
    public final static int SCMD_TACTICSHANDLE_LOGIN_NONICK = 0x45000008;  //NoNick
    public final static int SCMD_TACTICSHANDLE_LOGIN_CONFLICT = 0x45000009; //Dublicate


    public final static int SCMD_ENTER_SUCCESS = 0x70000011;                  //进入房间请求接受
    public final static int SCMD_ENTER_FAILED = 0x70000012;                   //进入房间请求拒绝

    public final static int SCMD_USER_REGISTER_SUCCESS = 0x40000310;          // 注册成功
    public final static int SCMD_USER_REGISTER_ERR_USERNAME = 0x40000301;     // 通行证已存在
    public final static int SCMD_USER_REGISTER_ERR_NICK = 0x40000302;         // 昵称已存在
    public final static int SCMD_USER_REGISTER_ERR = 0x40000303;              // 其他错误


    public final static int SCMD_LOGIN_SUCCESS = 0x10000023;                  // 登录验证成功
    public final static int SCMD_LOGIN_FAILED = 0x10000021;                   // 登录验证失败
    public final static int SCMD_LOGIN_SESSION = 0x10000026;                  // 客户端要求获取登录session_id
    public final static int SCMD_LOGIN_CONFLICT = 0x10000025;                 // 用户名和888的用户名有
    public final static int SCMD_UNION_LOGIN = 0x10000027;                    // 联合登录
    public final static int SCMD_STAND_LOGIN = 0x10000028;                    // 标准登录


    public final static int CMD_TACTICS_ADDPROJECT = 0x45000020;             // 5.1.3.	玩家增加另存策略
    public final static int SCMD_TACTICS_ADDPROJECT_SUCCESS = 0x45000021; // 客户端增加策略方案成功
    public final static int SCMD_TACTICS_ADDPROJECT_FAILED = 0x45000022; // 客户端增加策略方案失败
    public final static int SCMD_TACTICS_ADDPROJECT_ANALYZEFAILED = 0x45000023; // 客户端增加策略方案解析失败

    public final static Map<String, Integer> mapSize = new HashMap<String, Integer>();

    static {
        mapSize.put("Integer", 4);
        mapSize.put("int", 4);
        mapSize.put("Double", 8);
        mapSize.put("double", 8);
    }

    public final static Map<String, Integer> CuserRegistermap = new HashMap<String, Integer>();

    static {
        CuserRegistermap.put("user_name_", 61);
        CuserRegistermap.put("nick_", 13);
        CuserRegistermap.put("real_name_", 13);
        CuserRegistermap.put("password_", 33);
        CuserRegistermap.put("mobile_", 12);
        CuserRegistermap.put("ipaddr_", 16);
        CuserRegistermap.put("sex_", 4);
        CuserRegistermap.put("picpath", 81);
        CuserRegistermap.put("itype", 1);
        CuserRegistermap.put("umeng", 64);
    }


    public final static Map<String, Integer> CsitInfoMap = new HashMap<String, Integer>();

    static {
        CsitInfoMap.put("no_", 4);
        CsitInfoMap.put("nick_", 13);
        CsitInfoMap.put("level_", 4);
        CsitInfoMap.put("img_", 4);
        CsitInfoMap.put("sex_", 4);
        CsitInfoMap.put("state_", 21);
        CsitInfoMap.put("user_type_", 4);
        CsitInfoMap.put("sit_stat_", 4);
        CsitInfoMap.put("gamble_", 8);
        CsitInfoMap.put("chips_", 8);
        CsitInfoMap.put("join_stat_", 4);
        CsitInfoMap.put("play_stat_", 4);
        CsitInfoMap.put("chip_stat_", 4);
        CsitInfoMap.put("auto_stat_", 4);
        CsitInfoMap.put("pk_", 8);//4*2
        CsitInfoMap.put("potdex_", 4);
        CsitInfoMap.put("hidden_", 4);
        CsitInfoMap.put("Uid", 4);
    }

    public final static Map<String, Integer> CAddFriendsMap = new HashMap<String, Integer>();

    static {
        CAddFriendsMap.put("uid_", 4);
        CAddFriendsMap.put("nick_", 13);
        CAddFriendsMap.put("picpath", 81);
        CAddFriendsMap.put("gameCurrency", 8);
        CAddFriendsMap.put("gameSumNum", 4);
        CAddFriendsMap.put("successSum", 4);
        CAddFriendsMap.put("poolSum", 4);
        CAddFriendsMap.put("MaxPoker", 20);//4*5
        CAddFriendsMap.put("REMARKS", 13);
        CAddFriendsMap.put("IS_VIP", 2);
        CAddFriendsMap.put("STATE", 33);
        CAddFriendsMap.put("Msg", 33);
        CAddFriendsMap.put("type", 2);
        CAddFriendsMap.put("introducer", 42);
        CAddFriendsMap.put("diamonds", 8);

    }


}
