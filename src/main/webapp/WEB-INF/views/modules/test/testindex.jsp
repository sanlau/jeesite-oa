<!DOCTYPE html >
<html>
 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>nginxTest</title>
 
        <script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
 
    </head>
    <style>
        .expa {
            width: 300px;
            height: 350px;
            border: 1px solid #F2F2F2;
            overflow: auto;
            margin-left: 200px;
        }
        
        td {
            width: 291px;
            height: 40px;
            border: 1px solid #F2F2F2;
        }
    </style>
 
    <body>
        <div class="expa">
            <table id="tt">
                <tr id="hh">
                    <td>you have a new message0</td>
                </tr>
            </table>
        </div>
    </body>
 
    <script type="text/javascript">
        //var int =  = self.setInterval("al("clock()", 1000);
        var int = self.setInterval("clock()", 2000);
        var numb = 0;
 
        function clock() {
            numb += 1
            var htm = "<tr><td>you have a new message" + numb + "</td></tr>"
            $("#tt").prepend(htm);
        }
    </script>
 
</html>