package com.finp.moic.util.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NaverOcrService {

    @Value("${OCR_APIURL}")
    private String apiURL;

    @Value("${OCR_SECRET}")
    private String secretKey;

    public List<String> naverOcrApi(String filePath, String ext) {
        String apiURL = this.apiURL;
        String secretKey = this.secretKey;
        List<String> parseData = null;
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", ext);
            image.put("url", filePath); // image should be public, otherwise, should use data
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.add(image);
            json.put("images", images);
            String postParams = json.toString();

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            parseData = jsonParse(response);

        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }

        return parseData;
    }

    public List<String> jsonParse(StringBuffer response) throws ParseException {

            List<String> inferTextList = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.toString());

                JsonNode fields = jsonNode.get("images").get(0).get("fields");

                for (JsonNode field : fields) {
                    String inferText = field.get("inferText").asText();
                    inferTextList.add(inferText);
                }
            } catch (IOException e) {
                throw new BusinessException(ExceptionEnum.INTERNAL_SERVER_ERROR);
            }

            return inferTextList;
    }

}