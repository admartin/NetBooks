
        
        
        $(document).ready(function(){
            //listen for clicks on book images
            $('#main').on('click','li',function(){
   
   			
			var obj = {
				id : $(this).data('id')
			};
			
			
			$.ajax({
			    url: "PopulateSummary",
			    data: obj,
			    dataType: "json",
			    success: summaryParse,
			    complete: function() {
				}
			});
			
			$.ajax({
			    url: "PopulateReviews",
			    data: obj,
			    dataType: "json",
			    success: reviewParse,
			    complete: function() {
				}
			});
			
                $("#title").text($(this).data('title'));
                $("#bookimg").attr("src", $(this).data('image'));
                
                $("#info").html(
                    '<p><strong>Author: ' + $(this).data('author') + '<br>Year: ' + $(this).data('year') + '<br>Genre: ' + $(this).data('genre') + '</strong></p>'
                );
	
				if($(this).data('sub') == 1){
                 if(($(this).data('copies') - $(this).data('out')) > 0 )
	           {
	            	$('input[name=book_id]').attr('value', $(this).data('id'))
		            $("#footer").html(
	                    '<button id="order" type="button" class="btn btn-success" data-toggle="modal" href="#confirmation">Order Copy</button> <button id="reviewbtn" type="button" class="btn btn-success"  data-toggle="modal" href="#review">Add a Review</button>'
	                );
                }
                else
                {
                	$('input[name=wait_id]').attr('value', $(this).data('id'))
                	$("#footer").html(
	                    '<button id="order" type="button" class="btn btn-success" data-toggle="modal" href="#waitlist">Add To Waitlist</button><button id="reviewbtn" type="button" class="btn btn-success"  data-toggle="modal" href="#review">Add a Review</button>'
	                );
                }
                }
                var pdf = $(this).data('pdf');
                if(typeof pdf != 'undefined' ){
	                $("#footer").append(
		                    '<a href="' + $(this).data('pdf') + ' " role="button" class="btn btn-success">Read Now</a>'
		                );
                }
                $('input[name=book_id_review]').attr('value', $(this).data('id'));  
                $('#myModal').modal('show');
            })
            
        });
        
        function summaryParse(responseJson) {
			if(responseJson != null) {
	        var descr = $("#summary");
	        descr.html('<div></div>');
	        $.each(responseJson, function(key, value) {
	        	var row = '<p>' + value['desr'] + '</p>';
				descr.append(row);
	        });
	       }
	    }
        
        function reviewParse(responseJson) {
			if(responseJson != null) {
	        var reviews = $("#reviewtext");
	        reviews.html('<div></div>');
	        $.each(responseJson, function(key, value) {
	        	var row = '<div class="well well-sm"><q>' + value['details'] + '</q><br><div class="text-right">-' + value['username'] + '</div></div>';
				reviews.append(row);
	        });
	    }
	    }
	    
	    