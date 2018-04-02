package com.example.smartlxh.service;

/**
 * Created by lixianhai on 09/11/2016.
 */


import com.example.smartlxh.*;
import com.example.smartlxh.dao.dataPersitence;
import com.example.smartlxh.model.*;
import com.example.smartlxh.util.Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.smartlxh.model.ResultJson;
import com.example.smartlxh.model.ReturnCodeEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private static final String WEB_INf = "~/zhiKuSystem";
    @RequestMapping("/query")
    public ResultJson queryByKey(@RequestParam(value="key") String key, @RequestParam(value="value") String value) {


        dataPersitence p = new dataPersitence();
        LinkedList result = p.getByKey(key,value);
        return new ResultJson(ReturnCodeEnum.SUCCESS.getCode(),p.getByKey(key,value));


    }

    @RequestMapping(path = "modify",method=RequestMethod.GET)
    public ResultJson updateInfoByGet(Scholar scholar){


        dataPersitence p = new dataPersitence();
        ReturnCodeEnum returncode = p.updateInfo(scholar);


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


    @RequestMapping(path = "uploadSingleFile")
    @ResponseBody
    public ResultJson insertInfoByGet( MultipartFile file) {

        String filename = WEB_INf + File.separator+"文件ID"+File.separator + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ReturnCodeEnum returnCodeEnum = Utils.saveFile(file,filename);

        return new ResultJson(returnCodeEnum.getCode(),null);

    }


    @RequestMapping(path = "uploadMultiFiles")
    @ResponseBody
    public ResultJson insertInfoByGet( MultipartFile[] files) {



        for(int i = 0;i < files.length;i++){
            String filename = WEB_INf + File.separator+"文件ID"+File.separator + "_" + System.currentTimeMillis() + "_" + files[i].getOriginalFilename();
            ReturnCodeEnum  returnCodeEnum = Utils.saveFile(files[i],filename);
            if(returnCodeEnum == ReturnCodeEnum.ERROR){
                return new ResultJson(returnCodeEnum.getCode(),null);
            }
        }

        return new ResultJson(ReturnCodeEnum.SUCCESS.getCode(),null);

    }


    @RequestMapping(value = "/Download", method = RequestMethod.GET)
    public void testDownload(@RequestParam("filename") String filename, HttpServletResponse res) {

        String fileName = filename;
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("/Users/lixianhai/test.cpp")));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

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
