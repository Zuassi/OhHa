$(document).ready(function() {
    
    
//    $.get("/OhHa/harjoittelija/harjoitus",function(data){
//        $("#sisalto_laatikko").html(data);
//    });
    
      $(".tab_kirjaudu").click(function(event) {
        
        $.get("kirjaudu",function(data){
            $(".keskitettava").html(data);
        })
    });
    
         $(".tab_rekisteroidy").click(function(event) {
        
        $.get("rekisterointi",function(data){
            $(".keskitettava").html(data);
        })
    });
        
        
    $(".tab_lisaa").click(function(event) {
        $.get("harjoittelija/harjoitus",function(data){
            $("#sisalto_laatikko").html(data);
        })
    });
    
            
  
    
    
    $(".tab_selaa").click(function(event){
        $.get("harjoittelija/selaa", function(data){
            $("#sisalto_laatikko").html(data);
        
        })
    });
    
    
        $(".tab_selaa").click(function(event){
        $.get("harjoittelija/selaa", function(data){
            $("#sisalto_laatikko").html(data);
        
        })
    });
    
    $(".tab_tilasto").click(function(event){
        $.get("harjoittelija/tilasto", function(data){
            $("#sisalto_laatikko").html(data);
        
        })
    });
    
    $(".tab_asetukset").click(function(event){
        $.get("harjoittelija/asetukset", function(data){
            $("#sisalto_laatikko").html(data);
        })
    });

    
    $(document).on("submit","#harjoitus", function(){
        $.post("harjoittelija/harjoitus",$(this).serialize(), function(data){
            $("#sisalto_laatikko").html(data).show().fadeIn(2000);
            if(data.indexOf("null")==-1){
                $("#harjoitus").clearForm();
               
               
                $("#harjoitus_lisatty").html("Harjoitus lisätty!");
                $("#harjoitus_lisatty").addClass("treeni_lisatty").show().fadeOut(3000);
                
            }
        });
      
        return false;
    });
  
    
    
    $(document).on("click",".poista",function(){
        
        $.get($(this).attr("data-value"),function(data){
            $("#sisalto_laatikko").html(data);
        });
        
      
    } );
    
    $(document).on("click",".asetukset_salasana",function(e){
    
        $.post("harjoittelija/asetukset/salasana",$("#vaihda_salasana").serialize(),function(data){
           $("#asetukset_salasana").html(data);
        });
        return false;
    } );



  

    $(document).on("click",".nayta",function(){
   
        $.get($(this).attr("data-value"),function(data){   
            $("#treenisisalto").html(data);
            $("#treenisisalto").addClass("treenisisalto").show("slow");
        });
    } );
    
    $(document).on("click",".muokkaa",function(){
      
        $.get($(this).attr("data-value"),function(data){  
            $("#muokkaa").addClass("hidden");
            $("#muokkaa").html(data).slideDown(550);
               
          
        });
        return false;
     
    } );
    
    $(document).on("click",".aseta_submit",function(){
        $.post("harjoittelija/tilasto", $(".tilasto"), function(data){
            $("#sisalto_laatikko").html(data);
        });
        return false;
    });
    
    
    
    $(document).on("submit",".muokkaus",function(){
        $.post("harjoittelija/harjoitus",$(".muokkaus"),function(data){
            $(".muokkaa").slideUp(1000);
            $("#selaa").html(data); 
          
         
        }); 
    });



    $(document).on('submit', '#seuranta-avaimet', function() {
         
        $.post("harjoittelija/asetukset/poista_avain", $("#seuranta-avaimet").serialize(),function(data){
            $("#seuranta-avaimet").replaceWith(data);
            
        });   
        return false;
    });
    
    $(document).on('submit', '#luoAvain', function() {
        $.post("harjoittelija/asetukset/luo_avain", $("#luoAvain").serialize(),function(data){
           
            $("#seuranta-avaimet").replaceWith(data);
        });
        return false;
    });
 
} );






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



