/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.ObjectStorageConnector;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openstack4j.api.OSClient;
import org.openstack4j.model.storage.object.SwiftAccount;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.api.exceptions.AuthenticationException;

/**
 *
 * @author Danica
 */
@WebServlet(name = "StorageServlet", urlPatterns = {"/StorageServlet"})
public class StorageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
			
			ObjectStorageConnector connector = new ObjectStorageConnector();
			/*
			try {
				
			String envApp = System.getenv("VCAP_APPLICATION");
            String envServices = System.getenv("VCAP_SERVICES");
            
				out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet NewServlet</title>");            
				out.println("</head>");
				out.println("<body>");
				
			JSONParser parser = new JSONParser();
            Object obj = parser.parse(envServices);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vcapArray = (JSONArray) jsonObject.get("Object-Storage");
            JSONObject vcap = (JSONObject) vcapArray.get(0);
            JSONObject credentials = (JSONObject) vcap.get("credentials");
			*/
			out.println("<!DOCTYPE html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet NewServlet</title>");            
				out.println("</head>");
				out.println("<body>");
			
			String userId = "f9c3e14885be4fcfb3267cfc3e88b93f";
    String password = "P4PwVim5}NB^PYI9";
    String auth_url = "https://identity.open.softlayer.com"+"/v3";
    String domain = "904835";
    String project = "object_storage_45fe448f_2414_4904_9c6c_ab0be8b2ece4";
    Identifier domainIdent = Identifier.byName(domain);
    Identifier projectIdent = Identifier.byName(project);
	
	OSClient os = OSFactory.builderV3()
		.endpoint(auth_url)
		.credentials(userId, password)
		.scopeToProject(projectIdent, domainIdent)
		.authenticate();
		
		out.println("<br><h10>here</h10>");
		
		SwiftAccount account = os.objectStorage().account().get();
			
				out.println("<br><h10>account:"+ account.getContainerCount() +"</h10>");
				out.println("</body>");
				out.println("</html>");
			/*
			String auth_url = credentials.get("auth_url").toString()+"/v3";
            String project = credentials.get("project").toString();
            String projectId = credentials.get("projectId").toString();
            String region = credentials.get("region").toString();
            String userId = credentials.get("userId").toString();
            String username = credentials.get("username").toString();
            String password = credentials.get("password").toString();
            String domainId = credentials.get("domainId").toString();
            String domainName = credentials.get("domainName").toString();
			
			
			String userId = credentials.get("userId").toString();
			String password = credentials.get("password").toString();
			String auth_url = credentials.get("auth_url").toString() + "/v3";
			String domain = credentials.get("domainName").toString();
            String project = credentials.get("project").toString();
			String username = credentials.get("username").toString();
						
			out.println("<br><h10>auth_url: "+auth_url+"</h10>");
			out.println("<br><h10>project: "+project+"</h10>");
			out.println("<br><h10>password: "+password+"</h10>");
			
			Identifier domainIdent = Identifier.byName(domain);
            Identifier projectIdent = Identifier.byName(project);
			
			out.println("<br><br><h10>domainIdent: " + domainIdent.toString()+"</h10>");
			out.println("<br><h10>projectIdent: " + projectIdent.toString()+"</h10>");
			
				OSClient os = OSFactory.builderV3()
                    .endpoint(auth_url)
                    .credentials(userId, password)
					.scopeToProject(projectIdent, domainIdent)
                    .authenticate();
					
				SwiftAccount account = os.objectStorage().account().get();
			
				out.println("<br><h10>account:"+ account.getContainerCount() +"</h10>");
					
				out.println("<br><h10>HERE</h10>");
				out.println("</body>");
				out.println("</html>");
			} catch (ParseException ex) {
				out.println("<h10>aw</h10>");
				out.println("</body>");
				out.println("</html>");
			}
			SwiftAccount account = connector.getAccount();
			request.setAttribute("swiftaccnt", account);
			
			response.sendRedirect("home.jsp");
			*/
			
			
		}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
