window.onload = function() {
	$("#btnLogin").click(function(event) {
		
		$.ajax("login.do", {
			type: "POST",
			dataType: "json",
			data: {
				email: $("#login-email").val(),
				password: $("#password").val(),
				saveId: $("#saveId").is(":checked")
			},
			success: function(result) {
				if (result.status == "fail") {
					alert("이메일이나 암호가 맞지 않습니다.");
					
					$("#login-email").val("");
					$("#password").val("");
					$("#saveId").attr("checked", false);
				} else {
					window.location.href = "../main.html";
				}
			}
		});
		
	});
};
