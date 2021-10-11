$('#modalExcluir').on('show.bs.modal', function(event) {

	var button = $(event.relatedTarget);

	var codigoTitulo = button.data('id');


	var descricaoTitulo = button.data('descricao');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoTitulo);

	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({ decimal: ',', thousands: '.', allowZero: true });

	$('.datepicker').datepicker({
		startDate: '-3d'
	});

	$('.js-atualizarStatus').on('click', function(event) {

		event.preventDefault();
		var botaoReceber = $(event.currentTarget);
		var urlRecber = botaoReceber.attr('href');
		
	var response=$.ajax({
			url: urlRecber,
			type:'PUT'
			
		});
		
		response.done(function(e){
			var idTitulo = botaoReceber.data('id')
			$('[data-role=' + idTitulo +']').html('<span class="label label-success">'+ e +'</span>');
			botaoReceber.hide();
		});
		response.fail(function(e){
			console.log(e)
		});
	});

});