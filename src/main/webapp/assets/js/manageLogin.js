$(document).ready(function () {
    var alertDiv = $("#alertDiv");
    var alertHideBtn = $("#alertHideBtn");
    var alertMessageSpan = $("#alertMessageSpan");
    var captchaImage = $("#captchaImage");
    var inputCaptcha = $("#inputCaptcha");

    // hide alert div by default
    alertDiv.hide();

    // register event to alertHideBtn
    alertHideBtn.click(function () {
        alertDiv.hide();
    });

    // register event to captchaImage
    captchaImage.click(function () {
        updateCaptcha();
    });

    $('#inputUsername, #inputPassword, #inputCaptcha').keypress(function (event) {
        if(event.keyCode === 13) {
            login();
        }
    });

    $('#loginBtn').click(function () {
        login();
    });

    // login
    function login() {
        // report validity
        var valid = document.querySelector("form").reportValidity();
        if (!valid) {
            return;
        }
        // send post request
        $.post(BASE_PATH + '/manage/login', {
            username: $('#inputUsername').val(),
            password: $('#inputPassword').val(),
            captcha: inputCaptcha.val()
        }, function (data) {
            // if data is the error page
            if(typeof data !== 'object') {
                $('html').html(data);
                return;
            }
            if (data.success) {
                location.href = BASE_PATH + "/manage/index";
            } else {
                // clear captcha text
                inputCaptcha.val("");
                // change captcha
                updateCaptcha();
                // show error message
                alertMessageSpan.text(data.data);
                alertDiv.show();
            }
        });
    }

    // update captcha
    function updateCaptcha() {
        var suffix = "?id=" + new Date().getTime();
        var src = captchaImage.attr("src");
        if (src.indexOf("?") >= 0) {
            captchaImage.attr("src", src.split("?")[0] + suffix);
        } else {
            captchaImage.attr("src", src + suffix);
        }
    }
});