package com.model2.mvc.web.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.Comment;

@Controller
@RequestMapping("/comment/*")
public class CommentController {

	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentService;
	
	public CommentController() {
		System.out.println("CommentController Dafault Constructor");
	}
	
	@RequestMapping("/addComment")
	public String addProductView(@ModelAttribute("comment") Comment comment, @RequestParam("prod_No") int prodNo, HttpServletRequest request) throws Exception { 
		
		System.out.println("comment :: " + comment);

		comment.setProdNo(prodNo);
		System.out.println(request.getParameter("reply"));
		//comment.setReply(request.getParameter("reply"));
		
		System.out.println("comment :: " + comment);
		
		commentService.addComment(comment);
		
		return "redirect:/comment/listComment?prod_No="+prodNo;
	}
	
	@RequestMapping("/listComment")
	public String listProductView(@RequestParam("prod_No") int prodNo, HttpServletRequest request) throws Exception { 
		
		Map<String, Object> map = commentService.getCommentList(prodNo);
		System.out.println(" :: " + map.get("list") + " \n");
		request.setAttribute("list", map.get("list"));
		
		return "forward:/product/getProduct.jsp";
	}
	
}
