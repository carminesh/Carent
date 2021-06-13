$(document).ready (
	() => {
		
		$("#container-form-email").toggle();
		$("#container-form-password").toggle();
		
		$("#modificaemailButton").click (
			() => {
				if (!$("#container-form-email").is(":visible"))
					$("#container-form-email").fadeIn();
				else
					$("#container-form-email").fadeOut();
			}
		);
		
		$("#modificapassButton").click (
			() => {
				if (!$("#container-form-password").is(":visible"))
					$("#container-form-password").fadeIn();
				else
					$("#container-form-password").fadeOut();
			}
		);
	}
)