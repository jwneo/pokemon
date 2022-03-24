function init() {
    $(function(){
        $('img.poke-img').toggle(
            function(event){
            $(event.target).css('opacity', "1.0");
            },
            function(event){
            $(event.target).css('opacity', "0.5");
            });
    });
}

var scriptElement = document.createElement('script');
scriptElement.src = 'http://code.jquery.com/jquery-1.7.2.min.js';

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
