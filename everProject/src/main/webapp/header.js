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

	$("#navMember").click( function() {
		var event = new MouseEvent('memberManagement', {
			'view': window,
			'bubbles': true,
			'cancelable': true
		});
		this.dispatchEvent(event);
	});
	
	$("#updateMyInfo").click( function() {
		var event = new MouseEvent('updateMyInfo', {
			'view': window,
			'bubbles': true,
			'cancelable': true
		});
		this.dispatchEvent(event);
	});
	
	
};

function loadLoginInfo() {
	$("#userName").html("Hello " + session.name);
	$("#userTel").html( session.tel );
	$("#userEmail").html( session.email );
	if ($("#sessionPhoto").val() != undefined) {
		$("#userPhoto").attr("src", session.rootRealPath + "/res/photo/" + session.photo );
	} else {
		$("#userPhoto").attr("src", session.rootPath + "/img/img-profile.jpg");
	}
}




