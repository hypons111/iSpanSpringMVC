package springproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

// mvc-servlet : <context:component-scan base-package="springMVC"/>
@Configuration
// mvc-servlet : <mvc:annotation-driven />
@EnableWebMvc
// mvc-servlet : component 搜尋範圍
@ComponentScan(basePackages = "springproject")
public class WebAppConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setDefaultEncoding("UTF-8");
		return cmr;
	}

	// 取得 WEB-INF 中的 jsp
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/admin/");
		irvr.setSuffix(".jsp");
		irvr.setOrder(6);
		return irvr;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 把 ResourceLocations 縮短為 ResourceHandler
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/product/js/");
		registry.addResourceHandler("/admin/product/image/**").addResourceLocations("/WEB-INF/resources/product/image/");
																
	}
}
