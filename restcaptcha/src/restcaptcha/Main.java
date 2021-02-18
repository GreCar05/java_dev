package restcaptcha;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;

public class Main {
	
	
			public static class Prt{
				
			        private String URL = "http://www.prt.cl/Paginas/RevisionTecnica.aspx";
			        private WebDriver driver = new FirefoxDriver();
			        private String Patente = "XAXWQED3";
					private WebClient client;
					
					
					
					public void download(String url) throws IOException {
				        String script = "var url = arguments[0];" +
				                "var callback = arguments[arguments.length - 1];" +
				                "var xhr = new XMLHttpRequest();" +
				                "xhr.open('GET', url, true);" +
				                "xhr.responseType = \"arraybuffer\";" + //force the HTTP response, response-type header to be array buffer
				                "xhr.onload = function() {" +
				                "  var arrayBuffer = xhr.response;" +
				                "  var byteArray = new Uint8Array(arrayBuffer);" +
				                "  callback(byteArray);" +
				                "};" +
				                "xhr.send();";
				        Object response = ((JavascriptExecutor) driver).executeAsyncScript(script, url);
				       //Selenium returns an Array of Long, we need byte[]
				        ArrayList byteList = (ArrayList) response;
				        byte[] bytes = new byte[byteList.size()];
				        for(int i = 0; i < byteList.size(); i++) {
				            bytes[i] = (byte)(long)byteList.get(i);
				        }
				        InputStream is= new ByteArrayInputStream(bytes);
				        
				        
				        OutputStream outputStream = null;
				        try
				        {
				            File file = new File("tmp/output.mp3");
				            outputStream = new FileOutputStream(file);
				            
				            int read = 0;
				            byte[] bytes1 = new byte[1024];
				            while ((read = is.read(bytes1)) != -1) {
				                outputStream.write(bytes1, 0, read);
				            }
				        }
				        finally
				        {
				            if(outputStream != null)
				            {
				                outputStream.close();
				            }
				        }
				        
				        
				        
					}
			        
					
			        
			        public void ExtractAudio() throws IOException, InterruptedException{
			        	
			  
			    		driver.get(URL);
			    		
			    		Thread.sleep(1000);
			    		
			    		
			    		
			    		
			    		
			    		//WebElement element = driver.findElement(By.xpath("/html/body/form/div[8]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/div/iframe"));
			    		//System.out.println(element.);
			    		//new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("/html/body/form/div[8]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/div/iframe")));
			            // new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("html body div#rc-anchor-container.rc-anchor.rc-anchor-normal.rc-anchor-light div.rc-anchor-content div.rc-inline-block div.rc-anchor-center-container div.rc-anchor-center-item.rc-anchor-checkbox-holder span#recaptcha-anchor.recaptcha-checkbox.goog-inline-block.recaptcha-checkbox-unchecked.rc-anchor-checkbox div.recaptcha-checkbox-checkmark"))).click();
			    		
			    		
			    		//driver.findElement(By.name("a-smvx3m85uu6d")).toString());
			    		//driver.findElement(By.xpath("/html/body/form/div[8]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/div/iframe")).click();
			    		
			    		
			    		
			    		//driver = driver.switchTo().frame(driver.findElement((By.tagName("iframe"))));
			    		List<WebElement> elementName = driver.findElements((By.tagName("iframe")));
			    		
			    		
			    		elementName.get(0).click();
			    		//Thread.sleep(1000);
			    		
			    		//elementName.get(1).sendKeys(Keys.TAB);
			    		
			    		System.out.println(elementName.get(1).getAttribute("name"));
			    		
			    		//String ejs = ""
			    		
			    		driver.switchTo().frame(driver.findElement(By.name(elementName.get(1).getAttribute("name"))));
			    		//System.out.println("Number of elements:" +elementName.size());
			    		
			    		//driver.findElement(By.cssSelector("#recaptcha-anchor")).click();
			    		//driver.switchTo().defaultContent();
			    		//driver.findElement(By.cssSelector(".rc-doscaptcha-body-text > a:nth-child(1)")).click();
			    		//driver = driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			    		//System.out.println(driver.getPageSource());
			    		//driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div/a")).click();;
			    		//driver.switchTo().frame(driver.findElement(By.name("c-pa8q06psdms0")));
			    		//driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/div/a")).click();;
			    		//driver.switchTo().frame(arg0);
			    		Thread.sleep(3000);
			    		JavascriptExecutor js = null;
			    		if (driver instanceof JavascriptExecutor) {
			    		    js = (JavascriptExecutor)driver;
			    		} // e
			    		
			    		js.executeScript("document.getElementById(\"recaptcha-audio-button\").click();");
			    		System.out.println("---------------------");
			    		
			    		//System.out.println(driver.findElement(By.className("rc-audiochallenge-tdownload-link")).getAttribute("href"));
			    		
			    		//	download(driver.findElement(By.className("rc-audiochallenge-tdownload-link")).getAttribute("href"));
			    		
			    		// id audio-response  	; donde se va a escribir el audio traducido
			    		
			    	//	driver.findElement(By.id("audio-response")).sendKeys(IBMWatson.sendGet());
			    		
			    		
 			    		
			    		System.out.println("---------------------");
			    		
			    		
			    		
			    		/*
			    		WebElement iframe = driver.findElement(By.xpath("/html/body/form/div[8]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/div/iframe"));
			    		
			    		
			    		Actions action = new Actions(driver);
			    		//action.moveByOffset(600, 600);
			    		action = action.click();
			    		
			    		//action.build();
			    		
			    		action.perform();
			    		
			    		*/
			    		
			    		//WebElement iframe2 = driver.findElement(By.xpath("/html/body/div/div[4]/iframe"));
			    		//action.click(iframe2);
			    		
			    		//Select dropdown = new Select(driver.findElement(By.xpath("/html/body/form/div[8]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/div/iframe")));
			    		//dropdown.getFirstSelectedOption().click();
			    		
			    		
			    		
			    		//System.out.println(driver.getPageSource());
			    		//driver.switchTo().frame(driver.findElement(By.id("recaptcha-audio-button")));
			    		//driver.findElement(By.id("recaptcha-audio-button")).click();;
			    		//driver.switchTo().frame(driver.findElement(By.xpath("/html/body/form/div[8]/div[2]/div[1]/div[1]/div[2]/div/div/div/div/div/iframe")));
			    		//driver.switchTo().defaultContent();
			    		
			    		//driver.switchTo().frame(driver.findElement(By.name("c-mhoc8mb9nai3")));
			    		//WebDriverWait wait_iframe = new WebDriverWait(driver, 20000);
			    		
			    		//wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("recaptcha-audio-button"))).click();
			    		//System.out.println(driver.getPageSource());
			    		
			    		//  /html/body/div/div[4]/iframe
			    		//WebDriverWait wait_iframe2 = new WebDriverWait(driver, 20000);
			    		//wait_iframe.until(ExpectedConditions.elementToBeClickable(By.id("rc-anchor-container"))).click();
			    		//wait_iframe.until(ExpectedConditions.).click();
			    		//WebDriverWait wait_iframe2 = new WebDriverWait(driver, 20000);
			    		//wait_iframe2.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("/html/body/div/div[4]/iframe")))).click();
			    		//System.out.println(driver.getPageSource());
			    		//System.out.println("<---------------->");
			    		//System.out.println(driver.switchTo().frame(driver.findElement(By.name("a-spqzq3h8hm03"))));
			    		
			    		
			    		
			    		//System.out.println(driver.findElement(By.xpath("//*[@id=\"ReCaptchContainer\"]/div/div")));
			    		
			    		//WebElement element = driver.findElement(By.id("recaptcha-anchor"));
			    		
			    		//element.click();		
			    			
			    		//element.sendKeys(Keys.TAB);
			    		//element.sendKeys(Keys.TAB);
			    		//element.sendKeys(Keys.TAB);
			    		//element.sendKeys(Keys.ENTER);
			    		//element.click();
			    		//element.click();
			            //capchat.click();
			            //System.out.println(capchat.getText());
			        	
			        	
			        	
			        	/*
			               WebClient webClient = new WebClient();
			               HtmlPage currentPage = webClient.getPage(this.URL);
			               // currentPage.getElementById("recaptcha-anchor").asXml();
			               //System.out.println(page);
			               
			               // HtmlCheckBoxInput checkBox = currentPage.querySelector("#recaptcha-anchor");
			               // checkBox.
			               // WebResponse response =  checkBox.click().getWebResponse();
			               //System.out.println(response.getStatusMessage());
			               System.out.println(currentPage);   
			        	*/
			        }
			        
			        
			        public void ExtractAudioII() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
			        	
			        	 client = new WebClient();
			        	 HtmlPage mainPage = client.getPage("http://www.prt.cl/Paginas/RevisionTecnica.aspx");
			        	 
			        	// final HtmlPage pakcagePage = (HtmlPage) mainPage.;
			        	 //pakcagePage.getAnchors().get(1).click();
			        	 
			        	 System.out.println(mainPage.asXml());
			        		
			        		
			        }
			        
			        public void WriteInput() throws InterruptedException {
			        	
				        	driver.get(URL);
				    		Thread.sleep(1000);
				    		WebElement element = driver.findElement(By.id("ContentPlaceHolder1_patenteInput"));
				    		element.sendKeys(this.Patente);
			        	
			        }
			        
			        public void SeleniumExtractAudio() throws InterruptedException{
			            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			            ChromeOptions chromeOptions = new ChromeOptions();
			            //chromeOptions.addArguments("--headless");
			            //chromeOptions.addArguments("--no-sandbox");
			
			            WebDriver driver = new ChromeDriver(chromeOptions);
			
			            driver.get("http://www.prt.cl/Paginas/RevisionTecnica.aspx");
			
			            Thread.sleep(1000);
			            
			           // WebElement capchat = driver.findElement(By.id("recaptcha-anchor"));
			            
			           System.out.println(driver.getPageSource());
			            
			                /*
			            if (driver.getPageSource().contains(" Lucky")) {
			                    System.out.println("Pass");
			            } else {
			                    System.out.println("Fail");
			            }
			                        */
			            driver.quit();
			        
			        }
			
			}
			
			public static class IBMWatson {
				
				private static String URL = "https://stream.watsonplatform.net/speech-to-text/api/v1/recognize?model=en-US_BroadbandModel&access_token=undefined&watson-token="; 
				private static String Token = "1u5NGK7cRFAk2YpY8Wz8lWNBOioFdoasFtyzHyFRPUTtN_V-F-ydfIoIEju4JUutKL2X5ZqqmobHRC3b5QG-FOXM7jRuw4GS22YYBLvlq7As_fNmw3JKQW_6kdYs7vRbcWXTPYXHR2fC9g6WKeft13F4BNuck7pxCd5V3grck_vVTYLNEG_bHYiFvyzqyEdIFM7P3CdrLpL1IaN2mKopTBX9b2Z7bITrJMelDlZAVXy-6EeyxFncstTLVhkl99Ck2YPr7vrlK7qUH4cJ4ApG1pz2BGGYTGgkrJXg_TQncYujB95wuYVu4K7q0WkmsSup3P9oImfy_yQ52pSs0EFSTxJ2UI-qSAba_E2ugOuDgF2EDhLMwZh8BNu__amv5J56fOGjh-e2FKShGfPRpKhMpz3pBdMtATnfymGpqQc5aBJ4pheTCApQFXCmLV9kXJ_O4hqwY5D8jg0gZmm7t1PaRpD9YrSgTIhG7MlQB3Fqdd8zqtLaUMchx7TbM6XiH3kJVGk6DuMcUwcQluXAZSMYyfBptITY2E1NCHQV4Y6HzMKZ4SXoRmDCEc4RCdKuiO5AzTAZ4SpubO4X2_wDj3SdJjCUykI2jpnXifirlllxPSQr0fMRIiD1vdPWoaqC4JSHHWaZLzlMezPnOiDa6I-BfhdyEBlpDtRaignLi-M3gigFSgBsYHUcE3DFEb2RGxjsi1JocLkDW8ApclLVueHvoXcvqrlEAb3KtqewLPr1k15V4NDtFqidFwGSyIgSTTcyuqblaxuWSwuX4y9n9-JxSTHEBaxlC83eSyYqq2DfWNSNFFYrQ3yMkFWvrMLhnufLAJe-J_3zDQ==";
				
				public static void sendGet() throws IOException{
					URL obj = new URL(URL+Token);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();

					// optional default is GET
					con.setRequestMethod("GET");

					//add request header
					con.setRequestProperty("Accept", "*/*");
					con.setRequestProperty("Accept-Encoding","gzip, deflate, br");
					con.setRequestProperty("Accept-Language","es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3");
					con.setRequestProperty("Cache-Control","no-cache");
					con.setRequestProperty("Connection","keep-alive");
					con.setRequestProperty("Host","stream.watsonplatform.net");
					con.setRequestProperty("Origin","https://speech-to-text-demo.ng.bluemix.net");
					con.setRequestProperty("Pragma","no-cache");
					con.setRequestProperty("Sec-WebSocket-Extensions","permessage-deflate");
					con.setRequestProperty("Sec-WebSocket-Key","LmR/S9znjjhoVzx+k2nwpQ==");
					con.setRequestProperty("Sec-WebSocket-Version","13");
					con.setRequestProperty("Upgrade","websocket");
					//con.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0");
					
					int responseCode = con.getResponseCode();
					System.out.println("\nSending 'GET' request to URL : " + URL);
					System.out.println("Response Code : " + responseCode);

					BufferedReader in = new BufferedReader(
					        new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					//print result
					System.out.println(response.toString());
					
					
						
				}
				
				
				
				
				
				
			}
			
			
/*
			public static class ConvertOfoct{
		        private final String  URL = "https://www.ofoct.com/audio-converter/audio-to-text.html";
		        private static final String UrlPost = "https://ct1.ofoct.com/upload.php";
		        
		        
		        public static void SendPost() throws MalformedURLException, UnsupportedEncodingException, IOException{
		            
		               HttpClient httpclient;
		               httpclient = new DefaultHttpClient();
		               HttpPost httppost = new HttpPost(UrlPost);
		
		              // httppost.addHeader("Authorization", "Bearer 70e8e17d-e1ed-4b7a-8a8a-40383d74d467");
		              // httppost.addHeader("Accept", "application/json");
		
		               httppost.addHeader("Content-type", "multipart/form-data");
		               //httppost.addHeader("Host","ct2.ofoct.com");
		               httppost.addHeader("User-Agent","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:69.0) Gecko/20100101 Firefox/69.0");
		               httppost.addHeader("Accept","/*");
		               httppost.addHeader("Accept-Language","en-US,en;q=0.5");
		               httppost.addHeader("Accept-Encoding","gzip, deflate, br");
		               httppost.addHeader("Content-Type","multipart/form-data; boundary=---------------------------8470472521798686786629251780");
		               httppost.addHeader("Content-Length","35965");
		               httppost.addHeader("Origin","https://www.ofoct.com");
		               httppost.addHeader("Connection","keep-alive");
		               httppost.addHeader("Referer","https://www.ofoct.com/audio-converter/audio-to-text.html");
		
		               File fileToUse = new File("/home/oficina/NetBeansProjects/MultipartPOST/src/tmp/audio.mp3"); //e.g. /temp/dinnerplate-special.jpg
		               FileBody data = new FileBody(fileToUse);
		
		               String file_type = "mp3" ;
		               String description = "Oppa Gangnam Style";
		               String folder_id = "-1";
		               String source = "MYCOMPUTER" ;
		
		               MultipartEntity reqEntity = new MultipartEntity();
		
		
		               //reqEntity.addPart("file_name", new StringBody( fileToUse.getName() ) );
		               //reqEntity.addPart("folder_id", new StringBody(folder_id));
		               //reqEntity.addPart("description", new StringBody(description));
		               //reqEntity.addPart("source", new StringBody(source));
		               //reqEntity.addPart("file_type", new StringBody(file_type));
		               reqEntity.addPart("Content-Disposition", new StringBody("form-data"));
		               reqEntity.addPart("name", new StringBody("myfile"));
		               reqEntity.addPart("filename ", new StringBody("audio.mp3") );
		               reqEntity.addPart("Content-Type", new StringBody( "audio/mpeg"));
		               reqEntity.addPart("myfile", data);
		
		               httppost.setEntity(reqEntity);
		
		              HttpResponse response = httpclient.execute(httppost);
		              System.out.println( response ) ;
		
		              HttpEntity resEntity = response.getEntity();
		              System.out.println( resEntity ) ;
		              System.out.println( EntityUtils.toString(resEntity));
		
		               EntityUtils.consume(resEntity);
		               httpclient.getConnectionManager().shutdown();
		
		        
		        }
        
		        public byte[] convert() throws IOException {
		            
		            String path = "/home/oficina/NetBeansProjects/restcaptcha/src/restcaptcha/tmp/audio.mp3";
		            byte[] buf = new byte[8000];
		            ByteArrayInputStream bis = new ByteArrayInputStream(buf);
		            FileOutputStream fos = new FileOutputStream(path);
		            
		            byte[] b = new byte[8000];
		
		            for (int readNum; (readNum = bis.read(b)) != -1;) {
		                fos.write(b, 0, readNum);
		            }
		            
		            return b;
		            
		        }
			}
			*/
        
        

		public static void main(String[] args) throws IOException, InterruptedException {
		        Prt prt = new Prt();
		        
		        //prt.SeleniumExtractAudio();
		        prt.ExtractAudio();
		       // prt.ExtractAudioII();
				//IBMWatson IBM = new IBMWatson();
		//		IBMWatson.sendGet();
		        
		        //prt.WriteInput();
		        
		        //ConvertOfoct conv = new ConvertOfoct();
		        // ConvertOfoct.SendPost();
		        //System.out.println(conv.convert());
		               
		    
		  
		}
	
	

}
