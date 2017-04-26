package netbooks.boundary;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TemplateProcessor {
	Configuration cfg = null;
    String dir = "/WEB-INF/templates";
    
    public TemplateProcessor(ServletContext sc){
		cfg = new Configuration(Configuration.VERSION_2_3_25);
		cfg.setServletContextForTemplateLoading(sc, dir);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
    }
    
    public void runTemp(String tname, SimpleHash root, HttpServletRequest request, HttpServletResponse response){
    	Template t = null;
    	try{
    		t = cfg.getTemplate(tname);
    		response.setContentType("text/html");
    		Writer out = response.getWriter();
    		t.process(root,out);
    	}
    	catch(IOException e){
    		e.printStackTrace();
    	}
    	catch(TemplateException e){
    		e.printStackTrace();
    	}
    }

}