<?php echo form_open('group/add') ?>
<table class="table table-bordered">
    <tr>
        <td>Name Group</td>
        <td><input type="text" name="group_name" class="form-control" placeholder="Group Name"></td>
    </tr>
    <tr>
        <td colspan="2" align="right"><input type="submit" name="submit" value="Submit" /></td>
    </tr>
</table>
<?php echo form_close(); ?>