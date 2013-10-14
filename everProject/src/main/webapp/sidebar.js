sidebar_onload = function() {
	if ($("#sessionLevel").val() == 1) {
		$("#adminSideMenu").css("display", "");
		$("#generalSideMenu").css("display", "none");
	} else {
		$("#generalSideMenu").css("display", "");
		$("#adminSideMenu").css("display", "none");
	}
	
	loadMyProjects();
	
	$("#sidebarProject").click( function() {
		var event = new MouseEvent('projectManagement', {
		    'view': window,
		    'bubbles': true,
		    'cancelable': true
		  });
		this.dispatchEvent(event);
	});

	$("#sidebarMember").click( function() {
		var event = new MouseEvent('memberManagement', {
			'view': window,
			'bubbles': true,
			'cancelable': true
		});
		this.dispatchEvent(event);
	});
};

