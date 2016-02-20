/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;
 
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payload;
import org.openstack4j.openstack.OSFactory;
 
public class ObjectStorageConnector {
	
	private String auth_url;
    private String project;
    private String projectId;
    private String region;
    private String userId;
    private String username;
    private String password;
    private String domainId;
    private String domainName;
    private Identifier domainIdent;
    private Identifier projectIdent;
    private OSClient os;
	private SwiftAccount account;
	
	public void ObjectStorageConnector() {
		getConnection();
	}
	
    public void getConnection() {

		try {
            String envApp = System.getenv("VCAP_APPLICATION");
            String envServices = System.getenv("VCAP_SERVICES");
            
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(envServices);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vcapArray = (JSONArray) jsonObject.get("Object-Storage");
            JSONObject vcap = (JSONObject) vcapArray.get(0);
            JSONObject credentials = (JSONObject) vcap.get("credentials");
            auth_url = credentials.get("auth_url").toString() + "/v3";
            project = credentials.get("project").toString();
            projectId = credentials.get("projectId").toString();
            region = credentials.get("region").toString();
            userId = credentials.get("userId").toString();
            username = credentials.get("username").toString();
            password = credentials.get("password").toString();
            domainId = credentials.get("domainId").toString();
            domainName = credentials.get("domainName").toString();
            
            Identifier domainIdent = Identifier.byName(domainName);
            Identifier projectIdent = Identifier.byName(project);
            
            os = OSFactory.builderV3()
                .endpoint(auth_url)
                .credentials(userId, password)
                .scopeToProject(projectIdent, domainIdent)
                .authenticate();
            
            account = os.objectStorage().account().get();
            
        } catch (ParseException ex) {
        }
	}
	
	public boolean createContainer(String cName) {
        os.objectStorage().containers().create(cName);
        return true;
    }
    
    public boolean deleteContainer(String cName) {
        os.objectStorage().containers().delete(cName);
        return true;
    }
    
    public void uploadFile(String cName, String fName, Payload payload) {
        String etag = os.objectStorage().objects().put(cName, fName, payload);
    }
    
    public void downloadFile(String cName, String fName) {
        os.objectStorage().objects().download(cName, fName);
    }
	
	public SwiftAccount getAccount() {
        account = os.objectStorage().account().get();
        return account;
    }
	
	public List listAllObjects(String containerName) {
        return os.objectStorage().objects().list(containerName);
    }
}