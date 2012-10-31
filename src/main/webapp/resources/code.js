


function init(number) {
    displayArticle(number);
}




function displayArticle(index) {
    var sections = document.getElementsByTagName("section");

    for(var i = 0; i < sections.length; i++) {
        if (index == i) {
            sections[i].className='';
        } else {
            sections[i].className='hidden';
        }
    }
}

function tarkastaArvo(){
    var arvo = document.getElementById("avaimet");

    var kentta = document.getElementById("kopioitava");
    var splitattu = arvo[arvo.selectedIndex].innerHTML.split(" ")
    kentta.innerHTML=splitattu[2];
}


