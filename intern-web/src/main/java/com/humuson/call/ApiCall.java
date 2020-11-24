package com.humuson.call;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class ApiCall {
    public static void get(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
//            con.addRequestProperty("x-api-key", RestTestCommon.API_KEY); //key값 설정
            con.setRequestMethod("GET");

            //URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정한다. URL 연결은 입출력에 사용될 수 있다. URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고, 그렇지 않은 경우는 false로 설정해야 한다. 기본값은 false이다.

            con.setDoOutput(false);

            StringBuilder sb = new StringBuilder();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //Stream을 처리해줘야 하는 귀찮음이 있음.
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(con.getResponseMessage());
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
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
                return body;
            } else {
                log.error("Response is error : " + response.getStatusLine().getStatusCode());
                return response.getStatusLine().getStatusCode()+"";
            }
        } catch (Exception e){
            System.err.println(e.toString());
            return "4000"; // http 연결 에러
        }
    }
}
