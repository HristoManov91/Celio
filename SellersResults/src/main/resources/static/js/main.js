$(document).ready(function () {
    let max_fields = 100;
    let index = 1;


    $('.add-product').click(function () {

        if (index < max_fields) {
            let newRow = jQuery('<div class="product row">' +
                ' <div class="col-lg-6 mb-3">' +
                ' Name' +
                ' <input name="products[' + index + '].name" type="text" class="form-control form-control-lg"/>' +
                ' </div>' +
                // ' <div class="col-lg-6 mb-3">' +
                // '  Quantity(grams)\n' +
                // ' <input name="products[' + index + '].quantity" type="number" step="0.1" min="0"' +
                // ' class="form-control form-control-lg"/>' +
                // '</div>' +
                '</div>');

            index++;

            $('.products').append(newRow)
        }

    });
});