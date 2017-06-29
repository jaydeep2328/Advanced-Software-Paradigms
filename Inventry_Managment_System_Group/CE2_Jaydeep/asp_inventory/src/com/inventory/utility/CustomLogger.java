package com.inventory.utility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CustomLogger {
	private final Logger log = Logger.getLogger(this.getClass());

	private static final Set<String> exceptionalClasses = new HashSet<String>(Arrays.asList(
		     new String[] {"Interceptor"}
		));
	
	@Around("execution(* com.ict.trust.bsst..*.*(..))")
	public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		
		if(!exceptionalClasses.contains(joinPoint.getTarget().getClass().getSimpleName())){
			StringBuffer logMessage1 = new StringBuffer("START : ");
			logMessage1.append(joinPoint.getTarget().getClass().getSimpleName());
			logMessage1.append(".");
			logMessage1.append(joinPoint.getSignature().getName());
			log.info(logMessage1.toString());	
		}
		Object retVal = null;
		try {
			retVal = joinPoint.proceed();
		} catch (Throwable e) {
			StringBuffer logMessage2 = new StringBuffer("Error : ");
			logMessage2.append(joinPoint.getTarget().getClass().getSimpleName());
			logMessage2.append(".");
			logMessage2.append(joinPoint.getSignature().getName());
			log.error(logMessage2.toString() +" "+ e );
			e.printStackTrace();
		}

		if(!exceptionalClasses.contains(joinPoint.getTarget().getClass().getSimpleName())){
			StringBuffer logMessage = new StringBuffer("END : ");
			logMessage.append(joinPoint.getTarget().getClass().getSimpleName());
			logMessage.append(".");
			logMessage.append(joinPoint.getSignature().getName());
			log.info(logMessage.toString());
		}
		return retVal;
	}

}
