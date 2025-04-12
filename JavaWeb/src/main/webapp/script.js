// Función para capturar el clic en los botones de eliminación
document.querySelectorAll('.delete-btn').forEach(button => {
  button.addEventListener('click', function(event) {
      const form = this.closest('form'); // Obtener el formulario correspondiente

      // Mostrar el SweetAlert2 de confirmación
      Swal.fire({
          title: '¿Estás seguro que deseas eliminar la aerolínea?',
          text: '¡No podrás revertir esta acción!',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Sí, eliminar!',
          cancelButtonText: 'Cancelar'
      }).then((result) => {
          if (result.isConfirmed) {
              form.submit(); // Si el usuario confirma, se envía el formulario
          }
      });
  });
});