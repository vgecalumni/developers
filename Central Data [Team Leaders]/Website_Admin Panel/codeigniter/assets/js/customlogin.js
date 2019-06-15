$(document).ready(function () {
  
  $('#email').focusout(function() {
      $('.emaillabel').css({
          'top': '58%',
          'color': '#000000',
          'cursor': 'auto',
           'font-size':'100%',
      });
  });
  
  $('#email').change(function(){
    $('#email').focusout(function() {
      if($("#email").val()){
      $('.emaillabel').css({
          'color': '#000000',
          'top': '47%',
           'font-size':'100%',
      });}
    });
  });
  
  $('#email').focus(function() {
    $('.emaillabel').css({
      'color': '#4285F4',
      'top': '47%',
      'cursor': 'default',
      'font-size':'90%',
    });
  });
    
  $(".emaillabel").click(function(){
    $("#email").trigger("focus");
  });
    
  $("#email").trigger("focus");

  $('#pwd').focusout(function() {
      
      $('.passwordlabel').css({
          'top': '63%',
          'color': '#000000',
          'cursor': 'auto',
          'font-size':'100%',
      });
  });
  
  $('#pwd').change(function(){
    
    $('#pwd').focusout(function() {
      
      if($("#pwd").val()){
      
        $('.passwordlabel').css({
            'color': '#000000',
            'top': '54%',
            'font-size':'100%',
        });
      }
    });
  });
    
  $('#pwd').focus(function() {
      
    $('.passwordlabel').css({
      'color': '#4285F4',
      'top': '54%',
      'cursor': 'default',
      'font-size':'90%',
    });
  });
  
  $(".passwordlabel").click(function(){
      
      $("#pwd").trigger("focus");
    
  });
    
  $("#pwd").trigger("focus");

});

$('#next').click(function(){
  
  var email = $("#email").val();

  // Check Null

  if(!email){

    $(".loginmessage").html("Please provide email");
    

  }else{
   
    // Check Valid Syntax

    ValidateEmail(email);

  }

});

function ValidateEmail(email) 
{

	var mailFormat = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  	
  if(mailFormat.test(email))
  {
    
    // Check Database
    $(".login").addClass("loading");
  	$(".loadingline").removeClass("hidden");
    setTimeout(VerifyEmail, 000);

    function VerifyEmail(){
      
      $.ajaxSetup({
        headers: {
          'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
      });
      
      $.ajax({
        method:'post',
        url:"/login/identifier/emailverify",
        data: {
              email: $('#email').val()
        },
        
        success:function(data){
           
          if(data.msg == "true"){

            $('#loginform').trigger('submit');
           
          }else if(data.msg == "false"){  
             
            $(".password").addClass("loading");
            $(".loadingline").removeClass("hidden");
            $(".login").removeClass("loading");
            $(".loadingline").addClass("hidden");
            $(".loginmessage").html("Couldn't find your email");
            
           
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

    $(".loginmessage").html("Invalid Email Format");
  
  }
}
function emailSection() {

  $(".loadingline").addClass("hidden");
  $(".password").addClass("hidden");
  $(".login").removeClass("hidden");
  $(".loginmessage").addClass("hidden");

}  

function passwordsubmit()
{
  var pwd = $("#pwd").val();

  if(!pwd){
    $(".passwordmessage").html("Please provide password");
  }else{
    Validate(pwd)
  }
}
function Validate(pwd)
{
    if(pwd == "123"){

      $(".password").addClass("hidden");
      $(".bot").addClass("hidden");

    }
    else
    {
      $(".passwordmessage").html("Wrong Password");
    }
}

/*$(document).keypress(function(e) {
    var keycode = (e.keyCode ? e.keyCode : e.which);
    if (keycode == '13') {
        loginsubmit();
    }
});*/