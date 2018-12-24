package com.lxc.service.tooxiu.imagetype.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lxc.dao.DaoSupport;
import com.lxc.entity.Page;
import com.lxc.entity.tooxiu.ImageType;
import com.lxc.util.PageData;
import com.lxc.service.tooxiu.imagetype.ImageTypeManager;

/** 
 * 说明： 服装类别管理
 * 创建人：lxc Q1094921525
 * 创建时间：2018-12-08
 * @version
 */
@Service("imagetypeService")
public class ImageTypeService implements ImageTypeManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception{
		dao.save("ImageTypeMapper.save", pd);
	}
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception{
		dao.delete("ImageTypeMapper.delete", pd);
	}
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception{
		dao.update("ImageTypeMapper.edit", pd);
	}
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ImageTypeMapper.datalistPage", page);
	}
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ImageTypeMapper.listAll", pd);
	}
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ImageTypeMapper.findById", pd);
	}

	/**
	 * 通过ID获取其子级列表
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ImageType> listByParentId(String parentId) throws Exception {
		return (List<ImageType>) dao.findForList("ImageTypeMapper.listByParentId", parentId);
	}
	
	/**
	 * 获取所有数据并填充每条数据的子级列表(递归处理)
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<ImageType> listTree(String parentId) throws Exception {
		List<ImageType> valueList = this.listByParentId(parentId);
		for(ImageType fhentity : valueList){
			fhentity.setTreeurl("imagetype/list.do?IMAGETYPE_ID="+fhentity.getIMAGETYPE_ID());
			fhentity.setSubImageType(this.listTree(fhentity.getIMAGETYPE_ID()));
			fhentity.setTarget("treeFrame");
		}
		return valueList;
	}
		
}

