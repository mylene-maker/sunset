$("#addEmplacementButton").click(function () {
    let index = $('.collectionForm').length - 1;
    divToCopy = $('.collectionForm').last();

    newDiv = divToCopy.html().replace('name="items_' + index + '_file"', 'name="items_' + parseInt(index + 1) + '_file"');
    newDiv = newDiv.replace('name="items_' + index + '_equipment"', 'name="items_' + parseInt(index + 1) + '_equipment"');
    newDiv = newDiv.replace('Parassol ' + parseInt(index + 1), 'Parassol ' + parseInt(index + 2));

    // Add a delete button
    newDiv = '<div class="collectionForm">' + newDiv + '<button class="deleteEmplacementButton btn btn-danger mt-1" type="button">Supprimer l\'emplacement</button></div>';

    // Add this new div to the collection
    $('#collectionEquipment').append(newDiv);

});

// Add a click event for the delete button
$(document).on('click', '.deleteEmplacementButton', function () {
    $(this).closest('.collectionForm').remove();
});

// Modal update user info
$(document).ready(function () {
    $("#showModalBtn").click(function () {
        $("#myModal").modal('show');
    });

    $("#close").click(function (){
        $("#myModal").modal('hide')
    })
});

// Modal add location

$(document).ready(function() {
    $(".showModalEmplacement").click(function () {
        $("#myModalEmplacement").modal('show');
    });
    $("#close").click(function (){
        $("#myModalEmplacement").modal('hide')
    })
});