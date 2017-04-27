
$(document).ready(function(){
	//listen for clicks on book images
	$('#main').on('click','li',function(){

		//book object
		var obj = {
				id : $(this).data('id')
		};

		//populates book info
		$.ajax({
			url: "PopulateSummary",
			data: obj,
			dataType: "json",
			success: summaryParse,
			complete: function() {
			}
		});

		//populates reviews
		$.ajax({
			url: "PopulateReviews",
			data: obj,
			dataType: "json",
			success: reviewParse,
			complete: function() {
			}
		});

		$("#bookimg").attr("src", $(this).data('image'));

		//if premium user display order or waitlist button
		if($(this).data('sub') == 1){
			//if copy of book is available display order button
			if(($(this).data('copies') - $(this).data('out')) > 0 )
			{
				$('input[name=book_id]').attr('value', $(this).data('id'))
				$("#footer").html(
						'<button id="order" type="button" class="btn btn-success" data-toggle="modal" href="#confirmation">Order Copy</button> <button id="reviewbtn" type="button" class="btn btn-success"  data-toggle="modal" href="#review">Add a Review</button>'
				);
			}
			//otherwise waitlist button
			else
			{
				$('input[name=wait_id]').attr('value', $(this).data('id'))
				$("#footer").html(
						'<button id="order" type="button" class="btn btn-success" data-toggle="modal" href="#waitlist">Add To Waitlist</button><button id="reviewbtn" type="button" class="btn btn-success"  data-toggle="modal" href="#review">Add a Review</button>'
				);
			}
		}
		else
		{
			$("#footer").html(
					'<button id="reviewbtn" type="button" class="btn btn-success"  data-toggle="modal" href="#review">Add a Review</button>'
			);
		}
		//display pdf if an ebook is available
		var pdf = $(this).data('pdf');
		if(typeof pdf != 'undefined' ){
			$("#footer").append(
					'<a href="' + $(this).data('pdf') + ' " role="button" class="btn btn-success">Read Now</a>'
			);
		}
		
		//hidden value for servlet
		$('input[name=book_id_review]').attr('value', $(this).data('id'));  
		//show modal
		$('#myModal').modal('show');
	})

});

function summaryParse(responseJson) {
	if(responseJson != null) {
		var descr = $("#summary");
		 var title = $("#title");
		 var author = $("#author");
		 var year = $("#year");
		 var genre = $("#genre");
		 title.html('');
		 author.html('Author: ');
		 year.html('Year: ');
		 genre.html('Genre: ');
		$("#bookimg").attr("src", $(this).data('image'));
		descr.html('<div></div>');
		$.each(responseJson, function(key, value) {
			var row = '<p>' + value['desr'] + '</p>';
			descr.append(row);
			row = value['title'];
			title.append(row);
			row = value.author.name;
			author.append(row);
			row = value['pubDate'];
			year.append(row);
			row = value['genre'];
			genre.append(row);
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

