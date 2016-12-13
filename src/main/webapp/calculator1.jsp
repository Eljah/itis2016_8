<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet"/>
</head>
<body>

<%--без jquery--%>


<div class="container">
    <div class="col-md-6 col-md-offset-3">
        <h1>Калькулятор</h1>
        <form id="calcform" name="calcform">
            <div class="form-group">
                <p>Last expression: <span id="resultExp"></span></p>
                <input type="text" class="form-control" placeholder="Expression" name="digit" id="digit" autofocus
                       autocomplete="off"/>
                <div id="err"></div>
            </div>

            <button type="button" class="btn btn-default" id="add" name="mathAction" value="add"
                    onclick="loadDoc(document.getElementById('digit').value,document.getElementById('add').value)">+
            </button>
            <button type="button" class="btn btn-default" id="subtract" name="mathAction"
                    onclick="loadDoc(document.getElementById('digit').value,document.getElementById('subtract').value)"
                    value="subtract">-
            </button>
            <button type="button" class="btn btn-default" id="multiply" name="mathAction" value="multiply"
                    onclick="loadDoc(document.getElementById('digit').value,document.getElementById('multiply').value)">
                *
            </button>
            <button type="button" class="btn btn-default" id="divide" name="mathAction" value="divide"
                    onclick="loadDoc(document.getElementById('digit').value,document.getElementById('divide').value)">/
            </button>
            <button type="button" class="btn btn-primary" id="result" name="mathAction" value="result"
                    onclick="loadDoc(document.getElementById('digit').value,document.getElementById('result').value)">=
            </button>
        </form>
    </div>
</div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js"></script>
<script src="js/webcalc.js"></script>
<script>
    function checkForm() {
        var re = /^-?([0-9]+)(\.[0-9]+)?$/g;
        if (!re.test(document.getElementById('digit').value)) {
            document.getElementById("err").innerHTML = 'Enter valid number';
            document.getElementById('digit').focus;
            return false;
        }
        return true;
    }

    function loadDoc(val1, val2) {
        if (checkForm()) {
            var xhttp = new XMLHttpRequest();
            var body = "digit=" + val1 + "&mathAction=" + val2;
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("resultExp").innerHTML = this.responseText;
                    document.getElementById("err").innerHTML = '';
                }
            };
            xhttp.open("POST", "calculator", false);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send(body);
        }
    }
</script>
</body>
</html>
