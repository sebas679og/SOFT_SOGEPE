let editors = [];
	
		document.querySelectorAll('textarea').forEach((textarea) => {
		    ClassicEditor
		        .create(textarea)
		        .then(editor => {
		            editors.push(editor);
		            // Remove required attribute from textarea
		            textarea.removeAttribute('required');
		        })
		        .catch(error => {
		            console.error(error);
		        });
		});
	
		document.querySelector('form').addEventListener('submit', function(event) {
		    event.preventDefault(); // Prevent form from submitting immediately
	
		    let isValid = true;
	
		    editors.forEach(editor => {
		        const editorData = editor.getData();
		        const textareaId = editor.sourceElement.id;
		        const textarea = document.getElementById(textareaId);
		        
		        // Update textarea value
		        textarea.value = editorData;
		        
		        // Check if the editor is empty
		        if (editorData.trim() === '') {
		            isValid = false;
		            // You can add custom error handling here, e.g., highlighting the editor
		            editor.sourceElement.classList.add('error');
		        } else {
		            editor.sourceElement.classList.remove('error');
		        }
		    });
	
		    if (isValid) {
		        // If all editors have content, submit the form
		        this.submit();
		    } else {
		        alert('Please fill in all required fields.');
		    }
		});