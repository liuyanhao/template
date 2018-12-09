package com.lxc.controller.tooxiu.order_info;

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
import com.lxc.service.tooxiu.order_info.Order_INFOManager;
import com.lxc.service.tooxiu.order_infomx.Order_INFOMxManager;

/** 
 * 说明：订单管理
 * 创建人：lxc Q1094921525
 * 创建时间：2018-12-09
 */
@Controller
@RequestMapping(value="/order_info")
public class Order_INFOController extends BaseController {
	
	String menuUrl = "order_info/list.do"; //菜单地址(权限用)
	@Resource(name="order_infoService")
	private Order_INFOManager order_infoService;
	
	@Resource(name="order_infomxService")
	private Order_INFOMxManager order_infomxService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增Order_INFO");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String username = Jurisdiction.getUsername();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("ORDER_INFO_ID", this.get32UUID());	//主键
		pd.put("CREATE_USER", username);	//创建人
		pd.put("CREATE_TIME", Tools.date2Str(new Date()));	//创建时间
		pd.put("UPDATE_USER", username);	//修改人
		pd.put("UPDATE_TIME", Tools.date2Str(new Date()));	//修改时间
		pd.put("IS_DELETE", "2");	//是否删除 1是 2否
		pd.put("SIGN", "");	//签名
		pd.put("RECEIPT", "");	//开发发票入口开发标识
		pd.put("DEVICE_INFO", "");	//设备号
		order_infoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除Order_INFO");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null ;} //校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "success";
		if(Integer.parseInt(order_infomxService.findCount(pd).get("zs").toString()) > 0){
			errInfo = "false";
		}else{
			order_infoService.delete(pd);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**修改
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"修改Order_INFO");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String username = Jurisdiction.getUsername();
		pd.put("UPDATE_USER",username);
		pd.put("UPDATE_TIME", Tools.date2Str(new Date()));	//修改时间
		order_infoService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表Order_INFO");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");				//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		page.setPd(pd);
		List<PageData>	varList = order_infoService.list(page);	//列出Order_INFO列表
		mv.setViewName("tooxiu/order_info/order_info_list");
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
		mv.setViewName("tooxiu/order_info/order_info_edit");
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
		pd = order_infoService.findById(pd);	//根据ID读取
		mv.setViewName("tooxiu/order_info/order_info_edit");
		mv.addObject("msg", "edit");
		mv.addObject("pd", pd);
		return mv;
	}	
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出Order_INFO到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商户订单号");	//1
		titles.add("创建人");	//2
		titles.add("创建时间");	//3
		titles.add("修改人");	//4
		titles.add("修改时间");	//5
		titles.add("是否删除 1是 2否");	//6
		titles.add("状态");	//7
		titles.add("订单金额");	//8
		titles.add("备注");	//9
		titles.add("支付方式");	//10
		titles.add("下单时间");	//11
		titles.add("支付账户");	//12
		titles.add("公众账号ID");	//13
		titles.add("商户号");	//14
		titles.add("签名类型");	//15
		titles.add("签名");	//16
		titles.add("货币类型");	//17
		titles.add("订单优惠标记");	//18
		titles.add("终端IP");	//19
		titles.add("交易起始时间");	//20
		titles.add("交易结束时间");	//21
		titles.add("授权码");	//22
		titles.add("开发发票入口开发标识");	//23
		titles.add("设备号");	//24
		titles.add("随机字符串");	//25
		dataMap.put("titles", titles);
		List<PageData> varOList = order_infoService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("OUT_TRADE_NO"));	    //1
			vpd.put("var2", varOList.get(i).getString("CREATE_USER"));	    //2
			vpd.put("var3", varOList.get(i).getString("CREATE_TIME"));	    //3
			vpd.put("var4", varOList.get(i).getString("UPDATE_USER"));	    //4
			vpd.put("var5", varOList.get(i).getString("UPDATE_TIME"));	    //5
			vpd.put("var6", varOList.get(i).get("IS_DELETE").toString());	//6
			vpd.put("var7", varOList.get(i).get("ORDER_STATUS").toString());	//7
			vpd.put("var8", varOList.get(i).get("TOTAL_FEE").toString());	//8
			vpd.put("var9", varOList.get(i).getString("BZ"));	    //9
			vpd.put("var10", varOList.get(i).getString("PAY_TYPE"));	    //10
			vpd.put("var11", varOList.get(i).getString("PAY_TIME"));	    //11
			vpd.put("var12", varOList.get(i).getString("PAY_ACCOUNT"));	    //12
			vpd.put("var13", varOList.get(i).getString("APPID"));	    //13
			vpd.put("var14", varOList.get(i).getString("MCH_ID"));	    //14
			vpd.put("var15", varOList.get(i).getString("SIGN_TYPE"));	    //15
			vpd.put("var16", varOList.get(i).getString("SIGN"));	    //16
			vpd.put("var17", varOList.get(i).getString("FEE_TYPE"));	    //17
			vpd.put("var18", varOList.get(i).getString("GOODS_TAG"));	    //18
			vpd.put("var19", varOList.get(i).getString("SPBILL_CREATE_IP"));	    //19
			vpd.put("var20", varOList.get(i).getString("TIME_START"));	    //20
			vpd.put("var21", varOList.get(i).getString("TIME_EXPIRE"));	    //21
			vpd.put("var22", varOList.get(i).getString("AUTH_CODE"));	    //22
			vpd.put("var23", varOList.get(i).getString("RECEIPT"));	    //23
			vpd.put("var24", varOList.get(i).getString("DEVICE_INFO"));	    //24
			vpd.put("var25", varOList.get(i).getString("NONCE_STR"));	    //25
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
