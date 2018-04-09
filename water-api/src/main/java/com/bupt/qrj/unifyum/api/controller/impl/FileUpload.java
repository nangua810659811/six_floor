package com.bupt.qrj.unifyum.api.controller.impl;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/imageUpload.req")
public class FileUpload {

	public static ApplicationContext getContext() {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "META-INF/spring/unifyum-dal-dao.xml", "META-INF/spring/unifyum-dal-db.xml" });
		return context;
	}
    ApplicationContext context = getContext();

	@RequestMapping(method = { RequestMethod.POST }, params = "action=upload")
	public void getFile(HttpServletRequest request, HttpServletResponse response) throws Exception{


		
		 System.out.println("------------------method start-------------------------");
		
		 /*SimpleDateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");*/

         /*String imagePathDir = "/visaupload/"+dateformat.format(new Date());*/
			String imagePathDir = "/visaupload";
        /*String imageRealPathDir = this.getServletContext().getRealPath("/WEB-INF/upload");*/
         String imageRealPathDir = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
         File imageFile = new File(imageRealPathDir);
         if(!imageFile.exists())
        	 imageFile.mkdirs();
         
         System.out.println("-------------------imageRealPathDir is ----"+imageRealPathDir);
         
         
         String tempPath = request.getSession().getServletContext().getRealPath("/temp");

        System.out.println(tempPath);
         DiskFileItemFactory factory = new DiskFileItemFactory();  
         
         factory.setRepository(new File(tempPath));
         
         factory.setSizeThreshold(4*1024*1024); 
         
         ServletFileUpload upload = new ServletFileUpload(factory);  
         
//         upload.setHeaderEncoding("UTF-8");
         
         try {
			 System.out.println("step0");
			List<FileItem> list = upload.parseRequest(request);
			 System.out.println(list);

			 for(FileItem item:list){

				 if(item.isFormField()){
	                    System.out.println( "filelist" +item.getFieldName());
	                   /* System.out.println(item.getString("utf-8"));  */
	             }else{  
	                    System.out.println("filelist" + item.getFieldName());

	                    String filename = item.getName();
	                    String fileName = imageRealPathDir+File.separator+filename;
	                    File file = new File(fileName);
	                    item.write(file);
			     }

			 }
			
		} catch (FileUploadException e) {
			
			System.out.println(e.toString());
			
			e.printStackTrace();
		}
         
         
		
	}




}
