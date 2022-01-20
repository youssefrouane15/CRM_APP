package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}

	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {
	}

	// setup @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {

		// display the method we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("======>> in @Before: CALLING METHOD:" + method);

		// display the arguments of the method

		// get the arguments
		Object[] args = joinPoint.getArgs();

		// loop through the arguments
		for (Object tempArg : args) {
			myLogger.info("=========>> argument: " + tempArg);
		}
	}

	// setup @AfterReturning advice
	@AfterReturning(pointcut = "forAppFlow()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {

		// display the method we are calling
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("======>> in @AfterReturning: CALLING METHOD:" + method);

		// display the data returned
		myLogger.info("======>> Result: " + result);

	}

}
