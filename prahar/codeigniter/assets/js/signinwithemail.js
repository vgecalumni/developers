$(document).ready(function () {
  toastr.info("No language barrier here.You can select among 100+ languges"); 
toastr.info("Step 1 : Enter Your Email");  
  $('#email').focusout(function() {
      $('.emaillabel').css({
          'top': '68%',
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
          'top': '60%',
           'font-size':'100%',
      });}
    });
  });
    $('#email').focus(function() {
      $('.emaillabel').css({
          'color': '#4285F4',
          'top': '60%',
          'cursor': 'default',
            'font-size':'80%',
      });
    });
    $(".emaillabel").click(function(){
      $("#email").trigger("focus");
    });
    
  $("#email").trigger("focus");
  
});

$('#next').click(function(){
  
  var email = $("#email").val();

  // Check Null

  if(!email){

    $(".loginmessage").html("please provide your email");
    toastr.error('Dear User,You forgot to provide your email !');
    

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
            toastr.error("Couldn't find your email !");
            
           
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
    toastr.error('Invalid Email Format');
  
  
  }
}
function emailSection() {

  $(".loadingline").addClass("hidden");
  $(".password").addClass("hidden");
  $(".login").removeClass("hidden");
  $(".loginmessage").addClass("hidden");

}  


/*$(document).keypress(function(e) {
    var keycode = (e.keyCode ? e.keyCode : e.which);
    if (keycode == '13') {
        loginsubmit();
    }
});*/