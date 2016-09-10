package com.itheima.loadimages.model;

/**
 * 图片数据模型封装
 */
public class ImageData {

	public String url = null;
	public boolean isChecked = false;

	public ImageData(String url, boolean status) {
		this.url = url;
		this.isChecked = status;
	}
}
