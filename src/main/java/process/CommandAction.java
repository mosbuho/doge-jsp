package process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
