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
            
            <div class="container">
                <div class="row">
                <div class="col-sm-offset-3 col-sm-6">
                  <h3>Change Email</h3>
                </div>
              </div>
                <form method="post" action="Change">
                    <div class="row">
                        <div class="col-sm-offset-3 col-sm-6">
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input type="email" name = "email" id="email" class="form-control" onchange="if(this.checkValidity()) form.email2.pattern = this.value;"required autofocus>
                            </div>
                            <div class="form-group">
                                <label for="email2">Confirm Email:</label>
                                <input type="email" name = "email2" id="email2" class="form-control" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same email as above' : '');"required>
                            </div>
                            <div class="row" align="center">
                               <input type="submit"  class="btn btn-success" name="emailbutton" value="Submit">
                               <a href="Account" role="button" class="btn btn-danger" >Cancel</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div> <!--! end of #container -->        
        </div>
    </body>
</html>