$(document).ready(function () {
    $("#add-buddy").on("click", function() {
        const id = $("#addressBookId").val();  // get hidden input value
        const name = $("#name").val();
        const phone = $("#phone").val();
        const address = $("#address").val();

        const data = {
            name: name,
            phoneNum: phone,
            address: address
        };

        $.ajax({
            type: "POST",
            url: `http://localhost:8080/addressbook/${id}/spaAddBuddy`,
            data: JSON.stringify(data),
            contentType: "application/json"
        }).done(function(resp) {
            if (resp) {
                $(".list").append(`<p>${resp}</p>`);
                $("#name").val("");
                $("#phone").val("");
                $("#address").val("");
            }
        }).fail(function() {
            alert("Error adding buddy!");
        });
    });
});
