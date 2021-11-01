package com.ssafy.ws.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.ws.model.UserDto;

@SuppressWarnings("deprecation")
public class ConfirmInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session=request.getSession();
		UserDto userDto=(UserDto) session.getAttribute("userinfo");
		System.out.println(userDto);
		if (userDto==null) {
			response.sendRedirect(request.getContextPath()+"/");
			return false;
		}
		return true;
	}
}
