package my.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("eobj")
public class EmailConfig {
	
	@Value("gmail")
	private String host;
	
	@Value("852")
	private Integer port;
	@Override
	public String toString() {
		return "EmailConfig [host=" + host + ", port=" + port + "]";
	}
	
	
}
