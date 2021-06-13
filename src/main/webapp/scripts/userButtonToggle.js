$(document).ready (
	() => {
		
		$("#container-form-email").toggle();
		$("#container-form-password").toggle();
		
		$("#modificaemailButton").click (
			() => {
				$("#container-form-email").toggle();
			}
		);
		
		$("#modificapassButton").click (
			() => {
				$("#container-form-password").toggle();
			}
		);
	}
)