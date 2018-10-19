package sqlmap;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	
	// SqlSession 객체 생성기
	private static SqlSessionFactory instance;
	
	// 생성자는 보토 public인데 여기선 private 처리 한다. 따라서 외부에서 접근이 안된다. new해서 접근이 안되기 때문에 우회접근한다.
	// public 생성자를 막아서 외부에서 접근하는것을 막는다.
	// 웹은 사용자들이 많기 때문에 접속할 때 마다 MybatisManager m = new MybatisManager();을 실행하면 메모리가 늘어나기 때문에 다수의 인스턴스 생성을
	// 막고 하나의 인스턴스만 생성시켜 처리한다. 즉, 싱글톤패턴기업
	private MybatisManager() {
		
	}

	public static SqlSessionFactory getInstance() {
		Reader reader = null; // Reader는 InputStreamReader의 상위객체
		try {
			// InputStream, OutputStream => 1byte 단위
			// InputStreamReader, OutputStreamWriter = > 1char 단위
			reader = Resources.getResourceAsReader("sqlmap/sqlMapConfig.xml");
			instance = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
}