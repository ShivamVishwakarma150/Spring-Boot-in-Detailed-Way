package my.app.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("con")
public class DatabaseConnetion {
	
	@Value("${my.db.driver}")
	private String driver;
	
	@Value("${my.db-url}")
	private String url;
	
	@Value("${my.db_user}")
	private String userName;
	
	@Value("${my.db_pwd}")
	private String userPwd;
	@Override
	public String toString() {
		return "DatabaseConnetion [driver=" + driver + ", url=" + url + ", userName=" + userName + ", userPwd="
				+ userPwd + "]";
	}
	
	
}
