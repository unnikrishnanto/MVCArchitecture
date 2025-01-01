
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ModelComponent component;   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
    	try {
			
    		String username = req.getParameter("username");
    		String password = req.getParameter("password");
    		
    		component = ModelComponent.getModelComponent();
    		boolean status = component.loginUser(username, password);
    		if(status) {
    			resp.sendRedirect("http://localhost:9090/MVCArchitecture/dashboard.html");
    		} else {
    			resp.sendRedirect("http://localhost:9090/MVCArchitecture/loginfailed.html");
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    @Override
	public void destroy() {
		if(component != null) {
			component.closeConnection();
		}
	}
}
