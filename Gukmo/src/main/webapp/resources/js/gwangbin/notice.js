$(document).ready(function(){
	
	$("div#edit_delete_list").hide();
	
	$("div#more_list").click(function(){
		$("div#edit_delete_list").toggle();
	});
	
	$('span#like_button').click(function(){
		$('svg#heart').css({"color": "blue", "font-size": "20px"});
	});
	
	
});