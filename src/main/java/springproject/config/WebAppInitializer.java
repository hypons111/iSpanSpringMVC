package springproject.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 等如 web.xml 的 <servlet>
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 等如 beans.config.xml
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppConfig.class};
//		return null;
	}

	// 等如 mvc-servlet.xml 的 <context:component-scan>
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebAppConfig.class};
//		return null;
	}

	// 等如web.xml 的 <servlet-mapping>
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	// 等如web.xml 的 <filter>
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter();
		cef.setEncoding("UTF-8");
		cef.setForceEncoding(true);	
		// 簡寫
		// CharacterEncodingFilter cef = new CharacterEncodingFilter("UTF-8", true);
		
		return new Filter[] {cef};
	}

}
