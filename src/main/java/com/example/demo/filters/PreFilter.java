package com.example.demo.filters;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


public class PreFilter extends ZuulFilter{

    
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		System.out.println("filterType");
		return "pre";
	}
	
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		System.out.println("filterOrder");
		return 1;
	}
	
	@Override
	public Object run() throws ZuulException {
		System.out.println("run");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
 
 
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!validateToken(authorizationHeader)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("API key not authorized");
            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
		return null;
	}
    private boolean validateToken(String tokenHeader) {
        // do something to validate the token
        return true;
    }
	@Override
	public boolean shouldFilter() {
		System.out.println("shuldFilter");
		// TODO Auto-generated method stub
		return true;
	}
	public PreFilter() {
		// TODO Auto-generated constructor stub
	}
	
}
