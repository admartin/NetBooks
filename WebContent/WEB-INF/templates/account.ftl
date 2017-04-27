<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Netbooks</title>

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

        <!-- favicon-->
        <link rel="icon" href="http://www.iconsdb.com/icons/preview/green/book-xxl.png">
        
        <!-- Latest compiled and compressed bootsrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <!-- Navbar color override -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/waitlist.css">
        
    </head>
    <body>   
        
        <div id="container">
            
            <!--Nav bar -->
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="SignInServlet">Netbooks</a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> ${username}
                            <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="Account?user=${username}">Your Account</a></li>
                                <li class="divider"></li>
                                <li><a href="index.html">Sign Out</a></li>
                            </ul>
                          </li>
                    </ul>
                    <form class="navbar-form navbar-right" action="Search" method="post">
                        <div class="input-group">
                            <input type="text" name="search" class="form-control" placeholder="Search">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit">
                                        <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                          </div>
                    </form>
                </div>
            </nav>
            
            <div class="container" style="margin-top:75px;">
              <div class="row">
                <div class="col-md-4">
                  <h2>My Account</h2>
                </div>
              </div>
              <div class="row">
                <hr style="margin-top:10px;margin-bottom:10px;" >
              </div>
              <div class="row">
                  <div class="col-md-3">
                    <h4>Membership &amp; Billing</h4>
                  </div>
              </div>
              <div class="row">
                  <div class="col-md-2 col-md-offset-2">${username}</div>
                  <div class=" col-md-2 pull-right text-right"><a href="Settings?opt=delete" style="color:red">Delete User</a></div>
              </div>
	      <form action="Settings" method="post">
              <div class="row">
                <div class="col-md-2 col-md-offset-2">${email}</div>
                <div class="col-md-2 pull-right text-right"><a href="Settings?opt=email">Change Email</a></div>
               </div>
               <div class="row">
                  <div class="col-md-2 col-md-offset-2">*****</div>
                   <div class="col-md-2 pull-right text-right"><a href="Settings?opt=password">Change Password</a></div>
                </div>
                <div class="row">
                <div class="col-md-offset-2"><hr style="margin-top:10px;margin-bottom:10px;" ></div>
                </div>
                <div class="row">
                  <div class="col-md-4 col-md-offset-2">${address}</div>
                   <div class="col-md-3 pull-right text-right"><a href="Settings?opt=address">Change Billing Address</a></div>
                </div>
              <div class="row">
                <hr style="margin-top:10px;margin-bottom:10px;" >
              </div>
              <div class="row">
                  <div class="col-md-4">
                    <h4>Plan</h4>
                    </div>
                  <div class="col-md-2 pull-right text-right"><a href="Settings?opt=subscription">Change Plan</a></div>
              </div>
              <div class="row">
                <div class="col-md-2 col-md-offset-2">Ebook <span class="glyphicon glyphicon-ok " style="color:green"></span></div>
               </div>
               <div class="row">
                  <div class="col-md-2 col-md-offset-2">Book-to-Door 
                  	<#if premium><span class="glyphicon glyphicon-ok" style="color:green"></span></#if>
                  </div>
                </div>
                <div class="row">
                <hr style="margin-top:10px;margin-bottom:10px;" >
              </div>
              <div class="row">
                    <!-- Category -->
                    <div class="col-md-4">
                    <h4>Checked Out Books</h4>
                    </div>
                </div>
		</form>
                <#if checkedout?has_content>
                <div class="row">
                    <div class="category">
                        <ul class=" col-md-offset-2 categoryRow clearfix">
                        <#list checkedout>
						<#items as book>	
                            <li class="book" data-image="${book.getCover()}"  
                            <#if premium>
                            	data-sub="1"
                            </#if>
                            <#if book.ebook>
                            	data-pdf="${book.link}"
                            	data-ebook="1"
                            </#if>
                                data-copies="${book.numCopies}" data-out="${book.numOut}">
                                <span><img class="tile__img" src="${book.getCover()}"/></span>       
                            </li>
                        </#items>
                        </#list>
                        </ul>
                    </div>                   
                </div> 
                </#if>
            </div>
        </div> <!--! end of #container -->        

    </body>
</html>