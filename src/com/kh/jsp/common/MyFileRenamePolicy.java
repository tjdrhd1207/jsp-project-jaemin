package com.kh.jsp.common;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile){
		long currentTime = System.currentTimeMillis();
		
		int randomNumber = (int)(Math.random()*100000);
		
		//확장자명 분리 
		
		String name = oldFile.getName();
		String body = "";
		String ext = "";
	
		 //파일에 .이라는게 없으면 -1로 반환하고 있으면 양수로 반환함
	     //hojong.jpg 는 양수라는뜻 hojong은 -1

		int dot = name.lastIndexOf(".");
		if(dot != -1){
			//확장자가 있는 경우
			body = name.substring(0, dot);
			ext = name.substring(dot);
		}else {
			//확장자가 없는 경우
			body = name;
			
		}
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = ft.format(new Date(currentTime))+randomNumber + ext;

		File newFile = new File(oldFile.getParent(), fileName);
		
		return newFile;
	}
}
