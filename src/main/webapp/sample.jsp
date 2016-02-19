<%-- 
    Document   : sample
    Created on : Feb 19, 2016, 12:33:57 AM
    Author     : Danica
--%>

<%@page import="org.openstack4j.model.storage.object.SwiftAccount"%>
<%@page import="org.openstack4j.openstack.OSFactory"%>
<%@page import="org.openstack4j.api.OSClient"%>
<%@page import="org.openstack4j.model.common.Identifier"%>
<%@page import="org.openstack4j.model.common.Payloads"%>
<%@page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connect to Storage</title>
    </head>
    <body>
        <%
            String userId = "f9c3e14885be4fcfb3267cfc3e88b93f";
            String password = "P4PwVim5}NB^PYI9";
            String auth_url = "https://identity.open.softlayer.com" + "/v3";
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
        %>
    </body>
</html>
