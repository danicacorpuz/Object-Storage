/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.ObjectStorageConnector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openstack4j.model.storage.object.SwiftObject;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Danica
 */
@WebServlet(urlPatterns = {"/Download"})
public class Download extends HttpServlet {

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
				
		//response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter()
        try {
            SwiftObject swiftObj;
            InputStream inStr = null;
            OutputStream outStr = null;

            List<? extends SwiftObject> objectlist = os.objectStorage().objects().list("sample");
            String filename = request.getParameter("filename");

            for (int i = 0; i < objectlist.size(); i++) {
                if (filename.equals(objectlist.get(i).getName())) {
                    swiftObj = os.objectStorage().objects().get("sample", filename);
                    response.setContentType(swiftObj.getMimeType());
                    response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
                    inStr = swiftObj.download().getInputStream();
                    outStr = response.getOutputStream();
                    /*
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;
                    
                    while((bytesRead = inStr.read(buffer)) != -1) {
                        outStr.write(buffer, 0, bytesRead);
                    }
                    inStr.close();
                    outStr.flush();
                    outStr.close();
                    */
                    IOUtils.copy(inStr, outStr);
                    inStr.close();
                    outStr.flush();
                    outStr.close();
                    
                }
            }
            
        } catch(Exception e) {
            
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
