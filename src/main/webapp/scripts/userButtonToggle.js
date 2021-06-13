$(document).ready (
	() => {
		
		$("#form-email").toggle();
		$("#form-password").toggle();
		
		$("#modificaemailButton").click (
			() => {
				$("#form-email").toggle();
			}
		);
		
		$("#modificapassButton").click (
			() => {
				$("#form-password").toggle();
			}
		);
	}
)