$(document).ready(main);

var contador = 1;

function main(){
	$('.menu_bar').click(function(){
		if(contador == 1){
			$('nav').animate({
				left: '0'
			});

			$('.menu_bar').css('position','fixed');
			$('.menu_bar').css('background-color','#1b6229');
			$('.menu_bar').css('width','75%');
			/*INTERCAMBIO DE BOTONES*/
			$('#bars').css('display','none');
			$('#cerrar').css('display','block');
			$('.menu_bar').animate({
				top:'0'
			});
			contador = 0;
		} else {
			contador = 1;
			$('nav').animate({
				left: '-150%'
			});
			$('.menu_bar').css('position','relative');
			$('.menu_bar').css('width','auto');
			$('.menu_bar').css('background-color','transparent');//Ocultamos la barra	
			$('#cerrar').css('display','none');
			$('#bars').css('display','block');
			$('.menu_bar').animate({
				top:'-165'
			});
		}
 
	});

	$('.submenu').click(function(){
		$(this).children('.children').slideToggle();
 
	});
 
};