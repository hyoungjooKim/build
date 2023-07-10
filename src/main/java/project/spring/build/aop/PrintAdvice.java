package project.spring.build.aop;
//임시
import javax.servlet.http.HttpServletRequest;
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
	
	
	@Around("execution( * test.spring.service.Member*.*(..))")	
	public Object printMVC(ProceedingJoinPoint jp) throws Throwable{	//흐름에 영향을 미침(그대로 두면 잡혀있는 상태)
		//세션 확인
		RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
		ServletRequestAttributes sa = (ServletRequestAttributes)ra;
		HttpServletRequest request = sa.getRequest();
		HttpSession session = request.getSession();
		Object obj = null;
		if(session.getAttribute("memId") != null) {
			//로그인됨!!!
			obj = jp.proceed();  //매서드 실행을 진행시킴	(void 일땐 가는것만(return 이 없어서))
		}		
		System.out.println("체크");
		return obj;
	}
	@Around("execution( * test.spring.service.AopService*.*(..))")		
	public Object printAround(ProceedingJoinPoint jp) throws Throwable{	//흐름에 영향을 미침(그대로 두면 잡혀있는 상태)
		System.out.println("aop Around before");
		Object obj = jp.proceed();		//매서드 실행을 진행시킴	(void 일땐 가는것만(return 이 없어서))
		System.out.println("aop Around after");
		return obj;
	}

	

}
