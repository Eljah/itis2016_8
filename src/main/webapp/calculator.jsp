<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>

<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <h1>Калькулятор</h1>
        <form id="calcform">
            <div class="form-group">
                <p>Last expression: <span id="resultExp"></span></p>
                <input type="text" class="form-control" placeholder="Expression" name="digit" id="digit" autofocus
                       autocomplete="off"/>
            </div>
            <button type="button" class="btn btn-default" id="add" name="mathAction" value="add">+</button>
            <button type="button" class="btn btn-default" id="subtract" name="mathAction" value="subtract">-</button>
            <button type="button" class="btn btn-default" id="multiply" name="mathAction" value="multiply">*</button>
            <button type="button" class="btn btn-default" id="divide" name="mathAction" value="divide">/</button>
            <button type="button" class="btn btn-primary" id="result" name="mathAction" value="result">=</button>
        </form>
    </div>
</div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>
<script src="js/webcalc.js"></script>
<script>

    $.validator.setDefaults({
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block'
    });

    $("#calcform").validate({
        rules: {
            digit: {
                required: true,
                number: true
            }
        }

    });
    $(document).ready(function () {
        $("button").click(function (event) {
            var buttonId = event.target.id;
            var x = document.getElementById("digit").value;
            if ($("input[name=digit]").valid()) {
                $.post("calculator", {digit: x, mathAction: buttonId}, function (data) {
                    $("#resultExp").text(data);
                    $("#digit").val("");
                    $("#digit").focus();

                });
            }
            return false;
        })
    });
</script>
</body>
</html>
