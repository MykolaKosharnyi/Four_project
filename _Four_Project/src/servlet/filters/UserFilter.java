package servlet.filters;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter extends Filter {

	@Override
	public int decide(LoggingEvent event) {
//		int result = Filter.NEUTRAL;
//		Object object = event.getMessage();
//		if(object instanceof Object){
//			Object obj = object;
//			int id = obj.hashCode();
//			result = id <1_000 ? Filter.DENY : Filter.ACCEPT;
//		}
		return Filter.ACCEPT;
	}


   
}
