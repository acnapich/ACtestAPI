/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ac.recordweather;

import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONObject;

/**
 *
 * @author acnapich
 */
public class recordweather {
    
    public static void main(String[] args) {
        
        String myApiKey = args[0];
        String apiToCall = "https://api.openweathermap.org/data/2.5/onecall?lat=45.25&lon=9.18&appid=" + myApiKey;
        Header headerKey = new Header("X-Kount-Api-Key", "<!--Actual Kount RIS/API Key goes here-->");
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(apiToCall);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        method.addRequestHeader(headerKey);
        
        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);
 
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }
 
            // Read the response body.
            byte[] responseBody = method.getResponseBody();
 
            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            
            JSONObject obj = new JSONObject(responseBody);
            
            String strobj = new String(responseBody);

            int i = strobj.indexOf("description");
            int j = strobj.indexOf("icon");
            
            
            String tempo = strobj.substring(i+14,j-3);
            
            //System.out.println(strobj);
            System.out.println("Il tempo di oggi è: " + tempo);
            
 
        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
    }
        
        
    }
    

