package com.example;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.lookup.SourceTypeCollisionException;

public class HelloServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        
        out.write("Hello Heroku".getBytes());
        out.flush();
        out.close();
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		System.out.println("hello from post");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("destroy");
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("init");
	}
    
}
