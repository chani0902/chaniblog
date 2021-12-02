/**
 * 
 */
	$(function(){
		console.log("jquery ready");
		
		$("#top_menu  a").each(function(idx,elem){
			console.log($(this));
			
			$(this).on("click", function(){
				$(this).addClass("active");
			});
		})
		
	});