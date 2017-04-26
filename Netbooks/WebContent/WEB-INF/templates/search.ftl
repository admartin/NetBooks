<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Netbooks</title>

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script>
        $(document).ready(function(){
            //listen for clicks on book images
            $('#main').on('click','li',function(){
                $("#title").text($(this).data('title'));
                $("#bookimg").attr("src", $(this).data('image'));
                
                $("#info").html(
                    '<p>Author: ' + $(this).data('author') + '<br>Year: ' + $(this).data('year') + '<br>Genre: ' + $(this).data('genre') + '</p>'
                    + '<q>' + $(this).data('descr') + '</q>'
                );
                
                var json = JSON.parse($(this).data('reviews'));
                for(i = 0; i < json.length; i++)
                {
	                $("#reviews").append(
	                
	                    '<div class="well well-sm">' + json[i].details + '	</div>'
	                );
	            }
	            
	            if(($(this).data('copies') - $(this).data('out')) > 0 )
	            {
		            $("#footer").append(
	                    '<button id="order" type="button" class="btn btn-success" data-toggle="modal" href="#confirmation">Order Copy</button>'
	                );
                }
                else
                {
                	$("#footer").append(
	                    '<button id="order" type="button" class="btn btn-success" data-dismiss="modal">Add to Waitlist</button>'
	                );
                }
                
                if(($(this).data('ebook'))
                $("#footer").append(
	                    '<a href="' + $(this).data('pdf') + ' " role="button" class="btn btn-success">Read Now</a>'
	                );
	            $('input[name=book_id_review]').attr('value', $(this).data('id'));    
                $('#myModal').modal('show');
            })
            
        });
        </script>
        
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
        
        <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 id="title" class="modal-title"></h4>
              </div>
              <div class="modal-body">
                  <div class="media">
                      <div id="info" class="media-body">
                          <!--pulled from jQuery-->
                      </div>
                      <div class="media-right">
                        <img id="bookimg" src="" class="media-object" style="width:150px">
                      </div>
                  </div>
                  <hr style="margin-top:10px;margin-bottom:10px;" >
                  <div id="reviews">
                  		<!-- pulled from jQuery -->
                  </div>
              </div>
              <div id="footer" class="modal-footer">
                <!-- jQuery -->
                <button id="reviewbtn" type="button" class="btn btn-success"  data-toggle="modal" href="#review">Add a Review</button>
              </div>
            </div>

          </div>
        </div>
        
        <div id="review" class="modal fade" tabindex="-1" data-focus-on="input:first" style="display: none;">
            <div class="modal-dialog">
            <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                    <h4 id="title" class="modal-title">Add A Review</h4>
                  </div>
                  <div class="modal-body">
                    <form role="form" action="AddReview" method="post">
                    <input type="hidden" value="" name="book_id_review"></input>
                      <div class="form-group">
                        <textarea class="form-control" id="inputComment" rows="5"></textarea>
                      </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-success">Submit</button>
                    <button type="button" data-dismiss="modal" class="btn btn-danger">Cancel</button>
                  </div>
                  </form>
                </div>
            </div>
        </div>
        
        <div id="confirmation" class="modal fade" tabindex="-1" data-focus-on="input:first" style="display: none;">
            <div class="modal-dialog modal-sm">
            <!-- Modal content-->
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">�</button>
                  </div>
                  <div class="modal-body">
                    <p>Are you sure you would like to check out this book?</p>
                  </div>
                  <div class="modal-footer">
                  <form role="form" action="CheckOut" method="post">
                  	<input type="hidden" value="" name="book_id" />
                    <button type="submit"  name="checkout" data-dismiss="modal" class="btn btn-success">Order</button>
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
                        <a class="navbar-brand" href="home.html">Netbooks</a>
                    </div>
                     <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> ${username}
                            <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                            <form method="post" action="Account">
                                <li><a href="Account">Your Account</a></li>
                                </form>
                                <li class="divider"></li>
                                <li><a href="index.html">Sign Out</a></li>
                            </ul>
                          </li>
                    </ul>
                    <form class="navbar-form navbar-right" action="Search" method="post">
                        <div class="input-group">
                            <input type="text" class="form-control" name="search" placeholder="Search">
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
                           <#list scifi>
						<#items as book>	
                            <li class="book box" data-ebook="${book.ebook}" data-sub="${premium}" data-image="${book.cover}" data-title="${book.title}" data-reviews="${book.jsonReview} 
                            <#if book.ebook>
                            	data-pdf="${book.link}
                            </#if>
                                data-copies="${book.numCopies}" data-out="${book.numOut}" data-author="${book.author.name}+" data-year="${book.year}" data-genre="${book.genre}" data-rating="${book.rating}" data-descr="${book.desr}">
                                <span><img class="tile__img" src="${book.cover}" alt="${book.title}"/></span>       
                            </li>
                        </#items>
                        </#list>
                        </ul>
                    </div>   
                                        
                </div>    
            </div>            
        </div> <!--! end of #container -->        

        <!-- Add your site or application content here -->
        <script src="js/app.js"></script>
        <script>  
            //Kick start app
            App.startup();
        </script>        
    </body>
</html>
