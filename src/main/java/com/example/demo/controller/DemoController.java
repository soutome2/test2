package com.example.demo.controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@RestController
	public class DemoController {
	
@CrossOrigin
	@GetMapping("/test2")
	public Test check() {
	Test test = new Test();
	test.setName("山田さん");
	test.setScore(100);
	return test;
	}
	
@CrossOrigin
@GetMapping("/test")
	public String checkSentiment() {
	
	// Create ObjectMapper instance
	ObjectMapper mapper = new ObjectMapper();
	
	// Create the root object node
	ObjectNode rootNode = mapper.createObjectNode();
	rootNode.put("kind", "SentimentAnalysis");
	
	// Create the parameters node
	ObjectNode parametersNode = rootNode.putObject("parameters");
	parametersNode.put("modelVersion", "latest");
	parametersNode.put("opinionMining", "False");
	
	// Create the analysisInput node
	ObjectNode analysisInputNode = rootNode.putObject("analysisInput");
	
	// Create the documents array node
	ObjectNode documentNode = mapper.createObjectNode();
	documentNode.put("id", "1");
	documentNode.put("language", "ja");
	documentNode.put("text", "やっぱり楽しい Java");
	
	// Add the document node to the documents array
	analysisInputNode.putArray("documents").add(documentNode);
	
	
 String jsonString =null;
 try {
	 
 // Convert the rootNode to JSON string
	 jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
	 System.out.println(jsonString);
 } catch (JsonProcessingException e) {
	 e.printStackTrace();
 }
 
// リクエストヘッダを作成する
 HttpHeaders headers = new HttpHeaders();
 headers.set("Ocp-Apim-Subscription-Key", "ab5bb4c77a4c49a9aba068acf76e3d06");
 headers.setContentType(MediaType.APPLICATION_JSON);
 
 // JSON データをリクエストボディに設定する
 HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);
 
 // Web API にリクエストを送信し、レスポンスを受け取る
 ResponseEntity<String> responseEntity = new RestTemplate().exchange(
 "https://cong-serv-aeon2024.cognitiveservices.azure.com/language/:analyze-text?api-version=2023-04-01",
 HttpMethod.POST, requestEntity, String.class);
 
 // レスポンスデータを取得する
 String result = "";
 try {
 ObjectMapper objectMapper = new ObjectMapper();
 JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());
 
 // "documents"の配列から最初の要素を取得
 JsonNode documentsNode = jsonNode.get("results").get("documents").get(0);
 
 // 最初の要素の「sentiment」値を取得
 result = documentsNode.get("sentiment").asText();
 
 } catch (Exception e) {
 e.printStackTrace();
 }
return result;
 }
}

