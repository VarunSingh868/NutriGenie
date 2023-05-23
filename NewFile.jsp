<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form1</title>
</head>
<body>
<form method="post" action="demopt1">
<pre>
Enter Name:   <input type="text" name="name"><br>
Enter Age:    <input type="number" name="age"><br>
Enter Height: <input type="number" name="height"><br>
Enter Weight: <input type="number" name="weight"><br>
Enter Email:  <input type="email" name="email"><br>
Choose Your Goal:<select name="goal">
<option>Weight Gain</option>
<option>Weight Loss</option>
<option>Muscle Gain</option>
</select> <br>
<input type="submit" value="submit">        <input type="reset" value="reset">
</pre>
</form>
</body>
</html>