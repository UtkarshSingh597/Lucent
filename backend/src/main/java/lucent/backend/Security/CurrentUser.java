package lucent.backend.Security;

import lucent.backend.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
    public AppUserPrincipal require(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null || !(auth.getPrincipal() instanceof AppUserPrincipal principal)){
            throw new UnauthorizedException("Authentication failed");
        }
        return principal;
    }
}
