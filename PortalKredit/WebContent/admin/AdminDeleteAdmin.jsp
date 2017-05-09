<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.annotation.Resource" %>

<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2 col-sm-4 sidebar1">
                <div class="logo">
                    <img src="../assets/images/logo.png" class="img-responsive center-block" alt="Logo" style="width: 110">
                </div>
                <br>
                <div class="left-navigation">
                    <ul class="list">
                        <h5 style="color: rgba(255, 255, 255, 0.5)"><strong>DELETE ADMIN</strong></h5>
                        <li><a style = "color: #fff" href="AdminCreateClient.jsp" >Create Client</a></li>
                        <li><a style = "color: #fff" href="AdminCreateBanker.jsp" >Create Banker</a></li>
                        <li><a style = "color: #fff" href="AdminCreateAdmin.jsp" >Create Admin</a></li>
                        <li><a style = "color: #fff" href="AdminDeleteClient.jsp" >Delete Client</a></li>
                        <li><a style = "color: #fff" href="AdminDeleteBanker.jsp" >Delete Banker</a></li>
                        <li><a style = "color: #fff" href="AdminDeleteAdmin.jsp" >Delete Admin</a></li>
                        
                    </ul>
                </div>
            </div>
            <div class="col-md-10 col-sm-8 main-content">
            <h1>Delete Admin!</h1>
            
             <table class="table table-bordered">
    <thead>
      <tr>
        <th>Login</th>
        <th>Password</th>
      </tr>
    </thead>

    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>

      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>

      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
      </tr>
    </tbody>
  </table>
  
			</div>
    </div>
</body>