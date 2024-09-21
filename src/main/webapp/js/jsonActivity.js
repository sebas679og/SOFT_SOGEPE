$(document).ready(function() {
    $('#area').change(function() {
        let idArea = $(this).val();

        $.ajax({
            url: servletUrl,
            type: 'GET',
            data: { idArea: idArea },
            dataType: 'json',
            success: function(response) {
                $('#actividad').empty();
                $('#actividad').append('<option value="" disabled selected>Seleccionar Actividad</option>');

                $.each(response, function(index, actividad) {
                    $('#actividad').append('<option value="' + actividad.idActividades + '">' + actividad.actividad + '</option>');
                });

                // Disparar un evento personalizado cuando las actividades se hayan cargado
                $('#actividad').trigger('actividadesCargadas');
            },
            error: function(xhr, status, error) {
                console.error('Error al obtener actividades:', error);
                console.error('Estado:', status);
                console.error('Respuesta:', xhr.responseText);
            }
        });
    });
});