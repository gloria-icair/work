package org.Gloria.HTTPRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ONOSTopoInfo {

	private final String USER_AGENT = "Mozilla/5.0";
	
	public static void main(String[] args) throws Exception {

		ONOSTopoInfo http = new ONOSTopoInfo();
		
		System.out.println("Testing 1 - Send Http GET request");
		http.sendGetTopo();
		http.sendGetDevices();
		http.sendGetLinks();
		http.sendGetHosts();
	}
	
	private void sendGet(String url) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		String res = response.toString();
		
		//Write to file
		writeToFile(res);

		//print result
		System.out.println(res);
		
	}

	// HTTP GET Topology request
	private void sendGetTopo() throws Exception {

		String topoURL = "http://127.0.0.1:8181/onos/v1/topology";
		sendGet(topoURL);
		
	}
	
	//HTTP GET Devices request
	private void sendGetDevices() throws Exception {
		
		String devicesURL = "http://127.0.0.1:8181/onos/v1/devices";
		sendGet(devicesURL);
		
	}
	
	//HTTP GET Links request
	private void sendGetLinks() throws Exception {
			
		String linksURL = "http://127.0.0.1:8181/onos/v1/links";
		sendGet(linksURL);
		
	}
	
	//HTTP GET Hosts request
	private void sendGetHosts() throws Exception {
			
		String hostsURL = "http://127.0.0.1:8181/onos/v1/hosts";
		sendGet(hostsURL);
		
	}
	
	private void writeToFile(String content){
		
		try {

			File file = new File("/Users/Gloria//Desktop/AllTopo.txt");

			// if file doesn't exist, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Write to file: Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
