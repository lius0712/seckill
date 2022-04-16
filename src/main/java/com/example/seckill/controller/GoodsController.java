package com.example.seckill.controller;

import com.example.seckill.pojo.User;
import com.example.seckill.service.IGoodsService;
import com.example.seckill.service.IUserService;
import com.example.seckill.vo.GoodsVo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;

    /**
     * 跳转到商品列表页
     * @param
     * @param model
     * @param
     * @return
     */

    @RequestMapping("/toList")
    public String toList(Model model, User user) {

//        if(StringUtils.isEmpty(ticket)) {
//            return "login";
//        }
//
//        User user = userService.getUserByCookie(ticket, request, response);
//        if(null == user) {
//            return "login";
//        }

        model.addAttribute("user", user);
        model.addAttribute("goodsList", goodsService.findGoodsVo());
        return "goodsList";
    }

    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, User user, @PathVariable Long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();
        //秒杀状态
        int seckillStatus = 0;
        //秒杀倒计时
        int remainSecond = 0;
        //秒杀还未开始
        if(nowDate.before(startDate)) {
            remainSecond = (int) ((startDate.getTime() - nowDate.getTime()) / 1000);
        } else if(nowDate.after(endDate)) {
            //秒杀已经结束
            seckillStatus = 2;
            remainSecond = 1;
        } else {
            //秒杀进行时
            seckillStatus = 1;
            remainSecond = 0;
        }
        model.addAttribute("remainSecond",remainSecond);
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("goods", goodsVo);
        return "goodsDetail";
    }


}
