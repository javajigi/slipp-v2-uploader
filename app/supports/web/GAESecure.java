package supports.web;

import play.mvc.Before;
import play.mvc.Controller;

public class GAESecure extends Controller {
	@Before
    static void checkAccess() throws Throwable {
		Check check = getActionAnnotation(Check.class);
		if (check != null) {
			check(check);
			return;
		}
		check = getControllerAnnotation(Check.class);
		if (check != null) {
			check(check);
			return;
		}
	}

	private static void check(Check check) throws HasNotRoleException {
		Role role = check.value();
		if (role == Role.ROLE_USER || role == Role.ROLE_ADMIN_USER) {
			if (!Auth.isLoggedIn()) {
				Auth.login(request.action);
			}	
		}
		
		if (role == Role.ROLE_ADMIN_USER) {
			if (!Auth.isUserAdmin()){
				Auth.login(request.action);
			}
		}
	}
}
