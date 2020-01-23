package com.example.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * In computing, aspect-oriented programming (AOP) is a programming paradigm
 * that aims to increase modularity by allowing the separation of cross-cutting
 * concerns. It does so by adding additional behavior to existing code (an
 * advice) without modifying the code itself, instead separately specifying
 * which code is modified via a "pointcut" specification, such as "log all
 * function calls when the function's name begins with 'set'".
 * <p>
 * Reference: https://en.wikipedia.org/wiki/Aspect-oriented_programming
 * <p>
 * AOP (Aspect Oriented Programming):- It is used for “Cross-cutting-concern ” .
 * It means separate business logic and external services.
 * <p>
 * External service must behave as plug-in-code, that is without modifying
 * exited application, programmer, should able to add/remove external services
 * Example are :- Log4j,UnitTest,security,JMS,Cryptography,Encode and decode
 * request/response ,filter management, request identity process, etc………………………..
 * <p>
 * AOP Terms
 * <p>
 * 1. Aspect:- It is a class, which indicates external services logic. ,
 * <P>
 * 2. Advice:- It is a method inside Aspect (class). It is also called as
 * implementation of Aspect.
 * <p>
 * 3. Pointcut: It is an expression which select the business class method to
 * connect with advice. But it will not tell which advice it is
 * <p>
 * 4. Joinpoint:- It is a combination of Advice and Pointcut expression . It
 * means “joinpoint says which business class method need what and how many
 * advice.
 * <p>
 * 5. Target :- It is a pure business class object (before adding/without
 * external services logic).
 * <p>
 * 6. Weaving :- It is process done by weaver (sub component of spring container
 * ).It will add advice logic to target based on join points.
 * <p>
 * 7. Proxy:- It is a final output of weaving which contains business class
 * logic and selected advices logic. ie Code linked at object level, not at
 * class level.
 * 
 * <p>
 * Types of Advices in AOP
 * <p>
 * Every method defined in Aspect class must have signed with one of below
 * advice are;-----
 * <p>
 * 1> Before Advice:- Execute advice before business method. Execution order:
 * Advice- method (); b. method (); // Business Method.
 * <p>
 * 2> After Advice:- Execute advice before business method. Execution order: b.
 * method (); Advice- method ();
 * <p>
 * 3> Around advice:- Advice first part is called before business method and
 * second part of advice is called after business method line “Proceed” calls
 * business method from advice. Execution order: Advice- method (); --1st part
 * b. method (); Advice- method (); 2nd part
 * <p>
 * 4> After Returning:- This is after advice type but it is only called on
 * successful execution of b.method () only. Execution order:- b. method (); (if
 * execution succesfully) Advice- method ();
 * <p>
 * 5> AfterThrowing Advices:This is after advice type but it is only called on
 * fail/exception execution of b.method () only. Execution order:- b. method ();
 * (if throw execution) Advice- method ();
 * 
 * @author SAMBA CHNNEAMSETTY
 *
 */

@Aspect
@Component
public class LogAspect {

	Logger log = LogManager.getLogger(LogAspect.class);

	@Pointcut(value = "execution(* com.example.controller.*.*(..) )")
	public void pointCut() {

	}

	/**
	 * want to use other advice other than around(Before , After, AfterReturning,
	 * AfterThrowing) we should use JoinPoint in adviceMethod as a parameter.
	 * <p>
	 * public void adviceMethod(JoinPoint jp)
	 */
	@Around("pointCut()")
	public Object adviceMethod(ProceedingJoinPoint pj) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		Object[] inputArray = pj.getArgs();
		log.info(pj.getSignature().getName() + "() method started");
		log.info("Input parameters: " + mapper.writeValueAsString(inputArray));
		log.info(pj.getTarget().getClass().toString());
		Object obj = pj.proceed();
		log.info("response: " + mapper.writeValueAsString(obj));

		return obj;

	}

}
