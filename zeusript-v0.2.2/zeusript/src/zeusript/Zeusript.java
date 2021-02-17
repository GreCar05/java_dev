/*
    Originally developed by [Gregori Carvajal]
    First version published on 24/08/2019
    
    Current version: v0.2.2
    ADD:
    *
    IMPORT:
    *lib/commons-validator-1.4.0.jar
    *lib/htmlunit-2.35.0-OSGi.jar
    *lib/log4j-1.2.16.jar
    *lib/org-apache-commons-logging.jar

*/
package zeusript;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import org.apache.log4j.*;
import org.apache.commons.validator.routines.UrlValidator;
import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.commons.logging.LogFactory;



/***
 *
 * @author oficina
 */
public class Zeusript {
    
        public static class Answer{ // Contiene Datos de Respuesta
                private String status = null;
                private Person personal = null;
                private String Juridico = null;

                /**
                 * @return the status
                 */
                public String getStatus() {
                    return status;
                }

                /**
                 * @param status the status to set
                 */
                public void setStatus(String status) {
                    this.status = status;
                }

                /**
                 * @return the personal
                 */
                public Person getPersonal() {
                    return personal;
                }

                /**
                 * @param personal the personal to set
                 */
                public void setPersonal(Person personal) {
                    this.personal = personal;
                }

                /**
                 * @return the Juridico
                 */
                public String getJuridico() {
                    return Juridico;
                }

                /**
                 * @param Juridico the Juridico to set
                 */
                public void setJuridico(String Juridico) {
                    this.Juridico = Juridico;
                }

        
        
        
        }
    
        public static class Person{
            private String FirstName = "";
            private String lastName = "";
            private String FullName = " ";

                public String getFirstName() {return FirstName;}
                public void   setFirstName(String FirstName) {this.FirstName = FirstName;}
                public String getLastName() {
                    return lastName;
                }

                public void setLastName(String lastName) {
                    this.lastName = lastName;
                }

  
                public String getFullName() {return FullName;}
                      
                public void setFullName(String FullName) {
                    this.FullName = FullName;
                    
                    String Name= "";
                    String lastName = "";
                    int i=0;
                    int j=0;

                            String[] parts = FullName.split(" ", 7);
                            String space = " "; 
                            j= 1;
                            i = parts.length-1;
                            int ap = 0;
                            while(i >= 0){
         
                                if (i== 0) space =" "; 
                                    
                                if(j==1){
                                    if( !(parts[i].equals("DEL") || parts[i].equals("LAS") || parts[i].equals("LOS") || parts[i].equals("DE") || parts[i].equals("LA"))  ){
                                           if((i+1)==parts.length){ lastName = parts[i]+space+lastName; ap = ap+1; }
                                           else
                                        
                                                if( (parts[i+1].equals("DEL") || parts[i+1].equals("LAS") || parts[i+1].equals("LOS") || parts[i+1].equals("DE") || parts[i+1].equals("LA"))){
                                                    lastName = parts[i]+space+lastName;
                                                    ap = ap+1;
                                                    if(ap ==2) j=2;
                                                }else{ 
                                                    lastName = parts[i]+space+lastName;
                                                    ap = ap+1;
                                                    if(ap ==2) j=2; 
                                                 }
                                            
                                        
                                    }else{ 
                                            lastName =parts[i]+" "+parts[i-1]+space +lastName;
                                                //j = j -1;
                                             i = i-1;    
                                        }
                                }else{     
                                        Name =parts[i]+space+Name;

                                }
                                    
                                    i = i-1;
                            
                            }
                            
                            this.FirstName = Name;
                            this.setLastName(lastName);

                            
                            
                }

        }
    
    
        public static class Zeus{
                        private String URL = "https://zeus.sii.cl/cvc/stc/stc.html";
                        private String UrlPost = "https://zeus.sii.cl/cvc_cgi/stc/getstc";
                        private String RutALL = "";
                        private String RUT= "";
                        private String DV = "";
                        private String TxtCaptcha = "";
                        private String TxtCode = "";
                        private String PRG = "STC";
                        private String OPC = "NOR";
                        private String ACEPTAR = "Consultar+situación+tributaria";
                        
                        private HtmlPage page= null;
                        private String name = "";
                        
                        
                    public String   getURL() {return URL;}
                    public void     setURL(String URL) {this.URL = URL;}

                    public String   getUrlPost() {return UrlPost;}
                    public void     setUrlPost(String UrlPost) {this.UrlPost = UrlPost;}
                        
                    public String   getRUT() { return RUT;}
                    public void     setRUT(String RUT) { this.RUT = RUT;}

                    public String   getDV()  {return DV;}
                    public void     setDV(String DV) {this.DV = DV;}
                    
                    public String   getTxtCaptcha() {return TxtCaptcha;}
                    public void     setTxtCaptcha(String TxtCaptcha) {this.TxtCaptcha = TxtCaptcha;}

                    public String   getTxtCode() {return TxtCode;}
                    public void     setTxtCode(String TxtCode) {this.TxtCode = TxtCode;}

                    public String   getPRG() {return PRG;}
                    public void     setPRG(String PRG) {this.PRG = PRG;}

                    public String   getOPC() {return OPC;}
                    public void     setOPC(String OPC) {this.OPC = OPC;}

                    public String   getACEPTAR() { return ACEPTAR;}
                    public void     setACEPTAR(String ACEPTAR) {this.ACEPTAR = ACEPTAR;}
                    
                    public HtmlPage getPage() {return page;}
                    public void     setPage(HtmlPage page) {this.page = page;}
                    
                    public String   getName() {return name;}
                    public void     setName(String name) {this.name = name;}
                    
                    public String   getRutALL() {return RutALL;}
                    public void     setRutALL(String RutALL) {
                            this.RutALL = RutALL;
                            StringTokenizer tokens=new StringTokenizer(RutALL, "-");
                            tokens.hasMoreTokens();
                            this.setRUT(tokens.nextToken());
                            this.setDV(tokens.nextToken());
   
                    }
                    
                    public String SendPost() throws MalformedURLException, IOException{
                            
                                LogFactory.getFactory().setAttribute("com.gargoylesoftware.htmlunit", "org.apache.commons.logging.impl.Log4JLogger");
                                 LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
                                final WebClient webClient = new WebClient();
                                
                                WebRequest requestSettings = new WebRequest(new URL(this.getUrlPost()), HttpMethod.POST);
                                requestSettings.setRequestParameters(new ArrayList());
                                requestSettings.getRequestParameters().add(new NameValuePair("RUT",this.getRUT()));
                                requestSettings.getRequestParameters().add(new NameValuePair("DV",this.getDV()));
                                requestSettings.getRequestParameters().add(new NameValuePair("txt_captcha",this.getTxtCaptcha()));
                                requestSettings.getRequestParameters().add(new NameValuePair("txt_code",this.getTxtCode()));
                                requestSettings.getRequestParameters().add(new NameValuePair("PRG",this.getPRG()));
                                requestSettings.getRequestParameters().add(new NameValuePair("OPC",this.getOPC()));
                                requestSettings.getRequestParameters().add(new NameValuePair("ACEPTAR",this.getACEPTAR()));


                                page = webClient.getPage(requestSettings);
                                if(!page.getByXPath("/html/body/div/div[4]").isEmpty()){
                                        if(!page.querySelector("#contenedor > div:nth-child(4)").asText().equals("**")){
                                            this.setName(page.querySelector("#contenedor > div:nth-child(4)").asText());
                                            //System.out.println(ExtracText(page.getElementByName("output").asText()));
                                            return "200";
                                        }else 
                                            return "400";
                                
                                }
  
                               return "204";
                               
                             
    
                    }
                    
                    public String ReadCaptcha() throws IOException{
                        LogFactory.getFactory().setAttribute("com.gargoylesoftware.htmlunit", "org.apache.commons.logging.impl.Log4JLogger");
                        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Log4JLogger");
                        
                        org.apache.log4j.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
                        org.apache.log4j.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF); 
                        Logger.getRootLogger().setLevel(Level.DEBUG);
                        Logger.getRootLogger().debug("Start");
                        WebClient webClient = new WebClient();
                         
                          if(this.getStatusURL(this.getURL())){
                                this.setPage(webClient.getPage(this.getURL()));
                                
                                this.setTxtCaptcha(this.getPage().getElementById("txt_captcha").getAttribute("value"));
                                return "200";
                        }
                         
                                return "404";
                              
                    }
                    
                    public boolean getStatusURL(String Url){
                        UrlValidator validar = new UrlValidator();
        
                            return validar.isValid(Url);
                    }

       
        }
        
        public static class Decode64{
                private String URL = "https://www.base64decode.org/";
                private HtmlPage page = null;

                    public String    getURL() {return URL;}
                    public void      setURL(String URL) {this.URL = URL;}
                    public HtmlPage  getPage() {return page;}
                    public void      setPage(HtmlPage page) {this.page = page;}
                
                
                
                    public String SendPost(String TxtCaptcha) throws IOException {

                                WebClient webClient = new WebClient();
                                WebRequest requestSettings;
                        
                         
                            requestSettings = new WebRequest(new URL(this.getURL()), HttpMethod.POST);
                            requestSettings.setRequestParameters(new ArrayList());
                                requestSettings.getRequestParameters().add(new NameValuePair("input", TxtCaptcha));
                                requestSettings.getRequestParameters().add(new NameValuePair("charset","UTF-8"));
                                setPage(webClient.getPage(requestSettings));

                                return ExtracText(getPage().getElementByName("output").asText());
                                
                    }
                
                
                
                    public String ExtracText(String TextCode){
                                String Text="";
                                Text = Text + TextCode.charAt(36);
                                Text = Text + TextCode.charAt(37);
                                Text = Text + TextCode.charAt(38);
                                Text = Text + TextCode.charAt(39);
                                return Text;
                    }
                    
                    public boolean getStatusURL(){
                       UrlValidator validar = new UrlValidator();
        
                            return validar.isValid(this.getURL());
                    }

        }
 

    public static Answer SearchInfo(String Rut) throws IOException {
         
                
                Answer answer = new Answer();
                Person person = new Person();
                Zeus dataZeus = new Zeus();
                dataZeus.setRutALL(Rut);
                Decode64 dataDecode = new Decode64();
                if(dataZeus.ReadCaptcha().equals("200")){
                            dataZeus.setTxtCode(dataDecode.SendPost(dataZeus.getTxtCaptcha()));
                        
                            String ans = dataZeus.SendPost();
                            if(ans.equals("400") || ans.equals("204") ){
                                answer.setStatus(ans);
                                return answer;
                            }else{
                                    answer.setStatus("200");
                                   person.setFullName(dataZeus.getName());
                                   answer.setPersonal(person);
                            }
                }else{
                        answer.setStatus("404");
                        return answer;
                    }
         

            return answer;

    }

    public static void main(String[] args) throws IOException {
        //-------- TEST  -------------------
        // 76987464-K Nombre Juridico
        // 6554455-5
        // 13793194-K  
        // 10190308-7 NOMBRE lARGO
        // 5925898-2 name:1 latsname: 2 
        // 15176920-9
        // 10190308-7  ROSA DE LAS MERCEDES CHACON CARRASCO
        // 13063280-7 Campo Vacio
        
         //Replace with your name
        Answer ans = SearchInfo("10190308-7");
        
        if(ans.getStatus().equals("200")){
            System.out.println("Nombres: "+ans.getPersonal().getFirstName());
            System.out.println("Apellidos: "+ans.getPersonal().getLastName());
        }else System.out.println(ans.getStatus());
        
        
        //System.out.println( SearchInfo("5925898-2") );
        
        //  String SearchInfo   ----------------
        //  200: Exito.
        //  400: Campo Vacio.
        //  404: Url not Found.
        //  204: Rut no existe.
        //  DE LO CONTRARIO  SE RETORNA "NOMBRE Y apellido ": 

    }
}