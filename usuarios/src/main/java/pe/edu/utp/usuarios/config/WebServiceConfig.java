package pe.edu.utp.usuarios.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}
	
	@Bean
	public XsdSchema usuariosSchema() {
		return new SimpleXsdSchema(new ClassPathResource("usuario-detalle.xsd"));
	}
	
	//ws/usuarios.wsdl
	@Bean(name = "usuarios")
	public DefaultWsdl11Definition defaultWsdl11Definition2(XsdSchema usuariosSchema) {
			DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
			wsdl11Definition.setPortTypeName("UsuariosPort");
			wsdl11Definition.setLocationUri("/ws");
			wsdl11Definition.setTargetNamespace("http://utp.edu.pe/usuarios");
			wsdl11Definition.setSchema(usuariosSchema);
			return wsdl11Definition;
	}
	
	
	
}