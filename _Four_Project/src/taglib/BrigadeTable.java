package taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import model.entities.Brigade;

import org.apache.log4j.Logger;

import dao.jdbc.JDBCDaoFactory;

public class BrigadeTable extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(BrigadeTable.class);     
	
	    @Override
	    public int doStartTag() throws JspException {
	        
			
	        try {
	            //Get the writer object for output.
	            JspWriter out = pageContext.getOut();

				JDBCDaoFactory factory = new JDBCDaoFactory();
		    	
				List<Brigade> allBrigade = factory.createBrigadeDao().findAll();

				logger.info("allBrigade:" + allBrigade);
				
	            out.println("<TABLE border=1>");
	            out.println("<TR valign=\"top\">");
	            out.println("<TD>Id</TD>");
	            out.println("<TD>Id Employee</TD>");
//	            out.println("<TD> </TD>");
	            out.println("</TR>");
	            
	            for(Brigade b: allBrigade){
	            	out.println("<TR>");
		            out.println("<TD>" + b.getId() + "</TD>");
		            out.println("<TD>" + b.getIdEmployee() + "</TD>");
					
//		            out.println("<TD><a href='jsp.admin/admin_delete_brigade.jsp?id=" + b.getId() + "' /> delete</a></TD>");
		            out.println("</TR>");
	            } 
	            out.println("</TABLE>");

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return SKIP_BODY;
	    }
}