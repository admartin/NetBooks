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
                           <#list books as book>	
                            <li class="book box" data-image="${book.cover}" data-title="${book.title}" 
							<#if premium>
                            	data-sub="1"
                            </#if>
                                                        
                            <#if book.ebook>
                            	data-pdf="${book.link}"
                            </#if>
                                data-copies="${book.numCopies}" data-out="${book.numOut}" data-author="${book.author.name}+" data-year="${book.getPubDate()}" data-genre="${book.genre}" data-rating="${book.rating}" data-descr="${book.desr}">
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
