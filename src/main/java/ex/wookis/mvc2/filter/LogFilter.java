package ex.wookis.mvc2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    private static final String[] WHITE_LIST =
            {"/css/*", "/*.ico", "/error"};


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        if (isWhiteList(requestURI)) {
            log.info("Request [{} , {}]",uuid, requestURI);
        }

        chain.doFilter(request,response);
    }

    private boolean isWhiteList(String requestURI) {
        return !PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
