package com.humuson.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class ApiCallCC {
    private static String get(String targetUrl) throws Exception {

        URL url = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET"); // optional default is GET

        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        System.out.println("HTTP 응답 코드 : " + responseCode);
        System.out.println("HTTP body : " + response.toString());
        return response.toString();
    }



    //    출처: https://digitalbourgeois.tistory.com/58 [IT 글자국]
    /*
     * HTTP Client POST
     * */
    public static String post(String requestURL, String jsonMessage) {
        try {
            HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
            HttpPost postRequest = new HttpPost(requestURL); //POST 메소드 URL 새성
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json; charset=utf-8");
            //postRequest.addHeader("x-api-key", RestTestCommon.API_KEY); //KEY 입력
            //postRequest.addHeader("Authorization", token); // token 이용시
//            String getData = get("http://localhost:8080/api/v1/token");
//            postRequest.setHeader("X-CSRF-TOKEN", getData);



            HttpEntity entity = new StringEntity(jsonMessage, "UTF-8");
            log.info("엔터티 byte 크기 확인 : " + entity.getContentLength()); // 77


            postRequest.setEntity(entity); //json 메시지 입력
            HttpResponse response = client.execute(postRequest);

            //TODO : response 확인하기
//            log.info("response.getEntity() : " + response.getEntity());

            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                log.info("Response Data(body)"+body);
                // TODO : response handler body 가 9000이면 에러 일으키기!!!
                if (body.equals("9000")){ // produce 예외 발생시 9000
                    log.error("Response is error 예외 발생: " + body);
                }
                return "200";
            } else {
                log.error("Response is error : " + response.getStatusLine().getStatusCode());
                return response.getStatusLine().getStatusCode()+"";
            }
        } catch (Exception e){
            System.err.println(e.toString());
            return "4000"; // http 연결 에러
        }
    }
//
//    public static String put(String strUrl, String jsonMessage) throws Exception {
//        // TODO 안됨
//        String getData = get("http://localhost:8080/api/v1/token");
//
//        URL url = new URL(strUrl);
//        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//        httpCon.setDoOutput(true);
//        httpCon.setRequestMethod("PUT");
//        httpCon.setRequestProperty("X-CSRF-TOKEN", getData);
//
//        OutputStreamWriter out = new OutputStreamWriter(
//                httpCon.getOutputStream());
//        out.write("Resource content");
//        out.close();
//        httpCon.getInputStream();
//        return "9000";
//    }
//        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
//            HttpPut httpPut = new HttpPut(url);
//            httpPut.setHeader("Accept", "application/json");
//            httpPut.setHeader("Content-Type", "application/json; charset=utf-8");
//
//            String getData = get("http://localhost:8080/api/v1/token");
//
//            log.info("PUT TOKEN : " + getData );
//            httpPut.setHeader("X-CSRF-TOKEN", getData);
//
//
//            StringEntity stringEntity = new StringEntity(jsonMessage, "UTF-8");
//            httpPut.setEntity(stringEntity);
//
//            log.info("Executing request " + httpPut.getRequestLine());
//
//            String status = httpclient.execute(httpPut, new ResponseHandler<String>() {
//                @Override
//                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
//                    int status = response.getStatusLine().getStatusCode();
//                    if (status >= 200 && status < 300) {
//                        HttpEntity entity = response.getEntity();
//                        return entity != null ? EntityUtils.toString(entity) : null;
//                    } else {
//                        throw new ClientProtocolException("Unexpected response status: " + status);
//                    }
//                }
//            });
//            log.info("status : {}" + status);
//            return status;
//
//
//            //Response 출력
////            if (response.getStatusLine().getStatusCode() == 200) {
////                ResponseHandler<String> handler = new BasicResponseHandler();
////                String body = handler.handleResponse(response);
////                log.info("response handler body is " + body);
////                return "200";
////            } else {
////                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
////                return response.getStatusLine().getStatusCode()+"";
////            }
//        } catch (Exception e){
//            log.info(e.toString());
//            return "9000";
//        }
//    }

}
