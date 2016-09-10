package com.itheima.loadimages.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.itheima.loadimages.model.ImageData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageUtil {

	private ImageUtil(){}
	private static ImageUtil instance;

	/**单例*/
	public static ImageUtil getInstance(){
		if(instance == null){
			synchronized (ImageUtil.class){
				if(instance == null){
					instance = new ImageUtil();
				}
			}
		}
		return instance;
	}

	/**
	 * 读取相册中的图片
	 * @param context
	 * @return
	 */
	public List<ImageData> getMediaImage(Context context) {
		List<ImageData> datas = new ArrayList<ImageData>();
		final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
		final String[] columns = { MediaStore.Images.Media.DATA,MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DISPLAY_NAME };
		Cursor imagecursor = context.getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
				null, orderBy + " DESC");

		while(imagecursor.moveToNext()){
			int dataColumnIndex = imagecursor
					.getColumnIndex(MediaStore.Images.Media.DATA);
			int dirColumnIndex = imagecursor
					.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
			String buckedName = imagecursor.getString(dirColumnIndex);
			Log.v("image", "buckedName = " + buckedName);
			String filename = imagecursor.getString(dataColumnIndex);
			try {
				File file = new File(filename);
				if (!file.exists()) {
					continue;
				}
				Log.v("image", "filename = " + filename);
				ImageData galleryModel = new ImageData("file:/" + imagecursor.getString(dataColumnIndex).toString(), false);
				datas.add(galleryModel);
			} catch (Exception e) {
				continue;
			}
		}
		imagecursor.close();
		return datas;
	}

	/**
	 * 获取网页图片
	 * 
	 * @param context
	 * @return
	 */
	public HashMap<Integer, ImageData> getHttpImage(Context context) {
		String[] imageUrls = { "http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=30461668ab014c080d3620f76b12696d/d4628535e5dde711498dd837a5efce1b9c166190.jpg", 
				"http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=8d24b3b6e9f81a4c323fe49bb6430b3c/4034970a304e251f14dde53aa586c9177e3e53cd.jpg",
				"http://img12.360buyimg.com/n7/g15/M00/14/06/rBEhWlJ6BhcIAAAAAAFec2_o-0MAAFE-QFQCCoAAV6L159.jpg",
				"http://img12.360buyimg.com/n7/jfs/t205/73/3477944297/116109/e301ca30/53e1e522N503a1478.jpg",
				"http://img11.360buyimg.com/n1/g16/M00/0C/10/rBEbRlOO2LMIAAAAAAFq08aUYxoAACb9gA2bmgAAWrr167.jpg",
				"http://img30.360buyimg.com/pop/jfs/t253/106/1047558511/88461/e0f1ae61/53f5520eNddca4235.jpg",
				"http://img10.360buyimg.com/N3/jfs/t178/79/1794568307/193482/3f0296b0/53ba30daN72be9046.jpg",
				"http://img10.360buyimg.com/N3/jfs/t160/208/2479381927/1664774/13ec7860/53d0afc6N1fb427fe.jpg",
				"http://img10.360buyimg.com/N3/jfs/t178/336/3065682196/1014211/c68d67d3/53e02badN45f5b17c.jpg",
				"http://img10.360buyimg.com/N3/g14/M03/11/0A/rBEhVlI9UOEIAAAAAADSicb_cewAADYxgOwluQAANKh968.jpg",
				"http://b.hiphotos.baidu.com/image/pic/item/0ff41bd5ad6eddc4b05e750d3bdbb6fd5366338f.jpg",
				"http://c.hiphotos.baidu.com/image/w%3D230/sign=ddc644dd85d6277fe912353b18391f63/9922720e0cf3d7ca210cef10f01fbe096a63a9da.jpg",
				"http://g.hiphotos.baidu.com/image/pic/item/eaf81a4c510fd9f91194e601272dd42a2934a4d3.jpg",
				"http://c.hiphotos.baidu.com/image/w%3D2048/sign=3f0e215c38f33a879e6d071af2641138/55e736d12f2eb9387167d89ed7628535e5dd6f50.jpg",
				"http://a.hiphotos.baidu.com/image/w%3D310/sign=425764c7e2fe9925cb0c6f5104a95ee4/3ac79f3df8dcd100c2df8423708b4710b9122f74.jpg",
				"http://d.hiphotos.baidu.com/image/w%3D310/sign=3c7283257d3e6709be0043fe0bc69fb8/7a899e510fb30f24d9539301ca95d143ac4b03e2.jpg",
				"http://b.hiphotos.baidu.com/image/w%3D310/sign=e827cbf93bdbb6fd255be3273925aba6/8b82b9014a90f603ca70e3453b12b31bb151edf4.jpg",
				"http://e.hiphotos.baidu.com/image/w%3D310/sign=656802aeab014c08193b2ea43a7b025b/bf096b63f6246b60807f3d73e9f81a4c510fa240.jpg",
				"http://e.hiphotos.baidu.com/image/w%3D310/sign=564ef22579f40ad115e4c1e2672c1151/eaf81a4c510fd9f98b1f9074272dd42a2834a4a7.jpg",
				"http://a.hiphotos.baidu.com/image/w%3D310/sign=425764c7e2fe9925cb0c6f5104a95ee4/3ac79f3df8dcd100c2df8423708b4710b9122f74.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=56ca3afd39c79f3d8fe1e2318aa0cdbc/43a7d933c895d14332bc9b2171f082025baf07ed.jpg",
				"http://f.hiphotos.baidu.com/image/w%3D310/sign=6ef72347c88065387beaa212a7dca115/fcfaaf51f3deb48f71b007d7f21f3a292cf578da.jpg",
				"http://c.hiphotos.baidu.com/image/w%3D310/sign=f71fadff79899e51788e3c1572a6d990/8718367adab44aedec96c0e6b11c8701a08bfbdc.jpg",
				"http://c.hiphotos.baidu.com/image/w%3D310/sign=5ad89a32b8a1cd1105b674218912c8b0/ac4bd11373f08202f8abef3d49fbfbedab641bf2.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=9e72eade0afa513d51aa6adf0d6c554c/14ce36d3d539b600350e70b5eb50352ac75cb7eb.jpg",
				"http://g.hiphotos.baidu.com/image/w%3D310/sign=4d09bd1669600c33f079d8c92a4d5134/d1a20cf431adcbef50630004aeaf2edda3cc9f58.jpg",
				"http://b.hiphotos.baidu.com/image/w%3D310/sign=554a7620d01b0ef46ce89e5fedc551a1/b219ebc4b74543a973aec4c21c178a82b9011402.jpg",
				"http://g.hiphotos.baidu.com/image/w%3D310/sign=67fd680dba0e7bec23da05e01f2fb9fa/b2de9c82d158ccbf327dc9381bd8bc3eb03541ea.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=c58d3227aad3fd1f3609a43b004e25ce/38dbb6fd5266d0164e223f21952bd40735fa35fb.jpg",
				"http://e.hiphotos.baidu.com/image/w%3D310/sign=074f0d680db30f24359aea02f894d192/dbb44aed2e738bd4cfda1438a38b87d6267ff9c4.jpg",
				"http://b.hiphotos.baidu.com/image/w%3D310/sign=0504a294c9ef76093c0b9f9e1edca301/5d6034a85edf8db1c2e3da6d0b23dd54574e74ed.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=d1c120520e3387449cc5297d610ed937/0df431adcbef76096cd3e79d2cdda3cc7dd99ed3.jpg",
				"http://f.hiphotos.baidu.com/image/w%3D310/sign=f79f45b1d63f8794d3ff4e2fe21a0ead/f636afc379310a55d6c378fdb54543a9832610d7.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=6552bbe1552c11dfded1b92253266255/d62a6059252dd42a2fbf7da9013b5bb5c9eab879.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=fcd5da6d0b23dd542173a169e108b3df/c9fcc3cec3fdfc03a58345b1d63f8794a4c2263b.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=b56c439b0ef431adbcd245387b37ac0f/9825bc315c6034a859041e88c9134954082376d0.jpg",
				"http://g.hiphotos.baidu.com/image/w%3D310/sign=2c6cd429bb12c8fcb4f3f0cccc0292b4/5bafa40f4bfbfbedf58390767af0f736aec31fe4.jpg",
				"http://d.hiphotos.baidu.com/image/w%3D310/sign=136bcd2a347adab43dd01d42bbd5b36b/54fbb2fb43166d22a4386b96442309f79052d261.jpg",
				"http://g.hiphotos.baidu.com/image/w%3D310/sign=ec9399240b24ab18e016e73605fbe69a/728da9773912b31b0ea4b12b8418367adbb4e1ec.jpg",
				"http://f.hiphotos.baidu.com/image/w%3D310/sign=f9aadddcf21f3a295ac8d3cfa924bce3/6609c93d70cf3bc7eac3b1eed300baa1cc112af4.jpg",
				"http://e.hiphotos.baidu.com/image/w%3D310/sign=abb8a2ca184c510faec4e41b50582528/77094b36acaf2edd8f3035eb8f1001e9380193ea.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=7b54cca6a6c27d1ea5263dc52bd4adaf/78310a55b319ebc4182e089b8026cffc1e171659.jpg",
				"http://h.hiphotos.baidu.com/image/w%3D310/sign=29aff8590e3387449cc5297d610ed937/0df431adcbef760994bd3f962cdda3cc7cd99e01.jpg",
				"http://b.hiphotos.baidu.com/image/w%3D310/sign=805934bfcb177f3e1034fa0c40ce3bb9/e7cd7b899e510fb35eb5d19edb33c895d1430c0e.jpg",
				"http://g.hiphotos.baidu.com/image/w%3D310/sign=ed783a4e3b12b31bc76ccb28b6193674/09fa513d269759eea398c6c2b0fb43166c22dfeb.jpg",
				"http://g.hiphotos.baidu.com/image/w%3D310/sign=4a2517ed8fb1cb133e693a12ed5556da/738b4710b912c8fcbd438acffe039245d688217b.jpg",
				"http://c.hiphotos.baidu.com/image/w%3D310/sign=1f6a434900087bf47dec51e8c2d2575e/8644ebf81a4c510fdb069e186259252dd52aa5f5.jpg",
				"http://c.hiphotos.baidu.com/image/w%3D310/sign=1f6a434900087bf47dec51e8c2d2575e/8644ebf81a4c510fdb069e186259252dd52aa5f5.jpg",
				"http://d.hiphotos.baidu.com/image/w%3D310/sign=1a3fe679d31373f0f53f699e940e4b8b/86d6277f9e2f0708e3445402eb24b899a901f26b.jpg",
				"http://e.hiphotos.baidu.com/image/w%3D310/sign=97ee13f78f1001e94e3c120e880e7b06/dc54564e9258d10987fcf3acd358ccbf6c814d94.jpg",
				"http://f.hiphotos.baidu.com/image/w%3D310/sign=8d21d310700e0cf3a0f748fa3a47f23d/cb8065380cd791238aaeea76af345982b2b78061.jpg",
				"http://c.hiphotos.baidu.com/image/w%3D310/sign=12996b208418367aad8979dc1e728b68/3c6d55fbb2fb4316cfeadc7322a4462308f7d3c6.jpg"};
		HashMap<Integer, ImageData> datas = new HashMap<Integer, ImageData>();
		for (int i = 0; i < imageUrls.length; i++) {
			datas.put(i, new ImageData(imageUrls[i], false));
		}
		return datas;
	}
}
