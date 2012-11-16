$(document).ready(function() {
    
    
    //    $.get("/OhHa/harjoittelija/harjoitus",function(data){
    //        $("#sisalto_laatikko").html(data);
    //    });
    
    $(".tab_kirjaudu").click(function(event) {
        
        $.get($(".tab_kirjaudu").attr("data-value"),function(data){
          
            $("#sisalto_laatikko").html(data);
        })
    });
    
    $(".tab_rekisteroidy").click(function(event) {
        
        $.get($(".tab_rekisteroidy").attr("data-value"),function(data){
         
            $("#sisalto_laatikko").html(data);
        })
    });
    
    $(".tab_seuranta").click(function(event) {
        
        $.get($(".tab_seuranta").attr("data-value"),function(data){
         
            $("#sisalto_laatikko").html(data);
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
    
    $(document).on("click", ".rekisteroidy_nappula",function(){
        var vastaus;
   
        $.post("rekisterointi",$("#harjoittelija").serialize(),function(data){
            if(data.indexOf('<!--"fail"-->')>-1){
                $("#sisalto_laatikko").html(data);
                vastaus = data;
            }else{
                window.location.replace("home");
            }
           
        });
        return false;
    });
  
 
    $(document).on("click",".seuranta_nappula",function(){
        
        $.post("seuranta", $("#seuranta_avain").serialize(),function(data){
            $("#sisalto_laatikko").html(data);
          
            
            return false;
        });
        
        return false;
    } );
   
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
        console.log($("#aikavali").serialize());
        $.post("harjoittelija/tilasto", $("#aikavali").serialize(), function(data){
            $("#tilasto_table").html(data);
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
 
 
    $(document).on('click',".selaa",function(){
        $.get($(this).attr("data-value"),function(data){ 
          $("#selaa").html(data);
        });
   
        return false;
    })
    



} );








function tarkastaArvo(){
    var arvo = document.getElementById("avaimet");

    var kentta = document.getElementById("kopioitava");
    var splitattu = arvo[arvo.selectedIndex].innerHTML.split(" ")
    kentta.innerHTML=splitattu[2];
}



