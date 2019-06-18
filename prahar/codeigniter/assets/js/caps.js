$(document).ready(function()
{
	$(".one").focusout(function()
	{
		var x=$(".one").val();
		if(!validateEmail(x))
		{
			$(".check").text("Enter a valid email");
		}
	});
	$('#password').focusout(function() 
	{
		$('.check1').text(checkStrength($('#password').val()))
	});
});
function validateEmail(x)
{
	var filter=/^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(filter.test(x))
	{
		return true;
	}
	else
	{
		return false;
	}	
}	
function checkStrength(password) {
		var strength = 0
		if (password.length < 6) {
			$(".check1").addClass("too_weak");
			return 'Too short'
		}
		if (password.length > 7) strength += 1
		// If password contains both lower and uppercase characters, increase strength value.
		if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1
		// If it has numbers and characters, increase strength value.
		if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1
		// If it has one special character, increase strength value.
		if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
		// If it has two special characters, increase strength value.
		if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
		// Calculated strength value, we can return messages
		// If value is less than 2
		if (strength < 2) {
			$(".check1").addClass("weak");
			return 'Weak'
		} 
		else if (strength == 2) {
			$(".check1").addClass("good");
			return 'Good'
		} 
		else {
			return 'Strong'
			$(".check1").addClass("strong");
		}
}