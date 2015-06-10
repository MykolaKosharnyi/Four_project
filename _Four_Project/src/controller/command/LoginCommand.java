package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import controller.ConfigurationManager;
import controller.LoginLogic;
import controller.MessageManager;
import dao.jdbc.JDBCDaoFactory;

public class LoginCommand implements Command {
	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	static Logger logger = Logger.getLogger(LoginCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		// извлечение из запроса логина и пароля
		String login = request.getParameter(PARAM_NAME_LOGIN);
		logger.info("login:" + login);

		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		logger.info("password:" + pass);

		// проверка логина и пароля
		if (LoginLogic.checkLogin(login, pass)) {

			// если зашел admin
			logger.info("user is ckecking");
			if ("admin".equals(login)) {
//
//				response.setContentType("text/html");
//				response.setCharacterEncoding("UTF-8");
//				logger.info("user is admin");
//
//				HttpSession session = request.getSession();
//
//				JDBCDaoFactory factory = new JDBCDaoFactory();
//				
//				List<Tenant> allTenants = factory.createTenantDao().findAll();
//				for (Tenant t : allTenants) {
//					"admin".equals(t.getLogin());
//					allTenants.remove(t);
//					break;
//				}
//				session.setAttribute("tenants",  allTenants);
//				logger.info("tenants:" + allTenants);
//				
//				List<Petition> petitions = factory.createPetitionDao().findAll();
//				session.setAttribute("petitions", petitions);
//				logger.debug("petitions:" + petitions);	
//				
//				List<Work> works = factory.createWorkDao().findAll();
//				session.setAttribute("works", works);
//				logger.debug("works:" + works);
//
//				List<Employee> employees = factory.createEmployeeDao().findAll();
//				session.setAttribute("employees", employees);
//				logger.debug("employees:" + employees);
//				
//				List<Brigade> brigades = factory.createBrigadeDao().findAll();
//				session.setAttribute("brigades", brigades);
//				logger.debug("brigades:" + brigades);
//
//				// setting session to expiry in 30 mins
//				session.setMaxInactiveInterval(30 * 60);
//
//				// определение пути к user_form.jsp
				page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_PAGE_PATH);
			} else {
				// все остальные пользователи
				JDBCDaoFactory factory = new JDBCDaoFactory();
				String name = factory.createTenantDao().getNameWithLoginAndPassword(login, pass);
				int idUser = factory.createTenantDao().getId(login, pass);

				HttpSession session = request.getSession();
				logger.info("user's name is" + name);
				session.setAttribute("userName", name);
				
				logger.info("user's id is" + idUser);
				session.setAttribute("userId", idUser);
				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);

				Cookie userName = new Cookie("user", login);
				userName.setMaxAge(30 * 60);
				response.addCookie(userName);
				// определение пути к user_form.jsp
				page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.USER_FORM_PATH);
			}
		} else {
			request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
			logger.error(MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
			page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
		}
		return page;
	}
}
