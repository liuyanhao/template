package com.lxc.controller.tooxiu.order_infomx;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.lxc.controller.base.BaseController;
import com.lxc.entity.Page;
import com.lxc.util.AppUtil;
import com.lxc.util.ObjectExcelView;
import com.lxc.util.PageData;
import com.lxc.util.Jurisdiction;
import com.lxc.util.Tools;
import com.lxc.service.tooxiu.order_infomx.Order_INFOMxManager;

/** 
 * 说明：订单管理(明细)
 * 创建人：lxc Q1094921525
 * 创建时间：2018-12-09
 */
@Controller
@RequestMapping(value="/order_infomx")
public class Order_INFOMxController extends BaseController {
	
	String menuUrl = "order_infomx/list.do"; //菜单地址(权限用)
	@Resource(name="order_infomxService")
	private Order_INFOMxManager order_infomxService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Order_INFOMx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = Jurisdiction.getUsername();
		pd.put("ORDER_INFOMX_ID", this.get32UUID());	//主键
		pd.put("CREATE_USER", username);	//创建人
		pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("UPDATE_USER",username);	//修改人
		pd.put("UPDATE_TIME", Tools.date2Str(new Date()));	//修改时间
		pd.put("IS_DELETE", "2");	//是否删除 1 是 2 否
		order_infomxService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Order_INFOMx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		order_infomxService.delete(pd);
		out.write("success");
		out.close();
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Order_INFOMx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		order_infomxService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"列表Order_INFOMx");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = order_infomxService.list(page);	//列出Order_INFOMx列表
		mv.setViewName("tooxiu/order_infomx/order_infomx_list");
		mv.addObject("varList", varList);
		mv.addObject("pd", pd);
		mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
		return mv;
	}
	
	/**去新增页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("tooxiu/order_infomx/order_infomx_edit");
		mv.addObject("msg", "save");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**去修改页面
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = order_infomxService.findById(pd);	//根据ID读取
		mv.setViewName("tooxiu/order_infomx/order_infomx_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**批量删除
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"批量删除Order_INFOMx");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		pd = this.getPageData();
		List<PageData> pdList = new ArrayList<PageData>();
		String DATA_IDS = pd.getString("DATA_IDS");
		if(null != DATA_IDS && !"".equals(DATA_IDS)){
			String ArrayDATA_IDS[] = DATA_IDS.split(",");
			order_infomxService.deleteAll(ArrayDATA_IDS);
			pd.put("msg", "ok");
		}else{
			pd.put("msg", "no");
		}
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Order_INFOMx到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商品名称");	//1
		titles.add("价格");	//2
		titles.add("数量");	//3
		titles.add("创建人");	//4
		titles.add("创建时间");	//5
		titles.add("修改人");	//6
		titles.add("修改时间");	//7
		titles.add("商品详情");	//8
		titles.add("是否删除");	//9
		titles.add("商品编码");	//10
		titles.add("商品描述");	//11
		titles.add("备注");	//12
		dataMap.put("titles", titles);
		List<PageData> varOList = order_infomxService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("COMMODITY"));	    //1
			vpd.put("var2", varOList.get(i).get("PRICE").toString());	//2
			vpd.put("var3", varOList.get(i).get("AMOUNT").toString());	//3
			vpd.put("var4", varOList.get(i).getString("CREATE_USER"));	    //4
			vpd.put("var5", varOList.get(i).getString("CREATE_TIME"));	    //5
			vpd.put("var6", varOList.get(i).getString("UPDATE_USER"));	    //6
			vpd.put("var7", varOList.get(i).getString("UPDATE_TIME"));	    //7
			vpd.put("var8", varOList.get(i).getString("COMMODITY_INFO"));	    //8
			vpd.put("var9", varOList.get(i).get("IS_DELETE").toString());	//9
			vpd.put("var10", varOList.get(i).getString("COMMODITY_ID"));	    //10
			vpd.put("var11", varOList.get(i).getString("BODY"));	    //11
			vpd.put("var12", varOList.get(i).getString("BZ"));	    //12
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		return mv;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
