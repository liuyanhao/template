package com.lxc.controller.front.home;

import com.lxc.controller.base.BaseController;
import com.lxc.service.system.appuser.AppuserManager;
import com.lxc.service.tooxiu.front.FrontManager;
import com.lxc.util.AppUtil;
import com.lxc.util.PageData;
import com.lxc.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuxicai on 2018/12/9 0009.
 */
@Controller
@RequestMapping(value="/front")
public class FrontController extends BaseController {

    @Resource(name="frontService")
    private FrontManager frontService;

    @Resource(name="appuserService")
    private AppuserManager appuserService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value="/home")
    public ModelAndView getHome(){
        logBefore(logger, "首页");
        ModelAndView mv = this.getModelAndView();
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = new PageData();
        pd = this.getPageData();
        String result = "00";
        try{

        }catch (Exception e){

        }finally {
            map.put("result", result);
            logAfter(logger);
        }
        mv.setViewName("front/home/index");
        return mv;
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value="/imagetype")
    @ResponseBody
    public Object getImagetype(){
        logBefore(logger, "首页");
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = new PageData();
        pd = this.getPageData();
        String result = "00";
        try{

        }catch (Exception e){

        }finally {
            map.put("result", result);
            logAfter(logger);
        }
        return AppUtil.returnObject(new PageData(), map);
    }


    @RequestMapping(value="/getAppuserByUm")
    @ResponseBody
    public Object getAppuserByUsernmae(){
        logBefore(logger, "根据用户名获取会员信息");
        Map<String,Object> map = new HashMap<String,Object>();
        PageData pd = new PageData();
        pd = this.getPageData();
        String result = "00";
        try{
            if(Tools.checkKey("USERNAME", pd.getString("FKEY"))){	//检验请求key值是否合法
                if(AppUtil.checkParam("getAppuserByUsernmae", pd)){	//检查参数
                    pd = appuserService.findByUsername(pd);
                    map.put("pd", pd);
                    result = (null == pd) ?  "02" :  "01";
                }else {
                    result = "03";
                }
            }else {
                result = "05";
            }
        }catch (Exception e){
            logger.error(e.toString(), e);
        }finally{
            map.put("result", result);
            logAfter(logger);
        }
        return AppUtil.returnObject(new PageData(), map);
    }
}
