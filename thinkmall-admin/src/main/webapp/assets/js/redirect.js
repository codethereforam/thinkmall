$(document).ready(function () {
    var timeSpan = $("#timeSpan");
    var time = parseInt(timeSpan.text());
    var interval = setInterval(function () {
        --time;
        if (time === 0) {
            clearInterval(interval);
            location.href = BASE_PATH + REDIRECT_URL;
        }
        timeSpan.text(time);
    }, 1000);
});