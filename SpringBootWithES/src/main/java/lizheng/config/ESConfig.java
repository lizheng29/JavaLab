package lizheng.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.GsonBuilder;

@Configuration
public class ESConfig {

	@Bean
	public JestClient getJestClient() {

		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder("http://54.222.137.226:9200")
				.gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'hh:mm:ss").create()).connTimeout(1500)
				.readTimeout(3000).multiThreaded(true).build());
		return factory.getObject();
	}
	
}
