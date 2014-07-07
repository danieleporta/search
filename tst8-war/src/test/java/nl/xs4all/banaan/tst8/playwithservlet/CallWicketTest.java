package nl.xs4all.banaan.tst8.playwithservlet;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * test to show that it is possible to invoke wicket as a servlet in a mocked
 * environment. This suggests it is possible to avoid WicketTester for
 * scenario's where the tester deviates from actual browser behaviour
 */
public class CallWicketTest {

    @Test
    public void testInvokeServlet() throws ServletException, IOException {
        InputStream webXml = getClass().getResourceAsStream("/WEB-INF/web.xml");
        ServletOutputStream outputStream = makeServletOutputStream();
        ServletContext servletContext = EasyMock.createMock(ServletContext.class);
        FilterConfig filterConfig = EasyMock.createMock(FilterConfig.class);
        HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
        HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
        HttpSession session = EasyMock.createMock(HttpSession.class);        
        FilterChain chain = EasyMock.createMock(FilterChain.class);
        
        EasyMock.expect(filterConfig.getInitParameter("ignorePaths")).andReturn(null);
        EasyMock.expect(filterConfig.getInitParameter("filterMappingUrlPattern")).andReturn(null).atLeastOnce();
        EasyMock.expect(filterConfig.getServletContext()).andReturn(servletContext).atLeastOnce();
        EasyMock.expect(servletContext.getResourceAsStream("/WEB-INF/web.xml")).andReturn(webXml);
        EasyMock.expect(filterConfig.getInitParameter("applicationFactoryClassName")).andReturn("org.apache.wicket.guice.GuiceWebApplicationFactory");
        EasyMock.expect(filterConfig.getInitParameter("injectorContextAttribute")).andReturn(null);
        EasyMock.expect(filterConfig.getInitParameter("wicket-guice.stage")).andReturn("DEVELOPMENT");
        EasyMock.expect(filterConfig.getInitParameter("module")).andReturn("nl.xs4all.banaan.tst8.wiring.ProductionModule").atLeastOnce();
        EasyMock.expect(filterConfig.getFilterName()).andReturn("wicket.tst8-app").atLeastOnce();
        servletContext.setAttribute(EasyMock.eq("wicket:wicket.tst8-app"), EasyMock.isA(WebApplication.class));
        EasyMock.expect(servletContext.getAttribute("javax.servlet.context.tempdir")).andReturn(null);
        EasyMock.expect(filterConfig.getInitParameter("sourceFolder")).andReturn(null);
        EasyMock.expect(filterConfig.getInitParameter("wicket.configuration")).andReturn(null).atLeastOnce();
        EasyMock.expect(servletContext.getInitParameter("wicket.configuration")).andReturn(null).atLeastOnce();
        EasyMock.expect(filterConfig.getInitParameter("configuration")).andReturn(null).atLeastOnce();        
        EasyMock.expect(servletContext.getInitParameter("configuration")).andReturn(null).atLeastOnce(); 
        EasyMock.expect(filterConfig.getInitParameter("portletOnlyFilter")).andReturn("false");        
        EasyMock.expect(filterConfig.getInitParameter("detectPortletContext")).andReturn("false"); 
        EasyMock.expect(request.getRequestURI()).andReturn("/tst8-app/resources/double/").atLeastOnce();
        EasyMock.expect(request.getContextPath()).andReturn("/tst8-app").atLeastOnce();
        EasyMock.expect(filterConfig.getInitParameter("filterPath")).andReturn(null);   
        EasyMock.expect(request.getCharacterEncoding()).andReturn("UTF-8").atLeastOnce();
        EasyMock.expect(request.getHeader("Wicket-Ajax")).andReturn(null).atLeastOnce();        
        EasyMock.expect(request.getSession(false)).andReturn(null);     
        EasyMock.expect(request.getParameter("wicket:pageMapName")).andReturn(null);
        EasyMock.expect(request.getParameter("wicket:interface")).andReturn(null);
        EasyMock.expect(request.getParameter("wicket:bookmarkablePage")).andReturn(null); 
        EasyMock.expect(request.getParameter("wicket:ignoreIfNotActive")).andReturn(null);
        EasyMock.expect(request.getParameterMap()).andReturn(new HashMap<String,String>());
        EasyMock.expect(request.getQueryString()).andReturn(null);
        response.setDateHeader(EasyMock.isA(String.class), EasyMock.anyLong());
        EasyMock.expectLastCall().atLeastOnce();
        response.setHeader("Cache-Control", "max-age=3600");
        response.setContentType("text/plain; charset=UTF-8");
        response.setContentLength(4096);
        EasyMock.expect(response.getOutputStream()).andReturn(outputStream);
        
        EasyMock.replay(servletContext);
        EasyMock.replay(filterConfig);
        EasyMock.replay(request);
        EasyMock.replay(response);
        EasyMock.replay(session);
        EasyMock.replay(chain);
        
        WicketFilter filter = new WicketFilter();
        filter.init(filterConfig);
        filter.doFilter(request, response, chain);
        
        EasyMock.verify(servletContext);
        EasyMock.verify(filterConfig);
        EasyMock.verify(request);
        EasyMock.verify(response);
        EasyMock.verify(session);
        EasyMock.verify(chain);
        
    }

    private ServletOutputStream makeServletOutputStream() {
        return new ServletOutputStream() {
            
            @Override
            public void write(int b) throws IOException {
                // ignore the actual output
            }
        };
    }

}
