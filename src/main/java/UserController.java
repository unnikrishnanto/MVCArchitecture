

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ModelComponent component;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			
			component = ModelComponent.getModelComponent();
			int status = component.storeUser(username, password, name, email, phone);
			if(status == 1) {
				resp.sendRedirect("http://localhost:9090/MVCArchitecture/regsuccess.html");
			} else {
				resp.sendRedirect("http://localhost:9090/MVCArchitecture/regfailed.html");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		if(component != null) {
			component.closeConnection();
		}
	}
   
}
