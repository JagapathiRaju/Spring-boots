package com.sanvi.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sanvi.ApplicationSpringBootRestWithOutJersey;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jagapathiraju on 19/07/17.
 */
@ContextConfiguration(
        classes = ApplicationSpringBootRestWithOutJersey.class,
        loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
@IntegrationTest
public class BaseSpringRestTest {
    protected static ResponseResults latestResponse = null;


    protected RestTemplate restTemplate  = new RestTemplate();

    protected void executeGet(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate.execute(url, HttpMethod.GET, requestCallback, new ResponseExtractor<ResponseResults>() {
            @Override
            public ResponseResults extractData(ClientHttpResponse response) throws IOException {
                if (errorHandler.hadError) {
                    return (errorHandler.getResults());
                } else {
                    return (new ResponseResults(response));
                }
            }
        });

    }

    protected void executePost(String url) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate.execute(url, HttpMethod.POST, requestCallback, new ResponseExtractor<ResponseResults>() {
            @Override
            public ResponseResults extractData(ClientHttpResponse response) throws IOException {
                if (errorHandler.hadError) {
                    return (errorHandler.getResults());
                } else {
                    return (new ResponseResults(response));
                }
            }
        });

    }

    protected String getJSONData(String fileLocation){
        StringBuffer fileContents = new StringBuffer();

        try {

            File file =  ResourceUtils.getFile(this.getClass().getResource(fileLocation));

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                fileContents.append(line);
                line = br.readLine();
            }

            br.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return fileContents.toString();
    }

    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResults results = null;
        private Boolean hadError = false;

        private ResponseResults getResults() {
            return results;
        }

        @Override
        public boolean hasError(ClientHttpResponse response) throws IOException {
            hadError = response.getRawStatusCode() >= 400;
            return hadError;
        }

        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            results = new ResponseResults(response);
        }
    }
}