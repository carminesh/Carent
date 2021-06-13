$(document).ready(
	() => {
		$.ajax (
			{
				"url": "fotoiniziali",
				"type": "GET",
				"success": function (data) {
					console.log(data);
					$(".riga").html(data);
				},
				
				"error": function(xhr, ajaxOptions, thrownError) {
					alert(xhr.status);
					alert(thrownError);
				}
			}
		)
	}
)