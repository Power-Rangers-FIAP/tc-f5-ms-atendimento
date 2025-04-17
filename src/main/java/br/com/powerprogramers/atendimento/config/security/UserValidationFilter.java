package br.com.powerprogramers.atendimento.config.security;

import br.com.powerprogramers.atendimento.domain.dto.UserInfoDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class UserValidationFilter extends OncePerRequestFilter {

    private static final String REQUEST_ATTRIBUTE_USER_ID = "userId";
    private static final String REQUEST_ATTRIBUTE_USER_TYPE = "userType";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final Map<String, String[]> injectedParams = new HashMap<>();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {

            UserInfo user = new UserInfo();
            UserInfoDTO userInfo = user.getUserInfoDtoByJwt(jwt);

            final String userType = userInfo.getUserType(); // DOCTOR ou PATIENT
            final String loggedUserId = userInfo.getUserId();
            final String userIdFromRequest = getUserIdFromRequest(request);

            if (ObjectUtils.isEmpty(userIdFromRequest)) {
                request.setAttribute(REQUEST_ATTRIBUTE_USER_ID, loggedUserId);
                injectedParams.put(REQUEST_ATTRIBUTE_USER_ID, new String[] {loggedUserId});

                request.setAttribute(REQUEST_ATTRIBUTE_USER_TYPE, userType);
                injectedParams.put(REQUEST_ATTRIBUTE_USER_TYPE, new String[] {userType});

            } else if (!ObjectUtils.nullSafeEquals(userIdFromRequest, loggedUserId)) {
                throw new AccessDeniedException(
                        "ACCESS DENIED. User does not have permission to access this resource");
            }
        }

        filterChain.doFilter(RequestParameterWrapper.wrapper(request, injectedParams), response);
    }

    private String getUserIdFromRequest(HttpServletRequest request) {
        return request.getParameter("userId");
    }
}
