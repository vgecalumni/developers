$(document).ready(function () {
  
  $('#password').focusout(function() {
      $('.passwordlabel').css({
          'top': '58%',
          'color': '#000000',
          'cursor': 'auto',
           'font-size':'100%',
      });
  });
  
  $('#password').change(function(){
    $('#password').focusout(function() {
      if($("#password").val()){
      $('.passwordlabel').css({
          'color': '#000000',
          'top': '47%',
           'font-size':'100%',
      });}
    });
  });
  
  $('#password').focus(function() {
    $('.passwordlabel').css({
      'color': '#4285F4',
      'top': '47%',
      'cursor': 'default',
      'font-size':'90%',
    });
  });
    
  $(".passwordlabel").click(function(){
    $("#password").trigger("focus");
  });
    
  $("#password").trigger("focus");

});

$('#next').click(function(){
  
  var password = $("#password").val();

  // Check Null

  if(!password){

    $(".loginmessage").html("Please provide password");
    

  }else{
   
    // Check Valid Syntax

    ValidatePassword(password);

  }

});

function ValidatePassword(password) 
{

	var passwordFormat = /^[a-zA-Z0-9!@#\$%\^\&*\)\(+=._-]{6,}$/g
  	
  if(passwordFormat.test(password))
  {
    
    // Check Database
    $(".login").addClass("loading");
  	$(".loadingline").removeClass("hidden");
    setTimeout(Verifypassword, 000);

    function Verifypassword(){
      
      $.ajaxSetup({
        headers: {
          'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
      });
      
      $.ajax({
        method:'post',
        url:"/login/psd/passwordverify",
        data: {
              email : $('#email').val(),
              password: $('#password').val()
        },
        
        success:function(data){
           
          if(data.msg == "true"){

            $('#loginform').trigger('submit');
           
          }else if(data.msg == "false"){  
             
            $(".password").addClass("loading");
            $(".loadingline").removeClass("hidden");
            $(".login").removeClass("loading");
            $(".loadingline").addClass("hidden");
            $(".loginmessage").html("Invalid Password");
            
           
          }else{

            $(".password").addClass("loading");
            $(".loadingline").removeClass("hidden");
            $(".login").removeClass("loading");
            $(".loadingline").addClass("hidden");
            $(".loginmessage").html("Something went wrong.Try again later");
           
          }
        }
      });      
    } 
  }else{

    $(".loginmessage").html("Password must be atleast 6 characters");
  
  }
}

/*$(document).keypress(function(e) {
    var keycode = (e.keyCode ? e.keyCode : e.which);
    if (keycode == '13') {
        loginsubmit();
    }
});*/