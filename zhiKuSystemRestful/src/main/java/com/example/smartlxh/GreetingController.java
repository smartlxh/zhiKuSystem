package com.example.smartlxh;

/**
 * Created by lixianhai on 09/11/2016.
 */


import com.sun.tools.javac.jvm.Items;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/query")
    public ResultJson queryByKey(@RequestParam(value="key") String key,@RequestParam(value="value") String value) {


        dataPersitence p = new dataPersitence();
        LinkedList result = p.getByKey(key,value);
        return new ResultJson(ReturnCodeEnum.SUCCESS.getCode(),p.getByKey(key,value));


    }

    @RequestMapping(path = "modify",method=RequestMethod.GET)
    public ResultJson updateInfoByGet(Scholar scholar){


        dataPersitence p = new dataPersitence();
        ReturnCodeEnum returncode = p.updateInfo(scholar);

        System.out.println(scholar.id);
        return new ResultJson(returncode.getCode(),null);

    }


    @RequestMapping(path = "modify",method=RequestMethod.POST)
    public ResultJson updateInfoByPost(@RequestBody Scholar scholar) {

        dataPersitence p = new dataPersitence();
        ReturnCodeEnum returncode = p.updateInfo(scholar);
        return new ResultJson(returncode.getCode(),null);


    }

    @RequestMapping(path = "insert",method=RequestMethod.GET)
    public ResultJson insertInfoByGet(Scholar scholar) {

        dataPersitence p = new dataPersitence();
        ReturnCodeEnum returncode = p.insertScholar(scholar);
        return new ResultJson(returncode.getCode(),null);

    }


    @RequestMapping(path = "insert",method=RequestMethod.POST)
    public ResultJson insertInfoByPost(@RequestBody Scholar scholar) {

        dataPersitence p = new dataPersitence();
        ReturnCodeEnum returncode = p.insertScholar(scholar);
        return new ResultJson(returncode.getCode(),null);


    }
    @RequestMapping("delete")
    public ResultJson deleteInfo(@RequestParam(value="key") String key,@RequestParam(value="value") String value) {

        dataPersitence p = new dataPersitence();
        ReturnCodeEnum returncode = p.deleteInfo(key,value);
        return new ResultJson(returncode.getCode(),null);


    }

    @RequestMapping("login")
    public ResultJson verifyPassword(@RequestParam(value="name") String name,@RequestParam(value="password") String password) {

        dataPersitence p = new dataPersitence();
        List<User> list = p.getLoginUser(name,password);
        if(list.size() > 0){
            return new ResultJson(ReturnCodeEnum.SUCCESS.getCode(),null);
        }
        else{
            return new ResultJson(ReturnCodeEnum.ERROR.getCode(),null);
        }


    }


    @RequestMapping("register")
    public ResultJson register(@RequestParam(value="name") String name,@RequestParam(value="password") String password) {

        dataPersitence p = new dataPersitence();
        User user = new User();
        user.setUserName(name);
        user.setPassWord(password);
        ReturnCodeEnum returnCodeEnum = p.insertUser(user);
        return new ResultJson(returnCodeEnum.getCode(),null);


    }


    @RequestMapping(path = "upload")
    @ResponseBody
    public ResultJson insertInfoByGet(MultipartFile file) {
        String gen = "/Users/lixianhai/Desktop";
        String filename = gen + File.separator+"文件ID"+File.separator + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        System.out.println("");
        File saveFile = new File(filename);
        try {
            if (!saveFile.getParentFile().exists() || !saveFile.getParentFile().isDirectory()) {
                saveFile.getParentFile().mkdirs();
            }
            if (saveFile != null && !saveFile.exists()) {
                saveFile.createNewFile();
            }
            file.transferTo(saveFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //添加上传地址
//        scholar.setFilePath(filename);
        //dataPersitence p = new dataPersitence();
        //ReturnCodeEnum returncode = p.insertScholar(scholar);
        return new ResultJson(ReturnCodeEnum.SUCCESS.getCode(),null);

    }


    @RequestMapping("/queryByFuzzle")
    public FuzzleQueryJson queryByFuzzle(@RequestParam(value="query") String query) {


        dataPersitence p = new dataPersitence();
        LinkedList<Work> list = p.queryByFuzzle(query);
        return new FuzzleQueryJson(ReturnCodeEnum.SUCCESS.getCode(),list);


    }

    @RequestMapping("/*")
    public ResultJson getError(){
        return new ResultJson(ReturnCodeEnum.ERROR.getCode(),new LinkedList<Scholar>());
    }





}
