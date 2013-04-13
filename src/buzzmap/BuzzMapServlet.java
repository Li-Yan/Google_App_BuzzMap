package buzzmap;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class BuzzMapServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		
		System.out.println("Hello World!");
		
		resp.sendRedirect("../index.jsp");
	}
}
