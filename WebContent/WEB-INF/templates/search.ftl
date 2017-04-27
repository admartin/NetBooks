<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Netbooks</title>

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="js/ajax-query.js"></script>
        
        <script src="js/book-info.js"></script>
        
        <!-- favicon-->
        <link rel="icon" href="http://www.iconsdb.com/icons/preview/green/book-xxl.png">
        
        <!-- Latest compiled and compressed bootsrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <!-- Navbar color override -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <!--Scolling UI -->
        <link rel="stylesheet" href="css/flex.css">
        
    </head>
    <body>  
        
       
        <!-- Modal -->
        <div id="myModal" class="modal fade" tabindex="-1" data-focus-on="input:first" data-replace="true" style="display: none;"]>
          <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 id="title" class="modal-title"></h4>
              </div>
              <div class="modal-body">
                  <div class="media">
                      <div class="media-body">
                      	<div id="info"></div>
                      	<div id="summary"></div>
                          <!--pulled from jQuery-->
                      </div>
                      <div class="media-right">
                        <img id="bookimg" src="" class="media-object" style="width:150px">
                      </div>
                  </div>
                  <h4 class="text-center">Reviews</h4>
                  <hr style="margin-top:10px;margin-bottom:10px;" >
                  <div id="reviewtext">
                  		<!-- pulled from jQuery -->
                  </div>
              </div>
              <div id="footer" class="modal-footer">
                <!-- jQuery -->
               
              </div>
            </div>

          </div>
        </div>
        
         <div id="review" class="modal fade" tabindex="-1" data-focus-on="input:first" style="display: none;">
            <div class="modal-dialog">
            <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 id="title" class="modal-title">Add A Review</h4>
                  </div>
                 
                  
                  <div class="modal-footer">
                  <form role="form" action="AddReview" method="post">
                  <textarea name="review" class="form-control" id="inputComment" rows="5"></textarea>
                  	<input type="hidden" value="" name="book_id_review" /><hr/>
                    <button type="submit"  name="checkout" class="btn btn-success">Add</button>
                    <button type="button" data-dismiss="modal" class="btn btn-danger">Cancel</button>
                  </form>
                  </div>
                  
                  
                </div>
            </div>
        </div>
        
        <div id="confirmation" class="modal fade" tabindex="-1" data-focus-on="input:first" style="display: none;">
            <div class="modal-dialog modal-sm">
            <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  </div>
                  <div class="modal-body">
                    <p>Are you sure you would like to check out this book?</p>
                  </div>
                  <div class="modal-footer">
                  <form role="form" action="CheckOut" method="post">
                  	<input type="hidden" value="" name="book_id" />
                    <button type="submit"  name="checkout" class="btn btn-success">Order</button>
                    <button type="button" data-dismiss="modal" class="btn btn-danger">Cancel</button>
                  </form>
                  </div>
                </div>
            </div>
        </div>
        
        <div id="waitlist" class="modal fade" tabindex="-1" data-focus-on="input:first" style="display: none;">
            <div class="modal-dialog modal-sm">
            <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  </div>
                  <div class="modal-body">
                    <p>Are you sure you would like to add this book to your waitlist?</p>
                  </div>
                  <div class="modal-footer">
                  <form role="form" action="AddWaitlist" method="post">
                  	<input type="hidden" value="" name="wait_id" />
                    <button type="submit"  data-dismiss="modal" class="btn btn-success">Add</button>
                    <button type="button" data-dismiss="modal" class="btn btn-danger">Cancel</button>
                  </form>
                  </div>
                </div>
            </div>
        </div>
 
        
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
            
            <div id="main" class role="main">
                <!-- Book browser -->
                <div id="browser">
                    
                    <!-- Category -->
                    <div class="category">
                        <ul class="content">
                           <#list books as book>	
                            <li class="book" data-id="${book.getID()}" data-image="${book.cover}"
                            <#if premium>
                            	data-sub="1"
                            <#else>
                            	data-sub="0"
                            </#if> 
                            <#if book.ebook>
                            	data-pdf="${book.link}"
                            	data-ebook="1"
                            </#if>
                                data-copies="${book.numCopies}" data-out="${book.numOut}">
                                <span><img class="tile__img" src="${book.cover}" alt="${book.title}"/></span>       
                            </li>
                        </#list>
                        </ul>
                    </div>   
                                        
                </div>    
            </div>            
        </div> <!--! end of #container -->        
      
    </body>
</html>