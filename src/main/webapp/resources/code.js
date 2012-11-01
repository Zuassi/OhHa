$(document).ready(function() {
    $(document).on('submit', '#harjoitus', function() {
        $.post("harjoittelija/harjoitus", $("#harjoitus").serialize(),function(data){
          
            var content = $(data).find("#harjoitus_table");
            $("#harjoitus_table").replaceWith(content);

            var selaa = $(data).find("#selaa_table");
            $("#selaa_table").replaceWith(selaa);
        
        });
        return false;
    });       
    
    $(document).on('submit', '#seuranta-avaimet', function() {
         
        $.post("harjoittelija/asetukset/poista_avain", $("#seuranta-avaimet").serialize(),function(data){
            $("#seuranta-avaimet").replaceWith(data);
            
        });   
        return false;
    });
    
    $(document).on('submit', '#luoAvain', function() {
        $.post("harjoittelija/asetukset/luo_avain", $("#luoAvain").serialize(),function(data){
            console.log(data);
            $("#seuranta-avaimet").replaceWith(data);
        });
        return false;
    });
 
        
}); 


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


