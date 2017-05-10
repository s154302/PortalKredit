<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                        <h5 style="color: rgba(255, 255, 255, 0.5)"><strong>DELETE BANKER</strong></h5>
                        <li><a href="AdminCreateClient.jsp" >Create Client</a></li>
                        <li><a href="AdminCreateBanker.jsp" >Create Banker</a></li>
                        <li><a href="AdminCreateAdmin.jsp" >Create Admin</a></li>
                        <li><a href="AdminDeleteClient" >Delete Client</a></li>
                        <li><a href="AdminDeleteBanker" >Delete Banker</a></li>
                        <li><a href="AdminDeleteAdmin" >Delete Admin</a></li>
                        
                    </ul>
                </div>
            </div>
            <div class="col-md-10 col-sm-8 main-content">
            <h1>Delete Banker!</h1>
            <table class="table table-bordered">
    			<thead>
    
    
    
			    	<tr>
			        	<th>Login</th>
			        	<th>Password</th>
			        	<th> Delete </th>
			      	</tr>
			    </thead>

		    	<tbody >
			
		  			<c:forEach var="ob" items="${list}">
		     			<tr>
		       				<td><c:out value="${ob.username}"/></td>
		       				<td><c:out value="${ob.password}"/></td>
		       				
		       				<td> <form action="" method="post">
		       					<button class="btn btn-primary btn-block btn-action"
										type="submit" name="username" value="${ob.username}">Delete</button> 
								</form>
							
		    			</tr>
		 			</c:forEach>
		
		    	</tbody>
		  		</table>
			</div>
    </div>
</body>