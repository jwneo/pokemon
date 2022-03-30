function init() {
    $(document).on("click tap", "img.poke-img", function(event) {
        var className = "collect";

        if (!$(event.target).hasClass(className))
            $(event.target).addClass(className);
        else
            $(event.target).removeClass(className);

        var pokedex = document.querySelectorAll("img.poke-img");
        var pokeList = document.querySelectorAll("img.poke-img." + className);

        $(".count").text(pokeList.length + " / " + pokedex.length);
        $(".d-none").text("/" + Array.from(pokeList).map(o => o.title).join('/') + "/");
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
