// Opera 8.0+
var isOpera = (!!window.opr && !!opr.addons) || !!window.opera || navigator.userAgent.indexOf(' OPR/') >= 0;
    // Firefox 1.0+
var isFirefox = typeof InstallTrigger !== 'undefined';
    // At least Safari 3+: "[object HTMLElementConstructor]"
var isSafari = Object.prototype.toString.call(window.HTMLElement).indexOf('Constructor') > 0;
    // Internet Explorer 6-11
var isIE = /*@cc_on!@*/false || !!document.documentMode;
    // Edge 20+
var isEdge = !isIE && !!window.StyleMedia;
    // Chrome 1+
var isChrome = !!window.chrome && !!window.chrome.webstore;
    // Blink engine detection
var isBlink = (isChrome || isOpera) && !!window.CSS;

$(document).ready(function () {
    
//    $('#conteinerTextosDefault').textition({
//        handler: 'mouseenter mouseleave',
//        animation: 'ease-in-out',
//        speed: 0.5,
//        map: {x: 100, y: 0, z: 0},
//        element: $('#divEsquerda')
//    });
//    
//    $('#conteinerTextosDefault').textition({
//        handler: 'mouseenter mouseleave',
//        animation: 'ease-in-out',
//        speed: 0.5,
//        map: {x: 100, y: 0, z: 0},
//        element: $('#divDireita')
//    });
    
    varCorDesactive = "#c0c0c0";
    varWidthDefault = "33%";
    varWidthActive = "50%";
    varWidthDesactive = "16%";
    varEasing = "linear";
    varDuracao = 0;
    
    if (isIE || isEdge) {
        varDuracao = 500;
    }
    
    varTextoDefault = document.getElementById("textoDefault").innerHTML;
    
    function pararDivs() {
        $("#divEsquerda").velocity("stop", true);
        $("#divCorEsquerda").velocity("stop", true);
        $("#divDireita").velocity("stop", true);
        $("#divCorDireita").velocity("stop", true);
        $("#botoesEsquerda").velocity("stop", true);
        $("#botoesDireita").velocity("stop", true);
        $("#divBotaoEmpresa").velocity("stop", true);
        $("#divBotaoVendedor").velocity("stop", true);
        $("#divTextoEsquerda").velocity("stop", true);
        $("#divTextoDireita").velocity("stop", true);
    }
    
    var moveDivDefault = [
        {e: $('#divEsquerda'), p: {width: varWidthDefault}, options: {duration: varDuracao,easing: varEasing}},
        {e: $('#divDireita'), p: {width: varWidthDefault}, options: {duration: varDuracao,easing: varEasing,sequenceQueue: false}}
    ];
    var moveDivEsqActive = [
        {e: $('#divEsquerda'), p: {width: varWidthActive}, options: {duration: varDuracao,easing: varEasing}},
        {e: $('#divDireita'), p: {width: varWidthDesactive}, options: {duration: varDuracao,easing: varEasing,sequenceQueue: false}}
    ];
    var moveDivDirActive = [
        {e: $('#divDireita'), p: {width: varWidthActive}, options: {duration: varDuracao,easing: varEasing}},
        {e: $('#divEsquerda'), p: {width: varWidthDesactive}, options: {duration: varDuracao,easing: varEasing,sequenceQueue: false}}
    ];

    var moveTeste = [
        {e: $('.sloganDefault'), p: {opacity: 0, height: 0}, options: {duration: 100,easing: varEasing}},
        {e: $('.textoEmpresa'), p: {opacity: 1}, options: {duration: 100,delay: 1000,easing: varEasing}}
    ];
    
    (function() {
        $('#divEsquerda').hover( function() {

            pararDivs();
            $.Velocity.RunSequence(moveDivEsqActive);
            $.Velocity.animate($("#botoesEsquerda"),{left: "0px",opacity: 1}, varDuracao);
            $('#botoesEsquerda').toggleClass('animated flipInY');
            $.Velocity.animate($("#divBotaoVendedor"),{opacity: 0}, varDuracao);
            $.Velocity.animate($("#divTextoDireita"),{opacity: 0}, varDuracao);
            
            $("#textoDefault").html("De grandes corporações até micro empresários, todos são importantes para o desenvolvimento deste país, nós também pazemos parte disso, levamos seus produtos à varias regiões do Brasil proporcionando uma venda fácil e lucrativa para todos.");
            
            $("#btPequenasEmpresas").attr("disabled", false);
            $("#btMediasEmpresas").attr("disabled", false);
            $("#btGrandesEmpresas").attr("disabled", false);
            $("#btPfVendedores").attr("disabled", true);
            $("#btPjVendedores").attr("disabled", true);
            
            
            
/*
//            $("#textoEmpresa").html("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
            var heightTextoDefault = parseInt(($("#textoDefault").css( "height" ).split('px').join('')), 10);
//            var heightTextoEmpresa = parseInt(($("#textoEmpresa").css( "height" ).split('px').join('')), 10);
            
            pararDivs();
            $.Velocity.RunSequence(moveDivEsqActive);
            $.Velocity.animate($("#botoesEsquerda"),{left: "0px",opacity: 1}, varDuracao);
            $('#botoesEsquerda').toggleClass('animated flipInY');
            $.Velocity.animate($("#divBotaoVendedor"),{opacity: 0}, varDuracao);
            $.Velocity.animate($("#divTextoDireita"),{opacity: 0}, varDuracao);
            
            $('#textoDefault').animate({
                'opacity' : 0
            }, 400, function(){
                $(this).html('TESTE').animate({'opacity': 1}, 400);});
*/            
//            $.Velocity.animate($("#textoDefault"),{opacity: 0, height: 0}, 50);
//            setTimeout(function() {
//                document.getElementById("textoDefault").innerHTML = document.getElementById("textoEmpresa").innerHTML;
//            }, 100);
//            var soma = parseInt(($("#textoDefault").css( "height" ).split('px').join('')), 10) + parseInt(($("#divDefault").css( "height" ).split('px').join('')), 10);
//            setTimeout(function() {
//                $.Velocity.animate($("#divDefault"),{opacity: 1, height: "+="+ (heightTextoEmpresa - heightTextoDefault) + "px"}, 80);
//            }, 100);
            
//            
//        $("div").velocity(
//          { 
//            scale: 1.5
//          },
//          {
//             duration: 5000,
//                        progress: function(elements, percentComplete, timeRemaining, timeStart) {
//                          $percentComplete.html(Math.round(percentComplete * 100) + "% complete.");
//                          $timeRemaining.html(timeRemaining + "ms remaining.");
//                                                        }
//                });
            
            
//            $.Velocity.animate($("#textoDefault"),{opacity: 0, height: 0}, 100);
//            $('#textoDefault').html('Hello World').delay( 8000 );
//            $('#textoDefault').empty().append('Hello World');
//            document.getElementById("textoDefault").innerHTML = document.getElementById("textoEmpresa").innerHTML;
            
//            alert($("#divDefault").text());
//            alert($(".produtosIntTitulo").text());
            
            
//            var divDefaultHeight = $("#divDefault").css("height");
//            var sloganDefaultHeight = $(".sloganDefault").css("height");
//            var sub1 = parseInt(($("#divDefault").css( "height" ).split('px').join('')), 10) - parseInt(($(".sloganDefault").css( "height" ).split('px').join('')), 10);
//            
//            $.Velocity.animate($("#divDefault"),{height: sub1 + "px"}, 100);
//            $(".sloganDefault").velocity("transition.fadeOut");
//            
//            
//                     
////            $.Velocity.animate($(".sloganDefault"),{opacity: 0, height: 0}, 100);
////            
//            var divDefaultHeight = $("#divDefault").css("height");
//            var textoEmpresaHeight = $(".textoEmpresa").css("height");
//            var soma = parseInt(($(".textoEmpresa").css( "height" ).split('px').join('')), 10) + parseInt(($("#divDefault").css( "height" ).split('px').join('')), 10);
//            
////            
////            $.Velocity.animate($("#divDefault"),{height: 200 + "px"}, 100);
//////            $.Velocity.animate($(".textoEmpresa"),{display: "block"}, 100);
//            $(".textoEmpresa").velocity("transition.fadeIn");
            
//            $.Velocity.RunSequence(moveTeste);
            
            
            
//            $.Velocity.animate($(".sloganDefault"),{opacity: 0,height: 0}, 100);
//            $.Velocity.animate($(".textoEmpresa"),{opacity: 1,height: "100%"}, 100);
            
            
//            $.Velocity.animate($(".textoEmpresa"),{opacity: 1,height: "100%"}, 100,1000);
            
//            $(".textoEmpresa").velocity(
//              { 
//                opacity: 1,
//                height: "100%"
//              },
//              { 
//                duration: 100,
//                delay: 2000
//              });
            
            
//            $.Velocity.animate($("#newTeste"),{display: "block"}, 0);
//            $("#newTeste").velocity({ opacity: 1 }, { display: "block" });
//            $("#newTeste").velocity({ left: "-10px", opacity: 1 }, { display: "block" });
//            $("#newTeste").velocity("transition.fadeIn");
        }, function() {

            pararDivs();
            $.Velocity.RunSequence(moveDivDefault);
            $.Velocity.animate($("#botoesEsquerda"),{left: "-500%",opacity: 0}, varDuracao);
            $('#botoesEsquerda').toggleClass('animated flipInY');
            $.Velocity.animate($("#divBotaoVendedor"),{opacity: 1}, varDuracao);
            $.Velocity.animate($("#divTextoDireita"),{opacity: 1}, varDuracao);

            $("#textoDefault").html("Leve seus produtos à várias regiões do país.<br>O mercado que está a sua procura.");
            
            $("#btPequenasEmpresas").attr("disabled", true);
            $("#btMediasEmpresas").attr("disabled", true);
            $("#btGrandesEmpresas").attr("disabled", true);
            $("#btPfVendedores").attr("disabled", true);
            $("#btPjVendedores").attr("disabled", true);
/*
            $("#textoEmpresa").html("");
            var heightTextoDefault = parseInt(($("#textoDefault").css( "height" ).split('px').join('')), 10);
            var heightTextoEmpresa = parseInt(($("#textoEmpresa").css( "height" ).split('px').join('')), 10);
            pararDivs();
            $.Velocity.RunSequence(moveDivDefault);
            $.Velocity.animate($("#botoesEsquerda"),{left: "-500%",opacity: 0}, varDuracao);
            $('#botoesEsquerda').toggleClass('animated flipInY');
            $.Velocity.animate($("#divBotaoVendedor"),{opacity: 1}, varDuracao);
            $.Velocity.animate($("#divTextoDireita"),{opacity: 1}, varDuracao);
*/            
//            $.Velocity.animate($("#textoDefault"),{opacity: 0, height: 0}, 50);
//            setTimeout(function() {
//                document.getElementById("textoDefault").innerHTML = "O mercado que está a sua procura";
//            }, 300);
//            
//            var soma = parseInt(($("#textoDefault").css( "height" ).split('px').join('')), 10) + parseInt(($("#divDefault").css( "height" ).split('px').join('')), 10);
//            setTimeout(function() {
//                $.Velocity.animate($("#textoDefault"),{opacity: 1, height: "0px"}, 50);
//            }, 100);
            
        });
        
        $('#divDireita').hover( function() {
            pararDivs();
            $.Velocity.RunSequence(moveDivDirActive);
            $.Velocity.animate($("#botoesDireita"),{left: "0px",opacity: 1}, varDuracao);
            $('#botoesDireita').toggleClass('animated flipInY');
            $.Velocity.animate($("#divBotaoEmpresa"),{opacity: 0}, varDuracao);
            $.Velocity.animate($("#divTextoEsquerda"),{opacity: 0}, varDuracao);
            
            $("#textoDefault").html("O elo entre o fornecedor e o cliente não existiria sem você, representante comercial.<br>Venha fazer parte deste grupo, é muito fácil e sem burocracia, esperamos você.");
            
            $("#btPequenasEmpresas").attr("disabled", true);
            $("#btMediasEmpresas").attr("disabled", true);
            $("#btGrandesEmpresas").attr("disabled", true);
            $("#btPfVendedores").attr("disabled", false);
            $("#btPjVendedores").attr("disabled", false);            
        }, function() {
            pararDivs();
            $.Velocity.RunSequence(moveDivDefault);
            $.Velocity.animate($("#botoesDireita"),{left: "500%",opacity: 0}, varDuracao);
            $('#botoesDireita').toggleClass('animated flipInY');
            $.Velocity.animate($("#divBotaoEmpresa"),{opacity: 1}, varDuracao);
            $.Velocity.animate($("#divTextoEsquerda"),{opacity: 1}, varDuracao);
            
            $("#textoDefault").html("Leve seus produtos à várias regiões do país.<br>O mercado que está a sua procura.");
            
            $("#btPequenasEmpresas").attr("disabled", true);
            $("#btMediasEmpresas").attr("disabled", true);
            $("#btGrandesEmpresas").attr("disabled", true);
            $("#btPfVendedores").attr("disabled", true);
            $("#btPjVendedores").attr("disabled", true);
        });
        
    })();
    
    $('#btPequenasEmpresas').hover( function() {
        $("#textoDefault").html("Você, pequeno empresário, precisa mais do que nunca expandir sua área de atuação, para isto estamos à sua disposição.<br>Venha conhecer a mais nova empresa de marketplace do Brasil.");
    }, function() {
        $("#textoDefault").html("De grandes corporações até micro empresários, todos são importantes para o desenvolvimento deste país, nós também pazemos parte disso, levamos seus produtos à varias regiões do Brasil proporcionando uma venda fácil e lucrativa para todos.");
    });
    
    $('#btMediasEmpresas').hover( function() {
        $("#textoDefault").html("Você, dono de uma empresa de médio porte, precisa aumentar os horizontes de seu comércio.<br>Venha conhecer a mais nova empresa de marketplace do Brasil.");
    }, function() {
        $("#textoDefault").html("De grandes corporações até micro empresários, todos são importantes para o desenvolvimento deste país, nós também pazemos parte disso, levamos seus produtos à varias regiões do Brasil proporcionando uma venda fácil e lucrativa para todos.");
    });
    
    $('#btGrandesEmpresas').hover( function() {
        $("#textoDefault").html("As grandes empresas e corporações precisam manter e ampliar sua área de atendimento, e o mais importante, reduzir os custos.<br>Venha conhecer a mais nova empresa de marketplace do Brasil.");
    }, function() {
        $("#textoDefault").html("De grandes corporações até micro empresários, todos são importantes para o desenvolvimento deste país, nós também pazemos parte disso, levamos seus produtos à varias regiões do Brasil proporcionando uma venda fácil e lucrativa para todos.");
    });
    
    $('#btPfVendedores').hover( function() {
        $("#textoDefault").html("Seja um campeão de vendas. A Justprofit está esperando por você.<br>Venha conhecer a mais nova empresa de marketplace do Brasil.");
    }, function() {
        $("#textoDefault").html("O elo entre o fornecedor e o cliente não existiria sem você, representante comercial.<br>Venha fazer parte deste grupo, é muito fácil e sem burocracia, esperamos você.");
    });
    
    $('#btPjVendedores').hover( function() {
        $("#textoDefault").html("Faça de sua empresa uma campeã de vendas. A Justprofit está esperando por você.<br>Venha conhecer a mais nova empresa de marketplace do Brasil.");
    }, function() {
        $("#textoDefault").html("O elo entre o fornecedor e o cliente não existiria sem você, representante comercial.<br>Venha fazer parte deste grupo, é muito fácil e sem burocracia, esperamos você.");
    });
    
});




















//                $('#divEsquerda').velocity('stop').velocity({width: "50%"},{duration: 0,easing: "linear"});
//                $('#divDireita').velocity('stop').velocity({width: "16%"},{duration: 0,easing: "linear"});
//                $('#divDireita').addClass('divDesactive');



//    $('#divEsquerda').on({
//        mouseover: function() {
//            $('#divEsquerda').velocity({width: "50%"},{duration: 0});
//            $('#divDireita').velocity({width: "16%"},{duration: 0});
//        },
//        mouseout: function() {
//            $('#divEsquerda').velocity({width: "33%"},{duration: 0});
//            $('#divDireita').velocity({width: "33%"},{duration: 0});
//        }
//    });
//    
//    $('#divDireita').on({
//        mouseover: function() {
//            $('#divDireita').velocity({width: "50%"},{duration: 0});
//            $('#divEsquerda').velocity({width: "16%"},{duration: 0});
//        },
//        mouseout: function() {
//            $('#divEsquerda').velocity({width: "33%"},{duration: 0});
//            $('#divDireita').velocity({width: "33%"},{duration: 0});
//        }
//    });


//$('#divEsquerda').mouseenter(function () {
////   $(this).addClass('hover'); $('.hover').velocity({boxShadowBlur:15},{
////        duration: 50
////    });
////  $('.hover img').velocity({scale:1.25},{
////        duration: 150
////    });
////  $('.hover figcaption').velocity('transition.perspectiveRightIn', {duration:100, delay:100});
////  $('.hover .figcaption-wrap').velocity('transition.perspectiveRightIn', {delay:100});
// }).mouseleave(function () {
////  $('.hover,.hover figcaption,.hover .figcaption-wrap, .hover img').velocity("stop");
////    $('.hover,.hover figcaption,.hover .figcaption-wrap, .hover img').velocity('reverse'); 
////  $(this).removeClass('hover');
//});


//function mOver(obj) {
//    
////    $('#divEsquerda').velocity({width: "50%"},{duration: 0});
////    $('#divDireita').velocity({width: "16%"},{duration: 0});
//    
//    var mySequence1 = [
//        { e: $('#divEsquerda'), p: { width: "50%" }, options: { duration: 0 }},
//        { e: $('#divDireita'), p: { width: "16%" }, options: { duration: 0, sequenceQueue: false } }
//      ];
//
//    $.Velocity.RunSequence(mySequence1);
//
//}
//
//function mOut() {
//    $('#divEsquerda').velocity({width: "33%"},{duration: 0});
//    $('#divDireita').velocity({width: "33%"},{duration: 0});
//}


//
//
//
//$('#divEsquerda').hover(function() {
//
//var mySequence1 = [
//    { e: $('#divEsquerda'), p: { width: "50%" } },
//    { e: $('#divDireita'), p: { width: "16%" }, options: { sequenceQueue: false } }
//  ];
//
//$.Velocity.RunSequence(mySequence1);
//
//}, function() {
//    
//var mySequence1 = [
//    { e: $('#divEsquerda'), p: { width: "33%" } },
//    { e: $('#divDireita'), p: { width: "33%" }, options: { sequenceQueue: false } }
//  ];
//
//$.Velocity.RunSequence(mySequence1);
//
//});

















//$('#divEsquerda').velocity({
//    width: "50%"
//});
//
//setInterval(function() {
//  $('#divEsquerda')
//      .velocity("transition.slideLeftIn", 1250)
//      .delay(750)
//      .velocity({ opacity: 0 }, 750);
//}, 2000);