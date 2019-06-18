<?php 

if($request == "dashboard"){
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "core/dashboard/load_data",
    // dataType: 'json',
    data: {user: $user},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "contact") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "core/contact/load_data",
    // dataType: 'json',
    data: {user: $user},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "events") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  $event_op = "<?php echo $event_op; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "events/events/load_data",
    // dataType: 'json',
    data: {user: $user,event_op: $event_op},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "event_details") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  $event_id = "<?php echo $event_id; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "events/details/load_data",
    // dataType: 'json',
    data: {user: $user,event_id: $event_id},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "event_proposals") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  $event_id = "<?php echo $event_id; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "events/proposals/load_data",
    // dataType: 'json',
    data: {user: $user,event_id: $event_id},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "event_reports") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  $event_id = "<?php echo $event_id; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "events/reports/load_data",
    // dataType: 'json',
    data: {user: $user,event_id: $event_id},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "event_photos") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  $event_id = "<?php echo $event_id; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "events/photos/load_data",
    // dataType: 'json',
    data: {user: $user,event_id: $event_id},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "event_registrations") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  $event_id = "<?php echo $event_id; ?>";

  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "events/event_registrations/load_data",
    // dataType: 'json',
    data: {user: $user,event_id: $event_id},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "event_coupons") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  $event_id = "<?php echo $event_id; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "events/coupons/load_data",
    // dataType: 'json',
    data: {user: $user,event_id: $event_id},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "alumni_registrations") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "alumni/alumni_registrations/load_data",
    // dataType: 'json',
    data: {user: $user},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "messages") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "advanced/messages/load_data",
    // dataType: 'json',
    data: {user: $user},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "activity") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "advanced/activity/load_data",
    // dataType: 'json',
    data: {user: $user},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php }elseif ($request == "user_mgmt") {
?>
<script type="text/javascript">
  $("#data").addClass('hidden');
  $user = "<?php echo $designation; ?>";
  jQuery.ajax({
    type: "POST",
    url: "<?php echo base_url(); ?>" + "core/user_mgmt/load_data",
    // dataType: 'json',
    data: {user: $user},
    success: function(res){

      $("#data").html(res);
      $("#loader").addClass('hidden');
      $("#data").removeClass('hidden');
    } 
  });
</script>
<?php } ?>