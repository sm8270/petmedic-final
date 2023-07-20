function userLoginCheck(){

    var id = $("#inputUserName").val();
    var pw = $("#inputPassword").val();
    var asd = {users_id: id, users_pw:pw};
    console.log(asd);
    if (id === "" || pw === "") {
        return;
    }
    $.ajax({
        method: "POST",
        url: "login",
        data: asd,
        cache: false,
        success: function (data) {
            console.log('data.loginError:'+data.loginError);
            if (data.loginError=="true") {
                console.log('1');
                $("#nologin").show();
            } else {
                location.reload();
            }
        },
        error: function (request, status) {
            alert("오류가 발생했습니다.");
        }
    });
}

function hosLoginCheck(){
    var id = $("#inputHosId").val();
    var pw = $("#inputhosPassword").val();
    var asd = {hos_id: id, hos_pw:pw}
    console.log(asd);
    if (id === "" || pw === "") {
        return;
    }
    $.ajax({
        method: "POST",
        url: "hoslogin",
        data: asd,
        cache: false,
        success: function (data) {
            console.log('data.loginError:'+data.loginError);
            if (data.loginError=="true") {
                console.log('1');
                $("#nologin2").show();
            } else {
                $("#nologin").hide();
                location.reload();
            }
        },
        error: function (request, status) {
            alert("오류가 발생했습니다.");
        }
    });
}


