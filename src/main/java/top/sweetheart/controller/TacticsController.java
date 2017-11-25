package top.sweetheart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.sweetheart.service.TacticsService;
import top.sweetheart.util.JSON;

/**
 * 策略控制器
 */
@Controller
public class TacticsController {

    @Autowired
    private TacticsService tacticsService;


    @RequestMapping("/")
    public String index() {
        System.out.println("Hello");
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userName, String passWord) {

        try {
            JSON userInfo = tacticsService.login(userName, passWord);


            return "主界面-新建策略";
        }catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }

    public String addProject() {
        return null;
    }

    public String searchProject() {
        return null;
    }
}
