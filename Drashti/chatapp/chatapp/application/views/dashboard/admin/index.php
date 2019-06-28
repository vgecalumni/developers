<h2>Profile</h2>

<br /><br />

<table class="table table-bordered">
    <tr>
        <th>No</th>
        <th>Username</td>
        <th>Email</td>
        <th>First Name</td>
        <th>Last Name</td>
        <th>Division</td>
        <th>Status</td>
        <th>click to approve</td>
    </tr>
    <?php
    $no = 0;
    $aktif_status = '';
    $button_activate = '';
    foreach ($record->result() as $r) {
        if ($r->is_activated == 1) {
            $aktif_status = 'approved';
            $button_activate = '';
        } else {
            $aktif_status = 'approving';
            $button_activate = anchor('user/activate/'.$r->id, 'Approve!', ['class' => 'btn btn-success btn-sm']);
        }
        $no++;
        echo "<tr>
            <td>$no</td>
            <td>$r->username</td>
            <td>$r->email</td>
            <td>$r->first_name</td>
            <td>$r->last_name</td>
            <td>$r->division</td>
            <td>$aktif_status</td>
            <td>".
                $button_activate
            ."</td>
        </tr>";
    }
    ?>
</table>