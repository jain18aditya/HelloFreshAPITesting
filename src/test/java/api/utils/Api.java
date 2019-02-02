package api.utils;

import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import org.apache.log4j.Logger;

public abstract class Api {
	Logger log = Logger.getLogger(Api.class);
    public enum Transport {
        HTTP,
        HTTPS;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private final RestTemplate template = new RestTemplate();
    private final String baseUrl;
    private final String hostName;
    private final int portNumber;
    
    
    public Api(Transport transport, String hostname, int port) {
        if (StringUtils.isEmpty(transport)) {
            throw new IllegalArgumentException("'transport' must not be null");
        }
        if (StringUtils.isEmpty(hostname)) {
            throw new IllegalArgumentException("'hostname' must not be null");
        }
        this.baseUrl = transport.toString() + "://" + hostname;
        this.hostName = hostname;
        this.portNumber = port;
    }

    public RestTemplate getTemplate() {
        return template;
    }

    protected <T> T get(String url, Class<T> responseType, int expectedStatusCode) {
        log.info(url);
        HttpEntity<String> entity = new HttpEntity<>("", requestHeaders());
        ResponseEntity<T> T  = template.exchange(url, HttpMethod.GET, entity, responseType);
        Assert.assertEquals("Status code is not match for API ",expectedStatusCode, T.getStatusCodeValue());
        return T.getBody();
    }
    
    
    protected <T> T post(String url, Object request, Class<T> responseType, int expectedStatusCode) {
        log.info(url);

        HttpEntity<Object> entity = new HttpEntity<>(request, requestHeaders());
        ResponseEntity<T> T  = template.exchange(url, HttpMethod.POST, entity, responseType);
        Assert.assertEquals("Status code is not match for API ",expectedStatusCode, T.getStatusCodeValue());
        return T.getBody();
    }
    
    protected <T> T put(String url, Object request, Class<T> responseType) {
        log.info(url);

        HttpEntity<Object> entity = new HttpEntity<>(request, requestHeaders());
        return template.exchange(url, HttpMethod.PUT, entity, responseType).getBody();
    }

    protected void delete(String url) {
        log.info(url);

        HttpEntity<String> entity = new HttpEntity<>("", requestHeaders());
        template.exchange(url, HttpMethod.DELETE, entity, String.class).getBody();
    }

    protected HttpHeaders requestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return headers;
    }

    protected String addParam(String url, String name, String value) {
        if (url.contains("?")) {
            return url + "&" + name + "=" + value;
        } else {
            return url + "?" + name + "=" + value;
        }
    }
    
    protected String getBaseUrl() {
        return baseUrl;
    }

    protected String getHostName() {
        return hostName;
    }

    protected int getPortNumber() {
        return portNumber;
    }
}
