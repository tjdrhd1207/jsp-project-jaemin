package com.kh.jsp.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.jsp.board.model.service.BoardService;
import com.kh.jsp.board.model.vo.Attachment;
import com.kh.jsp.board.model.vo.Board;
import com.kh.jsp.common.MyFileRenamePolicy;
import com.kh.jsp.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ThumbnailInsertServlet
 */
@WebServlet("/insert.tn")
public class ThumbnailInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		
//		System.out.println("title : "+title);
//		System.out.println("content : "+content);
		
		
		if(ServletFileUpload.isMultipartContent(request)){
			//전송 파일 용량 제한 :10MByte 인 경우
			int maxSize = 1024 * 1024 * 10;
		
			String root = request.getSession().getServletContext().getRealPath("/");
			
			System.out.println(root);
			
			String filePath = root+ "resources/thumbnail_uploadFiles/";
		
			//cos.jar 라이브러리 사용
			//com.orelilly.servlet의 약자이다
			
			//사용자가 올린 파일명을 그대로 저장하지 않는게 일반적이다.
			//1. 같은 파일명이 있는 경우 이전 파일을 덮어 쓸 수 있다.
			//2. 한글로 된 파일명, 특수기호나 띄어쓰기등은 서버에 따라 문제가 생길 수 있다.
			//DefaultFileRenamePolicy는 cos.jar 안에 존재하는 기본적인 파일 이름을 변경하는 방식이 지정된 클래스이다.
			//같은 파일명이 존재하는지를 검사하고, 있을 경우 파일명 뒤에 숫자를 붙여주는 방식
			//예)aaa.zip, aaa1.zip, aaa2.zip
//			MultipartRequest multiRequest = new MultipartRequest(request,filePath,maxSize,"UTF-8",new DefaultFileRenamePolicy());
			
			MultipartRequest multiRequest = new MultipartRequest(request,filePath,maxSize,"UTF-8",new MyFileRenamePolicy());
			
			//저장한 파일 이름을 보관할 arrayList (변경된 파일 이름)
			ArrayList <String> saveFiles = new ArrayList<>();
			//원본 파일의 이름을 보관할 arrayList
			ArrayList <String> originFiles = new ArrayList<>();
			
			//enumeration 반복문을 사용할 수 있게 만든 객체
			//파일이 전송된 이름을 반환
			Enumeration <String> files = multiRequest.getFileNames();
										//input파일의 name속성값을 반환
			
			while(files.hasMoreElements()){
				String name= files.nextElement();
				
				System.out.println("name : "+name);
				
				saveFiles.add(multiRequest.getFilesystemName(name));
				originFiles.add(multiRequest.getOriginalFileName(name));
			}
			
			System.out.println("fileSystem name : "+saveFiles);
			System.out.println("originFile name : "+originFiles);
			
			//multipartRequest를 이용해서 파일 외에 form 전송 값 꺼내오기
			String multiTitle = multiRequest.getParameter("title");
			String multiContent = multiRequest.getParameter("content");
			
			System.out.println("title : "+multiTitle);
			System.out.println("content : "+multiContent );
			
			
			int bWriter = ((Member)request.getSession().getAttribute("loginUser")).getUno();
		
			Board board = new Board();
			board.setbTitle(multiTitle);
			board.setbContent(multiContent);
			board.setbWriter(bWriter);
			
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
	
			for(int i=originFiles.size()-1 ; i>=0; i--){
				Attachment at = new Attachment();
				at.setFilePath(filePath);
				at.setOriginName(originFiles.get(i));
				at.setChangeName(saveFiles.get(i));
				
				
				if(i == originFiles.size()-1){
					at.setFileLevel(0);
				}else {
					at.setFileLevel(1);
				}
				
				fileList.add(at);
			}
			System.out.println("upload fileList : "+fileList);
		
			Map<String, Object> requestData = new HashMap<String,Object>();
			requestData.put("board", board);
			requestData.put("fileList", fileList);
			
			System.out.println("requestData : "+ requestData);
			
			int result = new BoardService().insertThumbnail(requestData);
			System.out.println("result : "+result);
			if(result>0){
				response.sendRedirect(request.getContextPath()+"/selectList.tn");
				
			}else {
				
				//실패시 저장된 사진 삭제
				for(int i=0; i< saveFiles.size(); i++){
					File faildFile = new File(filePath + saveFiles.get(i));
					
					faildFile.delete();
					
				}
				request.setAttribute("message","사진 게시판 등록 실패!!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request,response);
			}
			
		
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
