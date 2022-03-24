function init() {
    $("img.poke-img").click(function(event) {
        if ($(event.target).css("opacity") == 0.2)
            $(event.target).css("opacity", "1.0")
        else
            $(event.target).css("opacity", "0.2")
    });
}

var scriptElement = document.createElement('script');
scriptElement.src = 'http://code.jquery.com/jquery-3.4.1.min.js';

scriptElement.onloadDone = false;
scriptElement.onload = function() {
    scriptElement.onloadDone = true;
    init();
}
scriptElement.onreadystatechange = function() {
    if ( ("loaded" === scriptElement.readyState || "complete" === scriptElement.readyState ) && !scriptElement.onloadDone ) {
        scriptElement.onloadDone = true;
        init();
    }
};
document.getElementsByTagName('head')[0].appendChild(scriptElement);
