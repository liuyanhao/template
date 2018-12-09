package com.lxc.entity.tooxiu;

import java.util.List;

/** 
 * 说明：服装类别管理 实体类
 * 创建人：lxc Q1094921525
 * 创建时间：2018-12-08
 */
public class ImageType{ 
	
	private String IMAGETYPE_ID;	//主键
	private String NAME;					//名称
	private String PARENT_ID;				//父类ID
	private String target;
	private ImageType imagetype;
	private List<ImageType> subImageType;
	private boolean hasImageType = false;
	private String treeurl;
	
	private String TYPE_NAME;			//类别名称
	public String getFTYPE_NAME() {
		return TYPE_NAME;
	}
	public void setFTYPE_NAME(String TYPE_NAME) {
		this.TYPE_NAME = TYPE_NAME;
	}
	private String TYPE_CODE;			//类别编码
	public String getFTYPE_CODE() {
		return TYPE_CODE;
	}
	public void setFTYPE_CODE(String TYPE_CODE) {
		this.TYPE_CODE = TYPE_CODE;
	}
	private int ORDER_BY;				//排序
	public int getFORDER_BY() {
		return ORDER_BY;
	}
	public void setFORDER_BY(int ORDER_BY) {
		this.ORDER_BY = ORDER_BY;
	}
	private String BZ;			//备注
	public String getFBZ() {
		return BZ;
	}
	public void setFBZ(String BZ) {
		this.BZ = BZ;
	}
	private String IAMGE_PATH;			//图标地址
	public String getFIAMGE_PATH() {
		return IAMGE_PATH;
	}
	public void setFIAMGE_PATH(String IAMGE_PATH) {
		this.IAMGE_PATH = IAMGE_PATH;
	}

	public String getIMAGETYPE_ID() {
		return IMAGETYPE_ID;
	}
	public void setIMAGETYPE_ID(String IMAGETYPE_ID) {
		this.IMAGETYPE_ID = IMAGETYPE_ID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String PARENT_ID) {
		this.PARENT_ID = PARENT_ID;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public ImageType getImageType() {
		return imagetype;
	}
	public void setImageType(ImageType imagetype) {
		this.imagetype = imagetype;
	}
	public List<ImageType> getSubImageType() {
		return subImageType;
	}
	public void setSubImageType(List<ImageType> subImageType) {
		this.subImageType = subImageType;
	}
	public boolean isHasImageType() {
		return hasImageType;
	}
	public void setHasImageType(boolean hasImageType) {
		this.hasImageType = hasImageType;
	}
	public String getTreeurl() {
		return treeurl;
	}
	public void setTreeurl(String treeurl) {
		this.treeurl = treeurl;
	}
	
}
