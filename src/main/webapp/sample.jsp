<%@page import="org.openstack4j.*%>
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
            String userId = "ec7f2a51ce6c4e2b8464e95fff499597";
            String password = "mP1xlJD6]D!87Dbs";
            String auth_url = "https://identity.open.softlayer.com" + "/v3";
            String domain = "904797";
            String project = "object_storage_f5e341c0_fe2b_4057_900e_cef0cfd92a52";
            Identifier domainIdent = Identifier.byName(domain);
            Identifier projectIdent = Identifier.byName(project);

            OSClient os = OSFactory.builderV3().endpoint(auth_url).credentials(userId, password).scopeToProject(projectIdent, domainIdent).authenticate();

            out.println("<br><h10>here</h10>");
        %>
    </body>
</html>
