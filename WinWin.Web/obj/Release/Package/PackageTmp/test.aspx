<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="test.aspx.cs" Inherits="WinWin.Web.test" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>

    <script src="Scripts/jquery-1.9.1.min.js"></script>

    <script type="text/javascript">

        function login() {

            $.ajax({
                cache: false,
                url: '<%= HttpContext.Current.Request.Url.GetLeftPart(UriPartial.Authority) + HttpContext.Current.Request.ApplicationPath%>/api/login',
                type: "POST",
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                data: { Email: $('#user').val(), Password: $('#pass').val() },
                success: function (data) {

                    alert(data.Email);

                },
                error: function (xhr, status, error) {
                    alert(xhr.responseText);
                }
            });
        }

    </script>

</head>
<body>
    <form id="form1" runat="server">
        <div>

            <fieldset>
                <legend>Login</legend>
                <p>
                    <label for="user">
                        <input type="text" id="user" /></label>
                    <label for="pass">
                        <input type="password" id="pass" /></label>
                </p>
                <p>
                    <div onclick="login()">Login</div>
                </p>
            </fieldset>

        </div>

    </form>
</body>
</html>
