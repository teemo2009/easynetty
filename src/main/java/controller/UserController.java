package controller;

import annotation.Controller;
import annotation.RequestMapping;
import io.netty.channel.ChannelHandlerContext;
import model.MyMsg;
import model.User;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
public class UserController {

    @RequestMapping
    public void login(ChannelHandlerContext ctx, User user){
        System.out.println("There is login method!!!!!!");
    }


    @RequestMapping
    public void sendMsg(ChannelHandlerContext ctx, MyMsg myMsg){
        System.out.println("There is send method!!!!!!");
    }


}
