package com.example.demo.controller;

import com.example.demo.dto.MoodDTO;
import com.example.demo.service.MoodServive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Random;

/**
 * Created by Ay on 2018/1/6.
 */
//@RestController
@Controller
@RequestMapping("/mood")
public class MoodController {

    @Resource
    private MoodServive moodServive;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<MoodDTO> moodDTOList = moodServive.findAll();
        model.addAttribute("moods",moodDTOList);
        return "mood";
    }

    @RequestMapping(value = "/praise",method = RequestMethod.GET)
    public String praise(Model model, @RequestParam(value="moodId")String moodId,
                                @RequestParam(value="userId")String userId){

        boolean isPraise = moodServive.praiseMood(userId, moodId);

        //查询所有的说说数据
        List<MoodDTO> moodDTOList = moodServive.findAll();
        model.addAttribute("moods",moodDTOList);
        return "mood";
    }

    @RequestMapping(value = "/praiseForRedis",method = RequestMethod.GET)
    public String praiseForRedis(Model model, @RequestParam(value="moodId")String moodId,
                         @RequestParam(value="userId")String userId){

        //方便使用，随机生成用户id
        Random random = new Random();
        userId = random.nextInt(100) + "";

        boolean isPraise = moodServive.praiseMoodForRedis(userId, moodId);
        //查询所有的说说数据
        List<MoodDTO> moodDTOList = moodServive.findAllForRedis();
        model.addAttribute("moods",moodDTOList);
        return "mood";
    }

}
