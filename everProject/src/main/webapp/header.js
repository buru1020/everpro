header_onload = function() {
	loadLoginInfo();
	loadMyProjects();
	
	$("#logout").click(function(event) {
		event.preventDefault();		//원래 태그가 하는일을 하지 못하도록 한다.
		
		$.ajax("auth/logout.do", {
			type: "GET",
			success: function(result) {
				if (result.status == "success") {
					location.href = "auth/login.html";
				}
			}
		});
	});

	$("#navProject").click( function() {
		var event = new MouseEvent('projectManagement', {
		    'view': window,
		    'bubbles': true,
		    'cancelable': true
		  });
		this.dispatchEvent(event);
	});
	
	
};




