$(function() {
	if (session.level == 1) {
		$("#adminSideMenu").css("display", "");
		$("#generalSideMenu").css("display", "none");
	} else {
		$("#generalSideMenu").css("display", "");
		$("#adminSideMenu").css("display", "none");
	}
	
	loadMyProjects();
	
	$(".sideMyProjects").on("click", ".clickProject", function() {
		$(this).trigger("viewProject", [$(this).attr("data-no")]);
	});
	
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
});

