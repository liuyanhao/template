package com.lxc.service.system.createcode.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lxc.dao.DaoSupport;
import com.lxc.entity.Page;
import com.lxc.service.system.createcode.CreateCodeManager;
import com.lxc.util.PageData;


/** 
 * 类名称：CreateCodeService 代码生成器
 * 创建人：lxc Q1094921525
 * 修改时间：2018年11月24日
 * @version
 */
@Service("createcodeService")
public class CreateCodeService implements CreateCodeManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("CreateCodeMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("CreateCodeMapper.delete", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CreateCodeMapper.datalistPage", page);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CreateCodeMapper.findById", pd);
	}
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CreateCodeMapper.deleteAll", ArrayDATA_IDS);
	}

	/**列表(主表)
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listFa() throws Exception {
		return (List<PageData>)dao.findForList("CreateCodeMapper.listFa", "");
	}
	
}

