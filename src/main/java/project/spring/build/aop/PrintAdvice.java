package project.spring.build.aop;
//юс╫ц
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PrintAdvice {
	
	

	@Around("execution(* project.spring.build.controller.*.apiinkorea(..))")
	public Object printBeforeAndRedirect(ProceedingJoinPoint joinPoint) throws Throwable {
	    // System.out.println("=====aop before======");

	    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    HttpServletRequest request = attributes.getRequest();
	    HttpServletResponse response = attributes.getResponse();
	    HttpSession session = request.getSession();

	    if (session.getAttribute("Email") == null) {
	       // System.out.println("Email attribute is null");

	        // Redirect to the desired URL
	        response.sendRedirect(request.getContextPath() + "/conn");

	        return null; // Optional, if you don't want to proceed with the target method
	    }

	    //System.out.println("=============");

	    // Proceed with the execution of the target method
	    return joinPoint.proceed();
	}



}
