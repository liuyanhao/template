package com.lxc.controller.tooxiu.imagetype;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import javax.annotation.Resource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.lxc.controller.base.BaseController;
import com.lxc.entity.Page;
import com.lxc.util.AppUtil;
import com.lxc.util.ObjectExcelView;
import com.lxc.util.PageData;
import com.lxc.util.Jurisdiction;
import com.lxc.service.tooxiu.imagetype.ImageTypeManager;

/** 
 * 说明：服装类别管理
 * 创建人：lxc Q1094921525
 * 创建时间：2018-12-08
 */
@Controller
@RequestMapping(value="/imagetype")
public class ImageTypeController extends BaseController {
	
	String menuUrl = "imagetype/list.do"; //菜单地址(权限用)
	@Resource(name="imagetypeService")
	private ImageTypeManager imagetypeService;
	
	/**保存
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"新增ImageType");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("IMAGETYPE_ID", this.get32UUID());	//主键
		imagetypeService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**删除
	 * @throws Exception
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(@RequestParam String IMAGETYPE_ID) throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"删除ImageType");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return null;} 					//校验权限
		Map<String,String> map = new HashMap<String,String>();
		PageData pd = new PageData();
		pd.put("IMAGETYPE_ID", IMAGETYPE_ID);
		String errInfo = "success";
		if(imagetypeService.listByParentId(IMAGETYPE_ID).size() > 0){		//判断是否有子级，是：不允许删除
			errInfo = "false";
		}else{
			imagetypeService.delete(pd);	//执行删除
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
		logBefore(logger, Jurisdiction.getUsername()+"修改ImageType");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		imagetypeService.edit(pd);
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
		logBefore(logger, Jurisdiction.getUsername()+"列表ImageType");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} 	//校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}
		String IMAGETYPE_ID = null == pd.get("IMAGETYPE_ID")?"":pd.get("IMAGETYPE_ID").toString();
		if(null != pd.get("id") && !"".equals(pd.get("id").toString())){
			IMAGETYPE_ID = pd.get("id").toString();
		}
		pd.put("IMAGETYPE_ID", IMAGETYPE_ID);					//上级ID
		page.setPd(pd);
		List<PageData>	varList = imagetypeService.list(page);			//列出ImageType列表
		mv.setViewName("tooxiu/imagetype/imagetype_list");
		mv.addObject("pd", imagetypeService.findById(pd));				//传入上级所有信息
		mv.addObject("IMAGETYPE_ID", IMAGETYPE_ID);			//上级ID
		mv.addObject("varList", varList);
		mv.addObject("QX",Jurisdiction.getHC());								//按钮权限
		return mv;
	}

	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listTree")
	public ModelAndView listTree(Model model,String IMAGETYPE_ID)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			JSONArray arr = JSONArray.fromObject(imagetypeService.listTree("0"));
			String json = arr.toString();
			json = json.replaceAll("IMAGETYPE_ID", "id").replaceAll("PARENT_ID", "pId").replaceAll("NAME", "name").replaceAll("subImageType", "nodes").replaceAll("hasImageType", "checked").replaceAll("treeurl", "url");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("IMAGETYPE_ID",IMAGETYPE_ID);
			mv.addObject("pd", pd);	
			mv.setViewName("tooxiu/imagetype/imagetype_tree");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
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
		String IMAGETYPE_ID = null == pd.get("IMAGETYPE_ID")?"":pd.get("IMAGETYPE_ID").toString();
		pd.put("IMAGETYPE_ID", IMAGETYPE_ID);					//上级ID
		mv.addObject("pds",imagetypeService.findById(pd));				//传入上级所有信息
		mv.addObject("IMAGETYPE_ID", IMAGETYPE_ID);			//传入ID，作为子级ID用
		mv.setViewName("tooxiu/imagetype/imagetype_edit");
		mv.addObject("msg", "save");
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
		String IMAGETYPE_ID = pd.getString("IMAGETYPE_ID");
		pd = imagetypeService.findById(pd);							//根据ID读取		
		mv.addObject("pd", pd);													//放入视图容器
		pd.put("IMAGETYPE_ID",pd.get("PARENT_ID").toString());			//用作上级信息
		mv.addObject("pds",imagetypeService.findById(pd));				//传入上级所有信息
		mv.addObject("IMAGETYPE_ID", pd.get("PARENT_ID").toString());	//传入上级ID，作为子ID用
		pd.put("IMAGETYPE_ID",IMAGETYPE_ID);					//复原本ID
		pd = imagetypeService.findById(pd);							//根据ID读取
		mv.setViewName("tooxiu/imagetype/imagetype_edit");
		mv.addObject("msg", "edit");
		return mv;
	}	
	
	 /**导出到excel
	 * @param
	 * @throws Exception
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel() throws Exception{
		logBefore(logger, Jurisdiction.getUsername()+"导出ImageType到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("类别名称");	//1
		titles.add("类别编码");	//2
		titles.add("排序");	//3
		titles.add("备注");	//4
		titles.add("图标地址");	//5
		dataMap.put("titles", titles);
		List<PageData> varOList = imagetypeService.listAll(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var1", varOList.get(i).getString("TYPE_NAME"));	    //1
			vpd.put("var2", varOList.get(i).getString("TYPE_CODE"));	    //2
			vpd.put("var3", varOList.get(i).get("ORDER_BY").toString());	//3
			vpd.put("var4", varOList.get(i).getString("BZ"));	    //4
			vpd.put("var5", varOList.get(i).getString("IAMGE_PATH"));	    //5
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
